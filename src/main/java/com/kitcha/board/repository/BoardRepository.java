package com.kitcha.board.repository;

import com.kitcha.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface BoardRepository extends MongoRepository<Board, Long> {
    Page<Board> findByDeletedYnFalse(Pageable pageable);
}
