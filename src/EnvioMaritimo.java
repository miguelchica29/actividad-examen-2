

public class EnvioMaritimo extends Envio {
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
}