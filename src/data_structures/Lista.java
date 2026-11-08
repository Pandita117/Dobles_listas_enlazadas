package data_structures;
import models.Nodo;

public class Lista {

    public Nodo PrimerNodo;
    public Nodo finalNodo;

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
            return (letra - 'A') * 2;
        }
        if (letra >= 'a' && letra <= 'z') {
            return (letra - 'a') * 2 + 1;
        }
        return -1;
    }

    public void insertar(char letra, int prioridad) {
        Nodo nuevo = new Nodo(letra, prioridad);

        if (PrimerNodo == null) {
            PrimerNodo = nuevo;
            finalNodo = nuevo;
            return;
        }

        if (prioridad == 1) {
            Nodo actual = PrimerNodo;
            Nodo ultimoP1 = null;

            while (actual != null && actual.prioridad == 1) {
                ultimoP1 = actual;
                actual = actual.sig;
            }

            if (ultimoP1 == null) {
                nuevo.sig = PrimerNodo;
                PrimerNodo.ant = nuevo;
                PrimerNodo = nuevo;
            } else {
                nuevo.sig = ultimoP1.sig;
                nuevo.ant = ultimoP1;
                if (ultimoP1.sig != null) {
                    ultimoP1.sig.ant = nuevo;
                } else {
                    finalNodo = nuevo;
                }
                ultimoP1.sig = nuevo;
            }
        } else {
            char letraUpper = Character.toUpperCase(letra);
            boolean buscarDesdeFin = (letraUpper >= 'N' && letraUpper <= 'Z');
            Nodo actual = null;

            if (buscarDesdeFin) {
                actual = finalNodo;
                while (actual != null && actual.prioridad == 2) {
                    if (posicionLetra(actual.info) <= posicionLetra(letra)) {
                        break;
                    }
                    actual = actual.ant;
                }

                if (actual == null || actual.prioridad == 1) {
                    Nodo primerP2 = PrimerNodo;
                    while (primerP2 != null && primerP2.prioridad == 1) {
                        primerP2 = primerP2.sig;
                    }

                    if (primerP2 == null) {
                        finalNodo.sig = nuevo;
                        nuevo.ant = finalNodo;
                        finalNodo = nuevo;
                    } else {
                        Nodo ant = primerP2.ant;
                        nuevo.sig = primerP2;
                        nuevo.ant = ant;
                        if (ant != null) ant.sig = nuevo;
                        primerP2.ant = nuevo;
                        if (primerP2 == PrimerNodo) PrimerNodo = nuevo;
                    }
                } else {
                    nuevo.sig = actual.sig;
                    nuevo.ant = actual;
                    if (actual.sig != null) {
                        actual.sig.ant = nuevo;
                    } else {
                        finalNodo = nuevo;
                    }
                    actual.sig = nuevo;
                }
            } else {
                actual = PrimerNodo;
                while (actual != null && actual.prioridad == 1) {
                    actual = actual.sig;
                }

                Nodo anteriorNodo = (actual != null) ? actual.ant : finalNodo;

                while (actual != null && actual.prioridad == 2 && posicionLetra(actual.info) < posicionLetra(letra)) {
                    anteriorNodo = actual;
                    actual = actual.sig;
                }

                if (anteriorNodo == null) {
                    nuevo.sig = PrimerNodo;
                    PrimerNodo.ant = nuevo;
                    PrimerNodo = nuevo;
                } else {
                    nuevo.sig = anteriorNodo.sig;
                    nuevo.ant = anteriorNodo;
                    if (anteriorNodo.sig != null) {
                        anteriorNodo.sig.ant = nuevo;
                    } else {
                        finalNodo = nuevo;
                    }
                    anteriorNodo.sig = nuevo;
                }
            }
        }
    }

    public void mostrar(boolean ascendente) {
        if (PrimerNodo == null) {
            System.out.println("La lista esta vacia.");
            return;
        }
        if (ascendente) {
            System.out.println("--- Mostrando Lista (P a F / Ascendente) ---");
            Nodo actual = PrimerNodo;
            while (actual != null) {
                System.out.println("[" + actual.info + " (P" + actual.prioridad + ")]");
                actual = actual.sig;
            }
        } else {
            System.out.println("--- Mostrando Lista (F a P / Descendente) ---");
            Nodo actual = finalNodo;
            while (actual != null) {
                System.out.println("[" + actual.info + " (P" + actual.prioridad + ")]");
                actual = actual.ant;
            }
        }
    }

    public Nodo buscarOptimizada(char letra, int prioridad) {
        if (PrimerNodo == null) return null;

        if (prioridad == 1) {
            Nodo actual = PrimerNodo;
            while (actual != null && actual.prioridad == 1) {
                if (actual.info == letra) return actual;
                actual = actual.sig;
            }
        } else if (prioridad == 2) {
            char letraMayus = Character.toUpperCase(letra);

            if (letraMayus >= 'A' && letraMayus <= 'M') {
                Nodo actual = PrimerNodo;
                while (actual != null && actual.prioridad == 1) {
                    actual = actual.sig;
                }
                while (actual != null) {
                    if (actual.info == letra) return actual;
                    if (Character.toUpperCase(actual.info) > letraMayus) break;
                    actual = actual.sig;
                }
            } else {
                Nodo actual = finalNodo;
                while (actual != null && actual.prioridad == 2) {
                    if (actual.info == letra) return actual;
                    if (Character.toUpperCase(actual.info) < letraMayus) break;
                    actual = actual.ant;
                }
            }
        }
        return null;
    }

    // --- ELIMINAR PRIORIDAD 1 (Como Cola / FIFO) ---
    public Nodo eliminarP1() {
        if (PrimerNodo == null || PrimerNodo.prioridad != 1) return null; // No hay P1

        Nodo eliminado = PrimerNodo;

        // El puenteo
        PrimerNodo = PrimerNodo.sig;
        if (PrimerNodo != null) {
            PrimerNodo.ant = null;
        } else {
            finalNodo = null; // La lista quedó completamente vacía
        }

        // Limpiamos la RAM (Garbage Collector)
        eliminado.sig = null;
        return eliminado;
    }

    // --- ELIMINAR PRIORIDAD 2 (Búsqueda exacta) ---
    public Nodo eliminarP2(char letra) {
        // Reutilizamos Búsqueda Optimizada para encontrar el nodo exacto
        Nodo objetivo = buscarOptimizada(letra, 2);

        if (objetivo == null) return null; // No existe

        // Puenteo de apuntadores (desconectar el nodo)
        if (objetivo.ant != null) {
            objetivo.ant.sig = objetivo.sig;
        } else {
            PrimerNodo = objetivo.sig;
        }

        if (objetivo.sig != null) {
            objetivo.sig.ant = objetivo.ant;
        } else {
            finalNodo = objetivo.ant;
        }

        // Clean de memoria
        objetivo.sig = null;
        objetivo.ant = null;

        return objetivo;
    }

}