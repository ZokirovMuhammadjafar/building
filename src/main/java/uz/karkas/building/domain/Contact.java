package uz.karkas.building.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.contact.ContactDTO;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String email;

    @Column(columnDefinition = "text")
    private String message;


    public Contact(String fullName, String phoneNumber, String email, String message) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.message = message;
    }

    public Contact() {

    }

    public ContactDTO get() {
        return ContactDTO.builder().message(this.message).id(this.getId()).email(this.email).fullName(this.fullName).phoneNumber(this.phoneNumber).build();
    }
}
