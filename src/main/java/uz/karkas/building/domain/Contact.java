package uz.karkas.building.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Contact {

    @Id
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String email;

    @Column(columnDefinition = "text")
    private String message;


}
