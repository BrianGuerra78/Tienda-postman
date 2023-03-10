package com.capgemini.academia.controller;

import com.capgemini.academia.service.ClienteService;
import com.capgemini.academia.service.CompraService;
import com.capgemini.academia.service.ProductoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.capgemini.academia.dto.*;

import com.capgemini.academia.exceptions.BusinessException;


@RestController
@RequestMapping("/API")
@Api(value = "Applciation academia")
public class ApplicationController {

    private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CompraService compraService;

    //Productos
    @PostMapping(value = "/crearProducto")
    public ProductoDTO crearProducto(@RequestBody ProductoDTO nuevoProducto) {
        if (nuevoProducto.getCodigo() == null || nuevoProducto.getCodigo().trim().isEmpty()) {
            throw new BusinessException(14);
        }
        return productoService.crearProducto(nuevoProducto);
    }

    @PostMapping(value = "/actualizarProducto/{id}")
    public ProductoDTO actualizarAplicacion(@PathVariable Long id, @RequestBody ProductoDTO actualizarProducto) {
        actualizarProducto.setId_producto(id);
        if (actualizarProducto.getId_producto() == null) {
            throw new BusinessException(18);
        }
        return productoService.actualizarProducto(actualizarProducto);
    }

    @DeleteMapping(value = "/eliminarProducto/{id}")
    public RespuestaDTO eliminiarRegistro(@PathVariable Long id) {
        return productoService.eliminarProducto(id);
    }

    @GetMapping(value = "/obtenerInformacionProducto/{id}")
    public ProductoDTO obtenerInformacionProducto(@PathVariable Long id) {
        return productoService.obtenerInformacionProducto(id);
    }

    //Clientes
    @PostMapping(value = "/crearCliente")
    public ClienteDTO crearCliente(@RequestBody ClienteDTO nuevoCliente) {
        if (nuevoCliente.getRfc() == null || nuevoCliente.getRfc().trim().isEmpty()) {
            throw new BusinessException(15);
        }
        return clienteService.crearCliente(nuevoCliente);
    }

    @PostMapping(value = "/actualizarCliente/{id}")
    public ClienteDTO actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO actualizarCliente) {
        actualizarCliente.setId_cliente(id);
        if (actualizarCliente.getId_cliente() == null) {
            throw new BusinessException(23);
        }
        return clienteService.actualizarCliente(actualizarCliente);
    }

    @DeleteMapping(value = "/eliminarCliente/{id}")
    public RespuestaDTO eliminiarCliente(@PathVariable Long id) {
        return clienteService.eliminarCliente(id);
    }

    @GetMapping(value = "/obtenerInformacionCliente/{id}")
    public ClienteDTO obtenerInformacionCliente(@PathVariable Long id) {
        return clienteService.obtenerInformacionCliente(id);
    }

    //Domicilio
    @PostMapping(value = "/crearDomicilio/{id}")
    public DomicilioDTO crearDomicilio(@PathVariable Long id, @RequestBody DomicilioDTO nuevoDomicilio) {
        if (nuevoDomicilio.getCodigo_postal() == null || nuevoDomicilio.getCodigo_postal().trim().isEmpty()) {
            throw new BusinessException(15);
        }
        nuevoDomicilio.setFk_id_cliente(id);
        return clienteService.crearDomicilio(nuevoDomicilio);
    }

    @PostMapping(value = "/actualizarDomicilio/{id}")
    public DomicilioDTO actualizarDomiciio(@PathVariable Long id, @RequestBody DomicilioDTO actualizarDomicilio) {
        actualizarDomicilio.setId_direccion(id);
        if (actualizarDomicilio.getId_direccion() == null) {
            throw new BusinessException(24);
        }
        return clienteService.actualizarDomicilio(actualizarDomicilio);
    }

    @DeleteMapping(value = "/eliminarDomicilio/{id}")
    public RespuestaDTO eliminiardomicilio(@PathVariable Long id) {

        return clienteService.eliminarDomicilio(id);
    }

    @GetMapping(value = "/obtenerInformacionDomicilio/{id}")
    public DomicilioDTO obtenerInformacionDomicilio(@PathVariable Long id) {
        return clienteService.obtenerInformacionDomicilio(id);
    }

    //Compras
    @PostMapping(value = "/compras")
    public ComprasDTO compras(@RequestBody ComprasDTO nuevaCompra) {
        return compraService.compras(nuevaCompra);
    }

    @PostMapping(value = "/editaCompras/{id}")
    public ComprasDTO editaCompras(@PathVariable Long id, @RequestBody ComprasDTO editaCompra) {
        editaCompra.setId_compra(id);
        if (editaCompra.getId_compra() == null) {
            throw new BusinessException(21);
        }
        return compraService.editaCompras(editaCompra);
    }

    @DeleteMapping(value = "/eliminarCompras/{id}")
    public RespuestaDTO eliminiarCompras(@PathVariable Long id) {
        return compraService.eliminarCompras(id);
    }

    @GetMapping(value = "/obtenerInformacionCompras/{id}")
    public ComprasDTO obtenerInformacionCompras(@PathVariable Long id) {
        return compraService.obtenerInformacionCompras(id);
    }

    //Compras Producto
    @PostMapping(value = "/comprasProducto")
    public ComprasProductoDTO comprasProducto(@RequestBody ComprasProductoDTO nuevaCompraProducto) {
        return compraService.comprasProducto(nuevaCompraProducto);
    }

    @PostMapping(value = "/editaComprasProducto/{id}")
    public ComprasProductoDTO editaComprasProducto(@PathVariable Long id, @RequestBody ComprasProductoDTO editaComprasProducto) {
        editaComprasProducto.setId_compras_productos(id);
        if (editaComprasProducto.getId_compras_productos() == null) {
            throw new BusinessException(21);
        }
        return compraService.editaComprasProducto(editaComprasProducto);
    }

    @DeleteMapping(value = "/eliminarComprasProducto/{id}")
    public RespuestaDTO eliminiarComprasProductos(@PathVariable Long id) {
        return compraService.eliminarComprasProductos(id);
    }

    @GetMapping(value = "/obtenerInformacionComprasProductos/{id}")
    public ComprasProductoDTO obtenerInformacionComprasProductos(@PathVariable Long id) {
        return compraService.obtenerInformacionComprasProductos(id);
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/version"})
    public String getVersion() {
        return "1.0";
    }

}
