package com.example.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class seleniumTests {
    private static WebDriver driver;
    private static WebDriverWait  wait;

    private static Actions actions;
    @BeforeAll
    static void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssrikris\\Anjhana\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
        driver= new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        actions = new Actions(driver);
        String pageUrl = "https://svtplay.se";
        driver.get(pageUrl);
        acceptCookies();
    }
    @Test
    void verifyTitle(){
        String websiteHeading = driver.getTitle();
        String pageTitle = "SVT Play";
        assertEquals(pageTitle,websiteHeading,  "title not matching...");
    }

    @Test
    void verifyLogo(){
        WebElement logo = driver.findElement(By.xpath("//*[name()='svg']"));
        Assertions.assertTrue(logo.isDisplayed());
    }

    @Test
    void verifyMenu(){
        List<String> name = Arrays.asList("start", "program", "kanaler");
        List<WebElement> menuList = driver.findElements(By.xpath("//*[@data-rt='play-navigation']//li[@data-rt='menu-item']"));
        int index=0;
        for (WebElement menu: menuList) {
            assertEquals(menu.getText().toLowerCase(), name.get(index), "Menu value does not match");
            index++;
        }
    }

    static WebElement getTillgänglighetLink() {
        WebElement link = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[5]/div[2]/p[1]/a"));
        actions.moveToElement(link);
        actions.perform();
        return link;
    }
    @Test
    void verifyTillgänglighetLink(){
        WebElement link = getTillgänglighetLink();
        Assertions.assertTrue(link.isDisplayed());
    }

    @Test
    void validateTillgänglighetLink() {
        WebElement link = getTillgänglighetLink();
        link.click();
        String pageTitle = "Så arbetar SVT med tillgänglighet";
        WebElement heading=driver.findElement(By.xpath("//h1"));
        assertEquals(heading.getText(),pageTitle,  "title not matching...");
        driver.navigate().back();
       // beforeTest();
    }


    @Test
    void verifyProgramKategorier() {
        WebElement link = driver.findElement(By.linkText("PROGRAM"));
        link.click();
        String pageTitle = "Program A-Ö | SVT Play";
        wait.until(ExpectedConditions.titleIs(pageTitle));
        String websiteHeading = driver.getTitle();
        assertEquals(websiteHeading,pageTitle,  "title not matching...");
        List<WebElement> kategorier = driver.findElements(By.xpath("//*[@data-rt='grid']//article"));
        for(WebElement category : kategorier) {
            assertTrue(category.isDisplayed());
        }
        driver.navigate().back();
    }

    @Test
    void verifyKanalerKategorier() {
        WebElement link = driver.findElement(By.linkText("KANALER"));
        link.click();
        String pageTitle = "Kanaler | SVT Play";
        wait.until(ExpectedConditions.titleIs(pageTitle));
        String websiteHeading = driver.getTitle();
        assertEquals(websiteHeading,pageTitle,  "title not matching...");

        WebElement link1 = driver.findElement(By.id("tab-1"));
        link1.click();
        String pagetitle1 = "Kanaler | SVT Play";
        wait.until(ExpectedConditions.titleIs(pagetitle1));
        String websiteHeading1 = driver.getTitle();
        assertEquals(websiteHeading1, pagetitle1, "title not matching...");
        driver.navigate().back();

    }
    static WebElement getInställningarLink() {
        WebElement link = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[3]/a[1]"));
        actions.moveToElement(link);
        actions.perform();
        return link;
    }
    @Test
    void verifyInställningarLink(){
        WebElement link = getInställningarLink();
        Assertions.assertTrue(link.isDisplayed());
    }
    @Test
    void validateInställningarLink() {
        WebElement link = getInställningarLink();
        link.click();
        String pageTitle = "Inställningar | SVT Play";
        wait.until(ExpectedConditions.titleIs(pageTitle));
        WebElement heading=driver.findElement(By.xpath("//h1"));
        assertEquals(heading.getText(),"Inställningar",  "title not matching...");
        driver.navigate().back();
    }
    @Test
    void verifyNyheterKategorier() {
        WebElement link = driver.findElement(By.linkText("Nyheter"));
        link.click();
        String pageTitle = "Nyheter | SVT Play";
        wait.until(ExpectedConditions.titleIs(pageTitle));
        String websiteHeading = driver.getTitle();
        assertEquals(websiteHeading,pageTitle,  "title not matching...");
        driver.navigate().back();
        }

    @Test
    void verifyBarnKategorier() {
        WebElement link = driver.findElement(By.linkText("Barn"));
        link.click();
        String pageTitle = "Barn | SVT Play";
        wait.until(ExpectedConditions.titleIs(pageTitle));
        String websiteHeading = driver.getTitle();
        assertEquals(websiteHeading,pageTitle,  "title not matching...");

        WebElement link1 = driver.findElement(By.id("tab-1"));
        link1.click();
        String pagetitle1 = "Barn | SVT Play";
        wait.until(ExpectedConditions.titleIs(pagetitle1));
        String websiteHeading1 = driver.getTitle();
        assertEquals(websiteHeading1, pagetitle1, "title not matching...");
        driver.navigate().back();
    }

    @Test
    void verifySök() throws InterruptedException {
        WebElement link = driver.findElement(By.name("q"));
        link.click();
        link.sendKeys("Drama");
        link.submit();
        Thread.sleep(1000);
        WebElement link1= driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/section/div/ul/li[1]/article/a/div[2]/h2"));
        link1.click();
        Thread.sleep(1000);
        driver.navigate().back();
        Thread.sleep(750);
        driver.navigate().to("https://svtplay.se");

    }

    static void acceptCookies(){
        WebElement cookieModel = driver.findElement(By.xpath("//*[@data-rt='cookie-consent-modal']"));
        wait.until(ExpectedConditions.visibilityOf(cookieModel));
        driver.findElement(By.xpath("//button[text()='Acceptera alla']")).click();
        wait.until(ExpectedConditions.invisibilityOf(cookieModel));
    }

    @AfterAll
    static void afterTest() {
        driver.quit();
    }
}
