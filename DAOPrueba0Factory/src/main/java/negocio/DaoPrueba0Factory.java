package negocio;

import java.util.Scanner;

import dao.DepartamentoDAO;
import dao.factory.DAOFactory;
import model.Departamento;

public class DaoPrueba0Factory {

    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("PRUEBA MYSQL");

        pruebamysql();
        System.out.println("------------------------------");
        System.out.println("PRUEBA NEODATIS");

        pruebaneodatis();

    }

    public static void pruebaneodatis() {
        DAOFactory bd = DAOFactory.getDAOFactory(DAOFactory.NEODATIS);
        DepartamentoDAO depDAO = bd.getDepartamentoDAO();

        //crear departamento
        Departamento dep = new Departamento(17, "NÓMINAS", "SEVILLA");
        depDAO.InsertarDep(dep);

        Scanner sc = new Scanner(System.in);
        int entero = 1;
        //Visualizar departamentos leidos por teclado
        System.out.println("\nTeclea Departamento a visualizar (0 sale): ");
        entero = sc.nextInt();
        while (entero > 0) {
            dep = depDAO.ConsultarDep(entero);
            System.out.printf("Dep: %d, Nombre: %s, Loc: %s %n", dep.getDeptno(),
                    dep.getDnombre(), dep.getLoc());
            System.out.println("\nTeclea Departamento a visualizar (0 sale): ");
            entero = sc.nextInt();
        }
        depDAO.EliminarDep(dep.getDeptno());
    }

    public static void pruebamysql() {
        DAOFactory bd = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        DepartamentoDAO depDAO = bd.getDepartamentoDAO();

        //crear departamento
        Departamento dep = new Departamento(17, "NÓMINAS", "SEVILLA");
        depDAO.InsertarDep(dep);

        Scanner sc = new Scanner(System.in);
        int entero = 1;
        //Visualizar departamentos leidos por teclado
        System.out.println("\nTeclea Departamento a visualizar (0 sale): ");
        entero = sc.nextInt();
        while (entero > 0) {

            dep = depDAO.ConsultarDep(entero);
            System.out.printf("Dep: %d, Nombre: %s, Loc: %s %n", dep.getDeptno(),
                    dep.getDnombre(), dep.getLoc());
            System.out.println("\nTeclea Departamento a visualizar (0 sale): ");
            entero = sc.nextInt();
        }
        depDAO.EliminarDep(dep.getDeptno());
    }

}


