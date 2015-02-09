package LLIGUES.LLIGUES;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class CreaPartit extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField GolPrimer = new JFormattedTextField (new Integer(0));
	private JFormattedTextField GolSegon = new JFormattedTextField (new Integer(0));
	private Lliga lligueta;
	private JComboBox comboBox_1;
	JComboBox comboBox;
	private int contador =0;
	private String[] combollista;
	Map<String, Equip> Mapequips = new HashMap<String,Equip>();
	private JTable table = new JTable(new DefaultTableModel(new Object[]{"Nom Equip", "Punts","P G","P P","P E"}, 0));
	DefaultTableModel model2 = (DefaultTableModel) table.getModel();
	/**
	 * Launch the application.
	 */
	
	public void renovartaula(Lliga lliga){

		Iterator it = lliga.getMapequips().entrySet().iterator();
		while(it.hasNext()){
      	  Map.Entry eq =  (Entry) it.next();
      	  Equip rows = (Equip) eq.getValue();
      	  model2.addRow(new Object[]{rows.nom, rows.puntuacio, rows.partitsguanyats, rows.partitsperduts, rows.partitsempatats});
		}
		table.setModel(model2);
	};
	
	/**
	 * Create the frame.
	 */
	public CreaPartit(Lliga l) {
		setTitle("Partit Nou");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow][grow]", "[][][][][][][]"));
		lligueta = l;
		
		combollista = new String[lligueta.getMapequips().size()];
		
		Iterator it = lligueta.getMapequips().entrySet().iterator();
		while(it.hasNext()){
      	  Map.Entry eq = (Entry) it.next();
      	  Equip rows = (Equip) eq.getValue();
      	  combollista[contador] = rows.getNom();
      	  contador++;
		}
			
		
		
		comboBox=new JComboBox(combollista);
		comboBox_1=new JComboBox(combollista);
		
		
	
		
		contentPane.add(comboBox, "cell 2 2,growx");
		
		
		contentPane.add(GolPrimer, "cell 3 2,growx");
		GolPrimer.setColumns(10);
		
		
		contentPane.add(comboBox_1, "cell 2 4,growx");
		
		contentPane.add(GolSegon, "cell 3 4,growx");
		GolSegon.setColumns(10);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomequipprimer = (String) comboBox.getSelectedItem();
				String nomequipsegon = (String) comboBox_1.getSelectedItem();
				if(nomequipprimer.equals(nomequipsegon) || (GolPrimer==null || GolSegon==null)){
					JOptionPane.showMessageDialog(contentPane,
							"Els equips no poden ser iguals o falta un resultat.",
							"Incorrecte",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int gol1 = Integer.parseInt(GolPrimer.getText());
					int gol2 = Integer.parseInt(GolSegon.getText());
					if(gol1>gol2){
						
						lligueta.getMapequips().get(nomequipprimer).setPartitsguanyats(1);
						lligueta.getMapequips().get(nomequipsegon).setPartitsperduts(1);
						lligueta.getMapequips().get(nomequipprimer).setPuntuacio(3);
						renovartaula(lligueta);
						setVisible(false);
					}
					if(gol1==gol2){
						lligueta.getMapequips().get(nomequipprimer).setPartitsempatats(1);
						lligueta.getMapequips().get(nomequipsegon).setPartitsempatats(1);
						lligueta.getMapequips().get(nomequipprimer).setPuntuacio(1);
						lligueta.getMapequips().get(nomequipsegon).setPuntuacio(1);
						renovartaula(lligueta);
						setVisible(false);
					}
					if(gol1<gol2){
						
						lligueta.getMapequips().get(nomequipsegon).setPartitsguanyats(1);
						lligueta.getMapequips().get(nomequipprimer).setPartitsperduts(1);
						lligueta.getMapequips().get(nomequipsegon).setPuntuacio(3);
						renovartaula(lligueta);
						setVisible(false);
					}
					
				}
				
				
				
			}
		});
		contentPane.add(btnNewButton, "cell 2 6");
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		contentPane.add(btnNewButton_1, "cell 3 6");
	}
	
	

	public Lliga getLligueta() {
		return lligueta;
	}

	public void setLligueta(Lliga lligueta) {
		this.lligueta = lligueta;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
}
