package org.lsi.pdf417.prueba;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.oned.Code39Reader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebcamCode39 {

    public static void main(String[] args) throws InterruptedException {

        // creo el objeto webcam.
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        WebcamPanel panel = new WebcamPanel(webcam);

        panel.setFPSLimit(10);

        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(false);

        JFrame window = new JFrame("Test webcam panel");
        window.add(panel);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        // para los nombres de las imagenes
        int i = 0;


        BufferedImage bufferedImage;
        while(true) {

            String snapshotsDir = "/home/juan/Programacion/LSI/Inventarios/snapshots/imag" + i + ".png";

            // obtengo imagen de la webcam y la guardo.
            bufferedImage = webcam.getImage();
            File file = new File(snapshotsDir);
            try {
                ImageIO.write(bufferedImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            i++;

            // ZXING
            BufferedImageLuminanceSource b = new BufferedImageLuminanceSource(bufferedImage);

            GlobalHistogramBinarizer binarizer = new GlobalHistogramBinarizer(b);

            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

            // lista con los formatos (solo 39)
            List<BarcodeFormat> lista = new ArrayList<>();
            lista.add(BarcodeFormat.CODE_39);

            // hints
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.POSSIBLE_FORMATS, lista);
            hints.put(DecodeHintType.ASSUME_CODE_39_CHECK_DIGIT, Boolean.TRUE);


            Code39Reader reader = new Code39Reader();

            try {
                Result result = reader.decode(binaryBitmap, hints);
                System.out.println("Resultado: " + result.getText());

                break;
            } catch (NotFoundException e) {
                System.out.println("SIN CODIGO");
            } catch (FormatException e) {
                System.out.println("FORMATO ERRONEO");
            }
            Thread.sleep(1000);

        }
        System.out.println("Programa terminado");
    }
}
