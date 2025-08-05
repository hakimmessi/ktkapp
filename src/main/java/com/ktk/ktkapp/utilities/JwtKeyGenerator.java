package com.ktk.ktkapp.utilities;


import java.security.SecureRandom;
import java.util.Base64;
public class JwtKeyGenerator {
    public static void main(String[] args) {
        // Generate a cryptographically secure random key of 256 bits (32 bytes)
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);

        // Encode the key to Base64 for easy storage in application.properties
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);

        System.out.println("Generated Base64 Secret Key:");
        System.out.println("----------------------------------------");
        System.out.println(base64Key);
        System.out.println("----------------------------------------");
    }
}
