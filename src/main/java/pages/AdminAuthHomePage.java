package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigLoader;

import java.time.Duration;


public class AdminAuthHomePage extends BasePage {

    public AdminAuthHomePage(WebDriver driver) {
        super(driver);
    }

    private final String BASE_URL = ConfigLoader.getProperty("BASE_URL");
    private final String FIELD_PASSWORD = ("//*[@placeholder='Введіть пароль']");
    private final String FIELD_EMAIL = ("//*[@placeholder='Введіть email']");
    private final String LOGIN_BUTTON = ("//button[@type='submit']");
    private final String ERROR_MESSAGE = ("//div[@class='login-error']");
    private final String LOGOUT_BUTTON = ("//button//span[contains(text(), 'Log out')]");
    private final String DASHBOARD_HEADING = ("//a[contains(@class, 'link') and contains(@href, '/layout/dashboard')]");

    public void openAdminHomePage() {
        driver.get(BASE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }
    public void enterValidEmail(String email) {
        WebElement emailField = waitElementToBeVisible(FIELD_EMAIL);
        emailField.clear();
        emailField.sendKeys(email);
        System.out.println("Entered email: " + email);
    }

    public void enterValidPassword(String password) {
        WebElement passwordField = waitElementToBeVisible(FIELD_PASSWORD);
        passwordField.clear();
        passwordField.sendKeys(password);
        System.out.println("Entered password: " + password.replaceAll(".", "*"));
    }



    public void enterInValidEmail(String email) {
        waitElementToBeVisible(FIELD_EMAIL).sendKeys(email);
    }


    public void enterInValidPassword(String password) {
        waitElementToBeVisible(FIELD_PASSWORD).sendKeys(password);
    }


    public void clickLoginButton() {
        WebElement loginButton = waitElementToBeVisible(LOGIN_BUTTON);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            loginButton.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Using JavaScript click as fallback");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
        }
    }


    public String getErrorMessage() {
        try {
            return waitElementToBeVisible(ERROR_MESSAGE).getText();
        } catch (Exception e) {
            System.out.println("Error finding message: " + e.getMessage());
            return "";
        }
    }


    public void clickLogoutButton() {
        waitElementToBeVisible(LOGOUT_BUTTON).click();
    }


    public boolean isLoginFormDisplayed() {
        try {
            return waitElementToBeVisible(FIELD_EMAIL).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isDashboardDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DASHBOARD_HEADING)));
            return dashboard.isDisplayed();
        } catch (Exception e) {
            System.out.println("Dashboard не знайдено: " + e.getMessage());
            return false;
        }
    }
}