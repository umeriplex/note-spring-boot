package com.tecqasr.notes.app.tecqasrnotes.payloads;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class UserDto {
    private long id;

    private String email;
    private String password;
    private String name;
    private String image;

}
