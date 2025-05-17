package com.platform.pidminy.entity.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessNotes {
    private String privateNotes;
    private String publicFeedback;
    private List<String> issues;
    private Map<String, Integer> ratingBreakdown;
}
