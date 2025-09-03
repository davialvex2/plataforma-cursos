package com.daviaugusto.plataforma_cursos.services;

import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Aluno;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Professor;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Usuario;
import com.daviaugusto.plataforma_cursos.infrastructure.enums.RoleEnum;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.AlunoRepository;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.ProfessorRepository;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.UsuarioRepository;
import com.daviaugusto.plataforma_cursos.infrastructure.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;



    public Usuario salvarUsuario(Usuario usuario) {
        verificarEmail(usuario.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        if(usuario.getRole() == RoleEnum.ALUNO){
            usuario.getAluno().setUsuario(usuario);
        }
        if(usuario.getRole() == RoleEnum.PROFESSOR){
            usuario.getProfessor().setUsuario(usuario);
        }
        Usuario user = usuarioRepository.save(usuario);
        return user;
    }

    public boolean verificarEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuario(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(() ->  new RuntimeException("Usuario não encontrado"));
    }

    public Usuario atualizarUsuario(Usuario usuario){
        Usuario user =usuarioRepository.findByEmail(usuario.getEmail()).orElseThrow(() ->  new RuntimeException("Usuario não encontrado"));
        if(user.getProfessor() == null){
            Aluno aluno = atualizarAluno(usuario.getAluno(), user.getAluno());
            user.setAluno(aluno);
            usuarioRepository.save(user);
        }
        else{
            Professor prof = atualizarProfessor(usuario.getProfessor(), user.getProfessor());
            user.setProfessor(prof);
            usuarioRepository.save(user);
        }
        return user;
    }

    public void excluirUsuario(Long id){
        Usuario user = usuarioRepository.findById(id).orElseThrow(() ->  new RuntimeException("Usuario não encontrado"));
        usuarioRepository.delete(user);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return usuario;
    }

    public Usuario alterarEmail(String token, String email){
        String emailToken = jwtUtil.extrairEmailToken(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(emailToken).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setEmail(email);
        return usuarioRepository.save(usuario);
    }

    public Usuario alterarSenha(String token, String senha){
        String emailToken = jwtUtil.extrairEmailToken(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(emailToken).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setSenha(passwordEncoder.encode(senha));
        return usuarioRepository.save(usuario);
    }


    public Aluno atualizarAluno(Aluno aluno, Aluno alunoRepository){
        alunoRepository.setNome(aluno.getNome() !=null ? aluno.getNome() : alunoRepository.getNome());
        alunoRepository.setDataNascimento(aluno.getDataNascimento()  !=null ? aluno.getDataNascimento() : alunoRepository.getDataNascimento());
        alunoRepository.setMatricula(aluno.getMatricula() !=null ? aluno.getMatricula() : alunoRepository.getMatricula());
        return alunoRepository;
    }

    public Professor atualizarProfessor(Professor professor, Professor professorRepository){
        professorRepository.setEspecialidade(professor.getNome() != null ? professor.getNome() : professorRepository.getNome());
        professorRepository.setDataNascimento(professor.getDataNascimento() != null ? professor.getDataNascimento() : professorRepository.getDataNascimento());
        professorRepository.setEspecialidade(professor.getEspecialidade() != null ? professor.getEspecialidade() : professorRepository.getEspecialidade());
        return professorRepository;
    }


}
