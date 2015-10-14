import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;


public class MySWTApplication {

	protected Shell shell;
	protected Shell shell2;
	private Text txtName;
	private Table table;
	private Text txtNamePlace;
	private Text txtCity;
	private Text txtCountry;
	private Combo combo;
	private DateTime dateTime;
	private Menu menu;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Operations.createTableEvent();
		Operations.createTablePlace();
		try {
			MySWTApplication window = new MySWTApplication();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		MenuItem mntmActions = new MenuItem(menu, SWT.NONE);
		mntmActions.setText("close");
		
		MenuItem mntmCascade = new MenuItem(menu, SWT.CASCADE);
		mntmCascade.setText("cascade");
		
		Menu menu_1 = new Menu(mntmCascade);
		mntmCascade.setMenu(menu_1);
		
		MenuItem mntmNewShell = new MenuItem(menu_1, SWT.NONE);
		mntmNewShell.setText("new shell");
		mntmNewShell.addSelectionListener(new SelectionAdapter()	{
			public void widgetSelected(SelectionEvent e)	{
				newShell ns = new newShell();
				newShell.open();
			}
		});
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage("/Users/ProjectSoft/Downloads/1014028_10154636333175304_8096995622403648799_n.png"));
		shell.setSize(900, 700);
		shell.setText("SWT Application");
		
		Button btnName = new Button(shell, SWT.NONE);
		btnName.addMouseListener(new InsertionEvent());
		btnName.setBounds(268, 56, 105, 42);
		btnName.setText("Insert");
		
