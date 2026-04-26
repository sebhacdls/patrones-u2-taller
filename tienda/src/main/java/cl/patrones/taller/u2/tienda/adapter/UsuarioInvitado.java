package cl.patrones.taller.u2.tienda.adapter;

import cl.patrones.taller.u2.clientes.Cliente;
import java.util.ArrayList;

public class UsuarioInvitado extends Usuario {

    public UsuarioInvitado() {
        // Le pasamos un cliente vacío al constructor padre
        super(new Cliente());
    }

    @Override
    public String getUsername() {
        return "Invitado"; // Esto es lo que verá el usuario en pantalla
    }

    // Un invitado no tiene permisos reales
    @Override
    public java.util.Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }
}