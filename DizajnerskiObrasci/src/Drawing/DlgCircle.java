package Drawing;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class DlgCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRadius;
	private JTextField txtX;
	private JTextField txtY;

	private boolean confirmation;
	private Color innerColor;
	private Color outerColor;
	
	private JButton outerColorButton;
	private JButton innerColorButton;
	

	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Create the dialog.
	*/
	public DlgCircle() {
		setResizable(false);
		setModal(true);
		setTitle("Add Circle");
		setBounds(100, 100, 300, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{113, 87, 76, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{22, 0, 0, 0, 34, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblRadius = new JLabel("Enter radius:");
			lblRadius.setFont(new Font("Bahnschrift", Font.BOLD, 14));
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 0, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 1;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius.insets = new Insets(0, 0, 5, 5);
			gbc_txtRadius.anchor = GridBagConstraints.NORTH;
			gbc_txtRadius.gridx = 1;
			gbc_txtRadius.gridy = 1;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
		}
		{
			JButton okButton = new JButton("OK");
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_okButton.insets = new Insets(0, 0, 5, 5);
			gbc_okButton.gridx = 0;
			gbc_okButton.gridy = 4;
			contentPanel.add(okButton, gbc_okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setConfirmation(true);
					setVisible(false);
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
			gbc_cancelButton.gridx = 1;
			gbc_cancelButton.gridy = 4;
			contentPanel.add(cancelButton, gbc_cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					dispose();
					
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
	}
		
		public DlgCircle(int x, int y, Color inColor, Color outColor) {
			setResizable(false);
			setModal(true);
			setTitle("Add Circle");
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
				contentPanel.add(txtX, gbc_txtXCoord);
				txtX.setColumns(10);
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
				contentPanel.add(txtY, gbc_txtYCoord);
				txtY.setColumns(10);
			}	
			
			{
				JLabel lblRadius = new JLabel("Enter radius:");
				lblRadius.setFont(new Font("Bahnschrift", Font.BOLD, 14));
				GridBagConstraints gbc_lblRadius = new GridBagConstraints();
				gbc_lblRadius.insets = new Insets(0, 0, 0, 5);
				gbc_lblRadius.gridx = 0;
				gbc_lblRadius.gridy = 3;
				contentPanel.add(lblRadius, gbc_lblRadius);
			}
			
			{
				txtRadius = new JTextField();
				GridBagConstraints gbc_txtRadius = new GridBagConstraints();
				gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtRadius.insets = new Insets(0, 0, 5, 5);
				gbc_txtRadius.anchor = GridBagConstraints.NORTH;
				gbc_txtRadius.gridx = 1;
				gbc_txtRadius.gridy = 3;
				contentPanel.add(txtRadius, gbc_txtRadius);
				txtRadius.setColumns(10);
			}
			
			{
				innerColorButton = new JButton("Inner color:");
				innerColor = inColor; 
				innerColorButton.setBackground(innerColor);
				innerColorButton.setForeground(new Color(0, 0, 0));
				innerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
				innerColorButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
				  innerColor = JColorChooser.showDialog(null,"Choose the inner color for your circle", innerColor);
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
			  outerColorButton = new JButton("Outer color:");
			  outerColor = outColor;
			  outerColorButton.setBackground(outerColor);
			  outerColorButton.setForeground(new Color(255, 255, 255));
			  outerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
			  outerColorButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
				  outerColor = JColorChooser.showDialog(null,"Choose the outer color of your circle", outerColor);
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
				JPanel panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.insets = new Insets(0, 0, 5, 5);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 1;
				gbc_panel.gridy = 2;
				contentPanel.add(panel, gbc_panel);
			}
			{
				JButton okButton = new JButton("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.fill = GridBagConstraints.HORIZONTAL;
				gbc_okButton.insets = new Insets(0, 0, 5, 5);
				gbc_okButton.gridx = 0;
				gbc_okButton.gridy = 4;
				contentPanel.add(okButton, gbc_okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(txtRadius.getText().equals(""))
						{
							JOptionPane.showMessageDialog(okButton, "Radius can't be empty!");
						}
						else
						{
							try {
								int radius = Integer.parseInt(txtRadius.getText());
								
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
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.fill = GridBagConstraints.HORIZONTAL;
				gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
				gbc_cancelButton.gridx = 1;
				gbc_cancelButton.gridy = 4;
				contentPanel.add(cancelButton, gbc_cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			
			
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
}