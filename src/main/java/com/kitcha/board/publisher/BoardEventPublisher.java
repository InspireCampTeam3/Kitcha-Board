package com.kitcha.board.publisher;

import com.kitcha.board.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardEventPublisher {
    private final KafkaTemplate<String, Map<String, Object>> kafkaTemplate;
    // Create 이벤트 발행
    public void sendBoardCreateEvent(Board board) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("eventType", "CREATE");
        payload.put("boardId", board.getBoardId());
        payload.put("nickname", board.getNickname());
        payload.put("boardTitle", board.getBoardTitle());
        payload.put("content", board.getContent());
        payload.put("newsTitle", board.getNewsTitle());
        payload.put("longSummary", board.getLongSummary());
        payload.put("newsUrl", board.getNewsUrl());
        payload.put("userId", board.getUserId());
        payload.put("createdAt", board.getCreatedAt());
        kafkaTemplate.send("board_topic", payload);
    }

    // Update 이벤트 발행
    public void sendBoardUpdateEvent(Board board) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("eventType", "UPDATE");
        payload.put("boardId", board.getBoardId());
        payload.put("boardTitle", board.getBoardTitle());
        payload.put("content", board.getContent());
        kafkaTemplate.send("board_topic", payload);
    }

    // Delete 이벤트 발행
    public void sendBoardDeleteEvent(Board board) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("eventType", "DELETE");
        payload.put("boardId", board.getBoardId());
        kafkaTemplate.send("board_topic", payload);
    }
}
