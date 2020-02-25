xmlDeclaration() SPGInterchange('xmlns': "http://www.justice.gsi.gov.uk/SPG/", SchemaDate: schemaDate, SchemaVersion: schemaVersion) {
    SPGInterchangeHeader {
        SenderIdentity(senderId)
        ReceiverIdentity(receiverId)
        SenderControlReference(senderControlRef)
        DateOfPreparation(dateOfPreparation)
        TimeOfPreparation(timeOfPreparation)
        ApplicationReference(applicationRef)
        TestIndicator("Y")
    }

    SPGMessage {
        SPGMessageHeader {
            MessageType(messageType)
            MessageVersionNumber(schemaVersion)
            MessageReferenceNumber(1)
        }

        ApprovedPremisesReferral {
            ApprovedPremisesReferralDetails {
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

                ApprovedPremisesReferral {
                    APReferralID(apReferralID)
                    EventID(eventID)
                    OffenderID(offenderID)
                    ReferralDate(referralDate)
                    ReferringProvider(referringProvider)
                    ReferringTeam(referringTeam)
                    ReferringOfficer(referringOfficer)
                    ReferralCategory(referralCategory)
                    ReferralDecision(referralDecision)
                    ReferralGroup(referralGroup)
                    APReferralSource(apReferralSource)
                    SourceType(sourceType)
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