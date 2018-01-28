package objects_For_Items;

import java.io.Serializable;

import main.Main;

public class ItemGroup  implements Serializable {
	
	private static int uniqueID = 1;
	
	
	private int groupID;
	private String groupName;
	

	
	public ItemGroup(int groupID, String groupName) {
		this.groupID = groupID;
		this.groupName = groupName;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public static int getGroupIDByName(String name) {
		for (ItemGroup gr : Main.mainWindow.groupsList) {
			if (gr.getGroupName() == name)
				return gr.getGroupID();
		}
		return -1;
	}
	

}
