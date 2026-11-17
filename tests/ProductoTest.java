import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Pruebas de la funcionalidad Producto (fase TEST).
 * Cada metodo corresponde a un caso de prueba (CP01 - CP07).
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductoTest {

    private Producto producto;
    private final ByteArrayOutputStream salida = new ByteArrayOutputStream();
    private PrintStream salidaOriginal;

    @Before
    public void prepararProducto() {
        producto = new Producto("P001", "Teclado", 350, 10);
        salidaOriginal = System.out;
        System.setOut(new PrintStream(salida));
    }

    @After
    public void restaurarSalida() {
        System.setOut(salidaOriginal);
    }

    /** Ejecuta mostrarInformacion() y regresa lo que se imprimio en pantalla. */
    private String capturarInformacion(Producto p) {
        salida.reset();
        p.mostrarInformacion();
        return salida.toString();
    }

    @Test
    public void CP01_crearProducto() {
        assertNotNull("El producto no se creo", producto);
        assertEquals("P001", producto.getCodigo());
        assertEquals("Teclado", producto.getNombre());
        assertEquals(350.0, producto.getPrecio(), 0.001);
        assertEquals(10, producto.getExistencia());
    }

    @Test
    public void CP02_mostrarCodigo() {
        assertEquals("P001", producto.getCodigo());
        assertTrue("No se muestra el codigo P001",
                capturarInformacion(producto).contains("Codigo: P001"));
    }

    @Test
    public void CP03_mostrarNombre() {
        assertEquals("Teclado", producto.getNombre());
        assertTrue("No se muestra el nombre Teclado",
                capturarInformacion(producto).contains("Nombre: Teclado"));
    }

    @Test
    public void CP04_mostrarPrecio() {
        assertEquals(350.0, producto.getPrecio(), 0.001);
        assertTrue("No se muestra el precio 350",
                capturarInformacion(producto).contains("Precio: $350.0"));
    }

    @Test
    public void CP05_mostrarExistencia() {
        assertEquals(10, producto.getExistencia());
        assertTrue("No se muestra la existencia 10",
                capturarInformacion(producto).contains("Existencia: 10"));
    }

    @Test
    public void CP06_mostrarInformacionCompleta() {
        String info = capturarInformacion(producto);
        assertTrue("Falta el codigo", info.contains("Codigo: P001"));
        assertTrue("Falta el nombre", info.contains("Nombre: Teclado"));
        assertTrue("Falta el precio", info.contains("Precio: $350.0"));
        assertTrue("Falta la existencia", info.contains("Existencia: 10"));
    }

    @Test
    public void CP07_crearSegundoProducto() {
        Producto segundo = new Producto("P002", "Mouse", 180, 5);
        assertNotSame(producto, segundo);

        assertEquals("P002", segundo.getCodigo());
        assertEquals("Mouse", segundo.getNombre());
        assertEquals(180.0, segundo.getPrecio(), 0.001);
        assertEquals(5, segundo.getExistencia());

        // El primer producto no debe cambiar al crear el segundo
        assertEquals("P001", producto.getCodigo());
        assertEquals("Teclado", producto.getNombre());
        assertEquals(350.0, producto.getPrecio(), 0.001);
        assertEquals(10, producto.getExistencia());

        String info = capturarInformacion(segundo);
        assertTrue(info.contains("Codigo: P002"));
        assertTrue(info.contains("Nombre: Mouse"));
        assertTrue("Se mezclaron datos del primer producto", !info.contains("Teclado"));
    }
}
