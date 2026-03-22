public class EnvioMaritimo extends Envio {
    // Constantes según la tabla
    private static final double TARIFA_BASE_POR_KM = 800;
    private static final double RECARGO_POR_KG = 1000;
    
    public EnvioMaritimo(Cliente cliente, double pesoKg, double distanciaKm) {
        super(cliente, pesoKg, distanciaKm);
    }
    
    @Override
    public double calcularTarifaBase() {
        return distanciaKm * TARIFA_BASE_POR_KM;
    }
    
    @Override
    public double calcularRecargo() {
        return pesoKg * RECARGO_POR_KG;
    }
    
    @Override
    public String mostrarInformacion() {
        return "=== ENVÍO MARÍTIMO ===\n" + super.mostrarInformacion();
    }
}