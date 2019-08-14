package uk.gov.gsi.justice.spg.test.template

import org.junit.Test

import static org.junit.Assert.assertThat
import static org.xmlunit.matchers.CompareMatcher.isIdenticalTo

class XmlTemplateRendererTest extends BaseTest {
    private final XmlTemplateRenderer sut = new XmlTemplateRenderer()

    @Test
    void testCrcResponseXmlTemplate() throws Exception {
        final String expectedXml = loadFileUTF8("/crc-200-resp.xml")

        final URL templateUrl = getResourceUrl("/templates/CrcResponse_xml.tpl")
        final Map<String, Object> data = ["code": 200] as Map<String, Integer>

        final String actualXml = sut.render(templateUrl, data)

        assertThat(actualXml, isIdenticalTo(expectedXml).ignoreWhitespace());
    }

    @Test
    void testInterchangeStatusNotificationXmlTemplate() throws Exception {
        final String expectedXml = loadFileUTF8("/interchange-status-notification.xml")

        final URL templateUrl = getResourceUrl("/templates/InterchangeStatusNotification_xml.tpl")
        final Map<String, Object> data = [
                "statusCode": 202,
                "schemaVersion": "0-9-13",
                "schemaDate": "2018-02-08",
                "senderId": "C21",
                "receiverId": "N00",
                "senderControlRef": 1555426447710,
                "receiverControlRef": 1555426447711,
                "dateOfPreparation": "2019-04-16",
                "timeOfPreparation": "15:54:08.792",
                "applicationRef": "SPGTest",
                "messageType": "RSP"
        ] as Map<String, Object>
        final String actualXml = sut.render(templateUrl, data)

        assertThat(actualXml, isIdenticalTo(expectedXml).ignoreWhitespace());
    }

    @Test
    void testSpgInterchangeMessageXmlTemplate() throws Exception {
        final String expectedXml = loadFileUTF8("/spg-interchange-message.xml")

        final URL templateUrl = getResourceUrl("/templates/SpgInterchangeMessage_xml.tpl")
        final Map<String, Object> data = [
                "schemaVersion": "0-9-13",
                "schemaDate": "2018-02-08",
                "senderId": "C12",
                "receiverId": "N00",
                "senderControlRef": 1551775774989,
                "receiverControlRef": 1555426447711,
                "dateOfPreparation": "2019-03-05",
                "timeOfPreparation": "08:49:35",
                "applicationRef": "SPG",
                "messageType": "SYS",
                "notificationCode": null
        ] as Map<String, Object>
        final String actualXml = sut.render(templateUrl, data)

        assertThat(actualXml, isIdenticalTo(expectedXml).ignoreWhitespace());
    }
}