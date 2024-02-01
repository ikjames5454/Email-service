package io.Email_service.Email_service.utils;

public class EmailUtils {
    public static String getEmailMessage(String name, String host, String token){

        return "Hello " + name + ",\n\nYour new account has been created. Please Click the link below to verify your account. \n\n" +
                getVerificationUrl(host,token) + "'\n\n' support Team";
    }

    private static String getVerificationUrl(String host, String token) {
        return host + "/api/users?token=" + token;
    }
}
