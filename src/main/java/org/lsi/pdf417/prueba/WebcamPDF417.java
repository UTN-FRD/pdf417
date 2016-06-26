package org.lsi.pdf417.prueba;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.pdf417.PDF417Reader;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebcamPDF417 {

    public static void main(String[] args) throws InterruptedException {

        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        WebcamPanel panel = new WebcamPanel(webcam);
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

        BufferedImage bufferedImage;
        while(true){

            // webcam
            bufferedImage = webcam.getImage();

            // ZXING
            BufferedImageLuminanceSource b = new BufferedImageLuminanceSource(bufferedImage);

            GlobalHistogramBinarizer binarizer = new GlobalHistogramBinarizer(b);

            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

            // lista con los formatos (solo 417)
            List<BarcodeFormat> lista = new ArrayList<>();
            lista.add(BarcodeFormat.PDF_417);

            // hints
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            hints.put(DecodeHintType.POSSIBLE_FORMATS, lista);


            PDF417Reader reader = new PDF417Reader();

            try {
                Result result = reader.decode(binaryBitmap, hints);
                System.out.println("Resultado: " + result.getText());
                break;
            } catch (NotFoundException e) {
                System.out.println("SIN CODIGO");
            } catch (FormatException e) {
                System.out.println("FORMATO ERRONEO");
            } catch (ChecksumException e) {
                System.out.println("CHECKSUM?");
            }

            Thread.sleep(500);
        }
        System.out.println("Programa terminado");
    }
}
