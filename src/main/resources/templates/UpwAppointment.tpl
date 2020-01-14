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

        UPWContact {
            UPWContactDetails {
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

                UPWAppointment {
                    UPWAppointmentID(upwAppointmentID)
                    EventID(eventID)
                    OffenderID(offenderID)
                    ProjectName(projectName)
                    ProjectType(projectType)
                    AppointmentDate(appointmentDate)
                    StartTime(startTime)
                    EndTime(endTime)
                    MinutesOffered(minutesOffered)

                    if (highVisibilityVest != null) {
                        HighVisibilityVest(highVisibilityVest)
                    }

                    if (notes != null) {
                        Notes(notes)
                    }

                    Provider(provider)
                    UPWTeam(upwTeam)
                    UPWContactOfficer(upwContactOfficer)

                    if (ndcupaContactID != null) {
                        NDCUPAContactID(ndcupaContactID)
                    }

                    if (sensitiveContact != null) {
                        SensitiveContact(sensitiveContact)
                    }

                    if (alert != null) {
                        Alert(alert)
                    }
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