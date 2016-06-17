package org.eclipse.sirius.sample.basicfamily.design.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.sample.basicfamily.Person;




public class FamilyServices {
	
	public int getCousinsNumber(Person person) {
		
		List<Person> cousins=new ArrayList<Person>();
		List<Person> parents=person.getParents();

		for (Person parent: parents) {
			for (Person grandParent: parent.getParents()) {
				for (Person uncleOrAunt: grandParent.getChildren()) {
					if (!parents.contains(uncleOrAunt)) {
						for (Person cousin:uncleOrAunt.getChildren()) {
							if (!cousins.contains(cousin))
								cousins.add(cousin);
						}
					}
				}
			}
		}
		return cousins.size();	

	}

}
