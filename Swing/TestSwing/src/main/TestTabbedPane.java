package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class TestTabbedPane {

	String requiredLabel = "必須";
	String optionalLabel = "任意";

	public TestTabbedPane() {
		// nothing to do
	}

	public void createTabbedPane(JFrame frame) {
		JTabbedPane tabbedpane = new JTabbedPane();

		/** タブの位置を設定 */
		tabbedpane.setTabPlacement(JTabbedPane.LEFT);
		/* タブが増えた場合は多段化 */
		tabbedpane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);

		JPanel tabPanel1 = new JPanel();
		tabPanel1.setPreferredSize(new Dimension(400, 400));
		tabbedpane.addTab(requiredLabel, tabPanel1);
		JPanel tabPanel2 = new JPanel();
		tabbedpane.addTab(optionalLabel, tabPanel2);

		createTab1(frame, tabPanel1);
		frame.add(tabbedpane);
	}

	public void createTab1(JFrame frame, JPanel tabPanel1) {
		JLabel label = new JLabel("TestText : ");
		JTextField field = new JTextField();
		field.setPreferredSize(new Dimension(100, 15));

		GridBagLayout layout = new GridBagLayout();
		tabPanel1.setLayout(layout);

		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.fill = GridBagConstraints.BOTH;
		gbc1.gridx = 0;
		gbc1.gridx = 0;
		layout.setConstraints(label, gbc1);

		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.fill = GridBagConstraints.BOTH;
		gbc2.gridx = 2;
		gbc2.gridx = 2;
		layout.setConstraints(field, gbc2);

		tabPanel1.add(label);
		tabPanel1.add(field);

	}
}
