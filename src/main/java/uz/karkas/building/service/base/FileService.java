package uz.karkas.building.service.base;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import uz.karkas.building.domain.Uploads;
import uz.karkas.building.repository.UploadsRepository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileService {


    private String PATH="/uploads/building/";
    private final Path path=Paths.get(PATH);
    private final UploadsRepository repository;

    @PostConstruct
    private void init() {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer save(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String name = System.currentTimeMillis() + "." + extension;
        Uploads uploads = new Uploads(originalFilename, name, file.getSize(), extension);
        Integer id = repository.save(uploads).getId();
        FileUtils.copyToFile(file.getInputStream(), new File(PATH.concat(name)));
        return id;

    }


    public Uploads get(Integer fileId) {
        // TODO: 10/04/2022 custom exception yozib yuboraman
        return repository.findById(fileId).orElseThrow(RuntimeException::new);

    }

    public Resource load(String filename) {
        try {
            Path root = path.resolve(filename);
            Resource resource = new UrlResource(root.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("not read");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
