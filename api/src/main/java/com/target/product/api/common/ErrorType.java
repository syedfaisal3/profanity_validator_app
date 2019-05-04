package com.target.product.api.common;


public class ErrorType {
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  private String message;
  private String code;

  public ErrorType() {

  }

  public ErrorType(String message, String code) {
    this.message = message;
    this.code = code;
  }
}
