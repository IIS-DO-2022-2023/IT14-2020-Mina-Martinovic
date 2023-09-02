package Drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DlgHexagon extends JDialog{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private boolean confirmation;
	private JTextField txtR;
	private Color outlineColor = Color.BLUE;
	private Color fillColor = Color.WHITE;
	private final JButton btnFillColor = new JButton("Fill Color");
	
	public JTextField getTxtX() {
		return txtX;
	}
	
	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}
	
	public JTextField getTxtY() {
		return txtY;
	}
	
	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}
	
	public boolean isConfirmation() {
		return confirmation;
	}
	
	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}
	
	public JTextField getTxtR() {
		return txtR;
	}
	
	public void setTxtR(JTextField txtR) {
		this.txtR = txtR;
	}
	
	public Color getOutlineColor() {
		return outlineColor;
	}
	
	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}
	
	public Color getFillColor() {
		return fillColor;
	}
	
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	
	public DlgHexagon(int x, int y, int radius) {
		setModal(true);
		setTitle("Update Hexagon");
		setBounds(100, 100, 495, 200);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{45, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCenterX = new JLabel("Center X :");
			lblCenterX.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
		
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.anchor = GridBagConstraints.EAST;
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 1;
			contentPanel.add(lblCenterX, gbc_lblX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblX.gridx = 1;
			gbc_lblX.gridy = 1;
			contentPanel.add(txtX, gbc_lblX);
			txtX.setColumns(10);
		}
		{
			JLabel lblCenterY = new JLabel("Center Y :");
			lblCenterY.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
			GridBagConstraints gbc_lblCenterY = new GridBagConstraints();
			gbc_lblCenterY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterY.gridx = 0;
			gbc_lblCenterY.gridy = 2;
			contentPanel.add(lblCenterY, gbc_lblCenterY);
		}
		{
			txtY = new JTextField();
			txtY.setText("");
			GridBagConstraints gbc_txtCenterY = new GridBagConstraints();
			gbc_txtCenterY.anchor = GridBagConstraints.NORTH;
			gbc_txtCenterY.insets = new Insets(0, 0, 5, 5);
			gbc_txtCenterY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCenterY.gridx = 1;
			gbc_txtCenterY.gridy = 2;
			contentPanel.add(txtY, gbc_txtCenterY);
			txtY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius :");
			lblRadius.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.WEST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 3;
			panel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtR = new JTextField();
			GridBagConstraints gbc_txtR = new GridBagConstraints();
			gbc_txtR.insets = new Insets(0, 0, 5, 5);
			gbc_txtR.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtR.gridx = 1;
			gbc_txtR.gridy = 3;
			panel.add(txtR, gbc_txtR);
			txtR.setColumns(10);
		}
		
		{
			JButton btnOutlineColor = new JButton("Outline Color");
			btnOutlineColor.setFont(new Font("Tahoma", Font.BOLD, 10));
			btnOutlineColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					outlineColor = JColorChooser.showDialog(btnOutlineColor, "Izaberite boju", Color.BLACK);
				}
			});
			GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
			gbc_btnOutlineColor.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnOutlineColor.anchor = GridBagConstraints.NORTH;
			gbc_btnOutlineColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnOutlineColor.gridx = 4;
			gbc_btnOutlineColor.gridy = 4;
			panel.add(btnOutlineColor, gbc_btnOutlineColor);
		}
	
		{
			JButton innerColorButton = new JButton("Boja unutrasnjosti");
			  innerColorButton.setBackground(SystemColor.activeCaption);
			  innerColorButton.setForeground(new Color(0, 0, 0));
			  innerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
			  innerColorButton.addActionListener(new ActionListener() {
				  public void actionPerformed(ActionEvent e)
				  {
					  fillColor = JColorChooser.showDialog(null,"Izaberi boju unutrasnjosti tvog oblika", fillColor);
					  innerColorButton.setBackground(fillColor);
				
					 
				  }
				}
			  );
			  GridBagConstraints gbc_innerColorButton = new GridBagConstraints();
			  gbc_innerColorButton.insets = new Insets(0, 0, 0, 5);
			  gbc_innerColorButton.gridx = 0;
			  gbc_innerColorButton.gridy = 7;
			  contentPanel.add(innerColorButton, gbc_innerColorButton);
		}
		
		{		
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setFont(new Font("Tahoma", Font.BOLD, 10));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			GridBagConstraints gbc_btnCancel = new GridBagConstraints();
			gbc_btnCancel.insets = new Insets(0, 0, 5, 0);
			gbc_btnCancel.gridx = 5;
			gbc_btnCancel.gridy = 7;
			panel.add(btnCancel, gbc_btnCancel);
		}
		{
			JButton btnOk = new JButton("Ok");
			btnOk.setFont(new Font("Tahoma", Font.BOLD, 10));
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					confirmation = true;
					setVisible(false);
				}
			});
			GridBagConstraints gbc_btnOk = new GridBagConstraints();
			gbc_btnOk.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnOk.anchor = GridBagConstraints.SOUTH;
			gbc_btnOk.insets = new Insets(0, 0, 5, 5);
			gbc_btnOk.gridx = 4;
			gbc_btnOk.gridy = 7;
			panel.add(btnOk, gbc_btnOk);
		}
	
	}

	public DlgHexagon() {
		// TODO Auto-generated constructor stub
	}	
		
}
