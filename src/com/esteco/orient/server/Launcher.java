//package com.esteco.orient.server;
//
//import com.orientechnologies.orient.server.OServer;
//import com.orientechnologies.orient.server.OServerMain;
//import com.orientechnologies.orient.server.config.OServerConfiguration;
//
///**
// * Created by mfrancia on 24/12/15.
// */
//public class Launcher {
//    public static void main(String[] args) throws Exception {
//        OServer server = OServerMain.create();
////        server.startup(new File("orientdb.xml"));
//        server.startup(
//                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
//                        + "<orient-server>"
//                        + "<network>"
//                        + "<protocols>"
//                        + "<protocol name=\"binary\" implementation=\"com.orientechnologies.orient.server.network.protocol.binary.ONetworkProtocolBinary\"/>"
//                        + "<protocol name=\"http\" implementation=\"com.orientechnologies.orient.server.network.protocol.http.ONetworkProtocolHttpDb\"/>"
//                        + "</protocols>"
//                        + "<listeners>"
//                        + "<listener ip-address=\"0.0.0.0\" port-range=\"2424-2430\" protocol=\"binary\"/>"
//                        + "<listener ip-address=\"0.0.0.0\" port-range=\"2480-2490\" protocol=\"http\"/>"
//                        + "</listeners>"
//                        + "</network>"
//                        + "<users>"
//                        + "<user name=\"root\" password=\"ThisIsA_TEST\" resources=\"*\"/>"
//                        + "</users>"
//                        + "<properties>"
//                        /*
//                        + "<entry name=\"orientdb.www.path\" value=\"C:/work/dev/orientechnologies/orientdb/releases/1.0rc1-SNAPSHOT/www/\"/>"
//                        + "<entry name=\"orientdb.config.file\" value=\"C:/work/dev/orientechnologies/orientdb/releases/1.0rc1-SNAPSHOT/config/orientdb-server-config.xml\"/>"
//                        */
//                        + "<entry name=\"server.cache.staticResources\" value=\"false\"/>"
//                        + "<entry name=\"log.console.level\" value=\"info\"/>"
//                        + "<entry name=\"log.file.level\" value=\"fine\"/>"
//                        //The following is required to eliminate an error or warning "Error on resolving property: ORIENTDB_HOME"
//                        + "<entry name=\"plugin.dynamic\" value=\"false\"/>"
//                        + "</properties>" + "</orient-server>");
//        server.activate();
//
//        System.out.println("\nIs server active? ==> " + server.isActive());
//
//
//
//
//        System.out.println("*************************** \n DB PROPERTIES:\n" +
//                "+ "+server.getDatabaseDirectory()+
//                "\n+ "+server.
//                +"\n ********************** \n");
//
////        ODatabaseDocumentTx db = null;
////        db = new ODatabaseDocumentTx ("plocal:/tmp/databases/d").create();
////        db.open("admin","admin");
//        //ODatabaseDocumentTx dbOpen = new ODatabaseDocumentTx("plocal:localhost/tmp/databases/petshop").open("admin", "admin");
//
//
//        server.shutdown();
//        System.exit(0);
//    }
//}
