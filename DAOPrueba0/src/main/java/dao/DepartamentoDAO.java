package dao;

import java.util.List;

import model.Departamento;

//interfaz DAO 
public interface DepartamentoDAO {     
  public boolean InsertarDep(Departamento dep); 
  public boolean EliminarDep(int deptno);  
  public boolean ModificarDep(int deptno, Departamento dep); 
  public Departamento ConsultarDep(int deptno); 
  public List<Departamento> ListarDep();
} 
