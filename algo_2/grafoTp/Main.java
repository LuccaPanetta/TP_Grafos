package grafoTp;

import java.util.*;
import javax.swing.JOptionPane;

public class Main {

    private static final Map<String, List<String>> conexiones = new HashMap<>();
    private static final List<String> estaciones = new ArrayList<>();

    static {
        AgregarLinea(Arrays.asList("Plaza de Mayo", "Peru", "Piedras", "Lima", "Saenz Peña", "Congreso", "Pasco",
                "Alberti", "Plaza Miserere", "Loria", "Castro Barros", "Rio de Janeiro", "Acoyte", "Primera Junta",
                "Puan", "Carabobo", "Flores", "San Pedrito")); // A
        AgregarLinea(Arrays.asList("Alem", "Florida", "Carlos Pellegrini", "Uruguay", "Callao B", "Pasteur",
                "Pueyrredon B", "Carlos Gardel", "Medrano", "Angel Gallardo", "Malabia", "Dorrego", "Lacroze",
                "Tronador", "Parque Chas", "Echeverria", "Rosas")); // B
        AgregarLinea(Arrays.asList("Retiro C", "San Martin", "Lavalle", "Diagonal Norte", "Avenida de Mayo", "Moreno",
                "Independencia C", "San Juan", "Constitucion")); // C
        AgregarLinea(Arrays.asList("Congreso de Tucuman", "Hernandez", "Olleros", "Ministro Carranza", "Palermo",
                "Scalabrini Ortiz", "Bulnes", "Aguero", "Pueyrredon D", "Facultad de Medicina", "Callao D",
                "Tribunales", "9 de Julio", "Catedral"));// D
        AgregarLinea(Arrays.asList("Retiro E", "Catalinas", "Correo Central", "Bolivar", "Belgrano", "Independencia E",
                "San Jose", "Entre Rios", "Pichincha", "Jujuy", "Urquiza", "Boedo", "La Plata", "Maria Moreno", "Mitre",
                "Medalla Milagrosa", "Varela", "Virreyes")); // E
        AgregarLinea(Arrays.asList("Facultad de Derecho", "Las Heras", "Santa Fe", "Cordoba", "Corrientes", "Once",
                "Venezuela", "Humberto I", "Inclan", "Caseros", "Parque Patricios", "Hospitales")); // H

        AgregarConexion("Catedral", "Peru");
        AgregarConexion("Catedral", "Bolivar");
        AgregarConexion("Peru", "Bolivar");
        AgregarConexion("Plaza Miserere", "Once");
        AgregarConexion("Santa Fe", "Pueyrredon D");
        AgregarConexion("Retiro C", "Retiro E");
        AgregarConexion("Diagonal Norte", "9 de Julio");
        AgregarConexion("Diagonal Norte", "Carlos Pellegrini");
        AgregarConexion("Avenida de Mayo", "Lima");
        AgregarConexion("Alem", "Correo Central");
        AgregarConexion("Corrientes", "Pueyrredon B");
        AgregarConexion("Humberto I", "Jujuy");
        AgregarConexion("Independencia C", "Independencia E");
        AgregarConexion("Carlos Pellegrini", "9 de Julio");
    }

    private static void AgregarLinea(List<String> linea) {
        for (int i = 0; i < linea.size(); i++) {
            String estacion = linea.get(i);
            if (!conexiones.containsKey(estacion)) {
                conexiones.put(estacion, new ArrayList<>());
                estaciones.add(estacion);
            }
            if (i > 0) {
                conexiones.get(estacion).add(linea.get(i - 1));
                conexiones.get(linea.get(i - 1)).add(estacion);
            }
        }
    }

    private static void AgregarConexion(String estacion1, String estacion2) {
        conexiones.get(estacion1).add(estacion2);
        conexiones.get(estacion2).add(estacion1);
    }

    public static void main(String[] args) {
        int n = estaciones.size();
        int[][] MatrizAdyacencia = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (String adyacente : conexiones.get(estaciones.get(i))) {
                int j = estaciones.indexOf(adyacente);
                MatrizAdyacencia[i][j] = 1;
            }
        }

        Grafitos grafo = new Grafitos(MatrizAdyacencia);
        String salida = JOptionPane.showInputDialog("Ingrese el nombre de la estacion de inicio");
        String llegada = JOptionPane.showInputDialog("Ingrese el nombre de la estacion de fin");
        int s, l;

        s = estaciones.indexOf(salida);
        l = estaciones.indexOf(llegada);

        ArrayList<Integer> a = new ArrayList<>();
        boolean encontrado = grafo.HallaCaminoNoIterativo(s, l, a);

        if (encontrado) {
            for (int i = 0; i < a.size(); i++) {
                System.out.println(estaciones.get(a.get(i)));
            }
        } else {
            System.out.println("Error");
        }
    }
}
