package Drawing;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import geometry1.Point;

import java.awt.Font;

public class DlgDonutUpdate extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtInnerRadius;
	private boolean confirmation;
	private JTextField txtOuterRadius;
	
	private Color outerColor;
	private Color innerColor;
	private JTextField txtX;
	private JTextField txtY;
	
	private JButton outerColorButton;
	private JButton innerColorButton;
	
	
	
		public DlgDonutUpdate(Point center, int innerRadius, int outerRadius, Color inColor, Color outColor) {
			setResizable(false);
			setModal(true);
			setTitle("Update Donut");
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
					JLabel lblX = new JLabel("X:");
					lblX.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
					GridBagConstraints gbc_lblX = new GridBagConstraints();
					gbc_lblX.insets = new Insets(0, 0, 5, 5);
					gbc_lblX.gridx = 0;
					gbc_lblX.gridy = 0;
					contentPanel.add(lblX, gbc_lblX);
				}
				{
					txtX = new JTextField();
					txtX.setText(Integer.toString(center.getX()));
					GridBagConstraints gbc_txtX = new GridBagConstraints();
					gbc_txtX.anchor = GridBagConstraints.NORTH;
					gbc_txtX.insets = new Insets(0, 0, 5, 5);
					gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtX.gridx = 1;
					gbc_txtX.gridy = 0;
					contentPanel.add(txtX, gbc_txtX);
					txtX.setColumns(10);
				}
				{
					JLabel lblY = new JLabel("Y:");
					lblY.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
					GridBagConstraints gbc_lblY = new GridBagConstraints();
					gbc_lblY.insets = new Insets(0, 0, 5, 5);
					gbc_lblY.gridx = 0;
					gbc_lblY.gridy = 1;
					contentPanel.add(lblY, gbc_lblY);
				}
				{
					txtY = new JTextField();
					txtY.setText(Integer.toString(center.getY()));
					GridBagConstraints gbc_txtY = new GridBagConstraints();
					gbc_txtY.anchor = GridBagConstraints.NORTH;
					gbc_txtY.insets = new Insets(0, 0, 5, 5);
					gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtY.gridx = 1;
					gbc_txtY.gridy = 1;
					contentPanel.add(txtY, gbc_txtY);
					txtY.setColumns(10);
				}
				{
					JLabel lblInnerRadius = new JLabel("Inner Radius:");
					lblInnerRadius.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
					GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
					gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
					gbc_lblInnerRadius.gridx = 0;
					gbc_lblInnerRadius.gridy = 2;
					contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
				}
				{
					txtInnerRadius = new JTextField();
					txtInnerRadius.setText(Integer.toString(innerRadius));
					GridBagConstraints gbc_txtInnerRadius = new GridBagConstraints();
					gbc_txtInnerRadius.insets = new Insets(0, 0, 5, 5);
					gbc_txtInnerRadius.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtInnerRadius.anchor = GridBagConstraints.NORTH;
					gbc_txtInnerRadius.gridx = 1;
					gbc_txtInnerRadius.gridy = 2;
					contentPanel.add(txtInnerRadius, gbc_txtInnerRadius);
					txtInnerRadius.setColumns(10);
				}
				{
					JLabel lblOuterRadius = new JLabel("OuterRadius :");
					lblOuterRadius.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
					GridBagConstraints gbc_lblOuterRadius = new GridBagConstraints();
					gbc_lblOuterRadius.insets = new Insets(0, 0, 5, 5);
					gbc_lblOuterRadius.gridx = 0;
					gbc_lblOuterRadius.gridy = 3;
					contentPanel.add(lblOuterRadius, gbc_lblOuterRadius);
				}
				{
					txtOuterRadius = new JTextField();
					txtOuterRadius.setText(Integer.toString(outerRadius));
					GridBagConstraints gbc_txtOuterRadius = new GridBagConstraints();
					gbc_txtOuterRadius.insets = new Insets(0, 0, 5, 5);
					gbc_txtOuterRadius.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtOuterRadius.gridx = 1;
					gbc_txtOuterRadius.gridy = 3;
					contentPanel.add(txtOuterRadius, gbc_txtOuterRadius);
					txtOuterRadius.setColumns(10);
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

							if(txtInnerRadius.getText().equals("") || txtOuterRadius.getText().equals("") || txtX.getText().equals("") || txtY.getText().equals(""))
							{
								JOptionPane.showMessageDialog(okButton, "Both inner and outer radius, x and y coordinates can't be empty!");
							}
							else
							{
								try {
									int innerRadius = Integer.parseInt(txtInnerRadius.getText());
									int outerRadius = Integer.parseInt(txtOuterRadius.getText());
									int x = Integer.parseInt(txtX.getText());
									int y = Integer.parseInt(txtY.getText());
									
									if(innerRadius > outerRadius - 10 || innerRadius < 2 || outerRadius < 2 || x < 2 || y < 2)
									{
										JOptionPane.showMessageDialog(okButton, "Outer radius must be greater than inner radius for at least 10 and both values of inner and outer radius must at least be a value of 2!");
									}
									else {
										
										setConfirmation(true);
										setVisible(false);
									}
								}
								catch (Exception e) {
									
									JOptionPane.showMessageDialog(okButton, "Both inner and outer radius must be a number!");
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
		
		public JTextField getTxtInnerRadius() {
			return txtInnerRadius;
		}
		
		public void setTxtInnerRadius(JTextField txtInnerRadius) {
			this.txtInnerRadius = txtInnerRadius;
		}
		
		public boolean isConfirmation() {
			return confirmation;
		}
		
		public void setConfirmation(boolean confirm) {
			this.confirmation = confirm;
		}
		
		public JTextField getTxtOuterRadius() {
			return txtOuterRadius;
		}
		
		public void setTxtOuterRadius(JTextField txtOuterRadius) {
			this.txtOuterRadius = txtOuterRadius;
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
		
		public JPanel getContentPanel() {
			return contentPanel;
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
