package com.tecqasr.notes.app.tecqasrnotes.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String updateImage(String path, MultipartFile file) throws IOException;
    InputStream getResource(String path, String fileName) throws FileNotFoundException ;
}
