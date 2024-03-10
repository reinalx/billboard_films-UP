package es.udc.paproject.e2etests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

class AppTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        driver.get("http://localhost:3000/");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    void login(String user, String password){

        WebElement loginLink = driver.findElement(By.id("login-link"));
        loginLink.click();

        WebElement userTextField = driver.findElement(By.id("userName"));
        userTextField.sendKeys(user);

        WebElement passTextField = driver.findElement(By.id("password"));
        passTextField.sendKeys(password + Keys.ENTER);
    }

    @Test
    void testLogin(){

        String title = driver.getTitle();
        assertTrue(title.contains("PA Project"));

        login("viewer", "pa2223");

        WebElement user = driver.findElement(By.id("user"));
        assertTrue(user.getText().contains("viewer"));

    }

    @Test
    void testSession(){

        //login viewer

        login("viewer", "pa2223");

        //select day

        WebElement billboard = driver.findElement(By.id("billboardDate"));
        List<WebElement> date = billboard.findElements(By.tagName("option"));
        date.get(1).click();

        WebElement movieTable = driver.findElement(By.id("tableMovies"));
        List<WebElement> movies = movieTable.findElements(By.tagName("tr"));

        String movieTitle = movies.get(0).findElement(By.tagName("td")).getText();
        String sessionTime = movies.get(0).findElements(By.id("session-link")).get(0).getText();

        movies.get(0).findElements(By.id("session-link")).get(0).click();

        //Check session page

        driver.findElement(By.id("duration"));
        driver.findElement(By.id("price"));
        driver.findElement(By.id("date"));
        driver.findElement(By.id("remainingSeats"));

        //Check sale form

        driver.findElement(By.id("creditCard"));
        driver.findElement(By.id("numberTickets"));
        driver.findElement(By.id("buy"));

        //Check movie

        String  movie = driver.findElement(By.id("movie-link")).getText();
        String session = driver.findElement(By.id("time-session")).getText();
        assertTrue(movie.contains(movieTitle));
        assertTrue(session.contains(sessionTime));

    }

}