package us.sts.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import us.sts.addressbook.model.GroupData;
import us.sts.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withFooter("test3"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData modifiedGroup = (GroupData) before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("qwe1").withHeader("qwe2").withFooter("qwe3");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));

        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).without(group).withAdded(group)));
        verifyGroupListInUI();
    }



}
