package us.sts.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import us.sts.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {

        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
        }

        generator.run();


        
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if(format.equals("csv")){
            saveAsCsv(contacts,new File(file));
        }else if (format.equals("xml")){
            saveAsXml(contacts,new File(file));
        }else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        }else {
            System.out.println("Unrecognized format " + format);
        }
    }



    private List<ContactData> generateContacts(int count) {
        List <ContactData> contacts = new ArrayList<>();
        for (int i = 0; i<count;i++)
        {
            contacts.add(new ContactData().withFirstName(String.format("name %s",i)).withMiddleName("Middle").
                    withLastName(String.format("Last %s",i)).withNickname("Nick").withTitle("user")
                    .withCompany("Company").withAddress("Moscow Prospect Mira 44 ").withHomePhone("1-1")
                    .withMobilePhone("(11)111110000").withWorkPhone("33 33").withEmail1("test@test.com")
                    .withEmail2("a_jkfdg@gmail.com").withEmail3("q.q.d@mnf.ru"));

        }
        return contacts;
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact: contacts){
            writer.write(String.format("%s;%s\n", contact.getFirstName(), contact.getLastName()));
        }
        writer.close();
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }
}
