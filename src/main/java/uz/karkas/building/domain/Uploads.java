package uz.karkas.building.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public Uploads(String originalName, String pathName, Long size, String extension) {
        this.originalName = originalName;
        this.pathName = pathName;
        this.size = size;
        this.extension = extension;
    }

    public Uploads() {
    }
}
