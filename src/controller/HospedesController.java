package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.HospedesDAO;
import factory.ConnectionFactory;
import modelo.Hospedes;

public class HospedesController {
    private HospedesDAO hospedesDAO;

    public HospedesController(){
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.hospedesDAO = new HospedesDAO(connection);
    }

    public void registrarHospede(Hospedes hospede){
        this.hospedesDAO.create(hospede);
    }

    public List<String[]> listarHospedes(){
        List<String[]> hospedesString = new ArrayList<>();
        hospedesDAO.read().forEach(hospede -> {
            hospedesString.add(hospede.toListString());
        });
        return hospedesString;
    }

    public List<Hospedes> buscar(String buscaString){
        Integer buscaNumerico;
        try{
            buscaNumerico = Integer.parseInt(buscaString);
        } catch(NumberFormatException e){
            buscaNumerico = 0;
        }
        return hospedesDAO.search(buscaString, buscaNumerico);
    }

    public void deletar(String registro){
        this.hospedesDAO.delete(Integer.parseInt(registro));
    }

    public boolean editar(String coluna, String valor, String id){
        if(coluna.equals("Nascimento")){
            Date date;
            try{
                date = Date.valueOf(valor);
            } catch(IllegalArgumentException e){
                return false;
            }
            hospedesDAO.updateDate(date, Integer.parseInt(id));
        } else{
            hospedesDAO.updateString(coluna, valor, Integer.parseInt(id));
        }
        return true;
    }
}
