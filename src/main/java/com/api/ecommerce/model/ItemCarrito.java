package com.api.ecommerce.model;

public class ItemCarrito {
    private String productoId;
    private String talle;
    private int cantidad;

    public ItemCarrito() {
    }

    public ItemCarrito(String productoId, String talle, int cantidad) {
        this.productoId = productoId;
        this.talle = talle;
        this.cantidad = cantidad;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
