package com.supermercado.supermercado.security;


import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interceptor de Spring MVC para manejar la anotación {@link RequiresRole} en los controladores de la aplicación.
 * Este interceptor se ejecuta antes de que se maneje la solicitud en el controlador y verifica si el usuario tiene el rol y los permisos necesarios para acceder al endpoint.
 * Si el usuario no tiene el rol o los permisos requeridos, el interceptor responde con un
 * error de autorización sin permitir que la solicitud llegue al controlador.
 */
@Component
public class
 RequiresRoleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true; // No nos importa
        }
        HandlerMethod method = (HandlerMethod) handler;

        RequiresRole annotation = method.getMethodAnnotation(RequiresRole.class);

        if (annotation == null) {
            annotation = method.getBeanType().getAnnotation(RequiresRole.class);
        }

        if (annotation == null) {
            return true; // Tampoco nos importa
        }

        Object rol = request.getAttribute("rolId");

        if (!(rol instanceof String)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Usuario no autenticado\"}");
            return false;
        }
        String rolId = (String) rol;

        // Verifica si el rol del usuario esta dentro de los roles permitidos
        boolean hasRole = Arrays.stream(annotation.value()).anyMatch(role -> role.getId().equals(rolId.toString()));
        if (!hasRole) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"No tienes permisos para realizar esta acción\"}");
            return false;
        }

        if (annotation.permisos().length > 0) {
            Roles currentRole;
            try {
                currentRole = Roles.fromId(rolId);
            } catch (IllegalArgumentException e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Rol no permitido\"}");
                return false;
            }

            boolean hasPermission = Arrays.stream(annotation.permisos())
                    .allMatch(currentRole::optenerPermisos);

            if (!hasPermission) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"No tiene permiso para esta acción\"}");
                return false;
            }
        }

        return true;
    }
}
