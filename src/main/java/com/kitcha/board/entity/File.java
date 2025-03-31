package com.kitcha.board.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
// import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
// @Entity
// @Table(name = "file")
@Document(collection = "file")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    private Long fileId;

    // @Column(nullable = false)
    private String fileName;

    // @Column(nullable = false)
    private String filePath;
}
