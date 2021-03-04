package cinema;

import java.util.Scanner;

public class Cinema {

    public static void printSeats(char[][] seats, int n, int m) {
        System.out.println("Cinema:");
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void initializeSeats(char[][] seats, int n, int m) {
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        for (int i = 1; i < m + 1; i++) {
            seats[0][i] = digits[i];
        }

        for (int i = 1; i < n + 1; i++) {
            seats[i][0] = digits[i];
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                seats[i][j] = 'S';
            }
        }
    }

    public static void buyTicket(char[][] seats, int m, int n, int[] soldTickets) {
        boolean purchased = false;

        while(!purchased) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a row number:");

            int x = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int y = scanner.nextInt();

            System.out.println();

            if (x > 9 || y > 9) {
                System.out.println("Wrong input!");
                continue;
            }

            if (seats[x][y] == 'S') {
                if (n * m <= 60) {
                    System.out.println("Ticket price: $10");
                    seats[x][y] = 'B';
                    purchased = true;
                    break;
                } else {
                    int div = n / 2;
                    if (x <= div) {
                        System.out.println("Ticket price: $10");
                        seats[x][y] = 'B';
                        purchased = true;
                    } else {
                        System.out.println("Ticket price: $8");
                        seats[x][y] = 'B';
                        purchased = true;
                    }
                }
                soldTickets[0]++;
            } else {
                System.out.println("That ticket has already been purchased!");
            }
        }
        System.out.println();
    }
    private static void statistics(char[][] seats, int n, int m, int[] soldTickets, int total) {
        System.out.println("Number of purchased tickets: " + soldTickets[0]);

        int currentInc = 0;
        if (n * m <= 60) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (seats[i + 1][j + 1] == 'B') {
                        currentInc += 10;
                    }
                }
            }
        } else {
            int div = n / 2;
            for (int i = 0; i < div; i++) {
                for (int j = 0; j < m; j++) {
                    if (seats[i + 1][j + 1] == 'B') {
                        currentInc += 10;
                    }
                }
            }
            for (int i = div; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (seats[i + 1][j + 1] == 'B') {
                        currentInc += 8;
                    }
                }
            }
        }

        double percentage = (double) soldTickets[0] / (n * m) * 100;
        System.out.printf("Percentage: %.2f", percentage);
        System.out.print("%\n");
        System.out.println("Current income: $" + currentInc);
        System.out.println("Total income: $" + total + "\n");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int n = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int m = sc.nextInt();

        System.out.println();

        char[][] seats = new char[n + 1][m + 1];

        initializeSeats(seats, n, m);
        boolean finished = false;
        int[] soldTickets = new int[1];
        soldTickets[0] = 0;
        int totalIncome;
        if (n * m <= 60) {
            totalIncome = n * m * 60;
        } else {
            int div = n / 2;
            totalIncome = div * m * 10 + (n - div) * m * 8;
        }


        while(!finished) {
            System.out.println("1.Show the seats\n2. Buy a ticket\n" +
                    "3. Statistics\n0. Exit");
            int inp = sc.nextInt();
            System.out.println();

            switch (inp) {
                case 1:
                    printSeats(seats, n, m);
                    break;
                case 2:
                    buyTicket(seats, n, m, soldTickets);
                    break;
                case 3:
                    statistics(seats, n, m, soldTickets, totalIncome);
                    break;
                case 0:
                    finished = true;
                    break;
            }
        }
    }


}