import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * This class will basically carry out three main functions on an address book:
 * 1. Add a new contact to address book.
 * 2. Remove contact from an address book
 * 3. Print contact from address book. if there are more than one address book then print unique contacts
 * @author Aivin John
 *
 */
public class ReeceAddressManager {
	//This is the address book that will be printed
	private Map<String, String> mAddressBookForPrint;  
	/**
	 * The Method will allow user to add a new name and contact to an existing address book
	 * @param mAddressBook -- the address book to with a new contact has to be added
	 * @param sName - The name of the person whose contact details has to be added
	 * @param sContactNum -- The contact phone number
	 * @return Returns the updated address book  with the new contact
	 */
	public HashMap<String, String> addNewContact(HashMap<String, String> mAddressBook, String sName, String sContactNum) {
		//add the new contact to the address book
		mAddressBook.put(sName,sContactNum);
		//return the updated address book
		return mAddressBook;	
	}
	/**
	 * The method can be called to delete a contact from a selected address book.
	 * @param mAddressBook -- the address book from where a contact has to be removed.
	 * @param sName -- the contact to be removed
	 * @return -- the updated address book with the contact removed.
	 */
	public HashMap<String, String> removeContact(HashMap<String, String> mAddressBook, String sName) {
		//remove contact from the address book
		mAddressBook.remove(sName);
		return mAddressBook;
	}
	/**
	 * The method can be called to print the 
	 */
	public void printAddressBook() {
		System.out.println(mAddressBookForPrint);	
	}
	/**
	 * Getter method for getting the address book
	 * @return
	 */
	public Map<String, String> getAddressBookForPrint() {
		return mAddressBookForPrint;
	}
	/**
	 * This method will be called to sort the address book and prepare for printing.
	 * @param mAddressBookForPrint -- the address book to be merged.
	 */
	public void setAddressBookForPrint(HashMap<String, String> mAddressBookForPrint) {
		//this will sort the address book 
		Map<String, String> sortedAddressBook = new TreeMap<String, String>(mAddressBookForPrint);
		this.mAddressBookForPrint = sortedAddressBook;
	}
	/**
	 * The method will be called to merge a new address book to an existing address book.
	 * @param mSecondaryAddressBook
	 */
	public void addNextAddressBook(HashMap<String, String> mSecondaryAddressBook) {	
		mAddressBookForPrint.putAll(mSecondaryAddressBook);
	}

	

}
