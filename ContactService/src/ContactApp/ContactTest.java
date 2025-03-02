package ContactApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    void testContactValidCreation() {
        Contact contact = new Contact("Joseph", "Eaton", "1234567890", "320 East Wallace Kneeland");
        
        assertNotNull(contact.getContactID()); // Ensure ID is generated
        assertEquals("Joseph Eaton", contact.getName());
        assertEquals("1234567890", contact.getPhoneNumber());
        assertEquals("320 East Wallace Kneeland", contact.getContactAddress());
    }

    @Test
    void testContactIDUniqueness() {
        Contact contact1 = new Contact("Alice", "Smith", "1234567890", "123 Main St");
        Contact contact2 = new Contact("Bob", "Johnson", "9876543210", "456 Oak Ave");
        
        assertNotEquals(contact1.getContactID(), contact2.getContactID()); // IDs should be unique
    }

    @Test
    void testInvalidContactCreation() {
        assertThrows(IllegalArgumentException.class, () -> new Contact(null, "Eaton", "1234567890", "320 East Wallace Kneeland"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("Joseph", null, "1234567890", "320 East Wallace Kneeland"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("Joseph", "Eaton", null, "320 East Wallace Kneeland"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("Joseph", "Eaton", "1234567890", null));

        // Test length constraints
        assertThrows(IllegalArgumentException.class, () -> new Contact("ThisNameIsTooLong", "Eaton", "1234567890", "320 East Wallace Kneeland"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("Joseph", "ThisLastNameIsTooLong", "1234567890", "320 East Wallace Kneeland"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("Joseph", "Eaton", "12345678901", "320 East Wallace Kneeland")); // 11-digit phone number
        assertThrows(IllegalArgumentException.class, () -> new Contact("Joseph", "Eaton", "12345678", "320 East Wallace Kneeland")); // 8-digit phone number
        assertThrows(IllegalArgumentException.class, () -> new Contact("Joseph", "Eaton", "1234567890", "This address is way too long and should fail validation"));
    }

    @Test
    void testSetValidAttributes() {
        Contact contact = new Contact("Joseph", "Eaton", "1234567890", "320 East Wallace Kneeland");
        
        contact.setFirstName("Mike");
        contact.setLastName("Smith");
        contact.setPhoneNumber("0987654321");
        contact.setContactAddress("456 Oak Avenue");

        assertEquals("Mike Smith", contact.getName());
        assertEquals("0987654321", contact.getPhoneNumber());
        assertEquals("456 Oak Avenue", contact.getContactAddress());
    }

    @Test
    void testSetInvalidAttributes() {
        Contact contact = new Contact("Joseph", "Eaton", "1234567890", "320 East Wallace Kneeland");
        
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("ThisIsTooLong"));

        assertThrows(IllegalArgumentException.class, () -> contact.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("ThisLastNameIsTooLong"));

        assertThrows(IllegalArgumentException.class, () -> contact.setPhoneNumber(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhoneNumber("12345")); // Too short
        assertThrows(IllegalArgumentException.class, () -> contact.setPhoneNumber("12345678901")); // Too long

        assertThrows(IllegalArgumentException.class, () -> contact.setContactAddress(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setContactAddress("This address is way too long and should fail validation"));
    }

    @Test
    void testAllGetters() {
        Contact contact = new Contact("Joseph", "Eaton", "1234567890", "320 East Wallace Kneeland");

        assertNotNull(contact.getContactID()); // Check ID exists
        assertEquals("Joseph Eaton", contact.getName());
        assertEquals("Joseph", contact.getFirstName());
        assertEquals("Eaton", contact.getLastName());
        assertEquals("1234567890", contact.getPhoneNumber());
        assertEquals("320 East Wallace Kneeland", contact.getContactAddress());
    }
}
