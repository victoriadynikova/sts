package us.sts.addressbook.tests;

import org.testng.annotations.Test;
import us.sts.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().groupCreation(new GroupData("test1", null, "test3"));
    }

}
