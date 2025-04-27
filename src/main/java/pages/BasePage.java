package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

     public class BasePage {

         public WebDriver driver;

         public BasePage(WebDriver driver) {
             this.driver = driver;
         }

         int BASIC_TIME = 20;

         public WebElement waitElementToBeVisible(String locator) {
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BASIC_TIME));
             return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
         }

         public void openAdminHomePage() {
             driver.getCurrentUrl();
         }

         public WebElement waitElementToBeVisible(String locator, int timeoutInSeconds) {
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
             return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
         }

         public WebElement waitElementToBeClickable(String locator) {
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BASIC_TIME));
             return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
         }

         public boolean waitForUrl(String url, int timeoutInSeconds) {
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
             return wait.until(ExpectedConditions.urlToBe(url));
         }

     }