package cinema;

import java.util.Scanner;

public class Cinema {
    private static Scanner scanner = new Scanner(System.in);
    private static int rows;
    private static int columns;
    private static char[][] seats;
    private static int purchasedTickets;
    private static int currentIncome;

    public static void initialize() {
        System.out.print("Enter the number of rows:\n> ");
        rows = scanner.nextInt();
        System.out.print("Enter the number of seats in each row:\n> ");
        columns = scanner.nextInt();
        seats = new char[rows][columns];
        purchasedTickets = 0;
    }
    public static void printSeats() {
        System.out.print("\nCinema:\n ");
        for (int i = 1; i <= columns; i++) {
            System.out.print(" " + i);
        }
        for (int i = 1; i <= rows; i++) {
            System.out.print("\n");
            for (int j = 0; j <= columns; j++) {
                if (j == 0) {
                    System.out.print(i);
                } else if (seats[i - 1][j - 1] == 'B') {
                    System.out.print(" B");
                } else {
                    System.out.print(" S");
                }
            }
        }
        System.out.print("\n");
    }
    public static void buyTicket() {
        int row;
        int column;
        while(true) {
            System.out.print("\nEnter a row number:\n> ");
            row = scanner.nextInt();
            System.out.print("Enter a seat number in that row:\n> ");
            column = scanner.nextInt();
            if (!(row > 0 && row <= rows && column > 0 && column <= columns)) {
                System.out.println("Wrong input!");
            } else if (seats[row - 1][column - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                break;
            }
        }
        seats[row - 1][column - 1] = 'B';
        purchasedTickets++;

        System.out.print("\nTicket price: $");
        if (rows * columns > 60) {
            if (row <= rows / 2) {
                System.out.println(10);
                currentIncome += 10;
            } else {
                System.out.println(8);
                currentIncome += 8;
            }
        } else {
            System.out.println(10);
            currentIncome += 10;
        }
    }
    public static void statistics() {
        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        double p = (double)purchasedTickets / (double)(rows * columns) * 100;
        System.out.printf("Percentage: %.2f", p);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        int totalIncome;
        if (rows * columns > 60) {
            if (rows % 2 == 0) {
                totalIncome = rows / 2 * columns * 10 + rows / 2 * columns * 8;
            } else {
                totalIncome = rows / 2 * columns * 10 + (rows + 1) / 2 * columns * 8;
            }
        } else {
            totalIncome = rows * columns * 10;
        }
        System.out.println("Total income: $" + totalIncome);
    }

    public static void main(String[] args) {
        initialize();
        int command;
        do {
            System.out.print("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n> ");
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    printSeats();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        } while (command != 0);
    }
}