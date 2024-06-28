package com.crud.home.mapper;

import com.crud.home.Entity.Board;
import com.crud.home.domain.BoardReqDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    Board toEntity(BoardReqDto dto);

}
