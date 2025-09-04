import java.util.Random;

/**
 * This class implements the Person object that is used as a user in the
 * LibraryManagementSystem class
 * 
 * @author ShaazSaleem
 * @since April 2024
 */
public class Person {
	private int libraryID;
	private String userName;
	private Book[] borrowedBooks;
	private int numBooksBorrowed;

	private static final int MIN_LIBRARY_ID = 1000;
	private static final int MAX_LIBRARY_ID = 9999;

	/**
	 * Constructs a Person object with the specified libraryID and username
	 * Initializes the borrowedBooks array with a maximum capacity of 5.
	 * 
	 * @param name - The name of the person
	 * 
	 */
	public Person(String name) {
		libraryID = generateRandomLibraryID();
		this.userName = name;
		this.borrowedBooks = new Book[5];
		this.numBooksBorrowed = 0;
	}

	/**
	 * Generates a random library ID for the person.
	 *
	 * @return A random library ID
	 */
	private int generateRandomLibraryID() {
		Random random = new Random();
		return random.nextInt(MAX_LIBRARY_ID - MIN_LIBRARY_ID + 1) + MIN_LIBRARY_ID;
	}

	/**
	 * Gets the ID of the user
	 * 
	 * @return libraryID of the user
	 */
	public int getLibraryID() {
		return libraryID;
	}

	/**
	 * Gets the username of the person.
	 *
	 * @return The username
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the username of the person.
	 *
	 * @param userName The new username
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Prints a string representation of the borrowed books array
	 * 
	 * @return String representation of the borrowed books array
	 */
	public String getBorrowedBooks() {
		String result = "Borrowed Books:\n";
		for (Book book : borrowedBooks) {
			if (book != null) {
				result += book + "\n";
			}
		}
		return result;
	}

	/**
	 * Adds a book to the list of books borrowed by the person. If there is space
	 * available in the borrowedBooks array, the book is added, and the number of
	 * books borrowed is incremented.
	 *
	 * @param b The book to be added
	 */
	public void addBorrowedBook(Book b) {
		for (int i = 0; i < borrowedBooks.length; i++) {
			if (borrowedBooks[i] == null) {
				borrowedBooks[i] = b;
				sortBorrowedBooks();
				numBooksBorrowed++;
				break;
			}
		}
	}

	/**
	 * Removes a book from the list of books borrowed by the person. If the book is
	 * found in the borrowedBooks array, it is removed, and the number of books
	 * borrowed is decremented.
	 *
	 * @param b The book to be removed
	 */
	public void removeBorrowedBook(Book b) {
		for (int i = 0; i < borrowedBooks.length; i++) {
			if (borrowedBooks[i].equals(b)) {
				borrowedBooks[i] = null;
				numBooksBorrowed--;
				break;
			}
		}
	}

	/**
	 * Returns the number of books currently borrowed by the person.
	 *
	 * @return The number of books borrowed
	 */
	public int getNumBooksBorrowed() {
		return numBooksBorrowed;
	}

	/**
	 * Sets the number of books borrowed by the person.
	 *
	 * @param numBooksBorrowed The new number of books borrowed
	 */
	public void setNumBooksBorrowed(int numBooksBorrowed) {
		this.numBooksBorrowed = numBooksBorrowed;
	}

	/**
	 * Checks if the person has borrowed a specific book.
	 *
	 * @param book The book to check for
	 * @return true if the person has borrowed the book, otherwise false
	 */
	public boolean hasBorrowedBook(Book book) {
		for (Book borrowedBook : borrowedBooks) {
			if (borrowedBook != null && borrowedBook.equals(book)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sorts the array of borrowed books in ascending order based on the book
	 * titles.
	 */
	public void sortBorrowedBooks() {
		for (int i = 1; i < borrowedBooks.length; i++) {
			if (borrowedBooks[i] != null) {
				Book key = borrowedBooks[i];
				int j = i - 1;
				while (j >= 0 && borrowedBooks[j] != null
						&& borrowedBooks[j].getTitle().compareTo(key.getTitle()) > 0) {
					borrowedBooks[j + 1] = borrowedBooks[j];
					j = j - 1;
				}
				borrowedBooks[j + 1] = key;
			}
		}
	}

	/**
	 * Returns a string representation of the Person object.
	 *
	 * @return A string representation of the object
	 */
	@Override
	public String toString() {
		return "Person:" + "\nlibraryID=" + libraryID + "\nUsername='" + userName + '\'' + "\n" + getBorrowedBooks()
		+ "numBooksBorrowed=" + numBooksBorrowed;
	}
}
