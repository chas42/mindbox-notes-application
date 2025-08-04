package com.mindbox.notes.dto;

import com.mindbox.notes.model.NoteHistory;

import java.time.LocalDateTime;

public record NoteHistoryDTO(Long id, String title, String content, LocalDateTime changedAt, Long noteId, Long userId,
                             Integer version) {

    public NoteHistoryDTO(NoteHistory noteHistory) {
        this(noteHistory.getId(), noteHistory.getTitle(), noteHistory.getContent(), noteHistory.getChangedAt(),
                noteHistory.getNote().getId(), noteHistory.getUser().getId(), noteHistory.getVersion());
    }


}
