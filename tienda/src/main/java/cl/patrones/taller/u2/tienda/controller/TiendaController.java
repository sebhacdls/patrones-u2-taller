package cl.patrones.taller.u2.tienda.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.patrones.taller.u2.bodegaje.domain.Producto;
import cl.patrones.taller.u2.bodegaje.repository.ProductoRepository;
import cl.patrones.taller.u2.catalogo.domain.Aviso;
import cl.patrones.taller.u2.tienda.adapter.ProductoAvisoAdapter;

@Controller
public class TiendaController {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@GetMapping("/")
public String inicio(Model model) {
    // 1. Obtenemos los productos de la base de datos (Bodega)
    List<Producto> productos = productoRepository.findAll();

    // 2. Los transformamos en "Avisos" usando nuestro Adapter
    List<Aviso> avisos = productos.stream()
            .map(p -> new ProductoAvisoAdapter(p))
            .collect(Collectors.toList());

    // 3. Pasamos la lista de avisos a la vista (HTML)
    model.addAttribute("avisos", avisos);

    return "inicio";
}
		
	@GetMapping("/categoria/{categoriaId}/{slug}")
	public String categoria(
			@PathVariable(name = "categoriaId") Long categoriaId,
			@PathVariable(name = "slug") String slug,
			Model model
	) {
		// TODO: Actividad 2: Avisos
		//model.addAttribute("avisos", avisos);
		//model.addAttribute("categoria", categoria);
		return "categoria";
	}
	
	@GetMapping("/ingresar")
	public String login() {
		return "login";
	}
	
	@GetMapping("/ubicacion")
	public String ubicacion() {return "ubicacion";}
	
	@GetMapping("/contacto")
	public String contacto() {return "contacto";}
	
}
