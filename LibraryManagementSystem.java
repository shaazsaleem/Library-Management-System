import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * Represents a library management system that allows users to register, borrow,
 * and return books, as well as search for books in the catalog and view their
 * borrowed books.
 * 
 * @author ShaazSaleem
 * @since April 2024
 */
public class LibraryManagementSystem {
	private HashTable bookCatalog;
	private List<Person> userRegistry;

	/**
	 * Constructs a new LibraryManagementSystem with an empty book catalog and user
	 * registry.
	 */
	public LibraryManagementSystem() {
		bookCatalog = new HashTable(100);
		userRegistry = new ArrayList<>();
	}

	/**
	 * Adds a book to the catalog.
	 *
	 * @param book The book to be added to the catalog
	 */
	public void addBook(Book book) {
		bookCatalog.insert(book.getTitle(), book);
	}

	/**
	 * Removes a book from the catalog.
	 *
	 * @param title The title of the book to be removed
	 */
	public void removeBook(String title) {
		bookCatalog.remove(title);
	}

	/**
	 * Registers a new user by prompting for their name and generating a library ID.
	 */
	private void registerNewUser() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter name for the new user: ");
		String name = scanner.nextLine();

		Person newUser = new Person(name);
		userRegistry.add(newUser);
		System.out.println("New user registered successfully! User ID is: " + newUser.getLibraryID());
	}

	/**
	 * Allows a user to borrow a book by specifying their library ID and the title
	 * of the book.
	 */
	private void borrowBook() {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter your library ID: ");
		int libraryID = input.nextInt();
		input.nextLine();

		System.out.println("Enter the title of the book you want to borrow: ");
		String bookName = input.nextLine();

		Book book = bookCatalog.search(bookName);

		Person user = null;
		for (Person person : userRegistry) {
			if (person.getLibraryID() == libraryID) {
				user = person;
				break;
			}
		}

		if (book != null && user != null) {
			if (book.isAvailable()) {
				if (user.getNumBooksBorrowed() < 5) {
					user.addBorrowedBook(book);
					book.setAvailability(false);
					System.out.println("Book borrowed successfully.");
				} else {
					System.out.println("You have already borrowed the maximum number of books.");
				}
			} else {
				System.out.println("Sorry, the book is not available for borrowing.");
			}
		} else {
			System.out.println("Invalid library ID or book title.");
		}
	}

	/**
	 * Allows a user to return a book by specifying their library ID and the title
	 * of the book.
	 */
	public void returnBook() {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter your library ID: ");
		int libraryID = input.nextInt();
		input.nextLine();

		System.out.println("Enter the title of the book you want to return: ");
		String bookName = input.nextLine();

		Book book = bookCatalog.search(bookName);

		Person user = null;
		for (Person person : userRegistry) {
			if (person.getLibraryID() == libraryID) {
				user = person;
				break;
			}
		}

		if (book != null && user != null) {
			if (user.hasBorrowedBook(book)) {
				user.removeBorrowedBook(book);
				book.setAvailability(true);
				System.out.println("Book returned successfully.");
				return;
			} else {
				System.out.println("You have not borrowed this book.");
				return;
			}
		} else {
			System.out.println("Invalid library ID or book ID.");
		}
	}

	/**
	 * Displays the main menu of the library management system and handles user
	 * input.
	 */
	public void displayMainMenu() {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		boolean userLoggedIn = false;
		while (choice != 6) {
			System.out.println("\n***Main Menu:***");
			while (userLoggedIn == false) {
				System.out.println("Register a new user");
				registerNewUser();
				userLoggedIn = true;
			}
			System.out.println("\n1. Search for a Book");
			System.out.println("2. Borrow a Book");
			System.out.println("3. Return a Book");
			System.out.println("4. View user's borrowed books");
			System.out.println("5. Register a new user");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			choice = input.nextInt();

			if (choice == 1) {
				searchForBook();
			} else if (choice == 2) {
				borrowBook();
			} else if (choice == 3) {
				returnBook();
			} else if (choice == 4) {
				displayBorrowedBooks();
			} else if (choice == 5) {
				registerNewUser();
			} else if (choice == 6) {
				System.out.println("Exiting...");
			} else {
				System.out.println("Invalid choice. Please enter a valid option.");
			}
		}
	}

	/**
	 * Displays the borrowed books of a user specified by their library ID.
	 */
	private void displayBorrowedBooks() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter library ID of the user: ");
		int libraryID = input.nextInt();
		input.nextLine();

		Person user = findUser(libraryID);
		if (user != null) {
			System.out.println("Borrowed books for user " + user.getUserName() + ":");
			System.out.println(user.getBorrowedBooks());
		} else {
			System.out.println("User with library ID " + libraryID + " not found.");
		}
	}

	/**
	 * Searches for a book in the catalog by its title.
	 */
	private void searchForBook() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the book title to search: ");
		String bookName = input.nextLine();

		Book foundBook = bookCatalog.search(bookName);
		if (foundBook != null) {
			System.out.println("Book found:");
			System.out.println(foundBook);
		} else {
			System.out.println("Book with title " + bookName + " not found.");
		}
	}

	/**
	 * Finds a user in the registry by their library ID.
	 *
	 * @param libraryID The library ID of the user to find
	 * @return The user with the specified library ID, or null if not found
	 */
	private Person findUser(int libraryID) {
		for (Person user : userRegistry) {
			if (user.getLibraryID() == libraryID) {
				return user;
			}
		}
		return null;
	}

	/**
	 * The main method to start the library management system. The entry point of
	 * this program.
	 *
	 * @param args The command-line arguments (not used)
	 */
	public static void main(String[] args) {
		LibraryManagementSystem library = new LibraryManagementSystem();
		Book book1 = new Book("Dune", "Frank Herbert", 1, true);
		Book book2 = new Book("The Hunt for Red October", "Tom Clancy", 2, true);
		Book book3 = new Book("Fahrenheit 451", "Ray Bradbury", 3, false);
		Book book4 = new Book("Romeo and Juliet", "Shakespeare", 4, true);
		Book book5 = new Book("Odyssey", "Homer", 5, true);
		Book book6 = new Book("The Hobbit", "J.R.R. Tolkien", 6, false);
		Book book7 = new Book("The Martian", "Andy Weir", 7, true);
		Book book8 = new Book("The Catcher in the Rye", "J.D. Salinger", 8, false);
		Book book9 = new Book("1984", "George Orwell", 9, false);
		Book book10 = new Book("Moby Dick", "Herman Melville", 10, true);

		library.addBook(book1);
		library.addBook(book2);
		library.addBook(book3);
		library.addBook(book4);
		library.addBook(book5);
		library.addBook(book6);
		library.addBook(book7);
		library.addBook(book8);
		library.addBook(book9);
		library.addBook(book10);
		library.displayMainMenu();
	}
}
