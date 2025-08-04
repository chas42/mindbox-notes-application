package com.mindbox.notes.service;

import com.mindbox.notes.dto.NoteHistoryDTO;
import com.mindbox.notes.dto.NoteUpdateDTO;
import com.mindbox.notes.model.Note;
import com.mindbox.notes.model.NoteHistory;
import com.mindbox.notes.repository.NoteHistoryRepository;
import com.mindbox.notes.repository.NoteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository notesRepository;
    private final NoteHistoryRepository noteHistoryRepository;
    private final UserService userService;

    @Autowired
    public NoteServiceImpl(NoteRepository notesRepository, NoteHistoryRepository noteHistoryRepository,
            UserService userService) {
        this.notesRepository = notesRepository;
        this.noteHistoryRepository = noteHistoryRepository;
        this.userService = userService;
    }


    @Override
    public void create(Note note) {
        note.setCreatedAt(LocalDateTime.now());
        notesRepository.save(note);
    }

    @Override
    public Note getById(long id) {
        return notesRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void update(NoteUpdateDTO noteUpdateDTO) {
        var note = notesRepository.findById(noteUpdateDTO.id()).orElseThrow(EntityNotFoundException::new);
        var user = userService.findById(noteUpdateDTO.userId());

        var noteHistory = new NoteHistory();

        noteHistory.setNote(note);
        noteHistory.setContent(noteUpdateDTO.content());
        noteHistory.setTitle(noteUpdateDTO.title());
        noteHistory.setVersion(note.getVersion());
        noteHistory.setUser(user);
        noteHistory.setChangedAt(LocalDateTime.now());

        noteHistoryRepository.save(noteHistory);

        note.setTitle(noteUpdateDTO.title());
        note.setContent( noteUpdateDTO.content());
        note.setUpdatedAt(LocalDateTime.now());

        notesRepository.save(note);
    }

    @Override
    public void delete(long id) {
        notesRepository.deleteById(id);
    }

    @Override
    public List<Note> findAll() {
        return notesRepository.findAll();
    }

    @Override
    public List<NoteHistoryDTO> findAllHistoryByNoteId(long noteId) {
        var noteHistoryList = noteHistoryRepository.findByNoteId(noteId);

        return noteHistoryList.stream().map(NoteHistoryDTO::new).toList();
    }
    
}
