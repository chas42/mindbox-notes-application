package com.mindbox.notes.repository;

import com.mindbox.notes.model.NoteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteHistoryRepository extends JpaRepository<NoteHistory, Long> {

    List<NoteHistory> findByNoteId(long noteId);

}
