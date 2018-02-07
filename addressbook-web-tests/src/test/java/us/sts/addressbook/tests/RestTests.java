package us.sts.addressbook.tests;

import org.apache.http.client.fluent.Executor;
import org.testng.annotations.Test;
import us.sts.addressbook.model.Issue;

import java.io.IOException;
import java.util.Set;


public class RestTests extends TestBase {

    @Test
    public void testForClosedIssue() throws IOException {
        Set<Issue> issues = getClosedIssues();
        Issue issue = issues.iterator().next();
        skipIfNotFixed(issue.getId());
        System.out.println("Closed issue");
    }

    @Test
    public void testForNotFixedIssue() throws IOException {
        Set<Issue> issues = getIssues();
        Issue issue = issues.iterator().next();
        skipIfNotFixed(issue.getId());
        System.out.println("Not closed issue");
    }
}

