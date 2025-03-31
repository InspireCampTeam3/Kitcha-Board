package com.kitcha.board.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
// MongoDB에서 시퀀스를 관리하기 위한 엔티티 클래스
public class DatabaseSequence {
    @Id
    private String id;
    private long seq;

    public DatabaseSequence() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }
}
