package io.gigachad.microservice.users.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {
    static boolean verifyPhoneNumber(String number) {
        String regex = "[0-9]{10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(number);
        return m.matches();
    }
}
