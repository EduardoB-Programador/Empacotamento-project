package com.eduardo.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MyFileReader {

    public static Map<String, Object> getFromFile() {
        String file = readCapacitiesFile();
        return file == null ? null : filterFileContent(file);
    }

    private static String readCapacitiesFile() {
        StringBuilder sb = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/eduardo/Capacities.txt"))) {
            String line;

            //Insere cada linha no StringBuilder
            while ((line = reader.readLine()) != null)
                sb.append(line);

        } catch (IOException e) {
            System.err.println("Algo errado ocorreu ao tentar acessar ou ler o arquivo.");
            return null;
        }

        return sb.toString();
    }

    private static Map<String,Object> filterFileContent(String fileContent) {
        Map<String, Object> contentsMap = new HashMap<>();

        int[] indexes = new int[3];

        try {
            indexes[0] = fileContent.indexOf("1-");
            indexes[1] = fileContent.indexOf("2-");
            indexes[2] = fileContent.indexOf("3-");
            
            //Percorre os indexes para procurar -1
            //caso ache, isso significa que não foi encontrado o index de algum dado
            for (int index : indexes)
                if (index == -1) throw new IllegalArgumentException();
            
        } catch (IllegalArgumentException e) {
            System.err.println("Não foi possivel identificar os pontos de separação dos dados dentro do arquivo.");
            return null;
        }

        Double truckCapacity, wareHouseCapacity;
        List<Double> containerVolumes;
        
        try {
            truckCapacity = extractSingleValue(fileContent.substring(indexes[0]));
            wareHouseCapacity = extractSingleValue(fileContent.substring(indexes[1]));
            containerVolumes = extractList(fileContent.substring(indexes[2]));
        } catch (NumberFormatException e) {
            System.err.println("Os dados inseridos dentro dos \"<>\" não são números.");
            return null;
        }
        
        contentsMap.put("truck", truckCapacity);
        contentsMap.put("warehouse", wareHouseCapacity);
        contentsMap.put("volumes", containerVolumes);
        
        return contentsMap;
    }

    private static Double extractSingleValue(String target) {
        int[] indexes = new int[2];
        
        //Pega o index do dado dentro dos "<>"
        indexes[0] = target.indexOf("<") +1;
        indexes[1] = target.indexOf(">");
        
        for (int index : indexes)
            if (index == -1) return null;
        
        return Double.parseDouble(target.substring(indexes[0], indexes[1]));
    }

    private static List<Double> extractList(String target) {
        List<Double> volumesList = new ArrayList<>();
        
        //Indexes do começo da lista até o final da lista
        int[] indexes = new int[2];
        indexes[0] = target.indexOf("[") +1;
        indexes[1] = target.indexOf("]");
        String newTarget = target.substring(indexes[0], indexes[1]);

        indexes[0] = 0;
        indexes[1] = newTarget.length() -1;

        while (indexes[0] < indexes[1]) {
            volumesList.add(extractSingleValue(newTarget.substring(indexes[0])));
            indexes[0] += newTarget.substring(indexes[0]).indexOf(">") +1;
        }

        return volumesList.stream().filter(Objects::nonNull).toList();
    }

}
