package view;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class StudentTest {

    WebDriver driver;

    String url = "http://localhost:8080/web_war_exploded/";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\paeps\\Documents\\19-20\\Semester2\\Webontwikkeling2\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @After
    public void clean(){
        driver.quit();
    }

    @Test
    public void test_StudentForm_alles_invullen_gaat_naar_overzicht_en_toont_nieuwe_student_in_tabel(){
        driver.get(url + "studentForm.jsp");
    clearForm();
    fillOutForm("Paeps", "Liena", 19, "TI");

    driver.findElement(By.id("bewaar")).click();

    assertEquals("Overzicht Studenten", driver.getTitle());
        ArrayList<WebElement> tds = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertTrue(containsWebElementsWithText(tds, "Paeps"));
        assertTrue(containsWebElementsWithText(tds, "Liena"));
        assertTrue(containsWebElementsWithText(tds, "19"));
        assertTrue(containsWebElementsWithText(tds, "TI"));
    }

    @Test
    public void test_als_student_gezocht_die_niet_toegevoegd_is_geeft_nietgevonden(){
    driver.get(url + "zoekForm.jsp" );

    driver.findElement(By.id("naam")).sendKeys("Peeters");
    driver.findElement(By.id("voornaam")).sendKeys("Stijn");
    driver.findElement(By.id("zoek")).click();

    assertEquals("Niet gevonden", driver.getTitle());
    driver.findElement(By.id("foutboodschap"));
    }

    @Test
    public void test_student_uit_constructor_wordt_gevonden(){
        driver.get(url + "zoekForm.jsp" );

        driver.findElement(By.id("naam")).sendKeys("Steegmans");
        driver.findElement(By.id("voornaam")).sendKeys("Elke");
        driver.findElement(By.id("zoek")).click();

        assertEquals("Gevonden", driver.getTitle());
        driver.findElement(By.id("boodschap"));
    }

    @Test
    public void test_student_die_nieuw_toegevoegd_is_wordt_gevonden(){
        driver.get(url + "studentForm.jsp");
        clearForm();
        fillOutForm("Lionel", "Messi", 32, "Sport");
        driver.findElement(By.id("bewaar")).click();

        driver.get(url + "zoekForm.jsp");
        driver.findElement(By.id("naam")).sendKeys("Lionel");
        driver.findElement(By.id("voornaam")).sendKeys("Messi");
        driver.findElement(By.id("zoek")).click();

        assertEquals("Gevonden", driver.getTitle());
        driver.findElement(By.id("boodschap"));
    }

    @Test
    public void test_als_Lege_Student_Toegevoegd_Dan_Wordt_Formulier_Opnieuw_Getoond_met_alle_foutboodschappen(){
        driver.get(url + "studentForm.jsp");
        clearForm();
        driver.findElement(By.id("bewaar")).click();

        assertEquals("Voeg een student toe", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Vul een naam in."));
        assertTrue(containsWebElementsWithText(lis, "Vul een voornaam in."));
        assertTrue(containsWebElementsWithText(lis, "Vul een geldige leeftijd in."));
        assertTrue(containsWebElementsWithText(lis, "Vul een studierichting in."));
    }

    @Test
    public void test_als_enkel_leeftijd_verkeerd_is_bevatten_overige_velden_vorige_waarde(){
        driver.get(url + "studentForm.jsp");
        fillOutForm("Vdb", "Dries", -2 , "Geneeskunde");
        driver.findElement(By.id("bewaar")).click();

        assertEquals("Voeg een student toe", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Vul een geldige leeftijd in."));
        assertEquals("Dries", driver.findElement(By.id("voornaam")).getAttribute("value"));
        assertEquals("Vdb", driver.findElement(By.id("naam")).getAttribute("value"));
    }

    @Test
    public void test_als_bestaande_student_wordt_toegevoegd_wordt_formulier_met_bijhorende_foutboodschap_getoond(){
        driver.get(url + "studentForm.jsp");
        fillOutForm("Steegmans", "Elke", 16 , "Vroedkunde");
        driver.findElement(By.id("bewaar")).click();

        assertEquals("Voeg een student toe", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Deze student bestaal al."));
    }

    private void clearForm() {
        driver.findElement(By.id("naam")).clear();
        driver.findElement(By.id("voornaam")).clear();
        driver.findElement(By.id("leeftijd")).clear();
        driver.findElement(By.id("studierichting")).clear();
    }

    private void fillOutForm(String naam, String voornaam, int leeftijd, String studierichting){
        driver.findElement(By.id("naam")).sendKeys(naam);
        driver.findElement(By.id("voornaam")).sendKeys(voornaam);
        driver.findElement(By.id("leeftijd")).sendKeys(String.valueOf(leeftijd));
        driver.findElement(By.id("studierichting")).sendKeys(studierichting);
    }

    private boolean containsWebElementsWithText(ArrayList<WebElement> elements, String text) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equals(text)) {
                return true;
            }
        }
        return false;
    }
}
