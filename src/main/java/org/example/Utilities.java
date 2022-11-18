package org.example;

import java.util.UUID;

public class Utilities {
    //CLASE DONDE SE CREARAN TODOS LOS METODOS DE CREACION Y GESTION

    public static String generarIdRandom(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


}
