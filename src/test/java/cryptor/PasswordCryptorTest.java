package cryptor;

import com.example.conference.cryptor.PasswordCryptor;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordCryptorTest {
    @Test
    public void generatePasswordCheck() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "test";
       String securedPassword = PasswordCryptor.generateStorngPasswordHash(password);
        Assert.assertTrue(!securedPassword.equals(password));
    }
    @Test
    public void validatePasswordCheck() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "test";
        String originalSecuredPass = PasswordCryptor.generateStorngPasswordHash(password);
        String currentPass = "test";
        boolean result = PasswordCryptor.validatePassword(currentPass, originalSecuredPass);
        Assert.assertTrue(result);
         password = "false";
         originalSecuredPass = PasswordCryptor.generateStorngPasswordHash(password);
         currentPass = "true";
         result = PasswordCryptor.validatePassword(currentPass, originalSecuredPass);
        Assert.assertFalse(result);
    }
}
