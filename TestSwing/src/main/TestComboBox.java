package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class TestComboBox {

	public TestComboBox() {
		// TODO Auto-generated constructor stub
	}
	
	public void createComboBox(JFrame frame){
		JCheckBox a = new JCheckBox("A");
		JCheckBox b = new JCheckBox("B");
		JCheckBox c = new JCheckBox("C");
		JCheckBox d = new JCheckBox("D");
		JCheckBox e = new JCheckBox("E");

		Box box = Box.createVerticalBox();

		box.add(a);
		box.add(b);
		box.add(c);
		box.add(d);
		box.add(e);

		JScrollPane jscrlpBox = new JScrollPane(box);
		jscrlpBox.setPreferredSize(new Dimension(140, 90));
		LineBorder inborder1 = new LineBorder(Color.gray, 1); // LineBorderのインスタンス
		TitledBorder border1 = new TitledBorder(inborder1, "Title",
				TitledBorder.LEFT, TitledBorder.TOP); // TitleBorderのインスタンス
		jscrlpBox.setBorder(border1);
		frame.add(jscrlpBox);
	}
}
