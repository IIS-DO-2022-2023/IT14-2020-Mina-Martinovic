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

public class DlgDonut extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	protected JTextField xCoord;
	protected JTextField yCoord;
	protected JTextField innerRadius;
	protected JTextField outerRadius;
	protected JTextField height;
	
	  private boolean confirmation;
	  private Color innerColor = Color.white;
	  private Color outerColor = Color.white;
		
	  private JButton outerColorButton;
	  private JButton innerColorButton;


	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the dialog.
	 */
	

	public DlgDonut(int x, int y, Color inColor, Color outColor ) {	
		setBounds(100, 100, 450, 300);
		setTitle("Add Donut");
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
			xCoord= new JTextField();
			xCoord.setEnabled(false);
			xCoord.setText(Integer.toString(x)); 
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
			yCoord.setEnabled(false);
			yCoord.setText(Integer.toString(y));
			GridBagConstraints gbc_txtYCoord = new GridBagConstraints();
			gbc_txtYCoord.insets = new Insets(0, 0, 5, 0);
			gbc_txtYCoord.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYCoord.gridx = 1;
			gbc_txtYCoord.gridy = 2;
			contentPanel.add(yCoord, gbc_txtYCoord);
			yCoord.setColumns(10);
		}
		{
			JLabel lblInnerRadius = new JLabel("Inner radius:");
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.insets = new Insets(0, 0, 0, 5);
			gbc_lblInnerRadius.anchor = GridBagConstraints.EAST;
			gbc_lblInnerRadius.gridx = 0;
			gbc_lblInnerRadius.gridy = 3;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			innerRadius = new JTextField();
			GridBagConstraints gbc_txtInnerRadius = new GridBagConstraints();
			gbc_txtInnerRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtInnerRadius.gridx = 1;
			gbc_txtInnerRadius.gridy = 3;
			contentPanel.add(innerRadius, gbc_txtInnerRadius);
			innerRadius.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Outer radius:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 0, 5);
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 4;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			outerRadius = new JTextField();
			GridBagConstraints gbc_txtOuterRadius = new GridBagConstraints();
			gbc_txtOuterRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtOuterRadius.gridx = 1;
			gbc_txtOuterRadius.gridy = 4;
			contentPanel.add(outerRadius, gbc_txtOuterRadius);
			outerRadius.setColumns(10);
		}
	
