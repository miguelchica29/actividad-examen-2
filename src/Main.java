

public class Main {
    
    public static void main(String[] args) {
        
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
                
                
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
}