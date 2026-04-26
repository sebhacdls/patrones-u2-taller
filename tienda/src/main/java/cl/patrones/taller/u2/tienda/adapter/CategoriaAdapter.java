package cl.patrones.taller.u2.tienda.adapter;

import java.util.ArrayList;
import java.util.List;

import cl.patrones.taller.u2.tienda.menu.ItemMenu;
import cl.patrones.taller.u2.catalogo.domain.Categoria;
import cl.patrones.taller.u2.tienda.menu.util.Slugger;

public class CategoriaAdapter implements ItemMenu {
    
    private Categoria categoria;

    public CategoriaAdapter(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String getTexto() {
        return categoria.getNombre();
    }

    @Override
    public String getSlug() {
        return Slugger.toSlug(categoria.getNombre());
    }

    @Override
    public String getEnlace() {
        return "/categoria/" + getSlug();
    }

    @Override
    public boolean tieneHijos() {
        return false;
    }

    @Override
    public List<? extends ItemMenu> getHijos() {
        return new ArrayList<>();
    }
}