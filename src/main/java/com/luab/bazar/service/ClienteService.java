package com.luab.bazar.service;

import com.luab.bazar.model.Cliente;
import com.luab.bazar.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    private ClienteRepository repoCliente;

    @Override
    public List<Cliente> getClientes() {
        return repoCliente.findAll();
    }

    @Override
    public Cliente findClienteById(Long id_cliente) {
        return repoCliente.findById(id_cliente).orElse(null);
    }

    @Override
    public void createCliente(Cliente cliente) {
        repoCliente.save(cliente);
    }

    @Override
    public void deleteCliente(Long id_cliente) {
        repoCliente.deleteById(id_cliente);
    }

    @Override
    public Cliente updateCliente(Long id_cliente, Long nuevo_id_cliente, String nuevo_nombre, String nuevo_apellido, String nuevo_dni) {
        Cliente cliente_modificado = repoCliente.findById(id_cliente).orElse(null);
        if(cliente_modificado!=null){
            if(nuevo_id_cliente!=null)cliente_modificado.setId_cliente(id_cliente);
            if(nuevo_nombre!=null)cliente_modificado.setNombre(nuevo_nombre);
            if(nuevo_apellido!=null)cliente_modificado.setApellido(nuevo_apellido);
            if(nuevo_dni!=null)cliente_modificado.setDni(nuevo_dni);
            repoCliente.save(cliente_modificado);
        }
        return cliente_modificado;
    }
}
