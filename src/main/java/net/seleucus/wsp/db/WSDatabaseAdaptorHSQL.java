package net.seleucus.wsp.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class WSDatabaseAdaptorHSQL implements WSDatabaseAdaptor {
	
    private static final String HSQLDB_JDBC_DRIVER = "org.hsqldb.jdbcDriver";
    private static final String PROTOCOL = "jdbc:hsqldb:";
    
    private Connection connection;
    private Statement statement;
    
    public WSDatabaseAdaptorHSQL(final String dbPath) throws ClassNotFoundException, SQLException {
    	
    	Class.forName(HSQLDB_JDBC_DRIVER);
    	
    	Properties props = new Properties();

		props.put("user", "sa");
		props.put("password", "");
        props.put("jdbc.strict_md", "false");
		props.put("jdbc.get_column_name", "false");
		
		connection = DriverManager.getConnection(PROTOCOL + dbPath + ";create=true", props);
		connection.setAutoCommit(false);
	
		statement = connection.createStatement();
		
    }
    
    public void shutdown() throws SQLException {
    	
    	statement.execute("SHUTDOWN");
    	statement.close();
    	connection.commit();
    	connection.close();
    	
    }
    
    public void createSchema() throws SQLException {
    	
    	// statement.execute("CREATE TABLE Users()");
    	statement.execute("CREATE TABLE Passwords(pwID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL, password VARCHAR(255) NOT NULL), PRIMARY KEY(pwID)");
    	// statement.execute("CREATE TABLE ActionsAvaiable()");
    	// statement.execute("CREATE TABLE ActionsReceived()");

    }

    @Override
    public void dropAllObjects() throws SQLException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void initDB(String dbPath) throws ClassNotFoundException, SQLException, IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void loadDB(String dbPath) throws ClassNotFoundException, SQLException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
