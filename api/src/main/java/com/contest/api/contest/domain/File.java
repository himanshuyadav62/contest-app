package com.contest.api.contest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class File {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String fileId; 

    private String fileName;

    private String fileType;

    private byte[] data;

}
