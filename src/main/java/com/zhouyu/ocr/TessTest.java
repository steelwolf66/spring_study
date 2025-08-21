package com.zhouyu.ocr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class TessTest {
    public static void main(String[] args) throws TesseractException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("D:\\tesseract\\tessdata");
        tesseract.setLanguage("chi_sim");
        String s = tesseract.doOCR(new File("C:\\Users\\sun\\Desktop\\temp\\17552481452521578620.pdf"));
        System.out.println(s);
    }
}
