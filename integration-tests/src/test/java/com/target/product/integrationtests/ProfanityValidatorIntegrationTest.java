package com.target.product.integrationtests;


import com.target.product.main.Application;
import com.target.product.validator.Result;
import com.target.product.validator.Validator;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;



@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ProfanityValidatorIntegrationTest {


    @Autowired
    private Validator<String> profanityValidator;

    public Validator<String> getProfanityValidator() {
        return profanityValidator;
    }

    public void setProfanityValidator(Validator<String> profanityValidator) {
        this.profanityValidator = profanityValidator;
    }

    @Test
    public void testCreateReviewTextChecksForProfanity() {

        Result<String> result = profanityValidator.validate("shit");

        assertFalse(result.isSuccess());
        assertEquals("****", result.getOutput());
    }
}
