package com.crud.home.Repository;

import com.crud.home.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Modifying
    @Query("UPDATE Board b SET b.hits=IFNULL(b.hits, 0) + 1 WHERE b.id = :id")
    void countBoard(@Param("id") Long id);

}
