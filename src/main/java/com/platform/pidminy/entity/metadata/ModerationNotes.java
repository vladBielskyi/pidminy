package com.platform.pidminy.entity.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModerationNotes {
    private String moderationReason;
    private String moderatedBy;
    private String originalComment;
    private Boolean isBlocked;
    private Boolean isFlagged;
}
