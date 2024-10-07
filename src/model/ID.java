package model;

public abstract class ID implements Printable {
    private static int counter = 0;
    private int id;

    public ID() {
        this.id = generateId();
    }

    public int geId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private static int generateId() {
        return ++counter;
    }
}
