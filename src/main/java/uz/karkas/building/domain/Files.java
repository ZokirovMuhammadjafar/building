package uz.karkas.building.domain;

import org.hibernate.validator.internal.util.stereotypes.Lazy;

import javax.persistence.*;

@Entity
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Lazy
    @Column(columnDefinition = "bytea")
    private byte[] bytes;



}
