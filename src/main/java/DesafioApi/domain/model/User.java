package DesafioApi.domain.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "login",unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Car> cars;

}
