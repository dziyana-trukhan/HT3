import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class githubTst {
    private Steps steps;
    private WebDriver driver;
    private final String USERNAME = "someuser";
    private final String PASSWORD = "somepassword";
    private final int LENGTH = 6;

    @BeforeMethod(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.openBrowser();
    }

    @Test
    public void tstIsEmpty() {
        steps.loginGithub(USERNAME, PASSWORD);
        steps.createNewRepository("someName", "someDescription");
        Assert.assertTrue(steps.currentRepositoryIsEmpty());

    }

    @Test
    public void tstCreation()
    {
        steps.loginGithub(USERNAME, PASSWORD);
        String repositoryName = steps.generateRandomRepositoryNameWithCharLength(LENGTH);
        steps.createNewRepository(repositoryName, "someDescription");
        Assert.assertEquals(steps.getCurrentRepositoryName(), repositoryName);
    }

    @Test
    public void tstLogin()
    {
        steps.loginGithub(USERNAME, PASSWORD);
        Assert.assertEquals(USERNAME, steps.getLoggedInUserName());
    }


    @AfterMethod(description = "Stop Browser")
    public void stopBrowser()
    {
        steps.closeBrowser();
    }
}
