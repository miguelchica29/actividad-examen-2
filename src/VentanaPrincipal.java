import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
    
    // Componentes principales
    private JTable tablaEnvios;
    private DefaultTableModel modeloTabla;
    private GestorEnvios gestor;
    
    // Campos del formulario
    private JTextField txtNumero;
    private JTextField txtCliente;
    private JTextField txtPeso;
    private JComboBox<String> comboTipo;
    private JTextField txtDistancia;
    
    public VentanaPrincipal() {
        gestor = new GestorEnvios();
        inicializarUI();
       
    }
    
    private void inicializarUI() {
        setTitle("Operador Logístico");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel superior con el formulario
        JPanel panelFormulario = crearPanelFormulario();
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        
        // Panel central con la tabla
        JPanel panelTabla = crearPanelTabla();
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);
        
        add(panelPrincipal);
    }
    
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Registro de Envíos"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Fila 1: Número
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Número:"), gbc);
        
        gbc.gridx = 1;
        txtNumero = new JTextField(15);
        txtNumero.setEditable(false);
        txtNumero.setBackground(Color.LIGHT_GRAY);
        txtNumero.setText("Se genera automático");
        panel.add(txtNumero, gbc);
        
        // Espacio
        gbc.gridx = 2;
        panel.add(new JLabel("     "), gbc);
        
        // Fila 1: Cliente
        gbc.gridx = 3;
        panel.add(new JLabel("Cliente:"), gbc);
        
        gbc.gridx = 4;
        txtCliente = new JTextField(15);
        panel.add(txtCliente, gbc);
        
        // Fila 2: Peso
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Peso (Kg):"), gbc);
        
        gbc.gridx = 1;
        txtPeso = new JTextField(15);
        panel.add(txtPeso, gbc);
        
        gbc.gridx = 2;
        panel.add(new JLabel("     "), gbc);
        
        // Fila 2: Tipo
        gbc.gridx = 3;
        panel.add(new JLabel("Tipo:"), gbc);
        
        gbc.gridx = 4;
        comboTipo = new JComboBox<>(new String[]{"Terrestre", "Aéreo", "Marítimo"});
        comboTipo.setPreferredSize(new Dimension(150, 25));
        panel.add(comboTipo, gbc);
        
        // Fila 3: Distancia
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Distancia (Km):"), gbc);
        
        gbc.gridx = 1;
        txtDistancia = new JTextField(15);
        panel.add(txtDistancia, gbc);
        
        // Fila 4: Botones
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 12));
        btnGuardar.addActionListener(e -> guardarEnvio());
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.addActionListener(e -> limpiarFormulario());
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        panel.add(panelBotones, gbc);
        
        // Evento para generar número automático al seleccionar tipo
        comboTipo.addActionListener(e -> generarNumeroAutomatico());
        
        return panel;
    }
    
    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Listado de Envíos"));
        
        // Crear la tabla con las columnas exactas como en la imagen
        String[] columnas = {"Tipo", "Código", "Cliente", "Peso (Kg)", "Distancia (Km)", "Costo ($)"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaEnvios = new JTable(modeloTabla);
        tablaEnvios.setRowHeight(30);
        tablaEnvios.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaEnvios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaEnvios.getTableHeader().setBackground(new Color(240, 240, 240));
        
        // Configurar ancho de columnas
        tablaEnvios.getColumnModel().getColumn(0).setPreferredWidth(80);
        tablaEnvios.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaEnvios.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaEnvios.getColumnModel().getColumn(3).setPreferredWidth(80);
        tablaEnvios.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaEnvios.getColumnModel().getColumn(5).setPreferredWidth(120);
        
        JScrollPane scrollPane = new JScrollPane(tablaEnvios);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones debajo de la tabla
        // Panel de botones debajo de la tabla
JPanel panelBotonesTabla = new JPanel(new FlowLayout(FlowLayout.RIGHT));

JButton btnEliminar = new JButton("Eliminar Seleccionado");
btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
btnEliminar.addActionListener(e -> eliminarEnvioSeleccionado());

panelBotonesTabla.add(btnEliminar);

panel.add(panelBotonesTabla, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void generarNumeroAutomatico() {
        int cantidad = gestor.getCantidadEnvios();
        int numero = 10001 + cantidad;
        txtNumero.setText(String.valueOf(numero));
    }
    
    private void guardarEnvio() {
        try {
            String nombreCliente = txtCliente.getText().trim();
            if (nombreCliente.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese el nombre del cliente", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double peso = Double.parseDouble(txtPeso.getText().trim());
            double distancia = Double.parseDouble(txtDistancia.getText().trim());
            
            if (peso <= 0 || distancia <= 0) {
                JOptionPane.showMessageDialog(this, "Peso y distancia deben ser mayores a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Cliente cliente = new Cliente(nombreCliente, "", "N/A");
            String tipo = (String) comboTipo.getSelectedItem();
            Envio nuevoEnvio = null;
            
            switch (tipo) {
                case "Terrestre":
                    nuevoEnvio = new EnvioTerrestre(cliente, peso, distancia);
                    break;
                case "Aéreo":
                    nuevoEnvio = new EnvioAereo(cliente, peso, distancia);
                    break;
                case "Marítimo":
                    nuevoEnvio = new EnvioMaritimo(cliente, peso, distancia);
                    break;
            }
            
            if (nuevoEnvio != null) {
                gestor.agregarEnvio(nuevoEnvio);
                actualizarTabla();
                limpiarFormulario();
                
                JOptionPane.showMessageDialog(this, 
                    "✓ Envío guardado exitosamente\nCódigo: " + nuevoEnvio.getCodigo(),
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos para peso y distancia", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarFormulario() {
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        comboTipo.setSelectedIndex(0);
        generarNumeroAutomatico();
        txtCliente.requestFocus();
    }
    
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        
        ArrayList<Envio> envios = gestor.getListaEnvios();
        
        for (Envio envio : envios) {
            String tipo = "";
            if (envio instanceof EnvioTerrestre) tipo = "Terrestre";
            else if (envio instanceof EnvioAereo) tipo = "Aéreo";
            else if (envio instanceof EnvioMaritimo) tipo = "Marítimo";
            
            String nombreCliente = envio.getCliente().getNombre();
            if (nombreCliente.length() > 18) {
                nombreCliente = nombreCliente.substring(0, 15) + "...";
            }
            
            Object[] fila = {
                tipo,
                envio.getCodigo().substring(envio.getCodigo().length() - 5),
                nombreCliente,
                envio.getPesoKg(),
                envio.getDistanciaKm(),
                String.format("$%,.0f", envio.calcularTarifaTotal())
            };
            modeloTabla.addRow(fila);
        }
        
        generarNumeroAutomatico();
    }
    
    private void eliminarEnvioSeleccionado() {
        int filaSeleccionada = tablaEnvios.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String codigoCompleto = "";
            ArrayList<Envio> envios = gestor.getListaEnvios();
            if (filaSeleccionada < envios.size()) {
                codigoCompleto = envios.get(filaSeleccionada).getCodigo();
            }
            
            int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de eliminar este envío?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                gestor.retirarEnvio(codigoCompleto);
                actualizarTabla();
                JOptionPane.showMessageDialog(this, "✓ Envío eliminado correctamente");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un envío para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void cargarDatosEjemplo() {
        Cliente cliente1 = new Cliente("Polimeros Colombia", "", "123");
        EnvioTerrestre envio1 = new EnvioTerrestre(cliente1, 1200.0, 400.0);
        gestor.agregarEnvio(envio1);
        
        Cliente cliente2 = new Cliente("Textiles Pepalfa", "", "456");
        EnvioTerrestre envio2 = new EnvioTerrestre(cliente2, 500.0, 600.0);
        gestor.agregarEnvio(envio2);
        
        Cliente cliente3 = new Cliente("Flores Colombianas", "", "789");
        EnvioAereo envio3 = new EnvioAereo(cliente3, 1500.0, 2000.0);
        gestor.agregarEnvio(envio3);
        
        actualizarTabla();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new VentanaPrincipal().setVisible(true);
        });
    }
}