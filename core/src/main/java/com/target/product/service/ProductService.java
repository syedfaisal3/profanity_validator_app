package com.target.product.service;

import com.target.product.dto.ReviewDTO;
import com.target.product.validator.Result;

public interface ProductService {

    Result<String> createReview(ReviewDTO employee);

}
