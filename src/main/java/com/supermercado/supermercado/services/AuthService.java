package com.supermercado.supermercado.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.supermercado.supermercado.dtos.LoginRequestDTO;
import com.supermercado.supermercado.dtos.LoginResponseDTO;
import com.supermercado.supermercado.dtos.MessageResponseDTO;
import com.supermercado.supermercado.dtos.RefreshTokenResponseDTO;
import com.supermercado.supermercado.dtos.RegisterRequestDTO;
import com.supermercado.supermercado.models.Users;
import com.supermercado.supermercado.repositories.UsersRepository;
import com.supermercado.supermercado.security.Roles;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UsersRepository usersRepository;

    private final JwtService jwtService;

    /**
     * Metodo que permite registrar un nuevo usuario
     * 
     * @param request
     * @return
     */
    public MessageResponseDTO register(RegisterRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage("Registro exitoso");

        // Validamos que el rol sea válido a traves de la clase UserRole

        // Si el rol no es válido, se lanzará una excepción
        try {
            Roles.fromId(String.valueOf(request.getRol()));

        } catch (IllegalArgumentException e) {
            // Lanzamos la excepción con un mensaje descriptivo
            throw new IllegalArgumentException("Rol no válido");
        }

        // Validamos que el usuario no exista
        if (usersRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Este nombre de usuario ya está en uso");
        }

        // Creamos el usuario
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRolId(String.valueOf(request.getRol()));

        usersRepository.save(user);

        return response;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        LoginResponseDTO response = new LoginResponseDTO();
        Optional<Users> user = usersRepository.findByUsername(request.getUsername());

        if (user.isEmpty() && request.getUsername() != null) {
            response.setMessage("Este usuario no se encuentra registrado");
            return response;
        }

        // Obtenemos el usuario y validamos la contraseña
        Users userFound = user.get();

        if (!passwordEncoder.matches(request.getPassword(), userFound.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Generamos el token con los datos del usuario obtenidos del metodo
        // findByUsername
        String jwt = jwtService.generateToken(String.valueOf(userFound.getId()), userFound.getRolId(),
                userFound.getUsername());

        response.setMessage("Inicio de sesión exitoso");
        response.setJwt(jwt);
        return response;
    }

    /**
     * Este método es para el refresco del token
     * 
     * @param token jwt viejo
     * @return nuevo token
     * @throws Exception
     */
    public RefreshTokenResponseDTO refreshToken(String token) throws Exception {
        String jwt = jwtService.refreshToken(token);
        RefreshTokenResponseDTO response = new RefreshTokenResponseDTO();
        response.setMessage("ok");
        response.setJwt(jwt);
        return response;
    }
}
