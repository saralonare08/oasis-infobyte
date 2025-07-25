import java.util.*;

class LibraryUser {
    String name;
    String username;
    String password;
    ArrayList<String> issuedBooks = new ArrayList<>();

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter your name: ");
        this.name = sc.nextLine();
        System.out.print("Choose a username: ");
        this.username = sc.nextLine();
        System.out.print("Set your password: ");
        this.password = sc.nextLine();
        System.out.println("\nRegistration successful! Please log in.");
    }

    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        if (uname.equals(username) && pass.equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
        }
        return false;
    }

    public void issueBook(ArrayList<String> availableBooks) {
        Scanner sc = new Scanner(System.in);
        if (availableBooks.isEmpty()) {
            System.out.println("No books available to issue.");
            return;
        }
        System.out.println("\nAvailable Books:");
        for (int i = 0; i < availableBooks.size(); i++) {
            System.out.println((i + 1) + ". " + availableBooks.get(i));
        }
        System.out.print("Enter book number to issue: ");
        int bookNo = sc.nextInt();
        if (bookNo >= 1 && bookNo <= availableBooks.size()) {
            String book = availableBooks.remove(bookNo - 1);
            issuedBooks.add(book);
            System.out.println("Book issued: " + book);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void returnBook(ArrayList<String> availableBooks) {
        Scanner sc = new Scanner(System.in);
        if (issuedBooks.isEmpty()) {
            System.out.println("No books to return.");
            return;
        }
        System.out.println("\nYour Issued Books:");
        for (int i = 0; i < issuedBooks.size(); i++) {
            System.out.println((i + 1) + ". " + issuedBooks.get(i));
        }
        System.out.print("Enter book number to return: ");
        int bookNo = sc.nextInt();
        if (bookNo >= 1 && bookNo <= issuedBooks.size()) {
            String book = issuedBooks.remove(bookNo - 1);
            availableBooks.add(book);
            System.out.println("Book returned: " + book);
        } else {
            System.out.println("Invalid selection.");
        }
    }

    public void viewIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println("No books issued.");
        } else {
            System.out.println("\nIssued Books:");
            for (String book : issuedBooks) {
                System.out.println("- " + book);
            }
        }
    }
}

class Admin {
    private final String adminUsername = "admin";
    private final String adminPassword = "admin123";

    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter admin username: ");
        String uname = sc.nextLine();
        System.out.print("Enter admin password: ");
        String pass = sc.nextLine();
        if (uname.equals(adminUsername) && pass.equals(adminPassword)) {
            System.out.println("Admin login successful!");
            return true;
        } else {
            System.out.println("Invalid admin credentials.");
        }
        return false;
    }

    public void adminMenu(ArrayList<String> books) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Admin Panel ---");
            System.out.println("1. View All Books");
            System.out.println("2. Add Book");
            System.out.println("3. Remove Book");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (ch) {
                case 1:
                    if (books.isEmpty()) {
                        System.out.println("No books in the library.");
                    } else {
                        System.out.println("Books in Library:");
                        for (String book : books) {
                            System.out.println("- " + book);
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter book name to add: ");
                    String newBook = sc.nextLine();
                    books.add(newBook);
                    System.out.println("Book added.");
                    break;
                case 3:
                    if (books.isEmpty()) {
                        System.out.println("No books to remove.");
                        break;
                    }
                    System.out.println("Books in Library:");
                    for (int i = 0; i < books.size(); i++) {
                        System.out.println((i + 1) + ". " + books.get(i));
                    }
                    System.out.print("Enter book number to remove: ");
                    int bNo = sc.nextInt();
                    if (bNo >= 1 && bNo <= books.size()) {
                        String removed = books.remove(bNo - 1);
                        System.out.println("Book removed: " + removed);
                    } else {
                        System.out.println("Invalid selection.");
                    }
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting admin panel.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

public class DigitalLibrarySystem {
    static ArrayList<String> books = new ArrayList<>();
    static LibraryUser user = new LibraryUser();
    static Admin admin = new Admin();

    public static int getInput(int limit) {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (true) {
            try {
                choice = sc.nextInt();
                if (choice >= 1 && choice <= limit) {
                    return choice;
                } else {
                    System.out.println("Choose between 1 to " + limit);
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter valid integer input.");
                sc.next(); // Clear buffer
            }
        }
    }

    public static void main(String[] args) {
        // Initial books
        books.add("The Alchemist");
        books.add("Clean Code");
        books.add("Java Programming");

        System.out.println("******** Welcome to Digital Library ********");

        while (true) {
            System.out.println("\nChoose role:");
            System.out.println("1. Admin Login");
            System.out.println("2. User Register & Login");
            System.out.println("3. Exit");
            int choice = getInput(3);

            if (choice == 1) {
                if (admin.login()) {
                    admin.adminMenu(books);
                }
            } else if (choice == 2) {
                user.register();
                if (user.login()) {
                    boolean exit = false;
                    while (!exit) {
                        System.out.println("\n1. Issue Book\n2. Return Book\n3. View Issued Books\n4. Exit");
                        int opt = getInput(4);
                        switch (opt) {
                            case 1:
                                user.issueBook(books);
                                break;
                            case 2:
                                user.returnBook(books);
                                break;
                            case 3:
                                user.viewIssuedBooks();
                                break;
                            case 4:
                                exit = true;
                                System.out.println("Thank you for using the library.");
                                break;
                        }
                    }
                }
            } else {
                System.out.println("Exiting system. Goodbye!");
                break;
            }
        }
    }
}
