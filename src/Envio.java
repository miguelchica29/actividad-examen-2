public abstract class Envio {
    // Atributos protegidos (accesibles para las clases hijas)
    protected Cliente cliente;
    protected String codigo;
    protected double pesoKg;
    protected double distanciaKm;
    protected static int contadorEnvios = 0;
    
    // Constructor
    public Envio(Cliente cliente, double pesoKg, double distanciaKm) {
        this.cliente = cliente;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
        this.codigo = generarCodigo();
        contadorEnvios++;
    }
    
    // Método para generar código único
    private String generarCodigo() {
        return "ENV-" + System.currentTimeMillis() + "-" + contadorEnvios;
    }
    
    // Getters
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
    
    // Métodos abstractos que las clases hijas deben implementar
    public abstract double calcularTarifaBase();
    public abstract double calcularRecargo();
    
    // Método concreto que usa polimorfismo
    public double calcularTarifaTotal() {
        return calcularTarifaBase() + calcularRecargo();
    }
    
    // Método para mostrar información del envío
    public String mostrarInformacion() {
        return String.format(
            "Código: %s\nCliente: %s\nPeso: %.2f Kg\nDistancia: %.2f Km\nTarifa Base: $%.2f\nRecargo: $%.2f\nTOTAL: $%.2f",
            codigo,
            cliente.getNombreCompleto(),
            pesoKg,
            distanciaKm,
            calcularTarifaBase(),
            calcularRecargo(),
            calcularTarifaTotal()
        );
    }
}