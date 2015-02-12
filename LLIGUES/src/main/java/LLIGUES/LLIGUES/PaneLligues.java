package LLIGUES.LLIGUES;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JButton;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.ScrollPane;
import java.io.File;
import java.io.IOException;

public class PaneLligues extends JFrame {
	Lliga lligueta = new Lliga(" ");
	private JPanel contentPane;
	private JTable table = new JTable(new DefaultTableModel(new Object[] {
			"Nom Equip", "Punts", "P G", "P P", "P E" }, 0));
	CreaLliga frame;
	CreaPartit framec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaneLligues framelliga = new PaneLligues();
					framelliga.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PaneLligues() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Nova Lliga");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					frame = new CreaLliga();
					frame.setLliganova(lligueta);
					frame.setTable(table);
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Obre Lliga");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser openFile = new JFileChooser();
				openFile.showOpenDialog(null);
				File lligaxml = openFile.getSelectedFile();

				final SAXParserFactory spfac = SAXParserFactory.newInstance();

				SAXParser sp = null;
				try {
					sp = spfac.newSAXParser();
				} catch (ParserConfigurationException e1) {

					e1.printStackTrace();
				} catch (SAXException e1) {

					e1.printStackTrace();
				}
				try {
					Llegirxml llegir = new Llegirxml();
					llegir.setLligueta(lligueta);
					llegir.setTable(table);
					sp.parse(new File(lligaxml.getName()), llegir);

				} catch (SAXException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Desa Lliga");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (lligueta.getNom() == " ") {
					JOptionPane.showMessageDialog(contentPane,
							"No hi ha cap lliga per guardar", "Incorrecte",
							JOptionPane.ERROR_MESSAGE);
				} else {
					CrearXml crearfitxer = new CrearXml();
					crearfitxer.guardardades(lligueta);
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Sortir");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);

		JMenu mnNewMenu_1 = new JMenu("Partit");
		menuBar.add(mnNewMenu_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane
				.setLayout(new MigLayout("", "[][grow][][]", "[][][grow][]"));

		JLabel lblNewLabel = new JLabel("Lliga");
		contentPane.add(lblNewLabel, "cell 2 1");

		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, "cell 1 2");

		JButton btnNewButton = new JButton("Afegeix Nova Jornada");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					framec = new CreaPartit(lligueta);
					framec.setVisible(true);
					framec.setTable(table);

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton, "cell 1 6");
	}

}
