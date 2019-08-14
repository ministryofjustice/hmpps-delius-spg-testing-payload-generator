package uk.gov.gsi.justice.spg.test.template

import org.junit.Test
import uk.gov.gsi.justice.spg.test.template.model.Message

import static javax.xml.soap.SOAPConstants.SOAP_1_2_PROTOCOL
import static org.junit.Assert.assertThat
import static org.xmlunit.matchers.CompareMatcher.isIdenticalTo

class MessageRendererTest extends BaseTest {
    private final MessageRenderer sut = new MessageRenderer(new TemplateRenderer())

    @Test
    void testCrcResponse200() {
        final String expectedSoap = loadFileUTF8("/crc-200-soap-resp.xml")

        final Message message = sut.renderResponseMessage(200, SOAP_1_2_PROTOCOL)
        final actualSoap = soapToString(message.soapMessage)

        assertThat(actualSoap, isIdenticalTo(expectedSoap).ignoreWhitespace());
    }
}