
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Captura {
    
    public void capturarPantalla(String r, int x, int y, int width, int height) throws IOException {
            // Hago captura
            BufferedImage captura = null;
            try {
                captura = new Robot().createScreenCapture(
                        new Rectangle(x,y,width,height));
            } catch (AWTException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Creo el nombre del archivo
            Calendar c = Calendar.getInstance();
                    
            int mes = c.get(Calendar.MONTH) + 1; // Convierto mes
            String month;
            if(mes < 10) month = "0" + Integer.toString(mes);
            else month = Integer.toString(mes);

            int dia = c.get(Calendar.DAY_OF_MONTH); // Convierto día del mes
            String day;
            if(dia < 10) day = "0" + Integer.toString(dia);
            else day = Integer.toString(dia);

            int hora = c.get(Calendar.HOUR_OF_DAY); // Convierto hora
            String hour;
            if(hora < 10) hour = "0" + Integer.toString(hora);
            else hour = Integer.toString(hora);

            int minutos = c.get(Calendar.MINUTE); // Convierto minutos
            String minutes;
            if(minutos < 10) minutes = "0" + Integer.toString(minutos);
            else minutes = Integer.toString(minutos);
            
            int segundos = c.get(Calendar.SECOND); // Convierto segundos
            String seconds;
            if(segundos < 10) seconds = "0" + Integer.toString(segundos);
            else seconds = Integer.toString(segundos);

            String ruta_defecto = r + "/captura_" + Integer.toString(c.get(Calendar.YEAR)) 
                    + month + day + "_" + hour + minutes + seconds;
            
            // Genero imagen en el directorio correspondiente
            File outputfile = new File(ruta_defecto + ".jpg");
            ImageIO.write(captura, "jpg", outputfile);
    }
}
