package us.sts.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import us.sts.addressbook.model.GroupData;
import us.sts.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1").withFooter("test3"));
        }
    }

    @Test
    public void testGroupDeletion() {

        Groups before = app.group().all();
        GroupData deletedGroup = (GroupData) before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() - 1));

        assertThat(after, equalTo(before.without(deletedGroup)));
    }


}
