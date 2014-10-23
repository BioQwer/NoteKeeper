package com.bioqwer.serverApp.skillsCache;

import com.bioqwer.serverApp.model.User;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionsValidatorTest {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String EMAIL_PATTERN_HABR ="^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    public static final String PASSWORD_PATTERN = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

    public boolean validateEmail(String hex) {
        pattern = Pattern.compile(EMAIL_PATTERN_HABR);
        matcher = pattern.matcher(hex);

        return matcher.matches();
    }

    private boolean validatePassword(String temp) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(temp);

        return matcher.matches();
    }

    String[] emailsValid = new String[] {
            "aleSx@yandex.ru",
            "alex-27@yandex.com",
            "aleSx.27@yandex.com",
            "alSex111@devcolibri.com",
            "alex.100@devcolibri.com.ua",
            "alex@1a.com",
            "alex@gmail.com.com",
            "aleSx+27@gmail.com",
            "alex-27@yandex-test.com"
    };

    String[] notValid = new String[] {
            "devcolibri",
            "alex@.com.ua",
            "alex123@gmail.a",
            "alex123@.com",
            "alex123@.com.com",
            ".alex@devcolibri.com",
            "alSex()*@gmail.com",
            "alSex@%*.com",
            "alSex..2013@gmail.com",
            "alSex.@gmail.com",
            "alex@devcolibri@gmail.com",
            "alex@gmail.com.1ua"
    };

    String[] passwordsValid = new String[] {
            "qwekSjlfdsa78632",
            "pasDsword123",
            "Qws!er123",
            "Q1q!@$%#!@$%!@"
    };

    String[] passwordsNotValid = new String[] {
            "123",
            "asd",
            "q1w"
    };

    @Test
    public void testEmails(){
        for (String temp : emailsValid) {
            boolean valid = validateEmail(temp);
            System.out.println("Email: " + temp + " -> " + valid);
            Assert.assertEquals(valid, true);
        }
        for (String temp : notValid) {
            boolean valid = validateEmail(temp);
            System.out.println("Email: " + temp + " -> " + valid);
            Assert.assertEquals(valid, false);
        }
    }

    @Test
    public void testPasswords(){
        for (String temp : passwordsValid) {
            boolean valid = validatePassword(temp);
            System.out.println("Password: " + temp + " -> " + valid);
            Assert.assertEquals(valid, true);
        }
        for (String temp : passwordsNotValid) {
            boolean valid = validatePassword(temp);
            System.out.println("Password: " + temp + " -> " + valid);
            Assert.assertEquals(valid, false);
        }
    }



}