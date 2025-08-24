package com.daviaugusto.plataforma_cursos.services;

import com.daviaugusto.plataforma_cursos.clients.MuxClient;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.DadosSaida;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.DadosVideoEntrada;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.PlaybackId;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.PlaybackIdRepository;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class MuxClientService {


    @Autowired
    private MuxClient muxClient;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private PlaybackIdRepository playbackIdRepository;


    public DadosSaida inserirVideo(DadosVideoEntrada entrada){
        String token = gerarToken();
        DadosSaida dadosSaida = muxClient.inserirVideo(token, entrada);
        videoRepository.save(dadosSaida.getData());
        playbackIdRepository.save(dadosSaida.getData().getPlayback_ids().get(0));
        return dadosSaida;
    }

    public DadosSaida buscarVideoId(String id){
        String token = gerarToken();
        DadosSaida dadosSaida = muxClient.buscarVideo(token, id);
        return dadosSaida;
    }

    public String gerarToken(){

        String userNamefixo =  "eb62650e-1a32-4b85-8afb-7abfeff21de1";

        String passwordfixo = "OmbYkSvyun3mwrliokcjlmvS4bFtE0RFwjJDi0+CdotCM7GurPZTL/uvZfyEaz1S0CHF200vrWm";

        String auth = userNamefixo + ":" + passwordfixo;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        return "Basic " + encodedAuth;
    }




}
