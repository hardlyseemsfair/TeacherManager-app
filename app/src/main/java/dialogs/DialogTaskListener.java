package dialogs;

import com.classroom.applicationactivity.ApplicationActivityAccessorInterface;


import java.util.ArrayList;

import helper.FileData;
import helper.Group;
import helper.HelpMessage;
import helper.Note;
import helper.Student;

/**
 * Interface for functions returned from dialogs
 * @author JACK
 *
 */
public interface DialogTaskListener extends ApplicationActivityAccessorInterface{

    //Generated from RequestHelpDialog
    public void onHelpRequested(String filename, String value, String message);

    //Generated from DeleteFileDialog
    public void deleteFileTask(String filename, String dir);
    //public void updateFolderViewContents();

    //Chat fragment
    public void executeChatFragment(String name);

    //Move / Copy call to upload to server
    public void fileMoveCopy(String filename, String sourceDIR, String destDIR, String mask);


    //RenameFile server change task
    public void renameServerFile(String newFilename, String workingDIR, String currentFilename);

    //Create a new file
    public void createNewFile(String filename, String workingDIR);

    //Store opened file data
    public void storeFileInfo(FileData fd);

    //ViewGroupsDialog call back
    public void onGroupSelected(String groupname);

    //ViewGroupDialog
    public Group getGroup(String groupname);
    public void deleteGroup(String groupname);

    //NoteDialog
    public void addNote(Note n);

    //Note view dialog
    public void removeNote(Note n);

    //Help view dialog
    public void removeHelpMessage(HelpMessage hm);

    public void updateHelpFragment();

    public void handleCameraDir(String destination);

    //Send to dialog
    public void sendFileToSet(String sourcedir, String filename, String set);
    public void sendFileToTarget(String workingDIR, String filename, String flag);

}
