package com.dk.vehicle.model;

public enum VehicleCategory {
    Small_Car(25),
    Estate_Car(35),
    Van(50);

    private int price;

    VehicleCategory(int price) {
        this.price = price;
    }
    public int getPrice() {
        return this.price;
    }

}
