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

        ConsolidatedTransferResponse {
            TransferResponse {
                TransferResponseDetails {
                    Offender {
                        CaseReferenceNumber(caseReferenceNumber)
                    }

                    SPGVersion {
                        if (masterSpgVersion == null) {
                            SPGVersion("00000000000000000000000000000000")
                        } else {
                            SPGVersion(masterSpgVersion)
                        }

                        SPGUpdateUser(spgUpdateUser)
                    }

                    TransferResponse {
                        TransferID(transferID)
                        OffenderID(offenderID)
                        TransferDate(transferDate)
                        TransferUser(transferUser)
                    }
                }
            }

            switch (transaction) {
                case "CRTRANSFERRESPONSE":
                    CRTransferResponse {
                        CRTransferResponseDetails {
                            Offender {
                                CaseReferenceNumber(caseReferenceNumber)
                            }
                            SPGVersion {
                                if (entitySpgVersion == null) {
                                    SPGVersion("00000000000000000000000000000000")
                                } else {
                                    SPGVersion(entitySpgVersion)
                                }
                                SPGUpdateUser(spgUpdateUser)
                            }
                            CRTransferResponse {
                                TransferID(transferID)
                                CourtReportID(entityID)
                                OffenderID(offenderID)
                                CRTransferStatus(entityTransferStatus)

                                if (entityTransferStatus.equals("TR")) {
                                    CRTransferRejectedReason(entityTransferRejectedReason)
                                }

                                CRTransferFromProvider(entityTransferFromProvider)
                                CRTransferToProvider(entityTransferToProvider)
                                CRTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                CRTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "IRTRANSFERRESPONSE":
                    IRTransferResponse {
                        IRTransferResponseDetails {
                            Offender {
                                CaseReferenceNumber(caseReferenceNumber)
                            }
                            SPGVersion {
                                if (entitySpgVersion == null) {
                                    SPGVersion("00000000000000000000000000000000")
                                } else {
                                    SPGVersion(entitySpgVersion)
                                }
                                SPGUpdateUser(spgUpdateUser)
                            }
                            IRTransferResponse {
                                TransferID(transferID)
                                InstReportID(entityID)
                                OffenderID(offenderID)
                                IRTransferStatus(entityTransferStatus)

                                if (entityTransferStatus.equals("TR")) {
                                    IRTransferRejectedReason(entityTransferRejectedReason)
                                }

                                IRTransferFromProvider(entityTransferFromProvider)
                                IRTransferToProvider(entityTransferToProvider)
                                IRTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                IRTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "LCTRANSFERRESPONSE":
                    LCTransferResponse {
                        LCTransferResponseDetails {
                            Offender {
                                CaseReferenceNumber(caseReferenceNumber)
                            }
                            SPGVersion {
                                if (entitySpgVersion == null) {
                                    SPGVersion("00000000000000000000000000000000")
                                } else {
                                    SPGVersion(entitySpgVersion)
                                }
                                SPGUpdateUser(spgUpdateUser)
                            }
                            LCTransferResponse {
                                TransferID(transferID)
                                LicConditionID(entityID)
                                OffenderID(offenderID)
                                LCTransferStatus(entityTransferStatus)

                                if (entityTransferStatus.equals("TR")) {
                                    LCTransferRejectedReason(entityTransferRejectedReason)
                                }

                                LCTransferFromProvider(entityTransferFromProvider)
                                LCTransferToProvider(entityTransferToProvider)
                                LCTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                LCTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "NSITRANSFERRESPONSE":
                    NSITransferResponse {
                        NSITransferResponseDetails {
                            Offender {
                                CaseReferenceNumber(caseReferenceNumber)
                            }
                            SPGVersion {
                                if (entitySpgVersion == null) {
                                    SPGVersion("00000000000000000000000000000000")
                                } else {
                                    SPGVersion(entitySpgVersion)
                                }
                                SPGUpdateUser(spgUpdateUser)
                            }
                            NSITransferResponse {
                                TransferID(transferID)
                                NSIID(entityID)
                                OffenderID(offenderID)
                                NSITransferStatus(entityTransferStatus)

                                if (entityTransferStatus.equals("TR")) {
                                    NSITransferRejectedReason(entityTransferRejectedReason)
                                }

                                NSITransferFromProvider(entityTransferFromProvider)
                                NSITransferToProvider(entityTransferToProvider)
                                NSITransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                NSITransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "OMTRANSFERRESPONSE":
                    OMTransferResponse {
                        OMTransferResponseDetails {
                            Offender {
                                CaseReferenceNumber(caseReferenceNumber)
                            }
                            SPGVersion {
                                if (entitySpgVersion == null) {
                                    SPGVersion("00000000000000000000000000000000")
                                } else {
                                    SPGVersion(entitySpgVersion)
                                }
                                SPGUpdateUser(spgUpdateUser)
                            }
                            OMTransferResponse {
                                TransferID(transferID)
                                OffenderID(offenderID)
                                OMTransferStatus(entityTransferStatus)

                                if (entityTransferStatus.equals("TR")) {
                                    OMTransferRejectedReason(entityTransferRejectedReason)
                                }

                                OMTransferFromProvider(entityTransferFromProvider)
                                OMTransferToProvider(entityTransferToProvider)
                                OMTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                OMTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "OSTRANSFERRESPONSE":
                    OMTransferResponse {
                        OMTransferResponseDetails {
                            Offender {
                                CaseReferenceNumber(caseReferenceNumber)
                            }
                            SPGVersion {
                                if (entitySpgVersion == null) {
                                    SPGVersion("00000000000000000000000000000000")
                                } else {
                                    SPGVersion(entitySpgVersion)
                                }
                                SPGUpdateUser(spgUpdateUser)
                            }
                            OMTransferResponse {
                                TransferID(transferID)
                                OffenderID(offenderID)
                                OMTransferStatus(entityTransferStatus)

                                if (entityTransferStatus.equals("TR")) {
                                    OMTransferRejectedReason(entityTransferRejectedReason)
                                }

                                OMTransferFromProvider(entityTransferFromProvider)
                                OMTransferToProvider(entityTransferToProvider)
                                OMTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                OMTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }

                    OSTransferResponse {
                        OSTransferResponseDetails {
                            Offender {
                                CaseReferenceNumber(caseReferenceNumber)
                            }
                            SPGVersion {
                                if (entitySpgVersion == null) {
                                    SPGVersion("00000000000000000000000000000000")
                                } else {
                                    SPGVersion(entitySpgVersion)
                                }
                                SPGUpdateUser(spgUpdateUser)
                            }
                            OSTransferResponse {
                                TransferID(transferID)
                                EventID(entityID)
                                OffenderID(offenderID)
                                OSTransferStatus(entityTransferStatus)

                                if (entityTransferStatus.equals("TR")) {
                                    OSTransferRejectedReason(entityTransferRejectedReason)
                                }

                                OSTransferFromProvider(entityTransferFromProvider)
                                OSTransferToProvider(entityTransferToProvider)
                                OSTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                OSTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "PSSTRANSFERRESPONSE":
                    PSSTransferResponse {
                        PSSTransferResponseDetails {
                            Offender {
                                CaseReferenceNumber(caseReferenceNumber)
                            }
                            SPGVersion {
                                if (entitySpgVersion == null) {
                                    SPGVersion("00000000000000000000000000000000")
                                } else {
                                    SPGVersion(entitySpgVersion)
                                }
                                SPGUpdateUser(spgUpdateUser)
                            }
                            PSSTransferResponse {
                                TransferID(transferID)
                                PSSRequirementID(entityID)
                                OffenderID(offenderID)
                                PSSTransferStatus(entityTransferStatus)

                                if (entityTransferStatus.equals("TR")) {
                                    PSSTransferRejectedReason(entityTransferRejectedReason)
                                }

                                PSSTransferFromProvider(entityTransferFromProvider)
                                PSSTransferToProvider(entityTransferToProvider)
                                PSSTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                PSSTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "RQTRANSFERRESPONSE":
                    RQTransferResponse {
                        RQTransferResponseDetails {
                            Offender {
                                CaseReferenceNumber(caseReferenceNumber)
                            }
                            SPGVersion {
                                if (entitySpgVersion == null) {
                                    SPGVersion("00000000000000000000000000000000")
                                } else {
                                    SPGVersion(entitySpgVersion)
                                }
                                SPGUpdateUser(spgUpdateUser)
                            }
                            RQTransferResponse {
                                TransferID(transferID)
                                RequirementID(entityID)
                                OffenderID(offenderID)
                                RQTransferStatus(entityTransferStatus)

                                if (entityTransferStatus.equals("TR")) {
                                    RQTransferRejectedReason(entityTransferRejectedReason)
                                }

                                RQTransferFromProvider(entityTransferFromProvider)
                                RQTransferToProvider(entityTransferToProvider)
                                RQTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                RQTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                default:
                    fail()
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