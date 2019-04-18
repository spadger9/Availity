package com.availity.filereader;

import com.availity.filereader.fileController.FileUploadController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class FilesForEveryoneApplication {

    public static void main(String[] args) {

        new File(FileUploadController.localDirectory).mkdir();

        SpringApplication.run(FilesForEveryoneApplication.class, args);
    }

}
