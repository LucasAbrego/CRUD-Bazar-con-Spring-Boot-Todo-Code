package com.luab.bazar.service;

import com.luab.bazar.model.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> getClientes();
    Cliente findClienteById(Long id_cliente);
    void createCliente(Cliente cliente);
    void deleteCliente(Long id_cliente);
    Cliente updateCliente(Long id_cliente,
                          Long nuevo_id_cliente,
                          String nuevo_nombre,
                          String nuevo_apellido,
                          String nuevo_dni);
}
