package com.company.Server;

import java.util.Scanner;

public class ServerMain {

    public static void main(String[] args) {
        Hub rabbitsHub = new Hub(7732, 7733);
        Thread listenHub = new Thread(rabbitsHub);
        listenHub.start();

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Введите команду:");
            String message = input.nextLine();
            System.out.println(message);
        }
    }
}
