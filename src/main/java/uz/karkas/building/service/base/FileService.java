package uz.karkas.building.service.base;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import uz.karkas.building.domain.Uploads;
import uz.karkas.building.exception.NotFoundException;
import uz.karkas.building.repository.UploadsRepository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService implements BaseGenericService {


    private String PATH="/uploads/";
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
        Integer integer=repository.savefile(file.getBytes());
        Uploads uploads = new Uploads(originalFilename, name, file.getSize(),extension);
        uploads.setFileId(integer);
        Integer id = repository.save(uploads).getId();

//        FileUtils.copyToFile(file.getInputStream(), new File(PATH.concat(name)));
        return id;

    }




    public Uploads get(Integer fileId) {
        Optional<Uploads> find = repository.findById(fileId);
        if(find.isPresent()){
            return find.get();
        }else {
            throw new NotFoundException("file not found");
        }


    }
    public Uploads get(String fileId) {
        Optional<Uploads> find = repository.findByPathName(fileId);
        if(find.isPresent()){
            return find.get();
        }else {
            throw new NotFoundException("file not found");
        }


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

    public Resource loads(Integer id){
            byte[] bytes=repository.take(id);
//            ByteArrayResource arrayResource = new ByteArrayResource(bytes);
//            File file=new File()
            InputStreamResource resource=new InputStreamResource(new ByteArrayInputStream(bytes));


//            FileSystemResource resource=new FileSystemResource()
            return resource;

    }

}
