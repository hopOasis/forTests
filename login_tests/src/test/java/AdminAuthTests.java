import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AdminAuthHomePage;
import utils.ConfigLoader;
import java.time.Duration;



public class AdminAuthTests extends TestInit {

    public AdminAuthHomePage adminAuthHomePage;
    private String adminUsername;
    private String adminPassword;
    private String baseUrl;


    @Override
    @BeforeEach
    public void setupTest() {
        super.setupTest();
        adminAuthHomePage = new AdminAuthHomePage(driver);

        adminUsername = ConfigLoader.getProperty("ADMIN_USERNAME");
        adminPassword = ConfigLoader.getProperty("ADMIN_PASSWORD");
        baseUrl = ConfigLoader.getProperty("BASE_URL");

        if (adminUsername == null || adminPassword == null || baseUrl == null) {
            Assertions.fail("Missing required environment variables: ADMIN_USERNAME, ADMIN_PASSWORD, BASE_URL");
        }
    }


    @Test
    public void testSuccessfulLogin() {

        adminAuthHomePage.openAdminHomePage();
        adminAuthHomePage.enterValidEmail(adminUsername);
        adminAuthHomePage.enterValidPassword(adminPassword);
        adminAuthHomePage.clickLoginButton();

        Assertions.assertTrue(adminAuthHomePage.isDashboardDisplayed(), "Dashboard не відображається після входу");
    }


    @Test
    public void testInvalidLogin() {

        adminAuthHomePage.openAdminHomePage();
        adminAuthHomePage.enterInValidEmail("invalid@example.com");
        adminAuthHomePage.enterInValidPassword("wrongpassword");
        adminAuthHomePage.clickLoginButton();

        Assertions.assertTrue(adminAuthHomePage.getErrorMessage().
                        contains("Логін або Пароль не вірні"),
                "Очікуване повідомлення про помилку не з'явилося");
    }


    @Test
    public void testSessionInvalidationAfterLogout() {

        adminAuthHomePage.openAdminHomePage();
        adminAuthHomePage.enterValidEmail(adminUsername);
        adminAuthHomePage.enterValidPassword(adminPassword);

        adminAuthHomePage.clickLoginButton();
        Assertions.assertTrue(adminAuthHomePage.isDashboardDisplayed(), "Dashboard не відображається після входу");

        adminAuthHomePage.clickLogoutButton();
        Assertions.assertTrue(adminAuthHomePage.isLoginFormDisplayed(), "Сесія не була інвалідована");
    }

    @Test
    public void testLogoutFunctionality() {

        adminAuthHomePage.openAdminHomePage();
        adminAuthHomePage.enterValidEmail(adminUsername);
        adminAuthHomePage.enterValidPassword(adminPassword);
        adminAuthHomePage.clickLoginButton();

        Assertions.assertTrue(adminAuthHomePage.isDashboardDisplayed(), "Dashboard is not displayed after login");

        adminAuthHomePage.clickLogoutButton();
        Assertions.assertTrue(adminAuthHomePage.isLoginFormDisplayed(), "Login form is not displayed after logout");
    }

    @Test
    public void testInvalidLoginErrorMessage() {
        adminAuthHomePage.openAdminHomePage();
        adminAuthHomePage.enterInValidEmail("invalid@example.com");
        adminAuthHomePage.enterInValidPassword("wrongpassword");
        adminAuthHomePage.clickLoginButton();

        String errorMessage = adminAuthHomePage.getErrorMessage();

        Assertions.assertTrue(
                errorMessage.toLowerCase().contains("логін") ||
                        errorMessage.toLowerCase().contains("пароль") ||
                        errorMessage.toLowerCase().contains("не вірні"),
                "Очікувалось повідомлення про помилку логіна або паролю, отримано: " + errorMessage
        );
    }

    @Test
    public void testRedirectToDashboardAfterLogin () {

        adminAuthHomePage.openAdminHomePage();
        adminAuthHomePage.enterValidEmail(adminUsername);
        adminAuthHomePage.enterValidPassword(adminPassword);
        adminAuthHomePage.clickLoginButton();


        String expectedUrl = baseUrl;
        if (!expectedUrl.endsWith("/")) {
            expectedUrl += "/";
        }
        expectedUrl += "layout/dashboard";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (TimeoutException e) {
            String errorMessage = adminAuthHomePage.getErrorMessage();
            Assertions.fail("Перенаправлення на Dashboard не відбулося. Повідомлення про помилку: " + errorMessage);
        }
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, currentUrl, "Після входу не відбувся перехід на сторінку Dashboard");
    }
}