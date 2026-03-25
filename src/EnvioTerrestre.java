

public class EnvioTerrestre extends Envio {
    private static final double TARIFA_BASE_POR_KM = 1500;
    private static final double RECARGO_POR_KG = 2000;
    
    public EnvioTerrestre(Cliente cliente, double pesoKg, double distanciaKm) {
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