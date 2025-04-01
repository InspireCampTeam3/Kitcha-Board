package com.kitcha.board.repository;

import com.kitcha.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends MongoRepository<Board, Long> {
    Page<Board> findByDeletedYnFalse(Pageable pageable);
    Optional<Board> findByBoardId(Long boardId);
}