//		  inner/border color
		  {
			  innerColorButton = new JButton("Inner color");
			  innerColor = inColor;
			  innerColorButton.setBackground(innerColor);
			  innerColor = inColor;
			  innerColorButton.setForeground(new Color(0, 0, 0));
			  innerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
			  innerColorButton.addActionListener(new ActionListener() {
				  public void actionPerformed(ActionEvent e)
				  {
					  innerColor = JColorChooser.showDialog(null,"Choose the inner color of your donut", innerColor);
					  innerColorButton.setBackground(innerColor);
				  }
			  });
		  GridBagConstraints gbc_innerColorButton = new GridBagConstraints();
		  gbc_innerColorButton.insets = new Insets(0, 0, 0, 5);
		  gbc_innerColorButton.gridx = 0;
		  gbc_innerColorButton.gridy = 6;
		  contentPanel.add(innerColorButton, gbc_innerColorButton);
		  }
		  
		  {
		  outerColorButton = new JButton("Outer color");
		  outerColor = outColor;
		  outerColorButton.setBackground(outColor);
		  outerColor = outColor;
		  outerColorButton.setForeground(new Color(255, 255, 255));
		  outerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		  outerColorButton.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e)
		  {
			  outerColor = JColorChooser.showDialog(null,"Choose the outer color of your donut", outerColor);
			  outerColorButton.setBackground(outerColor);
		  }
		  });
		  outerColorButton.setHorizontalAlignment(SwingConstants.LEFT);
		  GridBagConstraints gbc_outerColorButton = new GridBagConstraints();
		  gbc_outerColorButton.gridx = 1;
		  gbc_outerColorButton.gridy = 6;
		  contentPanel.add(outerColorButton, gbc_outerColorButton);
		  }
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						
						if(innerRadius.getText().equals("") || outerRadius.getText().equals(""))
						{
							JOptionPane.showMessageDialog(okButton, "Both inner and outer radius can't be empty!");
						}
						else {
							try {
							
								int inner = Integer.parseInt(innerRadius.getText());
								int outer = Integer.parseInt(outerRadius.getText());
								
								if(inner > outer - 10)
								{
									JOptionPane.showMessageDialog(okButton, "Outer radius must be greater than inner radius for at least 10!");
								}
								else {
									
									setConfirmation(true);
									setVisible(false);
								}
								
							}
							catch(Exception e) {
								
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
						confirmation=false;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
public DlgDonut() {
		
		setBounds(100, 100, 450, 300);
		setTitle("Add donut:");
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
			GridBagConstraints gbc_txtYCoord= new GridBagConstraints();
			gbc_txtYCoord.insets = new Insets(0, 0, 5, 0);
			gbc_txtYCoord.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYCoord.gridx = 1;
			gbc_txtYCoord.gridy = 2;
			contentPanel.add(yCoord, gbc_txtYCoord);
			yCoord.setColumns(10);
		}
		{
			JLabel lblInnerRadius = new JLabel("Inner radius:");
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.insets = new Insets(0, 0, 0, 5);
			gbc_lblInnerRadius.anchor = GridBagConstraints.EAST;
			gbc_lblInnerRadius.gridx = 0;
			gbc_lblInnerRadius.gridy = 3;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			innerRadius = new JTextField();
			GridBagConstraints gbc_txtInnerRadius = new GridBagConstraints();
			gbc_txtInnerRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtInnerRadius.gridx = 1;
			gbc_txtInnerRadius.gridy = 3;
			contentPanel.add(innerRadius, gbc_txtInnerRadius);
			innerRadius.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Outer radius:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 0, 5);
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 4;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			outerRadius = new JTextField();
			GridBagConstraints gbc_txtOuterRadius = new GridBagConstraints();
			gbc_txtOuterRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtOuterRadius.gridx = 1;
			gbc_txtOuterRadius.gridy = 4;
			contentPanel.add(outerRadius, gbc_txtOuterRadius);
			outerRadius.setColumns(10);
		}
		
		  {
			  innerColorButton = new JButton("Inner color");
			  innerColorButton.setBackground(SystemColor.activeCaption);
			  innerColorButton.setForeground(new Color(0, 0, 0));
			  innerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
			  innerColorButton.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e)
			  {
			  innerColor = JColorChooser.showDialog(null,"Choose the inner color of your donut", innerColor);
			  innerColorButton.setBackground(innerColor);
		  }
		  });
		  GridBagConstraints gbc_innerColorButton = new GridBagConstraints();
		  gbc_innerColorButton.insets = new Insets(0, 0, 0, 5);
		  gbc_innerColorButton.gridx = 0;
		  gbc_innerColorButton.gridy = 6;
		  contentPanel.add(innerColorButton, gbc_innerColorButton);
		  }
		  
		  {
		  outerColorButton = new JButton("Outer color");
		  outerColorButton.setBackground(SystemColor.activeCaption);
		  outerColorButton.setForeground(new Color(255, 255, 255));
		  outerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		  outerColorButton.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e)
		  {
			  outerColor = JColorChooser.showDialog(null,"Choose the outer color of your donut", outerColor);
			  outerColorButton.setBackground(outerColor);
		  }
		  });
		  outerColorButton.setHorizontalAlignment(SwingConstants.LEFT);
		  GridBagConstraints gbc_outerColorButton = new GridBagConstraints();
		  gbc_outerColorButton.gridx = 1;
		  gbc_outerColorButton.gridy = 6;
		  contentPanel.add(outerColorButton, gbc_outerColorButton);
		  }
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					
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

	
	public JTextField getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(JTextField innerRadius) {
		this.innerRadius = innerRadius;
	}

	public JTextField getOuterRadius() {
		return outerRadius;
	}

	public void setOuterRadius(JTextField outerRadius) {
		this.outerRadius = outerRadius;
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
