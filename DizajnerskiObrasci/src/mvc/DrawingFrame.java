package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class DrawingFrame extends JFrame{

	private JPanel contentPane;
	
	private DrawingView view = new DrawingView();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawingFrame frame = new DrawingFrame();
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
	public DrawingFrame() {
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

				switch (cbxIzaberiOblik.getSelectedItem().toString()) {
				case "Point":
					//PanelDrawing.drawingObject = "Point";
					break;
				case "Line":
					//PanelDrawing.drawingObject = "Line";
					break;
				case "Circle":
					//PanelDrawing.drawingObject = "Circle";
					break;
				case "Donut":
					//PanelDrawing.drawingObject = "Donut";
					break;
				case "Rectangle":
					//PanelDrawing.drawingObject = "Rectangle";
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
				//PanelDrawing.color = color;
			}
		});


		GridBagConstraints gbc_lbOdaberiBoju = new GridBagConstraints();
		gbc_lbOdaberiBoju.anchor = GridBagConstraints.EAST;
		gbc_lbOdaberiBoju.insets = new Insets(0, 0, 5, 0);
		gbc_lbOdaberiBoju.gridx = 4;
		gbc_lbOdaberiBoju.gridy = 1;
		pnlCenter.add(btnOdabirBoje, gbc_lbOdaberiBoju);


		// brisanje
		JButton btnBrisanje = new JButton("Obrisi");
		btnBrisanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
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

				


			}
		});




		GridBagConstraints gbc_lbModifikacija = new GridBagConstraints();
		gbc_lbModifikacija.anchor = GridBagConstraints.EAST;
		gbc_lbModifikacija.insets = new Insets(0, 0, 5, 5);
		gbc_lbModifikacija.gridx = 3;
		gbc_lbModifikacija.gridy = 2;
		pnlCenter.add(btnModifikacija, gbc_lbModifikacija);
														
														JPanel panel = new JPanel();
														GridBagConstraints gbc_panel = new GridBagConstraints();
														gbc_panel.fill = GridBagConstraints.BOTH;
														gbc_panel.gridx = 4;
														gbc_panel.gridy = 5;
														pnlCenter.add(panel, gbc_panel);
																panel.add(view);
														
														
																view.setBackground(Color.white);
																view.setPreferredSize(new Dimension(2000, 1800));




	}

	public DrawingView getView() {
		return view;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}
	
	

}
