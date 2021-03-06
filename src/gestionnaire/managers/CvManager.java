package gestionnaire.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionnaire.entities.Activity;
import gestionnaire.entities.CV;
import gestionnaire.entities.Person;

@Stateless
public class CvManager implements ICvManager {

	@PersistenceContext(unitName = "myData2")
	EntityManager em;

	@Override
	public List<Person> getAllPersons() {
		return em.createQuery("Select p From Person p", Person.class).getResultList();
	}

	@Override
	public Person getPersonById(long idPerson) {
		Person p = em.createQuery("Select p From Person p Where id = :idPerson", Person.class)
				.setParameter("idPerson", idPerson).getSingleResult();
		p.setCv(getActivitiesPerson(p));
		return p;
	}

	@Override
	public List<Activity> getAllActivities() {
		return em.createQuery("Select a From Activity a", Activity.class).getResultList();
	}

	@Override
	//non fonctionnel
	public CV getActivitiesPerson(long idPerson) {
		Query query = em.createQuery("Select a From Activity a Where owner = :idPerson", Activity.class)
				.setParameter("idPerson", idPerson);
		CV cv = new CV(query.getResultList());
		return cv;
	}
	
	@Override
	public CV getActivitiesPerson(Person p) {
		Query query = em.createQuery("Select a From Activity a Where owner = :p", Activity.class)
				.setParameter("p", p);
		CV cv = new CV(query.getResultList());
		return cv;
	}

	@Override
	public Activity getActivityById(long idActivity) {
		return em.createQuery("Select a From Activity a Where id = :idActivity", Activity.class)
				.setParameter("idActivity", idActivity).getSingleResult();
	}

}
