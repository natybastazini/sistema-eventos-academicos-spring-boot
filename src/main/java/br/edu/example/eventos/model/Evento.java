package br.edu.example.eventos.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Evento {

    private Long id;

    @NotBlank(message = "Informe o título")
    @Size(min = 5, max = 100, message = "O título deve conter entre 5 e 100 caracteres")
    private String titulo;

    @NotBlank(message = "Informe a descrição")
    @Size(min = 10, message = "A descrição deve conter no mínimo 10 caracteres")
    private String descricao;

    @NotBlank(message = "Informe o responsável")
    @Size(min = 3, max = 30, message = "O responsável deve conter entre 10 caracteres")
    private String responsavel;

    @NotBlank(message = "Informe o e-mail")
    @Email(message = "Informe um e-mail válido")
    private String emailResponsavel;

    @NotNull(message = "Informe o tipo")
    private TipoEvento tipo;

    @NotNull(message = "Informe o número de vagas")
    @Positive(message = "O número de vagas deve ser maior que zero")
    private Long numeroVagas;

    private StatusEvento status;

    public Evento() {

        this.status = StatusEvento.PLANEJADO;

    }

    public void abrirInscricoes() {

        if (status == StatusEvento.PLANEJADO) {
            status = StatusEvento.INSCRICOES_ABERTAS;
        }

    }

    public void encerrar() {

        if (status == StatusEvento.INSCRICOES_ABERTAS) {
            status = StatusEvento.ENCERRADO;
        }
    }

    public void cancelar() {

        if (status == StatusEvento.PLANEJADO || status == StatusEvento.INSCRICOES_ABERTAS) {
            status = StatusEvento.CANCELADO;
        }

    }

}
