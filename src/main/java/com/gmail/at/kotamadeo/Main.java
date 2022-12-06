package com.gmail.at.kotamadeo;

import com.gmail.at.kotamadeo.configuration.TomcatStarter;

import static com.gmail.at.kotamadeo.configuration.TomcatStarter.getTomcatStarter;

public class Main {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        TomcatStarter tomcatStarter = getTomcatStarter(PORT);
        tomcatStarter.init();
    }
}
