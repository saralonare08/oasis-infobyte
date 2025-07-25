import java.util.*;

class BankAccount {
    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 10000f;
    int transactions = 0;
    String transactionHistory = "";

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter your name: ");
        this.name = sc.nextLine();
        System.out.println("\nEnter your Username: ");
        this.userName = sc.nextLine();
        System.out.println("\nEnter your Password: ");
        this.password = sc.nextLine();
        System.out.println("\nEnter your Account No.: ");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistration Successful! Please log in to your bank account.");
    }

    public boolean login() {
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while (!isLogin) {
            System.out.println("\nEnter your Username: ");
            String Username = sc.nextLine();
            if (Username.equals(userName)) {
                while (!isLogin) {
                    System.out.println("\nEnter your Password: ");
                    String Password = sc.nextLine();
                    if (Password.equals(password)) {
                        System.out.println("\nLogin successful!");
                        isLogin = true;
                    } else {
                        System.out.println("\nIncorrect password.");
                    }
                }
            } else {
                System.out.println("\nUser not found.");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        System.out.println("\nEnter the amount you want to Withdraw: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if (balance >= amount) {
                transactions++;
                balance -= amount;
                System.out.println("\nWithdraw successful.");
                String str = amount + " Rs Withdrawn\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nInsufficient balance.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void deposit() {
        System.out.println("\nEnter the amount you want to Deposit: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if (amount <= 10000f) {
                transactions++;
                balance += amount;
                System.out.println("\nDeposit Successful.");
                String str = amount + " Rs Deposited\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nSorry, the limit is 10000.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the Recipient's name: ");
        String recipient = sc.nextLine();
        System.out.println("Enter the amount to transfer: ");
        float amount = sc.nextFloat();
        try {
            if (balance >= amount) {
                if (amount <= 50000f) {
                    transactions++;
                    balance -= amount;
                    System.out.println("\nSuccessfully transferred to " + recipient);
                    String str = amount + " Rs Transferred to " + recipient + "\n";
                    transactionHistory = transactionHistory.concat(str);
                } else {
                    System.out.println("\nSorry, the limit is 50000.");
                }
            } else {
                System.out.println("\nInsufficient balance.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void checkBalance() {
        System.out.println("\nCurrent Balance: " + balance + " Rs.");
    }

    public void transHistory() {
        if (transactions == 0) {
            System.out.println("\nNo Transactions happened.");
        } else {
            System.out.println("\nTransaction History:\n" + transactionHistory);
        }
    }
}

public class ATMInterface {
    public static int takenIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;

        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                if (input >= 1 && input <= limit) {
                    flag = true;
                } else {
                    System.out.println("Choose a number between 1 to " + limit);
                }
            } catch (Exception e) {
                System.out.println("Enter only integer values.");
            }
        }
        return input;
    }

    public static void main(String args[]) {
        System.out.println("\n********************** WELCOME TO NATIONAL BANK **********************");
        System.out.println("\nChoose one option:");
        System.out.println("\n1. Register \n2. Exit");
        int choose = takenIntegerInput(2);

        if (choose == 1) {
            BankAccount b = new BankAccount();
            b.register();
            while (true) {
                System.out.println("\nEnter your choice:");
                System.out.println("\n1. Login \n2. Exit");
                int ch = takenIntegerInput(2);
                if (ch == 1) {
                    if (b.login()) {
                        System.out.println("\n********************** WELCOME BACK TO NATIONAL BANK, " + b.name + " **********************");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\nEnter your choice:");
                            System.out.println("1. Withdraw\n2. Deposit\n3. Transfer\n4. Check Balance\n5. Transaction History\n6. Exit");
                            int c = takenIntegerInput(6);
                            switch (c) {
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.transHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    System.out.println("\nThank you for using National Bank!");
                                    break;
                            }
                        }
                    } else {
                        System.exit(0);
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}
