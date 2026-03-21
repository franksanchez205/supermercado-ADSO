package com.supermercado.supermercado.dtos;

public class ProductoDTO {

    private String uuid;
    private String nombre;
    private String descripción;
    private double precio;
    private int stock;

    private CategoriaDTO categoria;

    public ProductoDTO() {
    }

    public ProductoDTO(String uuid) {
        this.uuid = uuid;
    }

    public ProductoDTO(String uuid, String nombre, String descripción, double precio, int stock) {

        this.uuid = uuid;
        this.nombre = nombre;
        this.descripción = descripción;
        this.precio = precio;
        this.stock = stock;

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

}
