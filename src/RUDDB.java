import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.xswingx.PromptSupport;

public class RUDDB extends JFrame {
	private JLabel label1, label2, label3, label4, label5;
	private JTextField text1, text2, text3;
	private JTextField text5;
	private JPasswordField text4;
	private JButton b1, b2;
	private JPanel panel;
	private ImageIcon icon;
	private GridBagLayout layout;
	private GridBagConstraints gbc;

	Properties p = new Properties();

	private Connection dgconn;

	public static void main(String[] args) {

		RUDDB frame = new RUDDB();

	}

	public RUDDB() {

		super("RUD DB v1.0");
		layout = new GridBagLayout();
		setLayout(layout);
		gbc = new GridBagConstraints();
		icon = new ImageIcon(getClass().getResource("db5.png"));
		setIconImage(icon.getImage());

		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.insets = new Insets(5, 10, 5, 10);
		gbc.gridy = 0;
		gbc.gridx = 0;
		label1 = new JLabel("Host : ", JLabel.LEFT);
		addinframe(label1);
		// increase row automatic
		gbc.gridy++;
		label2 = new JLabel("Port : ", JLabel.LEFT);
		addinframe(label2);

		gbc.gridy++;
		label5 = new JLabel("Database : ", JLabel.LEFT);
		addinframe(label5);

		gbc.gridy++;
		label3 = new JLabel("User Name : ", JLabel.LEFT);
		addinframe(label3);

		gbc.gridy++;
		label4 = new JLabel("Password: ", JLabel.LEFT);
		addinframe(label4);

		gbc.gridx = 1;
		gbc.gridy = 0;
		text1 = new JTextField(15);
		PromptSupport.setPrompt("Enter Server or Host", text1);
		PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, text1);
		PromptSupport.setForeground(Color.gray, text1);
		PromptSupport.setFontStyle(Font.ITALIC, text1);
		addinframe(text1);

