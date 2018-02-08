package us.sts.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import us.sts.addressbook.appmanager.ApplicationManager;
import us.sts.addressbook.model.GroupData;
import us.sts.addressbook.model.Groups;
import us.sts.addressbook.model.Issue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

     @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

     @BeforeMethod
    public void logTestStart(Method method, Object[] p) {
        logger.info("Start " + method.getName() + "with parameters" + Arrays.asList(p));
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
    }


    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json?limit=1000"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    public Set<Issue> getClosedIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/filters/4/issues.json?limit=1000"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    public Issue getIssueById(int issueId) throws IOException {
        Set<Issue> notClosedIssues = getIssues();
        Set<Issue> closedIssues = getClosedIssues();
        Issue issue = null;
        for (Issue i : notClosedIssues) {
            if (i.getId() == issueId) {
                issue = i;
            }
        }
        for (Issue i : closedIssues) {
            if (i.getId() == issueId) {
                issue = i;
            }
        }
        return issue;
    }

    boolean isIssueOpen(int issueId) throws IOException {
        Issue issue = getIssueById(issueId);
        if (issue.getState() == 3 || issue.getState() == 99) {
            return false;
        } else {
            return true;
        }
    }


    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public static void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.
                    stream().map((g) -> new GroupData().withId(g.getId()).withName(g.getName())).
                    collect(Collectors.toSet())));
        }

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }


    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {
        logger.info("Stop " + method.getName());
    }
}

