package model;

import util.IdGenerator;

abstract public class User implements Identifiable, Printable {
    private final int classId;

    public User() {
        this.classId = IdGenerator.generateId();
    }


    @Override
    public int getId() {
        return classId;
    }

    abstract void printRole();

}
