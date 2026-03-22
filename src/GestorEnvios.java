import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase que gestiona la colección de envíos
 * Principio de Responsabilidad Única: Solo maneja operaciones con la lista de envíos
 */
public class GestorEnvios {
    private ArrayList<Envio> listaEnvios;
    
    public GestorEnvios() {
        listaEnvios = new ArrayList<>();
    }
    
    // Agregar envío
    public void agregarEnvio(Envio envio) {
        if (envio != null) {
            listaEnvios.add(envio);
            System.out.println("✓ Envío agregado exitosamente");
            System.out.println("Código del envío: " + envio.getCodigo());
        } else {
            System.out.println("✗ Error: El envío no puede ser nulo");
        }
    }
    
    // Retirar envío por código
    public boolean retirarEnvio(String codigo) {
        Iterator<Envio> iterator = listaEnvios.iterator();
        while (iterator.hasNext()) {
            Envio envio = iterator.next();
            if (envio.getCodigo().equals(codigo)) {
                iterator.remove();
                System.out.println("✓ Envío " + codigo + " retirado exitosamente");
                return true;
            }
        }
        System.out.println("✗ No se encontró el envío con código: " + codigo);
        return false;
    }
    
    // Listar todos los envíos
    public void listarEnvios() {
        if (listaEnvios.isEmpty()) {
            System.out.println("\n=== LISTA DE ENVÍOS ===");
            System.out.println("No hay envíos registrados");
            return;
        }
        
        System.out.println("\n=== LISTA DE ENVÍOS ===");
        System.out.println("Total de envíos: " + listaEnvios.size());
        System.out.println("----------------------------------------");
        
        int contador = 1;
        for (Envio envio : listaEnvios) {
            System.out.println("\n--- Envío #" + contador + " ---");
            System.out.println(envio.mostrarInformacion());
            System.out.println("----------------------------------------");
            contador++;
        }
    }
    
    // Buscar envío por código
    public Envio buscarEnvio(String codigo) {
        for (Envio envio : listaEnvios) {
            if (envio.getCodigo().equals(codigo)) {
                return envio;
            }
        }
        return null;
    }
    
    // Obtener cantidad de envíos
    public int getCantidadEnvios() {
        return listaEnvios.size();
    }
    // Agregar este método en la clase GestorEnvios
public ArrayList<Envio> getListaEnvios() {
    return listaEnvios;
}
}