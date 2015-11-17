package com.classroom.applicationactivity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.logging.Logger;

import helper.HelpMessageManager;
import helper.NoteManager;
import helper.Student;
import util.SessionLog;


/**
 * High level interface for generic Activity accessors, should not be implemented directly but extended through more targetted interfaces
 * @author JACK
 *
 */
public interface ApplicationActivityAccessorInterface {

    //Activity accessors
    public String getUsername();
    public ArrayList<String> getUserGroups();
    public ArrayList<String> getDirectories();
    public ArrayList<Student> getStudentList();
    public HelpMessageManager getHelpMessages();
    public void hide_keyboard(Activity activity);

    //Note fragment
    public NoteManager getNoteManager();

    public SessionLog getSessionLog();



    //Service check
    public boolean isMyServiceRunning(Class<?> serviceClass);

}
