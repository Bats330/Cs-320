package ContactApp;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Contact {
    private static final Set<String> usedIDs = new HashSet<>(); // Track used IDs
    private static final Random rand = new Random();

    private final String contactID;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String contactAddress;

    public Contact(String firstName, String lastName, String phoneNum, String conAddress) {
        this.contactID = generateUniqueId(); // Auto-generate unique ID

        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid First Name: Must be non-null and at most 10 characters");
        }
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid Last Name: Must be non-null and at most 10 characters");
        }
        if (phoneNum == null || phoneNum.length() != 10) { 
            throw new IllegalArgumentException("Invalid Phone Number: Must be exactly 10 digits");
        }
        if (conAddress == null || conAddress.length() > 30) { 
            throw new IllegalArgumentException("Invalid Address: Must be non-null and at most 30 characters");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.contactAddress = conAddress;
    }

    // Unique ID generator
    private static String generateUniqueId() {
        String uniqueID;
        do {
            uniqueID = String.format("%010d", rand.nextInt(1000000000)); // Always 10 digits
        } while (usedIDs.contains(uniqueID));

        usedIDs.add(uniqueID);
        return uniqueID;
    }

    // Getters
    public String getContactID() {
        return this.contactID;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNum;
    }

    public String getContactAddress() {
        return this.contactAddress;
    }

    // Setters
    public void setFirstName(String newFirstName) {
        if (newFirstName == null || newFirstName.length() > 10) {
            throw new IllegalArgumentException("Invalid first name: Must be at most 10 characters");
        }
        this.firstName = newFirstName;
    }

    public void setLastName(String newLastName) {
        if (newLastName == null || newLastName.length() > 10) {
            throw new IllegalArgumentException("Invalid last name: Must be at most 10 characters");
        }
        this.lastName = newLastName;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        if (newPhoneNumber == null || newPhoneNumber.length() != 10) {
            throw new IllegalArgumentException("Invalid phone number: Must be exactly 10 digits");
        }
        this.phoneNum = newPhoneNumber;
    }

    public void setContactAddress(String newContactAddress) {
        if (newContactAddress == null || newContactAddress.length() > 30) {
            throw new IllegalArgumentException("Invalid address: Must be at most 30 characters");
        }
        this.contactAddress = newContactAddress;
    }
}