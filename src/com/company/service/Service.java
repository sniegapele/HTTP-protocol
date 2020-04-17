package com.company.service;

import com.company.protocol.Protocol;

import java.io.IOException;
import java.util.Scanner;

public class Service {
    private Scanner scanner;

    public Service() {
        this.scanner = new Scanner(System.in);
    }

    public void run() throws IOException {
        while (true) {
            String urlAddress = scanner.nextLine();
            if (urlAddress.equals("q")) {
                break;
            }

            Protocol HTTPprotocol = new Protocol(urlAddress);
            HTTPprotocol.transfer();

            System.out.println("Received");
        }
    }
}
