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
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField xCoord;
	protected JTextField yCoord;
	protected JTextField width;
	protected JTextField height;
	
	private boolean confirmation;
	private Color innerColor = new Color(255, 255, 255);
	private Color outerColor;
	
	private JButton outerColorButton;
	private JButton innerColorButton;

	public JTextField getXCoord() {
		return xCoord;
	}

	public void setXCoord(JTextField xCoord) {
		this.xCoord = xCoord;
	}

	public JTextField getYCoord() {
		return yCoord;
	}

	public void setYCoord(JTextField yCoord) {
		this.yCoord = yCoord;
	}

	public JTextField getTxtWidth() {
		return width;
	}

	public void setWidth(JTextField width) {
		this.width = width;
	}

	public JTextField getTxtHeight() {
		return height;
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
		this.height = height;
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

	public static void main(String[] args) {
		try {
			DlgRectangle Dialog = new DlgRectangle();
			Dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			Dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setBounds(100, 100, 450, 300);
		setTitle("Add rectangle");
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
			xCoord = new JTextField();
			GridBagConstraints gbc_txtXCoord = new GridBagConstraints();
			gbc_txtXCoord.insets = new Insets(0, 0, 5, 0);
			gbc_txtXCoord.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXCoord.gridx = 1;
			gbc_txtXCoord.gridy = 1;
			contentPanel.add(xCoord, gbc_txtXCoord);
			xCoord.setColumns(10);
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
			yCoord = new JTextField();
			GridBagConstraints gbc_txtYCoord = new GridBagConstraints();
			gbc_txtYCoord.insets = new Insets(0, 0, 5, 0);
			gbc_txtYCoord.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYCoord.gridx = 1;
			gbc_txtYCoord.gridy = 2;
			contentPanel.add(yCoord, gbc_txtYCoord);
			yCoord.setColumns(10);
		}
		{
			JLabel lblWIdth = new JLabel("Width:");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.insets = new Insets(0, 0, 0, 5);
			gbc_lblWidth.anchor = GridBagConstraints.EAST;
			gbc_lblWidth.gridx = 0;
			gbc_lblWidth.gridy = 3;
			contentPanel.add(lblWIdth, gbc_lblWidth);
		}
		{
			width = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 1;
			gbc_txtWidth.gridy = 3;
			contentPanel.add(width, gbc_txtWidth);
			width.setColumns(10);
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
			height = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 1;
			gbc_txtHeight.gridy = 4;
			contentPanel.add(height, gbc_txtHeight);
			height.setColumns(10);
		}
		
	
		{
		  innerColorButton = new JButton("Inner color:");
		  innerColorButton.setBackground(SystemColor.activeCaption);
		  innerColorButton.setForeground(new Color(0, 0, 0));
		  innerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		  innerColorButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
				  innerColor = JColorChooser.showDialog(null,"Choose the inner color of your rectangle", innerColor);
				  innerColorButton.setBackground(innerColor);
				 // boolean innerColorConfirmation = true;
				 
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
		  outerColorButton.setBackground(SystemColor.activeCaption);
		  outerColorButton.setForeground(new Color(0, 0, 0));
		  outerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		  outerColorButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
				  outerColor = JColorChooser.showDialog(null,"Choose the outer color of your rectangle", outerColor);
				  outerColorButton.setBackground(outerColor);
			  }
 		  });
		  outerColorButton.setHorizontalAlignment(SwingConstants.LEFT);
		  GridBagConstraints gbc_outerColorButton = new GridBagConstraints();
		  gbc_outerColorButton.gridx = 1;
		  gbc_outerColorButton.gridy = 7;
		  contentPanel.add(outerColorButton, gbc_outerColorButton);
		  }
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					/**
					 * public void actionPerformed(Actionevent e) 
					 * {
					 * try
					 * {
					 * int newX = Integer.parseInt(xKoordinata.getText());
					 * int newY = Integer.parseInt(yKoordinata.getText());
					 * int newHeight = Integer.parseInt(visina.getText());
					 * int newWidth = Integer.parseInt(sirina.getText());
					 * 
					 * if(newX < 0 || newY < 0 || newHeight < 1 || newWidth < 1)
					 * {
					 * JOptionPane.showMessageDialog(null, "Uneli ste pogresnu vrednost!", JOptionPane.ERROR_MESSAGE);
					 * return;
					 * }
					 * confirmation = true;
					 * dispose();
					 * }
					 * catch(Exeption E)
					 * {
					 * JOptionPane.showMessageDialog(null, "Uneli ste pogresan tip podatka!", JOptionPane.ERROR_MESSAGE);
					 * }
					 * }
					 * });
					 * 
					 */
					/*
					public void actionPerformed(ActionEvent e) {
						boolean isModifying = false;
						// provera da li se radi modifikacija
						for (int i=PanelDrawing.shapes.size()-1; i>=0; i--) {
							if(PanelDrawing.shapes.get(i).isSelected()) {
								Rectangle r1 = new Rectangle(
										new Point(Integer.parseInt(xKoordinata.getText()), Integer.parseInt(yKoordinata.getText())),
										Integer.parseInt(width.getText()),
										Integer.parseInt(height.getText()));
								r1.setColor(innerFill);
								r1.setEdgeColor(borderFill);
								PanelDrawing.shapes.set(i, r1);  // zameni tekuci element sa izmenjenim
								isModifying = true;
								break;
							}
						}
						// radimo dodavanje objekta
						if (isModifying == false) {
							Rectangle r1 = new Rectangle(
									new Point(Integer.parseInt(xKoordinata.getText()), Integer.parseInt(yKoordinata.getText())),
									Integer.parseInt(width.getText()),
									Integer.parseInt(height.getText()));
							r1.setColor(innerFill);
							r1.setEdgeColor(borderFill);
							PanelDrawing.shapes.add(r1);
						}
						
						isOk=true;
						setVisible(false);
					}
					*/
					public void actionPerformed(ActionEvent arg0) {
						setConfirmation(true);
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
			xCoord = new JTextField();
			xCoord.setText(Integer.toString(x));
			GridBagConstraints gbc_txtXCoord = new GridBagConstraints();
			gbc_txtXCoord.insets = new Insets(0, 0, 5, 0);
			gbc_txtXCoord.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXCoord.gridx = 1;
			gbc_txtXCoord.gridy = 1;
			contentPanel.add(this.xCoord, gbc_txtXCoord);
			this.xCoord.setColumns(10);
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
			yCoord = new JTextField();
			yCoord.setText(Integer.toString(y));
			GridBagConstraints gbc_txtYCoord = new GridBagConstraints();
			gbc_txtYCoord.insets = new Insets(0, 0, 5, 0);
			gbc_txtYCoord.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYCoord.gridx = 1;
			gbc_txtYCoord.gridy = 2;
			contentPanel.add(this.yCoord, gbc_txtYCoord);
			this.yCoord.setColumns(10);
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
			width = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 1;
			gbc_txtWidth.gridy = 3;
			contentPanel.add(width, gbc_txtWidth);
			width.setColumns(10);
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
			height = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 1;
			gbc_txtHeight.gridy = 4;
			contentPanel.add(height, gbc_txtHeight);
			height.setColumns(10);
		}
		
		

