package bdd.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class User {
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    @Transient
    private transient String passwordConfirm;

}
