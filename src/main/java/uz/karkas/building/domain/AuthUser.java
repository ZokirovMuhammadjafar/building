package uz.karkas.building.domain;

import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.enums.Role;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String username;
    private String password;
    @Column(columnDefinition = "boolean default true")
    private boolean block;
    @Column(columnDefinition = "boolean default false")
    private boolean active;
    @Enumerated(EnumType.STRING)
    private Role role;


}
