package com.company;

import com.company.service.Service;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Service service = new Service();
        service.run();
    }
}
