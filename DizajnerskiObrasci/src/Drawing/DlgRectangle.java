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
	protected JTextField xKoordinata;
	protected JTextField yKoordinata;
	protected JTextField width;
	protected JTextField height;
	//protected boolean isOk;
	
	  private boolean confirmation;

	  Color innerFill = new Color(255, 255, 255);
	  Color borderFill;


	public JTextField getXKoordinata() {
		return xKoordinata;
	}



	public void setXKoordinata(JTextField xKoordinata) {
		this.xKoordinata = xKoordinata;
	}



	public JTextField getYKoordinata() {
		return yKoordinata;
	}



	public void setYKoordinata(JTextField yKoordinata) {
		this.yKoordinata = yKoordinata;
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



	public void setHeight(JTextField height) {
		this.height = height;
	}



	public boolean isConfirmation() {
		return confirmation;
	}



	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
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
		setTitle("Karakteristike pravougaonika");
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
			JLabel lblXKoordinata = new JLabel("X koordinata je:");
			GridBagConstraints gbc_lblXKoordinata = new GridBagConstraints();
			gbc_lblXKoordinata.anchor = GridBagConstraints.EAST;
			gbc_lblXKoordinata.insets = new Insets(0, 0, 5, 5);
			gbc_lblXKoordinata.gridx = 0;
			gbc_lblXKoordinata.gridy = 1;
			contentPanel.add(lblXKoordinata, gbc_lblXKoordinata);
		}
		{
			xKoordinata = new JTextField();
			//xKoordinata.setText(Integer.toString(x_koordinata));
			GridBagConstraints gbc_txtXKoordinata = new GridBagConstraints();
			gbc_txtXKoordinata.insets = new Insets(0, 0, 5, 0);
			gbc_txtXKoordinata.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXKoordinata.gridx = 1;
			gbc_txtXKoordinata.gridy = 1;
			contentPanel.add(xKoordinata, gbc_txtXKoordinata);
			xKoordinata.setColumns(10);
		}
		{
			JLabel lblYKoordinata = new JLabel("Y koordinata je:");
			GridBagConstraints gbc_lblYKoordinata = new GridBagConstraints();
			gbc_lblYKoordinata.anchor = GridBagConstraints.EAST;
			gbc_lblYKoordinata.insets = new Insets(0, 0, 5, 5);
			gbc_lblYKoordinata.gridx = 0;
			gbc_lblYKoordinata.gridy = 2;
			contentPanel.add(lblYKoordinata, gbc_lblYKoordinata);
		}
		{
			yKoordinata = new JTextField();
			//yKoordinata.setText(Integer.toString(y_koordinata));
			GridBagConstraints gbc_txtYKoordinata = new GridBagConstraints();
			gbc_txtYKoordinata.insets = new Insets(0, 0, 5, 0);
			gbc_txtYKoordinata.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYKoordinata.gridx = 1;
			gbc_txtYKoordinata.gridy = 2;
			contentPanel.add(yKoordinata, gbc_txtYKoordinata);
			yKoordinata.setColumns(10);
		}
		{
			JLabel lblSirina = new JLabel("Sirina je:");
			GridBagConstraints gbc_lblSirina = new GridBagConstraints();
			gbc_lblSirina.insets = new Insets(0, 0, 0, 5);
			gbc_lblSirina.anchor = GridBagConstraints.EAST;
			gbc_lblSirina.gridx = 0;
			gbc_lblSirina.gridy = 3;
			contentPanel.add(lblSirina, gbc_lblSirina);
		}
		{
			width = new JTextField();
			//width.setText(Integer.toString(sir));
			GridBagConstraints gbc_txtSirina = new GridBagConstraints();
			gbc_txtSirina.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSirina.gridx = 1;
			gbc_txtSirina.gridy = 3;
			contentPanel.add(width, gbc_txtSirina);
			width.setColumns(10);
		}
		{
			JLabel lblVisina = new JLabel("Visina je:");
			GridBagConstraints gbc_lblVisina = new GridBagConstraints();
			gbc_lblVisina.insets = new Insets(0, 0, 0, 5);
			gbc_lblVisina.anchor = GridBagConstraints.EAST;
			gbc_lblVisina.gridx = 0;
			gbc_lblVisina.gridy = 4;
			contentPanel.add(lblVisina, gbc_lblVisina);
		}
		{
			height = new JTextField();
			//height.setText(Integer.toString(vis));
			GridBagConstraints gbc_txtVisina = new GridBagConstraints();
			gbc_txtVisina.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtVisina.gridx = 1;
			gbc_txtVisina.gridy = 4;
			contentPanel.add(height, gbc_txtVisina);
			height.setColumns(10);
		}
		
		

//		  inner/border color
		{
		  JButton innerColorButton = new JButton("Boja unutrasnjosti");
		  innerColorButton.setBackground(SystemColor.activeCaption);
		  innerColorButton.setForeground(new Color(0, 0, 0));
		  innerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		  innerColorButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e)
			  {
				  innerFill = JColorChooser.showDialog(null,"Izaberi boju unutrasnjosti tvog oblika", innerFill);
				  innerColorButton.setBackground(innerFill);
				  boolean innerColorConfirmation = true;
				 
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
		  JButton borderColorButton = new JButton(" Boja ivice");
		  borderColorButton.setBackground(SystemColor.activeCaption);
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
		  gbc_borderColorButton.gridy = 7;
		  contentPanel.add(borderColorButton, gbc_borderColorButton);
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
