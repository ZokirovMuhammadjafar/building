package uz.karkas.building.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNullApi;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.util.List;
import uz.karkas.building.controller.BaseController;
import uz.karkas.building.dto.auth.AuthShowDto;
import uz.karkas.building.dto.auth.AuthUserDto;
import uz.karkas.building.dto.auth.SessionDto;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.AuthUserService;
@CrossOrigin(value = "*")
@RestController
public class AuthController extends BaseController<AuthUserService> {

    public AuthController(AuthUserService service) {
        super(service);
    }


    @RequestMapping(method = RequestMethod.POST,value = PATH+"/auth/login")
    public ResponseEntity<Data<SessionDto>>login(@RequestBody AuthUserDto dto, WebRequest request){
        return service.login(dto,request);
    }


    @RequestMapping(method = RequestMethod.POST,value = PATH+"/auth/create")

    public ResponseEntity<Data<Integer>>create(@RequestBody AuthUserDto dto){
        return service.create(dto);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = PATH+"/auth/delete/{id}")
    public ResponseEntity<Void>  update (@PathVariable Integer id){
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = PATH+"/auth/getAll", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthShowDto>>> getAll(){
        return service.getAll();
    }

}
