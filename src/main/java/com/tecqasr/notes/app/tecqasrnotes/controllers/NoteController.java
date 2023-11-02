package com.tecqasr.notes.app.tecqasrnotes.controllers;

import com.tecqasr.notes.app.tecqasrnotes.payloads.NoteDto;
import com.tecqasr.notes.app.tecqasrnotes.services.NoteService;
import com.tecqasr.notes.app.tecqasrnotes.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/create")
    public ResponseEntity<NoteDto> create(@RequestBody  NoteDto noteDto) {
        noteDto = noteService.create(noteDto);
        return new ResponseEntity<>(noteDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<NoteDto>> getAll(){
        List<NoteDto> notesList = noteService.getAllNotes();
        return new ResponseEntity<>(notesList, HttpStatus.OK);
    }


    @PostMapping("/update/{noteId}")
    public ResponseEntity<NoteDto> update(@RequestBody NoteDto noteDto, @PathVariable long noteId){
        noteDto = noteService.update(noteDto, noteId);
        return new ResponseEntity<>(noteDto, HttpStatus.OK);
    }

    @GetMapping("/delete/{noteId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable long noteId){
        noteService.delete(noteId);
        return new ResponseEntity<>(new ApiResponse("Note Deleted!",true),HttpStatus.OK);
    }

}
