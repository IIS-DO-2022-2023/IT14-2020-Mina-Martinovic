package Drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry1.Circle1;
import geometry1.Point1;
import geometry1.Rectangle1;

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
	protected JTextField xKoordinata;
	protected JTextField yKoordinata;
	protected JTextField radius;
	protected JTextField visina;
	protected boolean isOk;

	private boolean confirmation;
	private boolean innerColorConfirmation;
	private boolean ColorConfirmation;
	Color innerFill = new Color(255, 255, 255);
	Color borderFill;

	/**
	* Create the dialog.
	*/
	public DlgCircle(int x_koordinata, int y_koordinata, int poluprecnik) {
		setBounds(100, 100, 450, 300);
		setTitle("Karakteristike kruga");
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 45, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
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
			xKoordinata.setText(Integer.toString(x_koordinata));
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
			yKoordinata.setText(Integer.toString(y_koordinata));
			GridBagConstraints gbc_txtYKoordinata = new GridBagConstraints();
			gbc_txtYKoordinata.insets = new Insets(0, 0, 5, 0);
			gbc_txtYKoordinata.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYKoordinata.gridx = 1;
			gbc_txtYKoordinata.gridy = 2;
			contentPanel.add(yKoordinata, gbc_txtYKoordinata);
			yKoordinata.setColumns(10);
		}

		{
			JLabel lblRadius = new JLabel("Poluprecnik je:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 0, 5);
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 3;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}

		{
			radius = new JTextField();
			radius.setText(Integer.toString(poluprecnik));
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius.gridx = 1;
			gbc_txtRadius.gridy = 3;
			contentPanel.add(radius, gbc_txtRadius);
			radius.setColumns(10);
		}

		{
			JButton borderColorButton = new JButton(" Boja ivice");
			borderColorButton.setBackground(SystemColor.activeCaption);
			borderColorButton.setForeground(new Color(0, 0, 0));
			borderColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
			borderColorButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borderFill = JColorChooser.showDialog(null, "Izaberi boju ivice tvog oblika", borderFill);
					borderColorButton.setBackground(borderFill);
					boolean borderColorConfirmation = true;
				}
			});
			borderColorButton.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_borderColorButton = new GridBagConstraints();
			gbc_borderColorButton.gridx = 0;
			gbc_borderColorButton.gridy = 6;
			contentPanel.add(borderColorButton, gbc_borderColorButton);
		}

		{

			JButton innerColorButton = new JButton("Boja unutrasnjosti");
			innerColorButton.setBackground(SystemColor.activeCaption);
			innerColorButton.setForeground(new Color(0, 0, 0));
			innerColorButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
			innerColorButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					innerFill = JColorChooser.showDialog(null, "Izaberi boju unutrasnjosti tvog oblika", innerFill);
					innerColorButton.setBackground(innerFill);
					innerColorConfirmation = true;
				}

			});

			GridBagConstraints gbc_innerColorButton = new GridBagConstraints();
			gbc_innerColorButton.insets = new Insets(0, 0, 0, 5);
			gbc_innerColorButton.gridx = 1;
			gbc_innerColorButton.gridy = 6;
			contentPanel.add(innerColorButton, gbc_innerColorButton);

		}

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean isModifying = false;
				// provera da li se radi modifikacija
				for (int i = PanelDrawing.shapes.size() - 1; i >= 0; i--) {
					if (PanelDrawing.shapes.get(i).isSelected()) {
						Circle1 c1 = new Circle1(new Point1(Integer.parseInt(xKoordinata.getText()),
								Integer.parseInt(yKoordinata.getText())), Integer.parseInt(radius.getText()));
						c1.setColor(innerFill);
						c1.setEdgeColor(borderFill);
						PanelDrawing.shapes.set(i, c1); // zameni tekuci element sa izmenjenim
						isModifying = true;
						break;
					}
				}
				// radimo dodavanje objekta
				if (isModifying == false) {
					Circle1 c1 = new Circle1(new Point1(Integer.parseInt(xKoordinata.getText()),
							Integer.parseInt(yKoordinata.getText())), Integer.parseInt(radius.getText()));
					c1.setColor(innerFill);
					c1.setEdgeColor(borderFill);
					PanelDrawing.shapes.add(c1);
				}

				isOk = true;
				setVisible(false);

			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isOk = false;
				setVisible(false);
			};
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

}}