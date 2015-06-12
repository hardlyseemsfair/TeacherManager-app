package contentfragments;


import com.classroom.applicationactivity.ApplicationActivityAccessorInterface;

import java.util.ArrayList;
import java.util.List;

import helper.NoteManager;
import helper.Student;
import util.MessageBank;


/**
 * Inbterface for callbacks generated by application fragments
 * @author JACK
 *
 */
public interface ApplicationFragmentListener extends ApplicationActivityAccessorInterface {


    //USed by both top and bottom bars
    public void setContentFragment(String foldername);

    //Generated by BottomBar fragment
//    public void onCameraClick();
//    public void restartFolderViewFragment();
//    public MessageBank getMessages(String groupname);

    //Generated by TitelBar fragment
    public void onLogout();
    public void onMenuClick();

    //Generated by FolderViewFragment
    public void onFileSelected(String currentDir, String currentFile);
    public void setSpinnerEntry(String title);
    public MessageBank getMessages(String groupname);

    //Generated by ChatViewFragment
    public void commitChatMessage(String message, String chatGroup);

    public void createNewStudentGroup(List<Student> st, String groupname);




}
