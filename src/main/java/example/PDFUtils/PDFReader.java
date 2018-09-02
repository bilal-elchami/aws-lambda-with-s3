package example.PDFUtils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class PDFReader {

    public static String read(InputStream pdfInputStream) throws Exception {
        try {
            try (PDDocument document = PDDocument.load(pdfInputStream)) {
                document.getClass();
                if (!document.isEncrypted()) {
                    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                    stripper.setSortByPosition(true);
                    PDFTextStripper tStripper = new PDFTextStripper();
                    return tStripper.getText(document);
                } else {
                    throw new Exception("Encrypted File Exception");
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

}