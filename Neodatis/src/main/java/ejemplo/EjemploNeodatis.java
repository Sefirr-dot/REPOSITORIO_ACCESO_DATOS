package ejemplo;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class EjemploNeodatis {
    public static void main(String[] args) {
        // Crear instancias para almacenar en BD
        Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 14);
        Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15);
        Jugadores j3 = new Jugadores("Mario", "baloncesto", "Guadalajara", 15);
        Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 14);

        // Abrir BD
        ODB odb = ODBFactory.open("neodatis.test");

        // Almacenamos objetos
        odb.store(j1);
        odb.store(j2);
        odb.store(j3);
        odb.store(j4);

        // Recuperamos todos los objetos
        Objects<Jugadores> objects = odb.getObjects(Jugadores.class);
        System.out.println(objects.size() + " Jugadores:");

        // Visualizar los objetos usando un bucle while
        int i = 1;
        while (objects.hasNext()) {
            Jugadores jug = objects.next();
            System.out.println((i++) + "\t: " + jug.getNombre() + "*" +
                    jug.getDeporte() + "*" + jug.getCiudad() + "*" + jug.getEdad());
        }

        // O también usando un bucle for-each
        for (Jugadores jug : objects) {
            System.out.println((i++) + "\t: " + jug.getNombre() + "*" +
                    jug.getDeporte() + "*" + jug.getCiudad() + "*" + jug.getEdad());
        }

        // Cerrar BD
        odb.close();
    }
}
