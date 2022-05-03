package uz.karkas.building.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data<D> implements Serializable {
    private D body;
    private ApiErrorDto apiError;
    private boolean success;


    public Data(D body) {
        this.success=true;
        this.body = body;
    }
    public Data(D body, HttpStatus status) {
        this.success=true;
        this.body = body;
    }

    public Data(ApiErrorDto apiError) {
        this.apiError = apiError;
        this.success=false;
    }
}
