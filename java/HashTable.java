import java.util.ArrayList;
import java.util.List;

public class HashTable {
    // Hey guys this is a hash table implementation in Java. It uses separate chaining to handle collisions. The hash function is a simple modulo operation based on the size of the table.

    private List<String>[] table;

    public HashTable() {
        table = new ArrayList[10];
        for (int i = 0; i < table.length; i++) {
            table[i] = new ArrayList<>();
        }
    }

    //the hashcode is the key which is generated everytime you pass a value it to the hashfunction
    public int hashCodeFunction(String value) {
        int key = 0;

        for (int i = 0; i < value.length(); i++) {
            key += value.charAt(i);
        }

        return key % 10;
    }

    //This is the function to add value to the hashmap DS and also handle the collission case scenariao too
    public boolean addValue(String value) {
        int key = hashCodeFunction(value);
        table[key].add(value);
        return true;
    }

    //For checking/looking up a value in the hashmap on average case is constant time o(1) and for worstcase scenario is o(n).
    public String lookUpValue(String value) {
        int key = hashCodeFunction(value);

        if (table[key].contains(value)) {
            return value;
        }

        return "Sorry the word is not in the hash map";
    }

    // The deleted value returs the value that we deleted
    public String deleteValue(String value) {
        int key = hashCodeFunction(value);

        if (table[key].remove(value)) {
            return value;
        }

        return null;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        hashTable.addValue("demilade");
        hashTable.addValue("mary");
        hashTable.addValue("john");

        System.out.println(hashTable.lookUpValue("demilade"));
        System.out.println(hashTable.deleteValue("demilade"));
        System.out.println(hashTable.lookUpValue("demilade"));
    }
}
