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
import javax.swing.border.EmptyBorder;

import geometry1.Point;

public class DlgHexagonUpdate extends JDialog{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCenterX;
	private JTextField txtCenterY;
	private boolean confirmation;
	private JTextField txtR;
	private Color innerColor = new Color(255, 255, 255);
	private Color outerColor;
	
	private JButton outerColorButton;
	private JButton innerColorButton;
	
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

	public JTextField getTxtRadius() {
		return txtR;
	}

	public void setTxtR(JTextField txtR) {
		this.txtR = txtR;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

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

	public DlgHexagonUpdate() {
		setModal(true);
		setTitle("Update Hexagon");
		setBounds(100, 100, 495, 200);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 18, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblCenterX = new JLabel("Center X :");
		lblCenterX.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
		GridBagConstraints gbc_lblCenterX = new GridBagConstraints();
		gbc_lblCenterX.insets = new Insets(0, 0, 5, 5);
		gbc_lblCenterX.gridx = 1;
		gbc_lblCenterX.gridy = 3;
		panel.add(lblCenterX, gbc_lblCenterX);
		
		txtCenterX = new JTextField();
		GridBagConstraints gbc_txtCenterX = new GridBagConstraints();
		gbc_txtCenterX.insets = new Insets(0, 0, 5, 5);
		gbc_txtCenterX.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCenterX.gridx = 3;
		gbc_txtCenterX.gridy = 3;
		panel.add(txtCenterX, gbc_txtCenterX);
		txtCenterX.setColumns(10);
		
		JLabel lblCenterY = new JLabel("Center Y :");
		lblCenterY.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
		GridBagConstraints gbc_lblCenterY = new GridBagConstraints();
		gbc_lblCenterY.insets = new Insets(0, 0, 5, 5);
		gbc_lblCenterY.gridx = 1;
		gbc_lblCenterY.gridy = 4;
		panel.add(lblCenterY, gbc_lblCenterY);
		
		txtCenterY = new JTextField();
		GridBagConstraints gbc_txtCenterY = new GridBagConstraints();
		gbc_txtCenterY.anchor = GridBagConstraints.NORTH;
		gbc_txtCenterY.insets = new Insets(0, 0, 5, 5);
		gbc_txtCenterY.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCenterY.gridx = 3;
		gbc_txtCenterY.gridy = 4;
		panel.add(txtCenterY, gbc_txtCenterY);
		txtCenterY.setColumns(10);
		
		outerColorButton = new JButton("Outline Color");
		outerColorButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		outerColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outerColor = JColorChooser.showDialog(outerColorButton, "Choose your color", Color.BLACK);
				outerColorButton.setBackground(outerColor);
			}
		});
		GridBagConstraints gbc_outerColorButton = new GridBagConstraints();
		gbc_outerColorButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_outerColorButton.anchor = GridBagConstraints.NORTH;
		gbc_outerColorButton.insets = new Insets(0, 0, 5, 5);
		gbc_outerColorButton.gridx = 4;
		gbc_outerColorButton.gridy = 4;
		panel.add(outerColorButton, gbc_outerColorButton);
		
		JLabel lblRadius = new JLabel("Radius :");
		lblRadius.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
		GridBagConstraints gbc_lblRadius = new GridBagConstraints();
		gbc_lblRadius.anchor = GridBagConstraints.WEST;
		gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
		gbc_lblRadius.gridx = 1;
		gbc_lblRadius.gridy = 5;
		panel.add(lblRadius, gbc_lblRadius);
		
		txtR = new JTextField();
		GridBagConstraints gbc_txtR = new GridBagConstraints();
		gbc_txtR.insets = new Insets(0, 0, 5, 5);
		gbc_txtR.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtR.gridx = 3;
		gbc_txtR.gridy = 5;
		panel.add(txtR, gbc_txtR);
		txtR.setColumns(10);
		
		innerColorButton = new JButton("Inner Color");
		GridBagConstraints gbc_innerColorButton = new GridBagConstraints();
		gbc_innerColorButton.fill = GridBagConstraints.BOTH;
		gbc_innerColorButton.insets = new Insets(0, 0, 5, 5);
		gbc_innerColorButton.gridx = 4;
		gbc_innerColorButton.gridy = 5;
		innerColorButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		innerColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(innerColorButton, "Choose your color", Color.WHITE);
				innerColorButton.setBackground(innerColor);
			}
		});
		panel.add(innerColorButton, gbc_innerColorButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
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
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancel.gridx = 5;
		gbc_btnCancel.gridy = 7;
		panel.add(btnCancel, gbc_btnCancel);
	}
	/*
	
	public DlgHexagonUpdate(int x, int y, int radius, Color innerC, Color outerC) {
		setResizable(false);
		setModal(true);
		setTitle("Update Hexagon:");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{113, 87, 76, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{22, 0, 0, 0, 34, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel lblCenterX = new JLabel("Center X :");
		lblCenterX.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
		GridBagConstraints gbc_lblCenterX = new GridBagConstraints();
		gbc_lblCenterX.insets = new Insets(0, 0, 5, 5);
		gbc_lblCenterX.gridx = 1;
		gbc_lblCenterX.gridy = 3;
		contentPanel.add(lblCenterX, gbc_lblCenterX);
		
		txtCenterX = new JTextField();
		GridBagConstraints gbc_txtCenterX = new GridBagConstraints();
		gbc_txtCenterX.insets = new Insets(0, 0, 5, 5);
		gbc_txtCenterX.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCenterX.gridx = 3;
		gbc_txtCenterX.gridy = 3;
		contentPanel.add(txtCenterX, gbc_txtCenterX);
		txtCenterX.setColumns(10);
		
		JLabel lblCenterY = new JLabel("Center Y :");
		lblCenterY.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
		GridBagConstraints gbc_lblCenterY = new GridBagConstraints();
		gbc_lblCenterY.insets = new Insets(0, 0, 5, 5);
		gbc_lblCenterY.gridx = 1;
		gbc_lblCenterY.gridy = 4;
		contentPanel.add(lblCenterY, gbc_lblCenterY);
		
		txtCenterY = new JTextField();
		txtCenterY.setText("");
		GridBagConstraints gbc_txtCenterY = new GridBagConstraints();
		gbc_txtCenterY.anchor = GridBagConstraints.NORTH;
		gbc_txtCenterY.insets = new Insets(0, 0, 5, 5);
		gbc_txtCenterY.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCenterY.gridx = 3;
		gbc_txtCenterY.gridy = 4;
		contentPanel.add(txtCenterY, gbc_txtCenterY);
		txtCenterY.setColumns(10);
		
		outerColorButton = new JButton("Outer Color");
		outerColorButton.setBackground(outerC);
		outerColorButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		outerColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outerColor = JColorChooser.showDialog(outerColorButton, "Choose your color", Color.BLACK);
			}
		});
		GridBagConstraints gbc_btnOuterColor = new GridBagConstraints();
		gbc_btnOuterColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOuterColor.anchor = GridBagConstraints.NORTH;
		gbc_btnOuterColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnOuterColor.gridx = 4;
		gbc_btnOuterColor.gridy = 4;
		contentPanel.add(outerColorButton, gbc_btnOuterColor);
		
		JLabel lblRadius = new JLabel("Radius :");
		lblRadius.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
		GridBagConstraints gbc_lblRadius = new GridBagConstraints();
		gbc_lblRadius.anchor = GridBagConstraints.WEST;
		gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
		gbc_lblRadius.gridx = 1;
		gbc_lblRadius.gridy = 5;
		contentPanel.add(lblRadius, gbc_lblRadius);
		
		txtR = new JTextField();
		GridBagConstraints gbc_txtR = new GridBagConstraints();
		gbc_txtR.insets = new Insets(0, 0, 5, 5);
		gbc_txtR.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtR.gridx = 3;
		gbc_txtR.gridy = 5;
		contentPanel.add(txtR, gbc_txtR);
		txtR.setColumns(10);
		
		{
			innerColorButton = new JButton("Inner color");
			innerColorButton.setBackground(innerC);
			innerColorButton.setForeground(new Color(0, 0, 0));
			innerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
			innerColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			  innerColor = JColorChooser.showDialog(null,"Choose inner color of your shape", innerColor);
			  innerColorButton.setBackground(innerColor);
			  //boolean innerColorConfirmation = true;
		  }
		  });
		  GridBagConstraints gbc_innerColorButton = new GridBagConstraints();
		  gbc_innerColorButton.insets = new Insets(0, 0, 0, 5);
		  gbc_innerColorButton.gridx = 0;
		  gbc_innerColorButton.gridy = 6;
		  contentPanel.add(innerColorButton, gbc_innerColorButton);
		  }
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
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
		contentPanel.add(btnOk, gbc_btnOk);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancel.gridx = 5;
		gbc_btnCancel.gridy = 7;
		contentPanel.add(btnCancel, gbc_btnCancel);
	}

	*/
}
