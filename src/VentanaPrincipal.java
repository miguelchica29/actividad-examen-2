
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {

    private JTable tablaEnvios;
    private DefaultTableModel modeloTabla;
    private GestorEnvios gestor;

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
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JPanel panelFormulario = crearPanelFormulario();
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        
        JPanel panelTabla = crearPanelTabla();
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);
        
        add(panelPrincipal);
    }
    
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            "Registro de Envíos",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
       
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Número:"), gbc);
        gbc.gridx = 1;
        txtNumero = new JTextField(12);
        txtNumero.setEditable(false);
        txtNumero.setBackground(Color.WHITE);
        txtNumero.setFont(new Font("Arial", Font.PLAIN, 12));
        txtNumero.setText("10001");
        panel.add(txtNumero, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Cliente:"), gbc);
        gbc.gridx = 1;
        txtCliente = new JTextField(12);
        txtCliente.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(txtCliente, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Peso (Kg):"), gbc);
        gbc.gridx = 1;
        txtPeso = new JTextField(12);
        txtPeso.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(txtPeso, gbc);
        
        // COLUMNA DERECHA
        gbc.gridx = 2; gbc.gridy = 0;
        panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 3;
        comboTipo = new JComboBox<>(new String[]{"Terrestre", "Aéreo", "Marítimo"});
        comboTipo.setFont(new Font("Arial", Font.PLAIN, 12));
        comboTipo.setPreferredSize(new Dimension(120, 25));
        panel.add(comboTipo, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1;
        panel.add(new JLabel("Distancia (Km):"), gbc);
        gbc.gridx = 3;
        txtDistancia = new JTextField(12);
        txtDistancia.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(txtDistancia, gbc);
        
        gbc.gridx = 2; gbc.gridy = 2;
        panel.add(new JLabel(""), gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardar.setPreferredSize(new Dimension(100, 35));
        btnGuardar.addActionListener(e -> guardarEnvio());
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setPreferredSize(new Dimension(100, 35));
        btnCancelar.addActionListener(e -> limpiarFormulario());
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        panel.add(panelBotones, gbc);
        
        comboTipo.addActionListener(e -> generarNumeroAutomatico());
        
        return panel;
    }
    
    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Listado de Envíos",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12)
        ));
        
        String[] columnas = {"Tipo", "Código", "Cliente", "Peso (Kg)", "Distancia (Km)", "Costo ($)"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaEnvios = new JTable(modeloTabla);
        tablaEnvios.setRowHeight(28);
        tablaEnvios.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaEnvios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaEnvios.getTableHeader().setBackground(new Color(240, 240, 240));
        tablaEnvios.setGridColor(new Color(200, 200, 200));
        tablaEnvios.setShowGrid(true);
        
        tablaEnvios.getColumnModel().getColumn(0).setPreferredWidth(80);
        tablaEnvios.getColumnModel().getColumn(1).setPreferredWidth(80);
        tablaEnvios.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaEnvios.getColumnModel().getColumn(3).setPreferredWidth(80);
        tablaEnvios.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaEnvios.getColumnModel().getColumn(5).setPreferredWidth(120);
        
        JScrollPane scrollPane = new JScrollPane(tablaEnvios);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel panelBotonesTabla = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonesTabla.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        JButton btnEliminar = new JButton("Eliminar Seleccionado");
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEliminar.setPreferredSize(new Dimension(180, 35));
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
            
            
            String codigoMostrar = envio.getCodigo();
            
            Object[] fila = {
                tipo,
                codigoMostrar,
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