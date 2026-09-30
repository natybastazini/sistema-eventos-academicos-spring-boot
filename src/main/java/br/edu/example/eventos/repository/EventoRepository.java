package br.edu.example.eventos.repository;

import br.edu.example.eventos.model.Evento;
import br.edu.example.eventos.model.StatusEvento;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventoRepository {


    private List<Evento> eventos = new ArrayList<>();

    private Long contador = 1L;

    public List<Evento> listarTodos() {

        return eventos;

    }

    public void salvar(Evento evento) {

        evento.setId(contador++); // preciso setar o id e adicionar o contador
        eventos.add(evento); // preciso adicionar a lista de eventos o objeto evento

    }

    public Optional<Evento> buscarPorId(Long id) {

        return eventos.stream()
                .filter(evento -> evento.getId().equals(id)).findFirst();

        // tenho que pegar o id (getId()), depois comparar com o id (equals(id))
        // usar o findFirst() para retornar o 1º

    }

    public List<Evento> listarEventosAbertos() {

        return eventos.stream()
                .filter(evento -> evento.getStatus()== StatusEvento.INSCRICOES_ABERTAS).toList();

    }


}

