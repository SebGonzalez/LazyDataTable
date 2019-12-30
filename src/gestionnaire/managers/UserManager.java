package gestionnaire.managers;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gestionnaire.entities.Activity;
import gestionnaire.entities.Person;

@Stateful
public class UserManager implements IUserManager {

	@PersistenceContext(unitName = "myData2")
	EntityManager em;
	
	private boolean connected = false;
		
	@Override
	public boolean connect() {
		return true;
	}

	@Override
	public boolean isConnected() {
		return connected;
	}

	@Override
	public void savePerson(Person p) {
		em.persist(p);
	}

	@Override
	public void saveActivity(Activity a) {
		em.persist(a);
	}

}
