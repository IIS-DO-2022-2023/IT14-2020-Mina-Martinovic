package mvc;

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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawingFrame extends JFrame{
	
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	private DrawingModel model;
	private JPanel contentPane;
	private DefaultListModel<String> dlmBoje = new DefaultListModel<String>();
	private DrawingView pnlDrawing = new DrawingView();
	
	public DrawingView getView() {
		return view;
	}

	public void setDrawingController(DrawingController drawingController) {
		this.controller = drawingController;
	}
	
	/*
	/**
	 * Launch the application.
	 */
	/*
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
		
		GridBagConstraints gbc_lbModifikacija = new GridBagConstraints();
		gbc_lbModifikacija.anchor = GridBagConstraints.EAST;
		gbc_lbModifikacija.insets = new Insets(0, 0, 5, 5);
		gbc_lbModifikacija.gridx = 2;
		gbc_lbModifikacija.gridy = 2;
		pnlCenter.add(btnModifikacija, gbc_lbModifikacija);

		// bringToFront
		JButton btnBringToFront = new JButton("Bring to Front");
		btnBringToFront.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			controller.bringToFront(e);
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
			controller.btnSendToBack(e);
			}
		});
		
		GridBagConstraints gbc_lbSendToBack = new GridBagConstraints();
		gbc_lbSendToBack.anchor = GridBagConstraints.EAST;
		gbc_lbSendToBack.insets = new Insets(0, 0, 5, 5);
		gbc_lbSendToBack.gridx = 3;
		gbc_lbSendToBack.gridy = 2;
		pnlCenter.add(btnSendToBack, gbc_lbSendToBack);
		
		//undo
		JButton btnUndo = new JButton("Undo");  // undo button
		btnUndo.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.btnUndo(e);
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
		btnRedo.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.btnRedo(e);
				}
	    }); //handler
						
		GridBagConstraints gbc_lbRedo = new GridBagConstraints();
		gbc_lbRedo.anchor = GridBagConstraints.EAST;
		gbc_lbRedo.insets = new Insets(0, 0, 5, 5);
		gbc_lbRedo.gridx = 4;
		gbc_lbRedo.gridy = 2;
		pnlCenter.add(btnRedo, gbc_lbRedo);
		
		// crtanje
		DrawingModel model = new DrawingModel(null);
		DrawingView pnlDrawing = new DrawingView();
		DrawingController controller = new DrawingController(model, this);
		
        pnlDrawing.repaint();
		//IZMENI!!!!!!!

		GridBagConstraints gbl_pnlDrawing = new GridBagConstraints();
		gbl_pnlDrawing.anchor = GridBagConstraints.SOUTH;
		gbl_pnlDrawing.insets = new Insets(0, 0, 5, 5);
		gbl_pnlDrawing.gridx = 1;
		gbl_pnlDrawing.gridy = 3;
		pnlCenter.add(pnlDrawing, gbl_pnlDrawing);

	}


	
	//dodas dugmice sve to jev frmDrawing i listeneri 
	//jer ja odavde pozivma metodekoje se nalaze u kontroleru
	//kada dodam event pisem controller.undo recimo
	
	
}