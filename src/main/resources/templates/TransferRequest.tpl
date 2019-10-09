xmlDeclaration() SPGInterchange('xmlns': "http://www.justice.gsi.gov.uk/SPG/", 'xmlns:xsi': "http://www.w3.org/2001/XMLSchema-instance", SchemaVersion: "0-9-13", SchemaDate: "2018-02-08", 'xsi:schemaLocation': "http://www.justice.gsi.gov.uk/SPG/ SPG-XML_Message_Root-V0-9-13.xsd") {
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

        ConsolidatedTransferRequest {
            TransferRequest {
                TransferRequestDetails {
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

                    TransferRequest {
                        TransferID(transferID)
                        OffenderID(offenderID)
                        TransferDate(transferDate)
                        TransferUser(transferUser)
                    }
                }
            }

            switch (transaction) {
                case "CRTRANSFERREQUEST":
                    CRTransferRequest {
                        CRTransferRequestDetails {
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
                            CRTransferRequest {
                                TransferID(transferID)
                                CourtReportID(entityID)
                                OffenderID(offenderID)
                                CRTransferReason(entityTransferReason)
                                CRTransferStatus(entityTransferStatus)
                                CRTransferFromProvider(entityTransferFromProvider)
                                CRTransferFromResponsibleTeam(entityTransferFromResponsibleTeam)
                                CRTransferFromResponsibleOfficer(entityTransferFromResponsibleOfficer)
                                CRTransferToProvider(entityTransferToProvider)
                                CRTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                CRTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "IRTRANSFERREQUEST":
                    IRTransferRequest {
                        IRTransferRequestDetails {
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
                            IRTransferRequest {
                                TransferID(transferID)
                                InstReportID(entityID)
                                OffenderID(offenderID)
                                IRTransferReason(entityTransferReason)
                                IRTransferStatus(entityTransferStatus)
                                IRTransferFromProvider(entityTransferFromProvider)
                                IRTransferFromResponsibleTeam(entityTransferFromResponsibleTeam)
                                IRTransferFromResponsibleOfficer(entityTransferFromResponsibleOfficer)
                                IRTransferToProvider(entityTransferToProvider)
                                IRTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                IRTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "LCTRANSFERREQUEST":
                    LCTransferRequest {
                        LCTransferRequestDetails {
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
                            LCTransferRequest {
                                TransferID(transferID)
                                LicConditionID(entityID)
                                OffenderID(offenderID)
                                LCTransferReason(entityTransferReason)
                                LCTransferStatus(entityTransferStatus)
                                LCTransferFromProvider(entityTransferFromProvider)
                                LCTransferFromResponsibleTeam(entityTransferFromResponsibleTeam)
                                LCTransferFromResponsibleOfficer(entityTransferFromResponsibleOfficer)
                                LCTransferToProvider(entityTransferToProvider)
                                LCTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                LCTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "NSITRANSFERREQUEST":
                    NSITransferRequest {
                        NSITransferRequestDetails {
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
                            NSITransferRequest {
                                TransferID(transferID)
                                NSIID(entityID)
                                OffenderID(offenderID)
                                NSITransferReason(entityTransferReason)
                                TransferStatus(entityTransferStatus)
                                NSITransferFromProvider(entityTransferFromProvider)
                                NSITransferFromResponsibleTeam(entityTransferFromResponsibleTeam)
                                NSITransferFromResponsibleOfficer(entityTransferFromResponsibleOfficer)
                                NSITransferToProvider(entityTransferToProvider)
                                NSITransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                NSITransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "OMTRANSFERREQUEST":
                    OMTransferRequest {
                        OMTransferRequestDetails {
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
                            OMTransferRequest {
                                TransferID(transferID)
                                OffenderID(offenderID)
                                OMTransferReason(entityTransferReason)
                                OMTransferStatus(entityTransferStatus)
                                OMTransferFromProvider(entityTransferFromProvider)
                                OMTransferFromResponsibleTeam(entityTransferFromResponsibleTeam)
                                OMTransferFromResponsibleOfficer(entityTransferFromResponsibleOfficer)
                                OMTransferToProvider(entityTransferToProvider)
                                OMTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                OMTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "OSTRANSFERREQUEST":
                    OMTransferRequest {
                        OMTransferRequestDetails {
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
                            OMTransferRequest {
                                TransferID(transferID)
                                OffenderID(offenderID)
                                OMTransferReason(entityTransferReason)
                                OMTransferStatus(entityTransferStatus)
                                OMTransferFromProvider(entityTransferFromProvider)
                                OMTransferFromResponsibleTeam(entityTransferFromResponsibleTeam)
                                OMTransferFromResponsibleOfficer(entityTransferFromResponsibleOfficer)
                                OMTransferToProvider(entityTransferToProvider)
                                OMTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                OMTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }

                    OSTransferRequest {
                        OSTransferRequestDetails {
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
                            OSTransferRequest {
                                TransferID(transferID)
                                EventID(entityID)
                                OffenderID(offenderID)
                                OSTransferReason(entityTransferReason)
                                OSTransferStatus(entityTransferStatus)
                                OSTransferFromProvider(entityTransferFromProvider)
                                OSTransferFromResponsibleTeam(entityTransferFromResponsibleTeam)
                                OSTransferFromResponsibleOfficer(entityTransferFromResponsibleOfficer)
                                OSTransferToProvider(entityTransferToProvider)
                                OSTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                OSTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "PSSTRANSFERREQUEST":
                    PSSTransferRequest {
                        PSSTransferRequestDetails {
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
                            PSSTransferRequest {
                                TransferID(transferID)
                                PSSRequirementID(entityID)
                                OffenderID(offenderID)
                                PSSTransferReason(entityTransferReason)
                                PSSTransferStatus(entityTransferStatus)
                                PSSTransferFromProvider(entityTransferFromProvider)
                                PSSTransferFromResponsibleTeam(entityTransferFromResponsibleTeam)
                                PSSTransferFromResponsibleOfficer(entityTransferFromResponsibleOfficer)
                                PSSTransferToProvider(entityTransferToProvider)
                                PSSTransferToResponsibleTeam(entityTransferToResponsibleTeam)
                                PSSTransferToResponsibleOfficer(entityTransferToResponsibleOfficer)
                            }
                        }
                    }
                    break
                case "RQTRANSFERREQUEST":
                    RQTransferRequest {
                        RQTransferRequestDetails {
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
                            RQTransferRequest {
                                TransferID(transferID)
                                RequirementID(entityID)
                                OffenderID(offenderID)
                                RQTransferReason(entityTransferReason)
                                RQTransferStatus(entityTransferStatus)
                                RQTransferFromProvider(entityTransferFromProvider)
                                RQTransferFromResponsibleTeam(entityTransferFromResponsibleTeam)
                                RQTransferFromResponsibleOfficer(entityTransferFromResponsibleOfficer)
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