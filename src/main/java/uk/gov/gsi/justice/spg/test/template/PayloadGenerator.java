package uk.gov.gsi.justice.spg.test.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.gsi.justice.spg.test.template.model.Message;
import uk.gov.gsi.justice.spg.test.template.model.Notification;
import uk.gov.gsi.justice.spg.test.wss.SOAPMessageSecurer;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;

public class PayloadGenerator {
    private static final String SPG_TEST = "SPGTest";
    private static final String SYS = "SYS";
    private static final String RSP = "RSP";

    private final Logger logger;
    private final MessageRenderer messageRenderer;
    private final SOAPMessageSecurer messageSecurer;

    public static class Builder {
        private final ResourcePathResolver pathResolver = new ResourcePathResolver();
        private String keystoreOverride;
        private String aliasOverride;
        private String passwordOverride;

        public Builder() {
        }

        public PayloadGenerator.Builder withKeystore(Path keystore, String alias, String password) {
            this.keystoreOverride = keystore.toString();
            this.aliasOverride = alias;
            this.passwordOverride = password;

            return this;
        }

        public PayloadGenerator build() {
            final SOAPMessageSecurer soapMessageSecurer = Optional.ofNullable(keystoreOverride)
                    .map(x -> {
                            final Path path = pathResolver.getPath(x);
                            return new SOAPMessageSecurer.Builder()
                                    .withKeystore(path, aliasOverride, passwordOverride)
                                    .build();
                    })
                    .orElse(new SOAPMessageSecurer.Builder().build());

            return new PayloadGenerator(soapMessageSecurer);
        }
    }

    private PayloadGenerator(final SOAPMessageSecurer soapMessageSecurer) {
        this.logger = LoggerFactory.getLogger(PayloadGenerator.class);

        final TemplateRenderer templateRenderer = new TemplateRenderer();
        this.messageRenderer = new MessageRenderer(templateRenderer);

        this.messageSecurer = soapMessageSecurer;
    }

    public Notification createConnectionNotification(final Long senderControlRef,
                                                     final String version,
                                                     final String date,
                                                     final String from,
                                                     final String to,
                                                     final String protocol,
                                                     final String dateOfPreparation,
                                                     final String timeOfPreparation) throws Exception {
        final Message message = messageRenderer.renderConnectionNotification(
                version,
                date,
                senderControlRef,
                null,
                from,
                to,
                SPG_TEST,
                SYS,
                null,
                protocol,
                dateOfPreparation,
                timeOfPreparation
        );

        return new Notification(message.getSimpleXml(), signMessage(message.getSoapMessage()));
    }

    public Notification createConnectionNotification(final Long senderControlRef,
                                                     final String version,
                                                     final String date,
                                                     final String from,
                                                     final String to,
                                                     final Integer notificationCode,
                                                     final String protocol,
                                                     final String dateOfPreparation,
                                                     final String timeOfPreparation) throws Exception {
        final Message message = messageRenderer.renderConnectionNotification(
                version,
                date,
                senderControlRef,
                null,
                from,
                to,
                SPG_TEST,
                SYS,
                notificationCode,
                protocol,
                dateOfPreparation,
                timeOfPreparation
        );

        return new Notification(message.getSimpleXml(), signMessage(message.getSoapMessage()));
    }

    public String createSoapResponse(int code, String protocol) throws Exception {
        final Message message = messageRenderer.renderResponseMessage(code, protocol);

        return soapToString(message.getSoapMessage());
    }

    public Notification createResponseNotification(final Long senderControlRef,
                                                   final Long receiverControlRef,
                                                   final Integer code,
                                                   final String from,
                                                   final String to,
                                                   final String protocol,
                                                   final String schemaVersion,
                                                   final String schemaDate,
                                                   final String dateOfPreparation,
                                                   final String timeOfPreparation) throws Exception {
        final Message message = messageRenderer.renderStatusNotification(
                schemaVersion,
                schemaDate,
                senderControlRef,
                receiverControlRef,
                from,
                to,
                SPG_TEST,
                RSP,
                code,
                protocol,
                dateOfPreparation,
                timeOfPreparation
        );

        return new Notification(message.getSimpleXml(), signMessage(message.getSoapMessage()));
    }

    private String signMessage(SOAPMessage soapMessage) throws Exception {
        final SOAPMessage signedPayload = messageSecurer.signSOAPMessage(soapMessage);
        logger.info("Generated content::: {}{}", System.lineSeparator(), signedPayload);

        return soapToString(signedPayload);
    }

    private String soapToString(SOAPMessage soapMessage) throws IOException, SOAPException {
        try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            soapMessage.writeTo(out);

            return new String(out.toByteArray(), StandardCharsets.UTF_8);
        }
    }
}
