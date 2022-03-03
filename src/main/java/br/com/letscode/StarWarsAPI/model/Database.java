package br.com.letscode.StarWarsAPI.model;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static List<Rebelde> rebeldes = new ArrayList<>();

    public static void adiciona(Rebelde rebelde){
        Database.rebeldes.add(rebelde);
    }

    public static List<Rebelde> getRebeldes() {
        return rebeldes;
    }
}
