package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import main.Main;
import objects_For_Items.AutomotiveParts_Make;
import objects_For_Items.AutomotiveParts_Model;
import objects_For_Items.AutomotiveParts_Part;
import objects_For_Items.Instrument;
import objects_For_Items.ItemGroup;
import tableModels.TableModelAutomotiveParts;
import tableModels.TableModelInstruments;

public class ListenerForRadioButton implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		if (Main.mainWindow.rdbtnShowAllItems.isSelected()) {
			Main.mainWindow.itemsTable.setModel(Main.mainWindow.itemModel);
			Main.mainWindow.itemsTable.updateUI();

		}

		if (Main.mainWindow.rdbtnShowItemsFromGroup.isSelected() || e.getSource() == Main.mainWindow.cb_Group_name) {
			Main.mainWindow.rdbtnShowItemsFromGroup.setSelected(true);

			int groupID = ItemGroup.getGroupIDByName(Main.mainWindow.cb_Group_name.getSelectedItem().toString());

			ArrayList<Instrument> tempGoods = new ArrayList<Instrument>();
			for (int i = 0; i < Main.mainWindow.items.size(); i++) {

				if (Main.mainWindow.items.get(i).getGroupID() == groupID)
					tempGoods.add(Main.mainWindow.items.get(i));
			}
			TableModelInstruments tempGoodsModel = new TableModelInstruments(tempGoods);
			Main.mainWindow.itemsTable.setModel(tempGoodsModel);
			Main.mainWindow.itemsTable.updateUI();

		}

		if (Main.mainWindow.rdbtnAllParts.isSelected()) {
			Main.mainWindow.automotivePartsTable.setModel(Main.mainWindow.automotivePartsTableModel);
			Main.mainWindow.itemsTable.updateUI();

		}

		if (Main.mainWindow.rdbtnPartsForMake.isSelected() || e.getSource() == Main.mainWindow.comboBox_makeNames) {
			Main.mainWindow.rdbtnPartsForMake.setSelected(true);

			ArrayList<String> modelsList = new ArrayList<String>();
			int tempMakeID = AutomotiveParts_Make
					.getMakeIDByName(Main.mainWindow.comboBox_makeNames.getSelectedItem().toString());

			for (int i = 0; i < Main.mainWindow.modelList.size(); i++) {
				AutomotiveParts_Model model = Main.mainWindow.modelList.get(i);
				if (model.getMakeID() == tempMakeID) {
					modelsList.add(model.getModelName());
				}
			}
			String[] string_models = new String[modelsList.size()];
			for (int i = 0; i < modelsList.size(); i++) {
				string_models[i] = modelsList.get(i);
			}
			Main.mainWindow.comboBox_model_names.setModel(new DefaultComboBoxModel<>(string_models));

			ArrayList<AutomotiveParts_Part> tempParts = new ArrayList<AutomotiveParts_Part>();
			for (int i = 0; i < Main.mainWindow.partList.size(); i++) {
				int makeID = AutomotiveParts_Make
						.getMakeIDByName(Main.mainWindow.comboBox_makeNames.getSelectedItem().toString());

				if (Main.mainWindow.partList.get(i).getMakeID() == makeID)
					tempParts.add(Main.mainWindow.partList.get(i));
			}
			TableModelAutomotiveParts tempPartsTable = new TableModelAutomotiveParts(tempParts);
			Main.mainWindow.automotivePartsTable.setModel(tempPartsTable);
			Main.mainWindow.automotivePartsTable.updateUI();

		}

		if (Main.mainWindow.rdbtnPartsForModel.isSelected() || e.getSource() == Main.mainWindow.comboBox_model_names) {
			Main.mainWindow.rdbtnPartsForModel.setSelected(true);

			ArrayList<AutomotiveParts_Part> tempParts = new ArrayList<AutomotiveParts_Part>();
			for (int i = 0; i < Main.mainWindow.partList.size(); i++) {
				int modelID = AutomotiveParts_Model
						.getModelIDByName(Main.mainWindow.comboBox_model_names.getSelectedItem().toString());

				if (Main.mainWindow.partList.get(i).getModelID() == modelID)
					tempParts.add(Main.mainWindow.partList.get(i));
			}
			TableModelAutomotiveParts tempPartsTable = new TableModelAutomotiveParts(tempParts);
			Main.mainWindow.automotivePartsTable.setModel(tempPartsTable);
			Main.mainWindow.automotivePartsTable.updateUI();

		}

	}

}
