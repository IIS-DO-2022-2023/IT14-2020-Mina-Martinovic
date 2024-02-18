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
		JLabel lblChooseShape = new JLabel("Choose shape:");
		GridBagConstraints gbc_chooseShape = new GridBagConstraints();
		gbc_chooseShape.anchor = GridBagConstraints.EAST;
		gbc_chooseShape.insets = new Insets(0, 0, 5, 5);
		gbc_chooseShape.gridx = 3;
		gbc_chooseShape.gridy = 0;
		pnlCenter.add(lblChooseShape, gbc_chooseShape);

		JComboBox<String> cbxChooseShape = new JComboBox<String>();
		cbxChooseShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switch (cbxChooseShape.getSelectedItem().toString()) {
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
		cbxChooseShape
		.setModel(new DefaultComboBoxModel<String>(new String[] { 
				"Point",
				"Line",
				"Circle",
				"Donut",
				"Rectangle"
		}));
		GridBagConstraints gbc_cbxChooseShape = new GridBagConstraints();
		gbc_cbxChooseShape.insets = new Insets(0, 0, 5, 0);
		gbc_cbxChooseShape.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxChooseShape.gridx = 4;
		gbc_cbxChooseShape.gridy = 0;
		pnlCenter.add(cbxChooseShape, gbc_cbxChooseShape);

		// odabir boje
		JButton btnChooseColor = new JButton("Choose color");
		btnChooseColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JColorChooser();
				Color color = JColorChooser.showDialog(null, "Choose color", Color.blue);
				//PanelDrawing.color = color;
			}
		});


		GridBagConstraints gbc_lbChooseColor = new GridBagConstraints();
		gbc_lbChooseColor.anchor = GridBagConstraints.EAST;
		gbc_lbChooseColor.insets = new Insets(0, 0, 5, 0);
		gbc_lbChooseColor.gridx = 4;
		gbc_lbChooseColor.gridy = 1;
		pnlCenter.add(btnChooseColor, gbc_lbChooseColor);


		// brisanje
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});


		GridBagConstraints gbc_lbDelete = new GridBagConstraints();
		gbc_lbDelete.anchor = GridBagConstraints.EAST;
		gbc_lbDelete.insets = new Insets(0, 0, 5, 5);
		gbc_lbDelete.gridx = 3;
		gbc_lbDelete.gridy = 1;
		pnlCenter.add(btnDelete, gbc_lbDelete);


		// modifikacija
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

	
			}
		});


		GridBagConstraints gbc_lbModify = new GridBagConstraints();
		gbc_lbModify.anchor = GridBagConstraints.EAST;
		gbc_lbModify.insets = new Insets(0, 0, 5, 5);
		gbc_lbModify.gridx = 3;
		gbc_lbModify.gridy = 2;
		pnlCenter.add(btnModify, gbc_lbModify);
														
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
