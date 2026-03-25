

import java.util.ArrayList;
import java.util.Iterator;

public class GestorEnvios {
    private ArrayList<Envio> listaEnvios;
    
    public GestorEnvios() {
        listaEnvios = new ArrayList<>();
    }
    
    public void agregarEnvio(Envio envio) {
        if (envio != null) {
            listaEnvios.add(envio);
            System.out.println("✓ Envío agregado exitosamente");
            System.out.println("Código del envío: " + envio.getCodigo());
        } else {
            System.out.println("✗ Error: El envío no puede ser nulo");
        }
    }
    
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
    
    public Envio buscarEnvio(String codigo) {
        for (Envio envio : listaEnvios) {
            if (envio.getCodigo().equals(codigo)) {
                return envio;
            }
        }
        return null;
    }
    
    public int getCantidadEnvios() {
        return listaEnvios.size();
    }
    
    public ArrayList<Envio> getListaEnvios() {
        return listaEnvios;
    }
}