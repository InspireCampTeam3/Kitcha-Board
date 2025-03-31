package com.kitcha.board.publisher;

import com.kitcha.board.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileEventPublisher {
     private final KafkaTemplate<String, Map> kafkaTemplate;

    public void sendFileCreateEvent(Board board) {
        Map<String, Object> payload = Map.of(
                "boardId", board.getBoardId().longValue(),
                "newsTitle", board.getNewsTitle(),
                "longSummary", board.getLongSummary()
        );
        kafkaTemplate.send("create-pdf", payload);
    }
}
