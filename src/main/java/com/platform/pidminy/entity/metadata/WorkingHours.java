package com.platform.pidminy.entity.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHours {
    private Map<DayOfWeek, DailyHours> regularHours;
    private Map<String, DailyHours> specialDates;
    private boolean isOpen24Hours;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DailyHours {
        private LocalTime openTime;
        private LocalTime closeTime;
        private boolean isClosed;
    }
}
