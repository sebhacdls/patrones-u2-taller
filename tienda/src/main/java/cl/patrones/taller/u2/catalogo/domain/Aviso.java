package cl.patrones.taller.u2.catalogo.domain;

public interface Aviso {
    String getTitulo();
    String getSku();
    Double getPrecio(); // Usamos Double para los decimales del 30%
    int getStock();
    String getImagen();
    String getCategoriaNombre(); // Para obtener el nombre de la categoría
}