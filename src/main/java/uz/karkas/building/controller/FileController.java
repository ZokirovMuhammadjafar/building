package uz.karkas.building.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.karkas.building.domain.Uploads;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.FileService;

import java.io.IOException;

@CrossOrigin(value = "*")
@RestController
public class FileController extends BaseController<FileService> {

    public FileController(FileService service) {
        super(service);
    }

    @CrossOrigin(value = "*")
    @PostMapping(value = PATH + "/upload")
    @Secured("ADMIN")
    public ResponseEntity<Data<Integer>> photo(@RequestParam("file") MultipartFile file) {
        try {
            Integer save = service.save(file);
            return new ResponseEntity<>(new Data<>(save), HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException("file not uploaded");
        }
    }


    @GetMapping(PATH + "/download/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Uploads uploads = service.get(filename);
        Resource file = service.loads(uploads.getFileId());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" + uploads.getOriginalName() + "\"").body(file);

    }

}
