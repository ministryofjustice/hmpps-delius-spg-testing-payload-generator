package uk.gov.gsi.justice.spg.test.template.model;

import java.util.Objects;

public class Notification {
    private final String unsigned;
    private final String signed;

    public Notification(String unsigned, String signed) {
        this.unsigned = unsigned;
        this.signed = signed;
    }

    public String getUnsigned() {
        return unsigned;
    }

    public String getSigned() {
        return signed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return unsigned.equals(that.unsigned) &&
                signed.equals(that.signed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unsigned, signed);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "unsigned='" + unsigned + '\'' +
                ", signed='" + signed + '\'' +
                '}';
    }
}
