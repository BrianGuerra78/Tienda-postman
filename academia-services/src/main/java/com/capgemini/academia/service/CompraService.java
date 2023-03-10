package com.capgemini.academia.service;

import com.capgemini.academia.dto.ComprasDTO;
import com.capgemini.academia.dto.ComprasProductoDTO;
import com.capgemini.academia.dto.RespuestaDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface CompraService {

    ComprasDTO compras(@RequestBody ComprasDTO nuevaCompra);

    ComprasDTO editaCompras(ComprasDTO editaCompra);

    RespuestaDTO eliminarCompras(Long id);

    ComprasDTO obtenerInformacionCompras(Long id);

    ComprasProductoDTO comprasProducto(ComprasProductoDTO nuevaCompraProducto);

    ComprasProductoDTO editaComprasProducto(ComprasProductoDTO editaComprasProducto);

    RespuestaDTO eliminarComprasProductos(Long id);

    ComprasProductoDTO obtenerInformacionComprasProductos(Long id);
}
