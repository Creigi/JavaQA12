package ru.netology.products;

public class Smartphone extends Product {
    private String nameSmartphone;
    private String maker;

    public Smartphone(int id, String name, int price, String nameSmartphone, String maker) {
        super(id, name, price);
        this.nameSmartphone = nameSmartphone;
        this.maker = maker;
    }

    @Override
    public boolean matches(String search) {
        if (super.matches(search)) {
            return true;
        } else {
            return maker.contains(search);
        }
    }
}
