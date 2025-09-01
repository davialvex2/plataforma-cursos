package com.daviaugusto.plataforma_cursos.controller;

import com.daviaugusto.plataforma_cursos.infrastructure.entitys.entidadesMux.DadosSaida;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.entidadesMux.DadosVideoEntrada;
import com.daviaugusto.plataforma_cursos.services.MuxClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private MuxClientService service;


    @PostMapping
    ResponseEntity<DadosSaida> inserirVideo(@RequestBody DadosVideoEntrada entrada){
        return ResponseEntity.ok(service.inserirVideo(entrada));
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosSaida> buscarVideo(@PathVariable String id){
        return ResponseEntity.ok(service.buscarVideoId(id));
    }

}
