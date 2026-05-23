package com.supermercado.supermercado.security;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación para especificar los roles requeridos para acceder a un endpoint.
 * Esta anotación se puede aplicar a métodos o clases en los controladores de la aplicación
 * para indicar que solo los usuarios con ciertos roles y permisos pueden acceder a esos endpoints.
 * El interceptor {@link RequiresRoleInterceptor} se encarga de verificar esta anotación en tiempo de ejecución
 */
@Target({ElementType.METHOD, ElementType.TYPE})
// RUNTIME: Va a estar activa en tiempo de ejecución (siempre)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRole {
    Roles[] value();
    Permisos[] permisos() default {};
}
