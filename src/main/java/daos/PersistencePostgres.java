package daos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PersistencePostgres {
    private Connection connection;
    private String table;
    private static PersistencePostgres instance;

    private PersistencePostgres(){
        this.connect();
    }

    public static PersistencePostgres getInstance(){
        if (instance == null) {
            instance = new PersistencePostgres();
        }
        return instance;
    }

    private void connect() {
        try {
            Properties props = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("jdbc.properties");
            props.load(input);

            String host = props.getProperty("host");
            String port = props.getProperty("port");
            String driver = props.getProperty("driver");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            String dbname = props.getProperty("dbname");
            String schema = props.getProperty("schema");

            StringBuilder builder = new StringBuilder("jdbc:postgresql://");
            builder.append(host);
            builder.append(":");
            builder.append(port);
            builder.append("/");
            builder.append(dbname);
            builder.append("?user=");
            builder.append(username);
            builder.append("&password=");
            builder.append(password);
            builder.append("&currentSchema=");
            builder.append(schema);

            Class.forName(driver);
            this.connection = DriverManager.getConnection(builder.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getTable() {
        return this.table;
    }

    public void setTable( String table ) {
        this.table = table;
    }

    public DatasourceCRUD communicate(){
        DatasourceCRUD datasourceCRUD = null;
        if (getTable() == "user") {
            datasourceCRUD = new userDAO(this.connection);
        }
        return datasourceCRUD;
    }

}
