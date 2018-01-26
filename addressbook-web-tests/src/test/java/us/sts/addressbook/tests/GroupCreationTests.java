package us.sts.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.sts.addressbook.model.GroupData;
import us.sts.addressbook.model.Groups;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
        String line = reader.readLine();
        String xml = new String("");

        while (line != null){
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
        String line = reader.readLine();
        String json = new String("");
        while (line != null){
            json += line;
            line = reader.readLine();
        }
        Gson gsone = new Gson();
        List <GroupData> groups = gsone.fromJson(json,new TypeToken<List<GroupData>>(){}.getType()); // List<GroupData>.class
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test (dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData group) {

            app.goTo().groupPage();
            Groups before = app.group().all();
            app.group().create(group);
            assertThat(app.group().count(), equalTo(before.size() + 1));

            Groups after = app.group().all();
            assertThat(after, equalTo(before.
                    withAdded(group.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));

    }

}
