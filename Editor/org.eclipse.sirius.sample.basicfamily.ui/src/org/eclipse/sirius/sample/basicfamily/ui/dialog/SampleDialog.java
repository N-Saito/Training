package org.eclipse.sirius.sample.basicfamily.ui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.sample.basicfamily.Person;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SampleDialog extends Dialog {

	Person person;

	Text text;

	String textContent;

	String initialText;

	String titleText;

	public String getText() {
		return textContent;
	}

	public void setInitialText(String text) {
		initialText = text;
	}

	public void setTitleText(String text) {
		titleText = text;
	}

	public String getTitleText() {
		if ((titleText == null) || (titleText.equals(""))) {
			return "Title text";
		}
		return titleText;
	}

	public boolean isTextModified() {
		if (initialText == null) {
			return (textContent != null) && (!textContent.equals(""));
		} else {
			return !initialText.equals(textContent);
		}
	}

	public SampleDialog(Shell parentShell, Person person) {
		super(parentShell);

		this.person = person;

		setTitleText("ダイアログのタイトル");
		setInitialText(person.getName());
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 300);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(getTitleText());
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		
		Label label = new Label(composite, SWT.V_SCROLL | SWT.WRAP);
		label.setText("name:");
		
		text = new Text(composite, SWT.H_SCROLL | SWT.BORDER | SWT.WRAP);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
		if (initialText != null) {
			text.setText(initialText);
		}
		return super.createDialogArea(parent);
	}

	@Override
	protected void okPressed() {
		textContent = text.getText();

		if (isTextModified()) {
			person.setName(getText());
		}

		super.okPressed();
	}

}
