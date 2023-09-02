package Drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry1.Donut;
import geometry1.Point;
import geometry1.Rectangle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField xKoordinata;
	protected JTextField yKoordinata;
	protected JTextField innerRadius;
	protected JTextField outerRadius;
	protected JTextField height;
	protected boolean isOk;
	
	  private boolean confirmation;
	  private boolean innerColorConfirmation;
	  private boolean ColorConfirmation;
	  Color innerFill = new Color(255, 255, 255);
	  Color borderFill;
	 

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




	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
		setBounds(100, 100, 450, 300);
		setTitle("Karakteristike kruga sa rupom");
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
			JLabel lblInnerRadius = new JLabel("Unutrasnji poluprecnik je:");
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.insets = new Insets(0, 0, 0, 5);
			gbc_lblInnerRadius.anchor = GridBagConstraints.EAST;
			gbc_lblInnerRadius.gridx = 0;
			gbc_lblInnerRadius.gridy = 3;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			innerRadius = new JTextField();
			//innerRadius.setText(Integer.toString(unutrasnjiPol));
			GridBagConstraints gbc_txtInnerRadius = new GridBagConstraints();
			gbc_txtInnerRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtInnerRadius.gridx = 1;
			gbc_txtInnerRadius.gridy = 3;
			contentPanel.add(innerRadius, gbc_txtInnerRadius);
			innerRadius.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Spoljasnji poluprecnik je:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 0, 5);
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 4;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			outerRadius = new JTextField();
			//outerRadius.setText(Integer.toString(pol));
			GridBagConstraints gbc_txtOuterRadius = new GridBagConstraints();
			gbc_txtOuterRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtOuterRadius.gridx = 1;
			gbc_txtOuterRadius.gridy = 4;
			contentPanel.add(outerRadius, gbc_txtOuterRadius);
			outerRadius.setColumns(10);
		}
		
		
		
//		  inner/border color
		  {JButton innerColorButton = new JButton("Boja unutrasnjosti");
		  innerColorButton.setBackground(SystemColor.activeCaption);
		  innerColorButton.setForeground(new Color(0, 0, 0));
		  innerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		  innerColorButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e)
		  {
		  innerFill = JColorChooser.showDialog(null,"Izaberi boju unutrasnjosti tvog oblika", innerFill);
		  innerColorButton.setBackground(innerFill);
		  innerColorConfirmation = true;
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
		  gbc_borderColorButton.gridy = 6;
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
					 * okButton.addActionListener(new ActionListener() {
					 * public void actionPerformed(Actionevent e) 
					 * {
					 * try
					 * {
					 * int newX = Integer.parseInt(xKoordinata.getText());
					 * int newY = Integer.parseInt(yKoordinata.getText());
					 * int newInnerRadius = Integer.parseInt(outerRadius.getText());
					 * int newInnerRadius = Integer.parseInt(innerRadius.getText());
					 * 
					 * if(newX < 0 || newY < 0 || newInnerRadius < 1 || newInnerRadius < 1)
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
					
					public void actionPerformed(ActionEvent e) {
						boolean isModifying = false;
						//provera da li se radi modifikacija
						for (int i=PanelDrawing.shapes.size()-1; i>=0; i--) {
							if(PanelDrawing.shapes.get(i).isSelected()) {
								Donut d1 = new Donut(
										new Point(Integer.parseInt(xKoordinata.getText()), Integer.parseInt(yKoordinata.getText())),
										Integer.parseInt(outerRadius.getText()),
										Integer.parseInt(innerRadius.getText())
										);
								d1.setColor(innerFill);
								d1.setEdgeColor(borderFill);
								PanelDrawing.shapes.set(i, d1); // zameni tekuci element sa izmenjenim
								isModifying = true;
								break;
							}
						}
						//radimo dodavanje objekata
						if(isModifying == false) {
							Donut d1 = new Donut(
									new Point(Integer.parseInt(xKoordinata.getText()), Integer.parseInt(yKoordinata.getText())),
									Integer.parseInt(outerRadius.getText()),
									Integer.parseInt(innerRadius.getText())
									);
							d1.setColor(innerFill);
							d1.setEdgeColor(borderFill);
							PanelDrawing.shapes.add(d1);
						isOk=true;
						setVisible(false);
					}
				
					}});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isOk=false;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


	

}
