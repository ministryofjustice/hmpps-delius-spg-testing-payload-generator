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

        RateCardIntervention {
            RateCardInterventionDetails {
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

                RateCardIntervention {
                    if (interventionID == null) {
                        InterventionID("0")
                    } else {
                        InterventionID(interventionID)
                    }

                    if (eventID != null) {
                        EventID(eventID)
                    }

                    if (requirementID != null) {
                        RequirementID(requirementID)
                    }

                    OffenderID(offenderID)
                    InterventionTypeMainCategory(interventionTypeMainCategory)

                    if (interventionTypeSubCategory != null)
                        InterventionTypeSubCategory(interventionTypeSubCategory)
                    }

                    if (referralDate != null) {
                        ReferralDate(referralDate)
                    }

                    if (expectedStartDate != null) {
                        ExpectedStartDate(expectedStartDate)
                    }

                    if (actualStartDate != null) {
                        ActualStartDate(actualStartDate)
                    }

                    if (length != null) {
                        Length(length)
                    }

                    if (notes != null) {
                        Notes(notes)
                    }

                    InterventionStatus(interventionStatus)
                    InterventionStatusDateTime(interventionStatusDateTime)

                    if (expectedEndDate != null) {
                        ExpectedEndDate(expectedEndDate)
                    }

                    if (actualEndDate != null) {
                        ActualEndDate(actualEndDate)
                    }

                    if (interventionOutcome != null) {
                        InterventionOutCome(interventionOutcome)
                    }

                    if (attendanceCount != null) {
                        AttendanceCount(attendanceCount)
                    }

                    if (intendedProvider != null) {
                        IntendedProvider(intendedProvider)
                    }

                    InterventionProvider(interventionProvider)
                    InterventionResponsibleTeam(interventionResponsibleTeam)
                    InterventionResponsibleOfficer(interventionResponsibleOfficer)
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