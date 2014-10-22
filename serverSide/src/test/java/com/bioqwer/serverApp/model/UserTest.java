package com.bioqwer.serverApp.model;

import com.bioqwer.serverApp.config.DataConfig;
import com.bioqwer.serverApp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class UserTest {

    @Autowired
    UserService userService;
    User user;

    @Before
    public void setUp() {

    }

    @Test
    public void testGetUserId() throws Exception {

    }

    @Test
    public void testSetUserId() throws Exception {

    }

    @Test
    public void testGetEmail() throws Exception {

    }

    @Test
    public void testSetEmail() throws Exception {
        user = new User();
        user.setEmail("@ewe.qwe");
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(user);

        System.out.println(String.format("Кол-во ошибок: %d",
                constraintViolations.size()));

        for (ConstraintViolation<Object> cv : constraintViolations)
            System.out.println(String.format(
                    "Внимание, ошибка! property: [%s], value: [%s], message: [%s]",
                    cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
    }

    @Test
    public void testGetLogin() throws Exception {

    }

    @Test
    public void testSetLogin() throws Exception {

    }

    @Test
    public void testGetPassword() throws Exception {

    }

    @Test
    public void testSetPassword() throws Exception {

    }

    @Test
    public void testGetNotesByUserId() throws Exception {

    }

    @Test
    public void testSetNotesByUserId() throws Exception {

    }
}