import java.util.HashSet;
import java.util.Set;

public class HashTable {
    // Efficient hash table implementation using separate chaining with HashSet buckets.
    // Features: polynomial rolling hash, dynamic resizing (doubling), and load factor management.

    private Set<String>[] table;
    private int capacity;
    private int size;
    private static final float LOAD_FACTOR_THRESHOLD = 0.75f;
    private static final int INITIAL_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        this.table = new HashSet[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new HashSet<>();
        }
    }

    /**
     * Polynomial rolling hash function for better distribution.
     * Uses prime multiplier 31 to reduce collisions.
     * Time Complexity: O(k) where k = length of the string
     */
    public int hashCodeFunction(String value) {
        int hash = 0;
        for (int i = 0; i < value.length(); i++) {
            hash = (hash * 31 + value.charAt(i));
        }
        return Math.abs(hash % capacity);
    }

    /**
     * Rehash the table when load factor exceeds threshold.
     * Doubles the capacity and reinserts all existing values.
     * Time Complexity: O(n·k) where n = number of items, k = avg string length
     * Amortized cost per insert: O(1)
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        Set<String>[] oldTable = table;
        capacity *= 2;
        table = new HashSet[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new HashSet<>();
        }

        size = 0; // will be incremented during reinsert
        for (Set<String> bucket : oldTable) {
            for (String value : bucket) {
                int key = hashCodeFunction(value);
                table[key].add(value);
                size++;
            }
        }
    }

    /**
     * Add a value to the hash table.
     * Automatically resizes if load factor > 0.75.
     * Time Complexity: Average O(1 + k), Worst Case O(n + k) if rehash occurs
     * Amortized: O(1)
     */
    public boolean addValue(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        int key = hashCodeFunction(value);

        // Only add if not already present (Set behavior)
        if (!table[key].contains(value)) {
            table[key].add(value);
            size++;

            // Check load factor and rehash if necessary
            if ((float) size / capacity > LOAD_FACTOR_THRESHOLD) {
                rehash();
            }
        }

        return true;
    }

    /**
     * Look up a value in the hash table.
     * Time Complexity: Average O(1 + k), Worst Case O(n + k)
     */
    public String lookUpValue(String value) {
        if (value == null || value.isEmpty()) {
            return "Sorry the word is not in the hash map";
        }

        int key = hashCodeFunction(value);

        if (table[key].contains(value)) {
            return value;
        }

        return "Sorry the word is not in the hash map";
    }

    /**
     * Delete a value from the hash table.
     * Time Complexity: Average O(1 + k), Worst Case O(n + k)
     */
    public String deleteValue(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        int key = hashCodeFunction(value);

        if (table[key].remove(value)) {
            size--;
            return value;
        }

        return null;
    }

    /**
     * Get the current size of the hash table.
     */
    public int size() {
        return size;
    }

    /**
     * Get the current capacity of the hash table.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Get the current load factor.
     */
    public float getLoadFactor() {
        return (float) size / capacity;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        System.out.println("=== Adding values ===");
        hashTable.addValue("demilade");
        hashTable.addValue("mary");
        hashTable.addValue("john");
        hashTable.addValue("amiola");
        hashTable.addValue("grace");

        System.out.println("\n=== Hash Table Stats ===");
        System.out.println("Size: " + hashTable.size());
        System.out.println("Capacity: " + hashTable.getCapacity());
        System.out.println("Load Factor: " + String.format("%.2f", hashTable.getLoadFactor()));

        System.out.println("\n=== Look Up ===");
        System.out.println("Lookup 'demilade': " + hashTable.lookUpValue("demilade"));
        System.out.println("Lookup 'jane': " + hashTable.lookUpValue("jane"));

        System.out.println("\n=== Delete ===");
        System.out.println("Delete 'demilade': " + hashTable.deleteValue("demilade"));
        System.out.println("Lookup 'demilade' after delete: " + hashTable.lookUpValue("demilade"));

        System.out.println("\n=== Final Stats ===");
        System.out.println("Size: " + hashTable.size());
        System.out.println("Capacity: " + hashTable.getCapacity());
        System.out.println("Load Factor: " + String.format("%.2f", hashTable.getLoadFactor()));
    }
}