		txtName = new Text(shell, SWT.BORDER);
		txtName.addMouseListener(new CleanText(txtName));
		txtName.setText("Insert name");
		txtName.setBounds(38, 27, 161, 19);
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(38, 156, 498, 143);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);	// col Name
		tblclmnName.setWidth(100);
		tblclmnName.setText("Name");
		TableColumn tblclmnPlace = new TableColumn(table, SWT.NONE); // col Place
		tblclmnPlace.setWidth(100);
		tblclmnPlace.setText("Place");
		TableColumn tblclmnDate = new TableColumn(table, SWT.NONE); // col Date
		tblclmnDate.setWidth(100);
		tblclmnDate.setText("Date");
		TableColumn tblclmnCity = new TableColumn(table, SWT.NONE); // col City
		tblclmnCity.setWidth(100);
		tblclmnCity.setText("City");
		TableColumn tblclmnCountry = new TableColumn(table, SWT.NONE); // col Country
		tblclmnCountry.setWidth(100);
		tblclmnCountry.setText("Country");
		
		Button btnSelect = new Button(shell, SWT.NONE);
		btnSelect.addMouseListener(new Selection());
		// btnSelect.addMouseListener(new SetImage());
		btnSelect.setBounds(396, 56, 105, 42);
		btnSelect.setText("Select");
		
		txtNamePlace = new Text(shell, SWT.BORDER);
		txtNamePlace.addMouseListener(new CleanText(txtNamePlace));
		txtNamePlace.setText("name");
		txtNamePlace.setBounds(38, 327, 64, 19);
		
		txtCity = new Text(shell, SWT.BORDER);
		txtCity.addMouseListener(new CleanText(txtCity));
		txtCity.setText("city");
		txtCity.setBounds(38, 353, 64, 19);
		
		txtCountry = new Text(shell, SWT.BORDER);
		txtCountry.addMouseListener(new CleanText(txtCountry));
		txtCountry.setText("country");
		txtCountry.setBounds(38, 378, 64, 19);
		
		Button btnInsertPlace = new Button(shell, SWT.NONE);
		btnInsertPlace.addMouseListener(new InsertionPlace());
		btnInsertPlace.setBounds(148, 323, 94, 28);
		btnInsertPlace.setText("Insert place");
		
		combo = new Combo(shell, SWT.DROP_DOWN);
		combo.setText("Select place");
		combo.addMouseListener(new ComboList());
		combo.setBounds(38, 65, 179, 22);
		
		dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(38, 109, 99, 28);
		
		Button btnDelete = new Button(shell, SWT.NONE);
		btnDelete.addMouseListener(new Delete());
		btnDelete.setBounds(531, 56, 94, 42);
		btnDelete.setText("Delete");

	}
	
	class InsertionEvent implements MouseListener	{

		public void mouseDoubleClick(MouseEvent arg0) {}

		public void mouseDown(MouseEvent e) {
			Event event;
			String name = txtName.getText();
			String place = combo.getText();
			String date = Utilities.formatDate(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay());
			event = new Event(name, place, date);
			Operations.insertIntoTable(event);
			txtName.setText("");
			// txtDate.setText("");
			combo.setText("");
		}

		public void mouseUp(MouseEvent arg0) {}
		
	}
	class InsertionPlace implements MouseListener	{

		public void mouseDoubleClick(MouseEvent arg0) {}

		public void mouseDown(MouseEvent e) {
			Place place;
			String name = txtNamePlace.getText();
			String city = txtCity.getText();
			String country = txtCountry.getText();
			place = new Place(name, city, country);
			Operations.insertIntoTable(place);
			txtNamePlace.setText("");
			txtCity.setText("");
			txtCountry.setText("");
		}

		public void mouseUp(MouseEvent arg0) {}
		
	}
	
	class Selection implements MouseListener	{

		public void mouseDoubleClick(MouseEvent arg0) {
			table.removeAll();
			String[][] rows = Operations.selectAll();
			for (String[] r : rows)	{
				TableItem item = new TableItem(table, SWT.None);
				item.setText(r);
			}
		}

		public void mouseDown(MouseEvent arg0) {
			/*
			String name = null;
			if (txtName.getText() != "")	{
				name = txtName.getText();
			}
			String place = null;
			if (combo.getText() != "")	{
				place = combo.getText();
			}
			*/
			table.removeAll();
			String[][] rows = Operations.selectAll();
			for (String[] r : rows)	{
				TableItem item = new TableItem(table, SWT.None);
				item.setText(r);
			}
			
		}

		public void mouseUp(MouseEvent arg0) {}
		
	}
	
	class CleanText implements MouseListener	{
		private Text text;
		public CleanText(Text text)	{
			this.text = text;
		}

		@Override
		public void mouseDown(MouseEvent arg0) {
			text.setText("");
		}
		@Override
		public void mouseUp(MouseEvent arg0) {}
		@Override
		public void mouseDoubleClick(MouseEvent arg0) {}
		
	}
	class ComboList implements MouseListener	{

		@Override
		public void mouseDoubleClick(MouseEvent arg0) {}

		@Override
		public void mouseDown(MouseEvent arg0) {
			/*
			Place[] places = Operations.selectItem("","",""), "all");
			String[] names = new String[places.length];
			for (int i = 0; i < places.length; i++)	{
				names[i] = places[i].getName();
			}
			*/
			String[] places = Operations.selectItems("Place", "PName");
			combo.setItems(places);
			combo.setEnabled(true);
			combo.setVisible(true);
		}

		@Override
		public void mouseUp(MouseEvent arg0) {}
		
	}
	class Delete implements MouseListener	{

		@Override
		public void mouseDoubleClick(MouseEvent arg0) {
			Operations.deleteDb();
			Operations.createTableEvent();
			Operations.createTablePlace();
		}

		@Override
		public void mouseDown(MouseEvent arg0) {
			TableItem[] selection = table.getSelection();
			String[] selectedItems = new String[selection.length];
			for (int i = 0; i < selection.length; i++)	{
				selectedItems[i] = selection[i].getText();
			}
			Operations.deleteFromTable("Event", selectedItems[0]);
			Operations.selectAll();
		}

		@Override
		public void mouseUp(MouseEvent arg0) {}
		
	}
}
