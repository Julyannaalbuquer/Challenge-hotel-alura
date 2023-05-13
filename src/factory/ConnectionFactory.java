package factory;

import java.sql.*;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;

public class ConnectionFactory {

    public DataSource dataSource;

    public ConnectionFactory(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/desafio_hotel");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("Public.1");
        comboPooledDataSource.setMaxPoolSize(10);
        this.dataSource = comboPooledDataSource;
    }

    public Connection recuperarConexao(){    
        try{
            return this.dataSource.getConnection();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
