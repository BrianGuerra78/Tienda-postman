package com.capgemini.academia.service;

import com.capgemini.academia.dto.ProductoDTO;
import com.capgemini.academia.dto.RespuestaDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductoService {

    ProductoDTO crearProducto(@RequestBody ProductoDTO nuevoProducto);

    ProductoDTO actualizarProducto(@RequestBody ProductoDTO actualizarProducto);

    RespuestaDTO eliminarProducto(@RequestBody Long id);

    ProductoDTO obtenerInformacionProducto(Long id);

}
