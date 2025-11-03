package org.example.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationUtil {

    public static boolean validEmail(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }

    public static boolean isValidPassword(String password){

        return password!=null
                && password.length()>=8
                && password.matches(".*[A-Za-z].*")
                && password.matches(".*[0-9].*");
    }

}
