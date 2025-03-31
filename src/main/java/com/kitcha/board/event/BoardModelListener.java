package com.kitcha.board.event;

import com.kitcha.board.entity.Board;
import com.kitcha.board.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Lazy;

@Component
public class BoardModelListener implements BeforeConvertCallback<Board> {

    private final SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public BoardModelListener(@Lazy SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public Board onBeforeConvert(Board board, String collection) {
        if (board.getBoardId() == null) {
            board.setBoardId(sequenceGeneratorService.generateSequence("board_sequence"));
        }
        return board;
    }
}
