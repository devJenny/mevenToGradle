package com.crud.home.mapper;

import com.crud.home.Entity.Member;
import com.crud.home.domain.JoinReqDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(source = "nickname" , target = "nickname")
    Member toEntity(JoinReqDto dto);
}
