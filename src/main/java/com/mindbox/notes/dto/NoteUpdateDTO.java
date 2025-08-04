package com.mindbox.notes.dto;

public record NoteUpdateDTO(Long id, String title, String content, Long userId) {
}
