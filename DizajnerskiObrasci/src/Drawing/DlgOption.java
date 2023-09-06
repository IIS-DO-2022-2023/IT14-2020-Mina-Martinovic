package Drawing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DlgOption extends JDialog{

		private final JPanel contentPanel = new JPanel();
		public boolean confirmation;
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			try {
				DlgOption dialog = new DlgOption();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * Create the dialog.
		 */
		public DlgOption() {
			setModal(true);
			setBounds(100, 100, 450, 300);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblDeleteShape = new JLabel("Delete shape");
				lblDeleteShape.setHorizontalAlignment(SwingConstants.CENTER);
				lblDeleteShape.setFont(new Font("Courier New", Font.BOLD, 20));
				contentPanel.add(lblDeleteShape, BorderLayout.NORTH);
			}
			{
				JLabel lblChoice = new JLabel("Are you sure?");
				lblChoice.setFont(new Font("Courier New", Font.BOLD, 15));
				lblChoice.setHorizontalAlignment(SwingConstants.CENTER);
				contentPanel.add(lblChoice, BorderLayout.CENTER);
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							confirmation = true;
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
							dispose();
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
		}
	}

