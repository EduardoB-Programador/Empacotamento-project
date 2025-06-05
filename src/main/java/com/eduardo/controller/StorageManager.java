package com.eduardo.controller;

import com.eduardo.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StorageManager {
    private double[] volumes;
    private double truckCapacity;
    private double warehouseCapacity;

    public StorageManager(Map<String, Object> dataMap) {
        warehouseCapacity = (double) dataMap.get("warehouse");
        truckCapacity = (double) dataMap.get("truck");
        volumes = listToDoubleArrayParser(dataMap.get("volumes"));
        Arrays.sort(volumes); // Ordena de forma crescente
    }

    private double[] listToDoubleArrayParser(Object value) {
        @SuppressWarnings("unchecked")
        List<Double> values = (List<Double>) value;

        double[] target = new double[values.size()];

        for (int i = 0; i < target.length; i++)
            target[i] = values.get(i);

        return target;
    }

    public List<Truck> truckControl() {
        List<Truck> trucks = new ArrayList<>();
        for (int i = volumes.length - 1; i >= 0; i--) { // Itera do maior para o menor
            boolean added = false;
            for (Truck t : trucks) {
                if (t.addPackage(volumes[i])) {
                    added = true;
                    break;
                }
            }
            if (!added) {
                Truck newTruck = new Truck(truckCapacity);
                newTruck.addPackage(volumes[i]);
                trucks.add(newTruck);
            }
        }
        return trucks;
    }

    public List<Warehouse> warehouseControl() {
        List<Warehouse> warehouses = new ArrayList<>();
        for (int i = volumes.length - 1; i >= 0; i--) { // Itera do maior para o menor
            boolean added = false;
            for (Warehouse w : warehouses) {
                if (w.addPackage(volumes[i])) {
                    added = true;
                    break;
                }
            }
            if (!added) {
                Warehouse newWarehouse = new Warehouse(warehouseCapacity);
                newWarehouse.addPackage(volumes[i]);
                warehouses.add(newWarehouse);
            }
        }
        return warehouses;
    }


}
