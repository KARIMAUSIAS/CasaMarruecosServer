package com.casamarruecos.CasaMarruecos.api;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casamarruecos.CasaMarruecos.entity.EventoEntity;
import com.casamarruecos.CasaMarruecos.service.EventoService;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    EventoService oEventoService;

    @GetMapping("/{id}")
    public ResponseEntity<EventoEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<EventoEntity>(oEventoService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oEventoService.count(), HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<Page<EventoEntity>> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter,
            @RequestParam(name = "usuario", required = false) Long lUsuario) {
        return new ResponseEntity<Page<EventoEntity>>(oEventoService.getPage(oPageable, strFilter), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody EventoEntity oNewEventoEntity) {
        return new ResponseEntity<Long>(oEventoService.create(oNewEventoEntity), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Long> update(@RequestBody EventoEntity oEventoEntity) {
        return new ResponseEntity<Long>(oEventoService.update(oEventoEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oEventoService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<EventoEntity> generate() {
        return new ResponseEntity<EventoEntity>(oEventoService.generate(), HttpStatus.OK);
    }

    @PostMapping("/generate/{amount}")
    public ResponseEntity<Long> generateSome(@PathVariable(value = "amount") Integer amount) {
        return new ResponseEntity<>(oEventoService.generateSome(amount), HttpStatus.OK);
    }
    
}

