/**
 * Clase Producto: representa un producto del sistema de inventario.
 */
public class Producto {

    private String codigo;
    private String nombre;
    private double precio;
    private int existencia;

    public Producto(String codigo, String nombre, double precio, int existencia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.existencia = existencia;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getExistencia() { return existencia; }

    /** Muestra la informacion del producto en pantalla. */
    public void mostrarInformacion() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: $" + precio);
        System.out.println("Existencia: " + existencia);
        System.out.println("Estado: Producto registrado");
    }

    public static void main(String[] args) {
        Producto p = new Producto("P001", "Teclado USB", 349.90, 15);
        p.mostrarInformacion();
    }
}
