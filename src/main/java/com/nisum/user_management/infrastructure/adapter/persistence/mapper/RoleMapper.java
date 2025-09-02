package com.nisum.user_management.infrastructure.adapter.persistence.mapper;

import com.nisum.user_management.infrastructure.adapter.persistence.entity.RoleEntity;
import com.nisum.user_management.infrastructure.adapter.security.model.enums.RoleEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", expression = "java(roleEnum)")
    RoleEntity roleEnumToRoleEntity(RoleEnum roleEnum);
}
