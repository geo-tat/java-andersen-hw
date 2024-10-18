package com.andersen.hw.model;

import com.andersen.hw.util.IdGenerator;

public interface Identifiable {

    default int generateId() {
        return IdGenerator.generateId();
    }

    int getId();


}
