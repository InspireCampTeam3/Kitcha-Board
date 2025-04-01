package com.kitcha.board.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kitcha.board.dto.BoardDetail;
import com.kitcha.board.dto.BoardList;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Document(collection = "board")
@Data
@NoArgsConstructor
public class Board {

    @Id
    private Long boardId; // MongoDB용 자동 증가는 별도 로직 필요

    private String nickname;
    private String boardTitle;
    private String content;
    private int hitCnt = 0;
    private String newsTitle;
    private String longSummary;
    private String newsUrl;
    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean deletedYn = false;
    private Long userId;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "file_id") // file 테이블의 file_id를 FK로 참조
//    private File file;

    public Board(Long boardId, String nickname, String boardTitle, String content, String newsTitle, String longSummary, String newsUrl, Long userId) {
        this.boardId = boardId;
        this.nickname = nickname;
        this.boardTitle = boardTitle;
        this.content = content;
        this.newsTitle = newsTitle;
        this.longSummary = longSummary;
        this.newsUrl = newsUrl;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }

    public void updateHitCnt() {
        hitCnt++;
    }

    public BoardDetail toDetail(boolean isOwner, boolean isAdmin) {
        return new BoardDetail(boardId, boardTitle, hitCnt, nickname, createdAt.toString(), content, longSummary, newsUrl, isOwner, isAdmin);
    }

    public BoardList toList() {
        return new BoardList(boardId, boardTitle, hitCnt, nickname, createdAt.toString());
    }

    public void update(String boardTitle, String content) {
        this.boardTitle = boardTitle;
        this.content = content;
    }
}
