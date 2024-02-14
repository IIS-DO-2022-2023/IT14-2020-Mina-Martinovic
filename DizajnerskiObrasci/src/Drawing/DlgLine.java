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
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DlgLine extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField XStartPoint;
	private JTextField YStartPoint;
	private JTextField XEndPoint;
	private JTextField YEndPoint;

	private boolean confirmation;
	private Color outerColor;
	
	private JButton outerColorButton;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine(); //pokrece mi dijalog sam kad kao runujem samo klasu dlgLine npr
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setBounds(100, 100, 450, 300);
		setTitle("Line:");
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
			JLabel lblXStart = new JLabel("X coord:");
			GridBagConstraints gbc_lblXStart = new GridBagConstraints();
			gbc_lblXStart.anchor = GridBagConstraints.EAST;
			gbc_lblXStart.insets = new Insets(0, 0, 5, 5);
			gbc_lblXStart.gridx = 0;
			gbc_lblXStart.gridy = 1;
			contentPanel.add(lblXStart, gbc_lblXStart);
		}
		{
			XStartPoint = new JTextField();
			GridBagConstraints gbc_txtXStart = new GridBagConstraints();
			gbc_txtXStart.insets = new Insets(0, 0, 5, 0);
			gbc_txtXStart.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXStart.gridx = 1;
			gbc_txtXStart.gridy = 1;
			contentPanel.add(XStartPoint, gbc_txtXStart);
			XStartPoint.setColumns(10);
		}
		{
			JLabel lblYStart = new JLabel("Y coord:");
			GridBagConstraints gbc_lblYStart = new GridBagConstraints();
			gbc_lblYStart.anchor = GridBagConstraints.EAST;
			gbc_lblYStart.insets = new Insets(0, 0, 5, 5);
			gbc_lblYStart.gridx = 0;
			gbc_lblYStart.gridy = 2;
			contentPanel.add(lblYStart, gbc_lblYStart);
		}
		{
			YStartPoint = new JTextField();
			GridBagConstraints gbc_lblYStart = new GridBagConstraints();
			gbc_lblYStart.insets = new Insets(0, 0, 5, 0);
			gbc_lblYStart.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblYStart.gridx = 1;
			gbc_lblYStart.gridy = 2;
			contentPanel.add(YStartPoint, gbc_lblYStart);
			YStartPoint.setColumns(10);
		}		
		{
			JLabel lblXEnd = new JLabel("X coord:");
			GridBagConstraints gbc_lblXEnd = new GridBagConstraints();
			gbc_lblXEnd.anchor = GridBagConstraints.EAST;
			gbc_lblXEnd.insets = new Insets(0, 0, 5, 5);
			gbc_lblXEnd.gridx = 0;
			gbc_lblXEnd.gridy = 1;
			contentPanel.add(lblXEnd, gbc_lblXEnd);
		}
		{
			XEndPoint = new JTextField();
			GridBagConstraints gbc_txtXEnd = new GridBagConstraints();
			gbc_txtXEnd.insets = new Insets(0, 0, 5, 0);
			gbc_txtXEnd.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXEnd.gridx = 1;
			gbc_txtXEnd.gridy = 1;
			contentPanel.add(XEndPoint, gbc_txtXEnd);
			XEndPoint.setColumns(10);
		}
		{
			JLabel lblYEnd = new JLabel("Y coord:");
			GridBagConstraints gbc_lblYEnd = new GridBagConstraints();
			gbc_lblYEnd.anchor = GridBagConstraints.EAST;
			gbc_lblYEnd.insets = new Insets(0, 0, 5, 5);
			gbc_lblYEnd.gridx = 0;
			gbc_lblYEnd.gridy = 2;
			contentPanel.add(lblYEnd, gbc_lblYEnd);
		}
		{
			YEndPoint = new JTextField();
			GridBagConstraints gbc_lblYEnd = new GridBagConstraints();
			gbc_lblYEnd.insets = new Insets(0, 0, 5, 0);
			gbc_lblYEnd.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblYEnd.gridx = 1;
			gbc_lblYEnd.gridy = 2;
			contentPanel.add(YEndPoint, gbc_lblYEnd);
			YEndPoint.setColumns(10);
		}
		
		  {
		  outerColorButton = new JButton(" Outer color");
		  outerColorButton.setForeground(new Color(0, 0, 0));
		  outerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		  outerColorButton.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e)
		  {
			  outerColor = JColorChooser.showDialog(null,"Choose outer color for your line", outerColor);
			  outerColorButton.setBackground(outerColor);
		  }
		  });
		  outerColorButton.setHorizontalAlignment(SwingConstants.LEFT);
		  GridBagConstraints gbc_borderColorButton = new GridBagConstraints();
		  gbc_borderColorButton.gridx = 1;
		  gbc_borderColorButton.gridy = 6;
		  contentPanel.add(outerColorButton, gbc_borderColorButton);
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
	

	public JTextField getXStartPoint() {
		return XStartPoint;
	}

	public void setXStartPoint(JTextField xStartPoint) {
		XStartPoint = xStartPoint;
	}

	public JTextField getYStartPoint() {
		return YStartPoint;
	}

	public void setYStartPoint(JTextField yStartPoint) {
		YStartPoint = yStartPoint;
	}

	public JTextField getXEndPoint() {
		return XEndPoint;
	}

	public void setXEndPoint(JTextField xEndPoint) {
		XEndPoint = xEndPoint;
	}

	public JTextField getYEndPoint() {
		return YEndPoint;
	}

	public void setYEndPoint(JTextField yEndPoint) {
		YEndPoint = yEndPoint;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public Color getColor() {
		return outerColor;
	}

	public void setColor(Color color) {
		this.outerColor = color;
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
