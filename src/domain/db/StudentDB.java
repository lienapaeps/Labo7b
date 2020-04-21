package domain.db;

import domain.DomainException;
import domain.model.Student;

import java.util.ArrayList;


public class StudentDB {
	ArrayList<Student> klasLijst = new ArrayList<Student>();
	
	public StudentDB (){
		this.voegToe(new Student("Steegmans", "Elke", 16, "Vroedkunde"));
		this.voegToe(new Student("Jongen", "Greetje", 23, "Toegepaste Informatica"));
		this.voegToe(new Student("Van Hee","Jan",89,"Chemie"));
		this.voegToe(new Student("Melaerts","Kristien",89,"Chemie"));
	}

	public void voegToe(Student student) {
		if (heeftStudent(student)){
			throw new DomainException("Deze student bestaal al.");
		} else {
			klasLijst.add(student);
		}
	}

	private boolean heeftStudent (Student student) {
		for (Student s: klasLijst){
			if (student.getNaam().equals(s.getNaam())){
				return true;
			}
		}
		return false;
	}

	public Student vind(String naam, String voornaam) {
		if (naam.isEmpty() || naam == null && voornaam.isEmpty() || voornaam == null){
			throw new DomainException("Je moet een naam en voornaam ingeven om een student te kunnen zoeken.");
		}
		for (Student student:klasLijst) {
			if (student.heeftNaam(naam,voornaam)) {
				return student;
			}
		}
		return null;
	}
	
	public ArrayList <Student> getKlas() {
		return klasLijst;
	}

	public boolean verwijder(Student student) {
		return klasLijst.remove(student);
	}
}
