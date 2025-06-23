package dev.timi15.gratitudejournal.mapper;

import com.google.cloud.Timestamp;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateConverter {

    @Named("timestampToLocalDate")
    public static LocalDate convertTimestampToLocalDate(Timestamp timestamp) {
        return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Named("localDateToTimestamp")
    public static Timestamp convertLocalDateToTimestamp(LocalDate localDate) {
        long epochSecond = localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
        return Timestamp.ofTimeSecondsAndNanos(epochSecond, 0);
    }
}
