package br.ufscar.dc.dsw.domain;

import java.time.LocalDateTime;

public class Locacao {
    private long id;
    private LocalDateTime datahora;
    private long clienteId;
    private long locadoraId;


    public Locacao(long id, LocalDateTime datahora, long clienteId, long locadoraId) {
        this.id = id;
        this.datahora = datahora;
        this.clienteId = clienteId;
        this.locadoraId = locadoraId;
    }


    public long getLocadoraId() {
        return locadoraId;
    }

    public LocalDateTime getDatahora() {
        return datahora;
    }

    public long getClienteId() {
        return clienteId;
    }

    public long getId() {
        return id;
    }
}