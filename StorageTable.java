/*
Debamita Saha
SOLAR ID#: 112494564
debamita.saha@stonybrook.edu
Homework #6
Course: CSE 214
Recitation number: R04
TA's Name: James Finn
*/

package Homework6;

import java.io.Serializable;
import java.util.*;

/**
 * This class is a fully-documented class named StorageTable.
 * The StorageTable is a database of Storages that are stored in a hash
 * table to provide constant time insertion and deletion. The id of the
 * Storage objects was used as the key for hashing. This class uses the
 * HashTable implementation provided by the Java API and it uses inheritance.
 * This class implements the Serializable interface.
 */
public class StorageTable extends
        Hashtable<Integer, Storage> implements Serializable {

    private static int serialVersionUID;

    /**
     * Brief:
     * Manually inserts a Storage object into the table using the
     * specified key.
     * Preconditions:
     * storageId ≥ 0 and does not already exist in the table.
     * Storage ≠ null
     * Postconditions:
     * The Storage has been inserted into the table with the indicated key.
     * @param storageId The unique int key for the Storage object.
     * @param storage The Storage object to insert into the table.
     * @throws IllegalArgumentException Thrown if storageId is less than 0
     * or it already exists in the table, or if Storage is null
     */
    public void putStorage(int storageId, Storage storage)
            throws IllegalArgumentException {
        try {
            if (storageId < 0) {
                throw new IllegalArgumentException();
            }
            if (this.contains(storageId)) {
                throw new IllegalArgumentException();
            }
            if (storage == null) {
                throw new IllegalArgumentException();
            }
            this.put(storageId, storage);
        } catch (IllegalArgumentException e) {
            System.out.println("storageId must be greater than or equal to " +
                    "0 and it should not already exist in the table. " +
                    "Storage should not be null.");
        }
    }

    /**
     * Brief:
     * Retrieve the Storage from the table having the indicated
     * storageID. If the requested storageID does not exist in the
     * StorageTable, return null.
     * @param storageID The int key of the Storage to retrieve from
     *                  the table.
     * @return A Storage object with the given key, null if the StorageID
     * does not exist.
     */
    public Storage getStorage(int storageID) {
            return this.get(storageID);
    }

    /**
     * A String toString representation of the StorageTable class
     * @return a String representation of the StorageTable class
     */
    public String toString() {
        return "String toString in the StorageTable class";
    }
}
