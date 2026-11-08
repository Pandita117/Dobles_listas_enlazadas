public class Lista {
        private Nodo inicio; // P (Principio)
        private Nodo fin;    // F (Fin)

        public Lista() {
            this.inicio = null;
            this.fin = null;
        }

        // --- MÉTODO INSERTAR ---
        public void insertar(char letra, int prioridad) {
            Nodo nuevo = new Nodo(letra, prioridad);

            // Caso 1: Lista vacía
            if (inicio == null) {
                inicio = nuevo;
                fin = nuevo;
                return;
            }

            // --- PRIORIDAD 1: Se inserta como Cola (al final del bloque de prioridad 1) ---
            if (prioridad == 1) {
                Nodo actual = inicio;
                Nodo ultimoP1 = null;

                // Encontrar el último nodo que sea de prioridad 1
                while (actual != null && actual.prioridad == 1) {
                    ultimoP1 = actual;
                    actual = actual.siguiente;
                }

                if (ultimoP1 == null) {
                    // No hay elementos de prioridad 1, entra antes del inicio actual
                    nuevo.siguiente = inicio;
                    inicio.anterior = nuevo;
                    inicio = nuevo;
                } else {
                    // Insertar justo después del último nodo de prioridad 1
                    nuevo.siguiente = ultimoP1.siguiente;
                    nuevo.anterior = ultimoP1;
                    if (ultimoP1.siguiente != null) {
                        ultimoP1.siguiente.anterior = nuevo;
                    } else {
                        fin = nuevo; // Si era el último de toda la lista
                    }
                    ultimoP1.siguiente = nuevo;
                }
            }
            // --- PRIORIDAD 2: Orden alfabético con Búsqueda Optimizada ---
            else {
                // Regla del PDF: Si es de la N a la Z, busca desde F hacia atrás; si es de A a M, desde P hacia adelante.
                char letraUpper = Character.toUpperCase(letra);
                boolean buscarDesdeFin = (letraUpper >= 'N' && letraUpper <= 'Z');

                Nodo actual = null;

                if (buscarDesdeFin) {
                    // Buscar desde el fin (F) hacia atrás
                    actual = fin;
                    while (actual != null && actual.prioridad == 2) {
                        // Comparación alfabética respetando orden AaBbCc...Zz
                        if (compararLetras(actual.letra, letra) <= 0) {
                            break; // Encontramos dónde debe ir después de 'actual'
                        }
                        actual = actual.anterior;
                    }

                    if (actual == null || actual.prioridad == 1) {
                        // Significa que es menor que todos los de prioridad 2 o toca insertarlo al inicio de la zona P2
                        Nodo primerP2 = inicio;
                        while (primerP2 != null && primerP2.prioridad == 1) {
                            primerP2 = primerP2.siguiente;
                        }

                        if (primerP2 == null) {
                            // Si no hay P2, va al final de la lista
                            fin.siguiente = nuevo;
                            nuevo.anterior = fin;
                            fin = nuevo;
                        } else {
                            // Insertar antes del primer P2
                            Nodo ant = primerP2.anterior;
                            nuevo.siguiente = primerP2;
                            nuevo.anterior = ant;
                            if (ant != null) ant.siguiente = nuevo;
                            primerP2.anterior = nuevo;
                            if (primerP2 == inicio) inicio = nuevo;
                        }
                    } else {
                        // Insertar después de 'actual'
                        nuevo.siguiente = actual.siguiente;
                        nuevo.anterior = actual;
                        if (actual.siguiente != null) {
                            actual.siguiente.anterior = nuevo;
                        } else {
                            fin = nuevo;
                        }
                        actual.siguiente = nuevo;
                    }

                } else {
                    // Buscar desde el principio (P) hacia adelante en la zona de Prioridad 2
                    actual = inicio;
                    // Avanzar los de prioridad 1
                    while (actual != null && actual.prioridad == 1) {
                        actual = actual.siguiente;
                    }

                    Nodo anteriorNodo = (actual != null) ? actual.anterior : fin;

                    while (actual != null && actual.prioridad == 2 && compararLetras(actual.letra, letra) < 0) {
                        anteriorNodo = actual;
                        actual = actual.siguiente;
                    }

                    // Inserción en la posición encontrada
                    if (anteriorNodo == null) {
                        // Insertar al inicio absoluto
                        nuevo.siguiente = inicio;
                        inicio.anterior = nuevo;
                        inicio = nuevo;
                    } else {
                        nuevo.siguiente = anteriorNodo.siguiente;
                        nuevo.anterior = anteriorNodo;
                        if (anteriorNodo.siguiente != null) {
                            anteriorNodo.siguiente.anterior = nuevo;
                        } else {
                            fin = nuevo;
                        }
                        anteriorNodo.siguiente = nuevo;
                    }
                }
            }
        }

        // --- MÉTODO MOSTRAR ---
        // ascendente = true (de P a F), ascendente = false (de F a P)
        public void mostrar(boolean ascendente) {
            if (inicio == null) {
                System.out.println("La lista está vacía.");
                return;
            }

            if (ascendente) {
                System.out.println("\n--- Mostrando Lista (P a F / Ascendente) ---");
                Nodo actual = inicio;
                while (actual != null) {
                    System.out.println("[" + actual.letra + " (P" + actual.prioridad + ")]");
                    actual = actual.siguiente;
                }
            } else {
                System.out.println("\n--- Mostrando Lista (F a P / Descendente) ---");
                Nodo actual = fin;
                while (actual != null) {
                    System.out.println("[" + actual.letra + " (P" + actual.prioridad + ")]");
                    actual = actual.anterior;
                }
            }
        }

        // Método auxiliar para mantener el orden alfabético exacto AaBbCc...Zz
        private int compararLetras(char a, char b) {
            if (a == b) return 0;
            char upperA = Character.toUpperCase(a);
            char upperB = Character.toUpperCase(b);
            if (upperA != upperB) {
                return Character.compare(upperA, upperB);
            }
            // Si las letras mayúsculas/minúsculas base son iguales, la minúscula va después de la mayúscula
            return Character.compare(a, b);
        }
    }

