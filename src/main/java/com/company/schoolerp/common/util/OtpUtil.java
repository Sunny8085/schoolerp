package com.company.schoolerp.common.util;

import java.security.SecureRandom;

public class OtpUtil {
	
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateOtp(int digits) {

        if (digits < 4 || digits > 8) {
            throw new IllegalArgumentException("OTP digits must be between 4 and 8");
        }
        int bound = (int) Math.pow(10, digits);

        return String.format("%0" + digits + "d", RANDOM.nextInt(bound));
    }
}
