package ui.controller;


import domain.DomainException;
import domain.db.StudentDB;
import domain.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/StudentInfo")
public class StudentInformatie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudentDB klas = new StudentDB();

	public StudentInformatie() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "index.html";
		String command = request.getParameter("command");
		if (command == null)
			command = "";
		switch (command) {
			case "voegStudentToe":
				destination = voegStudentToe(request);
				break;
			case "vindStudent":
				destination = vindStudent(request);
				break;
			case "overzicht":
				destination = overzicht(request);
				break;
			case "verwijder":
				destination = verwijder(request);
				break;
			case "verwijderBevestig":
				destination = verwijderBevestig(request);
				break;
			default:
				destination = "index.html";
		}
		request.getRequestDispatcher(destination).forward(request, response);
	}

	private String voegStudentToe(HttpServletRequest request) {
		ArrayList<String> errors = new ArrayList<String>();

		Student student = new Student();
		setNaam(student, request, errors);
		setVoornaam(student, request, errors);
		setLeeftijd(student, request, errors);
		setStudierichting(student, request, errors);

		if (errors.size() == 0){
			try {
				klas.voegToe(student);
				return overzicht(request);
			} catch (DomainException exc) {
				errors.add(exc.getMessage());
			}
		}
		request.setAttribute("errors", errors);
		return "studentForm.jsp";
	}

	private void setStudierichting(Student student, HttpServletRequest request, ArrayList<String> errors) {
		String studierichting = request.getParameter("studierichting");
		try{
			student.setStudierichting(studierichting);
			request.setAttribute("studierichtingClass", "has-succes");
			request.setAttribute("studierichtingPreviousValue", studierichting);
		}
		catch (DomainException exc){
			errors.add(exc.getMessage());
			request.setAttribute("studierichtingClass", "has-error");
		}
	}

	private void setLeeftijd(Student student, HttpServletRequest request, ArrayList<String> errors) {
		String leeftijd = request.getParameter("leeftijd");
		try{
			student.setLeeftijd(Integer.parseInt(leeftijd));
			request.setAttribute("leeftijdClass", "has-succes");
			request.setAttribute("leeftijdPreviousValue", leeftijd);
		}catch (NumberFormatException exc){
			errors.add("Vul een geldige leeftijd in.");
			request.setAttribute("leeftijdClass", "has-error");
		}
		catch (DomainException exc){
			errors.add(exc.getMessage());
			request.setAttribute("leeftijdClass", "has-error");
		}
	}

	private void setVoornaam(Student student, HttpServletRequest request, ArrayList<String> errors) {
		String voornaam = request.getParameter("voornaam");
		try{
			student.setVoornaam(voornaam);
			request.setAttribute("voornaamClass", "has-succes");
			request.setAttribute("voornaamPreviousValue", voornaam);
		}
		catch (DomainException exc){
			errors.add(exc.getMessage());
			request.setAttribute("voornaamClass", "has-error");
		}
	}

	private void setNaam(Student student, HttpServletRequest request, ArrayList<String> errors) {
		String naam = request.getParameter("naam");
		try{
			student.setNaam(naam);
			request.setAttribute("naamClass", "has-succes");
			request.setAttribute("naamPreviousValue", naam);
		}
		catch (DomainException exc){
			errors.add(exc.getMessage());
			request.setAttribute("naamClass", "has-error");
		}
	}

	private String overzicht(HttpServletRequest request) {
		request.setAttribute("studenten", klas.getKlas());
		return "studentOverview.jsp";
	}

	private String vindStudent(HttpServletRequest request) {
		ArrayList<String> errors = new ArrayList<String>();

		Student student = new Student();
		setNaam(student, request, errors);
		setVoornaam(student, request, errors);

		if (errors.size() == 0) {
			try {
				if(klas.heeftStudent(student)){
					return "gevonden.jsp";
				}
				else {
					return "nietGevonden.jsp";
				}
			} catch (DomainException e) {
				errors.add(e.getMessage());
			}
		}
		request.setAttribute("errors", errors);
		return "zoekForm.jsp";
	}

	private String verwijderBevestig(HttpServletRequest request) {
		if (request.getParameter("submit").equals("Zeker")){
			String naam = request.getParameter("naam");
			String voornaam = request.getParameter("voornaam");
			klas.verwijder(klas.vind(naam, voornaam));
			return overzicht(request);
		}
		else
			return "index.html";
	}

	private String verwijder(HttpServletRequest request) {
		return "verwijderBevestiging.jsp";
	}

}
