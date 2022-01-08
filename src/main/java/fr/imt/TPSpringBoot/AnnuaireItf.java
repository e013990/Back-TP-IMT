package fr.imt.TPSpringBoot;
import java.util.Collection;

public interface AnnuaireItf {
	public Collection<Person> getAll();
	public Person getfromId(int id);
	public Collection<Person> findByName(String name);
	public void delPerson(int id);
	public void addPerson(Person p);
}
