package com.mycompany.springsecurityinactionbook.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(Authorities.AuthoritiesID.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authorities {

    @Id
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Users username;

    @Id
    @Builder.Default
    private String authority = "USER";

    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuthoritiesID implements Serializable {

        private String username;
        private String authority;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AuthoritiesID that = (AuthoritiesID) o;
            return Objects.equals(username, that.username) && Objects.equals(authority, that.authority);
        }

        @Override
        public int hashCode() {
            return Objects.hash(username, authority);
        }
    }
}


