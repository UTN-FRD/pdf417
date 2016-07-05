package org.lsi.pdf417.gui;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class GUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        String fechaDeHoy = LocalDate.now().toString();
        String horaActual = LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute();
        String date = fechaDeHoy + " " + horaActual;
        System.out.println(date);
        // -------------- Labels ------------------------------------- //
        JLabel labelUsuario = new JLabel("Usuario");
        labelUsuario.setBounds(300, 50, 100, 100);
        JLabel labelEquipo = new JLabel("Equipo");
        labelEquipo.setBounds(300, 120, 100, 100);
        JLabel retiradaLabel = new JLabel("Retirada Registrada");
        retiradaLabel.setBounds(100, 350, 150, 150);
        JLabel labelFecha = new JLabel(date);
        labelFecha.setBounds(20, 0, 160,100);

        // ---------------- Text fields ------------------------------ //
        JTextField usuarioTextField = new JTextField(); // aca va el usuario que lee del codigo
        usuarioTextField.setText("Hola"); // borrar esto despues
        usuarioTextField.setBounds(300,120,80,40);
        usuarioTextField.setEditable(false);

        JTextField equipoTextField = new JTextField();
        equipoTextField.setText("LSI-sarasa");
        equipoTextField.setBounds(300,190,80,40);
        equipoTextField.setEditable(false);

        // webcam panel
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(false);
        panel.setBounds(50,100,200,200);

        // agrego los componentes al frame.
        frame.add(panel);
        frame.add(labelUsuario);
        frame.add(labelEquipo);
        frame.add(labelFecha);
        frame.add(retiradaLabel);
        frame.add(usuarioTextField);
        frame.add(equipoTextField);

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setTitle("PDF417");
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}

