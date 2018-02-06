package us.sts.mantis.tests;

import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.sts.mantis.model.Issue;
import us.sts.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {

    //@Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    public Issue CreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue")
                .withDescription("Test issue description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummary(),created.getSummary());
        return created;
    }


    public Issue CreateAndCloseIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue")
                .withDescription("Test issue description").withProject(projects.iterator().next());
        return app.soap().closeIssue(issue);
    }

    @Test
    public void checkNotClosedIssue() throws RemoteException, ServiceException, MalformedURLException {
        Issue notCloseIssue = CreateIssue() ;
        skipIfNotFixed(notCloseIssue.getId());
        System.out.println("Issue is in progress");
    }

    @Test
    public void checkClosedIssue() throws RemoteException, ServiceException, MalformedURLException {
        Issue notCloseIssue = CreateAndCloseIssue();
        skipIfNotFixed(notCloseIssue.getId());
        System.out.println("Issue is closed");
    }


}
