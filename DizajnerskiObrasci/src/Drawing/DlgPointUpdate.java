package Drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

public class DlgPointUpdate extends JDialog {
	
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	protected JTextField txtX;
	protected JTextField txtY;
	private Color outerColor;
	
	private JButton outerColorButton;
	

	private boolean confirmation;
	private Color color = Color.BLACK;


	
	public DlgPointUpdate(int x, int y, Color outColor) {
		setBounds(100, 100, 450, 300);
		setTitle("Add point:");
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
			this.txtX.setText(Integer.toString(x)); 
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
			this.txtY.setText(Integer.toString(y));
			GridBagConstraints gbc_txtYCoord = new GridBagConstraints();
			gbc_txtYCoord.insets = new Insets(0, 0, 5, 0);
			gbc_txtYCoord.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYCoord.gridx = 1;
			gbc_txtYCoord.gridy = 2;
			contentPanel.add(txtY, gbc_txtYCoord);
			txtY.setColumns(10);
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
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent  arg0) {
						if(txtX.getText().equals("") || txtY.getText().equals(""))
						{
							JOptionPane.showMessageDialog(okButton, "X and y coordinates can't be empty!");
						}
						else
						{
							try {
								int x = Integer.parseInt(txtX.getText());
								int y = Integer.parseInt(txtY.getText());
								
								setConfirmation(true);
								setVisible(false);
							}
							catch (Exception e) {
								
								JOptionPane.showMessageDialog(okButton, "Input values must be a number!");
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public Color getOuterColor() {
		return outerColor;
	}

	public void setOuterColor(Color outerColor) {
		this.outerColor = outerColor;
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

}

