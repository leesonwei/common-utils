/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BarCodeUtil
 * Author:   Administrator
 * Date:     2019/1/3 20:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anywhere.commonutils.graphicode;

import com.anywhere.commonutils.safety.EncryptUtil;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/1/3
 * @since 1.0.0
 */
public class BarCodeUtil {
    private static final int WIDTH = 105;
    private static final int HEIGHT = 30;
    /**
     * 条形码编码
     *
     * @param contents
     * @param width
     * @param height
     */
    public static String encode(String contents, int width, int height) {
        int codeWidth = 3 + // start guard
                (7 * 6) + // left bars
                5 + // middle guard
                (7 * 6) + // right bars
                3; // end guard
        codeWidth = Math.max(codeWidth, width == 0?WIDTH:width);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.ITF, codeWidth, height == 0 ?HEIGHT:height, null);
            //MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(imgPath));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix,"png",out);
            return EncryptUtil.getInstance().Base64Encode(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 条形码解码
     *
     * @param imgPath
     * @return String
     */
    public static String decode(String imgPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            result = new MultiFormatReader().decode(bitmap, null);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}