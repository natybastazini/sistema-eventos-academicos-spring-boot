package br.edu.example.eventos.controller;

import br.edu.example.eventos.model.Evento;
import br.edu.example.eventos.model.TipoEvento;
import br.edu.example.eventos.repository.EventoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    private final EventoRepository repository;

    public EventoController(EventoRepository repository) {
        this.repository =  repository;
    }

    @GetMapping
    public String listarTodos(Model model) {

        model.addAttribute("eventos", repository.listarTodos());

        return "eventos/lista";
    }

    @GetMapping("/novo")
    public String criarEvento(Model model) {

        model.addAttribute("evento", new Evento());
        model.addAttribute("tipo", TipoEvento.values());

        return "eventos/formulario";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("evento") Evento evento, BindingResult result, Model model) {

        if (result.hasErrors()){
            model.addAttribute("tipo", TipoEvento.values());
            return "eventos/formulario";
        }

        repository.salvar(evento);
        return  "redirect:/eventos";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {

        model.addAttribute("evento", repository.buscarPorId(id).orElseThrow());

        return "eventos/detalhes";
    }

    @PostMapping("/{id}/abrir")
    public String abrir(@PathVariable Long id) {

        Evento evento = repository.buscarPorId(id).orElseThrow();
        evento.abrirInscricoes();

        return "redirect:/eventos";

    }

    @PostMapping("/{id}/encerrar")
    public String encerrar(@PathVariable Long id) {

        Evento evento = repository.buscarPorId(id).orElseThrow();
        evento.encerrar();

        return "redirect:/eventos";

    }

    @PostMapping("/{id}/cancelar")
    public String cancelar(@PathVariable Long id) {

        Evento evento = repository.buscarPorId(id).orElseThrow();
        evento.cancelar();

        return "redirect:/eventos";

    }

}
