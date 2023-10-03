package helper;

import java.security.SecureRandom;

public class Authentication {
    private static final int OTP_LENGTH = 10;
    private static final String OTP_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String generateOTP() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder(OTP_LENGTH);

        for (int i = 0; i < OTP_LENGTH; i++) {
            int randomIndex = random.nextInt(OTP_CHARS.length());
            char randomChar = OTP_CHARS.charAt(randomIndex);
            otp.append(randomChar);
        }

        return otp.toString();
    }
}
