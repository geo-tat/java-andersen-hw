package com.andersen.hw.model;

public interface Printable {
  default void print() {
    System.out.println("Print content in console");
  }
}
