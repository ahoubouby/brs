package com.ahoubouby.brs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * ahoubouby created on 6/30/20
 * E-MAIL: ahoubouby@gmail.com
 */


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "stop")
public class Stop {
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String code;

    private String name;

    private String detail;
}
