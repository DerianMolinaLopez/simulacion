package com.simulacion.productos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscar(@PathVariable Long id) {
        return service.buscar(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return service.crear(producto.nombre(), producto.precio());
    }

    @GetMapping("/buscar")
    public List<Producto> buscarPorNombre(@RequestParam String q) {
        return service.buscarPorNombre(q);
    }

    @PostMapping("/total")
    public double total(@RequestBody List<Long> ids) {
        return service.calcularTotal(ids);
    }
}