//		  inner/border color
		{
		  innerColorButton = new JButton("Inner color:");
		  innerColorButton.setBackground(inColor);
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
		  outerColorButton.setBackground(outColor);
		  outerColorButton.setForeground(new Color(0, 0, 0));
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
					/**
					 * public void actionPerformed(Actionevent e) 
					 * {
					 * try
					 * {
					 * int newX = Integer.parseInt(xKoordinata.getText());
					 * int newY = Integer.parseInt(yKoordinata.getText());
					 * int newHeight = Integer.parseInt(visina.getText());
					 * int newWidth = Integer.parseInt(sirina.getText());
					 * 
					 * if(newX < 0 || newY < 0 || newHeight < 1 || newWidth < 1)
					 * {
					 * JOptionPane.showMessageDialog(null, "Uneli ste pogresnu vrednost!", JOptionPane.ERROR_MESSAGE);
					 * return;
					 * }
					 * confirmation = true;
					 * dispose();
					 * }
					 * catch(Exeption E)
					 * {
					 * JOptionPane.showMessageDialog(null, "Uneli ste pogresan tip podatka!", JOptionPane.ERROR_MESSAGE);
					 * }
					 * }
					 * });
					 * 
					 */
					/*
					public void actionPerformed(ActionEvent e) {
						boolean isModifying = false;
						// provera da li se radi modifikacija
						for (int i=PanelDrawing.shapes.size()-1; i>=0; i--) {
							if(PanelDrawing.shapes.get(i).isSelected()) {
								Rectangle r1 = new Rectangle(
										new Point(Integer.parseInt(xKoordinata.getText()), Integer.parseInt(yKoordinata.getText())),
										Integer.parseInt(width.getText()),
										Integer.parseInt(height.getText()));
								r1.setColor(innerFill);
								r1.setEdgeColor(borderFill);
								PanelDrawing.shapes.set(i, r1);  // zameni tekuci element sa izmenjenim
								isModifying = true;
								break;
							}
						}
						// radimo dodavanje objekta
						if (isModifying == false) {
							Rectangle r1 = new Rectangle(
									new Point(Integer.parseInt(xKoordinata.getText()), Integer.parseInt(yKoordinata.getText())),
									Integer.parseInt(width.getText()),
									Integer.parseInt(height.getText()));
							r1.setColor(innerFill);
							r1.setEdgeColor(borderFill);
							PanelDrawing.shapes.add(r1);
						}
						
						isOk=true;
						setVisible(false);
					}
					*/
					public void actionPerformed(ActionEvent arg0) {
						setConfirmation(true);
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

	public JPanel getContentPanel() {
		return contentPanel;
	}


}
