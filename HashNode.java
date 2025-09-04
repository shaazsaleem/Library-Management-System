/**
 * Represents a node in a hash table, containing a key-value pair. The key is a
 * String of the book title representing the unique identifier of the node, and
 * the value is a Book object associated with the key.
 * 
 * @author ShaazSaleem
 * @since April 2024
 */
public class HashNode {
	public String key;
	public Book value;
	public HashNode next;

	/**
	 * Constructs a HashNode with the specified key and value.
	 *
	 * @param itemKey   The key of the node
	 * @param itemValue The value associated with the key
	 */
	public HashNode(String itemKey, Book itemValue) {
		this.key = itemKey;
		this.value = itemValue;
		this.next = null;
	}
}
