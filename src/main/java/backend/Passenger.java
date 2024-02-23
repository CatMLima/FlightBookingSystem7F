package backend;

//BST in case we want names on these

public class Passenger {
    private int kennitala; //do we want dashes?
    private String name;
    private int passportNumber;
    private String address;
    private String phoneNumber;

    //Constructor
    public Passenger(int kennitala, String name, int passportNumber, String address, String phoneNumber) {
        this.kennitala = kennitala; //setKennitala(kennitala); instead?
        this.name = name; //setName(name);?
        this.passportNumber = passportNumber; //setPassportnumber(passportNumber);?
        this.address = address; //setAddress(address);?
        this.phoneNumber = phoneNumber; //setPhonenumber(phoneNumber);?
    }
    public int getKennitala() {
        return kennitala;
    }
    public void setKennitala(int kennitala) {
        this.kennitala = kennitala;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
