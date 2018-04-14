package model;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "person")
public class Person {

    public String firstName;
    public String lastName;
    private String emailAddress;

    public Person() {
    }

    public Person(String emailAddress, String firstName, String lastName) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Person{" +
                "emailAddress='" + emailAddress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
