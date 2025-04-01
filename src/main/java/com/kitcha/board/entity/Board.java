package com.kitcha.board.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kitcha.board.dto.BoardDetail;
import com.kitcha.board.dto.BoardList;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Document(collection = "board")
@Data
@NoArgsConstructor
public class Board {

    @Id
    private ObjectId id; // MongoDB용 기본 키
    private Long boardId;
    private String nickname;
    private String boardTitle;
    private String content;
    private int hitCnt = 0;
    private String newsTitle;
    private String longSummary;
    private String newsUrl;
    private List<Long> createdAt = toListFromLocalDateTime(LocalDateTime.now());;
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
        this.deletedYn = false;
        this.createdAt = toListFromLocalDateTime(LocalDateTime.now());
    }

    public void updateHitCnt() {
        hitCnt++;
    }

    public BoardDetail toDetail(boolean isOwner, boolean isAdmin) {
        return new BoardDetail(boardId, boardTitle, hitCnt, nickname, createdAt.toString(), content, longSummary, newsUrl, isOwner, isAdmin);
    }

    public BoardList toList() {
        return new BoardList(boardId, boardTitle, hitCnt, nickname, fromListToLocalDateTime(createdAt).toString());
    }

    public void update(String boardTitle, String content) {
        this.boardTitle = boardTitle;
        this.content = content;
    }

    private List<Long> toListFromLocalDateTime(LocalDateTime time) {
        return List.of(
                (long) time.getYear(),
                (long) time.getMonthValue(),
                (long) time.getDayOfMonth(),
                (long) time.getHour(),
                (long) time.getMinute(),
                (long) time.getSecond(),
                (long) time.getNano()
        );
    }

    private LocalDateTime fromListToLocalDateTime(List<Long> list) {
        return LocalDateTime.of(
                list.get(0).intValue(), // year
                list.get(1).intValue(), // month
                list.get(2).intValue(), // day
                list.get(3).intValue(), // hour
                list.get(4).intValue(), // minute
                list.get(5).intValue(), // second
                list.get(6).intValue()  // nano
        );
    }
}
