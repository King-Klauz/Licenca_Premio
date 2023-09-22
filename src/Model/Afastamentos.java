package Model;

import java.time.LocalDate;

public class Afastamentos {
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer dias;
    private String acao;

    public Afastamentos(LocalDate dataInicio, LocalDate dataFim, Integer dias, String acao) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dias = dias;
        this.acao = acao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    @Override
    public String toString() {
        return "Afastamentos{" +
                "dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", dias=" + dias +
                ", acao='" + acao + '\'' +
                '}';
    }
}
