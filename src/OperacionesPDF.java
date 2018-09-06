
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PushbuttonField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author albertosml
 */
public class OperacionesPDF {
    
    public String generatePDFSueldo(int mes, int anio, String directorio) throws FileNotFoundException, DocumentException, ClassNotFoundException, SQLException {
        OperacionesBD o = new OperacionesBD();
        
        String month = o.obtainMes(mes);
        String ruta_pdf = directorio + "/sueldos_" + month + "_" + anio + ".pdf";
        
        try {
            ArrayList<String> a = o.calculateSueldo(mes, anio);
            if(a.get(0) == "error") return "error";
            
            Document doc = new Document(PageSize.A4);
            
            PdfWriter.getInstance(doc, new FileOutputStream(ruta_pdf));
            doc.open();
            
            doc.add(new Paragraph(" "));
            doc.add( Chunk.NEWLINE );
            
            Font f = new Font(Font.FontFamily.TIMES_ROMAN,24,Font.BOLD);
            
            Paragraph par = new Paragraph();
            par.setAlignment(Element.ALIGN_CENTER);
            par.setFont(f);
            par.add("SUELDOS " + month + " " + anio);
            doc.add(par);
            
            doc.add( Chunk.NEWLINE );
            doc.add( Chunk.NEWLINE );
            
            PdfPTable table = new PdfPTable(3); 
            
            f = new Font(Font.FontFamily.TIMES_ROMAN, 13f, Font.BOLDITALIC);
            
            PdfPCell columna1 = new PdfPCell(new Phrase("Nombre Trabajador",f));
            table.addCell(columna1);
            
            columna1 = new PdfPCell(new Phrase("Horas Trabajadas",f));
            table.addCell(columna1);
            
            columna1 = new PdfPCell(new Phrase("Sueldo",f));
            table.addCell(columna1);
            
            int i=0;
            Float sueldo_total = 0f;
            
            for(String s : a) {
                if(i % 3 == 2) {
                    s = s.replace(",", ".");
                    sueldo_total += Float.parseFloat(s);
                    table.addCell(s + "€");
                }
                else table.addCell(s);
                i++;
            }
            
            table.addCell("");
            table.addCell("");
            table.addCell("Total: " + sueldo_total + "€");
            
            doc.add(table);
            doc.close();
            
            return "";
        } catch (DocumentException | FileNotFoundException e) {
            return "error";
        }
        
    }
   
    public String generatePDFRegistro(String id, String mes, String anio, ArrayList<String> fechas, String directorio) throws IOException, DocumentException, ClassNotFoundException {
        OperacionesBD o = new OperacionesBD();
        ArrayList<Object> a = o.obtainTrabajador(id);
        
        if(a.get(0) == "error") return "BDerror";
        
        try {
            String month = o.obtainMes(Integer.parseInt(mes)-1);

            PdfReader reader = new PdfReader("registro_diario_trabajadores.pdf");
            PdfStamper stamper = new PdfStamper(reader,new FileOutputStream(
                    directorio + "/registro_diario_" + a.get(0) + "_" + 
                    month + "_" + anio + ".pdf"));

            AcroFields form = stamper.getAcroFields();

            String horas_totales = Integer.toString(6*fechas.size());

            PushbuttonField ad;

            PdfContentByte over = stamper.getOverContent(1);

            // Relleno fechas
            for(String fecha : fechas) {
                form.setField("hentradam" + fecha, "8H");
                form.setField("hsalidam" + fecha, "12H");
                form.setField("hentradat" + fecha, "16H");
                form.setField("hsalidat" + fecha, "18H");
                form.setField("htrabajadas" + fecha, "6H");

                //Item item = form.getFieldItem("firma" + fecha);
                Item item = form.getFieldItem("firma" + fecha);
                PdfDictionary widget = item.getWidget(0);
                PdfArray rect = widget.getAsArray(PdfName.RECT);

                Image image = Image.getInstance((byte[]) a.get(3));
                image.setAbsolutePosition(rect.getAsNumber(0).floatValue() + 30.0f, 
                        rect.getAsNumber(1).floatValue() - 0.2f);
                image.scalePercent(17,5.5f);
                over.addImage(image);
            }

            form.setField("htrabajadastotal", horas_totales + "H");
            form.setField("nombre", (String) a.get(0));
            form.setField("nif", (String) a.get(1));
            form.setField("nafiliacion", (String) a.get(2)); 
            form.setField("periodo", month + " " + anio);

            stamper.close();
            reader.close();

            return "";
        } catch (DocumentException | IOException | NumberFormatException ex) {
            return "PDFerror";
        }
    }
}
