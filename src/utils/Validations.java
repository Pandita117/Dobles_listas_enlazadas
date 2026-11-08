package utils;

import java.util.Scanner;

public class Validations {

    // validar que solo ingrese una letra (A-Z, a-z)
    public static char pedirLetra(Scanner read, String mensaje) {
        String input = "";
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje);
            input = read.nextLine().trim();

            // Validamos que no esté vacío, que sea de longitud 1 y que sea una letra
            if (input.isEmpty()) {
                System.out.println("¡Error! No dejes el campo en blanco");
            } else if (input.length() > 1) {
                System.out.println("¡Error! Solo debes ingresar UNA letra.");
            } else if (!Character.isLetter(input.charAt(0))) {
                System.out.println("¡Error! Metiste un número o símbolo. Usa solo letras.");
            } else {
                valido = true;
            }
        }
        return input.charAt(0);
    }

    // 2. Validar que la prioridad sea ESTRICTAMENTE 1 o 2
    public static int pedirPrioridad(Scanner read, String mensaje) {
        int prioridad = 0;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print(mensaje);
                prioridad = Integer.parseInt(read.nextLine().trim());

                if (prioridad == 1 || prioridad == 2) {
                    valido = true;
                } else {
                    System.out.println("¡Error! La prioridad solo puede ser 1 o 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("¡Error! Ingresa un número válido (1 o 2).");
            }
        }
        return prioridad;
    }

    public static int pedirenteropositivo(Scanner scanner, String message) {
        int number = -1;
        while (number < 0) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                number = Integer.parseInt(input);

                if (number < 0) {
                    System.out.println("Error: No metas números negativos. (mayor o igual a 0).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Ingresa un número entero válido.");;
            }
        }
        return number;
    }

}