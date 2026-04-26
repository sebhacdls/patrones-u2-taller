package cl.patrones.taller.u2.tienda.menu;

import java.util.ArrayList;
import java.util.List;

public class EnlaceSimple implements ItemMenu {
    private String texto;
    private String enlace;

    public EnlaceSimple(String texto, String enlace) {
        this.texto = texto;
        this.enlace = enlace;
    }

    @Override
    public String getTexto() { return this.texto; }

    @Override
    public String getSlug() { return ""; } // No requiere slug al ser estático

    @Override
    public String getEnlace() { return this.enlace; }

    @Override
    public boolean tieneHijos() { return false; }

    @Override
    public List<? extends ItemMenu> getHijos() { return new ArrayList<>(); }
}

