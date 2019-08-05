package uk.gov.gsi.justice.spg.test.template

import groovy.transform.CompileStatic
import uk.gov.gsi.justice.spg.test.template.model.Message

import static java.util.Collections.singletonMap
import static uk.gov.gsi.justice.spg.test.template.SoapMessageBuilder.buildSoapMessage

@CompileStatic
class MessageRenderer {
    private final TemplateRenderer templateRenderer

    MessageRenderer(TemplateRenderer templateRenderer) {
        this.templateRenderer = templateRenderer
    }

    Message renderResponseMessage(Integer code, String protocol) throws Exception {
        final URL templateUrl = getResourceUrl("templates/CrcResponse.tpl")
        final Map<String, Object> data = ["code": code] as Map<String, Object>

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, protocol)
    }

    Message renderConnectionNotification(final String version,
                                         final String date,
                                         final Long senderControlRef,
                                         final Long receiverControlRef,
                                         final String from,
                                         final String to,
                                         final String appRef,
                                         final String messageType,
                                         final Integer notificationCode,
                                         final String protocol,
                                         final String dateOfPreparation,
                                         final String timeOfPreparation) throws Exception {
        final URL templateUrl = getResourceUrl("templates/SpgInterchangeMessage.tpl")
        final Map<String, Object> notificationCodeMap = ["notificationCode": notificationCode] as Map<String, Object>
        final Map<String, Object> data = notificationCodeMap + buildNotificationData(
                version,
                date,
                senderControlRef,
                from,
                to,
                receiverControlRef,
                appRef,
                messageType,
                dateOfPreparation,
                timeOfPreparation
        ) as Map<String, Object>

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, protocol)
    }

    Message renderStatusNotification(final String version,
                                     final String date,
                                     final Long senderControlRef,
                                     final Long receiverControlRef,
                                     final String from,
                                     final String to,
                                     final String appRef,
                                     final String messageType,
                                     final Integer statusCode,
                                     final String protocol,
                                     final String dateOfPreparation,
                                     final String timeOfPreparation) throws Exception {
        final URL templateUrl = getResourceUrl("templates/InterchangeStatusNotification.tpl")

        final Map<String, Object> statusCodeMap = ["statusCode": statusCode] as Map<String, Object>
        final Map<String, Object> data = statusCodeMap + buildNotificationData(
                version,
                date,
                senderControlRef,
                from,
                to,
                receiverControlRef,
                appRef,
                messageType,
                dateOfPreparation,
                timeOfPreparation
        ) as Map<String, Object>

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, protocol)
    }

    private URL getResourceUrl(String filename) {
        return this.getClass().getClassLoader().getResource(filename)
    }

    private static Map<String, Object> buildNotificationData(final String version,
                                                             final String date,
                                                             final Long senderControlRef,
                                                             final String senderId,
                                                             final String receiverId,
                                                             final Long receiverControlRef,
                                                             final String appRef,
                                                             final String messageType,
                                                             final String dateOfPreparation,
                                                             final String timeOfPreparation) {
        return ["schemaVersion"     : version,
                "schemaDate"        : date,
                "senderId"          : senderId,
                "receiverId"        : receiverId,
                "senderControlRef"  : senderControlRef.toString(),
                "receiverControlRef": receiverControlRef,
                "dateOfPreparation" : dateOfPreparation,
                "timeOfPreparation" : timeOfPreparation,
                "applicationRef"    : appRef,
                "messageType"       : messageType] as Map<String, Object>
    }

    @SuppressWarnings("unused")
    Message renderCustodyRelease(final String version,
                                 final String date,
                                 final String senderControlRef,
                                 final String appRef,
                                 final String testIndicator,
                                 final String senderId,
                                 final String receiverId,
                                 final String messageType,
                                 final Integer notificationCode,
                                 final String caseReferenceNumber,
                                 final String spgVersion,
                                 final String spgUpdateUser,
                                 final String eventID,
                                 final String offenderID,
                                 final String releaseUser,
                                 final String actualReleaseDate,
                                 final String releaseType,
                                 final String protocol,
                                 final String dateOfPreparation,
                                 final String timeOfPreparation) throws Exception {
        final URL templateUrl = getResourceUrl("templates/CustodyRelease.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", notificationCode)
        final Map<String, Object> data = buildCustodyRelease(
                version,
                date,
                senderControlRef,
                appRef,
                testIndicator,
                senderId,
                receiverId,
                messageType,
                caseReferenceNumber,
                spgVersion,
                spgUpdateUser,
                eventID,
                offenderID,
                releaseUser,
                actualReleaseDate,
                releaseType,
                dateOfPreparation,
                timeOfPreparation
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, protocol)
    }

    private static Map<String, Object> buildCustodyRelease(final String version,
                                                           final String date,
                                                           final String senderControlRef,
                                                           final String appRef,
                                                           final String testIndicator,
                                                           final String senderId,
                                                           final String receiverId,
                                                           final String messageType,
                                                           final String caseReferenceNumber,
                                                           final String spgVersion,
                                                           final String spgUpdateUser,
                                                           final String eventID,
                                                           final String offenderID,
                                                           final String releaseUser,
                                                           final String actualReleaseDate,
                                                           final String releaseType,
                                                           final String dateOfPreparation,
                                                           final String timeOfPreparation) {

        final Map<String, Object> data = [
                "schemaVersion"      : version,
                "schemaDate"         : date,
                "senderControlRef"   : senderControlRef,
                "applicationRef"     : appRef,
                "testIndicator"      : testIndicator,
                "senderId"           : senderId,
                "receiverId"         : receiverId,
                "dateOfPreparation"  : dateOfPreparation,
                "timeOfPreparation"  : timeOfPreparation,
                "messageType"        : messageType,
                "caseReferenceNumber": caseReferenceNumber,
                "spgVersion"         : spgVersion,
                "spgUpdateUser"      : spgUpdateUser,
                "eventID"            : eventID,
                "offenderID"         : offenderID,
                "releaseUser"        : releaseUser,
                "actualReleaseDate"  : actualReleaseDate,
                "releaseType"        : releaseType,
        ] as Map<String, Object>
        return data
    }
}