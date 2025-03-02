package ContactApp;

import java.util.ArrayList;

public class ContactService {

    private final ArrayList<Contact> myContacts = new ArrayList<>();

    public int getNumContacts() {
        return myContacts.size(); // No need for a separate numContacts variable
    }

    public ArrayList<Contact> getContactList() {
        return new ArrayList<>(myContacts); // Return a copy to avoid direct modification
    }

    // Add contact without manually passing an ID (ID is now generated in Contact)
    public void addContact(String fName, String lName, String phoneNumber, String contactAddress) {
        Contact newContact = new Contact(fName, lName, phoneNumber, contactAddress);
        myContacts.add(newContact);
    }

    // Remove a contact by ID
    public void removeContact(String contactID) {
        if (contactID == null || contactID.length() > 10) {
            throw new IllegalArgumentException("Invalid contact ID.");
        }

        if (myContacts.isEmpty()) {
            throw new IllegalArgumentException("There are no contacts saved.");
        }

        Contact toRemove = null;
        for (Contact c : myContacts) {
            if (c.getContactID().equals(contactID)) {
                toRemove = c;
                break;
            }
        }

        if (toRemove != null) {
            myContacts.remove(toRemove);
            System.out.println("Contact removed successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Update specific fields in a contact
    public void updateContact(String ID, String update, int selection) {
        if (ID == null || ID.length() > 10 || update == null || selection < 1 || selection > 4) {
            throw new IllegalArgumentException("Invalid input.");
        }

        Contact contactToUpdate = findContactByID(ID);
        if (contactToUpdate == null) {
            System.out.println("Contact not found.");
            return;
        }

        switch (selection) {
            case 1 -> {
                if (update != null) contactToUpdate.setFirstName(update);
            }
            case 2 -> {
                if (update != null) contactToUpdate.setLastName(update);
            }
            case 3 -> {
                if (update != null) contactToUpdate.setPhoneNumber(update);
            }
            case 4 -> {
                if (update != null) contactToUpdate.setContactAddress(update);
            }
        }
    }

    // Update multiple fields at once
    public void updateContact(String ID, String fName, String lName, String pNumber, String contactAddress) {
        if (ID == null || ID.length() > 10) {
            throw new IllegalArgumentException("Invalid contact ID.");
        }

        Contact contactToUpdate = findContactByID(ID);
        if (contactToUpdate == null) {
            System.out.println("Contact not found.");
            return;
        }

        if (fName != null) contactToUpdate.setFirstName(fName);
        if (lName != null) contactToUpdate.setLastName(lName);
        if (pNumber != null) contactToUpdate.setPhoneNumber(pNumber);
        if (contactAddress != null) contactToUpdate.setContactAddress(contactAddress);
    }

    // Helper method to find a contact by ID
    private Contact findContactByID(String contactID) {
        for (Contact c : myContacts) {
            if (c.getContactID().equals(contactID)) {
                return c;
            }
        }
        return null;
    }

	public void addContact(Contact contact) {
		 if (contact != null) {
		        myContacts.add(contact);
		    } else {
		        throw new IllegalArgumentException("Contact cannot be null.");
		    }
		
	}
}