SPGInterchange(xmlns: 'http://www.justice.gsi.gov.uk/SPG/', SchemaDate: schemaDate, SchemaVersion: schemaVersion) {
    SPGInterchangeHeader {
        SenderIdentity(senderId)
        ReceiverIdentity(receiverId)
        SenderControlReference(senderControlRef)

        if (receiverControlRef != null) {
            ReceiverControlReference(receiverControlRef)
        }

        DateOfPreparation(dateOfPreparation)
        TimeOfPreparation(timeOfPreparation)
        if (applicationRef != null) {
            ApplicationReference(applicationRef)
        }

        if (testIndicator != null) {
            TestIndicator(testIndicator)
        }
        if (testControlData != null) {
            TestControlData(testControlData)
        }
    }

    SPGMessage {
        SPGMessageHeader {
            MessageType(messageType)
            MessageVersionNumber(schemaVersion)
            MessageReferenceNumber(1)
        }

        if (notificationCode == null) {
            ConnectionNotification()
        } else {
            ConnectionNotification {
                NotificationCode(notificationCode)
            }
        }

        SPGMessageTrailer {
            MessageReferenceNumber(1)
        }
    }

    SPGInterchangeTrailer {
        SenderControlReference(senderControlRef)

        if (receiverControlRef != null) {
            ReceiverControlReference(receiverControlRef)
        }
        ControlCount(1)
        SenderIdentity(senderId)
        ReceiverIdentity(receiverId)
    }
}