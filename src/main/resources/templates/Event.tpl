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

        Event {
            EventDetails {
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

                Event {
                    EventID(eventID)
                    OffenderID(offenderID)
                    ReferralDate(referralDate)
                    EventNumber(eventNumber)

                    if (notes != null) {
                        Notes(notes)
                    }

                    if (convictionDate != null) {
                        ConvictionDate(convictionDate)
                    }

                    OSResponsibleTeam(osResponsibleTeam)
                    OSResponsibleOfficer(osResponsibleOfficer)
                    OSProvider(osProvider)
                    OffenceDate(offenceDate)
                    OffenceCount(offenceCount)

                    if (tics != null) {
                        TICS(tics)
                    }

                    OffenceCode(offenceCode)

                    if (sentenceDate != null) {
                        SentenceDate(sentenceDate)
                    }

                    if (orderType != null) {
                        OrderType(orderType)
                    }

                    if (value != null) {
                        Value(value)
                    }

                    if (length != null) {
                        Length(length)
                    }

                    if (lengthUnit != null) {
                        LengthUnit(lengthUnit)
                    }

                    if (enteredLength != null) {
                        EnteredLength(enteredLength)
                    }

                    if (enteredLengthUnit != null) {
                        EnteredLengthUnit(enteredLengthUnit)
                    }

                    if (lengthDays != null) {
                        LengthDays(lengthDays)
                    }

                    if (secondLength != null) {
                        SecondLength(secondLength)
                    }

                    if (secondLengthUnit != null) {
                        SecondLengthUnit(secondLengthUnit)
                    }

                    if (expectedEndDate != null) {
                        ExpectedEndDate(expectedEndDate)
                    }

                    if (terminationDate != null) {
                        TerminationDate(terminationDate)
                    }

                    if (terminationReason != null) {
                        TerminationReason(terminationReason)
                    }

                    if (rsrScore != null) {
                        RSRScore(rsrScore)
                    }

                    if (rsrDate != null) {
                        RSRDate(rsrDate)
                    }

                    if (rsrProvider != null) {
                        RSRProvider(rsrProvider)
                    }

                    if (allocationDecision != null) {
                        AllocationDecision(allocationDecision)
                    }

                    if (allocationDate != null) {
                        AllocationDate(allocationDate)
                    }

                    if (decisionProvider != null) {
                        DecisionProvider(decisionProvider)
                    }

                    if (allocatedProvider != null) {
                        AllocatedProvider(allocatedProvider)
                    }

                    if (ogrs2Score != null) {
                        OGRS2Score(ogrs2Score)
                    }

                    if (ogrs3Score1 != null) {
                        OGRS3Score1(ogrs3Score1)
                    }

                    if (ogrs3Score2 != null) {
                        OGRS3Score2(ogrs3Score2)
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