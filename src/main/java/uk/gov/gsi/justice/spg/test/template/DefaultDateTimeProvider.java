package uk.gov.gsi.justice.spg.test.template;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DefaultDateTimeProvider implements DateTimeProvider {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public LocalDateTime provideLocalDateTime() {
        return LocalDateTime.now();
    }

    @Override
    public String provideDate() {
        final LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String provideTime() {
        final LocalDateTime dateTime = LocalDateTime.now();
        return TIME_FORMATTER.format(dateTime);
    }
}