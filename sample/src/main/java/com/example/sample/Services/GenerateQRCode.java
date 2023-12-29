package com.example.sample.Services;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.sun.mail.util.PropUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class GenerateQRCode {
    public static void main(String[] args) throws WriterException , IOException {


        generateQrCode("good job hamza ");



    }

    public static String generateQrCode(String data) throws WriterException , IOException{
        String generatedString = generateRandomString(6);
        System.out.println("Generated String: " + generatedString);
        String path = "C:\\Users\\DELL VOS\\OneDrive\\Bureau\\Project-java-Gi2\\Library-Management-Java-JavaFx\\sample\\src\\main\\resources\\com\\example\\sample\\imgs\\QR\\reservation\\"+generatedString+".jpg";
        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE,500,500);

        MatrixToImageWriter.writeToPath(matrix,"jpg", Paths.get(path));
        System.out.println("QR CREATED SUSSEFUELY ");
        return path;
    }

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }
}
