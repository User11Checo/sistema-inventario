import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.util.HashSet;
import java.util.Set;

/**
 * Ejecuta las pruebas de ProductoTest y muestra un reporte de resultados.
 * Termina con codigo 1 si alguna prueba falla (para que GitHub Actions lo detecte).
 */
public class EjecutarPruebas {

    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Set<String> fallidas = new HashSet<>();
        StringBuilder detalles = new StringBuilder();

        junit.addListener(new RunListener() {
            @Override
            public void testFailure(Failure failure) {
                fallidas.add(failure.getDescription().getMethodName());
                detalles.append("  ").append(failure.getDescription().getMethodName())
                        .append(": ").append(failure.getMessage()).append('\n');
            }

            @Override
            public void testFinished(Description d) {
                String nombre = d.getMethodName();
                String estado = fallidas.contains(nombre) ? "ERROR" : "CORRECTO";
                System.out.printf("%-35s %s%n", nombre.replaceFirst("_", " - "), estado);
            }
        });

        System.out.println("RESULTADOS DE LAS PRUEBAS");
        Result r = junit.run(ProductoTest.class);
        int correctas = r.getRunCount() - r.getFailureCount();
        System.out.println("Resultado: " + correctas + " de " + r.getRunCount() + " pruebas satisfactorias.");

        if (!r.wasSuccessful()) {
            System.out.println("\nDetalle de errores:");
            System.out.print(detalles);
            System.exit(1);
        }
    }
}
