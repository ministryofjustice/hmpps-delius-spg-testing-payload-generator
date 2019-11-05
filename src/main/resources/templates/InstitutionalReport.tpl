// xmlDeclaration() SPGInterchange('xmlns': "http://www.justice.gsi.gov.uk/SPG/", 'xmlns:xsi': "http://www.w3.org/2001/XMLSchema-instance", SchemaDate: schemaDate, SchemaVersion: schemaVersion, 'xsi:schemaLocation': "http://www.justice.gsi.gov.uk/SPG/ SPG-XML_Message_Root-V" + schemaVersion + ".xsd") {
//     SPGInterchangeHeader {
//         SenderIdentity(senderId)
//         ReceiverIdentity(receiverId)
//         SenderControlReference(senderControlRef)
//         DateOfPreparation(dateOfPreparation)
//         TimeOfPreparation(timeOfPreparation)
//         ApplicationReference(applicationRef)
//         TestIndicator(testIndicator)
//     }
//
//     SPGMessage {
//         SPGMessageHeader {
//             MessageType(messageType)
//             MessageVersionNumber(schemaVersion)
//             MessageReferenceNumber(1)
//         }
//
//         CommunityRequirement {
//             CommunityRequirementDetails {
//                 Offender {
//                     CaseReferenceNumber(caseReferenceNumber)
//                 }
//
//                 SPGVersion {
//                     if (masterSpgVersion == null || messageType.equals("INS")) {
//                         SPGVersion("00000000000000000000000000000000")
//                     } else {
//                         SPGVersion(masterSpgVersion)
//                     }
//
//                     SPGUpdateUser(spgUpdateUser)
//                 }
//
//                 CommunityRequirement {
//                     if (requirementID == null) {
//                         RequirementID("0")
//                     } else {
//                         RequirementID(requirementID)
//                     }
//
//                     EventID(eventID)
//                     OffenderID(offenderID)
//                     RequirementTypeMainCategory(requirementTypeMainCategory)
//                     RequirementTypeSubCategory(requirementTypeSubCategory)
//                     ImposedDate(imposedDate)
//                     Length(length)
//
//                     if (actualEndDate != null) {
//                         ActualEndDate(actualEndDate)
//                     }
//
//                     if (terminationReason != null) {
//                         TerminationReason(terminationReason)
//                     }
//
//                     RQProvider(rqProvider)
//                     RQResponsibleTeam(rqResponsibleTeam)
//                     RQResponsibleOfficer(rqResponsibleOfficer)
//                 }
//             }
//         }
//
//         SPGMessageTrailer {
//             MessageReferenceNumber(1)
//         }
//     }
//
//     SPGInterchangeTrailer {
//         SenderControlReference(senderControlRef)
//         ControlCount(1)
//         SenderIdentity(senderId)
//         ReceiverIdentity(receiverId)
//     }
// }