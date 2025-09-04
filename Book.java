/**
 * Represents a book with its title, author, book ID, and availability status.
 * 
 * @author ShaazSaleem
 * @since April 2024
 */
public class Book {
	private String title;
	private String author;
	private int bookID;
	private boolean isAvailable;

	/**
	 * Constructs a Book object with the specified title, author, book ID, and
	 * availability status.
	 *
	 * @param title       - The title of the book
	 * @param author      - The author of the book
	 * @param bookID      - The unique ID of the book
	 * @param isAvailable - The availability status of the book
	 */
	public Book(String title, String author, int bookID, boolean isAvailable) {
		this.title = title;
		this.author = author;
		this.bookID = bookID;
		this.isAvailable = isAvailable;
	}

	/**
	 * Gets the title of the book.
	 *
	 * @return The title of the book
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the author of the book.
	 *
	 * @return The author of the book
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Gets the unique ID of the book.
	 *
	 * @return The unique ID of the book
	 */
	public int getBookID() {
		return bookID;
	}

	/**
	 * Checks if the book is available for borrowing.
	 *
	 * @return true if the book is available, false otherwise
	 */
	public boolean isAvailable() {
		return isAvailable;
	}

	/**
	 * Sets the availability status of the book.
	 *
	 * @param isAvailable The new availability status of the book
	 */
	public void setAvailability(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * Generates a string representation of the book object.
	 *
	 * @return A string containing the book's title, author, ID, and availability
	 *         status
	 */
	@Override
	public String toString() {
		String availability;
		if (isAvailable) {
			availability = "Book is available";
		} else {
			availability = "Book is unavailable";
		}
		return "Book:" + "\nTitle: " + title + "\nAuthor: " + author + "\nBook ID: " + bookID + "\nAvailability: "
				+ availability + "\n";
	}
}
