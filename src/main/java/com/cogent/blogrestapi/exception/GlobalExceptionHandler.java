package com.cogent.blogrestapi.exception;

import com.cogent.blogrestapi.payload.ErrorDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  // handle specific exceptions
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                     WebRequest webRequest){
    ErrorDetail errorDetail = new ErrorDetail(
            new Date(),
            ex.getMessage(),
            webRequest.getDescription(false)
    );
    return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
  }

  // blog api exception
  @ExceptionHandler(BlogApiException.class)
  public ResponseEntity<ErrorDetail> handleBlogApiException(BlogApiException ex,
                                                            WebRequest webRequest){
      ErrorDetail errorDetail = new ErrorDetail(
              new Date(),
              ex.getMessage(),
              webRequest.getDescription(false)
      );
      return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
  }

  // global exception
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetail> handleGlobalException(Exception ex,
                                                           WebRequest webRequest){
    ErrorDetail errorDetail = new ErrorDetail(
            new Date(),
            ex.getMessage(),
            webRequest.getDescription(false)
    );

    return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatusCode status,
                                                                WebRequest request){
    // search more on this
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) ->{
      String fieldName = ((FieldError)error).getField();
      String message = error.getDefaultMessage();
      errors.put(fieldName, message);

    });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}
