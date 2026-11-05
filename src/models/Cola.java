
import java.util.ArrayList;

public class Cola<T> {


    //aun no ta ando viendo por que la cola que nos dieron es estatica pero el arreglo creo va aser dinamico, entonces no se si hacerla estatica o dinamica,
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
//
//    public void  insertar ( T elemento) {
//        if (fin == )
//    }


}


