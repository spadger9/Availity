package com.availity.filereader.model;


import javax.persistence.*;

@Entity

public class FilesDatabase {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    private String id;
    private String fileName;

    @Lob
    private byte[] data;

    public FilesDatabase(String fileName, byte[] data) {
        this.fileName = fileName;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
