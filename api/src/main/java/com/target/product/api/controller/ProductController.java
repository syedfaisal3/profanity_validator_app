package com.target.product.api.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import com.target.product.api.common.Errors;
import com.target.product.api.request.Review;
import com.target.product.api.response.ValidationResponse;
import com.target.product.dto.ReviewDTO;
import com.target.product.service.ProductService;
import com.target.product.validator.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(value = "/product/v1/", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProductController extends BaseController {
    private final ProductService productService;

    @Inject
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    // Assuming that user has access to create review on this product
    @RequestMapping(value="/{id}/review.json",method = RequestMethod.POST)
    public ValidationResponse<String> createReview(@PathVariable("id") int reviewId, @Valid @RequestBody Review review) {
        // TODO: add validation for rating, rating or no. of stars must be >= 1
        ReviewDTO dto = new ReviewDTO.ReviewDTOBuilder().reviewText(review.getReviewText())
                .rating(review.getRating()).build();
        Result<String> result = productService.createReview(dto);
        ValidationResponse<String> validationResponse = new ValidationResponse<>();
        if(!result.isSuccess()) {
            validationResponse.addError(Errors.TEXT_CONTAINS_PROFANITY.getMessage(), String.valueOf(Errors.TEXT_CONTAINS_PROFANITY.getId()));
            validationResponse.addError(result.getOutput(), String.valueOf(Errors.ACTUAL_PROFANITY_TEXT.getId()));
            return validationResponse;
        }
        validationResponse.setResult(Collections.singletonList("createReviewSuccess"));
        return validationResponse;

    }

}
