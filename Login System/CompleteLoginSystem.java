// * useful imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Some important method to be implemented for Users class
 */
interface importantMethods {
    /* Normal methods */
    public boolean register(String uname, String pwd);

    public boolean login(String uname, String pwd);

    public boolean changePassword(String uname, String oldPwd, String newPwd);

    public boolean removeUser(String uname, String pwd);

    public boolean isUsernameExists(String uname);

    public void listUsers();

    /* File input/output methods */
    public void saveToFile();

    public void loadFromFile();
}

/*
 * Users class implementing importantMethods interface
 */
class Users implements importantMethods {

    /*
     * Calling default constructor, so that if any records are present in the
     * Database.txt file then we can load it in the hashmap
     */
    Users() {
        /*
         * Checking if file exists or not, if file dont't exists then exception will be
         * created.
         * 
         * * This check will be helpful when this program is running for the very first
         * time on any system or file is deleted accidently.
         */
        if (new File(filePath).exists()) {
            loadFromFile();
            System.out.println("Initial configurations done.....");
        }
    }

    /*
     * To store users:
     * - key -> username
     * - value -> password
     */
    HashMap<String, String> users = new HashMap<>();

    final String filePath = "C:/javaprg/Database.txt";

    /*
     * @method: to register as a user.
     * 
     * @args: username and password
     * 
     * @returns: boolean based on user registered or not
     */
    public boolean register(String uname, String pwd) {
        if (!users.containsKey(uname)) {
            users.put(uname, pwd);
            saveToFile();
            return true;
        }
        return false;
    }

    /*
     * @method: login as a user
     * 
     * @args: username and password
     * 
     * @returns: boolean based on valid credentials or not
     */
    public boolean login(String uname, String pwd) {
        if (users.containsKey(uname) && users.get(uname).equals(pwd)) {
            return true;
        }
        return false;
    }

    /*
     * @method: change old password with new passoword
     * 
     * @args: username, old password and new password to be changed
     * 
     * @returns: boolean based on valid information is provided or not
     */
    public boolean changePassword(String uname, String oldPwd, String newPwd) {
        if (users.containsKey(uname) && users.get(uname).equals(oldPwd)) {
            users.put(uname, newPwd);
            saveToFile();
            return true;
        }
        return false;
    }

    /*
     * @method: removes the user from the database
     * 
     * @args: username and password of the accouunt to be removed
     * 
     * @returns: boolean based on accouunt removed or not
     */
    public boolean removeUser(String uname, String pwd) {
        if (users.containsKey(uname) && users.get(uname).equals(pwd)) {
            users.remove(uname);
            saveToFile();
            return true;
        }
        return false;
    }

    /*
     * @method: checkes if username already exists or not
     * 
     * @args: username to be searched
     * 
     * @returns: boolean based on username found or not
     */
    public boolean isUsernameExists(String uname) {
        if (users.containsKey(uname)) {
            return true;
        }
        return false;
    }

    /*
     * @method: saves the user data to the file
     */
    public void saveToFile() {
        BufferedWriter bf = null;
        try {
            bf = new BufferedWriter(new FileWriter(filePath));
            for (Map.Entry<String, String> pair : users.entrySet()) {
                bf.write(pair.getKey() + ":" + pair.getValue());
                bf.newLine();
            }

            bf.flush();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving the file !!");
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (IOException e) {
                System.out.println("Writer file object not closed properly.");
                e.printStackTrace();
            }
        }
    }

    /*
     * @method: loads the user data from the file
     */
    public void loadFromFile() {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(":");
                users.put(splitted[0], splitted[1]);
            }
        } catch (Exception e) {
            System.out.println("Loading from file went wrong !!");
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("File loader object not closed properly");
                e.printStackTrace();
            }
        }

    }

    /*
     * @method: prints the hashmap of user data
     * 
     * @purpose: just for crosscheck for the data saving and loading
     */
    public void listUsers() {
        System.out.println(users);
    }
}

public class CompleteLoginSystem {

    /*
     * @method: takes a username as input and returns it
     * 
     * @args: Scanner class object
     * 
     * @returns: String which user inputted
     * 
     * @purpose: to implement DRY approach in the code
     */
    public static String takeUsername(Scanner sc) {
        System.out.print("Enter username: ");
        return sc.nextLine();
    }

    /*
     * @method: takes a password as input and returns it
     * 
     * @args: Scanner class object
     * 
     * @returns: String which user inputted
     * 
     * @purpose: to implement DRY approach in the code
     */
    public static String takePassword(Scanner sc) {
        System.out.print("Enter password: ");
        return sc.nextLine();
    }

    /*
     * @method: acknowledges the user based on the res parameter true or false
     * 
     * @args: takes 3 arguements result of boolean type and on reseult being true
     * onSuccess will be printed else onFailure will
     * 
     * @purpose: to make the code look clean
     */
    public static void acknowledgment(boolean res, String onSuccess, String onFailure) {
        if (res) {
            System.out.println(onSuccess);
        } else {
            System.out.println(onFailure);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        Users users = new Users();

        do {
            boolean res = false;
            System.out.print(
                    "\n------------------------\n1. Register\n2. Login\n3. Change Password\n4. Delete Account\n5. Print users\n6. Save to file\n7. Load from file\n0. Exit\n------------------------\nEnter your choice: ");
            option = sc.nextInt();

            switch (option) {
                case 1: // register case
                    sc.nextLine();
                    String uname = takeUsername(sc);

                    if (users.isUsernameExists(uname)) {
                        System.out.println("Username already exists !!");
                        break;
                    }

                    res = users.register(uname, takePassword(sc));
                    acknowledgment(res, "Registered successfully !!", "Invalid username");
                    break;

                case 2: // login case
                    sc.nextLine();
                    res = users.login(takeUsername(sc), takePassword(sc));
                    acknowledgment(res, "Login successfull !!", "Invalid username or passowrd");
                    break;

                case 3: // change pwd case
                    sc.nextLine();
                    uname = takeUsername(sc);

                    if (!users.isUsernameExists(uname)) {
                        System.out.println("Username not exists !!");
                        break;
                    }

                    System.out.print("Enter your old password: ");
                    String oldPwd = sc.nextLine();

                    System.out.print("Enter your new password: ");
                    String newPwd = sc.nextLine();

                    if (newPwd != oldPwd) {
                        res = users.changePassword(uname, oldPwd, newPwd);
                        acknowledgment(res, "Password changed successfully !!", "Something went wrong !!");
                    } else {
                        System.out.println("Passoword must not be same !!");
                    }

                    break;

                case 4: // remove account case
                    sc.nextLine();
                    uname = takeUsername(sc);

                    if (!users.isUsernameExists(uname)) {
                        System.out.println("Username not exists");
                        break;
                    }

                    res = users.removeUser(uname, takePassword(sc));
                    acknowledgment(res, "Account deleted !!", "Something went wrong !!");

                    break;

                case 5: // print users case
                    users.listUsers();
                    break;

                case 6: // save user data to file case
                    users.saveToFile();
                    break;

                case 7: // load user data from the file
                    users.loadFromFile();
                    break;

                default:
                    System.out.println("Invalid choice !!");
            }
        } while (option != 0);

    }
}