package com.platform.pidminy.entity.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Availability {
    private Map<DayOfWeek, Set<TimeSlot>> weeklyAvailability;
    private Set<String> preferredShiftTypes;
    private Integer maxHoursPerWeek;
    private Integer maxHoursPerDay;
    private Set<String> unavailableDates;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TimeSlot {
        private LocalTime startTime;
        private LocalTime endTime;
    }
}
