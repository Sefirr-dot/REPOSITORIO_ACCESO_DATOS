package mysql;

import java.sql.*;
public class FuncNombre {

	 public static void main(String[] args) {  
		 try {     
			 Class.forName("oracle.jdbc.driver.OracleDriver");    
			 Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:free", "system", "Ora1234");       
			 //recuperar parametro de main  
			 String dep = args[0];
			 //departamento       
			 //Construir orden de llamada    
			 String sql = "{ ? = call nombre_dep (?, ?) } ";
			 // ORACLE       
			 //Preparar la llamada    
			 CallableStatement llamada = conexion.prepareCall(sql);   
			 //registrar parámetro de resultado   
			 llamada.registerOutParameter(1, Types.VARCHAR);
			 //valor devuelto         
			 llamada.setInt(2,Integer.parseInt(dep));    
			 //param de entrada     
			 //Registrar parámetro de salida  
			 llamada.registerOutParameter(3, Types.VARCHAR);
			 //parámetro OUT        
			 //Ejecutar el procedimiento      
			 llamada.executeUpdate();       
			 System.out.printf("Nombre Dep: %s, Localidad: %s %n", llamada.getString(1), llamada.getString(3));   
			 llamada.close();     
			 conexion.close();    
			 }       catch (ClassNotFoundException cn) { 
				 cn.printStackTrace();
				 }      catch (SQLException e) {
					 e.printStackTrace(); 
					 }   
		 }// fin de main }// fin de la clase 
 }

