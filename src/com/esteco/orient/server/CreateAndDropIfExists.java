package com.esteco.orient.server;

import com.orientechnologies.orient.client.remote.OServerAdmin;

import java.io.IOException;

/**
 * Created by mfrancia on 13/01/16.
 */
public class CreateAndDropIfExists {

    private static final String dbUrl      = "remote:localhost/databaseName";
    private static final String dbUser     = "root";
    private static final String dbPassword = "rootPassword";

    public static void main(String[] args) throws IOException {

        OServerAdmin server = new OServerAdmin(dbUrl).connect(dbUser, dbPassword);
        if (server.existsDatabase("plocal")) {
            server.dropDatabase("plocal");
        } else {
            server.createDatabase("graph", "plocal");
        }
        server.close();
    }
}
