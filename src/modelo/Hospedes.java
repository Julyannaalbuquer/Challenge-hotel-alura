package modelo;

import java.util.Date;

public class Hospedes {
    private Integer id;
    private String nome;
    private String sobrenome;
    private Date data_nascimento;
    private String nacionalidade;
    private String telefone;
    private Integer id_reserva;
    private Reservas reserva;

    public Hospedes(String nome, String sobrenome, Date data_nascimento, String nacionalidade, String telefone, Integer id_reserva){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.data_nascimento = data_nascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.id_reserva = id_reserva;
    }

    public Hospedes(Integer id, String nome, String sobrenome, Date data_nascimento, String nacionalidade, String telefone, Integer id_reserva){
        this.id=id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.data_nascimento = data_nascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.id_reserva = id_reserva;
    }

    public Hospedes(Integer id, String nome, String sobrenome, Date data_nascimento, String nacionalidade, String telefone, Integer id_reserva, Reservas reserva){
        this.id=id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.data_nascimento = data_nascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.id_reserva = id_reserva;
        this.reserva=reserva;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public Integer getId_reserva() {
        return id_reserva;
    }

    public Reservas getReserva() {
        return reserva;
    }

    public String[] toListString() {
        String[] string = {id.toString(), nome, sobrenome, data_nascimento.toString(), nacionalidade, telefone, id_reserva.toString()};
        return string;
    }
}
