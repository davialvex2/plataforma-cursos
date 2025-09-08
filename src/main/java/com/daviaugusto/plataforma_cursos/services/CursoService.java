package com.daviaugusto.plataforma_cursos.services;


import com.daviaugusto.plataforma_cursos.infrastructure.dtos.request.CursoRequest;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.response.CursoResponse;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Curso;

import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Aluno;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.entidadesMux.Data;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Usuario;
import com.daviaugusto.plataforma_cursos.infrastructure.mapper.CursoConverter;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.CursoRepository;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.UsuarioRepository;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.VideoRepository;
import com.daviaugusto.plataforma_cursos.infrastructure.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CursoService {


    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CursoConverter cursoConverter;


    public CursoResponse salvarCurso(CursoRequest cursoRequest, String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        Usuario user = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if(Objects.isNull(cursoRequest)){
            throw new RuntimeException("Dados do curso estão invalidos");
        }
        Curso curso = cursoConverter.paraCurso(cursoRequest);
        curso.setProfessor(user.getProfessor());
        return cursoConverter.paraCursoResponse(cursoRepository.save(curso));
    }

    public void inscreverAluno(Long id, String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        Usuario user = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        List<Aluno> lista = curso.getAlunos();
        lista.add(user.getAluno());
        curso.setAlunos(lista);
        cursoRepository.save(curso);
    }


    public void adicionarVideo(Long idCurso, Long idVideo, String token){
        Data data = videoRepository.findById(idVideo).orElseThrow(() -> new RuntimeException("Video não encontrado"));
        Curso curso = cursoRepository.findById(idCurso).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        List<Data> lista = curso.getVideos();
        lista.add(data);
        curso.setVideos(lista);
        cursoRepository.save(curso);
    }

    public List<Data> buscarVideos(Long id){
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        if(curso.getVideos().isEmpty()){
            throw new RuntimeException("O curso não possui nenhuma aula");
        }
        return curso.getVideos();
    }


    public CursoResponse buscarCursoPorId(Long id){
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        return cursoConverter.paraCursoResponse(curso);
    }

    public List<CursoResponse> buscarCursosIncritos(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<Curso> cursos = usuario.getAluno().getCursos();
        if(cursos.isEmpty()){
            throw new RuntimeException("O aluno nã está inscrito em nenhum curso");
        }
        return cursoConverter.paraListaCursoResponse(cursos);
    }


}
