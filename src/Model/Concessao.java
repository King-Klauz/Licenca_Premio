package Model;

import java.time.LocalDate;

public class Concessao {
    private int numConcessao;

    private LocalDate dataInicio;

    private LocalDate dataFim;
    private int saldoConcessao;
    private LocalDate dataIngOrgaoFormatada;
    private int codigoLicenca;
    private String licenca;

    public Concessao(int numConcessao, LocalDate dataInicio, LocalDate dataFim, int saldoConcessao, LocalDate dataIngOrgaoFormatada, int codigoLicenca, String licenca) {
        this.numConcessao = numConcessao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.saldoConcessao = saldoConcessao;
        this.dataIngOrgaoFormatada = dataIngOrgaoFormatada;
        this.codigoLicenca = codigoLicenca;
        this.licenca = licenca;
    }

    public int getNumConcessao() {
        return numConcessao;
    }

    public void setNumConcessao(int numConcessao) {
        this.numConcessao = numConcessao;
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

    public int getSaldoConcessao() {
        return saldoConcessao;
    }

    public void setSaldoConcessao(int saldoConcessao) {
        this.saldoConcessao = saldoConcessao;
    }

    public LocalDate getDataIngOrgaoFormatada() {
        return dataIngOrgaoFormatada;
    }

    public void setDataIngOrgaoFormatada(LocalDate dataIngOrgaoFormatada) {
        this.dataIngOrgaoFormatada = dataIngOrgaoFormatada;
    }

    public int getCodigoLicenca() {
        return codigoLicenca;
    }

    public void setCodigoLicenca(int codigoLicenca) {
        this.codigoLicenca = codigoLicenca;
    }

    public String getLicenca() {
        return licenca;
    }

    public void setLicenca(String licenca) {
        this.licenca = licenca;
    }

    @Override
    public String toString() {
        return "Concessao{" +
                "numConcessao=" + numConcessao +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", saldoConcessao=" + saldoConcessao +
                ", dataIngOrgaoFormatada=" + dataIngOrgaoFormatada +
                ", codigoLicenca=" + codigoLicenca +
                ", licenca='" + licenca + '\'' +
                '}';
    }
}
