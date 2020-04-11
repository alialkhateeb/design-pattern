package com.design.pattern.singleton;

import com.google.common.collect.Iterables;
import org.junit.Test;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static org.junit.Assert.assertEquals;

interface Database {
    int getPopulation(String name);
}

class SingletonDatabase implements Database {
    private Dictionary<String, Integer> capitals = new Hashtable<>();
    private static int instanceCount = 0;

    public static int getCount() {
        return instanceCount;
    }

    private SingletonDatabase() {
        instanceCount++;
        System.out.println("Init database...");

        try {
            File file = new File(
//                    SingletonDatabase.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                    "data.txt"
            );
            Path path = Paths.get(file.getPath());
            List<String> lines = Files.readAllLines(path);
            Iterables.partition(lines, 2).forEach(capital -> capitals.put(
                    capital.get(0).trim(),
                    Integer.parseInt(capital.get(1))
            ));


            System.out.println(capitals);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static SingletonDatabase getINSTANCE() {
        return INSTANCE;
    }

    public int getPopulation(String name) {
        return capitals.get(name);
    }
}

class SingletonRecordFinder {
    public int getTotalPopulation(List<String> names) {
        int result = 0;
        for (String name : names) {
            result += SingletonDatabase.getINSTANCE().getPopulation(name);
            return result;
        }
        return result;
    }
}

class ConfigurableRecordFinder {
    private Database database;

    public ConfigurableRecordFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulation(List<String> names) {
        int result = 0;
        for(String name : names)
            result += database.getPopulation(name);

        return result;
    }
}

class DummyDatabase implements Database{


    private Dictionary<String, Integer>  data = new Hashtable<>();

    public DummyDatabase() {
        data.put("alpha", 1);
        data.put("beta", 2);
        data.put("gamma", 3);
    }

    @Override
    public int getPopulation(String name) {
        return data.get(name);
    }
}

class Tests {

//    @Test
//    public void singletonTotalPopulationTest() {
//
//        SingletonRecordFinder rf = new SingletonRecordFinder();
//        List<String> names = List.of("Saudi Arabia");
//        int tp = rf.getTotalPopulation(names);
//
//        assertEquals(32000000, tp);
//    }

    @Test
    public void dependentPopulationTest(){
        DummyDatabase db = new DummyDatabase();
        ConfigurableRecordFinder config = new ConfigurableRecordFinder(db);
        assertEquals(4, config.getTotalPopulation(List.of("alpha", "gamma")));
    }
}

public class TestIssues {



    public static void main(String[] args) {
//        SingletonRecordFinder rf = new SingletonRecordFinder();
//        List<String> names = List.of("Saudi Arabia", "Max");
//        int tp = rf.getTotalPopulation(names);
//
//        assertEquals(32000000, tp);
    }
}
