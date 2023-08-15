package br.com.coruja.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import br.com.coruja.domain.model.AlunoModel;
import br.com.coruja.domain.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AplicationController {

    @Autowired
    private AlunoRepository actions;

    @GetMapping(value = "{id}")
    public ResponseEntity<AlunoModel> find(@PathVariable Integer id) {
        try {
            Optional<AlunoModel> aluno = actions.findById(id);
            return new ResponseEntity<>(aluno.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<AlunoModel>> list() {
        List<AlunoModel> alunos = actions.findAll();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlunoModel> save(@RequestBody AlunoModel aluno) {
        return new ResponseEntity<>(actions.save(aluno), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AlunoModel> update(@RequestBody AlunoModel aluno) {
        try {
            if(actions.findById(aluno.getID()).isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(actions.save(aluno), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<AlunoModel> delete(@PathVariable Integer id) {
        try {
            Optional<AlunoModel> aluno = actions.findById(id);
            actions.deleteById(id);
            return new ResponseEntity<>(aluno.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll(){
        try {
            actions.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
