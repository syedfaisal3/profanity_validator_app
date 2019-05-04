package com.target.product.validator;

public interface Validator<T> {

    Result<T> validate(T t);
}
