xmlDeclaration() SPGInterchange('xmlns': "http://www.justice.gsi.gov.uk/SPG/", SchemaDate: schemaDate, SchemaVersion: schemaVersion) {
    SPGInterchangeHeader {
        SenderIdentity(senderId)
        ReceiverIdentity(receiverId)
        SenderControlReference(senderControlRef)
        ReceiverControlReference(receiverControlReference)
        DateOfPreparation(dateOfPreparation)
        TimeOfPreparation(timeOfPreparation)
        ApplicationReference(applicationRef)
    }

    SPGMessage {
        SPGMessageHeader {
            MessageType(messageType)
            MessageVersionNumber(schemaVersion)
            MessageReferenceNumber(1)
        }

        GetDocumentList {
            GetDocumentListDetails {
                Offender {
                    CaseReferenceNumber(caseReferenceNumber)
                }

                GetDocumentList {
                    OffenderID(offenderID)
                    CRCSearchID(crcSearchID)
                    RefreshRequestUser(refreshRequestUser)
                }
            }
        }

        SPGMessageTrailer {
            MessageReferenceNumber(1)
        }
    }

    SPGInterchangeTrailer {
        SenderControlReference(senderControlRef)
        ReceiverControlReference(receiverControlReference)
        ControlCount(1)
        SenderIdentity(senderId)
        ReceiverIdentity(receiverId)
    }
}