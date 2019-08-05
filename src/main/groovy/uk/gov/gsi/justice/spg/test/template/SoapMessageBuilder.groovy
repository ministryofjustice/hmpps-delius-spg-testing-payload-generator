package uk.gov.gsi.justice.spg.test.template

import groovy.transform.PackageScope
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.w3c.dom.Document
import org.xml.sax.SAXException
import uk.gov.gsi.justice.spg.test.template.model.Message

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.soap.MessageFactory
import javax.xml.soap.SOAPException
import javax.xml.soap.SOAPMessage

import static javax.xml.soap.SOAPConstants.SOAP_1_1_PROTOCOL
import static javax.xml.soap.SOAPConstants.SOAP_1_2_PROTOCOL

class SoapMessageBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(SoapMessageBuilder.class)

    static Message buildSoapMessage(String body, String soapSpec = SOAP_1_2_PROTOCOL)
            throws SOAPException, ParserConfigurationException, SAXException, IOException {
        final SOAPMessage soapMessage = soapSpec == SOAP_1_1_PROTOCOL ? getSoap11Message() : getSoap12Message()

        final Document document = xmlToDocument(body)

        soapMessage.getSOAPBody().addDocument(document)

        return new Message(body, soapMessage)
    }

    @PackageScope
    static SOAPMessage getSoap11Message() {
        SOAPMessage soapMessage = null
        try {
            final MessageFactory messageFactory = MessageFactory.newInstance(SOAP_1_1_PROTOCOL)
            soapMessage = messageFactory.createMessage()
        } catch (SOAPException e) {
            LOGGER.error("Something went wrong: ", e)
        }

        return soapMessage
    }

    @PackageScope
    static SOAPMessage getSoap12Message() {
        SOAPMessage soapMessage = null
        try {
            final MessageFactory messageFactory = MessageFactory.newInstance(SOAP_1_2_PROTOCOL)
            soapMessage = messageFactory.createMessage()
            soapMessage.getSOAPPart().getEnvelope().setPrefix("soap")
            soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("env")
            soapMessage.getSOAPHeader().setPrefix("soap")
            soapMessage.getSOAPBody().setPrefix("soap")
        } catch (SOAPException e) {
            LOGGER.error("Something went wrong: ", e)
        }

        return soapMessage
    }

    @PackageScope
    static Document xmlToDocument(String xml) throws IOException, ParserConfigurationException, SAXException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance()
        factory.setNamespaceAware(true)
        final InputStream input = new ByteArrayInputStream(xml.getBytes())

        try {
            final DocumentBuilder builder = factory.newDocumentBuilder()
            return builder.parse(input)
        } finally {
            input.close()
        }
    }
}
