package com.company.testclient;

import java.awt.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Cliente[] clientes = {
                new Cliente(10, 10, 0, Color.BLUE),
                new Cliente(590, 10, -1, Color.RED),
                new Cliente(10, 400, -1, Color.GREEN),
                new Cliente(590, 400, -1, Color.YELLOW),
        };

        Executor executor = Executors.newFixedThreadPool(clientes.length);
        for(Cliente cliente : clientes) executor.execute(cliente::iniciar);
    }
}
