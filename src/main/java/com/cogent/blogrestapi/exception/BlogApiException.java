package com.cogent.blogrestapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BlogApiException extends RuntimeException {
  private HttpStatus status;
  private String message;

  public BlogApiException(HttpStatus status, String message) {
    this.message = message;
    this.status = status;
  }

  public BlogApiException(String customMessage, HttpStatus status, String message) {

    // search more about this
    super(customMessage);

    this.message = message;
    this.status = status;
  }
}
