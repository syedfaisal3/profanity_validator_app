package com.target.product.api.request;

import org.hibernate.validator.constraints.NotEmpty;

public class Review {

    public int getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    private int rating;

    @NotEmpty(message="reviewTextCannotBeEmpty")
    private String reviewText;
}
