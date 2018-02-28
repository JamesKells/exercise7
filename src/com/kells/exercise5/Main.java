package com.kells.exercise5;

import java.util.*;

public class Main {

    static Address address = new Address();

    public static void main(String[] args) {
        int choice = 1;

        String email, name;

        while (choice != 0) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.printf("What would you like to do? Type a number to do stuff");
                System.out.printf("%n1: Enter an email %n2: View emails %n3: Select email%n");
                choice = scan.nextInt();
            } catch (InputMismatchException ex) {
                choice = 5;
            }

            switch (choice) {
                case 1:
                    collect();
                    break;
                case 2:
                    address.view();
                    break;
                case 3:
                    address.search();
                    break;

                default:
                    Scanner scan = new Scanner(System.in);
                    String exit;
                    System.out.printf("Type a number please and press enter");
                    exit = scan.nextLine();
                    break;

            }
        }

    }

    public static void collect() {
        String email, name, contact, valid = "False";

        while (valid.equals("False")) {
            Scanner scan = new Scanner(System.in);
            boolean check = true;
            System.out.println("Enter a name: ");
            name = scan.nextLine();
            System.out.println("Enter your phone number: ");
            contact = scan.nextLine();
            contact = contact.replaceAll("[-+.^:,() ]", "");

            check = address.check(check, name, contact);
            if (name.equals("") || check == false) {
                valid = "False";
                System.out.println("Sorry not a valid name.");
            } else {
                System.out.println("Enter an email: ");
                email = scan.nextLine();
                Contact mail = new Contact(email, name, contact);
                address.save(mail.getContact(), mail.getPersonName());
                valid = "True";
            }
        }


    }

    public static class Person {
        String personEmail, personName;

        public Person() {
        }

        public Person(String email, String name) {
            System.out.println("Your name is " + name + " and your email is " + email);
            personEmail = email;
            personName = name;

        }

        public  String getPersonEmail() {
            return personEmail;
        }

        public String getPersonName() {
            return personName;
        }
    }

    public static class Contact extends Person{
        List contact;
        String personContact;

        public Contact(String email, String name, String personContact) {
            super(email, name);
        }

        public  String getPersonContact() {
            return personContact;
        }

        public List getContact() {
            String email = getPersonEmail();
            String number = getPersonContact();
            System.out.println(email);
            contact.add(email);
            contact.add(number);
            return contact;
        }
    }

    public static class Address {
        HashMap<String, List> book = new HashMap<>();
        List<String> keys = new ArrayList<>();

        public Address() {
        }

        public void save(List contact, String name) {
            book.put(name, contact);
            keys.add(name);
        }

        public void view() {
            boolean valid = false;
            for (int i = 0; i < keys.size(); i++) {
                System.out.println("Name: " + keys.get(i) + ", Address: " + book.get(keys.get(i)));
                Scanner scan = new Scanner(System.in);
                String exit;
                System.out.println("Press enter to continue");
                exit = scan.nextLine();
                valid = true;
            }

            if (valid == false){
                String exit;
                Scanner scan = new Scanner(System.in);
                System.out.println("No Emails to view(Press enter to continue).");
                exit = scan.nextLine();
            }
        }

        public void search() {
            Scanner scan = new Scanner(System.in);
            String choice = "", exit;
            int condtion = 1;

            if (keys.size() == 0) {
                System.out.println("No Emails to view(Press enter to continue).");
                exit = scan.nextLine();

            } else {
                while (condtion != 0) {
                    scan = new Scanner(System.in);
                    System.out.println("Type in the name of a persons email and press enter.");
                    choice = scan.nextLine();

                    if (choice.equals("")) {
                        condtion = 0;
                    } else {
                        if (book.get(choice) != null) {
                            System.out.println(choice + "'s email: " + book.get(choice));
                        } else {
                            System.out.println("Try again");
                            scan = new Scanner(System.in);
                            System.out.println(choice + " is not a valid user (Press enter to continue).");
                            exit = scan.nextLine();
                            choice = " ";
                        }
                    }

                }
            }
        }

        public boolean check(boolean check, String name, String number) {
            if (book.size() > 0) {
                if (book.containsKey(name)) {
                    check = false;
                    System.out.println("The name " + name + " is already taken");
                } else if ((book.get(name).get(1)) == number) {
                    check = false;
                    System.out.println("The number " + number + " is already taken");
                }

            }
            return check;
        }
    }
}
