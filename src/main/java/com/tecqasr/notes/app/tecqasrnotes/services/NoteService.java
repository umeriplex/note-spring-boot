package com.tecqasr.notes.app.tecqasrnotes.services;

import com.tecqasr.notes.app.tecqasrnotes.payloads.NoteDto;

import java.util.List;

public interface NoteService {
    NoteDto create(NoteDto noteDto);

    List<NoteDto> getAllNotes();

    NoteDto update(NoteDto noteDto, long noteId);

    NoteDto getNoteById(long noteId);

    void delete(long noteId);

}
