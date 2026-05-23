package com.supermercado.supermercado.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;



/**
 * Clase que define los roles del sistema
 */

public enum Roles {
    /**
     * Rol de administrador
     */
    ADMINISTRADOR("1", EnumSet.allOf(Permisos.class)),
    /**
     * Rol de cliente
     */
    CLIENTE("2", EnumSet.of(
            Permisos.USERS_CREATE,
            Permisos.USERS_READ));
     /**
     * Id del rol
     */
    private final String id;
    /**
     * Permisos del rol
     */
    private final Set<Permisos> permisos;

    /**
     * Constructor de la clase UserRole
     * 
     * @param id
     * @param permissions
     */
    Roles(String id, Set<Permisos> permisos) {
        this.id = id;
        this.permisos = Collections.unmodifiableSet(permisos);
    }

    /**
     * Metodo que permite obtener el id del rol
     * 
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Metodo que permite obtener los permisos del rol
     * 
     * @return
     */
    public Set<Permisos> getPermissions() {
        return permisos;
    }

    /**
     * Metodo que permite obtener un rol a partir de su id
     * 1: ADMINISTRADOR
     * 2: CLIENTE
     * 
     * @param id
     * @return
     */
    public static Roles fromId(String id) {
        // Buscamos el rol a partir del id en el enum UserRole
        // Si no lo encuentra, lanzamos una excepcion
        return Arrays.stream(values())
                .filter(role -> role.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rol no permitido"));
    }

    /**
     * Metodo que permite verificar si el rol tiene permiso para realizar la accion
     * 
     * @param permisos
     * @return
     */
    public boolean optenerPermisos(Permisos permisos) {
        return this.permisos.contains(permisos);
    }
}
