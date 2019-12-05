package uk.gov.gsi.justice.spg.test.template

import groovy.transform.CompileStatic
import uk.gov.gsi.justice.spg.test.template.model.Message

import static java.util.Collections.singletonMap
import static javax.xml.soap.SOAPConstants.SOAP_1_2_PROTOCOL
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

    @SuppressWarnings("unused")
    Message renderCustodyRecall(final String version,
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
                                 final String recallUser,
                                 final String recallDate,
                                 final String recallReason,
                                 final String recallLocation,
                                 final String protocol,
                                 final String dateOfPreparation,
                                 final String timeOfPreparation) throws Exception {
        final URL templateUrl = getResourceUrl("templates/CustodyRecall.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", notificationCode)
        final Map<String, Object> data = buildCustodyRecall(
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
                recallUser,
                recallDate,
                recallReason,
                recallLocation,
                dateOfPreparation,
                timeOfPreparation
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, protocol)
    }

    private static Map<String, Object> buildCustodyRecall(final String version,
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
                                                           final String recallUser,
                                                           final String recallDate,
                                                           final String recallReason,
                                                           final String recallLocation,
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
                "recallUser"         : recallUser,
                "recallDate"         : recallDate,
                "recallReason"       : recallReason,
                "recallLocation"     : recallLocation,
        ] as Map<String, Object>
        return data
    }

    @SuppressWarnings("unused")
    Message renderCustodyKeyDate(final String version,
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
                                 final String keyDateID,
                                 final String eventID,
                                 final String offenderID,
                                 final String keyDateType,
                                 final String keyDate,
                                 final String protocol,
                                 final String dateOfPreparation,
                                 final String timeOfPreparation) throws Exception {
        final URL templateUrl = getResourceUrl("templates/CustodyKeyDate.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", notificationCode)
        final Map<String, Object> data = buildCustodyKeyDate(
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
                keyDateID,
                eventID,
                offenderID,
                keyDateType,
                keyDate,
                dateOfPreparation,
                timeOfPreparation
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, protocol)
    }

    private static Map<String, Object> buildCustodyKeyDate(final String version,
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
                                                           final String keyDateID,
                                                           final String eventID,
                                                           final String offenderID,
                                                           final String keyDateType,
                                                           final String keyDate,
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
                "keyDateID"          : keyDateID,
                "eventID"            : eventID,
                "offenderID"         : offenderID,
                "keyDateType"        : keyDateType,
                "keyDate"            : keyDate,
        ] as Map<String, Object>
        return data
    }

    @SuppressWarnings("unused")
    Message renderCommunityRequirement(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String requirementID,
            final String eventID,
            final String offenderID,
            final String requirementTypeMainCategory,
            final String requirementTypeSubCategory,
            final String imposedDate,
            final String length,
            final String actualEndDate,
            final String terminationReason,
            final String rqProvider,
            final String rqResponsibleTeam,
            final String rqResponsibleOfficer
    ) throws Exception {
        final URL templateUrl = getResourceUrl("templates/CommunityRequirement.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", 200)
        final Map<String, Object> data = buildCommunityRequirement(
                schemaDate,
                schemaVersion,
                senderId,
                receiverId,
                senderControlRef,
                dateOfPreparation,
                timeOfPreparation,
                appRef,
                testIndicator,
                messageType,
                caseReferenceNumber,
                masterSpgVersion,
                spgUpdateUser,
                requirementID,
                eventID,
                offenderID,
                requirementTypeMainCategory,
                requirementTypeSubCategory,
                imposedDate,
                length,
                actualEndDate,
                terminationReason,
                rqProvider,
                rqResponsibleTeam,
                rqResponsibleOfficer,
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, SOAP_1_2_PROTOCOL)
    }

    private static Map<String, Object> buildCourtReport(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String requirementID,
            final String eventID,
            final String offenderID,
            final String requirementTypeMainCategory,
            final String requirementTypeSubCategory,
            final String imposedDate,
            final String length,
            final String actualEndDate,
            final String terminationReason,
            final String rqProvider,
            final String rqResponsibleTeam,
            final String rqResponsibleOfficer) {

        final Map<String, Object> data = [
                "schemaDate"                 : schemaDate,
                "schemaVersion"              : schemaVersion,
                "senderId"                   : senderId,
                "receiverId"                 : receiverId,
                "senderControlRef"           : senderControlRef,
                "dateOfPreparation"          : dateOfPreparation,
                "timeOfPreparation"          : timeOfPreparation,
                "applicationRef"             : appRef,
                "testIndicator"              : testIndicator,
                "messageType"                : messageType,
                "caseReferenceNumber"        : caseReferenceNumber,
                "masterSpgVersion"           : masterSpgVersion,
                "spgUpdateUser"              : spgUpdateUser,
                "requirementID"              : requirementID,
                "eventID"                    : eventID,
                "offenderID"                 : offenderID,
                "requirementTypeMainCategory": requirementTypeMainCategory,
                "requirementTypeSubCategory" : requirementTypeSubCategory,
                "imposedDate"                : imposedDate,
                "length"                     : length,
                "actualEndDate"              : actualEndDate,
                "terminationReason"          : terminationReason,
                "rqProvider"                 : rqProvider,
                "rqResponsibleTeam"          : rqResponsibleTeam,
                "rqResponsibleOfficer"       : rqResponsibleOfficer,
        ] as Map<String, Object>
        return data
    }

    private static Map<String, Object> buildEvent(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String requirementID,
            final String eventID,
            final String offenderID,
            final String requirementTypeMainCategory,
            final String requirementTypeSubCategory,
            final String imposedDate,
            final String length,
            final String actualEndDate,
            final String terminationReason,
            final String rqProvider,
            final String rqResponsibleTeam,
            final String rqResponsibleOfficer) {

        final Map<String, Object> data = [
                "schemaDate"                 : schemaDate,
                "schemaVersion"              : schemaVersion,
                "senderId"                   : senderId,
                "receiverId"                 : receiverId,
                "senderControlRef"           : senderControlRef,
                "dateOfPreparation"          : dateOfPreparation,
                "timeOfPreparation"          : timeOfPreparation,
                "applicationRef"             : appRef,
                "testIndicator"              : testIndicator,
                "messageType"                : messageType,
                "caseReferenceNumber"        : caseReferenceNumber,
                "masterSpgVersion"           : masterSpgVersion,
                "spgUpdateUser"              : spgUpdateUser,
                "requirementID"              : requirementID,
                "eventID"                    : eventID,
                "offenderID"                 : offenderID,
                "requirementTypeMainCategory": requirementTypeMainCategory,
                "requirementTypeSubCategory" : requirementTypeSubCategory,
                "imposedDate"                : imposedDate,
                "length"                     : length,
                "actualEndDate"              : actualEndDate,
                "terminationReason"          : terminationReason,
                "rqProvider"                 : rqProvider,
                "rqResponsibleTeam"          : rqResponsibleTeam,
                "rqResponsibleOfficer"       : rqResponsibleOfficer,
        ] as Map<String, Object>
        return data
    }

    private static Map<String, Object> buildTerminateSentence(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String eventID,
            final String offenderID,
            final String terminateUser,
            final String terminationDate,
            final String terminationReason,
            final String terminationDateTime) {

        final Map<String, Object> data = [
                "schemaDate"         : schemaDate,
                "schemaVersion"      : schemaVersion,
                "senderId"           : senderId,
                "receiverId"         : receiverId,
                "senderControlRef"   : senderControlRef,
                "dateOfPreparation"  : dateOfPreparation,
                "timeOfPreparation"  : timeOfPreparation,
                "applicationRef"     : appRef,
                "testIndicator"      : testIndicator,
                "messageType"        : messageType,
                "caseReferenceNumber": caseReferenceNumber,
                "masterSpgVersion"   : masterSpgVersion,
                "spgUpdateUser"      : spgUpdateUser,
                "eventID"            : eventID,
                "offenderID"         : offenderID,
                "terminateUser"      : terminateUser,
                "terminationDate"    : terminationDate,
                "terminationReason"  : terminationReason,
                "terminationDateTime": terminationDateTime,
        ] as Map<String, Object>
        return data
    }

    private static Map<String, Object> buildInstitutionalReport(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String requirementID,
            final String eventID,
            final String offenderID,
            final String requirementTypeMainCategory,
            final String requirementTypeSubCategory,
            final String imposedDate,
            final String length,
            final String actualEndDate,
            final String terminationReason,
            final String rqProvider,
            final String rqResponsibleTeam,
            final String rqResponsibleOfficer) {

        final Map<String, Object> data = [
                "schemaDate"                 : schemaDate,
                "schemaVersion"              : schemaVersion,
                "senderId"                   : senderId,
                "receiverId"                 : receiverId,
                "senderControlRef"           : senderControlRef,
                "dateOfPreparation"          : dateOfPreparation,
                "timeOfPreparation"          : timeOfPreparation,
                "applicationRef"             : appRef,
                "testIndicator"              : testIndicator,
                "messageType"                : messageType,
                "caseReferenceNumber"        : caseReferenceNumber,
                "masterSpgVersion"           : masterSpgVersion,
                "spgUpdateUser"              : spgUpdateUser,
                "requirementID"              : requirementID,
                "eventID"                    : eventID,
                "offenderID"                 : offenderID,
                "requirementTypeMainCategory": requirementTypeMainCategory,
                "requirementTypeSubCategory" : requirementTypeSubCategory,
                "imposedDate"                : imposedDate,
                "length"                     : length,
                "actualEndDate"              : actualEndDate,
                "terminationReason"          : terminationReason,
                "rqProvider"                 : rqProvider,
                "rqResponsibleTeam"          : rqResponsibleTeam,
                "rqResponsibleOfficer"       : rqResponsibleOfficer,
        ] as Map<String, Object>
        return data
    }

    private static Map<String, Object> buildLicCondition(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String entityID,
            final String eventID,
            final String offenderID,
            final String entityTypeMainCategory,
            final String entityTypeSubCategory,
            final String sentenceDate,
            final String expectedStartDate,
            final String length,
            final String actualStartDate,
            final String notes,
            final String expectedEndDate,
            final String actualEndDate,
            final String terminationReason,
            final String lcProvider,
            final String lcResponsibleTeam,
            final String lcResponsibleOfficer) {

        final Map<String, Object> data = [
                "schemaDate"            : schemaDate,
                "schemaVersion"         : schemaVersion,
                "senderId"              : senderId,
                "receiverId"            : receiverId,
                "senderControlRef"      : senderControlRef,
                "dateOfPreparation"     : dateOfPreparation,
                "timeOfPreparation"     : timeOfPreparation,
                "applicationRef"        : appRef,
                "testIndicator"         : testIndicator,
                "messageType"           : messageType,
                "caseReferenceNumber"   : caseReferenceNumber,
                "masterSpgVersion"      : masterSpgVersion,
                "spgUpdateUser"         : spgUpdateUser,
                "entityID"              : entityID,
                "eventID"               : eventID,
                "offenderID"            : offenderID,
                "entityTypeMainCategory": entityTypeMainCategory,
                "entityTypeSubCategory" : entityTypeSubCategory,
                "sentenceDate"          : sentenceDate,
                "expectedStartDate"     : expectedStartDate,
                "length"                : length,
                "actualStartDate"       : actualStartDate,
                "notes"                 : notes,
                "expectedEndDate"       : expectedEndDate,
                "actualEndDate"         : actualEndDate,
                "terminationReason"     : terminationReason,
                "lcProvider"            : lcProvider,
                "lcResponsibleTeam"     : lcResponsibleTeam,
                "lcResponsibleOfficer"  : lcResponsibleOfficer,
        ] as Map<String, Object>
        return data
    }

    private static Map<String, Object> buildProcessContact(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String processID,
            final String offenderID,
            final String processType,
            final String processRefDate,
            final String processStartDate,
            final String processExpEndDate,
            final String processEndDate,
            final String processStage,
            final String processStageDateTime,
            final String processOutcome,
            final String intendedProvider,
            final String processManagerProvider,
            final String processManagerTeam,
            final String processManagerOfficer) {

        final Map<String, Object> data = [
                "schemaDate"            : schemaDate,
                "schemaVersion"         : schemaVersion,
                "senderId"              : senderId,
                "receiverId"            : receiverId,
                "senderControlRef"      : senderControlRef,
                "dateOfPreparation"     : dateOfPreparation,
                "timeOfPreparation"     : timeOfPreparation,
                "applicationRef"        : appRef,
                "testIndicator"         : testIndicator,
                "messageType"           : messageType,
                "caseReferenceNumber"   : caseReferenceNumber,
                "masterSpgVersion"      : masterSpgVersion,
                "spgUpdateUser"         : spgUpdateUser,
                "processID"             : processID,
                "offenderID"            : offenderID,
                "processType"           : processType,
                "processRefDate"        : processRefDate,
                "processStartDate"      : processStartDate,
                "processExpEndDate"     : processExpEndDate,
                "processEndDate"        : processEndDate,
                "processStage"          : processStage,
                "processStageDateTime"  : processStageDateTime,
                "processOutcome"        : processOutcome,
                "intendedProvider"      : intendedProvider,
                "processManagerProvider": processManagerProvider,
                "processManagerTeam"    : processManagerTeam,
                "processManagerOfficer" : processManagerOfficer,
        ] as Map<String, Object>
        return data
    }

    private static Map<String, Object> buildPssRequirement(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String requirementID,
            final String eventID,
            final String offenderID,
            final String requirementTypeMainCategory,
            final String requirementTypeSubCategory,
            final String imposedDate,
            final String notes,
            final String length,
            final String actualEndDate,
            final String terminationReason,
            final String attendanceCount,
            final String psProvider,
            final String psResponsibleTeam,
            final String psResponsibleOfficer) {

        final Map<String, Object> data = [
                "schemaDate"                 : schemaDate,
                "schemaVersion"              : schemaVersion,
                "senderId"                   : senderId,
                "receiverId"                 : receiverId,
                "senderControlRef"           : senderControlRef,
                "dateOfPreparation"          : dateOfPreparation,
                "timeOfPreparation"          : timeOfPreparation,
                "applicationRef"             : appRef,
                "testIndicator"              : testIndicator,
                "messageType"                : messageType,
                "caseReferenceNumber"        : caseReferenceNumber,
                "masterSpgVersion"           : masterSpgVersion,
                "spgUpdateUser"              : spgUpdateUser,
                "requirementID"              : requirementID,
                "eventID"                    : eventID,
                "offenderID"                 : offenderID,
                "requirementTypeMainCategory": requirementTypeMainCategory,
                "requirementTypeSubCategory" : requirementTypeSubCategory,
                "imposedDate"                : imposedDate,
                "notes"                      : notes,
                "length"                     : length,
                "actualEndDate"              : actualEndDate,
                "terminationReason"          : terminationReason,
                "attendanceCount"            : attendanceCount,
                "psProvider"                 : psProvider,
                "psResponsibleTeam"          : psResponsibleTeam,
                "psResponsibleOfficer"       : psResponsibleOfficer,
        ] as Map<String, Object>
        return data
    }

    private static Map<String, Object> buildCommunityRequirement(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String requirementID,
            final String eventID,
            final String offenderID,
            final String requirementTypeMainCategory,
            final String requirementTypeSubCategory,
            final String imposedDate,
            final String length,
            final String actualEndDate,
            final String terminationReason,
            final String rqProvider,
            final String rqResponsibleTeam,
            final String rqResponsibleOfficer) {

        final Map<String, Object> data = [
                "schemaDate"                 : schemaDate,
                "schemaVersion"              : schemaVersion,
                "senderId"                   : senderId,
                "receiverId"                 : receiverId,
                "senderControlRef"           : senderControlRef,
                "dateOfPreparation"          : dateOfPreparation,
                "timeOfPreparation"          : timeOfPreparation,
                "applicationRef"             : appRef,
                "testIndicator"              : testIndicator,
                "messageType"                : messageType,
                "caseReferenceNumber"        : caseReferenceNumber,
                "masterSpgVersion"           : masterSpgVersion,
                "spgUpdateUser"              : spgUpdateUser,
                "requirementID"              : requirementID,
                "eventID"                    : eventID,
                "offenderID"                 : offenderID,
                "requirementTypeMainCategory": requirementTypeMainCategory,
                "requirementTypeSubCategory" : requirementTypeSubCategory,
                "imposedDate"                : imposedDate,
                "length"                     : length,
                "actualEndDate"              : actualEndDate,
                "terminationReason"          : terminationReason,
                "rqProvider"                 : rqProvider,
                "rqResponsibleTeam"          : rqResponsibleTeam,
                "rqResponsibleOfficer"       : rqResponsibleOfficer,
        ] as Map<String, Object>
        return data
    }

    @SuppressWarnings("unused")
    Message renderContact(final String schemaDate,
                          final String schemaVersion,
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
                          final String contactID,
                          final String offenderID,
                          final String eventID,
                          final String nsiID,
                          final String contactType,
                          final String contactDate,
                          final String contactStartTime,
                          final String provider,
                          final String team,
                          final String contactOfficer,
                          final String location,
                          final String contactOutcome,
                          final String sensitiveContact,
                          final String notes,
                          final String rarContact,
                          final String alert,
                          final String protocol,
                          final String dateOfPreparation,
                          final String timeOfPreparation) throws Exception {
        final URL templateUrl = getResourceUrl("templates/Contact.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", notificationCode)
        final Map<String, Object> data = buildContact(
                schemaDate,
                schemaVersion,
                senderControlRef,
                appRef,
                testIndicator,
                senderId,
                receiverId,
                messageType,
                caseReferenceNumber,
                spgVersion,
                spgUpdateUser,
                contactID,
                offenderID,
                eventID,
                nsiID,
                contactType,
                contactDate,
                contactStartTime,
                provider,
                team,
                contactOfficer,
                location,
                contactOutcome,
                sensitiveContact,
                notes,
                rarContact,
                alert,
                dateOfPreparation,
                timeOfPreparation
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, protocol)
    }

    private static Map<String, Object> buildContact(final String schemaDate,
                                                    final String schemaVersion,
                                                    final String senderControlRef,
                                                    final String appRef,
                                                    final String testIndicator,
                                                    final String senderId,
                                                    final String receiverId,
                                                    final String messageType,
                                                    final String caseReferenceNumber,
                                                    final String spgVersion,
                                                    final String spgUpdateUser,
                                                    final String contactID,
                                                    final String offenderID,
                                                    final String eventID,
                                                    final String nsiID,
                                                    final String contactType,
                                                    final String contactDate,
                                                    final String contactStartTime,
                                                    final String provider,
                                                    final String team,
                                                    final String contactOfficer,
                                                    final String location,
                                                    final String contactOutcome,
                                                    final String sensitiveContact,
                                                    final String notes,
                                                    final String rarContact,
                                                    final String alert,
                                                    final String dateOfPreparation,
                                                    final String timeOfPreparation) {

        final Map<String, Object> data = [
                "schemaDate"         : schemaDate,
                "schemaVersion"      : schemaVersion,
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
                "contactID"          : contactID,
                "nsiID"              : nsiID,
                "contactType"        : contactType,
                "contactDate"        : contactDate,
                "contactStartTime"   : contactStartTime,
                "provider"           : provider,
                "team"               : team,
                "contactOfficer"     : contactOfficer,
                "location"           : location,
                "contactOutcome"     : contactOutcome,
                "sensitiveContact"   : sensitiveContact,
                "notes"              : notes,
                "rarContact"         : rarContact,
                "alert"              : alert,
        ] as Map<String, Object>
        return data
    }

    @SuppressWarnings("unused")
    Message renderTransferRequest(
            final String schemaDate,
            final String schemaVersion,
            final String transaction,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String transferID,
            final String offenderID,
            final String transferDate,
            final String transferUser,
            final String entitySpgVersion,
            final String entityID,
            final String entityTransferReason,
            final String entityTransferStatus,
            final String entityTransferFromProvider,
            final String entityTransferFromResponsibleTeam,
            final String entityTransferFromResponsibleOfficer,
            final String entityTransferToProvider,
            final String entityTransferToResponsibleTeam,
            final String entityTransferToResponsibleOfficer
    ) throws Exception {
        final URL templateUrl = getResourceUrl("templates/TransferRequest.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", 200)
        final Map<String, Object> data = buildTransferRequest(
                schemaDate,
                schemaVersion,
                transaction,
                senderId,
                receiverId,
                senderControlRef,
                dateOfPreparation,
                timeOfPreparation,
                appRef,
                testIndicator,
                messageType,
                caseReferenceNumber,
                masterSpgVersion,
                spgUpdateUser,
                transferID,
                offenderID,
                transferDate,
                transferUser,
                entitySpgVersion,
                entityID,
                entityTransferReason,
                entityTransferStatus,
                entityTransferFromProvider,
                entityTransferFromResponsibleTeam,
                entityTransferFromResponsibleOfficer,
                entityTransferToProvider,
                entityTransferToResponsibleTeam,
                entityTransferToResponsibleOfficer,
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, SOAP_1_2_PROTOCOL)
    }

    private static Map<String, Object> buildTransferRequest(
            final String schemaDate,
            final String schemaVersion,
            final String transaction,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String transferID,
            final String offenderID,
            final String transferDate,
            final String transferUser,
            final String entitySpgVersion,
            final String entityID,
            final String entityTransferReason,
            final String entityTransferStatus,
            final String entityTransferFromProvider,
            final String entityTransferFromResponsibleTeam,
            final String entityTransferFromResponsibleOfficer,
            final String entityTransferToProvider,
            final String entityTransferToResponsibleTeam,
            final String entityTransferToResponsibleOfficer) {

        final Map<String, Object> data = [
                "schemaDate"                          : schemaDate,
                "schemaVersion"                       : schemaVersion,
                "transaction"                         : transaction,
                "senderId"                            : senderId,
                "receiverId"                          : receiverId,
                "senderControlRef"                    : senderControlRef,
                "dateOfPreparation"                   : dateOfPreparation,
                "timeOfPreparation"                   : timeOfPreparation,
                "applicationRef"                      : appRef,
                "testIndicator"                       : testIndicator,
                "messageType"                         : messageType,
                "caseReferenceNumber"                 : caseReferenceNumber,
                "masterSpgVersion"                    : masterSpgVersion,
                "spgUpdateUser"                       : spgUpdateUser,
                "transferID"                          : transferID,
                "offenderID"                          : offenderID,
                "transferDate"                        : transferDate,
                "transferUser"                        : transferUser,
                "entitySpgVersion"                    : entitySpgVersion,
                "entityID"                            : entityID,
                "entityTransferReason"                : entityTransferReason,
                "entityTransferStatus"                : entityTransferStatus,
                "entityTransferFromProvider"          : entityTransferFromProvider,
                "entityTransferFromResponsibleTeam"   : entityTransferFromResponsibleTeam,
                "entityTransferFromResponsibleOfficer": entityTransferFromResponsibleOfficer,
                "entityTransferToProvider"            : entityTransferToProvider,
                "entityTransferToResponsibleTeam"     : entityTransferToResponsibleTeam,
                "entityTransferToResponsibleOfficer"  : entityTransferToResponsibleOfficer,
        ] as Map<String, Object>
        return data
    }

    @SuppressWarnings("unused")
    Message renderTransferResponse(
            final String schemaDate,
            final String schemaVersion,
            final String transaction,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String transferID,
            final String offenderID,
            final String transferDate,
            final String transferUser,
            final String entitySpgVersion,
            final String entityID,
            final String entityTransferStatus,
            final String entityTransferRejectedReason,
            final String entityTransferFromProvider,
            final String entityTransferToProvider,
            final String entityTransferToResponsibleTeam,
            final String entityTransferToResponsibleOfficer
    ) throws Exception {
        final URL templateUrl = getResourceUrl("templates/TransferResponse.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", 200)
        final Map<String, Object> data = buildTransferResponse(
                schemaDate,
                schemaVersion,
                transaction,
                senderId,
                receiverId,
                senderControlRef,
                dateOfPreparation,
                timeOfPreparation,
                appRef,
                testIndicator,
                messageType,
                caseReferenceNumber,
                masterSpgVersion,
                spgUpdateUser,
                transferID,
                offenderID,
                transferDate,
                transferUser,
                entitySpgVersion,
                entityID,
                entityTransferStatus,
                entityTransferRejectedReason,
                entityTransferFromProvider,
                entityTransferToProvider,
                entityTransferToResponsibleTeam,
                entityTransferToResponsibleOfficer,
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, SOAP_1_2_PROTOCOL)
    }

    private static Map<String, Object> buildTransferResponse(
            final String schemaDate,
            final String schemaVersion,
            final String transaction,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String transferID,
            final String offenderID,
            final String transferDate,
            final String transferUser,
            final String entitySpgVersion,
            final String entityID,
            final String entityTransferStatus,
            final String entityTransferRejectedReason,
            final String entityTransferFromProvider,
            final String entityTransferToProvider,
            final String entityTransferToResponsibleTeam,
            final String entityTransferToResponsibleOfficer) {

        final Map<String, Object> data = [
                "schemaDate"                        : schemaDate,
                "schemaVersion"                     : schemaVersion,
                "transaction"                       : transaction,
                "senderId"                          : senderId,
                "receiverId"                        : receiverId,
                "senderControlRef"                  : senderControlRef,
                "dateOfPreparation"                 : dateOfPreparation,
                "timeOfPreparation"                 : timeOfPreparation,
                "applicationRef"                    : appRef,
                "testIndicator"                     : testIndicator,
                "messageType"                       : messageType,
                "caseReferenceNumber"               : caseReferenceNumber,
                "masterSpgVersion"                  : masterSpgVersion,
                "spgUpdateUser"                     : spgUpdateUser,
                "transferID"                        : transferID,
                "offenderID"                        : offenderID,
                "transferDate"                      : transferDate,
                "transferUser"                      : transferUser,
                "entitySpgVersion"                  : entitySpgVersion,
                "entityID"                          : entityID,
                "entityTransferStatus"              : entityTransferStatus,
                "entityTransferRejectedReason"      : entityTransferRejectedReason,
                "entityTransferFromProvider"        : entityTransferFromProvider,
                "entityTransferToProvider"          : entityTransferToProvider,
                "entityTransferToResponsibleTeam"   : entityTransferToResponsibleTeam,
                "entityTransferToResponsibleOfficer": entityTransferToResponsibleOfficer,
        ] as Map<String, Object>
        return data
    }

    @SuppressWarnings("unused")
    Message renderCourtReport(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String requirementID,
            final String eventID,
            final String offenderID,
            final String requirementTypeMainCategory,
            final String requirementTypeSubCategory,
            final String imposedDate,
            final String length,
            final String actualEndDate,
            final String terminationReason,
            final String rqProvider,
            final String rqResponsibleTeam,
            final String rqResponsibleOfficer
    ) throws Exception {
        final URL templateUrl = getResourceUrl("templates/CourtReport.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", 200)
        final Map<String, Object> data = buildCourtReport(
                schemaDate,
                schemaVersion,
                senderId,
                receiverId,
                senderControlRef,
                dateOfPreparation,
                timeOfPreparation,
                appRef,
                testIndicator,
                messageType,
                caseReferenceNumber,
                masterSpgVersion,
                spgUpdateUser,
                requirementID,
                eventID,
                offenderID,
                requirementTypeMainCategory,
                requirementTypeSubCategory,
                imposedDate,
                length,
                actualEndDate,
                terminationReason,
                rqProvider,
                rqResponsibleTeam,
                rqResponsibleOfficer,
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, SOAP_1_2_PROTOCOL)
    }

    @SuppressWarnings("unused")
    Message renderEvent(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String requirementID,
            final String eventID,
            final String offenderID,
            final String requirementTypeMainCategory,
            final String requirementTypeSubCategory,
            final String imposedDate,
            final String length,
            final String actualEndDate,
            final String terminationReason,
            final String rqProvider,
            final String rqResponsibleTeam,
            final String rqResponsibleOfficer
    ) throws Exception {
        final URL templateUrl = getResourceUrl("templates/Event.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", 200)
        final Map<String, Object> data = buildEvent(
                schemaDate,
                schemaVersion,
                senderId,
                receiverId,
                senderControlRef,
                dateOfPreparation,
                timeOfPreparation,
                appRef,
                testIndicator,
                messageType,
                caseReferenceNumber,
                masterSpgVersion,
                spgUpdateUser,
                requirementID,
                eventID,
                offenderID,
                requirementTypeMainCategory,
                requirementTypeSubCategory,
                imposedDate,
                length,
                actualEndDate,
                terminationReason,
                rqProvider,
                rqResponsibleTeam,
                rqResponsibleOfficer,
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, SOAP_1_2_PROTOCOL)
    }

    @SuppressWarnings("unused")
    Message renderInstitutionalReport(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String requirementID,
            final String eventID,
            final String offenderID,
            final String requirementTypeMainCategory,
            final String requirementTypeSubCategory,
            final String imposedDate,
            final String length,
            final String actualEndDate,
            final String terminationReason,
            final String rqProvider,
            final String rqResponsibleTeam,
            final String rqResponsibleOfficer
    ) throws Exception {
        final URL templateUrl = getResourceUrl("templates/InstitutionalReport.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", 200)
        final Map<String, Object> data = buildInstitutionalReport(
                schemaDate,
                schemaVersion,
                senderId,
                receiverId,
                senderControlRef,
                dateOfPreparation,
                timeOfPreparation,
                appRef,
                testIndicator,
                messageType,
                caseReferenceNumber,
                masterSpgVersion,
                spgUpdateUser,
                requirementID,
                eventID,
                offenderID,
                requirementTypeMainCategory,
                requirementTypeSubCategory,
                imposedDate,
                length,
                actualEndDate,
                terminationReason,
                rqProvider,
                rqResponsibleTeam,
                rqResponsibleOfficer,
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, SOAP_1_2_PROTOCOL)
    }

    @SuppressWarnings("unused")
    Message renderLicCondition(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String entityID,
            final String eventID,
            final String offenderID,
            final String entityTypeMainCategory,
            final String entityTypeSubCategory,
            final String sentenceDate,
            final String expectedStartDate,
            final String length,
            final String actualStartDate,
            final String notes,
            final String expectedEndDate,
            final String actualEndDate,
            final String terminationReason,
            final String lcProvider,
            final String lcResponsibleTeam,
            final String lcResponsibleOfficer
    ) throws Exception {
        final URL templateUrl = getResourceUrl("templates/LicCondition.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", 200)
        final Map<String, Object> data = buildLicCondition(
                schemaDate,
                schemaVersion,
                senderId,
                receiverId,
                senderControlRef,
                dateOfPreparation,
                timeOfPreparation,
                appRef,
                testIndicator,
                messageType,
                caseReferenceNumber,
                masterSpgVersion,
                spgUpdateUser,
                entityID,
                eventID,
                offenderID,
                entityTypeMainCategory,
                entityTypeSubCategory,
                sentenceDate,
                expectedStartDate,
                length,
                actualStartDate,
                notes,
                expectedEndDate,
                actualEndDate,
                terminationReason,
                lcProvider,
                lcResponsibleTeam,
                lcResponsibleOfficer,
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, SOAP_1_2_PROTOCOL)
    }

    @SuppressWarnings("unused")
    Message renderProcessContact(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String processID,
            final String offenderID,
            final String processType,
            final String processRefDate,
            final String processStartDate,
            final String processExpEndDate,
            final String processEndDate,
            final String processStage,
            final String processStageDateTime,
            final String processOutcome,
            final String intendedProvider,
            final String processManagerProvider,
            final String processManagerTeam,
            final String processManagerOfficer
    ) throws Exception {
        final URL templateUrl = getResourceUrl("templates/ProcessContact.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", 200)
        final Map<String, Object> data = buildProcessContact(
                schemaDate,
                schemaVersion,
                senderId,
                receiverId,
                senderControlRef,
                dateOfPreparation,
                timeOfPreparation,
                appRef,
                testIndicator,
                messageType,
                caseReferenceNumber,
                masterSpgVersion,
                spgUpdateUser,
                processID,
                offenderID,
                processType,
                processRefDate,
                processStartDate,
                processExpEndDate,
                processEndDate,
                processStage,
                processStageDateTime,
                processOutcome,
                intendedProvider,
                processManagerProvider,
                processManagerTeam,
                processManagerOfficer
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, SOAP_1_2_PROTOCOL)
    }

    @SuppressWarnings("unused")
    Message renderPssRequirement(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String requirementID,
            final String eventID,
            final String offenderID,
            final String requirementTypeMainCategory,
            final String requirementTypeSubCategory,
            final String imposedDate,
            final String notes,
            final String length,
            final String actualEndDate,
            final String terminationReason,
            final String attendanceCount,
            final String psProvider,
            final String psResponsibleTeam,
            final String psResponsibleOfficer
    ) throws Exception {
        final URL templateUrl = getResourceUrl("templates/PssRequirement.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", 200)
        final Map<String, Object> data = buildPssRequirement(
                schemaDate,
                schemaVersion,
                senderId,
                receiverId,
                senderControlRef,
                dateOfPreparation,
                timeOfPreparation,
                appRef,
                testIndicator,
                messageType,
                caseReferenceNumber,
                masterSpgVersion,
                spgUpdateUser,
                requirementID,
                eventID,
                offenderID,
                requirementTypeMainCategory,
                requirementTypeSubCategory,
                imposedDate,
                notes,
                length,
                actualEndDate,
                terminationReason,
                attendanceCount,
                psProvider,
                psResponsibleTeam,
                psResponsibleOfficer,
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, SOAP_1_2_PROTOCOL)
    }

    @SuppressWarnings("unused")
    Message renderTerminateSentence(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String eventID,
            final String offenderID,
            final String terminateUser,
            final String terminationDate,
            final String terminationReason,
            final String terminationDateTime
    ) throws Exception {
        final URL templateUrl = getResourceUrl("templates/TerminateSentence.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", 200)
        final Map<String, Object> data = buildTerminateSentence(
                schemaDate,
                schemaVersion,
                senderId,
                receiverId,
                senderControlRef,
                dateOfPreparation,
                timeOfPreparation,
                appRef,
                testIndicator,
                messageType,
                caseReferenceNumber,
                masterSpgVersion,
                spgUpdateUser,
                eventID,
                offenderID,
                terminateUser,
                terminationDate,
                terminationReason,
                terminationDateTime,
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, SOAP_1_2_PROTOCOL)
    }

    @SuppressWarnings("unused")
    Message renderUpwAppointment(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String upwAppointmentID,
            final String eventID,
            final String offenderID,
            final String projectName,
            final String projectType,
            final String appointmentDate,
            final String startTime,
            final String endTime,
            final String minutesOffered,
            final String notes,
            final String highVisibilityVest,
            final String provider,
            final String upwTeam,
            final String upwContactOfficer,
            final String ndcupaContactID,
            final String sensitiveContact,
            final String alert
    ) throws Exception {
        final URL templateUrl = getResourceUrl("templates/UpwAppointment.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", 200)
        final Map<String, Object> data = buildUpwAppointment(
                schemaDate,
                schemaVersion,
                senderId,
                receiverId,
                senderControlRef,
                dateOfPreparation,
                timeOfPreparation,
                appRef,
                testIndicator,
                messageType,
                caseReferenceNumber,
                masterSpgVersion,
                spgUpdateUser,
                upwAppointmentID,
                eventID,
                offenderID,
                projectName,
                projectType,
                appointmentDate,
                startTime,
                endTime,
                minutesOffered,
                notes,
                highVisibilityVest,
                provider,
                upwTeam,
                upwContactOfficer,
                ndcupaContactID,
                sensitiveContact,
                alert,
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, SOAP_1_2_PROTOCOL)
    }

    private static Map<String, Object> buildUpwAppointment(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String masterSpgVersion,
            final String spgUpdateUser,
            final String upwAppointmentID,
            final String eventID,
            final String offenderID,
            final String projectName,
            final String projectType,
            final String appointmentDate,
            final String startTime,
            final String endTime,
            final String minutesOffered,
            final String highVisibilityVest,
            final String notes,
            final String provider,
            final String upwTeam,
            final String upwContactOfficer,
            final String ndcupaContactID,
            final String sensitiveContact,
            final String alert) {

        final Map<String, Object> data = [
                "schemaDate"         : schemaDate,
                "schemaVersion"      : schemaVersion,
                "senderId"           : senderId,
                "receiverId"         : receiverId,
                "senderControlRef"   : senderControlRef,
                "dateOfPreparation"  : dateOfPreparation,
                "timeOfPreparation"  : timeOfPreparation,
                "applicationRef"     : appRef,
                "testIndicator"      : testIndicator,
                "messageType"        : messageType,
                "caseReferenceNumber": caseReferenceNumber,
                "masterSpgVersion"   : masterSpgVersion,
                "spgUpdateUser"      : spgUpdateUser,
                "upwAppointmentID"   : upwAppointmentID,
                "eventID"            : eventID,
                "offenderID"         : offenderID,
                "projectName"        : projectName,
                "projectType"        : projectType,
                "appointmentDate"    : appointmentDate,
                "startTime"          : startTime,
                "endTime"            : endTime,
                "minutesOffered"     : minutesOffered,
                "highVisibilityVest" : highVisibilityVest,
                "notes"              : notes,
                "provider"           : provider,
                "upwTeam"            : upwTeam,
                "upwContactOfficer"  : upwContactOfficer,
                "ndcupaContactID"    : ndcupaContactID,
                "sensitiveContact"   : sensitiveContact,
                "alert"              : alert,
        ] as Map<String, Object>
        return data
    }

    @SuppressWarnings("unused")
    Message renderOffenderAddress(final String version,
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
                                final String offenderAddressID,
                                final String offenderID,
                                final String status,
                                final String houseNumber,
                                final String streetName,
                                final String district,
                                final String townCity,
                                final String county,
                                final String postcode,
                                final String startDate,
                                final String noFixedAbode,
                                final String protocol,
                                final String dateOfPreparation,
                                final String timeOfPreparation) throws Exception {
        final URL templateUrl = getResourceUrl("templates/OffenderAddress.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", notificationCode)
        final Map<String, Object> data = buildOffenderAddress(
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
                offenderAddressID,
                offenderID,
                status,
                houseNumber,
                streetName,
                district,
                townCity,
                county,
                postcode,
                startDate,
                noFixedAbode,
                dateOfPreparation,
                timeOfPreparation
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, protocol)
    }

    private static Map<String, Object> buildOffenderAddress(final String version,
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
                                                            final String offenderAddressID,
                                                            final String offenderID,
                                                            final String status,
                                                            final String houseNumber,
                                                            final String streetName,
                                                            final String district,
                                                            final String townCity,
                                                            final String county,
                                                            final String postcode,
                                                            final String startDate,
                                                            final String noFixedAbode,
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
                "offenderAddressID"  : offenderAddressID,
                "offenderID"         : offenderID,
                "status"             : status,
                "houseNumber"        : houseNumber,
                "streetName"         : streetName,
                "district"           : district,
                "townCity"           : townCity,
                "county"             : county,
                "postcode"           : postcode,
                "startDate"          : startDate,
                "noFixedAbode"       : noFixedAbode,
        ] as Map<String, Object>
        return data
    }

    @SuppressWarnings("unused")
    Message renderOfflocRequest(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String offenderID,
            final String crcSearchID,
            final String offlocRequestUser
    ) throws Exception {
        final URL templateUrl = getResourceUrl("templates/OfflocRequest.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", 200)
        final Map<String, Object> data = buildOfflocRequest(
                schemaDate,
                schemaVersion,
                senderId,
                receiverId,
                senderControlRef,
                dateOfPreparation,
                timeOfPreparation,
                appRef,
                testIndicator,
                messageType,
                caseReferenceNumber,
                offenderID,
                crcSearchID,
                offlocRequestUser,
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, SOAP_1_2_PROTOCOL)
    }

    private static Map<String, Object> buildOfflocRequest(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String offenderID,
            final String crcSearchID,
            final String offlocRequestUser) {

        final Map<String, Object> data = [
                "schemaDate"         : schemaDate,
                "schemaVersion"      : schemaVersion,
                "senderId"           : senderId,
                "receiverId"         : receiverId,
                "senderControlRef"   : senderControlRef,
                "dateOfPreparation"  : dateOfPreparation,
                "timeOfPreparation"  : timeOfPreparation,
                "applicationRef"     : appRef,
                "testIndicator"      : testIndicator,
                "messageType"        : messageType,
                "caseReferenceNumber": caseReferenceNumber,
                "offenderID"         : offenderID,
                "crcSearchID"        : crcSearchID,
                "offlocRequestUser"  : offlocRequestUser
        ] as Map<String, Object>
        return data
    }

    @SuppressWarnings("unused")
    Message renderOfflocRequestV2(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String offenderID,
            final String crcSearchID,
            final String offlocRequestUser
    ) throws Exception {
        final URL templateUrl = getResourceUrl("templates/OfflocRequestV2.tpl")
        final Map<String, Integer> notificationCodeMap = singletonMap("notificationCode", 200)
        final Map<String, Object> data = buildOfflocRequestV2(
                schemaDate,
                schemaVersion,
                senderId,
                receiverId,
                senderControlRef,
                dateOfPreparation,
                timeOfPreparation,
                appRef,
                testIndicator,
                messageType,
                caseReferenceNumber,
                offenderID,
                crcSearchID,
                offlocRequestUser,
        )
        data.putAll(notificationCodeMap)

        final String body = templateRenderer.render(templateUrl, data)

        return buildSoapMessage(body, SOAP_1_2_PROTOCOL)
    }

    private static Map<String, Object> buildOfflocRequestV2(
            final String schemaDate,
            final String schemaVersion,
            final String senderId,
            final String receiverId,
            final String senderControlRef,
            final String dateOfPreparation,
            final String timeOfPreparation,
            final String appRef,
            final String testIndicator,
            final String messageType,
            final String caseReferenceNumber,
            final String offenderID,
            final String crcSearchID,
            final String offlocRequestUser) {

        final Map<String, Object> data = [
                "schemaDate"         : schemaDate,
                "schemaVersion"      : schemaVersion,
                "senderId"           : senderId,
                "receiverId"         : receiverId,
                "senderControlRef"   : senderControlRef,
                "dateOfPreparation"  : dateOfPreparation,
                "timeOfPreparation"  : timeOfPreparation,
                "applicationRef"     : appRef,
                "testIndicator"      : testIndicator,
                "messageType"        : messageType,
                "caseReferenceNumber": caseReferenceNumber,
                "offenderID"         : offenderID,
                "crcSearchID"        : crcSearchID,
                "offlocRequestUser"  : offlocRequestUser
        ] as Map<String, Object>
        return data
    }

}