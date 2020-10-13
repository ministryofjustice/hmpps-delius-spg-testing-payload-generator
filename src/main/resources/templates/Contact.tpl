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

        Contact {
            ContactDetails {
                Offender {
                    CaseReferenceNumber(caseReferenceNumber)
                }

                SPGVersion {

                    if (spgVersion == null) {
                        SPGVersion("00000000000000000000000000000000")
                    } else {
                        SPGVersion(spgVersion)
                    }

                    SPGUpdateUser(spgUpdateUser)
                }

                Contact {
                    ContactID(contactID)
                    OffenderID(offenderID)
                    EventID(eventID)

                    if (nsiID != null) {
                        NSIID(nsiID)
                    }

                    if (alert == null) {
                        Alert('N')
                    } else {
                        Alert(alert)
                    }

                    ContactType(contactType)
                    ContactDate(contactDate)
                    ContactStartTime(contactStartTime)
                    Provider(provider)
                    Team(team)
                    ContactOfficer(contactOfficer)

                    if (location != null) {
                        Location(location)
                    }

                    if (contactOutcome != null) {
                        ContactOutcome(contactOutcome)
                    }

                    SensitiveContact(sensitiveContact)

                    if (notes != null) {
                        Notes(notes)
                    }

                    RARContact(rarContact)
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