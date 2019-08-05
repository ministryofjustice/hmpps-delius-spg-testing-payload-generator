SPGInterchange(xmlns: "http://www.justice.gsi.gov.uk/SPG/",
    'xmlns:spg': "http://www.justice.gsi.gov.uk/SPG/",
    'xmlns:xsi': "http://www.w3.org/2001/XMLSchema-instance",
    SchemaDate: schemaDate,
    SchemaVersion: schemaVersion) {
   SPGInterchangeHeader {
      SenderIdentity(senderId)
      ReceiverIdentity(receiverId)
      SenderControlReference(senderControlRef)
      ReceiverControlReference(receiverControlRef)
      DateOfPreparation(dateOfPreparation)
      TimeOfPreparation(timeOfPreparation)
      ApplicationReference(applicationRef)
   }
   SPGMessage {
      SPGMessageHeader {
         MessageType(messageType)
         MessageVersionNumber(schemaVersion)
         MessageReferenceNumber(1)
      }
      InterchangeStatusNotification {
         StatusCode(statusCode)
      }
      SPGMessageTrailer {
         MessageReferenceNumber(1)
      }
   }
   SPGInterchangeTrailer {
      SenderControlReference(senderControlRef)
      ReceiverControlReference(receiverControlRef)
      ControlCount(1)
      SenderIdentity(senderId)
      ReceiverIdentity(receiverId)
   }
}