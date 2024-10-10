package model;

import util.IdGenerator;

public interface Identifiable {

    default int generateId() {
        return IdGenerator.generateId();
    }

    int getId();


}
