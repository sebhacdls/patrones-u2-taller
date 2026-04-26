package cl.patrones.taller.u2.tienda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import cl.patrones.taller.u2.tienda.menu.ItemMenu;
import cl.patrones.taller.u2.tienda.menu.EnlaceSimple;
import cl.patrones.taller.u2.tienda.adapter.CategoriaAdapter;
import cl.patrones.taller.u2.catalogo.repository.CategoriaRepository;
import cl.patrones.taller.u2.catalogo.domain.Categoria;

@ControllerAdvice
public class MenuControllerAdvice {

    // El @Autowired le dice a Java: "Traeme la conexión a la base de datos de categorías"
    @Autowired
    private CategoriaRepository categoriaRepository;

    @ModelAttribute("menu")
    public List<ItemMenu> menu() {
        // 1. Creamos una lista vacía para guardar los elementos del menú
        List<ItemMenu> listaMenu = new ArrayList<>();

        // 2. Agregamos el primer botón: "Inicio" (usando la clase EnlaceSimple que creaste)
        listaMenu.add(new EnlaceSimple("Inicio", "/"));

        // 3. Traemos todas las categorías de la base de datos
        List<Categoria> categoriasDesdeBD = categoriaRepository.findAll();

        // 4. Recorremos las categorías y las "adaptamos" para que entren en el menú
        for (Categoria cat : categoriasDesdeBD) {
            // Solo agregamos las categorías principales (las que no tienen una categoría padre)
            if (cat.getPadre() == null) {
                listaMenu.add(new CategoriaAdapter(cat));
            }
        }

        // 5. Entregamos la lista completa al sitio web
        return listaMenu;
    }
}
