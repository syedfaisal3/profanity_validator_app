package com.target.product.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.target.product.api.common.ErrorType;

import java.util.ArrayList;
import java.util.List;

public class BaseApiResponse {
  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public List<ErrorType> getErrors() {
    return errors;
  }

  public void setErrors(List<ErrorType> errors) {
    this.errors = errors;
  }

  protected Boolean success = Boolean.TRUE;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  protected List<ErrorType> errors = new ArrayList<>();

  public void addError(String message, String code) {
    this.success = false;
    this.errors.add(new ErrorType(message, code));
  }
}