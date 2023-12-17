package com.luab.bazar.service;

import com.luab.bazar.model.Producto;

import java.util.List;

public interface IProductoService {
    List<Producto> getProductos();
    Producto findProductoByCodigo(Long codigo_producto);
    void createProducto(Producto producto);
    void deleteProducto(Long codigo_producto);
    Producto updateProducto(Long codigo_producto,
                            Long nuevo_codigo_producto,
                            String nuevo_nombre,
                            String nueva_marca,
                            Double nuevo_costo,
                            Double nueva_cantidad_disponible);
}
