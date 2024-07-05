package com.crud.home.mapper;

import com.crud.home.Entity.Comments;
import com.crud.home.domain.CommentReqDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentsMapper {
    CommentsMapper INSTANCE = Mappers.getMapper(CommentsMapper.class);

    Comments toEntity(CommentReqDto dto);
}
