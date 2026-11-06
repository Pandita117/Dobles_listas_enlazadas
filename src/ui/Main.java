package ui;

import utils.Validations;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Instanciamos el controlador que maneja toda la vista
        MenuController menu = new MenuController();
        menu.start();
    }
}