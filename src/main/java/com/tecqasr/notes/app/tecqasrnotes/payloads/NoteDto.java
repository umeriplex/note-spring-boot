package com.tecqasr.notes.app.tecqasrnotes.payloads;
import com.tecqasr.notes.app.tecqasrnotes.entities.User;
import lombok.Data;
import java.util.Date;

@Data
public class NoteDto {

    private long id;

    private long userId;
    private String title;
    private String content;
    private String color;
    private Date date;

    private UserDto user;

}
