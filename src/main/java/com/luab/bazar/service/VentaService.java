package com.luab.bazar.service;

import com.luab.bazar.model.Cliente;
import com.luab.bazar.model.Producto;
import com.luab.bazar.model.Venta;
import com.luab.bazar.repository.ClienteRepository;
import com.luab.bazar.repository.ProductoRepository;
import com.luab.bazar.repository.VentaRepository;
import org.apache.tomcat.util.bcel.classfile.EnumElementValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{
    @Autowired
    private VentaRepository repoVenta;
    @Autowired
    private ProductoRepository repoProducto;
    @Autowired
    private ClienteRepository repoCliente;
    @Override
    public List<Venta> getVentas() {
        return repoVenta.findAll();
    }

    @Override
    public Venta findVentaByCodigo(Long codigo_venta) {
        return repoVenta.findById(codigo_venta).orElse(null);
    }

    @Override
    public void createVenta(List<Long> listaIdProductos, Long idCliente) {
        double total = 0d;
        LocalDate fecha_venta = LocalDate.now();
        List<Producto> listaProductos = new ArrayList<Producto>();
        for (Long idProducto : listaIdProductos){
            Producto producto = repoProducto.findById(idProducto).orElse(null);
            if(producto!=null){
                if(producto.getCosto()!=null) total = total+producto.getCosto();
                if(producto.getCantidad_disponible()>0){
                    listaProductos.add(producto);
                    producto.setCantidad_disponible(producto.getCantidad_disponible()-1);
                }
            }
        }

        Cliente cliente = repoCliente.findById(idCliente).orElse(null);

        Venta nuevaVenta= new Venta();
        nuevaVenta.setTotal(total);
        nuevaVenta.setFecha_venta(fecha_venta);
        if(cliente!=null)nuevaVenta.setCliente(cliente);
        nuevaVenta.setListaProductos(listaProductos);
        repoVenta.save(nuevaVenta);
    }

    @Override
    public void deleteVenta(Long codigo_venta) {
        Venta ventaEliminar = repoVenta.findById(codigo_venta).orElse(null);
        if(ventaEliminar!=null){
            for(Producto prod : ventaEliminar.getListaProductos()){
                Producto productoDB = repoProducto.findById(prod.getCodigo_producto()).orElse(null);
                if (productoDB!=null){
                    productoDB.setCantidad_disponible(productoDB.getCantidad_disponible()+1);
                    repoProducto.save(productoDB);
                }
            }
            repoVenta.deleteById(codigo_venta);
        }
    }

    @Override
    public Venta updateVenta(Long codigo_venta,
                             LocalDate nueva_fecha_venta,
                             List<Long> nueva_lista_id_productos,
                             Long nuevo_id_cliente) {
        Venta ventaModificada = repoVenta.findById(codigo_venta).orElse(null);
        if(ventaModificada!=null){
            if(nueva_fecha_venta!=null)ventaModificada.setFecha_venta(nueva_fecha_venta);
            if(nuevo_id_cliente!=null) {
                Cliente nuevoCliente = repoCliente.findById(nuevo_id_cliente).orElse(null);
                if (nuevoCliente != null) ventaModificada.setCliente(nuevoCliente);
            }
            //Devolver stock lista vieja
            List<Producto> viejaListaProductos = ventaModificada.getListaProductos();
            for (Producto prod : viejaListaProductos){
                Producto productoDB = repoProducto.findById(prod.getCodigo_producto()).orElse(null);
                if(productoDB!=null){
                    productoDB.setCantidad_disponible(productoDB.getCantidad_disponible()+1);
                    repoProducto.save(productoDB);
                }

            }

            List<Producto> nuevaListaProductos = new ArrayList<Producto>();
            double nuevoTotal = 0d;

            if(nueva_lista_id_productos!=null) {
                for (Long id_producto : nueva_lista_id_productos) {
                    Producto productoDB = repoProducto.findById(id_producto).orElse(null);
                    if (productoDB != null) {
                        if (productoDB.getCantidad_disponible() > 0) {
                            nuevaListaProductos.add(productoDB);
                            if (productoDB.getCosto() != null) nuevoTotal = nuevoTotal + productoDB.getCosto();
                            productoDB.setCantidad_disponible(productoDB.getCantidad_disponible() - 1);
                            repoProducto.save(productoDB);
                        }
                    }
                }
            }
            ventaModificada.setListaProductos(nuevaListaProductos);
            ventaModificada.setTotal(nuevoTotal);
            repoVenta.save(ventaModificada);
        }
        return ventaModificada;
    }
}
