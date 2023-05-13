package dao;

import java.sql.*;

import modelo.Usuarios;

public class UsuariosDAO {
    private Connection connection;

    public UsuariosDAO(Connection connection){
        this.connection=connection;
    }

    public String getPassword(Usuarios usuario){
        String password = null;
        String sql = "SELECT senha FROM usuarios WHERE usuario = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, usuario.getUsuario());
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while(resultSet.next()){
                    password = resultSet.getString(1);
                    return password;
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return password;
    }
}
