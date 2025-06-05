package com.eduardo.model;

//Eu poderia muito bem criar uma abstração que envolvesse tanto o
//armazém quanto o caminhão, mas não consigo pensar em um nome bom pra ela
//e um simples "Entity" fica muito aberto, então não terá herança

public class Warehouse {
    //Capacidade fixa dos armazéns
    private final double capacity;

    //Uso da capacidade
    private double usedCapacity;

    public Warehouse(double capacity) {
        this.capacity = capacity;
    }

    //Cria uma cópia vazia exatamente igual ao armazém atual
    public Warehouse getEmptyCopy() {
        return new Warehouse(this.capacity);
    }

    //Tenta adicionar um novo pacote ao armazém
    public boolean addPackage(double newPackage) {
        if (this.usedCapacity + newPackage > this.capacity)
            return false;

        usedCapacity += newPackage;
        return true;
    }

    //Retorna a capacidade restante
    public double getRemainingCapacity() {
        return this.capacity - this.usedCapacity;
    }

    //Verifica se o armazém está cheio
    public boolean isFull() {
        return this.usedCapacity >= this.capacity;
    }

    public double getUsedCapacity() {
        return this.usedCapacity;
    }

    //Retorna a capacidade total
    public double getCapacity() {
        return this.capacity;
    }

    @Override
    public String toString() {
        return this.usedCapacity + "";
    }

}
