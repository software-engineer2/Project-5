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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class StorageManager {
    private static StorageTable storage = new StorageTable();

    /**
     * This is a fully-documented class named StorageManager.
     * This class will allow the user to interact with the storage
     * database by listing the storage boxes occupied, allowing the
     * user to add or remove storage boxes, searching for a box by id,
     * and listing all the boxes for a user. In addition, the class provides
     * the functionality to load a saved (serialized) StorageTable or
     * create a new one if a saved table does not exist.
     *
     * On startup, the StorageManager checks to see if the
     * file storage.obj exists in the current directory. If it does,
     * then the file is loaded and deserialized into a StorageTable.
     * If the file does not exist, an empty StorageTable object is created
     * and used instead. In either case, the user is allowed to fully
     * interact with the storage table, inserting, removing, selecting,
     * and reading entries.
     *
     * When the user enters 'Q' to quit the program, the storage
     * table is serialized to the file storage.obj. That way,
     * the next time the program is run, the storages will
     * remain in the database and allow different users to manipulate
     * the storage records. If you would like to 'reset' the storage table,
     * use the "X" command to delete the file, if it exists, when the
     * program quits (the program first checks if the file exists, and
     * deletes it if it does; and it does not serialize the current
     * StorageTable upon exit).
     * @param args  contains the supplied command-line arguments as
     *              an array of String objects.
     * @throws IOException Thrown if an I/O exception of some sort
     * has occurred.
     * @throws ClassNotFoundException Thrown if a specified class cannot
     * be found in the classpath.
     * @throws FileNotFoundException Thrown if an attempt
     * to open the file denoted by a specified pathname has failed.
     */
    public static void main(String[] args) throws IOException,
            ClassNotFoundException, FileNotFoundException  {
        try {
            File f = new File("storage.obj");

            //if (f.exists() && !f.isDirectory()) {
        /*
        When the same application (or another application) runs again,
        you can initialize the member using the serialized data saved
         from step 2 so you don't have to recreate the object from scratch.
          To do this, you need to create an ObjectInputStream to read the
          data from, and then use the readObject method to read the hash
          from the stream.
        */
                FileInputStream file
                        = new FileInputStream("storage.obj");

                ObjectInputStream inStream = new ObjectInputStream(file);

                storage = (StorageTable) inStream.readObject();
                inStream.close();
                System.out.println("Hello, and welcome to " +
                        "Rocky Stream Storage Manager\n");

                boolean booleanVariable = true;
                Scanner input = new Scanner(System.in);
                while (booleanVariable) {
                    System.out.println(
                    "P - Print all storage boxes\n" +
                    "A - Insert into storage box\n" +
                    "R - Remove contents from a storage box\n" +
                    "C - Select all boxes owned by a particular client\n" +
                    "F - Find a box by ID and display its" +
                            " owner and contents\n" +
                    "Q - Quit and save workspace\n" +
                    "X - Quit and delete workspace");
                    System.out.print("Please select an option: ");
                    String userChoice = input.nextLine();
                    switch (userChoice) {
                        case "P":
                            System.out.println(
                            "Box#          Contents               " +
                                    "            Owner\n" +
                            "------------------------------------" +
                                    "----------------------------");
                            Enumeration<Integer> keys = storage.keys();
                            List<Integer> list = Collections.list(keys);
                            Collections.sort(list);
                            Collection<Storage> values = storage.values();
                            int i = 0;
                            while (i < storage.size()) {
                                Integer key = list.get(i);
                                Storage value = storage.getStorage(key);

                                System.out.printf
                                        ("%-14d%-35s%-30s", value.getID(),
                                        value.getContents(),
                                                value.getClient());
                                System.out.println();
                                i++;
                            }
                            break;
                        case "A":
                            System.out.print("Please enter id: ");
                            int id = input.nextInt();
                            input.nextLine();
                            System.out.print("Please enter client: ");
                            String client = input.nextLine();
                            System.out.print("Please Enter Contents: ");
                            String contents = input.nextLine();
                            Storage storage1
                                    = new Storage(id, client, contents);
                            storage.putStorage(id, storage1);
                            System.out.println("Storage " + id + " set!");
                            break;
                        case "R":
                            System.out.print("Please enter ID: ");
                            int boxID = input.nextInt();
                            input.nextLine();
                            storage.remove(boxID);
                            System.out.println("Box "
                                    + boxID + " is now removed.");
                            break;
                        case "C":
                            System.out.print(
                                    "Please enter the name of the client: ");
                            String findClient = input.nextLine();
                            System.out.println(
                            "Box#          Contents         " +
                                    "                  Owner\n" +
                            "-----------------------------------" +
                                    "-----------------------------");
                            Enumeration<Integer>
                                    enumerationIntegerKeys = storage.keys();
                            List<Integer> listInteger
                                    = Collections.list(enumerationIntegerKeys);
                            Collections.sort(listInteger);
                            Collection<Storage> collectionValues
                                    = storage.values();
                            int currentIndex = 0;
                            while (currentIndex < storage.size()) {
                                Integer integerKey
                                        = listInteger.get(currentIndex);
                                Storage value
                                        = storage.getStorage(integerKey);
                                if (value.getClient().equals(findClient)) {
                                    System.out.printf
                                            ("%-14d%-35s%-30s", value.getID(),
                                            value.getContents(),
                                                    value.getClient());
                                    System.out.println();
                                }
                                currentIndex++;
                            }
                            break;
                        case "F":
                            System.out.print("Please enter ID: ");
                            int boxId = input.nextInt();
                            input.nextLine();

                            System.out.println(
                            "Box " + storage.getStorage(boxId).getID() +
                            "\nContents: "
                                    + storage.getStorage(boxId).
                                    getContents() +
                            "\nOwner: "
                                    + storage.getStorage(boxId).getClient());
                            break;
                        case "X":
                            System.out.println
                                    ("Storage Manager is quitting," +
                                            " all data is being erased.");
                            storage.clear();
                            FileOutputStream newFileOutputStream
                                    = new FileOutputStream
                                    ("storage.obj");
                            ObjectOutputStream objectOutputStream
                                    = new ObjectOutputStream
                                    (newFileOutputStream);
                            // the following line will save
                            // the object in the file
                            objectOutputStream.writeObject(storage);
                            objectOutputStream.close();


                            if (f.delete()) {
                            } else {
                                System.out.println("The file doesn't exist.");
                            }


                            booleanVariable = false;
                            break;
                        case "Q":
//                        try {
                            System.out.print("Storage Manager is quitting, " +
                                    "current storage is saved " +
                                    "for next session.");
                            FileOutputStream newFile
                                    = new FileOutputStream
                                    ("storage.obj");
                            ObjectOutputStream outStream
                                    = new ObjectOutputStream(newFile);
                            // the following line will save
                            // the object in the file
                            outStream.writeObject(storage);
                            outStream.close();
                            booleanVariable = false;
                            break;
                    }
                }
                // missing code here can use StorageTable
            // constructed previously
           // }
            } catch(FileNotFoundException e){

                System.out.println("Hello, and welcome to " +
                        "Rocky Stream Storage Manager\n");

                boolean flag = true;
                Scanner scanner = new Scanner(System.in);
                while (flag) {
                    System.out.println(
                    "P - Print all storage boxes\n" +
                    "A - Insert into storage box\n" +
                    "R - Remove contents from a storage box\n" +
                    "C - Select all boxes owned by a particular client\n" +
                    "F - Find a box by ID and display its " +
                            "owner and contents\n" +
                    "Q - Quit and save workspace\n" +
                    "X - Quit and delete workspace");
                    System.out.print("Please select an option: ");
                    String userInput = scanner.nextLine();
                    switch (userInput) {
                        case "P":
                            System.out.println(
                            "Box#          Contents          " +
                                    "                 Owner\n" +
                            "-----------------------------------" +
                                    "-----------------------------");
                            Enumeration<Integer> keys = storage.keys();
                            List<Integer> list = Collections.list(keys);
                            Collections.sort(list);
                            Collection<Storage> values = storage.values();
                            int i = 0;
                            while (i < storage.size()) {
                                Integer key = list.get(i);
                                Storage value = storage.getStorage(key);

                                System.out.printf
                                        ("%-14d%-35s%-30s", value.getID(),
                                        value.getContents(),
                                                value.getClient());
                                System.out.println();
                                i++;
                            }
                            break;
                        case "A":
                            System.out.print("Please enter id: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Please enter client: ");
                            String client = scanner.nextLine();
                            System.out.print("Please Enter Contents: ");
                            String contents = scanner.nextLine();
                            Storage storage1
                                    = new Storage(id, client, contents);
                            storage.putStorage(id, storage1);
                            System.out.println("Storage " + id + " set!");
                            break;
                        case "R":
                            System.out.print("Please enter ID: ");
                            int boxID = scanner.nextInt();
                            scanner.nextLine();
                            storage.remove(boxID);
                            System.out.println("Box "
                                    + boxID + " is now removed.");
                            break;
                        case "C":
                            System.out.print(
                                    "Please enter the name of the client: ");
                            String findClient = scanner.nextLine();
                            System.out.println(
                            "Box#          Contents       " +
                                    "                    Owner\n" +
                            "--------------------------------" +
                                    "--------------------------------");
                            Enumeration<Integer>
                                    enumerationIntegerKeys = storage.keys();
                            List<Integer>
                                    listInteger
                                    = Collections.list
                                    (enumerationIntegerKeys);
                            Collections.sort(listInteger);
                            Collection<Storage>
                                    collectionValues = storage.values();
                            int currentIndex = 0;
                            while (currentIndex < storage.size()) {
                                Integer integerKey
                                        = listInteger.get(currentIndex);
                                Storage value
                                        = storage.getStorage(integerKey);
                                if (value.getClient().equals(findClient)) {
                                    System.out.printf
                                            ("%-14d%-35s%-30s", value.getID(),
                                            value.getContents(),
                                                    value.getClient());
                                    System.out.println();
                                }
                                currentIndex++;
                            }
                            break;
                        case "F":
                            System.out.print("Please enter ID: ");
                            int boxId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println(
                            "Box " + storage.getStorage(boxId).getID() +
                            "\nContents: "
                                    + storage.getStorage(boxId).getContents() +
                            "\nOwner: "
                                    + storage.getStorage(boxId).getClient());
                            break;
                        case "X":
                            System.out.println("Storage Manager " +
                                    "is quitting, all data is being erased.");
                            storage.clear();
                            File f = new File("storage.obj");
                            FileOutputStream newFileOutputStream3
                                    = new FileOutputStream
                                    ("storage.obj");
                            ObjectOutputStream objectOutputStream2
                                    = new ObjectOutputStream
                                    (newFileOutputStream3);
                            // the following line will save the
                            // object in the file
                            objectOutputStream2.writeObject(storage);
                            objectOutputStream2.close();

                            if (f.delete()) {
                            } else {
                                System.out.println("The file " +
                                        "does not exist.");
                            }
                            flag = false;
                            break;
                        case "Q":
                            try {
                                System.out.print("Storage Manager is " +
                                        "quitting, current storage is" +
                                        " saved for next session.");
                                FileOutputStream file
                                        = new FileOutputStream
                                        ("storage.obj");
                                ObjectOutputStream outStream
                                        = new ObjectOutputStream(file);
                                // the following line will save
                                // the object in the file
                                outStream.writeObject(storage);
                                outStream.close();
                                flag = false;
                                break;
                            } catch (FileNotFoundException exception) {
                                System.out.println("FileNotFoundException" +
                                        " in Q case");
                            } catch (IOException exception) {
                                System.out.print("IOException in Q case");
                            }
                    }
                }
            } catch(IOException e){
                System.out.println("IOException");
            } catch(ClassNotFoundException e){
                System.out.print("Class not found");
            }
        }
    }
