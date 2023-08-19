package mvc;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import Drawing.FrmDrawing;
import Drawing.PanelDrawing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingFrame extends JFrame{
	
	private DrawingView view = new DrawingView();
	private DrawingController controller;
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
	
	public DrawingFrame() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().add(view, BorderLayout.CENTER);
	}

	public DrawingView getView() {
		return view;
	}

	public void setDrawingController(DrawingController drawingController) {
		this.controller = drawingController;
	}
	
	//dodas dugmice sve to jev frmDrawing i listeneri 
	//jer ja odavde pozivma metodekoje se nalaze u kontroleru
	//kada dodam event pisem controller.undo recimo
	
	
}