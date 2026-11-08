package ui;

import utils.Validations;
import java.util.Scanner;
import data_structures.Lista;
import models.Nodo;

public class MenuController {
    // Atributos globales
    private Scanner read;
    private Lista lista;

    public MenuController() {
        this.read = new Scanner(System.in);
        this.lista = new Lista(); // Inicializamos la lista vacía al arrancar
    }

    public void start() {
        int option = 0;

        while (option != 8) {
            System.out.println("=== GESTION DE LISTA DOBLEMENTE ENLAZADA ===");
            System.out.println("1. Inicializar / Borrar Lista");
            System.out.println("2. Mostrar Lista");
            System.out.println("3. Buscar");
            System.out.println("4. Insertar");
            System.out.println("5. Eliminar");
            System.out.println("6. Modificar");
            System.out.println("7. Creditos");
            System.out.println("8. Salir");

            // validación genérica de números positivos
            option = Validations.pedirenteropositivo(read, "Elija una opcion: ");

            switch (option) {
                case 1:
                    System.out.println("--- INICIALIZAR LISTA ---");
                    this.lista.Iniciar_vaciarLista();
                    System.out.println("Lista inicializada y memoria liberada exitosamente.");
                    break;
                case 2:
                    System.out.println("--- MOSTRAR LISTA ---");
                    int orden = Validations.pedirPrioridad(read, "Ingresa 1 para Ascendente (P a F) o 2 para Descendente (F a P): ");
                    this.lista.mostrar(orden == 1);
                    break;
                case 3:
                    handleBuscar();
                    break;
                case 4:
                    handleInsertar();
                    break;
                case 5:
                    handleEliminar();
                    break;
                case 6:
                    handleModificar();
                    break;
                case 7:
                    showCredits();
                    break;
                case 8:
                    System.out.println("Hasta luego...");
                    break;
                default:
                    System.out.println("Opcion no valida. Escoja del 1 al 8.");
            }
        }
        this.read.close();
    }


    // MÉTODOS DELEGADOS
    private void handleInsertar() {
        System.out.println("--- INSERTAR LETRA ---");
        char letra = Validations.pedirLetra(this.read, "Ingresa la letra a insertar: ");
        int prioridad = Validations.pedirPrioridad(this.read, "Ingresa la prioridad (1 o 2): ");

        this.lista.insertar(letra, prioridad);
        System.out.println("Letra '" + letra + "' insertada correctamente en prioridad " + prioridad + ".");
    }

    private void handleBuscar() {
        System.out.println("--- BUSCAR LETRA ---");
        char letra = Validations.pedirLetra(this.read, "Ingresa la letra que deseas buscar: ");
        int prioridad = Validations.pedirPrioridad(this.read, "Ingresa en que prioridad buscarla (1 o 2): ");

        Nodo resultado = this.lista.buscarOptimizada(letra, prioridad);

        if (resultado != null) {
            System.out.println("¡Exito! La letra '" + resultado.info + "' SI existe en la prioridad " + resultado.prioridad);
        } else {
            System.out.println("No se encontro la letra '" + letra + "' en la prioridad " + prioridad);
        }
    }

    private void handleEliminar() {
        System.out.println("--- ELIMINAR LETRA ---");
        int prioridad = Validations.pedirPrioridad(this.read, "Ingresa de que prioridad deseas eliminar (1 o 2): ");

        if (prioridad == 1) {
            System.out.println("Eliminando el primer nodo de la prioridad 1 (como Cola)...");
            Nodo borrado = this.lista.eliminarP1();
            if (borrado != null) {
                System.out.println("Se elimino la letra: " + borrado.info);
            } else {
                System.out.println("No hay elementos de prioridad 1 para eliminar.");
            }
        } else {
            char letra = Validations.pedirLetra(this.read, "Ingresa la letra exacta que deseas eliminar: ");
            Nodo borrado = this.lista.eliminarP2(letra);
            if (borrado != null) {
                System.out.println("Se elimino correctamente la letra: " + borrado.info);
            } else {
                System.out.println("No se encontro esa letra en la prioridad 2.");
            }
        }
    }

    private void handleModificar() {
        System.out.println("--- MODIFICAR LETRA ---");
        int prioridad = Validations.pedirPrioridad(this.read, "Ingresa en que prioridad esta la letra a modificar (1 o 2): ");
        char letraVieja = Validations.pedirLetra(this.read, "Ingresa la letra ACTUAL que deseas cambiar: ");
        char letraNueva = Validations.pedirLetra(this.read, "Ingresa la NUEVA letra: ");

        if (prioridad == 1) {
            Nodo nodo = this.lista.buscarOptimizada(letraVieja, 1);
            if (nodo != null) {
                nodo.info = letraNueva;
                System.out.println("Letra modificada con exito en P1.");
            } else {
                System.out.println("No se encontro la letra original.");
            }
        } else {
            // Modificación en P2 respetando el orden alfabético
            Nodo borrado = this.lista.eliminarP2(letraVieja);
            if (borrado != null) {
                this.lista.insertar(letraNueva, 2);
                System.out.println("Letra modificada y reordenada alfabeticamente con exito.");
            } else {
                System.out.println("No se encontro la letra original.");
            }
        }
    }

    private static void showCredits() {
        System.out.println("                  CRÉDITOS                    ");
        System.out.println("Materia: Estructura de Datos");
        System.out.println("Integrantes del equipo:");
        System.out.println(" - Daniel Martínez Rodriguez (Matrícula: 25420122)");
        System.out.println(" - Juan Diego Arroyo Bucio    (Matrícula: 25420050)");
        System.out.println(" - Emiliano Lara Dominguez    (Matrícula: 25420010)");
    }
}