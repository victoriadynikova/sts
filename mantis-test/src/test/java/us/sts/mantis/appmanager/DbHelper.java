package us.sts.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

  /*  public List<String> users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<String> result = session.createQuery("from mantis_user_table").list();
        session.getTransaction().commit();
        session.close();
        return new ArrayList<String>(result) {
        };
    }
    */

  /*  public static List<String> users() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?" + "user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select username from mantis_user_table");
            ArrayList<String> users = new ArrayList<String>();
            while (rs.next()) {
                users.add(new String(rs.getString("username")));
            }
            rs.close();
            st.close();
            conn.close();


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return users();
    }
    */
}



