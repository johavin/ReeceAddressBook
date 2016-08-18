import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 
 */

/**
 * @author johai
 *
 */
@RunWith(JUnit4.class)
public class ReeceAddressManagerTest {
	ReeceAddressManager addressManager = new ReeceAddressManager();
	// Address book #1
	HashMap<String, String> primaryAddressBook = new HashMap();
	// Address book #2
	HashMap<String, String> secondaryAddressBook = new HashMap();
	// Count of contacts in the first address book
	final int iPrimaryAddressBookSize = 5;
	// Count of the combined address book without the duplicates
	final int iCombinedMapSize = 9;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// setting some initial contacts
		primaryAddressBook.put("Bob Miles", "0414767665");
		primaryAddressBook.put("Jack Sparrow", "0414766000");
		primaryAddressBook.put("Tom Hanks", "0414769007");
		primaryAddressBook.put("Asess Barret", "0414769097");
		primaryAddressBook.put("King Parrot", "0414769097");
		// This is a duplicate contact
		secondaryAddressBook.put("Bob Miles", "0414767665");
		secondaryAddressBook.put("Bill Gates", "0414767665");
		secondaryAddressBook.put("Jason McCarthy", "041470965");
		secondaryAddressBook.put("Michale Jackson", "041470987");
		secondaryAddressBook.put("Jess Barret", "0414769097");
	}

	/**
	 * Test to see we can add a new Contact to the address book.
	 */
	@Test
	public void testAddUniqueNewContact() {
		String sName = "Bob Mill";
		String sContactNum = "123456";
		primaryAddressBook = addressManager.addNewContact(primaryAddressBook, sName, sContactNum);
		assertTrue(primaryAddressBook.containsKey(sName) && primaryAddressBook.containsValue(sContactNum));
		primaryAddressBook.remove(sName);
	}

	/**
	 * Test to add an existing contact to the address book.
	 */
	@Test
	public void testAddExistingContact() {
		String sName = "Bob Miles";
		String sContactNum = "123456";
		int iSize = primaryAddressBook.size();
		primaryAddressBook = addressManager.addNewContact(primaryAddressBook, sName, sContactNum);
		// asserting that the duplicate record was not updated
		assertEquals("Test to see that we cant add a duplicate contact ", primaryAddressBook.size(), iSize);
		primaryAddressBook.remove(sName);
	}

	/**
	 * Test adding a contact to the second address book.
	 */
	@Test
	public void testAddSecondaryAddressBook() {
		String sName = "Rami Miles";
		String sContactNum = "123456";
		primaryAddressBook = addressManager.addNewContact(secondaryAddressBook, sName, sContactNum);
		// asserting that the new name and contact details exists
		assertTrue("Test that we can update secondary address book",secondaryAddressBook.containsKey(sName) && secondaryAddressBook.containsValue(sContactNum));
		secondaryAddressBook.remove(sName);
	}

	/**
	 * Test to see if we can remove a contact.
	 */
	@Test
	public void testRemoveContact() {
		String sName = "Jack Sparrow";
		assertTrue("Asserting that the contact exist before removing:", primaryAddressBook.containsKey(sName));
		primaryAddressBook = addressManager.removeContact(primaryAddressBook, sName);
		// Asserting that the contact no more exist in the address book
		assertFalse("Asserting that the contact edoes not exist after removing:",primaryAddressBook.containsKey(sName));

	}

	/**
	 * Test see that there are no error if attempted to remove a contact that
	 * dont exist
	 */
	@Test
	public void testRemoveNonExistantContact() {
		String sName = "Dummy";
		int iSize = primaryAddressBook.size();
		assertFalse("Asserting that the contact does not exist before removing:",primaryAddressBook.containsKey(sName));
		primaryAddressBook = addressManager.removeContact(primaryAddressBook, sName);
		// Asserting that the size of the address books did not change after
		// removing a non existent contact
		assertFalse("Asserting that the contact edoes not exist after removing:",primaryAddressBook.containsKey(sName));
		assertEquals("Asserting that the size of the map is un changed after removal", iSize,primaryAddressBook.size());

	}

	/**
	 * test to print a single address book.
	 */
	@Test
	public void testPrintSingleAddressBook() {
		addressManager.setAddressBookForPrint(primaryAddressBook);
		assertEquals("Asserting that the size of the before printing. ", iPrimaryAddressBookSize,primaryAddressBook.size());
		addressManager.printAddressBook();
	}

	/**
	 * test for printing multiple address book and verify that there are no duplicates
	 */
	@Test
	public void testPrintMultipleAddressBook() {
		addressManager.setAddressBookForPrint(primaryAddressBook);
		addressManager.addNextAddressBook(secondaryAddressBook);
		Map<String, String> tempMap = addressManager.getAddressBookForPrint();
		//assert that the new combined list without the duplicates
		  assertEquals( "The size of the new combined map must be less than the duplicate record" , tempMap.size(), iCombinedMapSize);		 
		addressManager.printAddressBook();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

}
