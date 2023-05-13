package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Reservas;

public class ReservasDAO {
    private Connection connection;

    public ReservasDAO(Connection connection){
        this.connection=connection;
    }

    public void create(Reservas reserva){
        String sql = "INSERT INTO reservas (data_entrada, data_saida, valor, forma_pagamento) VALUES (?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setDate(1, new Date(reserva.getData_entrada().getTime()));
            preparedStatement.setDate(2, new Date(reserva.getData_saida().getTime()));
            preparedStatement.setDouble(3, reserva.getValor());
            preparedStatement.setString(4, reserva.getForma_pagamento());
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                while(resultSet.next()){
                    reserva.setId(resultSet.getInt(1));
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Reservas> read(){
        List<Reservas> reservas = new ArrayList<Reservas>();
        String sql = "SELECT * FROM reservas";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while(resultSet.next()){
                    Reservas reserva = new Reservas(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3), resultSet.getDouble(4), resultSet.getString(5));
                    reservas.add(reserva);
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return reservas;
    }

    public void update(String valor, Integer id){
        String sql = "UPDATE reservas SET forma_pagamento = ? WHERE id = ? LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, valor);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(String coluna, Date date, Integer id){
        String sql = "UPDATE reservas SET " + coluna + " = ? WHERE id = ? LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setDate(1, new Date(date.getTime()));
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Double valor, Integer id){
        String sql = "UPDATE reservas SET valor = ? WHERE id = ? LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setDouble(1, valor);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
