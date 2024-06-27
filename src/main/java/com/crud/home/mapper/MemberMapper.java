package com.crud.home.mapper;

import com.crud.home.Entity.Member;
import com.crud.home.domain.JoinReqDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    Member toEntity(JoinReqDto dto);
}
