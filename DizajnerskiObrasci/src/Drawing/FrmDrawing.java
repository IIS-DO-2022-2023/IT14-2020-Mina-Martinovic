package Drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import geometry1.Circle1;
import geometry1.Donut1;
import geometry1.Line1;
import geometry1.Point1;
import geometry1.Rectangle1;

public class FrmDrawing extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> dlmBoje = new DefaultListModel<String>();
	private PanelDrawing pnlDrawing = new PanelDrawing();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					SwingUtilities.updateComponentTreeUI(frame);


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
	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// centralni panel
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_pnlCenter.rowHeights = new int[] { 33, 0, 0, 0, 0, 0, 0 };
		gbl_pnlCenter.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlCenter.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		pnlCenter.setLayout(gbl_pnlCenter);



		// izbor oblika za crtanje
		JLabel lblIzaberiOblik = new JLabel("Izaberi oblik:");
		GridBagConstraints gbc_izaberiOblik = new GridBagConstraints();
		gbc_izaberiOblik.anchor = GridBagConstraints.EAST;
		gbc_izaberiOblik.insets = new Insets(0, 0, 5, 5);
		gbc_izaberiOblik.gridx = 3;
		gbc_izaberiOblik.gridy = 0;
		pnlCenter.add(lblIzaberiOblik, gbc_izaberiOblik);

		JComboBox<String> cbxIzaberiOblik = new JComboBox<String>();
		cbxIzaberiOblik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlmBoje.addElement(cbxIzaberiOblik.getSelectedItem().toString());

				switch (cbxIzaberiOblik.getSelectedItem().toString()) {
				case "Point":
					PanelDrawing.drawingObject = "Point";
					break;
				case "Line":
					PanelDrawing.drawingObject = "Line";
					break;
				case "Circle":
					PanelDrawing.drawingObject = "Circle";
					break;
				case "Donut":
					PanelDrawing.drawingObject = "Donut";
					break;
				case "Rectangle":
					PanelDrawing.drawingObject = "Rectangle";
					break;
				}
			}
		});
		cbxIzaberiOblik
		.setModel(new DefaultComboBoxModel<String>(new String[] { 
				"Point",
				"Line",
				"Circle",
				"Donut",
				"Rectangle"
		}));
		GridBagConstraints gbc_cbxIzaberiBoju = new GridBagConstraints();
		gbc_cbxIzaberiBoju.insets = new Insets(0, 0, 5, 0);
		gbc_cbxIzaberiBoju.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxIzaberiBoju.gridx = 4;
		gbc_cbxIzaberiBoju.gridy = 0;
		pnlCenter.add(cbxIzaberiOblik, gbc_cbxIzaberiBoju);



		// odabir boje
		JButton btnOdabirBoje = new JButton("Odaberi boju");
		btnOdabirBoje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JColorChooser();
				Color color = JColorChooser.showDialog(null, "Odaberi boju", Color.blue);
				PanelDrawing.color = color;
			}
		});


		GridBagConstraints gbc_lbOdaberiBoju = new GridBagConstraints();
		gbc_lbOdaberiBoju.anchor = GridBagConstraints.EAST;
		gbc_lbOdaberiBoju.insets = new Insets(0, 0, 5, 5);
		gbc_lbOdaberiBoju.gridx = 4;
		gbc_lbOdaberiBoju.gridy = 1;
		pnlCenter.add(btnOdabirBoje, gbc_lbOdaberiBoju);


		// brisanje
		JButton btnBrisanje = new JButton("Obrisi");
		btnBrisanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i=PanelDrawing.shapes.size()-1; i>=0; i--) {
					if(PanelDrawing.shapes.get(i).isSelected() == true) {
						int reply = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete Vas objekat?");
						if(reply == JOptionPane.YES_OPTION) {
							PanelDrawing.shapes.remove(i);
						}
					}
				}
			}
		});


		GridBagConstraints gbc_lbBrisanje = new GridBagConstraints();
		gbc_lbBrisanje.anchor = GridBagConstraints.EAST;
		gbc_lbBrisanje.insets = new Insets(0, 0, 5, 5);
		gbc_lbBrisanje.gridx = 3;
		gbc_lbBrisanje.gridy = 1;
		pnlCenter.add(btnBrisanje, gbc_lbBrisanje);


		// modifikacija
		JButton btnModifikacija = new JButton("Izmeni");
		btnModifikacija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// prolazimo kroz sve promenljive kako bi proverili koja je selektovana
				for (int i=PanelDrawing.shapes.size()-1; i>=0; i--) {
					if(PanelDrawing.shapes.get(i).isSelected()) {
						// kreiramo dijalog
						if (PanelDrawing.shapes.get(i) instanceof Point1) {
							try {
								DlgPoint dialog = new DlgPoint();
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						else if(PanelDrawing.shapes.get(i) instanceof Donut1) {							
							try {
								Donut1 d1 = (Donut1) PanelDrawing.shapes.get(i);
								DlgDonut dialog = new DlgDonut(
										d1.getCenter().getX(),
										d1.getCenter().getY(),
										d1.getRadius(),
										d1.getInnerRadius());
										//d1.getColor()
				
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						else if(PanelDrawing.shapes.get(i) instanceof Circle1) {

							try {
								Circle1 c1 = (Circle1) PanelDrawing.shapes.get(i);
								DlgCircle dialog = new DlgCircle(
										c1.getCenter().getX(),
										c1.getCenter().getY(),
										c1.getRadius());
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						else if(PanelDrawing.shapes.get(i) instanceof Rectangle1) {
							try {
								Rectangle1 r1 = (Rectangle1) PanelDrawing.shapes.get(i);
								DlgRectangle dialog = new DlgRectangle(
										r1.getUpperLeftPoint().getX(),
										r1.getUpperLeftPoint().getY(),
										r1.getWidth(),
										r1.getHeight());
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						else if(PanelDrawing.shapes.get(i) instanceof Line1) {
							try {
								DlgLine dialog = new DlgLine();
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						
						break;
					}
				}


			}
		});




		GridBagConstraints gbc_lbModifikacija = new GridBagConstraints();
		gbc_lbModifikacija.anchor = GridBagConstraints.EAST;
		gbc_lbModifikacija.insets = new Insets(0, 0, 5, 5);
		gbc_lbModifikacija.gridx = 3;
		gbc_lbModifikacija.gridy = 2;
		pnlCenter.add(btnModifikacija, gbc_lbModifikacija);



		// crtanje
		PanelDrawing pnlDrawing = new PanelDrawing();
		PanelListener listener = new PanelListener();
		pnlDrawing.addMouseListener(listener);

		GridBagConstraints gbl_pnlDrawing = new GridBagConstraints();
		gbl_pnlDrawing.anchor = GridBagConstraints.SOUTH;
		gbl_pnlDrawing.insets = new Insets(0, 0, 5, 5);
		gbl_pnlDrawing.gridx = 3;
		gbl_pnlDrawing.gridy = 3;
		pnlCenter.add(pnlDrawing, gbl_pnlDrawing);




	}

}