package com.daviaugusto.plataforma_cursos.controller;

import com.daviaugusto.plataforma_cursos.infrastructure.dtos.request.LoginRequest;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.request.UsuarioRequest;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.response.UsuarioResponse;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Usuario;
import com.daviaugusto.plataforma_cursos.infrastructure.security.JwtUtil;
import com.daviaugusto.plataforma_cursos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;


    @PostMapping("/login")
    public String login (@RequestBody LoginRequest login){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.email(), login.senha()));
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }


    @PostMapping
    public ResponseEntity<UsuarioResponse> salvarUsuario(@RequestBody UsuarioRequest usuarioRequest){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioRequest));
    }

    @PreAuthorize("hasRole('PROFESSOR', 'ALUNO')")
    @PutMapping
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@RequestBody UsuarioRequest usuario){
        return ResponseEntity.ok(usuarioService.atualizarUsuario(usuario));
    }

    @PreAuthorize("hasRole('PROFESSOR', 'ALUNO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id){
        usuarioService.excluirUsuario(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('PROFESSOR', 'ALUNO')")
    @GetMapping
    public ResponseEntity<UsuarioResponse> buscarUsuario(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @PreAuthorize("hasRole('PROFESSOR', 'ALUNO')")
    @PatchMapping
    public ResponseEntity<UsuarioResponse> alterarEmail(@RequestHeader("Authorization") String token,
                                                @RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.alterarEmail(token, email));
    }

    @PreAuthorize("hasRole('PROFESSOR', 'ALUNO')")
    @PatchMapping("/{senha}")
    public ResponseEntity<UsuarioResponse> alterarSenha(@RequestHeader("Authorization") String token,
                                                @PathVariable String senha){
        return ResponseEntity.ok(usuarioService.alterarSenha(token, senha));
    }


}
