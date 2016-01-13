package com.esteco.orient.server;

import com.orientechnologies.orient.core.metadata.function.OFunction;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mfrancia on 13/01/16.
 */

public class Dbcreator {

    private static final String DB_PATH = "/Users/mfrancia/Desktop/prova";

    public static void main(String[] args) {
        // write your code here
        System.out.println("Creating MyAwesomeApp Db's schema...");

        String dblocation = DB_PATH;
        OrientGraphFactory factory = new OrientGraphFactory("plocal:" + dblocation,
                "admin", "admin");

        // if exists, delete the DB and start from scratch
        if (factory.exists  ()) {
            factory.drop();
            // reopen
            factory = new OrientGraphFactory("plocal:" + dblocation,
                    "admin", "admin");
        }

        // get graph db
        // "No transaction" version is used as schema creation functions below use their own Tx
        OrientGraphNoTx db = factory.getNoTx();

        // add User class
        OClass V = db.getRawGraph().getMetadata().getSchema().getClass("V");

        OClass UserClass = db.getRawGraph().getMetadata().getSchema().createClass("User", V);
        UserClass.createProperty("Email", OType.STRING).setMandatory(true).setNotNull(true);
        UserClass.createProperty("Password", OType.STRING);
        UserClass.createProperty("Firstname", OType.STRING);
        UserClass.createProperty("Lastname", OType.STRING);

        // START Adding functions
        CreateFunction_ValidateUser(db);
        // ... insert new function adds here
        // END Adding functions

        db.commit();
        db.shutdown(true);

        System.out.println("Done.");
    }

    private static void CreateFunction_ValidateUser(OrientGraphNoTx db) {
        // function name
        OFunction func = db.getRawGraph().getMetadata().getFunctionLibrary().createFunction("AddUser");
        func.setLanguage("javascript");

        // parameters
        List params = new ArrayList();
        params.add("email");
        params.add("password");
        params.add("firstname");
        params.add("lastname");

        func.setParameters(params);

        // code
        InputStream stream = ClassLoader.class.getResourceAsStream("/functions/AddUser.txt");
        func.setCode(convertStreamToString(stream));

        func.save();
    }

    private static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}