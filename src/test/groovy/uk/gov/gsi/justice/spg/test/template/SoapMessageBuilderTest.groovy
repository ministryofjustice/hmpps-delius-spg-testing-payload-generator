package uk.gov.gsi.justice.spg.test.template

import org.junit.Test

import static org.junit.Assert.assertThat
import static org.xmlunit.matchers.CompareMatcher.isIdenticalTo
import static uk.gov.gsi.justice.spg.test.template.SoapMessageBuilder.buildSoapMessage

class SoapMessageBuilderTest extends BaseTest {

    @Test
    void testBuildSoapMessageFromXmlStringWhenSoapSpecIsNotProvided() {
        final String xml = loadFileUTF8("/crc-200-resp.xml")
        final String expectedSoap = loadFileUTF8("/crc-200-soap-resp.xml")

        final message = buildSoapMessage(xml)
        final actualSoap = soapToString(message.soapMessage)

        assertThat(actualSoap, isIdenticalTo(expectedSoap).ignoreWhitespace());
    }
}