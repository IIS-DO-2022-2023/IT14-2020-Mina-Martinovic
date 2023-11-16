package Drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
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

public class DlgPoint extends JDialog {
	private final JPanel contentPanel = new JPanel();
	protected JTextField txtX;
	protected JTextField txtY;
	
	private JButton outerColorButton;
	

	private boolean confirmation;
	private Color color = Color.BLACK;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint() {
		setBounds(100, 100, 450, 300);
		setTitle("Karakteristike tacke");
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
			txtX = new JTextField();
			GridBagConstraints gbc_txtXKoordinata = new GridBagConstraints();
			gbc_txtXKoordinata.insets = new Insets(0, 0, 5, 0);
			gbc_txtXKoordinata.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXKoordinata.gridx = 1;
			gbc_txtXKoordinata.gridy = 1;
			contentPanel.add(txtX, gbc_txtXKoordinata);
			txtX.setColumns(10);
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
			txtY = new JTextField();
			GridBagConstraints gbc_txtYKoordinata = new GridBagConstraints();
			gbc_txtYKoordinata.insets = new Insets(0, 0, 5, 0);
			gbc_txtYKoordinata.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYKoordinata.gridx = 1;
			gbc_txtYKoordinata.gridy = 2;
			contentPanel.add(txtY, gbc_txtYKoordinata);
			txtY.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						confirmation=true;
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
	
	public DlgPoint(int x, int y) {
		setBounds(100, 100, 450, 300);
		setTitle("Karakteristike tacke");
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
			txtX = new JTextField();
			this.txtX.setText(Integer.toString(x)); 
			GridBagConstraints gbc_txtXKoordinata = new GridBagConstraints();
			gbc_txtXKoordinata.insets = new Insets(0, 0, 5, 0);
			gbc_txtXKoordinata.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXKoordinata.gridx = 1;
			gbc_txtXKoordinata.gridy = 1;
			contentPanel.add(txtX, gbc_txtXKoordinata);
			txtX.setColumns(10);
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
			txtY = new JTextField();
			this.txtY.setText(Integer.toString(y));
			GridBagConstraints gbc_txtYKoordinata = new GridBagConstraints();
			gbc_txtYKoordinata.insets = new Insets(0, 0, 5, 0);
			gbc_txtYKoordinata.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYKoordinata.gridx = 1;
			gbc_txtYKoordinata.gridy = 2;
			contentPanel.add(txtY, gbc_txtYKoordinata);
			txtY.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						confirmation=true;
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

}

