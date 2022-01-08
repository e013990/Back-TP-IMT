package fr.imt.TPSpringBoot;

//import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnuaireService implements AnnuaireItf {
	/*Map<Integer,Person> annuservice = new HashMap<Integer,Person>();*/
	
	@Autowired
	PersonRepository pr;
	
	public AnnuaireService() {
		/*super();
	    pr.save(null)
		Person p1 =new Person(1,"Titi","David","06.19.79.27.69","dunkerque");
		Person p2 =new Person(2,"Toto","Victor","06.19.79.27.70","dunkerque");
		Person p3 =new Person(3,"Tata","Louise","06.19.79.27.71","dunkerque");
		annuservice.put(1, p1);
		annuservice.put(2, p2);
		annuservice.put(3, p3);
        */
	}

	@Override
	public Collection<Person> getAll() {
		// TODO Auto-generated method stub
		//return (Collection<Person>) annuservice.values();
		return pr.findAll();
	}

	@Override
	public Person getfromId(int id) {
		// TODO Auto-generated method stub
		//return annuservice.get(id);
		return pr.findById(id);
	}

	@Override
	public Collection<Person> findByName(String name) {
		// TODO Auto-generated method stub
      //List<Person> result = new ArrayList<>();
     //for (Person entitee : annuservice.values()) {
      //    if (entitee.getName().matches(name + ".*")) {
      //      result.add(entitee);
      //  }
     //}
    return pr.findByName(name);
		
	}

	@Override
	public void delPerson(int id) {
		// TODO Auto-generated method stub
		//annuservice.remove(id);
		pr.deleteById(id);
	}

	@Override
	public void addPerson(Person p) {
		// TODO Auto-generated method stub
		//annuservice.put(id,p);
		pr.save(p);
		
	}
}