		gbc.gridy++;
		text2 = new JTextField(15);
		PromptSupport.setPrompt("Enter Port No", text2);
		PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, text2);
		PromptSupport.setForeground(Color.gray, text2);
		PromptSupport.setFontStyle(Font.ITALIC, text2);
		addinframe(text2);

		gbc.gridy++;
		text5 = new JTextField(15);
		PromptSupport.setPrompt("Default Database Name", text5);
		PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, text5);
		PromptSupport.setForeground(Color.gray, text5);
		PromptSupport.setFontStyle(Font.ITALIC, text5);
		addinframe(text5);

		gbc.gridy++;
		text3 = new JTextField(15);
		PromptSupport.setPrompt("Username", text3);
		PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, text3);
		PromptSupport.setForeground(Color.gray, text3);
		PromptSupport.setFontStyle(Font.ITALIC, text3);
		addinframe(text3);
		gbc.gridy++;
		text4 = new JPasswordField(15);
		PromptSupport.setPrompt("Password", text4);
		PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, text4);
		PromptSupport.setForeground(Color.gray, text4);
		PromptSupport.setFontStyle(Font.ITALIC, text4);
		addinframe(text4);

		gbc.gridx = 0;

		gbc.gridy = 5;
		gbc.gridwidth = GridBagConstraints.REMAINDER;

		gbc.anchor = GridBagConstraints.CENTER;
		panel = new JPanel();
		addinframe(panel);
		b1 = new JButton("OK");
		panel.add(b1);
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (text1.getText().isEmpty() || text2.getText().isEmpty() || text3.getText().isEmpty()
						|| text4.getPassword().length <= 0) {
					JOptionPane.showMessageDialog(null, "TextField Issue Or Start Your Server");
				} else {

					Properties newP = Setter();

					Connect connectclass = new Connect(newP, new String(text4.getPassword()));

					dgconn = connectclass.getConnection();
					if (dgconn != null) {

						dispose();

						new AfterLayout(dgconn);

					}

				}
			}

		});

		b2 = new JButton("Cancel");
		panel.add(b2);
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				System.exit(0);

			}

		});

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}

	private void addinframe(Component com) {

		layout.setConstraints(com, gbc);
		add(com);
	}

	private Properties Setter() {

		p.setProperty("HOST", text1.getText());

		p.setProperty("PORT", text2.getText());

		p.setProperty("UNAME", text3.getText());

		p.setProperty("DB", text5.getText());

		return p;

	}

	public class Connect {

		private Connection conn;

		String host, user, port, password, db;
		String url;

		public Connect(Properties p, String pass) {

			host = p.getProperty("HOST");
			port = p.getProperty("PORT");
			db = p.getProperty("DB");
			user = p.getProperty("UNAME");

			password = pass;
           //for mysql
			url = "jdbc:mysql://" + host + ":" + port + "/" + db;


		}

		public Connection getConnection() {

			try {
				conn = DriverManager.getConnection(url, user, password);

			}

			catch (Exception e) {

				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			if (conn == null) {

				return conn;
			}

			// otherwise
			return conn;

		}

	}

	public class TableModelInterf extends AbstractTableModel {

		private ResultSet rs;
		private ResultSetMetaData meta;
		List forColumn;
		List rowData;
		List forRow;

		public TableModelInterf(ResultSet rs) {

			try {

				forRow = new ArrayList();
				meta = rs.getMetaData();

				int totalcolumn = meta.getColumnCount();
				forColumn = new ArrayList(totalcolumn);

				for (int i = 1; i <= totalcolumn; i++) {
					forColumn.add(meta.getColumnName(i));

				}

				while (rs.next()) {

					rowData = new ArrayList(totalcolumn);

					for (int i = 1; i <= totalcolumn; i++) {

						rowData.add(rs.getObject(i));
					}
					forRow.add(rowData);

				}

			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, e.getMessage());

			}

		}

		@Override
		public int getColumnCount() {

			return forColumn.size();
		}

		@Override
		public int getRowCount() {

			return forRow.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {

			List rowData = (List) forRow.get(rowIndex);

			return rowData.get(columnIndex);
		}

		public String getColumnName(int columnIndex) {
			return (String) forColumn.get(columnIndex);

		}

	}

	public class AfterLayout extends JFrame {
		private GridBagLayout layout;
		private GridBagConstraints gbc;

		private JLabel label1, label2, label3, label4, label5, label6, lupdate;
		private JComboBox catalog, schema, tables;
		private JTextField field1, field2;
		private ImageIcon icon;
		private JTable table, table2;
		private JScrollPane scroll;
		private JButton b1, b2, b3, quit, refresh, update, delete;
		private Border raisedlower;
		private Icon credit;
		private Connection layoutconn;
		private Statement state;
		private ResultSet rs;
		private ResultSetMetaData meta;
		private DatabaseMetaData dbmeta;

		public AfterLayout(Connection transfer) {

			super("RUD DB v1.0");
			layoutconn = transfer;

			icon = new ImageIcon(getClass().getResource("db5.png"));
			setIconImage(icon.getImage());
			buildlayout();
			setSize(789, 606);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setVisible(true);

		}

		private void buildlayout() {

			layout = new GridBagLayout();
			gbc = new GridBagConstraints();

			add(textpanel(), BorderLayout.NORTH);

			add(combopanel(), BorderLayout.SOUTH);

			table = new JTable();
			selectClickandGet();
			scroll = new JScrollPane(table);

			add(scroll, BorderLayout.CENTER);

		}

		private JPanel textpanel() {

			JPanel p1 = new JPanel();
			credit = new ImageIcon(getClass().getResource("hu_1.png"));

			p1.setLayout(layout);

			JMenuBar menu = new JMenuBar();
			JMenu file = new JMenu("File");
			JMenu about = new JMenu("About");
			JMenuItem exit = new JMenuItem("Exit");
			JMenuItem aboutdeveloper = new JMenuItem("Developer");

			setJMenuBar(menu);

			menu.add(file);
			menu.add(about);

			file.add(exit);
			about.add(aboutdeveloper);

			exit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}

			});

			aboutdeveloper.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Credit Goes To Ahmad HussNain '(hussnianXain)'",
							"About Author", JOptionPane.ERROR_MESSAGE, credit);

				}

			});

			gbc.gridy = 0;
			gbc.gridx = 0;
			gbc.insets = new Insets(5, 2, 5, 10);
			label1 = new JLabel("Field For Manually DML Queries");
			p1.add(label1, gbc);

			gbc.gridy++;

			field1 = new JTextField(15);
			field1.setForeground(Color.BLACK);
			field1.setFont(new Font("Tahoma", Font.ITALIC, 20));
			p1.add(field1, gbc);

			gbc.gridy++;
			b1 = new JButton("Execute");
			b1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						state = layoutconn.createStatement();

						String bypasser = field1.getText();
						if (bypasser.length() >= 6 && bypasser.substring(0, 6).equalsIgnoreCase("SELECT")) {
							rs = state.executeQuery(bypasser);

							TableModelInterf OBJ = new TableModelInterf(rs);

							table.setModel(OBJ);

						} else {
							PreparedStatement pre = layoutconn.prepareStatement(field1.getText());

							pre.executeUpdate();

							JOptionPane.showMessageDialog(null, "Successfully Executed");
							JOptionPane.showMessageDialog(null, "Refresh Button To Refresh Table Records");

						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());

					}

				}

			});

			p1.add(b1, gbc);

			gbc.gridx = 1;
			gbc.gridy = 0;
			label2 = new JLabel("Type Here For Search in Table");
			p1.add(label2, gbc);

			gbc.gridy++;
			field2 = new JTextField(15);
			PromptSupport.setPrompt("Keywords For Searching", field2);
			PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, field2);
			PromptSupport.setForeground(Color.gray, field2);
			PromptSupport.setFontStyle(Font.ITALIC, field2);

			field2.addKeyListener(new KeyAdapter() {

				public void keyReleased(KeyEvent arg0) {

					TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());

					table.setRowSorter(sorter);
					String text = field2.getText();

					if (text.length() == 0) {
						sorter.setRowFilter(null);
					}

					else {
						try {
							sorter.setRowFilter(RowFilter.regexFilter(("(?i)" + text)));

						} catch (Exception e) {

							JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern",
									JOptionPane.ERROR_MESSAGE);

						}
					}
				}
			});

			p1.add(field2, gbc);
			gbc.gridy++;
			b2 = new JButton("Clear Field");
			b2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					field1.setText("");
				}

			});
			p1.add(b2, gbc);

			gbc.gridx = 2;
			gbc.gridy = 0;
			label6 = new JLabel("Insert / Refresh");
			p1.add(label6, gbc);

			gbc.gridy++;
			b3 = new JButton("Insert Row");
			b3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					Inserter();

				}

			});
			p1.add(b3, gbc);

			gbc.gridy++;
			refresh = new JButton("Refresh Table");
			refresh.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String agarCataEnablehuva;
					if (catalog.isEnabled()) {
						agarCataEnablehuva = catalog.getSelectedItem().toString();
					} else {
						agarCataEnablehuva = null;
					}

					String agarSchemaEnablehuva;
					if (schema.isEnabled()) {
						agarSchemaEnablehuva = schema.getSelectedItem().toString();
					} else {
						agarSchemaEnablehuva = null;
					}

					String Tablename = (String) tables.getSelectedItem();

					if (Tablename == null) {

						table.setModel(new DefaultTableModel());
						return;
					}

					String selectTable;
					if (agarSchemaEnablehuva == null) {
						selectTable = "" + Tablename;
					} else {
						selectTable = agarSchemaEnablehuva + "." + Tablename;
					}

					if (selectTable.indexOf(' ') > 0) {

						selectTable = "\"" + selectTable + "\"";
					}

					try {
						field1.setText("");
						state = layoutconn.createStatement();

						String queryRefresher = "select * from " + selectTable;
						rs = state.executeQuery(queryRefresher);

						TableModelInterf OBJ2 = new TableModelInterf(rs);

						table.setModel(OBJ2);
					} catch (Exception e1) {

						JOptionPane.showMessageDialog(null, e1.getMessage());

					}

					JOptionPane.showMessageDialog(null, "Your Table is Refresh");

				}
			});
			p1.add(refresh, gbc);

			gbc.gridx = 3;
			gbc.gridy = 0;
			lupdate = new JLabel("Update / Delete");
			p1.add(lupdate, gbc);

			gbc.gridy++;
			update = new JButton("Update Row");
			update.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String cata;
					if (catalog.isEnabled()) {
						cata = catalog.getSelectedItem().toString();
					} else {
						cata = null;
					}

					String tabNAME = (String) tables.getSelectedItem();

					if (tabNAME == null) {

						table.setModel(new DefaultTableModel());
						return;
					}

					int rowIndex = table.getSelectedRow();

					if (rowIndex == -1) {

						JOptionPane.showMessageDialog(null, "Select Row From Table Then Click", "Selection Error",
								JOptionPane.ERROR_MESSAGE, null);

					} else if (rowIndex >= 0) {

						String Must;
						String where;
						// Value
						Object click = table.getModel().getValueAt(rowIndex, 0);
						String colName = table.getModel().getColumnName(0);
						if ((click.getClass() == Integer.class) || (click.getClass() == BigDecimal.class)) {
							Must = "select * from " + cata + "." + tabNAME + " where " + colName + "=" + click;
							where = colName + "=" + click;
						} else {
							Must = "select * from " + cata + "." + tabNAME + " where " + colName + "=\"" + click + "\"";
							where = colName + "=\"" + click + "\"";
						}

						try {

							state = layoutconn.createStatement();
							String querEXT = "select *  from " + cata + "." + tabNAME;

							rs = state.executeQuery(querEXT);

							meta = rs.getMetaData();

							int joker = meta.getColumnCount();

							String array[] = new String[joker];
							for (int i = 1; i <= joker; i++) {

								array[i - 1] = meta.getColumnName(i);

							}

							//
							Update Ohj = new Update(joker, array, cata, tabNAME, Must, where, layoutconn);

						}

						catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());

						}

					}

				}

			});
			p1.add(update, gbc);

			gbc.gridy++;
			delete = new JButton("Delete Selected Row");
			delete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String cata;
					if (catalog.isEnabled()) {
						cata = catalog.getSelectedItem().toString();
					} else {
						cata = null;
					}

					String tabNAME = (String) tables.getSelectedItem();

					if (tabNAME == null) {

						table.setModel(new DefaultTableModel());
						return;
					}

					int rowIndex = table.getSelectedRow();
					if (rowIndex == -1) {

						JOptionPane.showMessageDialog(null, "Select Row From Table Then Click", "Selection Error",
								JOptionPane.ERROR_MESSAGE, null);

					} else if (rowIndex >= 0) {
						String Must;
						// Value
						Object click = table.getModel().getValueAt(rowIndex, 0);
						String colName = table.getModel().getColumnName(0);
						if ((click.getClass() == Integer.class) || (click.getClass() == BigDecimal.class)) {
							Must = "delete from " + cata + "." + tabNAME + " where " + colName + "=" + click;
						} else {
							Must = "delete from " + cata + "." + tabNAME + " where " + colName + "=\"" + click + "\"";
						}

						try {

							PreparedStatement pre = layoutconn.prepareStatement(Must);

							pre.executeUpdate();

							JOptionPane.showMessageDialog(null, "Row Has Been Deleted Successfully");
							JOptionPane.showMessageDialog(null, "Refresh Button To Refresh Table Records");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}

					}

				}
			});
			p1.add(delete, gbc);

			raisedlower = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			p1.setBorder(raisedlower);

			return p1;
		}

		private JPanel combopanel() {

			JPanel p2 = new JPanel();
			p2.setLayout(layout);

			GridBagConstraints gbc2 = new GridBagConstraints();

			raisedlower = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			p2.setBorder(raisedlower);

			gbc2.insets = new Insets(5, 10, 5, 10);
			gbc2.gridy = 0;
			label3 = new JLabel("Catalogs", JLabel.RIGHT);
			p2.add(label3, gbc2);

			label4 = new JLabel("Schema", JLabel.RIGHT);
			p2.add(label4, gbc2);

			label5 = new JLabel("Tables", JLabel.RIGHT);
			p2.add(label5, gbc2);

			// Row 2 C
			gbc2.gridy = 1;
			catalog = new JComboBox();

			CallCatalog();

			catalog.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {

					String n = (String) catalog.getSelectedItem();

					try {
						layoutconn.setCatalog(n);
					} catch (SQLException e1) {

						JOptionPane.showMessageDialog(null, e1.getMessage());
					}

					callSchema();

					calltable();
					selectClickandGet();

				}

			});
			p2.add(catalog, gbc2);

			schema = new JComboBox();

			callSchema();

			schema.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {

					calltable();
					selectClickandGet();

				}

			});

			p2.add(schema, gbc2);

			tables = new JComboBox();

			calltable();

			tables.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {

					selectClickandGet();

				}

			});

			p2.add(tables, gbc2);

			gbc2.gridy = 2;
			gbc2.gridx = 1;
			quit = new JButton("QUIT");

			quit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					System.exit(0);
				}

			});
			p2.add(quit, gbc2);
			return p2;
		}

		private void CallCatalog() {

			try {
				dbmeta = layoutconn.getMetaData();

				rs = dbmeta.getCatalogs();

				List<Object> ct = new ArrayList<>();

				while (rs.next()) {

					ct.add(rs.getString(1));

				}

				rs.close();
				catalog.setModel(new DefaultComboBoxModel(ct.toArray()));

				catalog.setSelectedItem(layoutconn.getCatalog());

				catalog.setEnabled(ct.size() > 0);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());

				catalog.setEnabled(false);

			}

		}

		private void callSchema() {

			try {
				dbmeta = layoutconn.getMetaData();

				rs = dbmeta.getSchemas();

				List<Object> list2 = new ArrayList<>();

				while (rs.next()) {

					list2.add(rs.getString(1));
				}
				rs.close();
				schema.setModel(new DefaultComboBoxModel(list2.toArray()));

				schema.setSelectedItem(layoutconn.getSchema());

				schema.setEnabled(list2.size() > 0);

			} catch (Exception e) {

				schema.setEnabled(false);

			}

		}

		private void calltable() {

			try {
				String[] that = { "Table" };

				String abhicatalog = layoutconn.getCatalog();

				String abhiScehemCombo = (String) schema.getSelectedItem();

				dbmeta = layoutconn.getMetaData();

				rs = dbmeta.getTables(abhicatalog, abhiScehemCombo, null, that);

				List list3 = new ArrayList();

				while (rs.next()) {

					list3.add(rs.getString(3));

				}

				rs.close();

				tables.setModel(new DefaultComboBoxModel(list3.toArray()));

				tables.setEnabled(list3.size() > 0);

			} catch (Exception e) {
				tables.setEnabled(false);
			}

		}

		private void selectClickandGet() {

			String agarCataEnablehuva;
			if (catalog.isEnabled()) {
				agarCataEnablehuva = catalog.getSelectedItem().toString();
			} else {
				agarCataEnablehuva = null;
			}

			String agarSchemaEnablehuva;
			if (schema.isEnabled()) {
				agarSchemaEnablehuva = schema.getSelectedItem().toString();
			} else {
				agarSchemaEnablehuva = null;
			}

			String Tablename = (String) tables.getSelectedItem();

			if (Tablename == null) {

				table.setModel(new DefaultTableModel());
				return;
			}

			String selectTable;
			if (agarSchemaEnablehuva == null) {
				selectTable = "" + Tablename;
			} else {
				selectTable = agarSchemaEnablehuva + "." + Tablename;
			}

			if (selectTable.indexOf(' ') > 0) {

				selectTable = "\"" + selectTable + "\"";
			}

			try {
				field1.setText("");
				state = layoutconn.createStatement();

				String queryRefresher = "select * from " + selectTable;
				rs = state.executeQuery(queryRefresher);

				TableModelInterf OBJ2 = new TableModelInterf(rs);

				table.setModel(OBJ2);
			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, e.getMessage());

			}
		}

		private void Inserter() {

			String cata;
			if (catalog.isEnabled()) {

				cata = catalog.getSelectedItem().toString();

			} else {
				cata = null;
			}

			String sch;
			if (schema.isEnabled()) {

				sch = schema.getSelectedItem().toString();

			} else {
				sch = null;
			}

			String tabNAME = (String) tables.getSelectedItem();

			if (tabNAME == null) {

				table.setModel(new DefaultTableModel());
				return;
			}

			String selTabName;
			if (sch == null) {

				selTabName = "" + tabNAME;

			} else {
				selTabName = sch + "." + tabNAME;

			}
			if (selTabName.indexOf(' ') > 0) {

				selTabName = "\"" + selTabName + "\"";
			}
			try {

				state = layoutconn.createStatement();
				String querEXT = "select *  from " + cata + "." + selTabName;

				rs = state.executeQuery(querEXT);

				meta = rs.getMetaData();

				int joker = meta.getColumnCount();

				String array[] = new String[joker];
				for (int i = 1; i <= joker; i++) {

					array[i - 1] = meta.getColumnName(i);

				}
				Insert OAK = new Insert(joker, array, cata, selTabName, layoutconn);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

		}

	}

	public class Insert extends JFrame {

		String catlog;
		String selectTable;
		private int col;
		private String array[];
		private JTextField[] texter;
		private JLabel[] labler;
		private JButton sub;
		private GridBagLayout grid;
		private GridBagConstraints gbc;
		private Connection insertTransfer;
		private PreparedStatement pre;
		private ImageIcon icon;

		public Insert(int col, String[] arr, String cata, String selectTable, Connection coat) {
			super("RUD DB v1.0");
			icon = new ImageIcon(getClass().getResource("db5.png"));
			setIconImage(icon.getImage());

			this.col = col;
			this.array = arr;
			this.catlog = cata;
			this.selectTable = selectTable;
			this.insertTransfer = coat;

			grid = new GridBagLayout();
			gbc = new GridBagConstraints();
			setLayout(grid);

			labler = new JLabel[col];
			texter = new JTextField[col];
			gbc.anchor = GridBagConstraints.SOUTH;
			gbc.insets = new Insets(5, 10, 5, 10);
			gbc.gridy = 0;
			for (int i = 0; i < labler.length; i++) {
				labler[i] = new JLabel(arr[i] + " : ", JLabel.LEFT);

				addGbc(labler[i]);
				gbc.gridx = 1;
				texter[i] = new JTextField(15);
				addGbc(texter[i]);

				gbc.gridy++;
				gbc.gridx = 0;

			}

			StringBuilder builder = new StringBuilder();
			for (String value : arr) {
				builder.append(value);
				builder.append(",");
			}
			int length = builder.length();
			builder.deleteCharAt(length - 1);

			String text = builder.toString();

			StringBuilder builder2 = new StringBuilder();

			String qustion[] = new String[col];
			for (int i = 0; i < col; i++) {
				String mark = "?";
				qustion[i] = mark;
			}

			for (String h : qustion) {
				builder2.append(h);
				builder2.append(",");

			}

			int length2 = builder2.length();
			builder2.deleteCharAt(length2 - 1);
			String text2 = builder2.toString();

			String idealQuery = "insert into " + cata + "." + selectTable + " (" + text + ") values (" + text2 + ")";

			gbc.gridy++;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.anchor = GridBagConstraints.CENTER;
			sub = new JButton("Submit Data");
			sub.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {

						pre = insertTransfer.prepareStatement(idealQuery);

						for (int i = 0; i < texter.length; i++) {

							if (texter[i].getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Fill the textfields : " + (i + 1));
							}

							else {

								pre.setObject((i + 1), texter[i].getText());

							} // else end
						}
						pre.executeUpdate();

						JOptionPane.showMessageDialog(null, "New Row Has Been Inserted");
						JOptionPane.showMessageDialog(null, "Refresh Button To Refresh Table Records");

						dispose();
					} catch (Exception e4) {
						JOptionPane.showMessageDialog(null, e4.getMessage());
					}

				}

			});

			addGbc(sub);

			pack();
			setLocationRelativeTo(null);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);

		}

		private void addGbc(Component com) {

			grid.setConstraints(com, gbc);
			add(com);

		}

	}

	public class Update extends JFrame {

		String catlog;
		String selectTable;
		String Must;
		String where;
		private int col;
		private String array[];
		private JTextField[] texter;
		private JLabel[] labler;
		private JButton save;
		private GridBagLayout grid;
		private GridBagConstraints gbc;
		private Connection insertTransfer;
		private PreparedStatement pre;
		private Statement state;
		private ResultSetMetaData meta;
		private ImageIcon icon;
		private ResultSet rs;

		public Update(int col, String[] arr, String cata, String selectTable, String mu, String wh, Connection coat) {
			super("RUD DB v1.0");
			this.col = col;
			this.array = arr;
			this.catlog = cata;
			this.selectTable = selectTable;
			this.Must = mu;
			this.where = wh;
			this.insertTransfer = coat;
			icon = new ImageIcon(getClass().getResource("db5.png"));
			setIconImage(icon.getImage());

			grid = new GridBagLayout();
			gbc = new GridBagConstraints();
			setLayout(grid);

			labler = new JLabel[col];
			texter = new JTextField[col];
			Object textTemp[] = new Object[col];

			try {

				gbc.anchor = GridBagConstraints.SOUTH;
				gbc.insets = new Insets(5, 10, 5, 10);
				gbc.gridy = 0;
				pre = insertTransfer.prepareStatement(Must);
				rs = pre.executeQuery();

				if (rs.next()) {

					for (int i = 0; i < labler.length; i++) {
						labler[i] = new JLabel(arr[i] + " : ", JLabel.LEFT);

						addGbc(labler[i]);
						gbc.gridx = 1;

						texter[i] = new JTextField(15);
						textTemp[i] = rs.getString(i + 1);
						texter[i].setText((String) textTemp[i]);

						addGbc(texter[i]);

						gbc.gridy++;
						gbc.gridx = 0;

					}

					gbc.gridy++;
					gbc.gridwidth = GridBagConstraints.REMAINDER;
					gbc.anchor = GridBagConstraints.CENTER;
					save = new JButton("Save");
					save.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							Object transfer[] = new Object[col];
							String nameQuery[] = new String[col];
							StringBuilder build = new StringBuilder();
							StringBuilder build2 = new StringBuilder();

							String qot = "select * from " + cata + "." + selectTable + " LIMIT 1";
							try {
								state = insertTransfer.createStatement();
								rs = state.executeQuery(qot);
								meta = rs.getMetaData();
								String we;
								for (int i = 1; i <= col; i++) {

									transfer[i - 1] = meta.getColumnClassName(i);
									nameQuery[i - 1] = meta.getColumnName(i);

									build.append(nameQuery[i - 1]);
									build.append("=?,");
								}
								int len = build.length();
								build.deleteCharAt(len - 1);
								String baazaa = build.toString();

								String query = "update " + cata + "." + selectTable + " set " + build + " where " + wh;

								pre = insertTransfer.prepareStatement(query);

								for (int i = 0; i < texter.length; i++) {

									pre.setObject((i + 1), texter[i].getText());

								}

								pre.executeUpdate();
								JOptionPane.showMessageDialog(null, "Record Has Been Updated");
								JOptionPane.showMessageDialog(null, "Refresh Button To Refresh Table Records");
								dispose();

							} catch (Exception e1) {

								e1.printStackTrace();
							}

						}

					});

					addGbc(save);
				}
			}

			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}

			pack();
			setLocationRelativeTo(null);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);

		}

		private void addGbc(Component com) {

			grid.setConstraints(com, gbc);
			add(com);

		}

	}

}
