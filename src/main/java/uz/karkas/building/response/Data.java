package uz.karkas.building.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class Data<D> implements Serializable {
    private D body;
    private ApiErrorDto apiError;
    private boolean success;
    private Integer status;

    public Data(D body) {
        this.status=200;
        this.success=true;
        this.body = body;
    }
    public Data(D body, HttpStatus status) {
        this.status=status.value();
        this.success=true;
        this.body = body;
    }

    public Data(ApiErrorDto apiError) {
        this.apiError = apiError;
        this.status = apiError.getStatus();
        this.success=false;
    }
}
