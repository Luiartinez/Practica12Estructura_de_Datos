data class Tarea(val nombre: String, val prioridad: Int)

class Nodoarbol(val tarea: Tarea) {
    var izquierda: Nodoarbol? = null
    var derecha: Nodoarbol? = null
}

class AborlTareas {
    private var raiz: Nodoarbol? = null

    fun insertar(tarea: Tarea) {
        raiz = insertarrecursivo(raiz, tarea)
    }

    private fun insertarrecursivo(actual: Nodoarbol?, tarea: Tarea): Nodoarbol {
        if (actual == null) {
            return Nodoarbol(tarea)
        }

        if (tarea.prioridad < actual.tarea.prioridad) {
            actual.izquierda = insertarrecursivo(actual.izquierda, tarea)
        } else {
            actual.derecha = insertarrecursivo(actual.derecha, tarea)
        }
        return actual
    }

    fun mostrarEnOrden() {
        mostrarEnOrdenRecursivo(raiz)
    }

    private fun mostrarEnOrdenRecursivo(nodo: Nodoarbol?) {
        if (nodo != null) {
            mostrarEnOrdenRecursivo(nodo.izquierda)
            println("Tarea: ${nodo.tarea.nombre}, prioridad: ${nodo.tarea.prioridad}")
            mostrarEnOrdenRecursivo(nodo.derecha)
        }
    }
}

//gestión de tareas
class CustomList<T> {
    private val elements = mutableListOf<T>()

    fun add(element: T): Boolean {
        return if (element !in elements) {
            elements.add(element)
            println("tarea agregada exitosamente")
            true
        } else {
            println("la tarea ya existe")
            false
        }
    }

    fun remove(element: T): Boolean {
        return if (element in elements) {
            elements.remove(element)
            println("Tarea eliminada")
            true
        } else {
            println("La tarea no se encuentra en la lista")
            false
        }
    }

    fun showAll() {
        if (elements.isEmpty()) {
            println("La lista esta vacia")
        } else {
            println("La lista de tareas:")
            elements.forEach { println(" - $it") }
        }
    }

    fun size(): Int {
        return elements.size
    }

    fun getAll(): List<T> {
        return elements
    }
}

//main
fun main(args: Array<String>) {
    val listaTareas = CustomList<Tarea>()
    val arbolTareas = AborlTareas()
    var opcion: Int

    do {
        println("\nGestor de tareas")
        println("1. agregar tarea")
        println("2. eliminar tarea")
        println("3. ver lista de tarea")
        println("4. ver lista de tarea por prioridad")
        println("5. salir")
        println("selecciona una opcion: ")
        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                println("ingrese el nombre de la tarea: ")
                val nombre = readLine() ?: ""
                println("ingrese la nueva prioridad de la tarea: ")
                val prioridad = readLine()?.toIntOrNull()?:0
                val nuevaTarea = Tarea(nombre, prioridad)
                listaTareas.add(nuevaTarea)
                arbolTareas.insertar(nuevaTarea)
            }
            2 -> {
                if (listaTareas.size() > 0) {
                    println("seleccione la tarea que desea eliminar:")
                    listaTareas.showAll()
                    println("ingrese el nombre exacto de la tarea: ")
                    val nombre = readLine() ?: ""
                    val eliminarTarea = listaTareas.getAll().find {it.nombre == nombre}
                    if(eliminarTarea != null){
                         listaTareas.remove(eliminarTarea)
                    }
                   
                } else {
                    println("la tarea no esta en la lista")
                }
            }
            3 -> {
                listaTareas.showAll()
            }
            4 -> {
                println("Lista de tareas por prioridad")
                arbolTareas.mostrarEnOrden()
            }
            5 -> println("saliendo del programa...")

            else -> println("Opcion no valida, ingrese una opcion valida. ")
        }
    } while (opcion != 5)
}