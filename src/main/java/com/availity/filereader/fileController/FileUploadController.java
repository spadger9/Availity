package com.availity.filereader.fileController;


import com.availity.filereader.model.FilesDatabase;
import com.availity.filereader.services.FilesDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.getProperty;

@Controller
public class FileUploadController {

    @Autowired
    private FilesDatabaseService filesDatabaseService;

    public static String localDirectory = getProperty("user.dir")+"/uploadedFiles";

    @RequestMapping("/")
    public String UploadView(Model model){
        return "uploadView";
    }

    @RequestMapping(value = "/uploadFilesLocal", params = "local")
    public String uploadFilesLocal(Model model, @RequestParam("Files") MultipartFile[] files){

        for(MultipartFile file: files){
            Path fileNamePath = Paths.get(localDirectory,file.getOriginalFilename());


            try {
                Files.write(fileNamePath,file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("msg", "Successfully added files to local directory.");
        return "uploadStatus";
    }

    @RequestMapping(value = "/uploadFilesLocal", params = "console")
    public String uploadFilesConsole(Model model, @RequestParam("Files") MultipartFile[] files){
        StringBuilder fileNames = new StringBuilder();
        for(MultipartFile file: files){
            fileNames.append(file.getOriginalFilename()).append(", ");
        }
        model.addAttribute("msg", "These files were selected: \n" + fileNames.toString());
        return "uploadStatus";
    }

    @RequestMapping(value = "/uploadFilesLocal", params = "database")
    public String uploadFilesDatabase(Model model, @RequestParam("Files") MultipartFile[] files){
        StringBuilder fileNames = new StringBuilder();
        for(MultipartFile file: files){
            try {
                FilesDatabase filesDatabase = filesDatabaseService.uploadFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("File Name:" + file.getOriginalFilename());

            fileNames.append(file.getOriginalFilename()).append("\n");

        }
        model.addAttribute("msg", "Successfully added files to database. ");
        return "uploadStatus";
    }
}
