package com.kitcha.board.entity;

// import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// @Entity
// @Table(name = "user")
@Document(collection = "user")
@Data
public class User {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    // @Column(nullable = false)
    private String nickname;

    // @Column(nullable = false)
    private String email;

    // @Column(nullable = false)
    private String password;

    // @Column(nullable = false)
    private String role;

    private String interest;
}