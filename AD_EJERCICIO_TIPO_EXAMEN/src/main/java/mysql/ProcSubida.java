package mysql;
import java.sql.*; 
public class ProcSubida {  
	public static void main(String[] args) { 
		try {          
			Class.forName("com.mysql.jdbc.Driver");  
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "");      
			//recuperar parámetros de main           
			String dep = args[0];   
			//departamento         
			String subida = args[1];
			//subida               //construir orden de llamada   
			String sql= "{ call subida_sal (?, ?) } ";               
			//Preparar la llamada        
			CallableStatement llamada = conexion.prepareCall(sql);    
			//Dar valor a los argumentos 
			llamada.setInt(1,Integer.parseInt(dep));    
			//primero
			llamada.setFloat(2,Float.parseFloat(subida));
			// segundo 
			//Ejecutar el procedimiento 
			llamada.executeUpdate(); 
			System.out.println ("Subida realizada....");
			llamada.close();  
			conexion.close();      
			}  catch (ClassNotFoundException cn) { 
				cn.printStackTrace();
				}  catch (SQLException e)   {
					e.printStackTrace();
					} 
		}
	}
