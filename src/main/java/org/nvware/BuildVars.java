package org.nvware;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Custom build vars FILL EVERYTHING CORRECTLY
 * @date 20 of June of 2015
 */
public class BuildVars {

    public static final Boolean debug = false;
//    public static final Boolean useWebHook = false;//true;
//    public static final int PORT = 8443;
//    public static final String EXTERNALWEBHOOKURL = "your-external-url:" + PORT;
//    public static final String INTERNALWEBHOOKURL = "your-internal-url:" + PORT;
//    public static final String pathToCertificatePublicKey = "path/to/my/certkey.pem";
//    public static final String certificatePublicKeyFileName = "certkey.pem";
//
//    public static final String OPENWEATHERAPIKEY = "<your-api-key>";
//
//    public static final String DirectionsApiKey = "<your-api-key>";
//
//    public static final String TRANSIFEXUSER = "<transifex-user>";
//    public static final String TRANSIFEXPASSWORD = "<transifex-password>";

    public static final String pathToLogs = "./";


    //old//jdbc:mysql://mysql.storage.cloud.wso2.com:3306/telegrambotapi_enbank
    //old/The new driver class is com.mysql.cj.jdbc.Driver
//    public static final String linkDB = "jdbc:mysql://localhost:3306/telegrambotapi?useUnicode=true&characterEncoding=UTF-8&connectTimeout=0&socketTimeout=0&autoReconnect=true";
//    public static final String controllerDB = "com.mysql.jdbc.Driver";
    public static final String linkDB = "jdbc:mariadb://62.151.178.228:12345/telegrambotapi?useUnicode=true&characterEncoding=UTF-8&connectTimeout=0&socketTimeout=0&autoReconnect=true";
    public static final String controllerDB = "org.mariadb.jdbc.Driver";
    public static String userDB = "telegrambotapi"; //"telegrambotapi"
    public static String password ="Telegrambotapi@123"  ;// "Telegrambotapi@123"  //9g&cn9/gGO
    public static final String userDBDev = "admin";// "root"  pass=password
    public static final String passwordDev = "password";///9g&cn9/gGO
//    public static final String passwordDev = "";///9g&cn9/gGO
    ///// CREATE DATABASE `telegrambotapi` /*!40100 DEFAULT CHARACTER SET utf8 */;

}
