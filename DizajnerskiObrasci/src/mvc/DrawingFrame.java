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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import Drawing.DlgCircle;
import Drawing.DlgDonut;
import Drawing.DlgLine;
import Drawing.DlgPoint;
import Drawing.DlgRectangle;
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

	private DrawingView view = new DrawingView();
	private DrawingController controller;
	
	private ButtonGroup shapesGroup = new ButtonGroup();
	
	private JToggleButton tglBtnSelect;
	private JToggleButton tglBtnModify;
	private JToggleButton tglBtnDelete;

	//private JButton btnModifikacija;
	//private JButton btnBrisanje;
	
	private JButton btnUndo;
	private JButton btnRedo;
	
	private int state = 0;
	
	private JPanel contentPane;
	
	private DefaultListModel<String> dlmBoje = new DefaultListModel<String>();

	private JList<String> activityLog;
	
	private DefaultListModel <String> dlmList;
	
	
	
	public JList<String> getActivityLog() {
		return activityLog;
	}

	public void setActivityLog(JList<String> activityLog) {
		this.activityLog = activityLog;
	}
	
	/*
	public JButton getbtnModifikacija() {
		return btnModifikacija;
	}

	public void setbtnModifikacija(JButton btnModifikacija) {
		this.btnModifikacija = btnModifikacija;
	}
	*/
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/*
	public JButton getbtnBrisanje() {
		return btnBrisanje;
	}

	public void setbtnBrisanje(JButton btnBrisanje) {
		this.btnBrisanje = btnBrisanje;
	}
	*/
	
	public JToggleButton getTglBtnDelete() {
		return tglBtnDelete;
	}

	public void setTglBtnDelete(JToggleButton tglBtnDelete) {
		this.tglBtnDelete = tglBtnDelete;
	}
	
	public JToggleButton getTglBtnSelect() {
		return tglBtnSelect;
	}

	public void setTglBtnSelect(JToggleButton tglBtnSelect) {
		this.tglBtnSelect = tglBtnSelect;
	}
	
	public JToggleButton getTglBtnModify() {
		return tglBtnModify;
	}

	public void setTglBtnModify(JToggleButton tglBtnModify) {
		this.tglBtnModify = tglBtnModify;
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

	/*
	public DefaultListModel<String> getDlmBoje() {
		return dlmBoje;
	}

	public void setDlmBoje(DefaultListModel<String> dlmBoje) {
		this.dlmBoje = dlmBoje;
	}
	*/
	
	public DefaultListModel<String> getDlmList() {
		return dlmList;
	}

	public void setDlmList(DefaultListModel<String> dlmList) {
		this.dlmList = dlmList;
	}
	
	/**
	 * Create the frame.
	 */
	
	public DrawingFrame() {	
		/*
		setForeground(Color.BLUE);
		setBackground(Color.CYAN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setLocationRelativeTo(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
		dlmList = new DefaultListModel<String>();
	
		JPanel buttonsPanelForDrawing = new JPanel();
		buttonsPanelForDrawing.setBackground(Color.WHITE);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.WHITE);

		view = new DrawingView();
		view.setBackground(Color.WHITE);
		mainPanel.add(buttonsPanel, BorderLayout.NORTH);
		mainPanel.add(buttonsPanelForDrawing, BorderLayout.SOUTH);
		mainPanel.add(view, BorderLayout.CENTER);
		ButtonGroup buttonsGroup = new ButtonGroup();

		JButton btnSaveDraw = new JButton();
		btnSaveDraw.setEnabled(false);
		btnSaveDraw.setText("Save");
		buttonsPanelForDrawing.add(btnSaveDraw);
		
		JButton btnNewDraw = new JButton();
		btnNewDraw.setText("New draw");
		btnNewDraw.setEnabled(false);
		buttonsPanelForDrawing.add(btnNewDraw);
		
		JButton btnOpenDraw = new JButton();
		btnOpenDraw.setText("Open");
		buttonsPanelForDrawing.add(btnOpenDraw);
		
		JButton btnLog = new JButton();
		btnLog.setEnabled(false);
		btnLog.setText("Log");
		buttonsPanelForDrawing.add(btnLog);

	
		JList<String> activityLog = new JList<String>();
		activityLog.setEnabled(false);
		activityLog.setModel(dlmList);
		activityLog.setFont(new Font("Lucida Console", Font.BOLD, 12));
		scrollPane.setViewportView(activityLog);

			
		MouseAdapter mouseAdapterSaveDrawing = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.save();
			}
		};
		
		MouseAdapter mouseAdapterLog = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if (btnLog.getText().equals("Log")) {
					mainPanel.remove(view);
					mainPanel.add(scrollPane, BorderLayout.CENTER);
					btnLog.setText("Draw");
					
				} else if (btnLog.getText().equals("Draw")) {
					mainPanel.remove(scrollPane);
					mainPanel.add(view, BorderLayout.CENTER);
					btnLog.setText("Log");
				}			
				repaint();
			}
		};
		
		btnOpenDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.open();
			}
		});
			
		*/
		
		final Color LIGHT_BLUE = new Color(51, 153, 255);
		this.setBackground(Color.WHITE);
		dlmList = new DefaultListModel<String>();
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlNorth.setBackground(LIGHT_BLUE);
		getContentPane().add(pnlNorth, BorderLayout.NORTH);
		
		GridBagLayout gbl_pnlNorth = new GridBagLayout();
		gbl_pnlNorth.columnWidths = new int[]{109, 109, 0, 72, 50, 57, 50, 0, 0, 0, 0, 69, 0};
		gbl_pnlNorth.rowHeights = new int[]{23, 0, 0};
		gbl_pnlNorth.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE}; //Ovi brojevi utiču na to kako će se prostor rasporediti između kolona u slučaju da panel može da se proširi ili smanji. Veći brojevi znače veću proporcionalnu širinu.
		gbl_pnlNorth.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE}; //isto kao za column 
		pnlNorth.setLayout(gbl_pnlNorth);
		
		JRadioButton rbtnPoint = new JRadioButton("Point");
		rbtnPoint.setBackground(LIGHT_BLUE);
		rbtnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(1);
			}
		});
		rbtnPoint.setForeground(Color.WHITE);
		shapesGroup.add(rbtnPoint);
		
		GridBagConstraints gbc_rbtnPoint = new GridBagConstraints();
		gbc_rbtnPoint.fill = GridBagConstraints.HORIZONTAL;
		gbc_rbtnPoint.anchor = GridBagConstraints.NORTH;
		gbc_rbtnPoint.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnPoint.gridx = 0;
		gbc_rbtnPoint.gridy = 0;
		pnlNorth.add(rbtnPoint, gbc_rbtnPoint);
		
		JRadioButton rbtnRectangle = new JRadioButton("Rectangle");
		rbtnRectangle.setForeground(Color.WHITE);
		rbtnRectangle.setBackground(LIGHT_BLUE);
		rbtnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(4);
			}
		});
		shapesGroup.add(rbtnRectangle);
		GridBagConstraints gbc_rbtnRectangle = new GridBagConstraints();
		gbc_rbtnRectangle.fill = GridBagConstraints.HORIZONTAL;
		gbc_rbtnRectangle.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnRectangle.gridx = 1;
		gbc_rbtnRectangle.gridy = 0;
		pnlNorth.add(rbtnRectangle, gbc_rbtnRectangle);
		
		JRadioButton rbtnDonut = new JRadioButton("Donut");
		rbtnDonut.setForeground(Color.WHITE);
		rbtnDonut.setBackground(LIGHT_BLUE);
		rbtnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(5);
			}
		});
		shapesGroup.add(rbtnDonut);
		GridBagConstraints gbc_rbtnDonut = new GridBagConstraints();
		gbc_rbtnDonut.fill = GridBagConstraints.HORIZONTAL;
		gbc_rbtnDonut.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnDonut.gridx = 2;
		gbc_rbtnDonut.gridy = 0;
		pnlNorth.add(rbtnDonut, gbc_rbtnDonut);
		
		JRadioButton rbtnLine = new JRadioButton("Line");
		rbtnLine.setForeground(Color.WHITE);
		rbtnLine.setBackground(LIGHT_BLUE);
		rbtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(2);
			}
		});
		shapesGroup.add(rbtnLine);
		GridBagConstraints gbc_rbtnLine = new GridBagConstraints();
		gbc_rbtnLine.fill = GridBagConstraints.HORIZONTAL;
		gbc_rbtnLine.insets = new Insets(0, 0, 0, 5);
		gbc_rbtnLine.anchor = GridBagConstraints.NORTH;
		gbc_rbtnLine.gridx = 0;
		gbc_rbtnLine.gridy = 1;
		pnlNorth.add(rbtnLine, gbc_rbtnLine);
		
		JButton btnUndo_1 = new JButton("Undo");
		final Color VERY_LIGHT_GREEN = new Color(102,255,102);
		btnUndo_1.setBackground(VERY_LIGHT_GREEN);
		btnUndo_1.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnUndo_1 = new GridBagConstraints();
		gbc_btnUndo_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUndo_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnUndo_1.gridx = 3;
		gbc_btnUndo_1.gridy = 0;
		pnlNorth.add(btnUndo_1, gbc_btnUndo_1);
		this.btnUndo = btnUndo_1;
		this.btnUndo.setVisible(false);
		btnUndo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		
		JButton btnToFront = new JButton("To Front");
		btnToFront.setBackground(Color.BLUE);
		btnToFront.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnToFront = new GridBagConstraints();
		gbc_btnToFront.anchor = GridBagConstraints.WEST;
		gbc_btnToFront.insets = new Insets(0, 0, 5, 5);
		gbc_btnToFront.gridx = 4;
		gbc_btnToFront.gridy = 0;
		pnlNorth.add(btnToFront, gbc_btnToFront);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		
		JButton btnBringToFront = new JButton("Bring To Front");
		btnBringToFront.setBackground(Color.BLUE);
		btnBringToFront.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
		gbc_btnBringToFront.anchor = GridBagConstraints.WEST;
		gbc_btnBringToFront.insets = new Insets(0, 0, 5, 5);
		gbc_btnBringToFront.gridx = 5;
		gbc_btnBringToFront.gridy = 0;
		pnlNorth.add(btnBringToFront, gbc_btnBringToFront);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.BringToFront();
			}
		});
		JButton btnOpenPainting = new JButton("Open");
		btnOpenPainting.setBackground(Color.WHITE);
		btnOpenPainting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.unserialize();
			}
		});
		GridBagConstraints gbc_btnOpenPainting = new GridBagConstraints();
		gbc_btnOpenPainting.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpenPainting.insets = new Insets(0, 0, 5, 0);
		gbc_btnOpenPainting.gridx = 11;
		gbc_btnOpenPainting.gridy = 0;
		pnlNorth.add(btnOpenPainting, gbc_btnOpenPainting);
		
		
		JRadioButton rbtnCircle = new JRadioButton("Circle");
		rbtnCircle.setForeground(Color.WHITE);
		rbtnCircle.setBackground(LIGHT_BLUE);
		rbtnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(3);
			}
		});
		shapesGroup.add(rbtnCircle);
		GridBagConstraints gbc_rbtnCircle = new GridBagConstraints();
		gbc_rbtnCircle.fill = GridBagConstraints.BOTH;
		gbc_rbtnCircle.insets = new Insets(0, 0, 0, 5);
		gbc_rbtnCircle.gridx = 1;
		gbc_rbtnCircle.gridy = 1;
		pnlNorth.add(rbtnCircle, gbc_rbtnCircle);
		
		JRadioButton rbtnHexagon = new JRadioButton("Hexagon");
		rbtnHexagon.setForeground(Color.WHITE);
		rbtnHexagon.setBackground(LIGHT_BLUE);
		rbtnHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(6);
			}
		});
		shapesGroup.add(rbtnHexagon);
		GridBagConstraints gbc_rbtnHexagon = new GridBagConstraints();
		gbc_rbtnHexagon.insets = new Insets(0, 0, 0, 5);
		gbc_rbtnHexagon.fill = GridBagConstraints.HORIZONTAL;
		gbc_rbtnHexagon.gridx = 2;
		gbc_rbtnHexagon.gridy = 1;
		pnlNorth.add(rbtnHexagon, gbc_rbtnHexagon);
		
		JButton btnRedo_1 = new JButton("Redo");
		final Color VERY_DARK_GREEN = new Color(0, 102, 0);
		btnRedo_1.setBackground(VERY_DARK_GREEN);
		btnRedo_1.setForeground(Color.WHITE);
		this.btnRedo = btnRedo_1;
		this.btnRedo.setVisible(false);
		btnRedo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		
		GridBagConstraints gbc_btnRedo_1 = new GridBagConstraints();
		gbc_btnRedo_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRedo_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnRedo_1.gridx = 3;
		gbc_btnRedo_1.gridy = 1;
		pnlNorth.add(btnRedo_1, gbc_btnRedo_1);
		
		JButton btnToBack = new JButton("To Back");
		btnToBack.setBackground(Color.BLUE);
		btnToBack.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnToBack = new GridBagConstraints();
		gbc_btnToBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnToBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnToBack.gridx = 4;
		gbc_btnToBack.gridy = 1;
		pnlNorth.add(btnToBack, gbc_btnToBack);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		
		JButton btnBringToBack = new JButton("Bring To Back");
		btnBringToBack.setBackground(Color.BLUE);
		btnBringToBack.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
		gbc_btnBringToBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBringToBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBringToBack.gridx = 5;
		gbc_btnBringToBack.gridy = 1;
		pnlNorth.add(btnBringToBack, gbc_btnBringToBack);
		
		JButton btnSerialize = new JButton("Save");
		btnSerialize.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnSerialize = new GridBagConstraints();
		gbc_btnSerialize.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSerialize.gridx = 11;
		gbc_btnSerialize.gridy = 1;
		pnlNorth.add(btnSerialize, gbc_btnSerialize);
		btnSerialize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.serialize();
			}
		});
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.BringToBack();
			}
		});
		
		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(LIGHT_BLUE);
		pnlWest.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlWest.setForeground(Color.WHITE);
		getContentPane().add(pnlWest, BorderLayout.WEST);
		GridBagLayout gbl_pnlWest = new GridBagLayout();
		gbl_pnlWest.columnWidths = new int[]{89, 0};
		gbl_pnlWest.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlWest.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlWest.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlWest.setLayout(gbl_pnlWest);
		
		JToggleButton tglbtnSelect = new JToggleButton("Select");
		tglbtnSelect.setBackground(Color.WHITE);
		tglbtnSelect.setForeground(Color.BLACK);
		tglbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(7);
				view.repaint();
			}
		});
		
		GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
		gbc_tglbtnSelect.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnSelect.gridx = 0;
		gbc_tglbtnSelect.gridy = 1;
		pnlWest.add(tglbtnSelect, gbc_tglbtnSelect);
		
		JToggleButton tglbtnModify = new JToggleButton("Modify");
		final Color VERY_LIGHT_BLUE = new Color(51,204,255);
		tglbtnModify.setBackground(VERY_LIGHT_BLUE);
		this.tglBtnModify = tglbtnModify;
		tglbtnModify.setVisible(false);
		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					controller.modifyShape();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		GridBagConstraints gbc_tglbtnModify = new GridBagConstraints();
		gbc_tglbtnModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnModify.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnModify.gridx = 0;
		gbc_tglbtnModify.gridy = 2;
		pnlWest.add(tglbtnModify, gbc_tglbtnModify);
		
		JToggleButton tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.setBackground(Color.YELLOW);
		this.tglBtnDelete = tglbtnDelete;
		tglbtnDelete.setVisible(false);
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteObject();
				view.repaint();
			}
		});
		GridBagConstraints gbc_tglbtnDelete = new GridBagConstraints();
		gbc_tglbtnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnDelete.gridx = 0;
		gbc_tglbtnDelete.gridy = 3;
		pnlWest.add(tglbtnDelete, gbc_tglbtnDelete);
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					controller.mouseClicked(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		shapesGroup.add(tglbtnDelete);
		shapesGroup.add(tglbtnModify);
		shapesGroup.add(tglbtnSelect);
		
		JButton btnFillColor = new JButton("Fill color");
		btnFillColor.setBackground(Color.WHITE);
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color fillColor = JColorChooser.showDialog(btnFillColor, "Izaberite boju", Color.WHITE);
				if(fillColor!=null) { //korisnik nije odustao od izbora boje)
					controller.setInColor(fillColor);
					btnFillColor.setBackground(fillColor); // postavljamo pozadinu dugmeta btnFillColor na izabranu boju, što je vizuelni pokazatelj izabrane boje
				}
			}
		});
		
		JButton btnOutlineColor = new JButton("Outline color");
		btnOutlineColor.setBackground(Color.WHITE);
		btnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outlineColor = JColorChooser.showDialog(btnOutlineColor, "Izaberite boju", Color.BLACK);
				if(outlineColor!=null) {
					controller.setOutColor(outlineColor);
					btnOutlineColor.setBackground(outlineColor);
				}	
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(LIGHT_BLUE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		pnlWest.add(panel, gbc_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(LIGHT_BLUE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 5;
		pnlWest.add(panel_1, gbc_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(LIGHT_BLUE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 6;
		pnlWest.add(panel_2, gbc_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(LIGHT_BLUE);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 7;
		pnlWest.add(panel_3, gbc_panel_3);
		
		GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
		gbc_btnOutlineColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOutlineColor.insets = new Insets(0, 0, 5, 0);
		gbc_btnOutlineColor.gridx = 0;
		gbc_btnOutlineColor.gridy = 10;
		pnlWest.add(btnOutlineColor, gbc_btnOutlineColor);
		GridBagConstraints gbc_btnFillColor = new GridBagConstraints();
		gbc_btnFillColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFillColor.gridx = 0;
		gbc_btnFillColor.gridy = 11;
		pnlWest.add(btnFillColor, gbc_btnFillColor);
		getContentPane().add(view, BorderLayout.CENTER);
		
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
		
		activityLog = new JList<String>(); //sadrzaj loga
		activityLog.setValueIsAdjusting(true); //automatsko prilagodjavanjevr
		activityLog.setBackground(Color.LIGHT_GRAY);
		activityLog.setModel(dlmList); //postavljam model liste
		activityLog.setFont(new Font("Lucida Console", Font.BOLD, 12));
		scrollPane.setViewportView(activityLog); //priakaz sadrzaja u scrollpane-u
		view.setBackground(Color.WHITE);
		scrollPane.setBounds(586, 452, 784, 461);
	}
		
		
		
		
		
		
		
		
		/*
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/*
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().add(view, BorderLayout.CENTER);
*/
		// centralni panel
		//JPanel pnlCenter = new JPanel();
		//contentPane.add(pnlCenter, BorderLayout.CENTER);
		//GridBagLayout gbl_pnlCenter = new GridBagLayout();
		//gbl_pnlCenter.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		//gbl_pnlCenter.rowHeights = new int[] { 33, 0, 0, 0, 0, 0, 0 };
		//gbl_pnlCenter.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		//gbl_pnlCenter.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		//pnlCenter.setLayout(gbl_pnlCenter);

		
		/*
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
		
		DrawingModel model = new DrawingModel();
		//DrawingView pnlDrawing = new DrawingView();
		DrawingController controller = new DrawingController(model, this);
		
        //view.repaint();
		//IZMENI!!!!!!!
		// * */
		/*
        view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
					controller.mouseClicked(e);			
			}
		});
        view.repaint();
		//shapesGroup.add(btnBrisanje);
		//shapesGroup.add(btnModifikacija);
		//shapesGroup.add(btnSelect);
		
		
		GridBagConstraints gbl_pnlDrawing = new GridBagConstraints();
		gbl_pnlDrawing.anchor = GridBagConstraints.SOUTH;
		gbl_pnlDrawing.insets = new Insets(0, 0, 5, 5);
		gbl_pnlDrawing.gridx = 1;
		gbl_pnlDrawing.gridy = 3;
		pnlCenter.add(view, gbl_pnlDrawing);

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