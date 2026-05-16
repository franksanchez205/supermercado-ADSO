package com.supermercado.supermercado.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.supermercado.supermercado.dtos.UserRequestDTO;
import com.supermercado.supermercado.dtos.UserResponseDTO;
import com.supermercado.supermercado.models.Users;
import com.supermercado.supermercado.repositories.UsersRepository;
import com.supermercado.supermercado.security.Permission;
import com.supermercado.supermercado.security.UserRole;
import com.supermercado.supermercado.services.AuthorizationService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorizationService authorizationService;

    @GetMapping
    public List<UserResponseDTO> findAll(HttpServletRequest request) {
        authorizationService.requirePermission(request, Permission.USERS_READ);
        return usersRepository.findAll().stream().map(this::toResponse).toList();
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO requestBody, HttpServletRequest request) {
        authorizationService.requirePermission(request, Permission.USERS_CREATE);
        validateRole(requestBody.getRolId());

        usersRepository.findByUsername(requestBody.getUsername()).ifPresent(user -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este nombre de usuario ya esta en uso");
        });

        Users user = new Users();
        user.setUsername(requestBody.getUsername());
        user.setPassword(passwordEncoder.encode(requestBody.getPassword()));
        user.setRolId(requestBody.getRolId());

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(usersRepository.save(user)));
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable Long id, @RequestBody UserRequestDTO requestBody,
            HttpServletRequest request) {
        authorizationService.requirePermission(request, Permission.USERS_UPDATE);
        validateRole(requestBody.getRolId());

        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        user.setUsername(requestBody.getUsername());
        if (requestBody.getPassword() != null && !requestBody.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(requestBody.getPassword()));
        }
        user.setRolId(requestBody.getRolId());

        return toResponse(usersRepository.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {
        authorizationService.requirePermission(request, Permission.USERS_DELETE);
        if (!usersRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        usersRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private UserResponseDTO toResponse(Users user) {
        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRolId(user.getRolId());
        return response;
    }

    private void validateRole(String rolId) {
        
        try {
            UserRole.fromId(String.valueOf(rolId));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol no valido");
        }
    }
}
