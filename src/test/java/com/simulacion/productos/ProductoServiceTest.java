package com.simulacion.productos;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductoServiceTest {

    @Test
    void creaYCalculaTotal() {
        ProductoService service = new ProductoService();
        Producto a = service.crear("Teclado", 100);
        Producto b = service.crear("Mouse", 50);
        assertEquals(150, service.calcularTotal(List.of(a.id(), b.id())));
    }

    @Test
    void rechazaNombreVacio() {
        ProductoService service = new ProductoService();
        assertThrows(IllegalArgumentException.class, () -> service.crear("  ", 10));
    }

    @Test
    void rechazaPrecioNegativo() {
        ProductoService service = new ProductoService();
        assertThrows(IllegalArgumentException.class, () -> service.crear("Monitor", -5));
    }
}
