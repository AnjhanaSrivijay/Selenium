package com.example.cucumberjava;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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


public class svtSteps {

    private static WebDriver driver;
    private static WebDriverWait  wait;

    private static Actions actions;
    @Given("user launches the homepage")
    public void user_launches_the_homepage() {

        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssrikris\\Anjhana\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
        driver= new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        actions = new Actions(driver);
        String pageUrl = "https://svtplay.se";
        driver.get(pageUrl);
    }
    @When("user accepts the cookies")
    public void user_accepts_the_cookies() {
        WebElement cookieModel = driver.findElement(By.xpath("//*[@data-rt='cookie-consent-modal']"));
        wait.until(ExpectedConditions.visibilityOf(cookieModel));
        driver.findElement(By.xpath("//button[text()='Acceptera alla']")).click();
        wait.until(ExpectedConditions.invisibilityOf(cookieModel));
    }
    @Then("user verify the page title")
    public void user_verify_the_page_title() {
        String websiteHeading = driver.getTitle();
        String pageTitle = "SVT Play";
        assertEquals(pageTitle,websiteHeading,  "title not matching...");
    }
    @Then("user verify the SVT logo")
    public void user_verify_the_svt_logo() {
        WebElement logo = driver.findElement(By.xpath("//*[name()='svg']"));
        assertTrue(logo.isDisplayed());
    }


    @Then("verify the menu on the homepage")
    public void verify_the_menu_on_the_homepage() {
        List<String> name = Arrays.asList("start", "program", "kanaler");
        List<WebElement> menuList = driver.findElements(By.xpath("//*[@data-rt='play-navigation']//li[@data-rt='menu-item']"));
        int index=0;
        for (WebElement menu: menuList) {
            assertEquals(menu.getText().toLowerCase(), name.get(index), "Menu value does not match");
            index++;
        }
    }


    @And("user scrolls to the footer section")
    public void user_scrolls_to_the_footer_section() {
        WebElement link = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[5]/div[2]/p[1]/a"));
        actions.moveToElement(link);
        actions.perform();
    }
    @Then("user verify the Tillgänglighet Link is available")
    public void user_verify_the_tillgänglighet_link_is_available() {
        WebElement link = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[5]/div[2]/p[1]/a"));
        actions.moveToElement(link);
        actions.perform();
        assertTrue(link.isDisplayed());
    }
    @When("user clicks on the link")
    public void user_clicks_on_the_link() {
        WebElement link = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[5]/div[2]/p[1]/a"));
        actions.moveToElement(link);
        actions.perform();
        assertTrue(link.isDisplayed());
        link.click();
    }
    @When("user clicks on the Inställingar link")
    public void user_clicks_on_the_inställingar_link() {
        WebElement link = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[3]/a[1]"));
        actions.moveToElement(link);
        actions.perform();
        assertTrue(link.isDisplayed());
        link.click();
    }
    @Then("user should land on the Tillgänglighet page")
    public void user_should_land_on_the_tillgänglighet_page() {
        String pageTitle = "Så arbetar SVT med tillgänglighet";
        WebElement heading=driver.findElement(By.xpath("//h1"));
        assertEquals(heading.getText(),pageTitle,  "title not matching...");
        driver.navigate().back();
    }



    @When("user clicks on the program")
    public void user_clicks_on_the_program() {
        WebElement link = driver.findElement(By.linkText("PROGRAM"));
        link.click();
    }

    @Then("user verifies the title of the program page")
    public void user_verifies_the_title_of_the_program_page() {
        String pageTitle = "Program A-Ö | SVT Play";
        wait.until(ExpectedConditions.titleIs(pageTitle));
        String websiteHeading = driver.getTitle();
        assertEquals(websiteHeading,pageTitle,  "title not matching...");

    }
    @Then("user verifies all the categories are displayed")
    public void user_verifies_all_the_categories_are_displayed() {
        List<WebElement> kategorier = driver.findElements(By.xpath("//*[@data-rt='grid']//article"));
        for(WebElement category : kategorier) {
            assertTrue(category.isDisplayed());
        }
        driver.navigate().back();
    }

