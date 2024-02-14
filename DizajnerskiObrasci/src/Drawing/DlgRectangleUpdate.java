package Drawing;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DlgRectangleUpdate extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtUpperLeftPointX;
	private JTextField txtUpperLeftPointY;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private boolean confirmation;
	private Color outerColor;
	private Color innerColor;
	
	private JButton outerColorButton;
	private JButton innerColorButton;

	
	private final JButton btnCancel = new JButton("Cancel");

	
	public DlgRectangleUpdate () {
		setModal(true);
		setTitle("Update Rectangle");
		setBounds(100, 100, 450, 250);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 117, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		{
			JLabel lblUpperLeftPointX = new JLabel("Upper Left Point X :");
			lblUpperLeftPointX.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
			GridBagConstraints gbc_lblUpperLeftPointX = new GridBagConstraints();
			gbc_lblUpperLeftPointX.insets = new Insets(0, 0, 5, 5);
			gbc_lblUpperLeftPointX.gridx = 1;
			gbc_lblUpperLeftPointX.gridy = 1;
			panel.add(lblUpperLeftPointX, gbc_lblUpperLeftPointX);
			
			txtUpperLeftPointX = new JTextField();
			GridBagConstraints gbc_txtUpperLeftPointX = new GridBagConstraints();
			gbc_txtUpperLeftPointX.insets = new Insets(0, 0, 5, 5);
			gbc_txtUpperLeftPointX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtUpperLeftPointX.gridx = 3;
			gbc_txtUpperLeftPointX.gridy = 1;
			panel.add(txtUpperLeftPointX, gbc_txtUpperLeftPointX);
			txtUpperLeftPointX.setColumns(10);
		}
		{
			outerColorButton = new JButton("Outer Color");
			outerColorButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					outerColor = JColorChooser.showDialog(outerColorButton, "Choose your color", Color.BLACK);
					outerColorButton.setBackground(outerColor);
				}
			});
			outerColorButton.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
			GridBagConstraints gbc_outerColorButton = new GridBagConstraints();
			gbc_outerColorButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_outerColorButton.anchor = GridBagConstraints.NORTH;
			gbc_outerColorButton.insets = new Insets(0, 0, 5, 0);
			gbc_outerColorButton.gridx = 5;
			gbc_outerColorButton.gridy = 1;
			panel.add(outerColorButton, gbc_outerColorButton);
		}
		{
			JLabel lblUpperLeftPointY = new JLabel("Upper Left Point Y :");
			lblUpperLeftPointY.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
			GridBagConstraints gbc_lblUpperLeftPointY = new GridBagConstraints();
			gbc_lblUpperLeftPointY.insets = new Insets(0, 0, 5, 5);
			gbc_lblUpperLeftPointY.gridx = 1;
			gbc_lblUpperLeftPointY.gridy = 3;
			panel.add(lblUpperLeftPointY, gbc_lblUpperLeftPointY);
			
			txtUpperLeftPointY = new JTextField();
			GridBagConstraints gbc_txtUpperLeftPointY = new GridBagConstraints();
			gbc_txtUpperLeftPointY.insets = new Insets(0, 0, 5, 5);
			gbc_txtUpperLeftPointY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtUpperLeftPointY.gridx = 3;
			gbc_txtUpperLeftPointY.gridy = 3;
			panel.add(txtUpperLeftPointY, gbc_txtUpperLeftPointY);
			txtUpperLeftPointY.setColumns(10);
		}
		{
			innerColorButton = new JButton("Inner Color");
			innerColorButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerColor = JColorChooser.showDialog(innerColorButton, "Choose your colour", Color.WHITE);
					innerColorButton.setBackground(innerColor);
				}
			});
			innerColorButton.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
			GridBagConstraints gbc_innerColorButton = new GridBagConstraints();
			gbc_innerColorButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_innerColorButton.insets = new Insets(0, 0, 5, 0);
			gbc_innerColorButton.gridx = 5;
			gbc_innerColorButton.gridy = 3;
			panel.add(innerColorButton, gbc_innerColorButton);
		}
		{
			JLabel lblWidth = new JLabel("Width :");
			lblWidth.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 1;
			gbc_lblWidth.gridy = 5;
			panel.add(lblWidth, gbc_lblWidth);
			
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.insets = new Insets(0, 0, 5, 5);
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 3;
			gbc_txtWidth.gridy = 5;
			panel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JButton btnOK = new JButton("Ok");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setConfirmation(true);
					setVisible(false);
				}
			});
			btnOK.setFont(new Font("Yu Gothic Medium", Font.BOLD, 12));
			GridBagConstraints gbc_btnOK = new GridBagConstraints();
			gbc_btnOK.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnOK.insets = new Insets(0, 0, 5, 0);
			gbc_btnOK.gridx = 5;
			gbc_btnOK.gridy = 6;
			panel.add(btnOK, gbc_btnOK);
		}
		{
			JLabel lblHeight = new JLabel("Height :");
			lblHeight.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 14));
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.insets = new Insets(0, 0, 0, 5);
			gbc_lblHeight.gridx = 1;
			gbc_lblHeight.gridy = 7;
			panel.add(lblHeight, gbc_lblHeight);
			
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.insets = new Insets(0, 0, 0, 5);
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 3;
			gbc_txtHeight.gridy = 7;
			panel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 5;
		gbc_btnCancel.gridy = 7;
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 12));
		btnCancel.setHorizontalAlignment(SwingConstants.CENTER); //POZICIONIRANJE KOMPONENTI na ekranu
		panel.add(btnCancel, gbc_btnCancel);
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
	
	public Color getOutlineColor() {
		return outerColor;
	}
	
	public void setOutlineColor(Color outlineColor) {
		this.outerColor = outlineColor;
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
