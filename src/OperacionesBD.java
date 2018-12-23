
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author albertosml
 */
public class OperacionesBD {
    
    private String[] meses;
    
    public OperacionesBD() {
        meses = new String[]{"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO",
            "JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
    }
    
    public String obtainMes(int n) { return meses[n]; }
    
    private Connection conectar() throws ClassNotFoundException {
        // SQLite connection string
        String url = "jdbc:sqlite:bd.db";
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("error");
        }
        return conn;
    }
 
    private void desconectar(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("error");
        }
    }
    
    public String addTrabajador(String nombre, String nif, String num_af, String ruta_firma, String price) throws FileNotFoundException, ClassNotFoundException, IOException {
        String sql = "INSERT INTO Trabajador(nombre,nif,nafiliacion,firma,preciohora,activo) VALUES (?,?,?,?,?,?);";
 
        try {
            Connection conn = this.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, nif);
            pstmt.setString(3, num_af);
            if(!ruta_firma.isEmpty()) {
                File f = new File(ruta_firma);
                byte[] foto = new byte[(int) f.length()];
                InputStream i = new FileInputStream(f);
                i.read(foto);
                pstmt.setBytes(4, foto);
            }
            else pstmt.setBytes(4, null);
            pstmt.setFloat(5, Float.parseFloat(price));
            pstmt.setBoolean(6, true);
            pstmt.executeUpdate();
            
            this.desconectar(conn);
        } catch (SQLException e) {
            return "error";
        }
        
        return "";
    }
    
    public ArrayList<String> obtainSpinnerList() throws ClassNotFoundException {
        String sql = "SELECT id,nombre FROM Trabajador where activo=1;";
 
        ArrayList<String> nombres = new ArrayList<>();
        
        try {
            Connection conn = this.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            // loop through the result set
            while (rs.next()) {
                nombres.add(rs.getInt("id") + ": " + rs.getString("nombre"));
            }
            
            this.desconectar(conn);
            
            if(nombres.size() == 0) nombres.add("error");
            
            return nombres;
        } catch (SQLException e) {
            nombres.clear();
            nombres.add("error");
            return nombres;
        }
       
    }
    
    public ArrayList<String> obtainSpinnerListInicio() throws ClassNotFoundException {
        String sql = "SELECT id,nombre,activo FROM Trabajador;";
 
        ArrayList<String> nombres = new ArrayList<>();
        
        try {
            Connection conn = this.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            // loop through the result set
            while (rs.next()) {
                nombres.add(rs.getInt("id") + ": " + rs.getString("nombre") + "_" + Boolean.toString(rs.getBoolean("activo")) );
            }
            
            this.desconectar(conn);
            
            if(nombres.size() == 0) nombres.add("error");
            
            return nombres;
        } catch (SQLException e) {
            nombres.clear();
            nombres.add("error");
            return nombres;
        }
       
    }
    
    public ArrayList<Object> obtainTrabajador(String id) throws ClassNotFoundException {
        String sql = "SELECT * FROM Trabajador WHERE id=?";
        
        ArrayList<Object> trabajador = new ArrayList<>();
        
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                trabajador.add(rs.getString("nombre"));
                trabajador.add(rs.getString("nif"));
                trabajador.add(rs.getString("nafiliacion"));
                trabajador.add(rs.getBytes("firma"));
                trabajador.add(rs.getFloat("preciohora"));
                trabajador.add(rs.getBoolean("activo"));
            }
            
            this.desconectar(conn);
            
            return trabajador;
        } catch (SQLException e) {
            trabajador.clear();
            trabajador.add("error");
            return trabajador;
        }
       
    }
    
    public String updateTrabajador(String id, String elem, int opcion) throws ClassNotFoundException, IOException {
        String sentencia = "";
        switch (opcion) {
            case 1:
                sentencia = "UPDATE Trabajador SET nombre = ? WHERE id = ?";
                break;
            case 2:
                sentencia = "UPDATE Trabajador SET nif = ? WHERE id = ?";
                break;
            case 3:
                sentencia = "UPDATE Trabajador SET nafiliacion = ? WHERE id = ?";
                break;
            case 4:
                sentencia = "UPDATE Trabajador SET firma = ? WHERE id = ?";
                break;
            case 5:
                sentencia = "UPDATE Trabajador SET preciohora = ? WHERE id = ?";
                break;
            default:
                sentencia = "UPDATE Trabajador SET activo = ? WHERE id = ?";
                break;
        }
        
        ArrayList<Object> trabajador = new ArrayList<>();
        
        try {
            Connection conn = this.conectar();
            
            PreparedStatement stmt = conn.prepareStatement(sentencia);
            if(opcion==4) {
                if(!elem.isEmpty()) {
                    byte[] bFile = Files.readAllBytes(Paths.get(elem));
                    stmt.setBytes(1, bFile);
                }
                else stmt.setBytes(1, null);
            }
            else stmt.setString(1, elem);
            stmt.setString(2, id);
            stmt.executeUpdate();
            
            this.desconectar(conn);
            
            return "";
        } catch (SQLException e) {
            return "error";
        }
    }
    
    public boolean searchMovimiento(String id) throws ClassNotFoundException{
        Calendar c = Calendar.getInstance();
        
        String sql = "SELECT * FROM Movimiento WHERE dia=" + c.get(Calendar.DAY_OF_MONTH) + " and mes=\"" + meses[c.get(Calendar.MONTH)] 
                + "\" and anio=" + c.get(Calendar.YEAR) + " and idTrabajador=?;";
      
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            
            this.desconectar(conn);
            
            // loop through the result set
            if (rs.next()) return true;
            else return false;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public String deleteMovimiento(String id) throws ClassNotFoundException{
        Calendar c = Calendar.getInstance();
        
        String sql = "DELETE FROM Movimiento WHERE dia=" + c.get(Calendar.DAY_OF_MONTH) + " and mes=\"" + meses[c.get(Calendar.MONTH)] 
                + "\" and anio=" + c.get(Calendar.YEAR) + " and idTrabajador=?;";
      
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeUpdate();
            
            this.desconectar(conn);
            
            return "";
        } catch (SQLException e) {
            return "error";
        }
    }
    
    public String insertMovimiento(String id, Float horas) throws ClassNotFoundException{
        Calendar c = Calendar.getInstance();
        
        String sql = "INSERT INTO Movimiento VALUES (?,?,?,?,?,?);";
      
        // Obtener precio/hora actual
        ArrayList<Object> datos_trabajador = this.obtainTrabajador(id);
        
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, c.get(Calendar.DAY_OF_MONTH));
            stmt.setString(2, meses[c.get(Calendar.MONTH)]);
            stmt.setInt(3, c.get(Calendar.YEAR));
            stmt.setString(4, id);
            stmt.setFloat(5, horas);
            stmt.setFloat(6, (float) datos_trabajador.get(4));
            stmt.executeUpdate();
            
            this.desconectar(conn);
            
            return "";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "error";
        }
    }
    
    public boolean checkSueldo(int mes,int anio,String id) throws ClassNotFoundException{
        String sql = "SELECT * FROM Sueldo WHERE mes=? and anio=? and idTrabajador=?";
      
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, meses[mes]);
            stmt.setInt(2, anio);
            stmt.setString(3, id);
            ResultSet rs = stmt.executeQuery();
            
            this.desconectar(conn);
            
            // loop through the result set
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
    
    public ArrayList<String> calculateSueldo(int mes, int anio) throws ClassNotFoundException, SQLException {
        ArrayList<String> a = new ArrayList<>();
        
        String sql = "SELECT id,nombre FROM Trabajador where activo=1;";
           
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                sql = "SELECT dia,horastrabajadas,preciohora FROM Movimiento where mes=? and anio=? and "
                            + "idTrabajador=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, meses[mes]);
                stmt.setInt(2, anio);
                stmt.setString(3, rs.getString("id"));
                ResultSet r = stmt.executeQuery();

                Float horas_trabajadas = 0f;
                Float sueldo = 0f;
                
                while(r.next()) {
                    horas_trabajadas += r.getFloat("horastrabajadas");
                    sueldo += (r.getFloat("horastrabajadas") * r.getFloat("preciohora"));
                }
                
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.HALF_UP);
                
                boolean calculado = this.checkSueldo(mes, anio, rs.getString("id"));
                
                if(calculado) {
                    sql = "UPDATE Sueldo SET precio=?,horastrabajadasmensuales=? WHERE idTrabajador=? and mes=? and anio=?";

                    stmt = conn.prepareStatement(sql);
                    stmt.setFloat(1, sueldo);
                    stmt.setFloat(2, horas_trabajadas);
                    stmt.setString(3, rs.getString("id"));
                    stmt.setString(4, meses[mes]);
                    stmt.setInt(5, anio);
                    
                    stmt.executeUpdate();
                }
                else {
                    sql = "INSERT INTO Sueldo VALUES(?,?,?,?,?);";

                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, meses[mes]);
                    stmt.setInt(2, anio);
                    stmt.setFloat(3, sueldo);
                    stmt.setString(4, rs.getString("id"));
                    stmt.setFloat(5, horas_trabajadas);
                    stmt.executeUpdate();
                }
                    
                a.add(rs.getString("nombre"));
                a.add(horas_trabajadas + " h");
                a.add(df.format(sueldo));
            }

            this.desconectar(conn);
            
            return a;
        } catch (SQLException ex) {
            a.clear();
            a.add("error");
            return a;
        }
    }
    
    public void calculateSueldo(int mes, int anio, Connection conn) throws ClassNotFoundException, SQLException {
        String sql = "SELECT id,nombre FROM Trabajador where activo=1;";
        
        try {
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                sql = "SELECT dia,horastrabajadas,preciohora FROM Movimiento where mes=? and "
                        + "anio=? and idTrabajador=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, meses[mes]);
                stmt.setInt(2, anio);
                stmt.setString(3, rs.getString("id"));
                ResultSet r = stmt.executeQuery();

                Float horas_trabajadas = 0f;
                Float sueldo = 0f;
                
                while(r.next()) {
                    horas_trabajadas += r.getFloat("horastrabajadas");
                    sueldo += (r.getFloat("horastrabajadas") * r.getFloat("preciohora"));
                }
                
                boolean calculado = this.checkSueldo(mes, anio, rs.getString("id"));
                
                if(calculado) {
                    sql = "UPDATE Sueldo SET precio=?,horastrabajadasmensuales=? WHERE idTrabajador=? and mes=? and anio=?";

                    stmt = conn.prepareStatement(sql);
                    stmt.setFloat(1, sueldo);
                    stmt.setFloat(2, horas_trabajadas);
                    stmt.setString(3, rs.getString("id"));
                    stmt.setString(4, meses[mes]);
                    stmt.setInt(5, anio);

                    stmt.executeUpdate();
                }
                else {
                    sql = "INSERT INTO Sueldo VALUES(?,?,?,?,?);";

                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, meses[mes]);
                    stmt.setInt(2, anio);
                    stmt.setFloat(3, sueldo);
                    stmt.setString(4, rs.getString("id"));
                    stmt.setFloat(5, horas_trabajadas);
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<Vector<String>> fillTable(int inicio,int fin) throws ClassNotFoundException {
        // Obtener Mes Actual
        Calendar c = Calendar.getInstance();
        
        ArrayList<Vector<String>> a = new ArrayList<>();
        
        String sql = "SELECT id,nombre FROM Trabajador where activo=1;";
      
        Float [] sueldos = new Float[]{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f};
        
        Vector<String> v;
        
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                v = new Vector<>();
                v.add(rs.getString("nombre"));
                for(int i=8;i<12;i++) {
                    int mes = c.get(Calendar.MONTH);
                    if(mes == i || mes == (i+1) || (mes == 0 && i==11) ) {
                        this.calculateSueldo(i, inicio, conn);
                        
                        sql = "SELECT precio FROM Sueldo where mes=? and anio=? and idTrabajador=?;";
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1, meses[i]);
                        stmt.setInt(2, inicio);
                        stmt.setString(3, rs.getString("id"));
                        ResultSet r = stmt.executeQuery();

                        if(r.next()) {
                            v.add(df.format(r.getFloat("precio")) + "€");
                            sueldos[i] += r.getFloat("precio");
                        }
                    }
                    else {
                        sql = "SELECT precio FROM Sueldo where mes=? and anio=? and idTrabajador=?;";
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1, meses[i]);
                        stmt.setInt(2, inicio);
                        stmt.setString(3, rs.getString("id"));
                        ResultSet r = stmt.executeQuery();

                        if(r.next()) {
                            v.add(df.format(r.getFloat("precio")) + "€");
                            sueldos[i] += r.getFloat("precio");
                        }
                        else v.add("0€");
                    }
                }
                
                for(int i=0;i<8;i++) {
                    int mes = c.get(Calendar.MONTH);
                    if(mes == i || (mes == (i+1) && i!=7)) {
                        this.calculateSueldo(i, fin, conn);
                        
                        sql = "SELECT precio FROM Sueldo where mes=? and anio=? and idTrabajador=?;";
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1, meses[i]);
                        stmt.setInt(2, fin);
                        stmt.setString(3, rs.getString("id"));
                        ResultSet r = stmt.executeQuery();

                        if(r.next()) {
                            v.add(df.format(r.getFloat("precio")) + "€");
                            sueldos[i] += r.getFloat("precio");
                        }
                    }
                    else {
                        sql = "SELECT precio FROM Sueldo where mes=? and anio=? and idTrabajador=?;";
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1, meses[i]);
                        stmt.setInt(2, fin);
                        stmt.setString(3, rs.getString("id"));
                        ResultSet r = stmt.executeQuery();

                        if(r.next()) {
                            v.add(df.format(r.getFloat("precio")) + "€");
                            sueldos[i] += r.getFloat("precio");
                        }
                        else v.add("0€");
                    }
                }
                
                a.add(v);
            }
            
            v = new Vector<>();
            Float acumulado = 0f;
            
            v.add("Total:");
            
            for(int i=8;i<12;i++) {
                v.add(df.format(sueldos[i]) + "€");
                acumulado += sueldos[i];
            }
            for(int i=0;i<8;i++) {
                v.add(df.format(sueldos[i]) + "€");
                acumulado += sueldos[i];
            }
            
            a.add(v);
            
            v = new Vector<>();
            v.add(df.format(acumulado) + "€");
            
            a.add(v);
            
            this.desconectar(conn);
            
            return a;
        } catch (SQLException e) {
            a.clear();
            Vector<String> s = new Vector<>();
            s.add("error");
            a.add(s);
            return a;
        }
    }
    
    public Vector<String> obtainDiasTrabajados(String id) throws ClassNotFoundException {
        Vector<String> dias = new Vector<>();
        
        String sql = "SELECT nombre FROM Trabajador WHERE id=?;";
        
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            
            dias.add(rs.getString("nombre"));
            
            sql = "SELECT dia,horastrabajadas FROM Movimiento WHERE idTrabajador=?;";
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            
            for(int i=0;i<31;i++) dias.add("0 horas");
            
            while(rs.next()) dias.set(rs.getInt("dia"), rs.getString("horastrabajadas") + " horas");
            
            this.desconectar(conn);
            
            // loop through the result set
            return dias;
        } catch (SQLException e) {
            dias.clear(); 
            dias.add("error");
            return dias;
        }
    }
}
