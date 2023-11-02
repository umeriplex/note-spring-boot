package com.tecqasr.notes.app.tecqasrnotes.services.service_impl;

import com.tecqasr.notes.app.tecqasrnotes.entities.Note;
import com.tecqasr.notes.app.tecqasrnotes.entities.User;
import com.tecqasr.notes.app.tecqasrnotes.exceptions.ResourceNotFoundException;
import com.tecqasr.notes.app.tecqasrnotes.payloads.NoteDto;
import com.tecqasr.notes.app.tecqasrnotes.repositories.NoteRepository;
import com.tecqasr.notes.app.tecqasrnotes.repositories.UserRepository;
import com.tecqasr.notes.app.tecqasrnotes.services.NoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public NoteDto create(NoteDto noteDto) {
        NoteDto finalNoteDto = noteDto;
        User user = userRepository.findById(noteDto.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User","id", finalNoteDto.getUserId()));
        Note note = new Note();
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        note.setColor(noteDto.getColor());
        note.setDate(new Date());
        note.setUser(user);
        note = noteRepository.save(note);
        noteDto = modelMapper.map(note, NoteDto.class);
        return noteDto;
    }

    @Override
    public List<NoteDto> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return notes.stream().map(eachNote -> modelMapper.map(eachNote, NoteDto.class)).collect(Collectors.toList());
    }

    @Override
    public NoteDto update(NoteDto noteDto, long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(()-> new ResourceNotFoundException("Note","id", noteId));
        if(!noteDto.getTitle().isEmpty()){
            note.setTitle(noteDto.getTitle());
        }
        if(!noteDto.getContent().isEmpty()){
            note.setContent(noteDto.getContent());
        }
        if(!noteDto.getColor().isEmpty()){
            note.setColor(noteDto.getColor());
        }
        note.setDate(new Date());

        note = noteRepository.save(note);
        return modelMapper.map(note, NoteDto.class);
    }

    @Override
    public NoteDto getNoteById(long noteId) {
        return null;
    }

    @Override
    public void delete(long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(()-> new ResourceNotFoundException("Note","id", noteId));
        noteRepository.delete(note);
    }
}
