package Drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class DlgRectangle extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	protected JTextField txtX;
	protected JTextField txtY;
	protected JTextField txtWidth;
	protected JTextField txtHeight;
	
	private boolean confirmation;
	private Color innerColor;
	private Color outerColor;
	
	private JButton outerColorButton;
	private JButton innerColorButton;

	
	public DlgRectangle(int x, int y, Color inColor, Color outColor) {
		setBounds(100, 100, 450, 300);
		setTitle("Add rectangle:");
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
			JLabel lblXCoord = new JLabel("X coord:");
			GridBagConstraints gbc_lblXCoord = new GridBagConstraints();
			gbc_lblXCoord.anchor = GridBagConstraints.EAST;
			gbc_lblXCoord.insets = new Insets(0, 0, 5, 5);
			gbc_lblXCoord.gridx = 0;
			gbc_lblXCoord.gridy = 1;
			contentPanel.add(lblXCoord, gbc_lblXCoord);
		}
		{
			txtX = new JTextField();
			txtX.setEnabled(false);
			txtX.setText(Integer.toString(x));
			GridBagConstraints gbc_txtXCoord = new GridBagConstraints();
			gbc_txtXCoord.insets = new Insets(0, 0, 5, 0);
			gbc_txtXCoord.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXCoord.gridx = 1;
			gbc_txtXCoord.gridy = 1;
			contentPanel.add(this.txtX, gbc_txtXCoord);
			this.txtX.setColumns(10);
		}
		{
			JLabel lblYCoord = new JLabel("Y coord:");
			GridBagConstraints gbc_lblYCoord = new GridBagConstraints();
			gbc_lblYCoord.anchor = GridBagConstraints.EAST;
			gbc_lblYCoord.insets = new Insets(0, 0, 5, 5);
			gbc_lblYCoord.gridx = 0;
			gbc_lblYCoord.gridy = 2;
			contentPanel.add(lblYCoord, gbc_lblYCoord);
		}
		{
			txtY = new JTextField();
			txtY.setEnabled(false);
			txtY.setText(Integer.toString(y));
			GridBagConstraints gbc_txtYCoord = new GridBagConstraints();
			gbc_txtYCoord.insets = new Insets(0, 0, 5, 0);
			gbc_txtYCoord.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYCoord.gridx = 1;
			gbc_txtYCoord.gridy = 2;
			contentPanel.add(this.txtY, gbc_txtYCoord);
			this.txtY.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.insets = new Insets(0, 0, 0, 5);
			gbc_lblWidth.anchor = GridBagConstraints.EAST;
			gbc_lblWidth.gridx = 0;
			gbc_lblWidth.gridy = 3;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 1;
			gbc_txtWidth.gridy = 3;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Height:");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.insets = new Insets(0, 0, 0, 5);
			gbc_lblHeight.anchor = GridBagConstraints.EAST;
			gbc_lblHeight.gridx = 0;
			gbc_lblHeight.gridy = 4;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 1;
			gbc_txtHeight.gridy = 4;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		
		

//		  inner/border color
		{
		  innerColorButton = new JButton("Inner color:");
		  innerColor = inColor;
		  innerColorButton.setBackground(innerColor);
		  innerColorButton.setForeground(new Color(0, 0, 0));
		  innerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		  innerColorButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
				  innerColor = JColorChooser.showDialog(null,"Choose the inner color of your rectangle", innerColor);
				  innerColorButton.setBackground(innerColor);				 
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
		  outerColorButton = new JButton("Outer color:");
		  outerColor = outColor;
		  outerColorButton.setBackground(outColor);
		  outerColorButton.setForeground(new Color(255, 255, 255));
		  outerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		  outerColorButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
				  outerColor = JColorChooser.showDialog(null,"Choose the outer color of your rectangle", outerColor);
				  outerColorButton.setBackground(outerColor);			  }
 		  });
		  outerColorButton.setHorizontalAlignment(SwingConstants.LEFT);
		  GridBagConstraints gbc_borderColorButton = new GridBagConstraints();
		  gbc_borderColorButton.gridx = 1;
		  gbc_borderColorButton.gridy = 7;
		  contentPanel.add(outerColorButton, gbc_borderColorButton);
		  }
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						if(txtWidth.getText().equals("") || txtHeight.getText().equals(""))
						{
							JOptionPane.showMessageDialog(okButton, "Neither width nor height can't be empty!");
						}
						else
						{
							try {
								int width = Integer.parseInt(txtWidth.getText());
								int height = Integer.parseInt(txtHeight.getText());
								
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

	public JPanel getContentPanel() {
		return contentPanel;
	}


	public JTextField getXCoord() {
		return txtX;
	}

	public void setXCoord(JTextField xCoord) {
		this.txtX = xCoord;
	}

	public JTextField getYCoord() {
		return txtY;
	}

	public void setYCoord(JTextField yCoord) {
		this.txtY = yCoord;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setWidth(JTextField width) {
		this.txtWidth = width;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
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

	public void setHeight(JTextField height) {
		this.txtHeight = height;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
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
