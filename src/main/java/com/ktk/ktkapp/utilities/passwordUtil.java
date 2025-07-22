package com.ktk.ktkapp.utilities;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class passwordUtil {
    private static final Argon2 argon2 = Argon2Factory.create();

    public static String hash(String password) {

        return argon2.hash(2, 65536, 1, password.toCharArray());
    }

    public static boolean verify(String raw, String hash) {

        return argon2.verify(hash, raw.toCharArray());
    }

    private passwordUtil() {}
}