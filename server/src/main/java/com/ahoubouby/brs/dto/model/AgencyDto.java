package com.ahoubouby.brs.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;
/*
 * ahoubouby created on 6/30/20
 * E-MAIL: ahoubouby@gmail.com
 */

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyDto {
    private String code;

    private UserDto owner;

    private Set<BusDto> buses;

    private String name;

    private String details;
}
