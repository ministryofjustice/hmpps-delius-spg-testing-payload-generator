<SPGInterchange xmlns:gsp='http://groovy.codehaus.org/2005/gsp'
 xmlns="http://www.justice.gsi.gov.uk/SPG/"
 SchemaDate="${schemaDate}"
 SchemaVersion="${schemaVersion}">
    <SPGInterchangeHeader>
        <SenderIdentity>$senderId</SenderIdentity>
        <ReceiverIdentity>$receiverId</ReceiverIdentity>
        <SenderControlReference>$senderControlRef</SenderControlReference>

        <gsp:scriptlet>if (receiverControlRef != null) {</gsp:scriptlet>
            <ReceiverControlReference>$receiverControlRef</ReceiverControlReference>
        <gsp:scriptlet>}</gsp:scriptlet>

        <DateOfPreparation>$dateOfPreparation</DateOfPreparation>
        <TimeOfPreparation>$timeOfPreparation</TimeOfPreparation>
        <gsp:scriptlet>if (applicationRef != null) {</gsp:scriptlet>
            <ApplicationReference>$applicationRef</ApplicationReference>
        <gsp:scriptlet>}</gsp:scriptlet>
    </SPGInterchangeHeader>

    <SPGMessage>
        <SPGMessageHeader>
            <MessageType>$messageType</MessageType>
            <MessageVersionNumber>$schemaVersion</MessageVersionNumber>
            <MessageReferenceNumber>1</MessageReferenceNumber>
        </SPGMessageHeader>

        <gsp:scriptlet>if (notificationCode == null) {</gsp:scriptlet>
            <ConnectionNotification/>
        <gsp:scriptlet>} else {</gsp:scriptlet>
            <ConnectionNotification>
                <NotificationCode>$notificationCode</NotificationCode>
            </ConnectionNotification>
        <gsp:scriptlet>}</gsp:scriptlet>

        <SPGMessageTrailer>
            <MessageReferenceNumber>1</MessageReferenceNumber>
        </SPGMessageTrailer>
    </SPGMessage>

    <SPGInterchangeTrailer>
        <SenderControlReference>$senderControlRef</SenderControlReference>

        <gsp:scriptlet>if (receiverControlRef != null) {</gsp:scriptlet>
            <ReceiverControlReference>$receiverControlRef</ReceiverControlReference>
        <gsp:scriptlet>}</gsp:scriptlet>
        <ControlCount>1</ControlCount>
        <SenderIdentity>$senderId</SenderIdentity>
        <ReceiverIdentity>$receiverId</ReceiverIdentity>
    </SPGInterchangeTrailer>
</SPGInterchange>