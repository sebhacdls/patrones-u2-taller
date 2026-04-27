package cl.patrones.taller.u2.tienda.adapter;

import java.util.List;
import java.util.stream.Collectors;

import cl.patrones.taller.u2.tienda.menu.ItemMenu;
import cl.patrones.taller.u2.catalogo.domain.Categoria;
import cl.patrones.taller.u2.tienda.menu.util.Slugger;

public class CategoriaAdapter implements ItemMenu {
    
    private Categoria categoria;
    private List<ItemMenu> hijos;

    public CategoriaAdapter(Categoria categoria, List<Categoria> todasLasCategorias) {
        this.categoria = categoria;
        this.hijos = todasLasCategorias.stream()
                .filter(c -> c.getPadre() != null && c.getPadre().getId().equals(categoria.getId()))
                .map(c -> new CategoriaAdapter(c, todasLasCategorias))
                .collect(Collectors.toList());
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
        return !hijos.isEmpty();
    }

    @Override
    public List<? extends ItemMenu> getHijos() {
        return hijos;
    }
}