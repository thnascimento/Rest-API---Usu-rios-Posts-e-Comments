package meuprojeto.meuprojeto.controllers.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomDefaultException extends RuntimeException {

    private Integer code;
    private String detail;
    private String message;
    private Throwable throwable;
    private HttpStatus httpStatus;

    public CustomDefaultException(String message) {
        this.message = message;
    }

    public CustomDefaultException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomDefaultException(Integer code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public CustomDefaultException(Integer code, String detail, String message, Throwable throwable, HttpStatus httpStatus) {
        this.code = code;
        this.detail = detail;
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : this.httpStatus;
    }
}