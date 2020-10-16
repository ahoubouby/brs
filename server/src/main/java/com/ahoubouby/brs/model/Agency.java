package com.ahoubouby.brs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

import java.util.Set;

/*
 * ahoubouby created on 6/30/20
 * E-MAIL: ahoubouby@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "agency")
public class Agency {
    @Id
    private String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String code;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String name;
    private String details;
    @DBRef(lazy = true)
    private User owner;
    @DBRef(lazy = true)
    private Set<Bus> buses;


}
