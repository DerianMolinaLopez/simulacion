package com.simulacion.productos;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductoService {

    private final Map<Long, Producto> productos = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong();

    public List<Producto> listar() {
        return List.copyOf(productos.values());
    }

    public Optional<Producto> buscar(Long id) {
        return Optional.ofNullable(productos.get(id));
    }

    public Producto crear(String nombre, double precio) {
        if (StringUtils.isBlank(nombre)) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        long id = secuencia.incrementAndGet();
        Producto producto = new Producto(id, nombre.trim(), precio);
        productos.put(id, producto);
        return producto;
    }

    public double calcularTotal(List<Long> ids) {
        double total = 0;
        for (Long id : ids) {
            total += buscar(id).map(Producto::precio).orElse(0.0);
        }
        return total;
    }
}
