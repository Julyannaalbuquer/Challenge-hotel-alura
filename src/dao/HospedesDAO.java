package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Hospedes;
import modelo.Reservas;

public class HospedesDAO {
    private Connection connection;

    public HospedesDAO(Connection connection){
        this.connection=connection;
    }

    public void create(Hospedes hospede){
        String sql = "INSERT INTO hospedes (nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, hospede.getNome());
            preparedStatement.setString(2, hospede.getSobrenome());
            preparedStatement.setDate(3, new Date(hospede.getData_nascimento().getTime()));
            preparedStatement.setString(4, hospede.getNacionalidade());
            preparedStatement.setString(5, hospede.getTelefone());
            preparedStatement.setInt(6, hospede.getId_reserva());
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                while(resultSet.next()){
                    hospede.setId(resultSet.getInt(1));
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        } 
    }

    public List<Hospedes> read(){
        List<Hospedes> hospedes = new ArrayList<Hospedes>();
        String sql = "SELECT * FROM hospedes";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while(resultSet.next()){
                    Hospedes hospede = new Hospedes(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
                    hospedes.add(hospede);
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return hospedes;
    }

    public List<Hospedes> search(String sobrenome, Integer id_reserva){
        List<Hospedes> hospedes = new ArrayList<Hospedes>();
        String sql = "SELECT * FROM hospedes H JOIN reservas R ON H.id_reserva = R.id WHERE H.sobrenome = ? OR H.id_reserva = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, sobrenome);
            preparedStatement.setInt(2, id_reserva);
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while(resultSet.next()){
                    Reservas reserva = new Reservas(resultSet.getInt(8), resultSet.getDate(9), resultSet.getDate(10), resultSet.getDouble(11), resultSet.getString(12));
                    Hospedes hospede = new Hospedes(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), reserva);
                    hospedes.add(hospede);
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return hospedes;
    }

    public void delete(Integer row){
        String sql = "DELETE H, R FROM hospedes H JOIN reservas R ON H.id_reserva = R.id WHERE H.id_reserva = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, row);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateString(String coluna, String valor, Integer id){
        String sql = "UPDATE hospedes SET " + coluna + " = ? WHERE id = ? LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, valor);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateDate(Date date, int id){
        String sql = "UPDATE hospedes SET data_nascimento = ? WHERE id = ? LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setDate(1, new Date(date.getTime()));
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
