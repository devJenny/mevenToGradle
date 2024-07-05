package com.crud.home.Repository;

import com.crud.home.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findByBoardId(Long id);

    @Transactional
    void deleteByBoardId(@Param("boardId") Long boardId);

}
