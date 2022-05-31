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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.karkas.building.domain.Uploads;
import uz.karkas.building.exception.NotFoundException;
import uz.karkas.building.repository.UploadsRepository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService implements BaseGenericService {
    private static String extensions="{jpg};{png};{PNG};{JPG}";

    private final UploadsRepository repository;



    public Integer save(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        if(Objects.nonNull(extension)&&!extensions.contains(extension))throw new RuntimeException("image format not comfortable");
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



    public Resource loads(Integer id){
            byte[] bytes=repository.take(id);
             InputStreamResource resource=new InputStreamResource(new ByteArrayInputStream(bytes));


//            FileSystemResource resource=new FileSystemResource()
            return resource;

    }

}
