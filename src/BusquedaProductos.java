import java.util.ArrayList;
import java.util.List;

/**
 * Funcionalidad de busqueda de productos del sistema de inventario.
 */
public class BusquedaProductos {

    /** Busca un producto por su codigo. Regresa null si no existe. */
    public static Producto buscarPorCodigo(List<Producto> productos, String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    /** Busca los productos cuyo nombre contenga el texto indicado. */
    public static List<Producto> buscarPorNombre(List<Producto> productos, String texto) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        List<Producto> inventario = new ArrayList<>();
        inventario.add(new Producto("P001", "Teclado USB", 349.90, 15));
        inventario.add(new Producto("P002", "Mouse inalambrico", 199.00, 30));
        inventario.add(new Producto("P003", "Teclado mecanico", 1250.00, 5));

        System.out.println("Busqueda por codigo P002:");
        Producto encontrado = buscarPorCodigo(inventario, "P002");
        if (encontrado != null) {
            encontrado.mostrarInformacion();
        } else {
            System.out.println("Producto no encontrado");
        }

        System.out.println("Busqueda por nombre 'teclado':");
        for (Producto p : buscarPorNombre(inventario, "teclado")) {
            p.mostrarInformacion();
        }
    }
}
