package ContactApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContactServiceTest {
    
    @Test
    void testAddContactMethod() {
        ContactService contactService = new ContactService();
        
        // Create a contact with required fields (ID will be auto-generated)
        Contact contact = new Contact("Joseph", "Eaton", "1234567890", "320 East Wallace Kneeland");
        
        // Test adding a contact with all fields specified
        contactService.addContact(contact); // Add the contact
        
        // Check if the contact was added correctly
        assertFalse(contactService.getContactList().isEmpty());
        assertEquals(1, contactService.getNumContacts());
        assertEquals(contact.getContactID(), contactService.getContactList().get(0).getContactID());
        
        // Test adding a contact with null fields
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.addContact(null));
    }
    
    @Test
    void testRemoveContactMethod() {
        ContactService contactService = new ContactService();
        Contact contact = new Contact("Joseph", "Eaton", "1234567890", "320 East Wallace Kneeland");
        
        // Attempting invalid removal
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.removeContact("")); // Invalid ID
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.removeContact("12345678901")); // Too long ID
        
        // Add a contact
        contactService.addContact(contact);
        
        // Try removing a non-existing contact (ID mismatch)
        contactService.removeContact("123457");
        assertEquals(1, contactService.getNumContacts());
        
        // Successfully remove the contact
        contactService.removeContact(contact.getContactID());
        assertEquals(0, contactService.getNumContacts());
        assertTrue(contactService.getContactList().isEmpty());
    }
    
    @Test
    void testUpdateContactMethodErrors() {
        ContactService contactService = new ContactService();
        
        // Attempt invalid updates
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.updateContact("123456", "Joseph", 1)); // Missing contact
        
        // Create and add a contact
        Contact contact = new Contact("Joseph", "Eaton", "1234567890", "320 East Wallace Kneeland");
        contactService.addContact(contact);
        
        // Test invalid IDs and parameters
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.updateContact("12345678901", "Joseph", 1)); // Invalid ID
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.updateContact(null, "Joseph", 1)); // Null ID
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.updateContact("123456", null, 1)); // Null name
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.updateContact("123456", "Joseph", -1)); // Invalid selection
    }
    
    @Test
    void testUpdateContactMethod() {
        ContactService contactService = new ContactService();
        
        // Create and add a contact
        Contact contact = new Contact("Joseph", "Eaton", "1234567890", "320 East Wallace Kneeland");
        contactService.addContact(contact);
        
        // Test valid field updates
        contactService.updateContact(contact.getContactID(), "John", 1); // Update first name
        assertEquals("John Eaton", contactService.getContactList().get(0).getName());
        
        contactService.updateContact(contact.getContactID(), "Smith", 2); // Update last name
        assertEquals("John Smith", contactService.getContactList().get(0).getName());
        
        contactService.updateContact(contact.getContactID(), "9876543210", 3); // Update phone number
        assertEquals("9876543210", contactService.getContactList().get(0).getPhoneNumber());
        
        contactService.updateContact(contact.getContactID(), "500 West Wallace Kneeland", 4); // Update address
        assertEquals("500 West Wallace Kneeland", contactService.getContactList().get(0).getContactAddress());
        
        // Test invalid first name (exceeds length)
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.updateContact(contact.getContactID(), "JosephEaton", 1));
        
        // Test updating with null values for fields
        contactService.updateContact(contact.getContactID(), null, null, null, null); // Update multiple fields with nulls
        assertEquals("John Smith", contactService.getContactList().get(0).getName());
        assertEquals("9876543210", contactService.getContactList().get(0).getPhoneNumber());
        assertEquals("500 West Wallace Kneeland", contactService.getContactList().get(0).getContactAddress());
        
        // Verify that only valid updates persist
        assertEquals(1, contactService.getNumContacts());
    }

    @Test
    void testAddContactWithInvalidInput() {
        ContactService contactService = new ContactService();
        
        // Test adding a contact with invalid or null data
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.addContact(null));
    }
    
    @Test
    void testUpdateMultipleFields() {
        ContactService contactService = new ContactService();
        
        // Create and add a contact
        Contact contact = new Contact("Joseph", "Eaton", "1234567890", "320 East Wallace Kneeland");
        contactService.addContact(contact);
        
        // Test updating multiple fields at once
        contactService.updateContact(contact.getContactID(), "NewFirstName", "NewLastName", "1111111111", "New Address");
        assertEquals("NewFirstName NewLastName", contactService.getContactList().get(0).getName());
        assertEquals("1111111111", contactService.getContactList().get(0).getPhoneNumber());
        assertEquals("New Address", contactService.getContactList().get(0).getContactAddress());
    }
}