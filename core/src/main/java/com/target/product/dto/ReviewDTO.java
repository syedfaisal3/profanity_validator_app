package com.target.product.dto;


public class ReviewDTO {

    private int rating;

    private String reviewText;

    public int getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public static class ReviewDTOBuilder {

        private ReviewDTO reviewDTO;
        public ReviewDTOBuilder() {
            this.reviewDTO = new ReviewDTO();
        }


        public ReviewDTOBuilder reviewText(String arg) {
            reviewDTO.reviewText = arg;
            return this;
        }

        public ReviewDTOBuilder rating(int arg) {
            reviewDTO.rating = arg;
            return this;
        }
        public ReviewDTO build() {
            return reviewDTO;
        }
    }
}

