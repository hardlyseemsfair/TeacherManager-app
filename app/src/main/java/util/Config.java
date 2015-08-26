package util;

import java.io.File;

import handlers.UserDBHandler;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

/**
 * Config file containing application values
 * @author JACK
 *
 */

public class Config {

    /*
     * 											Server settings for the connect address of the php index and the remote user directories
     */
//    public static final String CONNECT_IP = "connect";
//    public static final String CONNECT_DIR = "connect_dir";
//
//    public static final String HOME_CONNECT_IP = "http://192.168.1.209";
//    public static final String LOCALHOST_CONNECT_IP = "localhost";
//    public static final String TESTHOT_CONNECT_IP = "http://192.168.43.34";
//
//    public static final String CONNECT_EXT = ":80/php_files/";
//    public static final String DIR_EXT = ":80/directorys/";

    public static final String CONNECT_IP = "http://192.168.1.209:80/php_files/";
    public static final String CONNECT_DIR = "http://192.168.1.209:80/directorys/";

//    public static final String CONNECT_IP = "http://10.30.18.214:80/php_files/";
//    public static final String CONNECT_DIR = "http://10.30.18.214:80/directorys/";
//


    /*
     *																		 Operation tags for the server
     */

    public static final String PREFS_FILE = "prefsfile";


    /*
     *													 Synchronize tags for specifying upload / download direction for syncing
     */
    public static final String SEND_TO_SERVER = "send_to_server";
    public static final String SEND_TO_DEVICE = "send_to_device";



    public static final String STUDENT = "Send to student..";
    public static final String GROUP = "Send to group...";
    public static final String STUDENT_SET = "Send to all students..";
    public static final String GROUP_SET = "Send to all groups...";


    /*
     * 																JSON node names, used mostly within JSONParser class
     */
    public static final String KEY_SUCCESS = "success";
    public static final String KEY_UID = "uid";
    public static final String KEY_NAME = "name";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_CREATED_AT = "created_at";
    public static final String ERROR_MSG = "error_msg";


    /*
     * 																Misc Tags used across various application classes
     */
    public static final String FILE_MOVE = "file_moved";
    public static final String FILE_COPY = "file_copy";



//    public static String getConnectIp(Activity activity){
//        SharedPreferences settings = activity.getSharedPreferences(PREFS_FILE, 0);
//        return settings.getString("SERVER_CONNECT", CONNECT_IP);
//    }
//
//    public static String getServerDirRoot(Activity activity){
//        SharedPreferences settings = activity.getSharedPreferences(PREFS_FILE, 0);
//        return settings.getString("SERVER_ROOT", HOME_SERVER_USER_ROOT_DIR);
//    }
//
	
	/*
	 * 															Accessor methods to provide device specific directory locations
	 */

    /**
     * Return the current user's directory based on application context
     * @param context application context
     * @return external storage directory
     */
    public static String getUserDirectoryRoot(Context context){
        UserDBHandler dbh = new UserDBHandler(context);
        return Environment.getExternalStorageDirectory().toString() + File.separator + dbh.getUserName();
    }

    /**
     * Return the current user's directory based on username
     * @param username the username provided
     * @return external storage directory
     */
    public static String getUserDirectoryRoot(String username){
        return Environment.getExternalStorageDirectory().toString() + File.separator + username;
    }

    /**
     * Return the current user's camera directoy based on provided username
     * @param username the current username
     * @return external storage directory
     */
    public static String getCameraDirectoryRoot(String username){
        return Environment.getExternalStorageDirectory().toString() + File.separator + username + File.separator + "Camera";
    }

    /**
     * provided the directory the user wishes to be working in
     * @param dir the name of the directory to be used
     * @param context application context
     * @return external storage directory
     */
    public static String getWorkingDirectory(String dir, Context context){
        if(dir.equalsIgnoreCase("camera")){
            return Environment.getExternalStorageDirectory().toString() + File.separator + Config.getCurrentUsername(context) + File.separator + "Camera";
        } else {
            return Environment.getExternalStorageDirectory().toString() + File.separator + dir;
        }
    }

    /**
     * Returns current logged in username
     * @param context application context
     * @return current user's username
     */
    public static String getCurrentUsername(Context context){
        UserDBHandler dbh = new UserDBHandler(context);
        return dbh.getUserName();
    }

    public static String formatGroupNameDisplay(String s){
        return s.replaceAll("_", " ");
    }



}
