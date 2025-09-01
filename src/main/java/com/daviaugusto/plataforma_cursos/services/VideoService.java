package com.daviaugusto.plataforma_cursos.services;

import com.daviaugusto.plataforma_cursos.infrastructure.entitys.entidadesMux.Data;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {


    @Autowired
    VideoRepository videoRepository;


    public Data buscarVideo(Long id){
        Data data = videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Video não encontrado"));
        return data;
    }


}
