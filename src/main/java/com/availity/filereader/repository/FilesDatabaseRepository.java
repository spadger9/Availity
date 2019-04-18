package com.availity.filereader.repository;

import com.availity.filereader.model.FilesDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesDatabaseRepository extends JpaRepository<FilesDatabase, String> {
}
