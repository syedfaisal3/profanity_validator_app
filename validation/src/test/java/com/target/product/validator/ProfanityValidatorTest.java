package com.target.product.validator;

import com.target.product.validator.impl.ProfanityValidator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;


/**
 * Created by kunal on 21/04/17.
 */
public class ProfanityValidatorTest {

    @Test
    public void testStringValidationForNonNullString(){
        Validator<String> validator = new ProfanityValidator();
        Object result = validator.validate("bad word");
        Assert.assertTrue(Objects.nonNull(result));
    }
}
