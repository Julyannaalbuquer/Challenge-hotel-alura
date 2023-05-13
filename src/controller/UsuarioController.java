package controller;

import java.sql.*;

import dao.UsuariosDAO;
import factory.ConnectionFactory;
import modelo.Usuarios;

public class UsuarioController {
    
    private UsuariosDAO usuariosDAO;

    public UsuarioController(){
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.usuariosDAO = new UsuariosDAO(connection);
    }

    public boolean validarSenha(Usuarios usuario){
        if(usuario.getSenha().equals(usuariosDAO.getPassword(usuario))){
            return true;
        } 
        return false;
    }
}
