package com.eduardo.controller;

import java.util.*;
import com.eduardo.model.*;

public class Main {

    public static void main(String[] args) {
        Map<String, Object> dataMap = getData();

        StorageManager sm = new StorageManager(dataMap);

        List<Truck> trucks = sm.truckControl();
        List<Warehouse> warehouses = sm.warehouseControl();

        System.out.println(trucks.size() + " caminhões, aqui está o uso de cada caminhão: " + trucks);
        System.out.println(warehouses.size() + " armazéns, aqui está o useo de cada armazém: " + warehouses);

    }

    private static Map<String, Object> getData() {
        Scanner scan = new Scanner(System.in);
        Map<String, Object> dataMap = null;

        while (true) {
            System.out.println("1- Dados do arquivo \"Capacities.txt\"\n2- Inserção manual\n\nQual método você gostaria de adicionar dados?\n");
            String result = scan.nextLine();

            if (result.equals("1")) {
                if ((dataMap = MyFileReader.getFromFile()) == null) {
                    System.err.println("Você será encaminhado para realizar o inputManual.");
                    dataMap = manualInput();
                }
                break;
            } else if (result.equals("2")) {
                dataMap = manualInput();
                break;
            }
        }
        return dataMap;
    }

    public static Map<String, Object> manualInput() {
        Map<String, Object> valuesMap = new HashMap<>();
        List<Double> volumesList = new ArrayList<>();

        try (Scanner scan = new Scanner(System.in)) {
            System.out.print("Insira a capacidade fixa dos caminhões: ");
            valuesMap.put("truck", scan.nextDouble());
            scan.nextLine();

            System.out.print("Insira a capacidade fixa dos armazéns: ");
            valuesMap.put("warehouse", scan.nextDouble());
            scan.nextLine();

            while (true) {
                System.out.print("Insira o volume de UM container, para sair escreva qualquer letra: ");
                String temp = scan.nextLine();

                volumesList.add(Double.parseDouble(temp));
            }

        } catch (InputMismatchException | NumberFormatException e) {
            if (e instanceof NumberFormatException) {
                valuesMap.put("volumes", volumesList);
                return valuesMap;
            }

            System.err.println("Os dados inseridos não são números.\n\n\n\n\n\n\n\n\n\n\n\n");
            return manualInput();
        }

    }
}
