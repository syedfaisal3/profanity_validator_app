package com.target.product.service.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


import com.target.product.dto.ReviewDTO;
import com.target.product.service.ProductService;
import com.target.product.validator.Result;
import com.target.product.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private Validator<String> profanityValidator;

    public Result<String> createReview(@NotNull @Valid final ReviewDTO review) {
        LOGGER.debug("Creating review: {}", review);
        return profanityValidator.validate(review.getReviewText());
    }

}
