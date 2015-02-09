package LLIGUES.LLIGUES;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import LLIGUES.LLIGUES.PaneLligues;

import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class CreaLliga extends JFrame {
	private int contadorequips=0;
	private Lliga lliganova;
	private JList list=null;
	private JList llistaequips=null;
    private DefaultListModel model = new DefaultListModel();
	private JPanel contentPane;
	private JTextField EquipNom;
	private JTextField lliganom;
	private ArrayList<Equip> arraylliga = new ArrayList<Equip>();
	private PaneLligues f;
	Map<String, Equip> Mapequips = new HashMap<String,Equip>();
	private JTable table = new JTable(new DefaultTableModel(new Object[]{"Nom Equip", "Punts","P G","P P","P E"}, 0));
	DefaultTableModel model1 = (DefaultTableModel) table.getModel();
	/**
	 * Launch the application.
	 */

	public void creartaula(Lliga lliga){

		Iterator it = Mapequips.entrySet().iterator();
		while(it.hasNext()){
      	  Map.Entry eq = (Entry) it.next();
      	  Equip rows = (Equip) eq.getValue();
      	  model1.addRow(new Object[]{rows.nom, rows.puntuacio, rows.partitsguanyats, rows.partitsperduts, rows.partitsempatats});
		}
		table.setModel(model1);
	};

	/**
	 * Create the frame.
	 */
	public CreaLliga() {
        
		setTitle("Crear una Lliga");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow]", "[][][][grow][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Nom de la Lliga");
		contentPane.add(lblNewLabel, "cell 1 1");
		
		JLabel lblEquip = new JLabel("Equip");
		contentPane.add(lblEquip, "cell 1 3");
		
		llistaequips = new JList();
		contentPane.add(llistaequips, "cell 2 3 1 3,grow");
		
		EquipNom = new JTextField();
		contentPane.add(EquipNom, "cell 1 4,growx");
		EquipNom.setColumns(10);
		
		lliganom = new JTextField();
		contentPane.add(lliganom, "cell 2 1");
		lliganom.setColumns(10);
		
		JButton btnNewButton = new JButton("Afegir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Equip equipnou = new Equip(EquipNom.getText());
				
				if(Mapequips.isEmpty()){
					Mapequips.put(equipnou.getNom(), equipnou);
					model.add(contadorequips,equipnou);
					contadorequips ++;
					llistaequips.setModel(model);
				}
				else{
					if(Mapequips.containsKey(equipnou.getNom())){
						JOptionPane.showMessageDialog(contentPane,
								"L'equip ja existeix.",
								"Incorrecte",
								JOptionPane.ERROR_MESSAGE);
					}
					else{
						Mapequips.put(equipnou.getNom(), equipnou);
						model.add(contadorequips,equipnou);
						contadorequips ++;
						llistaequips.setModel(model);
					}
				}
				
			}
		});
		contentPane.add(btnNewButton, "cell 1 5");
		
		list = new JList();
		contentPane.add(list, "flowx,cell 2 6");
		
		
		JButton btnTreure = new JButton("Treure");
		btnTreure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Equip selected = (Equip) llistaequips.getSelectedValue();
				model.removeElement(selected);
				llistaequips.setModel(model);
			}
		});
		contentPane.add(btnTreure, "cell 1 6");
		
		
  
		    
		JButton btnAcabat = new JButton("Acabat");
		btnAcabat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    lliganova.setNom(lliganom.getText());
				
				lliganova.setMapequips(Mapequips);
				
				creartaula(lliganova);
				setVisible(false);
			}
		});
		contentPane.add(btnAcabat, "cell 2 6");
		
		JList list_1 = new JList();
		contentPane.add(list_1, "cell 2 6");
	}


	public Lliga getLliganova() {
		return lliganova;
	}


	public void setLliganova(Lliga lliganova) {
		this.lliganova = lliganova;
		System.out.println("entro a lliga nova");
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

}
