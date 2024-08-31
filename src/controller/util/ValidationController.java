package controller.util;

import java.util.regex.Pattern;

public class ValidationController {

    public static boolean name(String name) {

        Pattern idPattern = Pattern.compile("^[A-z\\s]{3,15}$");
        boolean matches = idPattern.matcher(name).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean email(String name) {

        Pattern idPattern = Pattern.compile("^[A-z\\s][@]{3,15}$");
        boolean matches = idPattern.matcher(name).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean address(String name) {
        Pattern idPattern = Pattern.compile("^[A-Z][a-zA-Z0-9-_]{4,32}$");
        boolean matches = idPattern.matcher(name).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean qty(String status) {
        Pattern idPattern = Pattern.compile("^[0-9]{1,3}$");
        boolean matches = idPattern.matcher(status).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean id(String id) {
        Pattern idPattern = Pattern.compile("^[0-9]{1,3}$");
        boolean matches = idPattern.matcher(id).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean password(String pwd) {
        Pattern idPattern = Pattern.compile("^(?=[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
        boolean matches = idPattern.matcher(pwd).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }

    }
}
