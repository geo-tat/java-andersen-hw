package model;


public class Admin extends User{
    @Override
    void printRole() {
        System.out.println("Role is ADMIN");
    }

    public void checkTicket(Ticket ticket) {
        System.out.println("Checking ticket...");
    }

}
