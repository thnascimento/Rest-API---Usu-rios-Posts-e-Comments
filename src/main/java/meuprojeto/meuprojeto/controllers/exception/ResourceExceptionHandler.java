package meuprojeto.meuprojeto.controllers.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ResourceExceptionHandler {

    @ExceptionHandler(CustomDefaultException.class)
    public ResponseEntity<StandardError> objectNotFound(CustomDefaultException exception, HttpServletRequest request) {
        if (exception.getThrowable() != null) log.error(exception.getThrowable().toString());
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(StandardError.builder()
                        .code(exception.getCode())
                        .message(exception.getMessage())
                        .detail(exception.getDetail())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
        log.error(exception.toString());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(StandardError.builder()
                        .code(32)
                        .message("Os dados devem ser enviados de acordo com as regras, por favor tente novamente")
                        .detail(exception.getMessage() == null ? exception.toString() : exception.getMessage())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> exception(Exception exception, HttpServletRequest request) {
        log.error(exception.toString());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(StandardError.builder()
                        .code(null)
                        .message("Ocorreu um erro, por favor tente novamente")
                        .detail(exception.getMessage() == null ? exception.toString() : exception.getMessage())
                        .build());
    }
}