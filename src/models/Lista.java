package models;

public class Lista {

    public  Nodo PrimerNodo;
    public  Nodo finalNodo;


    public Lista() {
        this.PrimerNodo = null;
        this.finalNodo = null;
    }

    public void Iniciar_vaciarLista() {
        Nodo actual = PrimerNodo;
        while (actual != null) {

            Nodo siguiente = actual.sig;

            actual.sig = null;
            actual.ant = null;

            actual = siguiente;

        }

        this.PrimerNodo = null;
        this.finalNodo = null;
    }

    public static int posicionLetra(char letra) {
        if (letra >= 'A' && letra <= 'Z') {
            return (letra - 'A') * 2; //el espacio entre mayusculas
        }
        if (letra >= 'a' && letra <= 'z') {
            return (letra - 'a') * 2 + 1; //coloca la miniscula en la posicion siguiente a la mayuscula
        }
        return -1;
    }

    }

