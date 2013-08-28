package FileUtilities;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6489220066140541781L;
	private final JPanel contentPane;
	private final JTextField parentfolder;
	private final JTextField fdir;
	private final JTextField basenamef;
	private final JTextField separatorf;
	private final JSpinner minf;
	private final JSpinner maxf;
	private final JPanel HashCalc;
	private final JTextField dupeparentdir;
	private final JTextField hfile;
	private final JLabel hmd5;
	private final JLabel hsha1;
	private final JTextArea ffiles;
	private final JCheckBox fsub;
	private final JComboBox xformat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setTitle("Saad Folder Utilities");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setName("");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE));

		JPanel FolderCreator = new JPanel();
		tabbedPane.addTab("Folder Creator", null, FolderCreator, null);

		parentfolder = new JTextField();
		parentfolder.setColumns(10);

		JLabel lblParentFolder = new JLabel("Parent Folder");

		final JTextArea foldernames = new JTextArea();
		foldernames.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		foldernames.setBackground(Color.PINK);

		JLabel lblNames = new JLabel("Names");

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (String line : foldernames.getText().split(",")) {
					new File(parentfolder.getText() + "\\" + line).mkdirs();

				}
				// message.setText("Folders Created");
			}//

		});

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser picker = new JFileChooser();
				picker.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int pickerResult = picker.showOpenDialog(getParent());
				if (pickerResult == JFileChooser.APPROVE_OPTION) {
					String f = picker.getSelectedFile().getPath();
					parentfolder.setText(f);
				}
				if (pickerResult == JFileChooser.CANCEL_OPTION) {
					picker.setVisible(false);

				}
			}
		});

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				foldernames.setText("");
			}
		});

		JLabel lblBasename = new JLabel("Basename");
		lblBasename.setForeground(Color.RED);

		basenamef = new JTextField();
		basenamef.setColumns(10);

		JLabel lblSuffix = new JLabel("Suffix");
		lblSuffix.setForeground(Color.RED);

		minf = new JSpinner();
		minf.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0),
				null, new Integer(1)));

		JLabel lblMax = new JLabel("Min");
		lblMax.setForeground(Color.BLUE);

		JLabel lblMin = new JLabel("Max");
		lblMin.setForeground(Color.BLUE);

		maxf = new JSpinner();
		maxf.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0),
				null, new Integer(1)));

		JButton btnCreate_1 = new JButton("Create");
		btnCreate_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int min = (int) minf.getValue();
				int max = (int) maxf.getValue();
				String basename = basenamef.getText();
				String separator = separatorf.getText();
				for (int i = min; i <= max; i++) {
					new File(parentfolder.getText() + "\\" + basename
							+ separator + i).mkdirs();
				}
			}
		});

		JLabel lblSeparator = new JLabel("Separator");
		lblSeparator.setForeground(Color.RED);

		separatorf = new JTextField();
		separatorf.setColumns(10);
		GroupLayout gl_FolderCreator = new GroupLayout(FolderCreator);
		gl_FolderCreator
				.setHorizontalGroup(gl_FolderCreator
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_FolderCreator
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_FolderCreator
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_FolderCreator
																		.createSequentialGroup()
																		.addGroup(
																				gl_FolderCreator
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_FolderCreator
																										.createSequentialGroup()
																										.addGap(2)
																										.addComponent(
																												foldernames,
																												GroupLayout.PREFERRED_SIZE,
																												362,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_FolderCreator
																										.createParallelGroup(
																												Alignment.TRAILING,
																												false)
																										.addGroup(
																												Alignment.LEADING,
																												gl_FolderCreator
																														.createSequentialGroup()
																														.addComponent(
																																lblBasename)
																														.addGap(18)
																														.addComponent(
																																basenamef,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE)
																														.addPreferredGap(
																																ComponentPlacement.UNRELATED)
																														.addComponent(
																																lblSeparator)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																separatorf,
																																0,
																																0,
																																Short.MAX_VALUE))
																										.addGroup(
																												Alignment.LEADING,
																												gl_FolderCreator
																														.createSequentialGroup()
																														.addComponent(
																																lblParentFolder)
																														.addGap(18)
																														.addComponent(
																																parentfolder,
																																GroupLayout.PREFERRED_SIZE,
																																235,
																																GroupLayout.PREFERRED_SIZE)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																btnNewButton,
																																GroupLayout.PREFERRED_SIZE,
																																20,
																																GroupLayout.PREFERRED_SIZE))))
																		.addContainerGap(
																				43,
																				Short.MAX_VALUE))
														.addGroup(
																gl_FolderCreator
																		.createSequentialGroup()
																		.addGap(121)
																		.addComponent(
																				btnClear)
																		.addGap(18)
																		.addComponent(
																				btnCreate)
																		.addGap(134))
														.addGroup(
																gl_FolderCreator
																		.createSequentialGroup()
																		.addComponent(
																				lblNames)
																		.addContainerGap(
																				369,
																				Short.MAX_VALUE))
														.addGroup(
																gl_FolderCreator
																		.createSequentialGroup()
																		.addComponent(
																				lblSuffix)
																		.addContainerGap(
																				373,
																				Short.MAX_VALUE))
														.addGroup(
																Alignment.TRAILING,
																gl_FolderCreator
																		.createSequentialGroup()
																		.addGroup(
																				gl_FolderCreator
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblMin)
																						.addComponent(
																								lblMax))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				gl_FolderCreator
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								minf,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								84,
																								Short.MAX_VALUE)
																						.addComponent(
																								maxf,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								87,
																								Short.MAX_VALUE))
																		.addGap(45)
																		.addComponent(
																				btnCreate_1)
																		.addGap(143)))));
		gl_FolderCreator
				.setVerticalGroup(gl_FolderCreator
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_FolderCreator
										.createSequentialGroup()
										.addGap(25)
										.addGroup(
												gl_FolderCreator
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblParentFolder)
														.addComponent(
																parentfolder,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnNewButton))
										.addGap(40)
										.addGroup(
												gl_FolderCreator
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblBasename)
														.addComponent(
																basenamef,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblSeparator)
														.addComponent(
																separatorf,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_FolderCreator
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_FolderCreator
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblSuffix)
																		.addGap(8)
																		.addGroup(
																				gl_FolderCreator
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								minf,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblMax))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_FolderCreator
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblMin)
																						.addComponent(
																								maxf,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(29)
																		.addComponent(
																				lblNames))
														.addGroup(
																gl_FolderCreator
																		.createSequentialGroup()
																		.addGap(40)
																		.addComponent(
																				btnCreate_1)))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(foldernames,
												GroupLayout.PREFERRED_SIZE,
												234, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(
												gl_FolderCreator
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(btnClear)
														.addComponent(btnCreate))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		FolderCreator.setLayout(gl_FolderCreator);

		JPanel DupFinder = new JPanel();
		tabbedPane.addTab("DupFinder", null, DupFinder, null);

		JLabel lblRootDirectory = new JLabel("Directory");

		dupeparentdir = new JTextField();
		dupeparentdir.setColumns(10);

		JList list = new JList();
		list.setBackground(SystemColor.inactiveCaption);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
				null));

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser picker = new JFileChooser();
				picker.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int pickerResult = picker.showOpenDialog(getParent());
				if (pickerResult == JFileChooser.APPROVE_OPTION) {
					String f = picker.getSelectedFile().getPath();
					dupeparentdir.setText(f);
				}
				if (pickerResult == JFileChooser.CANCEL_OPTION) {
					picker.setVisible(false);

				}
			}

		});

		JButton btnSearch = new JButton("Search");

		JButton btnSelectAll = new JButton("Select All");

		JButton btnRemove = new JButton("Remove");

		JButton btnDeselectAll = new JButton("Deselect All");
		GroupLayout gl_DupFinder = new GroupLayout(DupFinder);
		gl_DupFinder
				.setHorizontalGroup(gl_DupFinder
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_DupFinder
										.createSequentialGroup()
										.addGroup(
												gl_DupFinder
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_DupFinder
																		.createSequentialGroup()
																		.addGap(150)
																		.addComponent(
																				btnSearch))
														.addGroup(
																gl_DupFinder
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				gl_DupFinder
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_DupFinder
																										.createSequentialGroup()
																										.addComponent(
																												btnSelectAll)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnDeselectAll)
																										.addPreferredGap(
																												ComponentPlacement.RELATED,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												btnRemove))
																						.addComponent(
																								list,
																								GroupLayout.PREFERRED_SIZE,
																								362,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								gl_DupFinder
																										.createSequentialGroup()
																										.addComponent(
																												lblRootDirectory)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												dupeparentdir,
																												GroupLayout.PREFERRED_SIZE,
																												217,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnNewButton_1,
																												GroupLayout.PREFERRED_SIZE,
																												23,
																												GroupLayout.PREFERRED_SIZE)))))
										.addContainerGap(56, Short.MAX_VALUE)));
		gl_DupFinder
				.setVerticalGroup(gl_DupFinder
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_DupFinder
										.createSequentialGroup()
										.addGap(29)
										.addGroup(
												gl_DupFinder
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblRootDirectory)
														.addComponent(
																dupeparentdir,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnNewButton_1))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(btnSearch)
										.addGap(18)
										.addComponent(list,
												GroupLayout.PREFERRED_SIZE,
												334, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(
												gl_DupFinder
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnSelectAll)
														.addComponent(btnRemove)
														.addComponent(
																btnDeselectAll))
										.addContainerGap(61, Short.MAX_VALUE)));
		DupFinder.setLayout(gl_DupFinder);

		JPanel FileLister = new JPanel();
		tabbedPane.addTab("FileLister", null, FileLister, null);

		JLabel lblDirectory = new JLabel("Directory");

		fdir = new JTextField();

		fdir.setColumns(10);

		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser picker = new JFileChooser();
				picker.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int pickerResult = picker.showOpenDialog(getParent());
				if (pickerResult == JFileChooser.APPROVE_OPTION) {
					String f = picker.getSelectedFile().getPath();
					fdir.setText(f);
				}
				if (pickerResult == JFileChooser.CANCEL_OPTION) {
					picker.setVisible(false);

				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JButton btnList = new JButton("Scan");
		btnList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ffiles.setText("");
				FolderUtil folderutil = new FolderUtil();
				if (!fsub.isSelected()) {
					String[] files = folderutil.listFolders(fdir.getText());
					for (String f : files)
						ffiles.append(f + "\n");

				} else {
					List<String> files = folderutil.listSubFolders(fdir
							.getText());
					for (String f : files)
						ffiles.append(f + "\n");
				}
			}
		});

		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FolderUtil fu = new FolderUtil();
				int i = xformat.getSelectedIndex();
				String filelist = ffiles.getText();
				String path = fdir.getText();
				switch (i) {
				case 0:
					fu.exportToTXT(filelist, path);
					break;
				case 1:
					fu.exportToHTML(filelist, path);
					break;
				case 2:
					fu.exportToCSV(filelist);
					break;
				case 3:
					fu.exportToXLS(filelist);
					break;
				case 4:
					fu.exportToPDF(filelist);
					break;
				default:
					fu.exportToTXT(filelist, path);
					break;
				}
				// fu.exportToTXT(ffiles.getText(), fdir.getText());
				// fu.exportToXLS(ffiles.getText());
				fu.exportToPDF(ffiles.getText());

			}

		});

		fsub = new JCheckBox("SubFolders");

		xformat = new JComboBox();
		xformat.setModel(new DefaultComboBoxModel(new String[] { "Text",
				"Html", "CSV", "XLS", "PDF" }));
		GroupLayout gl_FileLister = new GroupLayout(FileLister);
		gl_FileLister
				.setHorizontalGroup(gl_FileLister
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_FileLister
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_FileLister
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_FileLister
																		.createSequentialGroup()
																		.addComponent(
																				fsub)
																		.addGap(90)
																		.addComponent(
																				btnList))
														.addGroup(
																gl_FileLister
																		.createSequentialGroup()
																		.addComponent(
																				btnExport)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				xformat,
																				GroupLayout.PREFERRED_SIZE,
																				66,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																scrollPane,
																GroupLayout.DEFAULT_SIZE,
																397,
																Short.MAX_VALUE)
														.addGroup(
																gl_FileLister
																		.createSequentialGroup()
																		.addComponent(
																				lblDirectory)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				fdir,
																				GroupLayout.PREFERRED_SIZE,
																				251,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnNewButton_2,
																				GroupLayout.PREFERRED_SIZE,
																				39,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		gl_FileLister
				.setVerticalGroup(gl_FileLister
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_FileLister
										.createSequentialGroup()
										.addGap(32)
										.addGroup(
												gl_FileLister
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblDirectory)
														.addComponent(
																fdir,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnNewButton_2))
										.addGap(19)
										.addGroup(
												gl_FileLister
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_FileLister
																		.createSequentialGroup()
																		.addComponent(
																				fsub)
																		.addGap(27))
														.addGroup(
																gl_FileLister
																		.createSequentialGroup()
																		.addComponent(
																				btnList)
																		.addGap(18)))
										.addComponent(scrollPane,
												GroupLayout.PREFERRED_SIZE,
												276, GroupLayout.PREFERRED_SIZE)
										.addGap(32)
										.addGroup(
												gl_FileLister
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(btnExport)
														.addComponent(
																xformat,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(72, Short.MAX_VALUE)));

		ffiles = new JTextArea();
		ffiles.setBackground(SystemColor.inactiveCaption);
		scrollPane.setViewportView(ffiles);
		FileLister.setLayout(gl_FileLister);

		HashCalc = new JPanel();
		tabbedPane.addTab("HashCalc", null, HashCalc, null);

		JLabel lblFile = new JLabel("File");
		lblFile.setForeground(Color.BLUE);

		hfile = new JTextField();
		hfile.setColumns(10);

		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser picker = new JFileChooser();
				picker.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int pickerResult = picker.showOpenDialog(getParent());
				if (pickerResult == JFileChooser.APPROVE_OPTION) {
					String f = picker.getSelectedFile().getPath();
					hfile.setText(f);
				}
				if (pickerResult == JFileChooser.CANCEL_OPTION) {
					picker.setVisible(false);

				}
			}
		});

		JButton hcompute = new JButton("Compute");
		hcompute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FolderUtil hashcalc = new FolderUtil();
				String md5digest = hashcalc.genHash("md5", hfile.getText());
				String sha1digest = hashcalc.genHash("sha1", hfile.getText());
				hmd5.setText(md5digest);
				hsha1.setText(sha1digest);

			}
		});

		JLabel lblMd = new JLabel("MD5");
		lblMd.setForeground(Color.RED);

		hmd5 = new JLabel("");
		hmd5.setBackground(Color.LIGHT_GRAY);

		JLabel lblSha = new JLabel("SHA1");
		lblSha.setForeground(Color.RED);

		hsha1 = new JLabel("");
		hsha1.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_HashCalc = new GroupLayout(HashCalc);
		gl_HashCalc
				.setHorizontalGroup(gl_HashCalc
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_HashCalc
										.createSequentialGroup()
										.addGroup(
												gl_HashCalc
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addGroup(
																gl_HashCalc
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblFile)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				hfile,
																				GroupLayout.PREFERRED_SIZE,
																				284,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnNewButton_3,
																				GroupLayout.PREFERRED_SIZE,
																				39,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_HashCalc
																		.createSequentialGroup()
																		.addGap(159)
																		.addComponent(
																				hcompute))
														.addGroup(
																gl_HashCalc
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblMd)
																		.addGap(18)
																		.addComponent(
																				hmd5,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addGroup(
																gl_HashCalc
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblSha)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				hsha1,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addContainerGap(49, Short.MAX_VALUE)));
		gl_HashCalc
				.setVerticalGroup(gl_HashCalc
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_HashCalc
										.createSequentialGroup()
										.addGap(51)
										.addGroup(
												gl_HashCalc
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblFile)
														.addComponent(
																hfile,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnNewButton_3))
										.addGap(37)
										.addComponent(hcompute)
										.addGap(46)
										.addGroup(
												gl_HashCalc
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblMd)
														.addComponent(hmd5))
										.addGap(32)
										.addGroup(
												gl_HashCalc
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblSha)
														.addComponent(hsha1))
										.addContainerGap(284, Short.MAX_VALUE)));
		HashCalc.setLayout(gl_HashCalc);
		contentPane.setLayout(gl_contentPane);
	}
}
