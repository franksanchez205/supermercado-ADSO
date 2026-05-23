package com.supermercado.supermercado.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.supermercado.supermercado.security.RequiresRoleInterceptor;

import lombok.RequiredArgsConstructor;

/** * Configuración de la aplicación para el manejo de interceptores de Spring MVC.
 * Esta clase registra el interceptor {@link RequiresRoleInterceptor} para validar
 * las anotaciones de seguridad en los controladores de la aplicación.
  */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final RequiresRoleInterceptor requiresRoleInterceptor;

    /* Método para agregar interceptores a la configuración de Spring MVC. En este caso,
    /* se registra el interceptor {@link RequiresRoleInterceptor} para que se ejecute en todas las rutas de la aplicación.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Registramos el interceptor para que se ejecute en todas las rutas
        registry.addInterceptor(requiresRoleInterceptor);
    }
}
