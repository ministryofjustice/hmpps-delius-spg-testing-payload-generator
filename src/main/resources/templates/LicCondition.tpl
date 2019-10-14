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

        LicenceCondition {
            LicenceConditionDetails {
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

                LicCondition {
                    if (entityID == null) {
                        LicConditionID("0")
                    } else {
                        LicConditionID(entityID)
                    }

                    EventID(eventID)
                    OffenderID(offenderID)
                    LicCondTypeMainCategory(entityTypeMainCategory)

                    if (entityTypeSubCategory != null) {
                    LicCondTypeSubCategory(entityTypeSubCategory)
                    }

                    SentenceDate(sentenceDate)

                    if (expectedStartDate != null) {
                        ExpectedStartDate(expectedStartDate)
                    }

                    if (actualStartDate != null) {
                        ActualStartDate(actualStartDate)
                    }

                    if (notes != null) {
                        Notes(notes)
                    }

                    if (expectedEndDate != null) {
                        ExpectedEndDate(expectedEndDate)
                    }

                    if (actualEndDate != null) {
                        ActualEndDate(actualEndDate)
                    }

                    if (terminationReason != null) {
                        TerminationReason(terminationReason)
                    }

                    LCProvider(lcProvider)
                    LCResponsibleTeam(lcResponsibleTeam)
                    LCResponsibleOfficer(lcResponsibleOfficer)
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