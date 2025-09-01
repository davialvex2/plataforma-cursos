package com.daviaugusto.plataforma_cursos.clients;


import com.daviaugusto.plataforma_cursos.infrastructure.entitys.entidadesMux.DadosSaida;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.entidadesMux.DadosVideoEntrada;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "muxClient", url = "https://api.mux.com/video/v1/assets")
public interface MuxClient {


    @PostMapping
    DadosSaida inserirVideo(@RequestHeader ("Authorization") String token, @RequestBody DadosVideoEntrada dadosEntrada);

    @GetMapping("/{id}")
    DadosSaida buscarVideo(@RequestHeader ("Authorization") String token, @PathVariable String id);



}
