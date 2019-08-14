package uk.gov.gsi.justice.spg.test.template;

import java.time.LocalDateTime;

public interface DateTimeProvider {
    LocalDateTime provideLocalDateTime();
    String provideDate();
    String provideTime();
}
