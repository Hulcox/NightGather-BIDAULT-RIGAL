package com.night.gather.nightgather.security;

import lombok.NoArgsConstructor;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

@NoArgsConstructor
public class Password {

    public static final int SALT_BYTES = 24;
    public static final int HASH_BYTES = 24;
    public static final int PBKDF2_ITERATIONS = 1000;

    public static String createHash(final char[] password, final byte[] salt) {
        byte[] hash = pbkd2f(password, salt, PBKDF2_ITERATIONS, HASH_BYTES);
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" +  toHex(hash);
    }

    public static byte[] createSalt() {
        var random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTES];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] pbkd2f(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            var secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            return secretKeyFactory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        }
    }

    public static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    public static String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0){
            return String.format("%0" + paddingLength + "d", 0) + hex;
        }
        return hex;
    }
}

