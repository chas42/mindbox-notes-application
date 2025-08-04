package com.mindbox.notes.controller;

import com.mindbox.notes.dto.NoteHistoryDTO;
import com.mindbox.notes.dto.NoteUpdateDTO;
import com.mindbox.notes.infra.NoteUpdateLimiter;
import com.mindbox.notes.model.Note;
import com.mindbox.notes.model.NoteHistory;
import com.mindbox.notes.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@Slf4j
public class NoteController {

    private final NoteService noteService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NoteUpdateLimiter noteUpdateLimiter;

    @Autowired
    public NoteController(NoteService noteService,SimpMessagingTemplate simpMessagingTemplate,
            NoteUpdateLimiter noteUpdateLimiter) {
        this.noteService = noteService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.noteUpdateLimiter = noteUpdateLimiter;
    }

    @GetMapping
    public ResponseEntity<List<Note>> findAll() {

        List<Note>  notes = noteService.findAll();

        return ResponseEntity.ok(notes);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Note note) {

        noteService.create(note);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getById(@PathVariable long id) {

        Note note = noteService.getById(id);

        return ResponseEntity.ok(note);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody NoteUpdateDTO noteUpdateDTO) {

        noteService.update(noteUpdateDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {

        noteService.delete(id);

        return ResponseEntity.ok().build();
    }

    @MessageMapping("/note/update/{id}")
    public void handleUpdate(@DestinationVariable long id, @Payload NoteUpdateDTO noteUpdateDTO) {

        if(!noteUpdateLimiter.canUpdate(id)){
            log.error("Can't update note id :{}", id);
            return;
        }

        noteService.update(noteUpdateDTO);

        simpMessagingTemplate.convertAndSend("/topic/note/update/" + id, noteUpdateDTO);

    }

    @GetMapping("{id}/history")
    public ResponseEntity<List<NoteHistoryDTO>> getHistory(@PathVariable long id) {

        var noteHistoryList = noteService.findAllHistoryByNoteId(id);

        return ResponseEntity.ok(noteHistoryList);
    }

}
