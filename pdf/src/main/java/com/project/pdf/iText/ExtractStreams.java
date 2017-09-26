package com.project.pdf.iText;

import com.itextpdf.kernel.PdfException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtractStreams {

    public static final String DEST = "./target/test/resources/sandbox/parse/extract_streams%s";
    public static final String SRC = "/Users/apple/Downloads/13--带有下标[第8页].pdf";

    public static void before() {
        new File(DEST).getParentFile().mkdirs();
    }

//    public static void main(String[] args) throws IOException {
////        before();
//        new ExtractStreams().manipulatePdf();
//    }

    public void manipulatePdf() throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC));

        PdfObject obj;
        List<Integer> streamLengths = new ArrayList<>();
        for (int i = 1; i <= pdfDoc.getNumberOfPdfObjects(); i++) {
            obj = pdfDoc.getPdfObject(i);
            if (obj != null && obj.isStream()) {
                byte[] b;
                try {
                    b = ((PdfStream) obj).getBytes();
                } catch (PdfException exc) {
                    b = ((PdfStream)obj).getBytes(false);
                }
                System.out.println(b.length);
                System.out.println("deal with:" + new String(b,"utf-8"));

            }
        }
        pdfDoc.close();
    }

}
