package dialogWindows;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.table.AbstractTableModel;

import main.Main;
import objects_For_Items.AutomotiveParts_Part;
import objects_For_Items.Instrument;
import tableModels.TableModelAutomotiveParts;
import tableModels.TableModelInstruments;

public class SearchResult extends JDialog {

	public AbstractTableModel itemModel;
	public JTable foundItemsTable;
	public ArrayList<Instrument> items = new ArrayList<Instrument>();
	private JScrollPane jsp_itemTable;
	private ArrayList<Instrument> searchResultList = new ArrayList<>();
	private ArrayList<AutomotiveParts_Part> partsSearchResult = new ArrayList<>();

	public SearchResult(Frame parent, String searchWord, int id) {
		super(parent, true);

		if (id == 1) {
			for (int i = 0; i < Main.mainWindow.items.size(); i++) {
				if (Main.mainWindow.items.get(i).getName().toLowerCase().contains(searchWord)
						|| Main.mainWindow.items.get(i).getMaker().toLowerCase().contains(searchWord)
						|| Main.mainWindow.items.get(i).getDescription().toLowerCase().contains(searchWord))
					searchResultList.add(Main.mainWindow.items.get(i));
			}

		}
		if (id == 2) {
			for (int i = 0; i < Main.mainWindow.partList.size(); i++) {
				if (Main.mainWindow.partList.get(i).getPartName().toLowerCase().contains(searchWord)
						|| Main.mainWindow.partList.get(i).getCatlogID().toLowerCase().contains(searchWord)
						|| Main.mainWindow.partList.get(i).getDescription().toLowerCase().contains(searchWord)
						|| Main.mainWindow.partList.get(i).getMaker().toLowerCase().contains(searchWord)
						|| Main.mainWindow.partList.get(i).getStoragePlace().toLowerCase().contains(searchWord)) {
					partsSearchResult.add(Main.mainWindow.partList.get(i));

				}
			}

		}

		setTitle("Search Result");
		setSize(660, 305);
		setLocation(Main.mainWindow.windowWidth - this.getWidth() / 2,
				Main.mainWindow.windowHeight - this.getHeight() / 2);
		setResizable(true);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		if (id == 1) {
			itemModel = new TableModelInstruments(searchResultList);
			ListSelectionModel lm = new DefaultListSelectionModel();
			lm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			foundItemsTable = new JTable(itemModel);
			foundItemsTable.getTableHeader().setReorderingAllowed(true);

		}
		if (id == 2) {

			itemModel = new TableModelAutomotiveParts(partsSearchResult);
			ListSelectionModel lm = new DefaultListSelectionModel();
			lm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			foundItemsTable = new JTable(itemModel);
			foundItemsTable.getTableHeader().setReorderingAllowed(true);

		}

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -215, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, getContentPane());
		getContentPane().add(panel);

		JLabel lblSearchResult = new JLabel("Search result");
		lblSearchResult.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblSearchResult);
		jsp_itemTable = new JScrollPane(foundItemsTable);
		springLayout.putConstraint(SpringLayout.NORTH, jsp_itemTable, 0, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, jsp_itemTable, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, jsp_itemTable, -40, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, jsp_itemTable, 0, SpringLayout.EAST, getContentPane());
		getContentPane().add(jsp_itemTable);

		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 0, SpringLayout.SOUTH, jsp_itemTable);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 0, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, getContentPane());
		getContentPane().add(panel_1);

		JButton btnShow = new JButton("Show");
		panel_1.add(btnShow);
		btnShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idOfFoundItem = (int) foundItemsTable.getValueAt(foundItemsTable.getSelectedRow(), 0);

				int rawIndex = 0;

				for (int i = 0; i < Main.mainWindow.items.size(); i++) {
					if (Main.mainWindow.items.get(i).getInstrumentID() == idOfFoundItem) {
						rawIndex = Main.mainWindow.items.indexOf(Main.mainWindow.items.get(i));
					}
				}
				Main.mainWindow.rdbtnShowAllItems.setSelected(true);
				Main.mainWindow.itemsTable.setModel(Main.mainWindow.itemModel);
				Main.mainWindow.itemsTable.setRowSelectionInterval(rawIndex, rawIndex);
				dispose();
			}
		});

		JButton btnCancel = new JButton("Cancel");
		panel_1.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

	}
}
