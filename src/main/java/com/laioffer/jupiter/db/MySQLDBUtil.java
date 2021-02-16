package com.laioffer.jupiter.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MySQLDBUtil {
    private static final String INSTANCE = "laioffer-database.ckj4znjqximz.us-west-1.rds.amazonaws.com"; // endpoint , if connect wit local db, can bo localhost
    private static final String PORT_NUM = "3306";
    private static final String DB_NAME = "jupiter";

    public static String getMySQLAddress() throws IOException {
        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = MySQLDBUtil.class.getClassLoader().getResourceAsStream(propFileName);
        // why output stream? file may be big file, so can read part by part.
        prop.load(inputStream);
        // prop is a hashtable, can see from the source code

        String username = prop.getProperty("user");
        String password = prop.getProperty("password");
        return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&autoReconnect=true&serverTimezone=UTC&createDatabaseIfNotExist=true",
                INSTANCE, PORT_NUM, DB_NAME, username, password);
    }
}

