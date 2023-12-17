package com.luab.bazar.controller;

import com.luab.bazar.model.Producto;
import com.luab.bazar.model.Venta;
import com.luab.bazar.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VentaController {
    @Autowired
    private IVentaService svcVenta;
    @PostMapping("/ventas/crear")
    public String createVenta(@RequestParam List<Long> lista_id_productos,
                              @RequestParam Long id_cliente){
        svcVenta.createVenta(lista_id_productos, id_cliente);
        return  "La venta se registro exitosamente";
    }
    @GetMapping("/ventas")
    public List<Venta> getAllVentas(){
        return svcVenta.getVentas();
    }
    @GetMapping("/ventas/{codigo_venta}")
    public Venta findVentaByCodigo(@PathVariable Long codigo_venta){
        return svcVenta.findVentaByCodigo(codigo_venta);
    }

    @DeleteMapping("/ventas/eliminar/{codigo_venta}")
    public String deleteVenta(@PathVariable Long codigo_venta){
        svcVenta.deleteVenta(codigo_venta);
        return "La venta "+codigo_venta+" ha sido eliminada";
    }

    @PutMapping("/ventas/editar/{codigo_venta}")
    public Venta updateVenta(@PathVariable Long codigo_venta,
                             @RequestParam(required = false, name = "codigo_venta")Long nuevo_codigo_venta,
                             @RequestParam(required = false, name = "fecha_venta") LocalDate nueva_fecha_venta,
                             @RequestParam(required = false, name = "lista_id_productos") List<Long> nueva_lista_id_productos,
                             @RequestParam(required = false, name = "id_cliente") Long nuevo_id_cliente){
        return svcVenta.updateVenta(codigo_venta, nueva_fecha_venta, nueva_lista_id_productos, nuevo_id_cliente);
    }
}

