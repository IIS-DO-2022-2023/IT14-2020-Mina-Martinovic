package Drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DlgCircleUpdate extends JDialog{

	private JTextField txtRadius;
	private boolean confirmation;
	private int radius;
	private Color outerColor;
	private Color innerColor;
	private JTextField txtCenterX;
	private JTextField txtCenterY;
	private JButton outerColorButton;
	private JButton innerColorButton;

	
	public JTextField getTxtCenterX() {
		return txtCenterX;
	}

	public void setTxtCenterX(JTextField txtCenterX) {
		this.txtCenterX = txtCenterX;
	}

	public JTextField getTxtCenterY() {
		return txtCenterY;
	}

	public void setTxtCenterY(JTextField txtCenterY) {
		this.txtCenterY = txtCenterY;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public Color getOuterColor() {
		return outerColor;
	}

	public void setOuterColor(Color outerColor) {
		this.outerColor = outerColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	
	public Color getOuterColorBtnBackgroundColor()
	{
		return this.outerColorButton.getBackground();
	}
	
	public void setOuterColorBtnBackgroundColor(Color color)
	{
		
		this.outerColorButton.setBackground(color);
	}
	
	public Color getInnerColorBtnBackgroundColor()
	{
		return this.innerColorButton.getBackground();
	}
	
	public void setInnerColorBtnBackgroundColor(Color color)
	{
		
		this.innerColorButton.setBackground(color);
	}
	

	public DlgCircleUpdate() {
		setResizable(true);
		setModal(true);
		setTitle("Update Circle");
		setBounds(100, 100, 350, 200);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 100, 62, 21, 68, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 22, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnOuterColor = new JButton("Outer Color");
		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outerColor = JColorChooser.showDialog(btnOuterColor, "Choose your color", Color.BLACK);
			}
		});
		
		JLabel lblCenterX = new JLabel("Center X :");
		lblCenterX.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		GridBagConstraints gbc_lblCenterX = new GridBagConstraints();
		gbc_lblCenterX.insets = new Insets(0, 0, 5, 5);
		gbc_lblCenterX.gridx = 2;
		gbc_lblCenterX.gridy = 1;
		panel.add(lblCenterX, gbc_lblCenterX);
		
		txtCenterX = new JTextField();
		GridBagConstraints gbc_txtCenterX = new GridBagConstraints();
		gbc_txtCenterX.insets = new Insets(0, 0, 5, 5);
		gbc_txtCenterX.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCenterX.gridx = 3;
		gbc_txtCenterX.gridy = 1;
		panel.add(txtCenterX, gbc_txtCenterX);
		txtCenterX.setColumns(10);
		
		JLabel lblCenterY = new JLabel("Center Y :");
		lblCenterY.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		GridBagConstraints gbc_lblCenterY = new GridBagConstraints();
		gbc_lblCenterY.insets = new Insets(0, 0, 5, 5);
		gbc_lblCenterY.gridx = 2;
		gbc_lblCenterY.gridy = 2;
		panel.add(lblCenterY, gbc_lblCenterY);
		
		txtCenterY = new JTextField();
		GridBagConstraints gbc_txtCenterY = new GridBagConstraints();
		gbc_txtCenterY.insets = new Insets(0, 0, 5, 5);
		gbc_txtCenterY.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCenterY.gridx = 3;
		gbc_txtCenterY.gridy = 2;
		panel.add(txtCenterY, gbc_txtCenterY);
		txtCenterY.setColumns(10);
		
		JLabel lblR = new JLabel("Radius :");
		lblR.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		GridBagConstraints gbc_lblR = new GridBagConstraints();
		gbc_lblR.insets = new Insets(0, 0, 5, 5);
		gbc_lblR.gridx = 2;
		gbc_lblR.gridy = 3;
		panel.add(lblR, gbc_lblR);
		
		txtRadius = new JTextField();
		GridBagConstraints gbc_txtCenterX1 = new GridBagConstraints();
		gbc_txtCenterX1.insets = new Insets(0, 0, 5, 5);
		gbc_txtCenterX1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCenterX1.gridx = 3;
		gbc_txtCenterX1.gridy = 3;
		panel.add(txtRadius, gbc_txtCenterX1);
		txtRadius.setColumns(10);
		btnOuterColor.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		GridBagConstraints gbc_btnOuterColor = new GridBagConstraints();
		gbc_btnOuterColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOuterColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnOuterColor.gridx = 2;
		gbc_btnOuterColor.gridy = 4;
		panel.add(btnOuterColor, gbc_btnOuterColor);
		
		JButton btnInnerColor = new JButton("Inner Color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(btnInnerColor, "Choose your color", Color.WHITE);
			}
		});
		btnInnerColor.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInnerColor.gridx = 3;
		gbc_btnInnerColor.gridy = 4;
		panel.add(btnInnerColor, gbc_btnInnerColor);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setConfirmation(true);
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 6;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 6;
		panel.add(btnCancel, gbc_btnCancel);
	}
	
	
}
