package gestionnaire.managers;

import gestionnaire.entities.Activity;
import gestionnaire.entities.Person;

public interface IUserManager {
	public boolean connect();
	public boolean isConnected();
	public void savePerson(Person p);
	public void saveActivity(Activity a);
}
