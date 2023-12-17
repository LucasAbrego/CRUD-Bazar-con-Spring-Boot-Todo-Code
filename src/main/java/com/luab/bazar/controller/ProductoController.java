package com.luab.bazar.controller;

import com.luab.bazar.model.Producto;
import com.luab.bazar.service.IProductoService;
import com.luab.bazar.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {
    @Autowired
    private IProductoService svcProducto;
    @PostMapping("/productos/crear")
    public String crearProducto(@RequestBody Producto producto){
        svcProducto.createProducto(producto);
        return "El producto se ha creado exitosamente";
    };

    @GetMapping("/productos")
    public List<Producto> getAllProductos(){
        return svcProducto.getProductos();
    }

    @GetMapping("/productos/{codigo_producto}")
    public Producto findProductoByCodigo(@PathVariable Long codigo_producto){
        return svcProducto.findProductoByCodigo(codigo_producto);
    }

    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    public String deleteProducto(@PathVariable Long codigo_producto){
        svcProducto.deleteProducto(codigo_producto);
        return "El producto ha sido eliminado";
    }

    @PutMapping("/productos/editar/{codigo_producto}")
    public Producto updateProducto(@PathVariable Long codigo_producto,
                                   @RequestParam(required = false, name = "codigo_producto") Long nuevo_codigo_producto,
                                   @RequestParam(required = false, name = "nombre") String nuevo_nombre,
                                   @RequestParam(required = false, name = "marca") String nueva_marca,
                                   @RequestParam(required = false, name = "costo") Double nuevo_costo,
                                   @RequestParam(required = false, name = "cantidad_disponible") Double nueva_cantidad_disponible){
        return svcProducto.updateProducto(codigo_producto, nuevo_codigo_producto, nuevo_nombre, nueva_marca, nuevo_costo, nueva_cantidad_disponible);
    }
}
