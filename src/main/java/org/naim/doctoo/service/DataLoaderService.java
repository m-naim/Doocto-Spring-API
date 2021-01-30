package org.naim.doctoo.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.naim.doctoo.model.AuthProvider;
import org.naim.doctoo.model.Coordonnees;
import org.naim.doctoo.model.Doctor;
import org.naim.doctoo.model.Location;
import org.naim.doctoo.model.Profession;
import org.naim.doctoo.model.User;
import org.naim.doctoo.repository.ProfessionRepository;
import org.naim.doctoo.repository.UserRepository;
import org.naim.doctoo.repository.DocteurRepository;
import org.naim.doctoo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class DataLoaderService {
	
	 @Autowired
	    private PasswordEncoder passwordEncoder;

	private static List<String> professions = Arrays.asList(
			"Médecin généraliste"
	);
	
	private static List<String> dairas = Arrays.asList(
			"Adekar", "Akbou", "Amizour", "Aokas",
			"Barbacha", "Bejaia", "Beni Maouche", "Chemini", 
			"Darguina", "El Kseur","Ighil Ali","Kherrata","Ouzellaguen","Seddouk",
			"Sidi-Aïch","Souk El-Ténine","Tazmalt","Tichy","Timezrit"
	);
	
	
	private static List<String> doctorNames = Arrays.asList(
			"Adekar", "Akbou", "Amizour", "Aokas"
	);
	
	private Map<String,Profession> professionMap;
	private Map<String,Location> locationMap;
	private Set<Doctor> doctors;

	@Autowired
	private ProfessionRepository professionRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private DocteurRepository doctorRepository;
	@Autowired
	private UserRepository userRepository;

	public  void populateProfessions() {
			professionMap= new HashMap<>();
			for(String profession: professions) {
				Profession p = new Profession();
				p.setProfession(profession);
				professionMap.put(profession, p);	
				try {
					Profession result = professionRepository.save(p);
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	
	public  void populateLocations() {
		locationMap= new HashMap<>();
		for(String daira: dairas) {
			Location p = new Location();
			p.setDaira(daira);
			p.setWilaya("bejaia");
			locationMap.put(daira, p);			
			try {
				Location result = locationRepository.save(p);
			}
			catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public  void populateDoctors() {
		
			User user= new User();
			user.setName("Hocini Nabil");
			user.setEmail("Hocini@gmail.com");
			user.setPassword(passwordEncoder.encode("hhhh"));
			user.setProvider(AuthProvider.local);
			doctors= new HashSet<Doctor>();
		
			Doctor d = new Doctor();
			d.setNomProfessionel("Hocini Nabil");
			d.setProfession(professionMap.get("Médecin généraliste"));
			d.setLocation(locationMap.get("Sidi-Aïch"));
			d.setCoordonnees(new Coordonnees(4.679926712000816,36.622540534118365));
			d.setSchedule("9:00,12:00,13:00,18:00,0,0,0,0,0,0,0,0,0,0,0,0,9:00,12:00,13:00,18:00,0,0,0,0,0,0,0,0");
			user.setDoctor(d);
			doctors.add(d);
			
			d = new Doctor();
			d.setNomProfessionel("Alitouche");
			d.setProfession(professionMap.get("Médecin généraliste"));
			d.setLocation(locationMap.get("Akbou"));
			d.setCoordonnees(new Coordonnees(4.54079641572381,36.477554757758746));
			d.setTelephone("+21334356793");
			doctors.add(d);
			
			d = new Doctor();
			d.setNomProfessionel("A.KACI");
			d.setProfession(professionMap.get("Médecin généraliste"));
			d.setLocation(locationMap.get("Ouzellaguen"));
			d.setCoordonnees(new Coordonnees(4.608086965445685,36.55630484219738));
			d.setAddresse("Rue du Congrès");
			d.setTelephone("+21334351077");
			doctors.add(d);
			
			d = new Doctor();
			d.setNomProfessionel("BENHIMI");
			d.setProfession(professionMap.get("Médecin généraliste"));
			d.setLocation(locationMap.get("Tazmalt"));
			d.setCoordonnees(new Coordonnees(4.391520104939628,36.404035676422716));
			doctors.add(d);
			
			
			d = new Doctor();
			d.setNomProfessionel("Nadir SLIMANI");
			d.setProfession(professionMap.get("Médecin généraliste"));
			d.setLocation(locationMap.get("Bejaia"));
			d.setCoordonnees(new Coordonnees(4.608086965445685,36.55630484219738));
			d.setAddresse("Cité Abane Ramdane, 1000 Logements, Ihaddadène Batiment D3, N° 453 Béjaia، 06000, Algérie");
			d.setTelephone("+213662803392");
			doctors.add(d);
			
			
			try {
				userRepository.save(user);
			} catch (Exception e) {
				System.out.println(e);
			}
			for (Doctor doc : doctors) {				
				try {
					Doctor result = doctorRepository.save(doc);
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			
		}
	
	}


