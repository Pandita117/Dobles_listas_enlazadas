public class Nodo {
        char letra;
        int prioridad; // 1 o 2
        Nodo siguiente;
        Nodo anterior;

        public Nodo(char letra, int prioridad) {
            this.letra = letra;
            this.prioridad = prioridad;
            this.siguiente = null;
            this.anterior = null;
        }
    }

