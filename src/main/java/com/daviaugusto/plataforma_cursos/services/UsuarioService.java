package com.daviaugusto.plataforma_cursos.services;

import com.daviaugusto.plataforma_cursos.infrastructure.dtos.request.AlunoRequest;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.request.ProfessorRequest;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.request.UsuarioRequest;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.response.UsuarioResponse;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Aluno;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Professor;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Usuario;
import com.daviaugusto.plataforma_cursos.infrastructure.enums.RoleEnum;
import com.daviaugusto.plataforma_cursos.infrastructure.exceptions.ResourceNotFoundException;
import com.daviaugusto.plataforma_cursos.infrastructure.mapper.UsuarioConverter;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.AlunoRepository;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.ProfessorRepository;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.UsuarioRepository;
import com.daviaugusto.plataforma_cursos.infrastructure.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.daviaugusto.plataforma_cursos.infrastructure.exceptions.IllegalArgumentException;

import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UsuarioConverter usuarioConverter;



    public UsuarioResponse salvarUsuario(UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioConverter.paraUsuario(usuarioRequest);
        verificarEmail(usuario.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        if(usuario.getRole() == RoleEnum.ALUNO){
            usuario.getAluno().setUsuario(usuario);
        }
        if(usuario.getRole() == RoleEnum.PROFESSOR){
            usuario.getProfessor().setUsuario(usuario);
        }
        Usuario user = usuarioRepository.save(usuario);

        return usuarioConverter.paraUsuarioResponse(user);
    }

    public void verificarEmail(String email){
        try {
            boolean existe = buscarEmail(email);
            if(existe){
                throw new IllegalArgumentException("Email já cadastrado");
            }
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Email já cadastrado");
        }
    }

    public boolean buscarEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }



    public UsuarioResponse atualizarUsuario(UsuarioRequest usuarioRequest){
        Usuario user = usuarioConverter.paraUsuario(usuarioRequest);
        user = usuarioRepository.findByEmail(user.getEmail()).orElseThrow(() ->  new RuntimeException("Usuario não encontrado"));
        if(user.getProfessor() == null){
            Aluno aluno = atualizarAluno(usuarioRequest.getAluno(), user.getAluno());
            user.setAluno(aluno);
            usuarioRepository.save(user);
        }
        else{
            Professor prof = atualizarProfessor(usuarioRequest.getProfessor(), user.getProfessor());
            user.setProfessor(prof);
            usuarioRepository.save(user);
        }
        return usuarioConverter.paraUsuarioResponse(user);
    }

    public void excluirUsuario(Long id){
        Usuario user = usuarioRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Usuario não encontrado"));
        usuarioRepository.delete(user);
    }

    public UsuarioResponse buscarUsuarioPorEmail(String email){
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return usuarioConverter.paraUsuarioResponse(usuario);
    }

    public UsuarioResponse alterarEmail(String token, String email){
        String emailToken = jwtUtil.extrairEmailToken(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(emailToken).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        usuario.setEmail(email);
        return usuarioConverter.paraUsuarioResponse(usuarioRepository.save(usuario));
    }

    public UsuarioResponse alterarSenha(String token, String senha){
        String emailToken = jwtUtil.extrairEmailToken(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(emailToken).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        usuario.setSenha(passwordEncoder.encode(senha));
        return usuarioConverter.paraUsuarioResponse(usuarioRepository.save(usuario));
    }


    public Aluno atualizarAluno(AlunoRequest aluno, Aluno alunoRepository){
        alunoRepository.setNome(aluno.getNome() !=null ? aluno.getNome() : alunoRepository.getNome());
        alunoRepository.setDataNascimento(aluno.getDataNascimento()  !=null ? aluno.getDataNascimento() : alunoRepository.getDataNascimento());
        alunoRepository.setMatricula(aluno.getMatricula() !=null ? aluno.getMatricula() : alunoRepository.getMatricula());
        return alunoRepository;
    }

    public Professor atualizarProfessor(ProfessorRequest professor, Professor professorRepository){
        professorRepository.setNome(professor.getNome() != null ? professor.getNome() : professorRepository.getNome());
        professorRepository.setDataNascimento(professor.getDataNascimento() != null ? professor.getDataNascimento() : professorRepository.getDataNascimento());
        professorRepository.setEspecialidade(professor.getEspecialidade() != null ? professor.getEspecialidade() : professorRepository.getEspecialidade());
        return professorRepository;
    }


}
