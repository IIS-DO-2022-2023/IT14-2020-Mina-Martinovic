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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import geometry1.Point;

public class DlgRectangleUpdate extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUpperLeftPointX;
	private JTextField txtUpperLeftPointY;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private boolean confirmation;
	
	private Color outerColor;
	private Color innerColor;
	
	private JButton outerColorButton;
	private JButton innerColorButton;

	
	public DlgRectangleUpdate (Point upperLeftPoint, int width, int height, Color inColor, Color outColor) {
		setResizable(false);
		setModal(true);
		setTitle("Update Rectangle");
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
				JLabel lblUpperLeftPointX = new JLabel(" Upper Left Point X:");
				lblUpperLeftPointX.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
				GridBagConstraints gbc_lblUpperLeftPointX = new GridBagConstraints();
				gbc_lblUpperLeftPointX.insets = new Insets(0, 0, 5, 5);
				gbc_lblUpperLeftPointX.gridx = 0;
				gbc_lblUpperLeftPointX.gridy = 0;
				contentPanel.add(lblUpperLeftPointX, gbc_lblUpperLeftPointX);
			}
			{
				txtUpperLeftPointX  = new JTextField();
				txtUpperLeftPointX .setText(Integer.toString(upperLeftPoint.getX()));
				GridBagConstraints gbc_txtUpperLeftPointX = new GridBagConstraints();
				gbc_txtUpperLeftPointX.anchor = GridBagConstraints.NORTH;
				gbc_txtUpperLeftPointX.insets = new Insets(0, 0, 5, 5);
				gbc_txtUpperLeftPointX.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtUpperLeftPointX.gridx = 1;
				gbc_txtUpperLeftPointX.gridy = 0;
				contentPanel.add(txtUpperLeftPointX , gbc_txtUpperLeftPointX);
				txtUpperLeftPointX .setColumns(10);
			}
			{
				JLabel lblUpperLeftPointY = new JLabel("Upper Left Point Y:");
				lblUpperLeftPointY.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
				GridBagConstraints gbc_lblUpperLeftPointY = new GridBagConstraints();
				gbc_lblUpperLeftPointY.insets = new Insets(0, 0, 5, 5);
				gbc_lblUpperLeftPointY.gridx = 0;
				gbc_lblUpperLeftPointY.gridy = 1;
				contentPanel.add(lblUpperLeftPointY, gbc_lblUpperLeftPointY);
			}
			{
				txtUpperLeftPointY  = new JTextField();
				txtUpperLeftPointY .setText(Integer.toString(upperLeftPoint.getY()));
				GridBagConstraints gbc_txtUpperLeftPointY = new GridBagConstraints();
				gbc_txtUpperLeftPointY.anchor = GridBagConstraints.NORTH;
				gbc_txtUpperLeftPointY.insets = new Insets(0, 0, 5, 5);
				gbc_txtUpperLeftPointY.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtUpperLeftPointY.gridx = 1;
				gbc_txtUpperLeftPointY.gridy = 1;
				contentPanel.add(txtUpperLeftPointY , gbc_txtUpperLeftPointY );
				txtUpperLeftPointY .setColumns(10);
			}
			{
				JLabel lblHeight = new JLabel("Height:");
				lblHeight.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
				GridBagConstraints gbc_lblHeight = new GridBagConstraints();
				gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
				gbc_lblHeight.gridx = 0;
				gbc_lblHeight.gridy = 2;
				contentPanel.add(lblHeight, gbc_lblHeight);
			}
			{
				txtHeight = new JTextField();
				txtHeight.setText(Integer.toString(height));
				GridBagConstraints gbc_txtHeight = new GridBagConstraints();
				gbc_txtHeight.insets = new Insets(0, 0, 5, 5);
				gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtHeight.anchor = GridBagConstraints.NORTH;
				gbc_txtHeight.gridx = 1;
				gbc_txtHeight.gridy = 2;
				contentPanel.add(txtHeight, gbc_txtHeight);
				txtHeight.setColumns(10);
			}
			{
				JLabel lblWidth = new JLabel("Width :");
				lblWidth.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
				GridBagConstraints gbc_lblOuterRadius = new GridBagConstraints();
				gbc_lblOuterRadius.insets = new Insets(0, 0, 5, 5);
				gbc_lblOuterRadius.gridx = 0;
				gbc_lblOuterRadius.gridy = 3;
				contentPanel.add(lblWidth, gbc_lblOuterRadius);
			}
			{
				txtWidth = new JTextField();
				txtWidth.setText(Integer.toString(width));
				GridBagConstraints gbc_txtWidth = new GridBagConstraints();
				gbc_txtWidth.insets = new Insets(0, 0, 5, 5);
				gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtWidth.gridx = 1;
				gbc_txtWidth.gridy = 3;
				contentPanel.add(txtWidth, gbc_txtWidth);
				txtWidth.setColumns(10);
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
							if(txtWidth.getText().equals("") || txtHeight.getText().equals("") || txtUpperLeftPointX.getText().equals("") || txtUpperLeftPointY.getText().equals(""))
							{
								JOptionPane.showMessageDialog(okButton, "Radius, x and y coordinates can't be empty!");
							}
							else
							{
								try {
									int width = Integer.parseInt(txtWidth.getText());
									int height = Integer.parseInt(txtHeight.getText());
									int x = Integer.parseInt(txtUpperLeftPointX.getText());
									int y = Integer.parseInt(txtUpperLeftPointY.getText());
									
									if(width < 2 && height < 2)
									{
										JOptionPane.showMessageDialog(okButton, "Both width and height must be at least a value of 2!");
									}
									else {
										
										setConfirmation(true);
										setVisible(false);
									}
								}
								catch (Exception e) {
									
									JOptionPane.showMessageDialog(okButton, "Both width and height must be a number!");
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
	
	public JTextField getTxtUpperLeftPointX() {
		return txtUpperLeftPointX;
	}
	
	public void setTxtUpperLeftPointX(JTextField txtUpperLeftPointX) {
		this.txtUpperLeftPointX = txtUpperLeftPointX;
	}
	
	public JTextField getTxtUpperLeftPointY() {
		return txtUpperLeftPointY;
	}
	
	public void setTxtUpperLeftPointY(JTextField txtUpperLeftPointY) {
		this.txtUpperLeftPointY = txtUpperLeftPointY;
	}
	
	public JTextField getTxtWidth() {
		return txtWidth;
	}
	
	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}
	
	public JTextField getTxtHeight() {
		return txtHeight;
	}
	
	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
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
	
}
