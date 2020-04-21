package domain.model;

import domain.DomainException;

public class Student {
	private String naam;
	private String voornaam;
	private int leeftijd;
	private String studierichting;
	
	public Student() {
	}
	
	public Student(String naam, String voornaam, int leeftijd, String studierichting) {
		setNaam(naam);
		setVoornaam(voornaam);
		setLeeftijd(leeftijd);
		setStudierichting(studierichting);
	}
	
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		if (naam == null || naam.trim().isEmpty()){
			throw new DomainException("Vul een naam in.");
		}
		this.naam = naam;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		if (voornaam == null || voornaam.trim().isEmpty()){
			throw new DomainException("Vul een voornaam in.");
		}
		this.voornaam = voornaam;
	}
	public int getLeeftijd() {
		return leeftijd;
	}

	public void setLeeftijd(int leeftijd) {
		if (leeftijd <= 0){
			throw new DomainException("Vul een geldige leeftijd in.");
		}
		this.leeftijd = leeftijd;
	}
	public String getStudierichting() {
		return studierichting;
	}
	public void setStudierichting(String studierichting) {
		if (studierichting == null || studierichting.trim().isEmpty()){
			throw new DomainException("Vul een studierichting in.");
		}
		this.studierichting = studierichting;
	}
	
	public String format() {
		return getNaam()+" "+getVoornaam()+" ("+getLeeftijd()+" jaar): "+getStudierichting();
	}
	
	public boolean heeftNaam(String naam,String voornaam) {
		return naam.equals(this.getNaam()) && voornaam.equals(this.getVoornaam());
	}
	
}
