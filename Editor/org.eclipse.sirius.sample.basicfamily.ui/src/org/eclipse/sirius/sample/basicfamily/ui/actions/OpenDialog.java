package org.eclipse.sirius.sample.basicfamily.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeSpec;
import org.eclipse.sirius.sample.basicfamily.Person;
import org.eclipse.sirius.sample.basicfamily.ui.dialog.SampleDialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class OpenDialog implements IExternalJavaAction {

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		EObject semanticObject = getSemanticObject(arg0);
		if (semanticObject == null) {
			return false;
		}
		return true;
	}

	@Override
	public void execute(Collection<? extends EObject> arg0, Map<String, Object> arg1) {
		EObject semanticObject = getSemanticObject(arg0);
		if (semanticObject == null) {
			return;
		}
		
		if (!Person.class.isAssignableFrom(semanticObject.getClass())) {
			return;
		}
		
		Person person = (Person) semanticObject;
		
		final Shell shell = Display.getCurrent().getActiveShell();
		SampleDialog dialog = new SampleDialog(shell, person);
		dialog.open();
	}

	private EObject getSemanticObject(Collection<? extends EObject> arguments) {
		List<EObject> semanticObjectList = getSemanticObjectList(arguments);
		if (semanticObjectList.size() != 1) {
			return null;
		}
		return semanticObjectList.get(0);
	}

	private List<EObject> getSemanticObjectList(Collection<? extends EObject> arguments) {
		List<EObject> resultList = new ArrayList<EObject>();
		for (EObject argument : arguments) {
			if (argument instanceof DNodeSpec) {
				DNodeSpec spec = (DNodeSpec)argument;
				EList<EObject> semanticElements = spec.getSemanticElements();
				
				for (EObject element : semanticElements) {
					resultList.add(element);
				}
			}
		}
		return resultList;
	}
}
