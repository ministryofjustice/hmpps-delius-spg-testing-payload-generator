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

        ProcessContact {
            ProcessContactDetails {
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

                ProcessContact {
                    if (processID == null) {
                        ProcessID("0")
                    } else {
                        ProcessID(processID)
                    }

                    OffenderID(offenderID)
                    ProcessType(processType)
                    ProcessRefDate(processRefDate)
                    ProcessStartDate(processStartDate)

                    if (processExpEndDate != null) {
                        ProcessExpEndDate(processExpEndDate)
                    }

                    if (processEndDate != null) {
                        ProcessEndDate(processEndDate)
                    }

                    ProcessStage(processStage)
                    ProcessStageDateTime(processStageDateTime)

                    if (processOutcome != null) {
                        ProcessOutcome(processOutcome)
                    }

                    IntendedProvider(intendedProvider)
                    ProcessManagerProvider(processManagerProvider)
                    ProcessManagerTeam(processManagerTeam)
                    ProcessManagerOfficer(processManagerOfficer)
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