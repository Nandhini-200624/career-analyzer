package com.career.analyzer.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class OcrService {

    public String extractText(File imageFile) {

        try {

            System.out.println("OCR START");

            Tesseract tesseract = new Tesseract();

            tesseract.setDatapath(
                "/usr/share/tesseract-ocr/5/tessdata"
            );

            tesseract.setLanguage("eng");

            String text = tesseract.doOCR(imageFile);

            System.out.println("OCR END");

            return text;

        } catch (TesseractException e) {

            e.printStackTrace();
            return "";
        }
    }
}