
package models;

public class Nodo {

    public char info;
    public int prioridad;
    public Nodo sig;
    public Nodo ant;


    public Nodo(char info, int prioridad) {
        this.info = info;
        this.prioridad = prioridad;
        this.sig = null;
        this.ant = null;
    }

}
