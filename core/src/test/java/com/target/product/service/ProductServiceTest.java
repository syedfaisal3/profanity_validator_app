package com.target.product.service;

import com.target.product.dto.ReviewDTO;
import com.target.product.service.impl.ProductServiceImpl;
import com.target.product.validator.Validator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ProductServiceTest {

    @Mock
    private Validator<String> profanityValidator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private ProductServiceImpl productService;

    // *********** tests for SAVED_FLOW_CREATE_MSG_STR call
    @Test
    public void testCreateReviewTextChecksForProfanity() {
        String reviewText = "great product";
        ReviewDTO dto = new ReviewDTO.ReviewDTOBuilder().reviewText("great product").rating(5).build();
        productService.createReview(dto);
        verify(profanityValidator).validate(reviewText);
    }
}


