package com.company.service;

import com.company.protocol.Protocol;

import java.io.IOException;
import java.util.Scanner;

public class Service {
    private Scanner scanner;

    public Service() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            String urlAddress = scanner.nextLine();
            if (urlAddress.equals("q")) {
                break;
            }

            try {
                Protocol HTTPprotocol = new Protocol(urlAddress);
                HTTPprotocol.transfer();

                System.out.println("Received");
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }
    }
}
