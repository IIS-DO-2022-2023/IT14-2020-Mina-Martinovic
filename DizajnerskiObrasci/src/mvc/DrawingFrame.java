package mvc;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Drawing.DlgCircle;
import Drawing.DlgDonut;
import Drawing.DlgLine;
import Drawing.DlgPoint;
import Drawing.DlgRectangle;
import Drawing.FrmDrawing;
import Drawing.PanelListener;
import geometry1.Circle;
import geometry1.Donut;
import geometry1.Line;
import geometry1.Point;
import geometry1.Rectangle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DrawingFrame extends JFrame{
	//implements PropertyChangeListener
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	//private DrawingModel model;
	
	private ButtonGroup shapesGroup = new ButtonGroup();

	private JButton btnModifikacija;
	private JButton btnBrisanje;
	
	private JButton btnUndo;
	private JButton btnRedo;
	
	private JPanel contentPane;
	private DefaultListModel<String> dlmBoje = new DefaultListModel<String>();
	//private DrawingView pnlDrawing = new DrawingView();
	private JList<String> activityLog;
	DefaultListModel<String> dlmList = new DefaultListModel<String>();
	
	public JList<String> getActivityLog() {
		return activityLog;
	}

	public void setActivityLog(JList<String> activityLog) {
		this.activityLog = activityLog;
	}
	
	public JButton getbtnModifikacija() {
		return btnModifikacija;
	}

	public void setbtnModifikacija(JButton btnModifikacija) {
		this.btnModifikacija = btnModifikacija;
	}

	public JButton getbtnBrisanje() {
		return btnBrisanje;
	}

	public void setbtnBrisanje(JButton btnBrisanje) {
		this.btnBrisanje = btnBrisanje;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
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

	public DefaultListModel<String> getDlmBoje() {
		return dlmBoje;
	}

	public void setDlmBoje(DefaultListModel<String> dlmBoje) {
		this.dlmBoje = dlmBoje;
	}
	
	public DefaultListModel<String> getDlmList() {
		return dlmList;
	}

	public void setDlmList(DefaultListModel<String> dlmList) {
		this.dlmList = dlmList;
	}
/*
	public void setDrawingController(DrawingController drawingController) {
		this.controller = drawingController;
	}
*/	
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
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().add(view, BorderLayout.CENTER);

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
		gbc_izaberiOblik.gridx = 2;
		gbc_izaberiOblik.gridy = 0;
		pnlCenter.add(lblIzaberiOblik, gbc_izaberiOblik);

		JComboBox<String> cbxIzaberiOblik = new JComboBox<String>();
		cbxIzaberiOblik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlmBoje.addElement(cbxIzaberiOblik.getSelectedItem().toString());

				switch (cbxIzaberiOblik.getSelectedItem().toString()) {
				case "Point":
					DrawingModel.drawingObject = "Point";
					break;
				case "Line":
					DrawingModel.drawingObject = "Line";
					break;
				case "Circle":
					DrawingModel.drawingObject = "Circle";
					break;
				case "Donut":
					DrawingModel.drawingObject = "Donut";
					break;
				case "Rectangle":
					DrawingModel.drawingObject = "Rectangle";
					break;
				case "Hexagon":
					DrawingModel.drawingObject = "Hexagon";
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
				"Rectangle",
				"Hexagon"
		}));
		GridBagConstraints gbc_cbxIzaberiBoju = new GridBagConstraints();
		gbc_cbxIzaberiBoju.insets = new Insets(0, 0, 5, 0);
		gbc_cbxIzaberiBoju.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxIzaberiBoju.gridx = 3;
		gbc_cbxIzaberiBoju.gridy = 0;
		pnlCenter.add(cbxIzaberiOblik, gbc_cbxIzaberiBoju);



		// odabir boje
		JButton btnOdabirBoje = new JButton("Odaberi boju");
		btnOdabirBoje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JColorChooser();
				Color color = JColorChooser.showDialog(null, "Odaberi boju", Color.blue);
				DrawingModel.color = color;
			}
		});


		GridBagConstraints gbc_lbOdaberiBoju = new GridBagConstraints();
		gbc_lbOdaberiBoju.anchor = GridBagConstraints.EAST;
		gbc_lbOdaberiBoju.insets = new Insets(0, 0, 5, 5);
		gbc_lbOdaberiBoju.gridx = 4;
		gbc_lbOdaberiBoju.gridy = 0;
		pnlCenter.add(btnOdabirBoje, gbc_lbOdaberiBoju);


		// brisanje
		JButton btnBrisanje = new JButton("Obrisi");
		btnBrisanje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.deleteObject(e);
			}
	
		});
		//getbtnBrisanje().setVisible(false);

		
		GridBagConstraints gbc_lbBrisanje = new GridBagConstraints();
		gbc_lbBrisanje.anchor = GridBagConstraints.EAST;
		gbc_lbBrisanje.insets = new Insets(0, 0, 5, 5);
		gbc_lbBrisanje.gridx = 2;
		gbc_lbBrisanje.gridy = 1;
		pnlCenter.add(btnBrisanje, gbc_lbBrisanje);


		// modifikacija
		JButton btnModifikacija = new JButton("Izmeni");
		btnModifikacija.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.modifyObject(e);
			}
		});
		//getbtnModifikacija().setVisible(false);
		
		GridBagConstraints gbc_lbModifikacija = new GridBagConstraints();
		gbc_lbModifikacija.anchor = GridBagConstraints.EAST;
		gbc_lbModifikacija.insets = new Insets(0, 0, 5, 5);
		gbc_lbModifikacija.gridx = 2;
		gbc_lbModifikacija.gridy = 2;
		pnlCenter.add(btnModifikacija, gbc_lbModifikacija);

		//select
		JButton btnSelect = new JButton("Select");
		btnSelect.setBackground(Color.WHITE);
		btnSelect.setForeground(Color.BLACK);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.repaint();
			}
		});
		
		GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
		gbc_tglbtnSelect.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnSelect.gridx = 0;
		gbc_tglbtnSelect.gridy = 1;
		pnlCenter.add(btnSelect, gbc_tglbtnSelect);
		
		// bringToFront
		JButton btnBringToFront = new JButton("Bring to Front");
		btnBringToFront.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			controller.BringToFront(e);
			}
		});
				
		GridBagConstraints gbc_lbBringToFront = new GridBagConstraints();
		gbc_lbBringToFront.anchor = GridBagConstraints.EAST;
		gbc_lbBringToFront.insets = new Insets(0, 0, 5, 5);
		gbc_lbBringToFront.gridx = 3;
		gbc_lbBringToFront.gridy = 1;
		pnlCenter.add(btnBringToFront, gbc_lbBringToFront);
		
		
		// sendToBack
		JButton btnSendToBack = new JButton("Send to Back");
		btnSendToBack.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			controller.BringToBack(e);
			}
		});
		
		GridBagConstraints gbc_lbSendToBack = new GridBagConstraints();
		gbc_lbSendToBack.anchor = GridBagConstraints.EAST;
		gbc_lbSendToBack.insets = new Insets(0, 0, 5, 5);
		gbc_lbSendToBack.gridx = 3;
		gbc_lbSendToBack.gridy = 2;
		pnlCenter.add(btnSendToBack, gbc_lbSendToBack);
		
		
		//undo
		JButton btnUndo = new JButton("Undo"); 
		final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
		btnUndo.setBackground(VERY_LIGHT_BLUE);
		btnUndo.setForeground(Color.BLACK);// undo button
		//this.btnUndo.setVisible(false);
		btnUndo.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.undo();
				}
	    }); //handler
						
		GridBagConstraints gbc_lbUndo = new GridBagConstraints();
		gbc_lbUndo.anchor = GridBagConstraints.EAST;
		gbc_lbUndo.insets = new Insets(0, 0, 5, 5);
		gbc_lbUndo.gridx = 4;
		gbc_lbUndo.gridy = 1;
		pnlCenter.add(btnUndo, gbc_lbUndo);

		//redo
		JButton btnRedo = new JButton("Redo");  // undo button
		final Color VERY_LIGHT_RED = new Color(255, 102, 102);
		btnUndo.setBackground(VERY_LIGHT_RED);
		//this.btnRedo.setVisible(false);
		btnRedo.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.redo();
				}
	    }); //handler
						
		GridBagConstraints gbc_lbRedo = new GridBagConstraints();
		gbc_lbRedo.anchor = GridBagConstraints.EAST;
		gbc_lbRedo.insets = new Insets(0, 0, 5, 5);
		gbc_lbRedo.gridx = 4;
		gbc_lbRedo.gridy = 2;
		pnlCenter.add(btnRedo, gbc_lbRedo);
		
		JButton btnSerialize = new JButton("Save");
		btnSerialize.setBackground(Color.WHITE);
		btnSerialize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.serialize();
			}
		});
		
		GridBagConstraints gbc_btnSerialize = new GridBagConstraints();
		gbc_btnSerialize.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbRedo.insets = new Insets(0, 0, 5, 5);
		gbc_btnSerialize.gridx = 11;
		gbc_btnSerialize.gridy = 1;
		pnlCenter.add(btnSerialize, gbc_btnSerialize);
		
		
		// crtanje
		/*
		DrawingModel model = new DrawingModel();
		DrawingView pnlDrawing = new DrawingView();
		DrawingController controller = new DrawingController(model, this);
		
        pnlDrawing.repaint();
		//IZMENI!!!!!!!
		 * */
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
					controller.mouseClicked(e);			
			}
		});
		shapesGroup.add(btnBrisanje);
		shapesGroup.add(btnModifikacija);
		shapesGroup.add(btnSelect);
		
		/*
		GridBagConstraints gbl_pnlDrawing = new GridBagConstraints();
		gbl_pnlDrawing.anchor = GridBagConstraints.SOUTH;
		gbl_pnlDrawing.insets = new Insets(0, 0, 5, 5);
		gbl_pnlDrawing.gridx = 1;
		gbl_pnlDrawing.gridy = 3;
		pnlCenter.add(pnlDrawing, gbl_pnlDrawing);
*/
		JPanel pnlSouth = new JPanel();
		getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		GridBagLayout gbl_pnlSouth = new GridBagLayout();
		gbl_pnlSouth.columnWidths = new int[]{402, 7, 0};
		gbl_pnlSouth.rowHeights = new int[]{53, 25};
		gbl_pnlSouth.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_pnlSouth.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnlSouth.setLayout(gbl_pnlSouth);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 10;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlSouth.add(scrollPane, gbc_scrollPane);
		
		activityLog = new JList<String>();
		activityLog.setValueIsAdjusting(true);
		activityLog.setBackground(Color.LIGHT_GRAY);
		activityLog.setModel(dlmList);
		activityLog.setFont(new Font("Lucida Console", Font.BOLD, 12));
		scrollPane.setViewportView(activityLog);
		view.setBackground(Color.WHITE);
		scrollPane.setBounds(586, 452, 784, 461);
	}

	/*
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals("delete")) {
			if(controller.getModel().getSelectedShapes().size()  > 0) {
				getbtnBrisanje().setVisible((boolean)arg0.getNewValue());
			} else {
				getbtnBrisanje().setVisible((boolean)arg0.getOldValue());
			}
			
		} else if (arg0.getPropertyName().equals("modify")) {
			if(controller.getModel().getSelectedShapes().size() == 1) {
				getbtnModifikacija().setVisible((boolean)arg0.getNewValue());
			} else {
				getbtnModifikacija().setVisible((boolean)arg0.getOldValue());
			}
			
		} else if (arg0.getPropertyName().equals("redo")) {
			if(controller.getCommandsReverse().size() > 0) {
				getBtnRedo().setVisible((boolean)arg0.getNewValue());
			} else {
				getBtnRedo().setVisible((boolean)arg0.getOldValue());
			}
		}  else if (arg0.getPropertyName().equals("undo")) {
			if(controller.getCommandsNormal().size() > 0) {
				getBtnUndo().setVisible((boolean)arg0.getNewValue());
			} else {
				getBtnUndo().setVisible((boolean)arg0.getOldValue());
			}				
			
		}		
		
	}
	*/

	
	//dodas dugmice sve to jev frmDrawing i listeneri 
	//jer ja odavde pozivma metodekoje se nalaze u kontroleru
	//kada dodam event pisem controller.undo recimo
	
	
}