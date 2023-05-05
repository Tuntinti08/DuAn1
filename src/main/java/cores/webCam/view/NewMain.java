/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cores.webCam.view;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

/**
 *
 * @author QUOC HUY
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        File file = new File("D:\\Du_an_1\\src\\main\\resources\\Phiếu-nhập-kho-mẫu-01-VT-Thông-tư-200.pdf");
        PDDocument document = PDDocument.load(file);

        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);
        Rectangle rect1 = new Rectangle(0, 350, 1000, 1000);
        stripper.addRegion("row1column1", rect1);
        PDPage firstPage = document.getPage(0);
        stripper.extractRegions(firstPage);
        String text = stripper.getTextForRegion("row1column1");
        String textData = text.substring(0, text.indexOf("Cộng"));
        String [] arrayData = textData.split("VND. ");
        String [] newArraya = null;
        for (int i = 0; i < arrayData.length; i++) {
            String string = arrayData[i];
            if(string.trim().length() == 0) {
                continue;
            }
            String [] elData = string.split("  ");
            System.out.println("-------------------------");
            for (int j = 0; j < elData.length; j++) {
                String string1 = elData[j];
                if(string1.trim().length() == 0) {
                    continue;
                }
                System.out.println(string1.trim());
            }
            
        }
        System.out.println(arrayData.length);
    }
    
}
