package com.andrei.template.database;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;


//Edit it, and run it with CTRL+SHIFT+F10
//copy the table file into the database folder after
//make some constructors, add toString method

public class MyDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.andrei.template");
        schema.setDefaultJavaPackageDao("com.andrei.template.database");

        addSchema(schema);

        new DaoGenerator().generateAll(schema, "C:\\workspace\\UltimateAndroidAppTemplate\\app\\src\\main\\java");

    }

    private static void addSchema(Schema schema) {
        Entity note = schema.addEntity("Table_WhatsMyIpPOJO");
        note.setTableName("table_whatsmyip");
        note.addIdProperty();
        note.addStringProperty("YourFuckingIPAddress"); //.notNull().unique();   //<--- add them like this.
        note.addStringProperty("YourFuckingLocation");
        note.addStringProperty("YourFuckingHostname");
        note.addStringProperty("YourFuckingISP");
        note.addDateProperty("date");

    }

}