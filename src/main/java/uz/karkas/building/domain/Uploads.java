package uz.karkas.building.domain;


import lombok.Getter;

import lombok.Setter;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import javax.persistence.*;

@Getter
@Setter
//@Builder
@Entity
public class Uploads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String originalName;

    private String pathName;

    private Long size;

    private String extension;

    @Column(nullable = false)
    private Integer fileId;





    public Uploads(String originalName, String pathName, Long size, String extension) {
        this.originalName = originalName;
        this.pathName = pathName;
        this.size = size;
        this.extension = extension;
    }

    public Uploads() {
    }
}
