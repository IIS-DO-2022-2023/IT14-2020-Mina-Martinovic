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
	Color innerFill = new Color(255, 255, 255);
	Color borderFill;
	
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
		
		public DlgCircle(int x, int y, Color outColor, Color inColor) {
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
				JButton innerColorButton = new JButton("Boja unutrasnjosti");
				innerColorButton.setBackground(inColor);
				innerColorButton.setForeground(new Color(0, 0, 0));
				innerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
				innerColorButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
				  innerFill = JColorChooser.showDialog(null,"Izaberi boju unutrasnjosti tvog oblika", innerFill);
				  innerColorButton.setBackground(innerFill);
				  boolean innerColorConfirmation = true;
			  }
			  });
			  GridBagConstraints gbc_innerColorButton = new GridBagConstraints();
			  gbc_innerColorButton.insets = new Insets(0, 0, 0, 5);
			  gbc_innerColorButton.gridx = 0;
			  gbc_innerColorButton.gridy = 6;
			  contentPanel.add(innerColorButton, gbc_innerColorButton);
			  }
			  
			  {
			  JButton borderColorButton = new JButton(" Boja ivice");
			  borderColorButton.setBackground(outColor);
			  borderColorButton.setForeground(new Color(0, 0, 0));
			  borderColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
			  borderColorButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
				  borderFill = JColorChooser.showDialog(null,"Izaberi boju ivice tvog oblika", borderFill);
				  borderColorButton.setBackground(borderFill);
				  boolean borderColorConfirmation = true;
			  }
			  });
			  borderColorButton.setHorizontalAlignment(SwingConstants.LEFT);
			  GridBagConstraints gbc_borderColorButton = new GridBagConstraints();
			  gbc_borderColorButton.gridx = 1;
			  gbc_borderColorButton.gridy = 6;
			  contentPanel.add(borderColorButton, gbc_borderColorButton);
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
}