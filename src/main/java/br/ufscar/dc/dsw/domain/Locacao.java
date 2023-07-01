package br.ufscar.dc.dsw.domain;

import java.time.LocalDateTime;

public class Locacao {
    private int id;
    private LocalDateTime datahora;
    private int clienteId;
    private int locadoraId;


    public Locacao(int id, LocalDateTime datahora, int clienteId, int locadoraId) {
        this.id = id;
        this.datahora = datahora;
        this.clienteId = clienteId;
        this.locadoraId = locadoraId;
    }


    public int getLocadoraId() {
        return locadoraId;
    }

    public LocalDateTime getDatahora() {
        return datahora;
    }

    public int getClienteId() {
        return clienteId;
    }

    public int getId() {
        return id;
    }
}
