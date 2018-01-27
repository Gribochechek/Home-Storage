package objects_For_Items;

import java.io.Serializable;

import main.Main;

public class AutomotiveParts_Make implements Serializable {

	private int makeID;
	private String makeName;

	public static int getMakeIDByName(String name) {
		for (AutomotiveParts_Make mk : Main.mainWindow.makesList) {
			if (mk.getMakeName() == name)
				return mk.getMakeID();
		}
		return -1;
	}

	public AutomotiveParts_Make(int makeID, String makeName) {
		this.makeID = makeID;
		this.makeName = makeName;
	}

	public int getMakeID() {
		return makeID;
	}

	public void setMakeID(int makeID) {
		this.makeID = makeID;
	}

	public String getMakeName() {
		return makeName;
	}

	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}

}
