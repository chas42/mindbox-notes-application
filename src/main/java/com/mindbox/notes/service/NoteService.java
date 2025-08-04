package com.mindbox.notes.service;

import com.mindbox.notes.dto.NoteHistoryDTO;
import com.mindbox.notes.dto.NoteUpdateDTO;
import com.mindbox.notes.model.Note;
import com.mindbox.notes.model.NoteHistory;

import java.util.List;

public interface NoteService {

    void create(Note note);
    Note getById(long id);
    void update(NoteUpdateDTO noteUpdateDTO);
    void delete(long id);
    List<Note> findAll();
    List<NoteHistoryDTO> findAllHistoryByNoteId(long noteId);

}
