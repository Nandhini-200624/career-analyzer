package com.career.analyzer.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class OcrService {

    public String extractText(File imageFile) {

        try {

            Tesseract tesseract = new Tesseract();

            tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");

            return tesseract.doOCR(imageFile);

        } catch (TesseractException e) {

            e.printStackTrace();
            return "";
        }
    }
}