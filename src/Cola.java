import java.util.ArrayList;

public class Cola<T> {

    public ArrayList<T> cola;
    public int max;
    public int inicio;
    public int fin;

    public Cola(ArrayList<T> cola, int inicio, int fin) {
        this.cola = cola;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Cola() {
        this.cola = new ArrayList<>();
        this.inicio = -1;
        this.fin = -1;
    }
}
