package com.availity.filereader.services;

import com.availity.filereader.model.FilesDatabase;
import com.availity.filereader.repository.FilesDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesDatabaseService {


    @Autowired
    private FilesDatabaseRepository fdbRepository;

    public FilesDatabase uploadFile(MultipartFile multipartFile) throws Exception {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        try {
            FilesDatabase filesDatabase = new FilesDatabase(fileName, multipartFile.getBytes());
             return fdbRepository.save(filesDatabase);
        } catch (Exception e) {
            throw new Exception("Files cannot be uploaded. " + e);
        }
    }
}
