package com.projects.urlshortener.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
  private int status;
  private String message;
  private Object data;

  public static ApiResponse of(Object data) {
    return new ApiResponse(200, "", data);
  }

  public static ApiResponse of(int status, String message) {
    return new ApiResponse(status, message, null);
  }

  public static ApiResponse of(int status, String message, Object data) {
    return new ApiResponse(status, message, data);
  }
}
