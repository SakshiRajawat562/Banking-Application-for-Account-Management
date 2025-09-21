import java.util.Scanner;

class BankAccount {
    static long nextAccountNumber;
    static {
        nextAccountNumber = 200000;
        System.out.println("Bank System Started");
    }

    long accountNumber;
    String holder;
    double balance;
    String email;
    String phone;

    {
        accountNumber = ++nextAccountNumber;
    }

    BankAccount(String name, double opening, String mail, String ph) {
        holder = name;
        balance = opening;
        email = mail;
        phone = ph;
        System.out.println("New account created for " + name);
        System.out.println("Your Account Number is : "+accountNumber);
    }

    void addMoney(double amt) {
        if (amt > 0) {
            balance += amt;
            System.out.println("Deposited: " + amt + " | Balance: " + balance);
        } else System.out.println("Enter a positive amount");
    }

    void takeMoney(double amt) {
        if (amt > 0) {
            if (amt <= balance) {
                balance -= amt;
                System.out.println("Withdrawn: " + amt + " | Balance: " + balance);
            } else System.out.println("Insufficient balance");
        } else System.out.println("Enter a positive amount");
    }

    void changeContact(String mail, String ph) {
        email = mail;
        phone = ph;
        System.out.println("Contact updated");
    }

    void printInfo() {
        System.out.println("\n---- Account Details ----");
        System.out.println("Number : " + accountNumber);
        System.out.println("Name   : " + holder);
        System.out.println("Balance: " + balance);
        System.out.println("Email  : " + email);
        System.out.println("Phone  : " + phone);
        System.out.println("------------------------");
    }
}

class BankMenu {
    BankAccount[] records = new BankAccount[50];
    int size = 0;
    Scanner sc = new Scanner(System.in);

    void openAccount() {
        if (size >= records.length) {
            System.out.println("No space for new accounts");
            return;
        }
        System.out.print("Name: ");
        String n = sc.nextLine();
        System.out.print("Initial amount: ");
        double bal = sc.nextDouble();
        sc.nextLine();
        System.out.print("Email: ");
        String e = sc.nextLine();
        System.out.print("Phone: ");
        String p = sc.nextLine();
        records[size++] = new BankAccount(n, bal, e, p);
    }

    BankAccount search(long no) {
        for (int i = 0; i < size; i++)
            if (records[i].accountNumber == no) return records[i];
        return null;
    }

    void depositToAccount() {
        System.out.print("Account No: ");
        long no = sc.nextLong();
        System.out.print("Amount: ");
        double a = sc.nextDouble();
        sc.nextLine();
        BankAccount b = search(no);
        if (b != null) b.addMoney(a);
        else System.out.println("Account not found");
    }

    void withdrawFromAccount() {
        System.out.print("Account No: ");
        long no = sc.nextLong();
        System.out.print("Amount: ");
        double a = sc.nextDouble();
        sc.nextLine();
        BankAccount b = search(no);
        if (b != null) b.takeMoney(a);
        else System.out.println("Account not found");
    }

    void viewAccount() {
        System.out.print("Account No: ");
        long no = sc.nextLong();
        sc.nextLine();
        BankAccount b = search(no);
        if (b != null) b.printInfo();
        else System.out.println("Account not found");
    }

    void updateAccount() {
        System.out.print("Account No: ");
        long no = sc.nextLong();
        sc.nextLine();
        BankAccount b = search(no);
        if (b != null) {
            System.out.print("New email: ");
            String e = sc.nextLine();
            System.out.print("New phone: ");
            String p = sc.nextLine();
            b.changeContact(e, p);
        } else System.out.println("Account not found");
    }

    void runMenu() {
        int ch;
        do {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Open New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Account Info");
            System.out.println("5. Update Contact Info");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1 -> openAccount();
                case 2 -> depositToAccount();
                case 3 -> withdrawFromAccount();
                case 4 -> viewAccount();
                case 5 -> updateAccount();
                case 6 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice");
            }
        } while (ch != 6);
    }

    public static void main(String[] args) {
        new BankMenu().runMenu();
    }
}