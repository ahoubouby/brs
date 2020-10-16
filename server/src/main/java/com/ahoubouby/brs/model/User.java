package com.ahoubouby.brs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Set;

/*
 * ahoubouby created on 6/26/20
 * E-MAIL: ahoubouby@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@Document("user")
public class User {
    @Id
    private String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    @DBRef
    private Set<Role> roles;

    public String getFullName() {
        return firstName != null ? firstName.concat(" ").concat(lastName) : "";
    }

}
