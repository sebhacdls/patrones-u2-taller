package cl.patrones.taller.u2.tienda.adapter;

import cl.patrones.taller.u2.bodegaje.domain.Producto;
import cl.patrones.taller.u2.catalogo.domain.Aviso;

public class ProductoAvisoAdapter extends Aviso {

    public ProductoAvisoAdapter(Producto producto) {
        // Configuramos los atributos heredados de Aviso
        this.setTitulo(producto.getNombre());
        this.setSku(producto.getSku());
        this.setImagen(producto.getImagen());
        this.setPrecio(producto.getCosto() * 130 / 100); // Costo + 30% de utilidad
        this.setStock((int) producto.getStocks().stream()
                .mapToLong(s -> s.getCantidad())
                .sum()); // Stock consolidado de todas las bodegas
    }
}
