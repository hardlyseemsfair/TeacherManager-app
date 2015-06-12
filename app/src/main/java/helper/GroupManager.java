package helper;

import java.util.ArrayList;

/**
 * Created by NAPOLEON on 5/8/2015.
 */
public class GroupManager {

     ArrayList<Group> groups;

    public GroupManager(){
        groups = new ArrayList<>();
    }

    public void addGroup(Group group){
        groups.add(group);
    }

    public boolean groupExists(String groupname){
        for(int i = 0; i < groups.size(); i++){
            if(groups.get(i).getGroupname().equalsIgnoreCase(groupname)) return true;
        }
        return false;
    }

    public String[] getGroupsAsArray(){
        String[] list = new String[groups.size()];
        for(int i = 0; i < groups.size(); i++){
            list[i] = groups.get(i).getGroupname();
        }
        return list;
    }

    public Group getGroup(String groupname){
        for(int i = 0; i < groups.size(); i++){
            if(groups.get(i).getGroupname().equalsIgnoreCase(groupname)) return groups.get(i);
        }
        return null;
    }


}
