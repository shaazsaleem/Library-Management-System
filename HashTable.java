/**
 * Represents a hash table data structure that stores key-value pairs. Each key
 * is associated with a Book object. 
 * The key is the book title and the value is the Book object itself
 * This HashTable uses separate chaining.
 * 
 * @author ShaazSaleem
 * @since April 2024
 */
public class HashTable {
	private HashNode[] table;
	private int capacity;

	/**
	 * Constructs a hash table with the specified initial capacity.
	 *
	 * @param initialCapacity The initial capacity of the hash table
	 */
	public HashTable(int initialCapacity) {
		capacity = initialCapacity;
		table = new HashNode[capacity];
	}

	/**
	 * Computes the hash code for a given key.
	 *
	 * @param key The key for which the hash code is computed
	 * @return The computed hash code
	 */
	public int hash(Object key) {
		int hash = key.hashCode() % capacity;
		if (hash < 0) {
			hash += capacity;
		}
		return hash;
	}

	/**
	 * Inserts a key-value pair into the hash table.
	 *
	 * @param bookName The name of the book (key)
	 * @param book     The Book object to be stored (value)
	 * @return true if the insertion is successful, false otherwise
	 */
	public boolean insert(String bookName, Book book) {
		int bucketIndex = hash(bookName);
		HashNode item = table[bucketIndex];
		HashNode previous = null;
		while (item != null) {
			if (bookName.equals(item.key)) {
				item.value = book;
				return true;
			}
			previous = item;
			item = item.next;
		}

		if (table[bucketIndex] == null) {
			table[bucketIndex] = new HashNode(bookName, book);
		} else {
			previous.next = new HashNode(bookName, book);
		}
		return true;
	}

	/**
	 * Removes a key-value pair from the hash table.
	 *
	 * @param bookName The name of the book (key) to be removed
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean remove(String bookName) {
		int bucketIndex = hash(bookName);
		HashNode item = table[bucketIndex];
		HashNode previous = null;

		while (item != null) {
			if (bookName.equals(item.key)) {
				if (previous == null && item.next == null) {
					table[bucketIndex] = null;
				} else {
					previous.next = item.next;
				}
				return true;
			}
			previous = item;
			item = item.next;

		}
		return false;
	}

	/**
	 * Searches for a book by name in the hash table.
	 *
	 * @param bookName The name of the book to search for
	 * @return The Book object associated with the specified name, or null if not
	 *         found
	 */
	public Book search(String bookName) {
		int bucketIndex = hash(bookName);
		HashNode item = table[bucketIndex];

		while (item != null) {
			if (bookName.equals(item.key)) {
				return item.value;
			}
			item = item.next;
		}
		return null;
	}
}
