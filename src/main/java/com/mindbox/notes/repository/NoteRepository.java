package com.mindbox.notes.repository;

import com.mindbox.notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
