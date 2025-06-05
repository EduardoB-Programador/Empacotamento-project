package com.eduardo.model;

public class Truck {
    //Capacidade fixa do caminhão
    private final double capacity;

    //Uso da capacidade
    private double usedVolume;

    public Truck(double capacity) {
        this.capacity = capacity;
    }

    //Cria uma cópia vazia exatamente igual ao caminhão atual
    public Truck getEmptyCopy() {
        return new Truck(this.capacity);
    }

    // Verifica se o caminhão está cheio
    public boolean isFull() {
        return usedVolume >= capacity;
    }

    // Retorna o volume restante
    public double getRemainingVolume() {
        return capacity - usedVolume;
    }

    //Adiciona um novo pacote dentro do caminhão, caso não caiba retorna false
    public boolean addPackage(double newPackage) {
        if (this.usedVolume + newPackage > this.capacity)
            return false;

        this.usedVolume += newPackage;
        return true;
    }

    @Override
    public String toString() {
        return this.usedVolume + "";
    }
}
