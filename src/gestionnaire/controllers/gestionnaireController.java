package gestionnaire.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;

import gestionnaire.entities.Activity;
import gestionnaire.entities.ActivityType;
import gestionnaire.entities.CV;
import gestionnaire.entities.Person;
import gestionnaire.managers.ICvManager;
import gestionnaire.managers.IUserManager;

@ManagedBean(name = "gestionnaire")
@SessionScoped
public class gestionnaireController implements IUserController {

	private static final int NB_PERSON = 300;
	@EJB
	IUserManager um;

	@EJB
	ICvManager cm;
	
	Person p;
	
	private LazyDataModel<Person> lazyModel;

	@PostConstruct
	public void initBd() {
		if (cm.getAllPersons().size() == 0) {
			int leftLimit = 97; // letter 'a'
			int rightLimit = 122; // letter 'z'
			int targetStringLength = 10;
			Random random = new Random();

			Calendar c1 = GregorianCalendar.getInstance();
			c1.set(1997, Calendar.FEBRUARY, 25);

			for (int i = 0; i < NB_PERSON; i++) {
				String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
						.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
				Person p = new Person(generatedString, generatedString, generatedString + "@hotmail.fr",
						generatedString + ".fr", "azerty");
				p.setBirthDay(c1.getTime());
				um.savePerson(p);

				for (int y = 0; y < 10; y++) {
					String generatedString2 = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
							.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
							.toString();
					Activity activity = new Activity(random.nextInt(2020 - 1950) + 1950, ActivityType.FORMATION,
							generatedString2, generatedString2, generatedString2, p);
					um.saveActivity(activity);
				}

			}
		}
		
		lazyModel = new LazyPersonDataModel(this.getAllPersons());
	}

    public LazyDataModel<Person> getLazyModel() {
        return lazyModel;
    }
    
	@Override
	public List<Person> getAllPersons() {
		return cm.getAllPersons();
	}
    
	@Override
	public boolean connect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConnected() {
		return um.isConnected();
	}

	@Override
	public void savePerson(Person p) {
		um.savePerson(p);
	}

	@Override
	public void saveActivity(Activity a) {
		um.saveActivity(a);

	}

	@Override
	public Person getPersonById(long idPerson) {
		return cm.getPersonById(idPerson);
	}

	@Override
	public List<Activity> getAllActivities() {
		return cm.getAllActivities();
	}

	@Override
	public CV getActivitiesPerson(long idPerson) {
		return cm.getActivitiesPerson(idPerson);
	}

	@Override
	public Activity getActivityById(long idActivity) {
		return cm.getActivityById(idActivity);
	}
	
	public String showPerson(long idPerson) {
		System.out.println("Personne : " + idPerson);
		p = getPersonById(idPerson);
		return "showPerson";
	}
	
	public Person getPersonne() {
		return p;
	}

	

}
