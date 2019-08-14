package uk.gov.gsi.justice.spg.test.template;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class BaseTest {
    protected String loadFileUTF8(String resourcePath) throws IOException, URISyntaxException {
        final Path filePath = getPath(resourcePath);
        return new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
    }

    protected URL getResourceUrl(String resourcePath) {
        return this.getClass().getResource(resourcePath);
    }

    protected String soapToString(SOAPMessage soapMessage) throws IOException, SOAPException {
        try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            soapMessage.writeTo(out);

            return new String(out.toByteArray(), StandardCharsets.UTF_8);
        }
    }

    private Path getPath(String resourcePath) throws URISyntaxException {
        final URI fileLocation = getResourceUrl(resourcePath).toURI();

        return Paths.get(fileLocation);
    }
}
