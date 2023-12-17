package com.luab.bazar.service;

import com.luab.bazar.model.Producto;
import com.luab.bazar.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{
    @Autowired
    private ProductoRepository repoProducto;

    @Override
    public List<Producto> getProductos() {
        return repoProducto.findAll();
    }

    @Override
    public Producto findProductoByCodigo(Long codigo_producto) {
        return repoProducto.findById(codigo_producto).orElse(null);
    }

    @Override
    public void createProducto(Producto producto) {
        repoProducto.save(producto);
    }

    @Override
    public void deleteProducto(Long codigo_producto) {
        repoProducto.deleteById(codigo_producto);
    }

    @Override
    public Producto updateProducto(Long codigo_producto,
                                   Long nuevo_codigo_producto,
                                   String nuevo_nombre,
                                   String nueva_marca,
                                   Double nuevo_costo,
                                   Double nueva_cantidad_disponible) {
        Producto producto_modificado = repoProducto.findById(codigo_producto).orElse(null);
        if(producto_modificado!=null){
            if(nuevo_codigo_producto!=null)producto_modificado.setCodigo_producto(nuevo_codigo_producto);
            if(nuevo_nombre!=null)producto_modificado.setNombre(nuevo_nombre);
            if(nueva_marca!=null)producto_modificado.setMarca(nueva_marca);
            if(nuevo_costo!=null)producto_modificado.setCosto(nuevo_costo);
            if(nueva_cantidad_disponible!=null)producto_modificado.setCantidad_disponible(nueva_cantidad_disponible);
            repoProducto.save(producto_modificado);
        }
        return producto_modificado;
    }
}
