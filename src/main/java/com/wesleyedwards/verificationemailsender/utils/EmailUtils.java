package com.wesleyedwards.verificationemailsender.utils;

public class EmailUtils {
    public static String getEmailMessage(String name, String host, String token) {
        return "Hello " + name + "\n\nYour new account has been created. Please click the link below " +
                "to verify your account. \n\n" + getVerification(host, token) + "\n\nThe support team";
    }

    private static String getVerification(String host, String token) {
        return host + "/email/users?token=" + token;
    }
}
