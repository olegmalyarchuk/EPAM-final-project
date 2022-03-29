package validator;

import com.example.conference.validator.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void checkEmail() {
        boolean result = Validator.isValidEmail("admin@gmail.com");
        Assert.assertEquals(result, true);
        result = Validator.isValidEmail("admin@gmail.com");
        Assert.assertEquals(result, true);
        result = Validator.isValidEmail("admin1234@rambler.ua");
        Assert.assertEquals(result, true);
        result = Validator.isValidEmail("admin.google.com");
        Assert.assertEquals(result, false);
        result = Validator.isValidEmail("admin@mail.com");
        Assert.assertEquals(result, true);
        result = Validator.isValidEmail("@.a");
        Assert.assertEquals(result, false);
    }

    @Test
    public void checkPass() {
        boolean result = Validator.isValidEmail("1");
        Assert.assertEquals(result, false);
        result = Validator.isValidPassword("12345678");
        Assert.assertEquals(result, true);
        result = Validator.isValidPassword("123456789101112131415");
        Assert.assertEquals(result, false);
        result = Validator.isValidPassword("Y");
        Assert.assertEquals(result, false);
        result = Validator.isValidPassword("");
        Assert.assertEquals(result, false);
        result = Validator.isValidPassword("  ");
        Assert.assertEquals(result, false);
        result = Validator.isValidPassword("12 912");
        Assert.assertEquals(result, false);
        result = Validator.isValidPassword("admin");
        Assert.assertEquals(result, true);
    }

    @Test
    public void checkPhone() {
        boolean result = Validator.isValidPhone("1");
        Assert.assertEquals(result, false);
        result = Validator.isValidPhone("+1234");
        Assert.assertEquals(result, false);
        result = Validator.isValidPhone("+123456789");
        Assert.assertEquals(result, true);
        result = Validator.isValidPhone("+777777777");
        Assert.assertEquals(result, true);
    }

    @Test
    public void checkId() {
        boolean result = Validator.isValidId("1");
        Assert.assertEquals(result, true);
        result = Validator.isValidId("-1");
        Assert.assertEquals(result, false);
        result = Validator.isValidId("01");
        Assert.assertEquals(result, false);
        result = Validator.isValidId("abc");
        Assert.assertEquals(result, false);
        result = Validator.isValidId("");
        Assert.assertEquals(result, false);
    }

    @Test
    public void checkName() {
        boolean result = Validator.isValidName("User");
        Assert.assertEquals(result, true);
        result = Validator.isValidName(" User");
        Assert.assertEquals(result, false);
        result = Validator.isValidName("");
        Assert.assertEquals(result, false);
        result = Validator.isValidName("   ");
        Assert.assertEquals(result, false);
    }

    @Test
    public void checkText() {
        boolean result = Validator.isValidText("Lorem ipsum");
        Assert.assertEquals(result, true);
    }

    @Test
    public void checkDate() {
        boolean result = Validator.isValidDateFormat("2021-12-12");
        Assert.assertEquals(result, true);
        result = Validator.isValidDateFormat("2021-15-12");
        Assert.assertEquals(result, false);
        result = Validator.isValidDateFormat("2021-12-15");
        Assert.assertEquals(result, true);
        result = Validator.isValidDateFormat("2021-12");
        Assert.assertEquals(result, false);
    }
}