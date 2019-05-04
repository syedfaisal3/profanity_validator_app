package com.target.product.api.response;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public class ValidationResponse<T> extends BaseApiResponse {

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<String> warnings = new ArrayList<>();

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<T> result;

  public void addWarning(String warning){
    this.warnings.add(warning);
  }

  public List<String> getWarnings() {
    return warnings;
  }

  public void setWarnings(List<String> warnings) {
    this.warnings = warnings;
  }

  public List<T> getResult() {
    return result;
  }

  public void setResult(List<T> result) {
    this.result = result;
  }
}
