<SPGInterchange xmlns="http://www.justice.gsi.gov.uk/SPG/"
    xmlns:spg="http://www.justice.gsi.gov.uk/SPG/"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    SchemaDate="${schemaDate}"
    SchemaVersion="${schemaVersion}">
   <SPGInterchangeHeader>
      <SenderIdentity>$senderId</SenderIdentity>
      <ReceiverIdentity>$receiverId</ReceiverIdentity>
      <SenderControlReference>$senderControlRef</SenderControlReference>
      <ReceiverControlReference>$receiverControlRef</ReceiverControlReference>
      <DateOfPreparation>$dateOfPreparation</DateOfPreparation>
      <TimeOfPreparation>$timeOfPreparation</TimeOfPreparation>
      <ApplicationReference>$applicationRef</ApplicationReference>
   </SPGInterchangeHeader>
   <SPGMessage>
      <SPGMessageHeader>
         <MessageType>$messageType</MessageType>
         <MessageVersionNumber>$schemaVersion</MessageVersionNumber>
         <MessageReferenceNumber>1</MessageReferenceNumber>
      </SPGMessageHeader>
      <InterchangeStatusNotification>
         <StatusCode>$statusCode</StatusCode>
      </InterchangeStatusNotification>
      <SPGMessageTrailer>
         <MessageReferenceNumber>1</MessageReferenceNumber>
      </SPGMessageTrailer>
   </SPGMessage>
   <SPGInterchangeTrailer>
      <SenderControlReference>$senderControlRef</SenderControlReference>
      <ReceiverControlReference>$receiverControlRef</ReceiverControlReference>
      <ControlCount>1</ControlCount>
      <SenderIdentity>$senderId</SenderIdentity>
      <ReceiverIdentity>$receiverId</ReceiverIdentity>
   </SPGInterchangeTrailer>
</SPGInterchange>