package Drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField XStartPoint;
	private JTextField YStartPoint;
	private JTextField XEndPoint;
	private JTextField YEndPoint;
	//protected boolean isOk;
	private boolean confirmation;
	private Color color = Color.BLACK;


	public JTextField getXStartPoint() {
		return XStartPoint;
	}

	public void setXStartPoint(JTextField xStartPoint) {
		XStartPoint = xStartPoint;
	}

	public JTextField getYStartPoint() {
		return YStartPoint;
	}

	public void setYStartPoint(JTextField yStartPoint) {
		YStartPoint = yStartPoint;
	}

	public JTextField getXEndPoint() {
		return XEndPoint;
	}

	public void setXEndPoint(JTextField xEndPoint) {
		XEndPoint = xEndPoint;
	}

	public JTextField getYEndPoint() {
		return YEndPoint;
	}

	public void setYEndPoint(JTextField yEndPoint) {
		YEndPoint = yEndPoint;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setBounds(100, 100, 450, 300);
		setTitle("Karakteristike linije");
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{45, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblXKoordinataStart = new JLabel("X koordinata je:");
			GridBagConstraints gbc_lblXKoordinataStart = new GridBagConstraints();
			gbc_lblXKoordinataStart.anchor = GridBagConstraints.EAST;
			gbc_lblXKoordinataStart.insets = new Insets(0, 0, 5, 5);
			gbc_lblXKoordinataStart.gridx = 0;
			gbc_lblXKoordinataStart.gridy = 1;
			contentPanel.add(lblXKoordinataStart, gbc_lblXKoordinataStart);
		}
		{
			XStartPoint = new JTextField();
			GridBagConstraints gbc_txtXKoordinataStart = new GridBagConstraints();
			gbc_txtXKoordinataStart.insets = new Insets(0, 0, 5, 0);
			gbc_txtXKoordinataStart.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXKoordinataStart.gridx = 1;
			gbc_txtXKoordinataStart.gridy = 1;
			contentPanel.add(XStartPoint, gbc_txtXKoordinataStart);
			XStartPoint.setColumns(10);
		}
		{
			JLabel lblYKoordinataStart = new JLabel("Y koordinata je:");
			GridBagConstraints gbc_lblYKoordinataStart = new GridBagConstraints();
			gbc_lblYKoordinataStart.anchor = GridBagConstraints.EAST;
			gbc_lblYKoordinataStart.insets = new Insets(0, 0, 5, 5);
			gbc_lblYKoordinataStart.gridx = 0;
			gbc_lblYKoordinataStart.gridy = 2;
			contentPanel.add(lblYKoordinataStart, gbc_lblYKoordinataStart);
		}
		{
			YStartPoint = new JTextField();
			GridBagConstraints gbc_lblYKoordinataStart = new GridBagConstraints();
			gbc_lblYKoordinataStart.insets = new Insets(0, 0, 5, 0);
			gbc_lblYKoordinataStart.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblYKoordinataStart.gridx = 1;
			gbc_lblYKoordinataStart.gridy = 2;
			contentPanel.add(YStartPoint, gbc_lblYKoordinataStart);
			YStartPoint.setColumns(10);
		}		
		{
			JLabel lblXKoordinataEnd = new JLabel("X koordinata je:");
			GridBagConstraints gbc_lblXKoordinataEnd = new GridBagConstraints();
			gbc_lblXKoordinataEnd.anchor = GridBagConstraints.EAST;
			gbc_lblXKoordinataEnd.insets = new Insets(0, 0, 5, 5);
			gbc_lblXKoordinataEnd.gridx = 0;
			gbc_lblXKoordinataEnd.gridy = 1;
			contentPanel.add(lblXKoordinataEnd, gbc_lblXKoordinataEnd);
		}
		{
			XEndPoint = new JTextField();
			GridBagConstraints gbc_txtXKoordinataEnd = new GridBagConstraints();
			gbc_txtXKoordinataEnd.insets = new Insets(0, 0, 5, 0);
			gbc_txtXKoordinataEnd.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXKoordinataEnd.gridx = 1;
			gbc_txtXKoordinataEnd.gridy = 1;
			contentPanel.add(XEndPoint, gbc_txtXKoordinataEnd);
			XEndPoint.setColumns(10);
		}
		{
			JLabel lblYKoordinataEnd = new JLabel("Y koordinata je:");
			GridBagConstraints gbc_lblYKoordinataEnd = new GridBagConstraints();
			gbc_lblYKoordinataEnd.anchor = GridBagConstraints.EAST;
			gbc_lblYKoordinataEnd.insets = new Insets(0, 0, 5, 5);
			gbc_lblYKoordinataEnd.gridx = 0;
			gbc_lblYKoordinataEnd.gridy = 2;
			contentPanel.add(lblYKoordinataEnd, gbc_lblYKoordinataEnd);
		}
		{
			YEndPoint = new JTextField();
			GridBagConstraints gbc_lblYKoordinataEnd = new GridBagConstraints();
			gbc_lblYKoordinataEnd.insets = new Insets(0, 0, 5, 0);
			gbc_lblYKoordinataEnd.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblYKoordinataEnd.gridx = 1;
			gbc_lblYKoordinataEnd.gridy = 2;
			contentPanel.add(YEndPoint, gbc_lblYKoordinataEnd);
			YEndPoint.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						confirmation=true;
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						confirmation=false;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
