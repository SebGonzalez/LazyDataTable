package gestionnaire.controllers;

import java.util.List;

import gestionnaire.entities.Activity;
import gestionnaire.entities.CV;
import gestionnaire.entities.Person;

public interface IUserController {	
	public boolean connect();
	public boolean isConnected();
	public void savePerson(Person p);
	public void saveActivity(Activity a);
	
	public List<Person> getAllPersons();
	public Person getPersonById(long idPerson);
	public List<Activity> getAllActivities();
	public CV getActivitiesPerson(long idPerson);
	public Activity getActivityById(long idActivity);
}
