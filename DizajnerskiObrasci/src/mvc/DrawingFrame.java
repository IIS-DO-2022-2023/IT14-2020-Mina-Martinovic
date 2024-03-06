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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class DrawingFrame extends JFrame implements PropertyChangeListener{

	private JPanel contentPane;
	
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	private String drawingObject = "Point";

	private Color innerColor = Color.white;
	private Color outerColor = Color.black;
	
	private JButton btnDelete;
	private JButton btnModify;
	
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
		setBounds(300, 100, 639, 417);
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
		
				
				
				// brisanje
				btnDelete = new JButton("Delete");
				btnDelete.setEnabled(false);
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						controller.deleteSelectedShapes();
					}
				});
				
				
						GridBagConstraints gbc_lbDelete = new GridBagConstraints();
						gbc_lbDelete.anchor = GridBagConstraints.EAST;
						gbc_lbDelete.insets = new Insets(0, 0, 5, 5);
						gbc_lbDelete.gridx = 1;
						gbc_lbDelete.gridy = 1;
						pnlCenter.add(btnDelete, gbc_lbDelete);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 4;
		gbc_panel_1.gridy = 1;
		pnlCenter.add(panel_1, gbc_panel_1);
		
				// odabir boje
				JButton btnChooseInnerColor = new JButton("Choose inner color");
				panel_1.add(btnChooseInnerColor);
				btnChooseInnerColor.setBackground(Color.WHITE);
				
						JButton btnChooseOuterColor = new JButton("Choose outer color");
						panel_1.add(btnChooseOuterColor);
						btnChooseOuterColor.setForeground(Color.WHITE);
						btnChooseOuterColor.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {				
								Color color = JColorChooser.showDialog(null, "Choose color", outerColor);
								if(color != null)
								{
									outerColor = color;
									btnChooseOuterColor.setBackground(outerColor);
								}
							}
						});
						btnChooseOuterColor.setBackground(Color.BLACK);
				btnChooseInnerColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {			
						Color color = JColorChooser.showDialog(null, "Choose color", innerColor);
						if(color != null)
						{
							innerColor = color;
							btnChooseInnerColor.setBackground(innerColor);
						}

					}
				});
		
				
				
		
				// modifikacija
				btnModify = new JButton("Modify");
				btnModify.setEnabled(false);
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						controller.modifySelectedShape();
					}
				});
				
				
						GridBagConstraints gbc_lbModify = new GridBagConstraints();
						gbc_lbModify.anchor = GridBagConstraints.EAST;
						gbc_lbModify.insets = new Insets(0, 0, 5, 5);
						gbc_lbModify.gridx = 1;
						gbc_lbModify.gridy = 2;
						pnlCenter.add(btnModify, gbc_lbModify);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 4;
		gbc_panel_2.gridy = 2;
		pnlCenter.add(panel_2, gbc_panel_2);
		
		JButton btnToFront = new JButton("To Front");
		panel_2.add(btnToFront);
		
		JButton btnToBack = new JButton("To Back");
		panel_2.add(btnToBack);
		
		JButton btnBringToFront = new JButton("Bring To Front");
		panel_2.add(btnBringToFront);
		
		JButton btnBringToBack = new JButton("Bring To Back");
		panel_2.add(btnBringToBack);
		
		
		JToggleButton tglbtnSelect = new JToggleButton("Select");
		
		
		GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
		gbc_tglbtnSelect.anchor = GridBagConstraints.EAST;
		gbc_tglbtnSelect.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnSelect.gridx = 1;
		gbc_tglbtnSelect.gridy = 3;
		pnlCenter.add(tglbtnSelect, gbc_tglbtnSelect);
		
		
		JComboBox<String> cbxChooseShape = new JComboBox<String>();
		cbxChooseShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tglbtnSelect.setSelected(false);
				
				switch (cbxChooseShape.getSelectedItem().toString()) {
				case "Point":
					drawingObject = "Point";
					break;
				case "Line":
					drawingObject = "Line";
					break;
				case "Circle":
					drawingObject = "Circle";
					break;
				case "Donut":
					drawingObject = "Donut";
					break;
				case "Rectangle":
					drawingObject = "Rectangle";
					break;					
				case "Hexagon":
					drawingObject = "Hexagon";
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
				"Rectangle",
				"Hexagon"
		}));
		GridBagConstraints gbc_cbxChooseShape = new GridBagConstraints();
		gbc_cbxChooseShape.insets = new Insets(0, 0, 5, 0);
		gbc_cbxChooseShape.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxChooseShape.gridx = 4;
		gbc_cbxChooseShape.gridy = 0;
		pnlCenter.add(cbxChooseShape, gbc_cbxChooseShape);
		

														
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 5;
		pnlCenter.add(panel, gbc_panel);
		
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(tglbtnSelect.isSelected())					
				{
					controller.selectShape(e);
				}
				else 
				{
					controller.drawingShape(e);
				}
//			
			}
		});
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

	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public String getDrawingObject() {
		return drawingObject;
	}

	public void setDrawingObject(String drawingObject) {
		this.drawingObject = drawingObject;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public Color getOuterColor() {
		return outerColor;
	}

	public void setOuterColor(Color outerColor) {
		this.outerColor = outerColor;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		
		if(event.getPropertyName().equals("Delete"))
		{
			btnDelete.setEnabled((boolean) event.getNewValue());
		}
		
		else if(event.getPropertyName().equals("Modify"))
		{
			btnModify.setEnabled((boolean) event.getNewValue());
		}
	}
	
	

}
