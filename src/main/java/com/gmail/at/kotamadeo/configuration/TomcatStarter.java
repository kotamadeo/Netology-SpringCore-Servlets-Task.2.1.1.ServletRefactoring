package com.gmail.at.kotamadeo.configuration;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.nio.file.Files;
import java.nio.file.Path;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Value
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
public class TomcatStarter {
    private static TomcatStarter tomcatStarter;
    private static final Connector CONNECTOR = new Connector();

    public static TomcatStarter getTomcatStarter(int port) {
        if (tomcatStarter == null) {
            tomcatStarter = new TomcatStarter();
            CONNECTOR.setPort(port);
        }
        return tomcatStarter;
    }


    public void init() {
        Tomcat tomcat = new Tomcat();
        tomcat.setConnector(CONNECTOR);
        try {
            Path baseDir = Files.createTempDirectory("tomcat");
            baseDir.toFile().deleteOnExit();
            tomcat.setBaseDir(baseDir.toAbsolutePath().toString());
            tomcat.getHost().setAppBase(".");
            tomcat.addWebapp("", ".");
            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
