package tasks;

import java.util.ArrayList;

import com.classroom.applicationactivity.ApplicationActivityAccessorInterface;

import helper.FileMoveCopyResponse;
import helper.FileUploadResponse;
import helper.Group;
import helper.HelpMessageManager;
import helper.NoteManager;
import helper.ServerDeleteResponse;
import helper.Student;
import util.MessageBank;

public interface OnTaskCompleteListener extends ApplicationActivityAccessorInterface {

    //Register device task
    public void onRegisterDeviceComplete(ArrayList<String> result);

    //Get chat messages task
    public void setAllChatGroupMessages(ArrayList<MessageBank> ml);


    //Delete from server
    public void serverFileDeleted(ServerDeleteResponse args);

    //Upload to server
    public void uploadFileToServerComplete(FileUploadResponse fur);

    //Get student list
    public void setStudentList(ArrayList<Student> st);

    //Update user groups from CreateGroupTask onPostExecute
    public void updateUserGroups(String group);

    //Add group members to group manager from GetGroupTask onPostExecute
    public void addToGroupManager(Group g);

    //Add notes from server to title build
    public void addNotes(NoteManager noteManager);

    //Set help messages
    public void setHelpMessages(HelpMessageManager hmm);

    //FileMoveCopy response
    public void onFileMoveCopyResponse(FileMoveCopyResponse s);

}
