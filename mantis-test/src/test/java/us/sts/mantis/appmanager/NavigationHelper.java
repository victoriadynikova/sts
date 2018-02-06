package us.sts.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public NavigationHelper manage() throws InterruptedException {
       // wd.get(app.getProperty("web.baseUrl") + "/my_view_page.php");
        click(By.xpath("//div[@id='sidebar']//a[normalize-space(.)='Manage']"));
        return this;
    }

    public void users() {
        click(By.linkText("Manage Users"));
    }

    public NavigationHelper chooseUser(String username){
        click(By.linkText(username));
        return this;
    }

    public void resetPassword(){
        click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
    }



    public void confirmation(String confirmationLink,String username, String newPassword) {
        wd.get(confirmationLink);
        type(By.name("realname"),username);
        type(By.name("password"),newPassword);
        type(By.name("password_confirm"),newPassword);
        click(By.xpath("//span[@class='submit-button']//button[normalize-space(.)='Update User']"));

    }
}
