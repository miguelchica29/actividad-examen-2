

public abstract class Envio {
    protected Cliente cliente;
    protected String codigo;
    protected double pesoKg;
    protected double distanciaKm;
    protected static int contadorEnvios = 0;
    
    public Envio(Cliente cliente, double pesoKg, double distanciaKm) {
        this.cliente = cliente;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
        this.codigo = generarCodigo();
        contadorEnvios++;
    }
    
    private String generarCodigo() {
        return String.valueOf(10000 + contadorEnvios + 1);
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public double getPesoKg() {
        return pesoKg;
    }
    
    public double getDistanciaKm() {
        return distanciaKm;
    }
    
    public abstract double calcularTarifaBase();
    public abstract double calcularRecargo();
    
    public double calcularTarifaTotal() {
        return calcularTarifaBase() + calcularRecargo();
    }
}