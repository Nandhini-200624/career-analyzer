package com.career.analyzer.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.InputStream;

public class ResumeTextExtractor {

    public static String extractText(InputStream inputStream) throws Exception {

        PDDocument document = PDDocument.load(inputStream);
        PDFTextStripper stripper = new PDFTextStripper();

        String text = stripper.getText(document);
    
        document.close();

        return text;
    }
}