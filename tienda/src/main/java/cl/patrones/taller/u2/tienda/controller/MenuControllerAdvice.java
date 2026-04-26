package cl.patrones.taller.u2.tienda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// Imports para la seguridad y el patrón Null Object
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;

import cl.patrones.taller.u2.tienda.menu.ItemMenu;
import cl.patrones.taller.u2.tienda.menu.EnlaceSimple;
import cl.patrones.taller.u2.tienda.adapter.CategoriaAdapter;
import cl.patrones.taller.u2.tienda.adapter.UsuarioInvitado; // Tu Null Object
import cl.patrones.taller.u2.catalogo.repository.CategoriaRepository;
import cl.patrones.taller.u2.catalogo.domain.Categoria;

@ControllerAdvice
public class MenuControllerAdvice {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Lógica para el Menú (Patrón Composite + Adapter)
     */
    @ModelAttribute("menu")
    public List<ItemMenu> menu() {
        List<ItemMenu> listaMenu = new ArrayList<>();

        listaMenu.add(new EnlaceSimple("Inicio", "/"));

        List<Categoria> categoriasDesdeBD = categoriaRepository.findAll();

        for (Categoria cat : categoriasDesdeBD) {
            if (cat.getPadre() == null) {
                listaMenu.add(new CategoriaAdapter(cat));
            }
        }

        return listaMenu;
    }

    /**
     * Lógica para el Usuario (Patrón Null Object)
     * Este método hace que "usuarioActual" esté disponible en todo el HTML
     */
    @ModelAttribute("usuarioActual")
    public String obtenerUsuario() {
        // 1. Le preguntamos a Spring Security quién está conectado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 2. Verificamos si el usuario existe y está autenticado legalmente
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            // Si está logueado, devolvemos su nombre real (email)
            return auth.getName();
        }

        // 3. SI NO HAY NADIE: En lugar de devolver null, usamos nuestro Patrón Null Object
        UsuarioInvitado invitado = new UsuarioInvitado();
        return invitado.getUsername(); // Esto devolverá "Invitado"
    }
}