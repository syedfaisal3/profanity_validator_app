package com.target.product.api.common;

public enum Errors {

  TEXT_CONTAINS_PROFANITY(1001, "textContainsProfanity"),
  ACTUAL_PROFANITY_TEXT(4001, "textContainsProfanity");


  private final int id;
  private final String message;

  Errors(int id, String message) {
    this.id = id;
    this.message = message;
  }

  public int getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }
}
