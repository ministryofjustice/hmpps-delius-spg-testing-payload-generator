package uk.gov.gsi.justice.spg.test.template.model;

import javax.xml.soap.SOAPMessage;
import java.util.Objects;

public class Message {
    private final String simpleXml;
    private final SOAPMessage soapMessage;

    public Message(String simpleXml, SOAPMessage soapMessage) {
        this.simpleXml = simpleXml;
        this.soapMessage = soapMessage;
    }

    public String getSimpleXml() {
        return simpleXml;
    }

    public SOAPMessage getSoapMessage() {
        return soapMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return simpleXml.equals(message.simpleXml) &&
                soapMessage.equals(message.soapMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(simpleXml, soapMessage);
    }

    @Override
    public String toString() {
        return "Message{" +
                "simpleXml='" + simpleXml + '\'' +
                ", soapMessage=" + soapMessage +
                '}';
    }
}
