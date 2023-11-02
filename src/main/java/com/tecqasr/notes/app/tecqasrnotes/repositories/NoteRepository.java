package com.tecqasr.notes.app.tecqasrnotes.repositories;

import com.tecqasr.notes.app.tecqasrnotes.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
