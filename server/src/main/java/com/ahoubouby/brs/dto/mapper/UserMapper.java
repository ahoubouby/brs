package com.ahoubouby.brs.dto.mapper;
/*
 * ahoubouby created on 6/30/20
 * E-MAIL: ahoubouby@gmail.com
 */

import com.ahoubouby.brs.dto.model.RoleDto;
import com.ahoubouby.brs.dto.model.UserDto;
import com.ahoubouby.brs.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public static UserDto toUserDto(User user) {
        return new UserDto()
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setMobileNumber(user.getMobileNumber())
                .setRoles(new HashSet<RoleDto>(user
                        .getRoles()
                        .stream()
                        .map(role -> new ModelMapper().map(role, RoleDto.class))
                        .collect(Collectors.toSet())));
    }
}
