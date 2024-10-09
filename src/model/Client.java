package model;

public class Client extends User{
    @Override
    void printRole() {
        System.out.println("Role is CLIENT");
    }

    public void getTicket() {
        System.out.println("Client ticket information:");
    }
    @Override
    public void print() {
        System.out.println("Class ID = " + getId());
    }

}
