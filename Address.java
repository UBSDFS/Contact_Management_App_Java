/*
 * Ulysses Burden III
 * November 13, 2025
 * Assignment: Week 1 - Project- Show Composition
 * Description: This class represents a physical address with street, city, state, and zipcode. This class is used in composition with the Contact class.
 */

public class Address {

    // Address attributes
    private String street;
    private String city;
    private String state;
    private String zipcode;

    // Constructor
    public Address(String street, String city, String state, String zipcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    // Getters
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + zipcode;
    }
}
