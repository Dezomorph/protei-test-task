import Pages.ConfProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
    }

    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //Тест на логин без заполнения полей
    @Test
    public void testEmptyLoginForm() {
        driver.get(ConfProperties.getProperty("loginpage"));

        WebElement loginEmail = driver.findElement(By.id("loginEmail"));
        WebElement loginPassword = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailFormatError")));

        Assertions.assertNotNull(errorMessage);
    }


    // Тест успешного входа
    @Test
    public void testSuccessfulLogin() {
        driver.get(ConfProperties.getProperty("loginpage"));

        WebElement loginEmail = driver.findElement(By.id("loginEmail"));
        WebElement loginPassword = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        loginEmail.sendKeys(ConfProperties.getProperty("loginEmail"));
        loginPassword.sendKeys(ConfProperties.getProperty("loginPassword"));
        loginButton.click();

        WebElement inputsPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputsPage")));
        Assertions.assertNotNull(inputsPage);
    }

    //Тест на корректность заполнения поля Email
    @Test
    public void testIncorrectEmailType() {
        driver.get(ConfProperties.getProperty("loginpage"));

        WebElement loginEmail = driver.findElement(By.id("loginEmail"));
        WebElement loginPassword = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        loginEmail.sendKeys(ConfProperties.getProperty("incorrectEmailType"));
        loginPassword.sendKeys(ConfProperties.getProperty("loginPassword"));


        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailFormatError")));
        Assertions.assertNotNull(errorMessage);

    }

    //Тест на логин с некорректными данными
    @Test
    public void testIncorrectData() {
        driver.get(ConfProperties.getProperty("loginpage"));

        WebElement loginEmail = driver.findElement(By.id("loginEmail"));
        WebElement loginPassword = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        loginEmail.sendKeys(ConfProperties.getProperty("incorrectEmail"));
        loginPassword.sendKeys(ConfProperties.getProperty("incorrectPassword"));

        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("invalidEmailPassword")));
        Assertions.assertNotNull(errorMessage);
    }

    //Тест на заполнение формы с пуcтым Email
    @Test
    public void testEmptyEmailInputsForm() {
        driver.get(ConfProperties.getProperty("loginpage"));

        WebElement loginEmail = driver.findElement(By.id("loginEmail"));
        WebElement loginPassword = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        loginEmail.sendKeys(ConfProperties.getProperty("loginEmail"));
        loginPassword.sendKeys(ConfProperties.getProperty("loginPassword"));
        loginButton.click();

        WebElement inputsPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputsPage")));
        Assertions.assertNotNull(inputsPage);

        WebElement dataSend = driver.findElement(By.id("dataSend"));
        dataSend.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailFormatError")));
        Assertions.assertNotNull(errorMessage);
    }

    //Тест на заполнение формы с пуcтым Name
    @Test
    public void testEmptyNameInputsForm() {
        driver.get(ConfProperties.getProperty("loginpage"));

        WebElement loginEmail = driver.findElement(By.id("loginEmail"));
        WebElement loginPassword = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        loginEmail.sendKeys(ConfProperties.getProperty("loginEmail"));
        loginPassword.sendKeys(ConfProperties.getProperty("loginPassword"));
        loginButton.click();

        WebElement inputsPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputsPage")));
        Assertions.assertNotNull(inputsPage);

        WebElement dataEmail = driver.findElement(By.id("dataEmail"));
        WebElement dataSend = driver.findElement(By.id("dataSend"));

        dataEmail.sendKeys(ConfProperties.getProperty("dataEmail"));
        dataSend.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("blankNameError")));
        Assertions.assertNotNull(errorMessage);
    }

    //Teст на некорректное заполнение email на форме
    @Test
    public void testIncorrectEmailInputsForm() {
        driver.get(ConfProperties.getProperty("loginpage"));

        WebElement loginEmail = driver.findElement(By.id("loginEmail"));
        WebElement loginPassword = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        loginEmail.sendKeys(ConfProperties.getProperty("loginEmail"));
        loginPassword.sendKeys(ConfProperties.getProperty("loginPassword"));
        loginButton.click();

        WebElement inputsPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputsPage")));
        Assertions.assertNotNull(inputsPage);

        WebElement dataEmail = driver.findElement(By.id("dataEmail"));
        WebElement dataName = driver.findElement(By.id("dataName"));
        WebElement dataSend = driver.findElement(By.id("dataSend"));

        dataEmail.sendKeys(ConfProperties.getProperty("incorrectDataEmail"));
        dataName.sendKeys(ConfProperties.getProperty("dataName"));
        dataSend.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailFormatError")));
        Assertions.assertNotNull(errorMessage);
    }

    //Teст на полное заполнение полей на форме
    @Test
    public void testFullSetInputsForm() {
        driver.get(ConfProperties.getProperty("loginpage"));

        WebElement loginEmail = driver.findElement(By.id("loginEmail"));
        WebElement loginPassword = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        loginEmail.sendKeys(ConfProperties.getProperty("loginEmail"));
        loginPassword.sendKeys(ConfProperties.getProperty("loginPassword"));
        loginButton.click();

        WebElement inputsPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputsPage")));
        Assertions.assertNotNull(inputsPage);

        WebElement dataEmail = driver.findElement(By.id("dataEmail"));
        WebElement dataName = driver.findElement(By.id("dataName"));
        WebElement dataSend = driver.findElement(By.id("dataSend"));
        WebElement dataCheck11 = driver.findElement(By.id("dataCheck11"));
        WebElement dataCheck12 = driver.findElement(By.id("dataCheck12"));
        WebElement dataSelect21 = driver.findElement(By.id("dataSelect21"));
        WebElement dataSelect22 = driver.findElement(By.id("dataSelect22"));
        WebElement dataSelect23 = driver.findElement(By.id("dataSelect23"));

        dataEmail.sendKeys(ConfProperties.getProperty("dataEmail"));
        dataName.sendKeys(ConfProperties.getProperty("dataName"));
        dataCheck11.click();
        dataCheck12.click();
        dataSelect21.click();
        dataSelect22.click();
        dataSelect23.click();
        dataSend.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement modalWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div/div[1]")));

        WebElement okButton = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button"));
        okButton.click();

        WebElement dataTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"dataTable\"]/tbody/tr")));
        Assertions.assertNotNull(dataTable);

    }

    //Teст на неполное заполнение полей на форме
    @Test
    public void testNotFullSetInputsForm() {
        driver.get(ConfProperties.getProperty("loginpage"));

        WebElement loginEmail = driver.findElement(By.id("loginEmail"));
        WebElement loginPassword = driver.findElement(By.id("loginPassword"));
        WebElement loginButton = driver.findElement(By.id("authButton"));

        loginEmail.sendKeys(ConfProperties.getProperty("loginEmail"));
        loginPassword.sendKeys(ConfProperties.getProperty("loginPassword"));
        loginButton.click();

        WebElement inputsPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputsPage")));
        Assertions.assertNotNull(inputsPage);

        WebElement dataEmail = driver.findElement(By.id("dataEmail"));
        WebElement dataName = driver.findElement(By.id("dataName"));
        WebElement dataSend = driver.findElement(By.id("dataSend"));
        WebElement dataGender = driver.findElement(By.id("dataGender"));

        dataEmail.sendKeys(ConfProperties.getProperty("dataEmail"));
        dataName.sendKeys(ConfProperties.getProperty("dataName"));
        dataGender.click();
        dataGender.sendKeys("Женский");
        dataSend.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement modalWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div/div[1]")));

        WebElement okButton = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button"));
        okButton.click();

        WebElement dataTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"dataTable\"]/tbody/tr")));
        Assertions.assertNotNull(dataTable);

    }
}
