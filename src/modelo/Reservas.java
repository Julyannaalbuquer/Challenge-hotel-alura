package modelo;

import java.util.Date;

public class Reservas {
    private Integer id;
    private Date data_entrada;
    private Date data_saida;
    private Double valor;
    private String forma_pagamento;

    public Reservas(Date data_entrada, Date data_saida, Double valor, String forma_pagamento){
        this.data_entrada=data_entrada;
        this.data_saida=data_saida;
        this.valor=valor;
        this.forma_pagamento=forma_pagamento;
    }

    public Reservas(Integer id, Date data_entrada, Date data_saida, Double valor, String forma_pagamento){
        this.id=id;
        this.data_entrada=data_entrada;
        this.data_saida=data_saida;
        this.valor=valor;
        this.forma_pagamento=forma_pagamento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Date getData_entrada() {
        return data_entrada;
    }

    public Date getData_saida() {
        return data_saida;
    }

    public Double getValor() {
        return valor;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }
    
    public String[] toListString() {
        String[] string = {id.toString(), data_entrada.toString(), data_saida.toString(), valor.toString(), forma_pagamento.toString()};
        return string;
    }
}
