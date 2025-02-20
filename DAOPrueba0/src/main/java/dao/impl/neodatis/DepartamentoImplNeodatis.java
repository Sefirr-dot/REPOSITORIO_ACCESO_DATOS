package dao.impl.neodatis;

import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import dao.DepartamentoDAO;
import model.Departamento; 
 
//implementación de la interfaz 
public class DepartamentoImplNeodatis implements DepartamentoDAO { 
  static ODB bd; 
 
  public DepartamentoImplNeodatis() { 
     bd = ODBFactory.open("Departamento.BD"); 
  } 
 
  public static ODB crearConexion() { 
       return bd; //Devuelve el objeto ODB         
  } 
 
  @Override 
  public boolean InsertarDep(Departamento dep) { 
    bd.store(dep); 
    bd.commit(); 
    System.out.printf("Departamento: %d Insertado", dep.getDeptno()); 
    return true; 
  } 
 
  @Override 
  public boolean EliminarDep(int deptno) { 
    boolean valor = false; 
    IQuery query = new  
      CriteriaQuery(Departamento.class, Where.equal("deptno", deptno)); 
    Objects<Departamento> objetos = bd.getObjects(query); 
    try { 
        Departamento depart = (Departamento) objetos.getFirst(); 
        bd.delete(depart); 
        bd.commit(); 
        valor = true; 
        System.out.printf("Departamento: %d eliminado %n", deptno); 
    } catch (IndexOutOfBoundsException i) { 
        System.out.printf("Departamento a eliminar: %d No existe %n", 
                           deptno); 
    } 
    return valor; 
  } 
 
  @Override 
  public boolean ModificarDep(int deptno, Departamento dep) { 
    boolean valor = false; 
    IQuery query = new CriteriaQuery(Departamento.class, 
                       Where.equal("deptno", deptno)); 
    Objects<Departamento> objetos = bd.getObjects(query); 
    try { 
        Departamento depart = (Departamento) objetos.getFirst(); 
        depart.setDnombre(dep.getDnombre()); 
        depart.setLoc(dep.getLoc()); 
        bd.store(depart); // actualiza el objeto  
        valor = true; 
        bd.commit(); 
    } catch (IndexOutOfBoundsException i) { 
        System.out.printf("Departamento: %d No existe%n", deptno); 
    } 
    return valor; 
  } 
 
  @Override 
  public Departamento ConsultarDep(int deptno) { 
    IQuery query = new CriteriaQuery(Departamento.class, 
                       Where.equal("deptno", deptno)); 
    Objects<Departamento> objetos = bd.getObjects(query); 
    Departamento dep = new Departamento(); 
    if (objetos != null) { 
        try { 
            dep = (Departamento) objetos.getFirst(); 
        } catch (IndexOutOfBoundsException i) { 
            System.out.printf("Departamento: %d No existe%n", deptno); 
            dep.setDnombre("no existe"); 
            dep.setDeptno(deptno); 
            dep.setLoc("no existe"); 
        } 
    } 
    return dep; 
  } 
  @Override
  public List<Departamento> ListarDep() {
      IQuery query = new CriteriaQuery(Departamento.class);
      Objects<Departamento> objetos = bd.getObjects(query);
      List<Departamento> departamentos = new ArrayList<>();

      if (objetos != null) {
          try {
              for (Departamento dep : objetos) {
                  departamentos.add(dep);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

      return departamentos;
  }
}