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

/**
 * This a fully documented class named Storage that contains three
 * private data fields: int id, String client, String contents, along
 * with a public getter and setter method for each of these fields. This
 * class will be used to represent a storage box registered with the company.
 * This class should implement the Serializable interface.
 */
public class Storage implements Serializable {

    private int id;
    private String client;
    private String contents;
    private static long serialVersionUID;

    /**
     * A getter for the unique ID of the storage box.
     * @return an int id of the storage box
     */
    public int getID() {
        return id;
    }

    /**
     * A setter for the unique ID of the storage box.
     * @param id the new int id for the storage box
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * A getter for the name of the client storing the box with the company.
     * @return a String for the name of the client
     */
    public String getClient() {
        return client;
    }

    /**
     * A setter for the name of the client storing the box with the company.
     * @param client a new String name for the client
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     * A getter for a brief description of the contents of the box.
     * @return a String that describes the contents of the box
     */
    public String getContents() {
        return contents;
    }

    /**
     * A setter for the brief description of the contents of the box.
     * @param contents a new String that describes the contents
     *                 of the box
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * The default constructor for the Storage class
     */
    public Storage() {
    }

    /**
     * A loaded constructor with arguments for the Storage class
     * @param id an int that is the id of the storage box
     * @param client a String that is the name of the client storing
     *               the box with the company
     * @param contents a String that describes the contents
     *                 of the box
     */
    public Storage(int id, String client, String contents) {
        this.id = id;
        this.client = client;
        this.contents = contents;
    }

    /**
     * A toString method for the Storage class that returns the id,
     * the contents, and the client
     * @return a String representation of the int id, the String contents,
     * and the String client
     */
    public String toString() {
        return id + contents + client;
    }

    /**
     * A deep copy of the Storage class
     * @return a new Storage object with the int id, the String contents,
     * and the String client
     */
    public Storage deepCopy() {
        return new Storage(this.id, this.contents, this.client);
    }
}
