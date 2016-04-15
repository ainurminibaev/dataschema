package ru.ainurminibaev.db.util;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by ainurminibaev on 13.05.14.
 */
public class PasswordHelper {


    private static String getSaltedPass(String password, String salt) {
        return password + salt;
    }

    /**
     * Generate hash for users with own password
     */
    public static String encrypt(String unencrypted, String salt) {
        return DigestUtils.md5Hex(getSaltedPass(unencrypted, salt));
    }

    /**
     * Generate hash for random passwords, when users has been generated
     */
    public static String encryptForGenerated(String unencrypted, String salt) {
        return DigestUtils.md5Hex(getSaltedPass(unencrypted, salt)) + unencrypted;
    }


    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(Constants.GENERATED_PASS_LENGTH);
    }

    public static String generateSalt() {
        return UUID.randomUUID().toString();
    }

    public static String getGeneratedPasswordFromHash(String hash) {
        return hash.substring(hash.length() - Constants.GENERATED_PASS_LENGTH);
    }

    public static boolean isPasswordGenerated(String maybeGeneratedPassword, String salt) {
        String password = getGeneratedPasswordFromHash(maybeGeneratedPassword);
        String generatedHash = PasswordHelper.encrypt(password, salt) + password;
        return generatedHash.equals(maybeGeneratedPassword);
    }
}
