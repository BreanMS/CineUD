package utils;

import java.util.regex.Pattern;

import io.github.palexdev.materialfx.controls.MFXPasswordField;

public class Validators {

    public static boolean matchNombres(String input) {
        return Pattern.matches("[A-Z][a-z]{1,24}", input);
    }


    public static boolean matchEmail(String input) {
        return Pattern.matches("[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+", input);
    }

    public static boolean matchContraseña(String input) {
        return Pattern.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])([a-zA-Z\\d]{6,15}))", input);
    }

    public static boolean matchTelefono(String input){
        return Pattern.matches("([0-9]{7,10})", input);
    }
    public static boolean matchCedula(String input){
        return Pattern.matches("([0-9]{7,12})", input);
    }
    public void confirmarContraseñas(MFXPasswordField passwordTextField, MFXPasswordField confirmPasswordTextField) throws IllegalAccessException {

        if (!passwordTextField.getText().trim().isEmpty() && !confirmPasswordTextField.getText().trim().isEmpty()) {
            if (passwordTextField.getText().equals(confirmPasswordTextField.getText()) && matchContraseña(passwordTextField.getText())) {
                System.out.println("Las contraseñas son iguales");
            } else {
                throw new IllegalAccessException("Contras distintas");
            }

        } else {
            return ;
        }
    }
}
