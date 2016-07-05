package org.lsi.pdf417.decoder;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// mejorar esto //
public class WebcamDecoder<Reader> {

    private Webcam webcam = Webcam.getDefault();
    private BufferedImage bufferedImage;
    private Reader reader;

    public WebcamDecoder() {
        BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        GlobalHistogramBinarizer binarizer = new GlobalHistogramBinarizer(bufferedImageLuminanceSource);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        List<BarcodeFormat> lista = new ArrayList<>();
        lista.add(BarcodeFormat.PDF_417);

        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        hints.put(DecodeHintType.POSSIBLE_FORMATS, lista);

        //reader = new Reader();
    }
}
