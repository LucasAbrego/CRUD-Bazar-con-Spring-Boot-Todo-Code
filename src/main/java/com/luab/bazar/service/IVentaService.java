package com.luab.bazar.service;

import com.luab.bazar.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    List<Venta> getVentas();
    Venta findVentaByCodigo(Long codigo_venta);
    void createVenta(List<Long> listaIdProductos, Long idCliente);
    void deleteVenta(Long codigo_venta);
    Venta updateVenta(Long codigo_venta,
                      LocalDate nueva_fecha_venta,
                      List<Long> nueva_lista_id_poductos,
                      Long nuevo_id_cliente);
}
