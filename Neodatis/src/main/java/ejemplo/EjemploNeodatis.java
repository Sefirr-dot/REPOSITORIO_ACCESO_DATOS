package ejemplo;

import org.neodatis.odb.*;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.core.query.criteria.ICriterion;

import java.math.BigDecimal;
import java.math.BigInteger;

public class EjemploNeodatis {
    public static void main(String[] args) {
        // Crear instancias para almacenar en BD
        Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 14);
        Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15);
        Jugadores j3 = new Jugadores("Mario", "baloncesto", "Guadalajara", 15);
        Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 14);

        // Abrir BD
        ODB odb = ODBFactory.open("neodatis.test");

        // Almacenar objetos
        odb.store(j1);
        odb.store(j2);
        odb.store(j3);
        odb.store(j4);

        // Recuperar y mostrar objetos básicos
        mostrarTodosLosJugadores(odb);

        // Consultas complejas
        mostrarNombreCiudadJugadores(odb);
        contarJugadores(odb);
        calcularEdadMedia(odb);
        obtenerMaxMinEdad(odb);
        agruparPorCiudad(odb);

        // Cerrar BD
        odb.close();
    }

    private static void mostrarTodosLosJugadores(ODB odb) {
        Objects<Jugadores> objects = odb.getObjects(Jugadores.class);
        System.out.println(objects.size() + " Jugadores:");
        for (Jugadores jug : objects) {
            System.out.println(jug.getNombre() + " - " + jug.getDeporte() + " - " + jug.getCiudad() + " - " + jug.getEdad());
        }
    }

    private static void mostrarNombreCiudadJugadores(ODB odb) {
        System.out.println("\nNombre y ciudad de todos los jugadores:");
        Values valores = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).field("nombre", "nom").field("ciudad"));
        while (valores.hasNext()) {
            ObjectValues ov = (ObjectValues) valores.next();
            System.out.println(ov.getByAlias("nom") + " - " + ov.getByAlias("ciudad"));
        }
    }

    private static void contarJugadores(ODB odb) {
        System.out.println("\nNúmero total de jugadores:");
        Values val = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).count("nombre"));
        ObjectValues ov = val.nextValues();
        BigInteger total = (BigInteger) ov.getByAlias("nombre");
        System.out.println("Total jugadores: " + total.intValue());
    }

    private static void calcularEdadMedia(ODB odb) {
        System.out.println("\nEdad media de los jugadores:");
        try {
            Values val = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).avg("edad"));
            ObjectValues ov = val.nextValues();
            BigDecimal media = (BigDecimal) ov.getByAlias("edad");
            System.out.println("Edad media: " + media.floatValue());
        } catch (ArithmeticException e) {
            Values val = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).sum("edad").count("edad"));
            ObjectValues ov = val.nextValues();
            BigDecimal suma = (BigDecimal) ov.getByIndex(0);
            BigInteger cuenta = (BigInteger) ov.getByIndex(1);
            float media = suma.floatValue() / cuenta.floatValue();
            System.out.println("Por excepción, edad media calculada: " + media);
        }
    }

    private static void obtenerMaxMinEdad(ODB odb) {
        System.out.println("\nEdad máxima y mínima de los jugadores:");
        Values val = odb.getValues(((ValuesCriteriaQuery) new ValuesCriteriaQuery(Jugadores.class).max("edad", "edad_max")).min("edad", "edad_min"));
        ObjectValues ov = val.nextValues();
        BigDecimal maxima = (BigDecimal) ov.getByAlias("edad_max");
        BigDecimal minima = (BigDecimal) ov.getByAlias("edad_min");
        System.out.println("Edad máxima: " + maxima.intValue() + " - Edad mínima: " + minima.intValue());
    }

    private static void agruparPorCiudad(ODB odb) {
        System.out.println("\nNúmero de jugadores por ciudad:");
        Values groupby = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).field("ciudad").count("nombre").groupBy("ciudad"));
        while (groupby.hasNext()) {
            ObjectValues objetos = (ObjectValues) groupby.next();
            System.out.println("Ciudad: " + objetos.getByAlias("ciudad") + " - Jugadores: " + objetos.getByIndex(1));
        }
    }
}