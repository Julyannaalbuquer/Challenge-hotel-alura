package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.ReservasDAO;
import factory.ConnectionFactory;
import modelo.Reservas;

public class ReservaController {
    
    private ReservasDAO reservasDAO;

    public ReservaController(){
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.reservasDAO = new ReservasDAO(connection);
    }

    public void registrarReserva(Reservas reserva){
        this.reservasDAO.create(reserva);
    }

    public List<String[]> listarReservas(){
        List<String[]> reservasString = new ArrayList<>();
        reservasDAO.read().forEach(reserva -> {
            reservasString.add(reserva.toListString());
        });
        return reservasString;
    }

    public boolean editar(String coluna, String valor, String id){
        String atributo = null;
        if(coluna.equals("Data Check In")){
            atributo = "data_entrada";
        } else if(coluna.equals("Data Check Out")){
            atributo = "data_saida";
        } else{
            reservasDAO.update(valor, Integer.parseInt(id));
            return true;
        }
        Date date;
        try{
            date = Date.valueOf(valor);
        } catch(IllegalArgumentException e){
            return false;
        }
        reservasDAO.update(atributo, date, Integer.parseInt(id));
        return true;
    }

    public void editar(Double valor, String id){
        reservasDAO.update(valor, Integer.parseInt(id));
    }
}
