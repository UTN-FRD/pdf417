package org.lsi.pdf417.prueba;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.oned.Code39Reader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImgCode39 {

    public static void main(String[] args)  {

        String path = "/home/juan/Escritorio/imagen.png";
        BufferedImage bufferedImage = null;

        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImageLuminanceSource b = new BufferedImageLuminanceSource(bufferedImage);
        GlobalHistogramBinarizer binarizer = new GlobalHistogramBinarizer(b);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

        List<BarcodeFormat> lista = new ArrayList<>();
        lista.add(BarcodeFormat.CODE_39);

        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.POSSIBLE_FORMATS,lista);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);

        Code39Reader reader = new Code39Reader();

        try {
            Result res = reader.decode(binaryBitmap, hints);
            System.out.println(res.getText());
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }
}
