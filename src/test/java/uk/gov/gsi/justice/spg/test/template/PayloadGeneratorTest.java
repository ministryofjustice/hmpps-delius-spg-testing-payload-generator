package uk.gov.gsi.justice.spg.test.template;

import org.junit.Test;

import static javax.xml.soap.SOAPConstants.SOAP_1_2_PROTOCOL;
import static org.junit.Assert.assertThat;
import static org.xmlunit.matchers.CompareMatcher.isIdenticalTo;

public class PayloadGeneratorTest extends BaseTest {
    private final PayloadGenerator sut = new PayloadGenerator.Builder().build();

    @Test
    public void testContentGeneration() throws Exception {
        final String expectedXml = loadFileUTF8("/crc-200-soap-resp.xml");

        final String actualXml = sut.createSoapResponse(200, SOAP_1_2_PROTOCOL);

        assertThat(actualXml, isIdenticalTo(expectedXml).ignoreWhitespace());
    }
}