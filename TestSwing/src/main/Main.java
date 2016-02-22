package main;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Main extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/** JComboBox,JScrollPane */
		// TestComboBox comboCreater = new TestComboBox();
		// comboCreater.createComboBox(frame);

		/** JTabbedPane */
		TestTabbedPane tabbedPaneCreater = new TestTabbedPane();
		tabbedPaneCreater.createTabbedPane(frame);

		frame.setVisible(true);
	}
}
