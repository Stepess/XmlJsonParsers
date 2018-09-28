package ua.training.entity;

public class Person {

    private String name;
    private String address;
    private int cash;
    private String education;

    public Person(String name, String address, int cash, String education) {
        this.name = name;
        this.address = address;
        this.cash = cash;
        this.education = education;
    }

    public int getCash() {
        return cash;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cash=" + cash +
                ", education='" + education + '\'' +
                '}';
    }
}