    @When("user clicks on the kanaler")
    public void user_clicks_on_the_kanaler() {
        WebElement link = driver.findElement(By.linkText("KANALER"));
        link.click();
    }
    @Then("user verifies the title of the kanaler page")
    public void user_verifies_the_title_of_the_kanaler_page() {
        String pageTitle = "Kanaler | SVT Play";
        wait.until(ExpectedConditions.titleIs(pageTitle));
        String websiteHeading = driver.getTitle();
        assertEquals(websiteHeading,pageTitle,  "title not matching...");
    }
    @When("user clicks on the programs tab")
    public void user_clicks_on_the_programs_tab() {
        WebElement link1 = driver.findElement(By.id("tab-1"));
        link1.click();
    }

    @Then("user verifies the title of the page")
    public void user_verifies_the_title_of_the_page() {
        String pagetitle1 = "Kanaler | SVT Play";
        wait.until(ExpectedConditions.titleIs(pagetitle1));
        String websiteHeading1 = driver.getTitle();
        assertEquals(websiteHeading1, pagetitle1, "title not matching...");
        driver.navigate().back();
    }

    @Then("user verify the Inställingar Link is available")
    public void user_verify_the_inställingar_link_is_available() {
        WebElement link = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[3]/a[1]"));
        actions.moveToElement(link);
        actions.perform();
        assertTrue(link.isDisplayed());
    }

    @Then("user should land on the Inställingar page")
    public void user_should_land_on_the_inställingar_page() {
        String pageTitle = "Inställningar | SVT Play";
        wait.until(ExpectedConditions.titleIs(pageTitle));
        WebElement heading=driver.findElement(By.xpath("//h1"));
        assertEquals(heading.getText(),"Inställningar",  "title not matching...");
    }

    @When("user selects the nyheter category")
    public void user_selects_the_nyheter_category() {
        WebElement link = driver.findElement(By.linkText("Nyheter"));
        link.click();
    }

    @Then("user should land on the Nyheter page")
    public void user_should_land_on_the_nyheter_page() {
        String pageTitle = "Nyheter | SVT Play";
        wait.until(ExpectedConditions.titleIs(pageTitle));
        String websiteHeading = driver.getTitle();
        assertEquals(websiteHeading,pageTitle,  "title not matching...");
        driver.navigate().back();
    }

    @When("user validates the title of the Barn category page")
    public void user_clicks_on_the_barn_category() {
        WebElement link = driver.findElement(By.linkText("Barn"));
        link.click();
        String pageTitle = "Barn | SVT Play";
        wait.until(ExpectedConditions.titleIs(pageTitle));
        String websiteHeading = driver.getTitle();
        assertEquals(websiteHeading,pageTitle,  "title not matching...");
    }

    @When("user clicks on the program tab of the barn category")
    public void user_clicks_on_the_tab() {
        WebElement link1 = driver.findElement(By.id("tab-1"));
        link1.click();
    }


    @Then("user verifies the title of the program category of barn page")
    public void user_verifies_the_title_of_the_barn_category_page() {
        String pagetitle1 = "Barn | SVT Play";
        wait.until(ExpectedConditions.titleIs(pagetitle1));
        String websiteHeading1 = driver.getTitle();
        assertEquals(websiteHeading1, pagetitle1, "title not matching...");
        driver.navigate().back();
    }

    @When("user clicks on the Sök tab")
    public void user_clicks_on_the_sök_tab() {
        WebElement link = driver.findElement(By.name("q"));
        link.click();
    }

    @When("user provides input as drama")
    public void user_provides_input_as_drama() {
        WebElement link = driver.findElement(By.name("q"));
        link.click();
        link.sendKeys("Drama");
    }

    @When("user click submit")
    public void user_click_submit() {
        WebElement link = driver.findElement(By.name("q"));
        link.click();
        link.sendKeys("Drama");
        link.submit();
    }

    @Then("user should land on the Drama category page")
    public void user_should_land_on_the_drama_category_page() {
        WebElement link1= driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/section/div/ul/li[1]/article/a/div[2]/h2"));
        link1.click();
    }

    @When("user navigates back")
    public void user_clicks_on_back() {
        driver.navigate().back();
    }

    @When("user clicks on the homepage")
    public void user_clicks_on_the_homepage() {
        driver.navigate().to("https://svtplay.se");
    }












}
