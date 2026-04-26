package cl.patrones.taller.u2.tienda.menu;

import cl.patrones.taller.u2.catalogo.domain.Aviso;
import cl.patrones.taller.u2.bodegaje.domain.Producto;

public class ProductoAvisoAdapter implements Aviso {
    
    private Producto producto;

    public ProductoAvisoAdapter(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String getTitulo() {
        return producto.getNombre();
    }

    @Override
    public String getSku() {
        return producto.getSku();
    }

    @Override
    public Double getPrecio() {
        // Cálculo: Costo base + 30% de utilidad
        return producto.getCosto() * 1.30;
    }

    @Override
    public int getStock() {
        // Sumamos el stock de todas las bodegas
        return producto.getStocks().stream()
                .mapToInt(e -> e.getCantidad())
                .sum();
    }

    @Override
    public String getImagen() {
        return producto.getImagen();
    }

    @Override
    public String getCategoriaNombre() {
        return ""; // Producto no tiene categoría asociada directamente
    }
}