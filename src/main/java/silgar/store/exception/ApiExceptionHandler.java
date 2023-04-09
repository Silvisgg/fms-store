package silgar.store.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ErrorMessage> notFound (WebRequest webRequest, Exception exception){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Controller advice");

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(),
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false),
                exception.getStackTrace().toString());

        //return ResponseEntity.notFound().headers(headers).build();
        return new ResponseEntity<ErrorMessage>(errorMessage,headers,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<ErrorMessage> badRequest (WebRequest webRequest, Exception exception){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Controller advice");

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false),
                exception.getStackTrace().toString());

        return new ResponseEntity<ErrorMessage>(errorMessage,headers,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<ErrorMessage> conflictRequest (WebRequest webRequest, Exception exception){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Controller advice");

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.CONFLICT.value(),
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false),
                exception.getStackTrace().toString());

        return new ResponseEntity<ErrorMessage>(errorMessage,headers,HttpStatus.CONFLICT);
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessage> globalException (WebRequest webRequest, Exception exception){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Controller advice");

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false),
                exception.getStackTrace().toString());

        return new ResponseEntity<ErrorMessage>(errorMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
