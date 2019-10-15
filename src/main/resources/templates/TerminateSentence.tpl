xmlDeclaration() SPGInterchange('xmlns': "http://www.justice.gsi.gov.uk/SPG/", 'xmlns:xsi': "http://www.w3.org/2001/XMLSchema-instance", SchemaDate: "2018-02-08", SchemaVersion: "0-9-13", 'xsi:schemaLocation': "http://www.justice.gsi.gov.uk/SPG/ SPG-XML_Message_Root-V0-9-13.xsd") {
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
            MessageVersionNumber("0-9-13")
            MessageReferenceNumber(1)
        }

        TerminateSentence {
            TerminateSentenceDetails {
                Offender {
                    CaseReferenceNumber(caseReferenceNumber)
                }

                SPGVersion {
                    if (masterSpgVersion == null || messageType.equals("INS")) {
                        SPGVersion("00000000000000000000000000000000")
                    } else {
                        SPGVersion(masterSpgVersion)
                    }

                    SPGUpdateUser(spgUpdateUser)
                }

                TerminateSentence {
                    EventID(eventID)
                    OffenderID(offenderID)
                    TerminateUser(spgUpdateUser)
                    TerminationDate(terminationDate)
                    TerminationReason(terminationReason)
                    TerminationDateTime(terminationDateTime)
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