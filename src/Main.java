

/**
 * Clase principal que inicia la aplicación de gestión de envíos
 * con interfaz gráfica de usuario
 */
public class Main {
    
    public static void main(String[] args) {
        // Ejecutar la interfaz gráfica en el hilo de eventos de Swing
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Usar el estilo visual del sistema operativo
                    javax.swing.UIManager.setLookAndFeel(
                        javax.swing.UIManager.getSystemLookAndFeelClassName()
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                // Crear y mostrar la ventana principal
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
}