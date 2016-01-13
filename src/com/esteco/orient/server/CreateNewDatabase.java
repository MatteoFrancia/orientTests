package com.esteco.orient.server;

import com.orientechnologies.orient.core.serialization.serializer.object.OObjectSerializer;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.orientechnologies.orient.object.serialization.OObjectSerializerContext;
import com.orientechnologies.orient.object.serialization.OObjectSerializerHelper;
import java.io.File;
import java.math.BigInteger;

/**
 * Created by mfrancia on 13/01/16.
 */

public class CreateNewDatabase {

    public static void main(String[] args) {

        // Don't run without database location argument.
        if (args == null || args.length != 1) {
            System.out
                    .println("Expected single argument of folder path where to store the database.");
            return;
        }

        String path = args[0];
        if (path.length() < 2) {
            System.out.println("Path is too short: " + path);
            return;
        }

        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }

        OObjectDatabaseTx db = null;

        try {

            File folder = new File(path);
            if (folder.exists()) {
                throw new Exception("Database already exists on that path!");
            }

            // Create the folder as required.
            if (!folder.mkdirs()) {
                throw new Exception("Failed to create required folders:"
                        + folder.getAbsolutePath());
            }
            System.out.println("Creating database to: " + path);
            db = new OObjectDatabaseTx("plocal:" + path);
            // Avoid overwriting existing databases.
            if (db.exists()) {
                throw new Exception("Database already exists on that path!");
            }

            // Create database
            db.create();

            // Important code to serialize BigIntegers to Integers.
            // It's vital that this code is run before you register your classes.
            OObjectSerializerContext serializerContext = new OObjectSerializerContext();
            serializerContext
                    .bind(new OObjectSerializer<BigInteger, Integer>() {

                        public Integer serializeFieldValue(Class<?> itype,
                                                           BigInteger iFieldValue) {
                            return iFieldValue.intValue();
                        }

                        public BigInteger unserializeFieldValue(Class<?> itype,
                                                                Integer iFieldValue) {
                            return new BigInteger(iFieldValue.toString());
                        }

                    });
            OObjectSerializerHelper.bindSerializerContext(null,
                    serializerContext);

            // Tell OrientDB to automatically generate schema during class
            // registration, this saves us some code.
            db.setAutomaticSchemaGeneration(true);
            // Now register our classes...
            db.getEntityManager().registerEntityClasses(
                    "your.entity.package.goes.here");

            // Save the schema of our new database...
            db.getMetadata().getSchema().save();

            System.out.println("DONE");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

}