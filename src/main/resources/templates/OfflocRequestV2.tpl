xmlDeclaration() SPGInterchange('xmlns': "http://www.justice.gsi.gov.uk/SPG/", 'xmlns:xsi': "http://www.w3.org/2001/XMLSchema-instance", SchemaDate: schemaDate, SchemaVersion: schemaVersion, 'xsi:schemaLocation': "http://www.justice.gsi.gov.uk/SPG/ SPG-XML_Message_Root-V" + schemaVersion + ".xsd") {
    SPGInterchangeHeader {
        SenderIdentity(senderId)
        ReceiverIdentity(receiverId)
        SenderControlReference(senderControlRef)
        DateOfPreparation(dateOfPreparation)
        TimeOfPreparation(timeOfPreparation)
        ApplicationReference(applicationRef)
        TestIndicator(testIndicator)
    }

    SPGMessage {
        SPGMessageHeader {
            MessageType(messageType)
            MessageVersionNumber(schemaVersion)
            MessageReferenceNumber(1)
        }

        OFFLOCRequest2 {
            OFFLOCRequest2Details {
                Offender {
                    CaseReferenceNumber(caseReferenceNumber)
                }

                OFFLOCRequest2 {
                    OffenderID(offenderID)
                    CRCSearchID(crcSearchID)
                    OFFLOCRequestUser(offlocRequestUser)
                }
            }
        }

        SPGMessageTrailer {
            MessageReferenceNumber(1)
        }
    }

    SPGInterchangeTrailer {
        SenderControlReference(senderControlRef)
        ControlCount(1)
        SenderIdentity(senderId)
        ReceiverIdentity(receiverId)
    }
}