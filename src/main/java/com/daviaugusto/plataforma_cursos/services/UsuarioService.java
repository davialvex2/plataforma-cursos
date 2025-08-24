package com.daviaugusto.plataforma_cursos.services;

import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Usuario;
import com.daviaugusto.plataforma_cursos.infrastructure.enums.RoleEnum;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.AlunoRepository;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.ProfessorRepository;
import com.daviaugusto.plataforma_cursos.infrastructure.repositories.UsuarioRepository;
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

}
