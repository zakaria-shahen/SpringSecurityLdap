package com.mycompany.springsecurityinactionbook.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    private String username;
    private String password;
    private Boolean enabled;

    @OneToMany(mappedBy = "authority")
    private List<Authorities> authorities;

}
