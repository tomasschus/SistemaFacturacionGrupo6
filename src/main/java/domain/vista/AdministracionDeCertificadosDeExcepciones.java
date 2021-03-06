package domain.vista;

import com.toedter.calendar.JDateChooser;
import domain.controlador.ControllerProveedor;
import domain.modelo.proveedores.Proveedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

public class AdministracionDeCertificadosDeExcepciones {
    private JButton guardarButton;
    private JPanel panelFECHAIIBB;
    private JPanel panelFECHAIVA;
    private JPanel panelPrincipal;
    private JComboBox selectProveedor;
    private JButton certificadoIIBBButton;
    private JButton certificadoIVAButton;
    private JDateChooser fechaIVA;
    private JDateChooser fechaIIBB;
    private JFrame frame;
    private ControllerProveedor cldrProveedor;
    private Proveedor proveedorSeleccionado;
    public AdministracionDeCertificadosDeExcepciones(){
        this.cldrProveedor = ControllerProveedor.getInstance();
        selectProveedor.addItem("");
        selectProveedor = cldrProveedor.comboProveedor(selectProveedor);

        Calendar cld = Calendar.getInstance();
        fechaIVA = new JDateChooser(cld.getTime());
        fechaIIBB = new JDateChooser(cld.getTime());
        panelFECHAIVA.add(fechaIVA);panelFECHAIIBB.add(fechaIIBB);
        fechaIVA.setDateFormatString("dd/MM/yyyy");
        fechaIIBB.setDateFormatString("dd/MM/yyyy");
        fechaIIBB.setEnabled(false);
        fechaIVA.setEnabled(false);

        certificadoIVAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fechaIVA.isEnabled()){
                    fechaIVA.setEnabled(false);
                }
                else{
                    fechaIVA.setEnabled(true);
                }
            }
        });
        certificadoIIBBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fechaIIBB.isEnabled()){
                    fechaIIBB.setEnabled(false);
                }
                else{
                    fechaIIBB.setEnabled(true);
                }
            }
        });
        selectProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cuit = Integer.valueOf(selectProveedor.getSelectedItem().toString().split(" cuit:")[1]);
                proveedorSeleccionado =  cldrProveedor.getProveedorXcuit(cuit);
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate dateIVA = LocalDate.ofInstant(fechaIVA.getDate().toInstant(), ZoneId.systemDefault());
                LocalDate dateIIBB = LocalDate.ofInstant(fechaIIBB.getDate().toInstant(), ZoneId.systemDefault());
                if(proveedorSeleccionado!=null){
                    if(fechaIVA.isEnabled()){
                        proveedorSeleccionado.setExcencionIVA(dateIVA);
                    }
                    if(fechaIIBB.isEnabled()){
                        proveedorSeleccionado.setExcencionIIBB(dateIIBB);
                    }
                    JOptionPane.showMessageDialog(null,"Se registraron los certificados");

                }


            }
        });
    }

    public void start(){
        frame = new JFrame("ADMINISTRAR CERTIFICADOS");
        frame.setContentPane( new AdministracionDeCertificadosDeExcepciones().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //frame.setSize(900,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
