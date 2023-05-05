package cores.exportPDF.services.serviceImpls;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import cores.exportPDF.repositoris.ExportSanPhamRepository;
import cores.exportPDF.services.ExportSanPhamService;
import cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCtspCustom;
import domainModels.ChiTietPhieuXuat;
import domainModels.ChiTietSanPham;
import domainModels.PhieuXuat;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import utilities.Converter;

/**
 *
 * @author QUOC HUY
 */
public class ExportSanPhamServiceImpl implements ExportSanPhamService {

    private ExportSanPhamRepository rp;
    private String partFileQrCode = "demo.png";

    public ExportSanPhamServiceImpl() {
        rp = new ExportSanPhamRepository();
    }

    public void createQR(String data)
            throws WriterException, IOException {

        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                = new HashMap<EncodeHintType, ErrorCorrectionLevel>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                ErrorCorrectionLevel.L);

        // Create the QR code and save
        // in the specified folder
        // as a jpg file
        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200);

        MatrixToImageWriter.writeToFile(
                matrix,
                partFileQrCode.substring(partFileQrCode.lastIndexOf('.') + 1),
                new File(partFileQrCode));
    }

    @Override
    public boolean exportSanPham(String fileName, UUID idSanPham) {
        try {
            File file = new File(fileName);
            ChiTietSanPham ctsp = findChiTietSanPhamById(idSanPham);

            createQR(ctsp.getId().toString());

            //Loading an existing document
            PDDocument doc = new PDDocument();

            PDPage page = new PDPage();

            PDFont font = PDType0Font.load(doc, new File("font\\vuArial.ttf"));

            PDFont fontBold = PDType0Font.load(doc, new File("font\\vuArialBold.ttf"));

            doc.addPage(page);

            PDImageXObject pdImage = PDImageXObject.createFromFile(partFileQrCode, doc);

            File fileQr = new File(partFileQrCode);
            fileQr.delete();

            PDPageContentStream contents = new PDPageContentStream(doc, page);
            if (ctsp.getHinhAnh() != null) {
                PDImageXObject pdImageGiay = PDImageXObject.createFromFile(ctsp.getHinhAnh(), doc);
                contents.drawImage(pdImageGiay, 400, 200);
            }
            contents.drawImage(pdImage, 400, 550);

            contents.beginText();

            String title = "Thông tin sản phẩm";

            String text = "Tên sản phẩm : " + ctsp.getSanPham().getTen();
            String text1 = "Mã sản phẩm : " + ctsp.getSanPham().getMa();
            String text2 = "Màu sắc : " + Converter.trangThaiMauSac(ctsp.getMau());
            String text3 = "Đơn vị : " + ctsp.getDonVi().getDonViQuyDoi();
            String text4 = "Size : " + ctsp.getSize();
            String text5 = "Ngày nhập : " + new Date(ctsp.getNgayTao()).toString();
            String text6 = "Năm bảo hành : " + ctsp.getNamBaoHanh();
            String text7 = "Giá bán : " + ctsp.getGiaBan();

            contents.setFont(fontBold, 30);
            contents.newLineAtOffset(70, 700);
            contents.setLeading(30f);
            contents.showText(title);
            contents.newLine();

            contents.setFont(font, 16);
            contents.setLeading(25f);
            contents.showText(text);
            contents.newLine();
            contents.showText(text1);
            contents.newLine();
            contents.showText(text2);
            contents.newLine();
            contents.showText(text3);
            contents.newLine();
            contents.showText(text4);
            contents.newLine();
            contents.showText(text5);
            contents.newLine();
            contents.showText(text6);
            contents.newLine();
            contents.showText(text7);
            contents.newLine();
            contents.endText();
            contents.close();

            //Saving the document
            doc.save(file);

            //Closing the document
            doc.close();
            return true;
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ChiTietSanPham findChiTietSanPhamById(UUID idSanPham) {
        return rp.findChiTietSanPham(idSanPham);
    }

    @Override
    public boolean exportPhieuXuat(String fileName, UUID idPhieu) {
        try {
            File file = new File(fileName);

            List<ChiTietPhieuXuat> ctpx = findChiTietPhieuXuat(idPhieu);
            if (ctpx.isEmpty()) {
                return false;
            }

            PhieuXuat px = ctpx.get(0).getIdPhieuXuat();

            //Loading an existing document
            PDDocument doc = new PDDocument();

            PDPage page = new PDPage();

            PDFont font = PDType0Font.load(doc, new File("font\\vuArial.ttf"));

            PDFont fontBold = PDType0Font.load(doc, new File("font\\vuArialBold.ttf"));

            doc.addPage(page);

            String[][] content = new String[ctpx.size() + 1][5];
            String[] a = {"Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"};
            content[0] = a;
            int index = 0;
            Double tongTien = 0.0;
            for (ChiTietPhieuXuat el : ctpx) {
                index ++;
                String [] elment = {el.getIdChiTietSp().getSanPham().getMa()
                        , el.getIdChiTietSp().getSanPham().getTen()
                        , String.valueOf(el.getSoLuong())
                        , String.valueOf(el.getIdChiTietSp().getGiaBan()) + "VND"
                        , String.valueOf(el.getIdChiTietSp().getGiaBan().doubleValue() * el.getSoLuong()) + "VND"
                };
                tongTien += el.getIdChiTietSp().getGiaBan().doubleValue() * el.getSoLuong();
                content[index] = elment;
            }
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            
            final int rows = content.length;
            final int cols = content[0].length;
            final float rowHeight = 20f;
            final float tableWidth = 500;
            final float tableHeight = rowHeight * rows;
            final float colWidth = tableWidth / (float) cols;
            final float cellMargin = 5f;

            float nexty = 500;
            for (int i = 0; i <= rows; i++) {
                contentStream.drawLine(100, nexty, 100 + tableWidth, nexty);
                nexty -= rowHeight;
            }

            float nextx = 100;
            for (int i = 0; i <= cols; i++) {
                contentStream.drawLine(nextx, 500, nextx, 500 - tableHeight);
                nextx += colWidth;
            }
            contentStream.beginText();
            contentStream.setFont(fontBold, 30);
            contentStream.newLineAtOffset(70, 700);
            contentStream.setLeading(30f);
            contentStream.showText("       Thông tin phiếu xuất");
            contentStream.newLine();

            contentStream.setFont(font, 16);
            contentStream.setLeading(25f);
            contentStream.showText("Mã phiếu: " + px.getId());
            contentStream.newLine();
            contentStream.showText("Tên khách hàng: " + px.getKhachHang().getTen());
            contentStream.newLine();
            contentStream.showText("Ngày tạo: " + new Date(px.getNgayTao()).toString());
            contentStream.newLine();
            contentStream.showText("Ngày thanh toán: " + px.getNgayThanhToan() == null ? "Chưa thanh toán" : new Date(px.getNgayThanhToan()).toString());
            contentStream.newLine();
                        
            contentStream.showText("Tổng tiền: " + String.valueOf(tongTien) + "VND");
            contentStream.newLine();
            
            contentStream.setFont(font, 12);
            contentStream.endText();
            float textx = 100 + cellMargin;
            float texty = 500 - 15;
            for (int i = 0; i < content.length; i++) {
                for (int j = 0; j < content[i].length; j++) {
                    String text = content[i][j];
                    contentStream.beginText();
                    contentStream.moveTextPositionByAmount(textx, texty);
                    contentStream.drawString(text);
                    contentStream.endText();
                    textx += colWidth;
                }
                texty -= rowHeight;
                textx = 100 + cellMargin;
            }

            
            contentStream.close();

            //Saving the document
            doc.save(file);

            //Closing the document
            doc.close();
            return true;
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public PhieuXuat findPhieuXuatById(UUID id) {
        return rp.findPhieuXuat(id);
    }

    @Override
    public List<ChiTietPhieuXuat> findChiTietPhieuXuat(UUID id) {
        return rp.findChiTietSanPhamByIdPhieuXuat(id);
    }

    @Override
    public boolean exportDanhSachSanPham(String fileName) {
        try {
            File file = new File(fileName);

            List<NvqlLuongKiemKeCtspCustom> listKiemKe = getListSanPhamKiemKe();
            if (listKiemKe.isEmpty()) {
                return false;
            }

            //Loading an existing document
            PDDocument doc = new PDDocument();

            PDPage page = new PDPage();

            PDFont font = PDType0Font.load(doc, new File("font\\vuArial.ttf"));

            PDFont fontBold = PDType0Font.load(doc, new File("font\\vuArialBold.ttf"));

            doc.addPage(page);

            String[][] content = new String[listKiemKe.size() + 1][5];
            String[] a = {"Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"};
            content[0] = a;
            int index = 0;
            Double tongTien = 0.0;
            for (NvqlLuongKiemKeCtspCustom el : listKiemKe) {
                index ++;
//                String [] elment = {el.getIdChiTietSp().getSanPham().getMa()
//                        , el.getIdChiTietSp().getSanPham().getTen()
//                        , String.valueOf(el.getSoLuong())
//                        , String.valueOf(el.getIdChiTietSp().getGiaBan()) + "VND"
//                        , String.valueOf(el.getIdChiTietSp().getGiaBan().doubleValue() * el.getSoLuong()) + "VND"
//                };
//                tongTien += el.getIdChiTietSp().getGiaBan().doubleValue() * el.getSoLuong();
//                content[index] = elment;
            }
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            
            final int rows = content.length;
            final int cols = content[0].length;
            final float rowHeight = 20f;
            final float tableWidth = 500;
            final float tableHeight = rowHeight * rows;
            final float colWidth = tableWidth / (float) cols;
            final float cellMargin = 5f;

            float nexty = 500;
            for (int i = 0; i <= rows; i++) {
                contentStream.drawLine(100, nexty, 100 + tableWidth, nexty);
                nexty -= rowHeight;
            }

            float nextx = 100;
            for (int i = 0; i <= cols; i++) {
                contentStream.drawLine(nextx, 500, nextx, 500 - tableHeight);
                nextx += colWidth;
            }
            contentStream.beginText();
            contentStream.setFont(fontBold, 30);
            contentStream.newLineAtOffset(0, 700);
            contentStream.setLeading(30f);
            contentStream.showText("       Thông tin phiếu xuất");
            contentStream.newLine();          
            contentStream.showText("Tổng tiền: " + String.valueOf(tongTien) + "VND");
            contentStream.newLine();
            
            contentStream.setFont(font, 12);
            contentStream.endText();
            float textx = 100 + cellMargin;
            float texty = 500 - 15;
            for (int i = 0; i < content.length; i++) {
                for (int j = 0; j < content[i].length; j++) {
                    String text = content[i][j];
                    contentStream.beginText();
                    contentStream.moveTextPositionByAmount(textx, texty);
                    contentStream.drawString(text);
                    contentStream.endText();
                    textx += colWidth;
                }
                texty -= rowHeight;
                textx = 100 + cellMargin;
            }

            
            contentStream.close();

            //Saving the document
            doc.save(file);

            //Closing the document
            doc.close();
            return true;
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<NvqlLuongKiemKeCtspCustom> getListSanPhamKiemKe() {
        return rp.getAllSanPhamKiemKe();
    }

}
