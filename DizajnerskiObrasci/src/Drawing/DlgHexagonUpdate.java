package Drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import geometry1.Point;


public class DlgHexagonUpdate extends JDialog{

	
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCenterX;
	private JTextField txtCenterY;
	private boolean confirmation;
	private int radius;
	private JTextField txtRadius;
	
	private Color innerColor;
	private Color outerColor;
	
	private JButton outerColorButton;
	private JButton innerColorButton;
	
	
	public DlgHexagonUpdate(Point center, int radius, Color inColor, Color outColor) {
		setResizable(false);
		setModal(true);
		setTitle("Update Hexagon");
		setBounds(100, 100, 390, 277);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{95, 120, 116, 0};
		gbl_contentPanel.rowHeights = new int[]{22, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			{
				JLabel lblCenterX  = new JLabel("X:");
				lblCenterX .setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
				GridBagConstraints gbc_lblCenterX = new GridBagConstraints();
				gbc_lblCenterX.insets = new Insets(0, 0, 5, 5);
				gbc_lblCenterX.gridx = 0;
				gbc_lblCenterX.gridy = 0;
				contentPanel.add(lblCenterX , gbc_lblCenterX);
			}
			{
				txtCenterX  = new JTextField();
				txtCenterX .setText(Integer.toString(center.getX()));
				GridBagConstraints gbc_txtCenterX = new GridBagConstraints();
				gbc_txtCenterX.anchor = GridBagConstraints.NORTH;
				gbc_txtCenterX.insets = new Insets(0, 0, 5, 5);
				gbc_txtCenterX.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtCenterX.gridx = 1;
				gbc_txtCenterX.gridy = 0;
				contentPanel.add(txtCenterX , gbc_txtCenterX);
				txtCenterX .setColumns(10);
			}
			{
				JLabel lblCenterY = new JLabel("Y:");
				lblCenterY.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
				GridBagConstraints gbc_lblCenterY = new GridBagConstraints();
				gbc_lblCenterY.insets = new Insets(0, 0, 5, 5);
				gbc_lblCenterY.gridx = 0;
				gbc_lblCenterY.gridy = 1;
				contentPanel.add(lblCenterY, gbc_lblCenterY);
			}
			{
				txtCenterY = new JTextField();
				txtCenterY.setText(Integer.toString(center.getY()));
				GridBagConstraints gbc_txtCenterY = new GridBagConstraints();
				gbc_txtCenterY.anchor = GridBagConstraints.NORTH;
				gbc_txtCenterY.insets = new Insets(0, 0, 5, 5);
				gbc_txtCenterY.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtCenterY.gridx = 1;
				gbc_txtCenterY.gridy = 1;
				contentPanel.add(txtCenterY, gbc_txtCenterY);
				txtCenterY.setColumns(10);
			}
			{
				JLabel lblRadius = new JLabel("Radius:");
				lblRadius.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
				GridBagConstraints gbc_lblRadius = new GridBagConstraints();
				gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
				gbc_lblRadius.gridx = 0;
				gbc_lblRadius.gridy = 2;
				contentPanel.add(lblRadius, gbc_lblRadius);
			}
			{
				txtRadius = new JTextField();
				txtRadius.setText(Integer.toString(radius));
				GridBagConstraints gbc_txtRadius = new GridBagConstraints();
				gbc_txtRadius.insets = new Insets(0, 0, 5, 5);
				gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtRadius.anchor = GridBagConstraints.NORTH;
				gbc_txtRadius.gridx = 1;
				gbc_txtRadius.gridy = 2;
				contentPanel.add(txtRadius, gbc_txtRadius);
				txtRadius.setColumns(10);
			}
			
			outerColorButton = new JButton("Outer Color");
			outerColor = outColor;
			outerColorButton.setBackground(outerColor);
			outerColorButton.setForeground(new Color(255, 255, 255));
			outerColorButton.setFont(new Font("Tahoma", Font.BOLD, 10));
			outerColorButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					outerColor =  JColorChooser.showDialog(outerColorButton, "Choose your colour", outerColor);
					outerColorButton.setBackground(outerColor);
				}
			});
			GridBagConstraints gbc_outerColorButton = new GridBagConstraints();
			gbc_outerColorButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_outerColorButton.anchor = GridBagConstraints.SOUTH;
			gbc_outerColorButton.insets = new Insets(0, 0, 5, 5);
			gbc_outerColorButton.gridx = 0;
			gbc_outerColorButton.gridy = 4;
			contentPanel.add(outerColorButton, gbc_outerColorButton);
		}
		{
			innerColorButton = new JButton("Inner Color");
			innerColor = inColor;
			innerColorButton.setBackground(innerColor);
			innerColorButton.setForeground(new Color(0, 0, 0));
			innerColorButton.setFont(new Font("Tahoma", Font.BOLD, 10));
			innerColorButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerColor = JColorChooser.showDialog(innerColorButton, "Choose your color:", innerColor);
					innerColorButton.setBackground(innerColor);
				}
			});
			GridBagConstraints gbc_innerColorButton = new GridBagConstraints();
			gbc_innerColorButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_innerColorButton.insets = new Insets(0, 0, 0, 5);
			gbc_innerColorButton.gridx = 0;
			gbc_innerColorButton.gridy = 5;
			contentPanel.add(innerColorButton, gbc_innerColorButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(txtRadius.getText().equals("") || txtCenterX.getText().equals("") || txtCenterY.getText().equals(""))
						{
							JOptionPane.showMessageDialog(okButton, "Radius, x and y coordinates can't be empty!");
						}
						else
						{
							try {
								int radius = Integer.parseInt(txtRadius.getText());
								int x = Integer.parseInt(txtCenterX.getText());
								int y = Integer.parseInt(txtCenterY.getText());
								
								setConfirmation(true);
								setVisible(false);
							}
							catch (Exception e) {
								
								JOptionPane.showMessageDialog(okButton, "Radius must be a number!");
							}
						
						}
							
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
						
						dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
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

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtR(JTextField txtRadius) {
		this.txtRadius = txtRadius;
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

}
