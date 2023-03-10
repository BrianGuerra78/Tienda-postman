package com.capgemini.academia.service;

import com.capgemini.academia.dto.ClienteDTO;
import com.capgemini.academia.dto.DomicilioDTO;
import com.capgemini.academia.dto.RespuestaDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface ClienteService {

    ClienteDTO crearCliente(@RequestBody ClienteDTO nuevoCliente);

    DomicilioDTO crearDomicilio(@RequestBody DomicilioDTO nuevoDomicilio);

    ClienteDTO actualizarCliente(@RequestBody ClienteDTO actualizarCliente);

    ClienteDTO obtenerInformacionCliente(Long id);

    RespuestaDTO eliminarCliente(Long id);

    DomicilioDTO actualizarDomicilio(DomicilioDTO actualizarDomicilio);

    RespuestaDTO eliminarDomicilio(Long id);

    DomicilioDTO obtenerInformacionDomicilio(Long id);
}
