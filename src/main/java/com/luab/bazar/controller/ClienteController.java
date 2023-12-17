package com.luab.bazar.controller;

import com.luab.bazar.model.Cliente;
import com.luab.bazar.model.Venta;
import com.luab.bazar.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {
    @Autowired
    private IClienteService svrCliente;
    @PostMapping ("/clientes/crear")
    public String createCliente(@RequestBody Cliente cliente){
        svrCliente.createCliente(cliente);
        return "Cliente registrado con exito!";
    }

    @GetMapping("/clientes")
    public List<Cliente> getAllClientes(){
        return svrCliente.getClientes();
    }

    @GetMapping("/clientes/{id_cliente}")
    public Cliente getClienteById(@PathVariable Long id_cliente){
        return svrCliente.findClienteById(id_cliente);
    }


    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public String deleteClienteById(@PathVariable Long id_cliente){
        svrCliente.deleteCliente(id_cliente);
        return "Cliente eliminado";
    }


    @PutMapping("/clientes/editar/{id_cliente}")
    public Cliente updateCliente(@PathVariable Long id_cliente,
                                 @RequestParam(required = false, name = "id") Long nuevo_id_cliente,
                                 @RequestParam(required = false, name = "nombre") String nuevo_nombre,
                                 @RequestParam(required = false, name = "apellido") String nuevo_apellido,
                                 @RequestParam(required = false, name = "dni") String nuevo_dni){
        return svrCliente.updateCliente(id_cliente, nuevo_id_cliente, nuevo_nombre, nuevo_apellido, nuevo_dni);
    }
}
