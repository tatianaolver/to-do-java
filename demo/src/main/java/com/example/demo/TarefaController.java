package com.example.demo;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin
public class TarefaController {
    
    @Autowired
    private TarefaService service;

    @GetMapping(value="/tarefas", produces={"application/json","application/xml"})
	public ResponseEntity<Collection<Tarefa>> getTasks() {
		Collection<Tarefa> result = service.getTarefas();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value="/tarefa/{id}", produces={"application/json","application/xml"})
	public ResponseEntity<Tarefa> getItem(@PathVariable int id) {
		Tarefa result = service.getTarefa(id);
		if (service.getTarefa(id) == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok().body(result);
	}

	@PostMapping(value="/tarefa",
			consumes={"application/json","application/xml"},
			produces={"application/json","application/xml"})
	public ResponseEntity<Tarefa> addTarefa(@RequestBody Tarefa tarefa) {
		service.insert(tarefa);
		URI uri = URI.create("/tarefa/" + tarefa.getId());
		return ResponseEntity.created(uri).body(tarefa);
	}

	@PutMapping(value="/tarefa/{id}", consumes={"application/json","application/xml"})
	public ResponseEntity modifyTarefa(@PathVariable int id, @RequestBody Tarefa tarefa) {
		if (service.getTarefa(id) == null)
			return ResponseEntity.notFound().build();
		else {
			service.update(tarefa);
			return ResponseEntity.ok().build();
		}
	}

    @DeleteMapping("/tarefa/{id}")
	public ResponseEntity deleteTarefa(@PathVariable int id) {
		if (service.getTarefa(id) == null)
			return ResponseEntity.notFound().build();
		else {
			service.delete(id);
			return ResponseEntity.ok().build();
		}
	}
}