package com.target.product.validator;

public class Result<T> {

    public Result(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Result(boolean isSuccess, T output) {
        this.isSuccess = isSuccess;
        this.output = output;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getOutput() {
        return output;
    }

    public void setOutput(T output) {
        this.output = output;
    }

    private boolean isSuccess;
    private T output;
}
