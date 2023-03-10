package com.capgemini.academia.service.impl;

import com.capgemini.academia.dto.ComprasDTO;
import com.capgemini.academia.dto.ComprasProductoDTO;
import com.capgemini.academia.dto.RespuestaDTO;
import com.capgemini.academia.exceptions.BusinessException;
import com.capgemini.academia.model.*;
import com.capgemini.academia.repository.*;
import com.capgemini.academia.service.CompraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComprasServiceImpl implements CompraService {
    private static final Logger log = LoggerFactory.getLogger(ComprasServiceImpl.class);

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    ComprasProductoRepository comprasProductoRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    DomicilioRepository domicilioRepository;

    //Compras
    @Override
    @Transactional
    public ComprasDTO compras(ComprasDTO nuevaCompra) {
        java.util.Date fecha = new Date();
        Optional<ClienteItem> registro = clienteRepository.findById(nuevaCompra.getFk_id_cliente());
        Optional<DomicilioItem> registroDir = domicilioRepository.findById(nuevaCompra.getFk_id_direccion());
        ComprasItem nuevoRegistro = new ComprasItem();
        nuevoRegistro.setFecha_compra(fecha);
        nuevaCompra.setFecha_compra(fecha);
        nuevoRegistro.setDescripcion(nuevaCompra.getDescripcion());
        nuevoRegistro.setFk_id_cliente(registro.get());
        nuevoRegistro.setTotal(nuevaCompra.getTotal());
        nuevoRegistro.setFk_id_direccion(registroDir.get());
        List<ComprasProductoItem> lstaCompras = new ArrayList<>();
        for (ComprasProductoDTO compras : nuevaCompra.getLstComprasProductos()) {
            ComprasProductoItem compraLista = new ComprasProductoItem();
            Optional<ProductoItem> buscarProducto = productoRepository.findById(compras.getFk_id_producto());
            //  Optional<ComprasItem> compra = compraRepository.findById(compras.getFk_id_compra());
            compraLista.setNo_articulos(compras.getNo_articulo());
            compraLista.setProductoItem(buscarProducto.get());
            compraLista.setComprasItem(nuevoRegistro);
            compraLista.setPrecio(buscarProducto.get().getPrecio() * compraLista.getNo_articulos());
            lstaCompras.add(compraLista);
            nuevoRegistro.setCompras(lstaCompras);
            compraRepository.save(nuevoRegistro);
            compras.setPrecio(compraLista.getPrecio());
            nuevaCompra.setId_compra(nuevoRegistro.getId_compra());
            compras.setFk_id_compra(nuevaCompra.getId_compra());
            compras.setId_compras_productos(compraLista.getId_compras_productos());
        }
        return nuevaCompra;
    }

    @Override
    @Transactional
    public ComprasDTO editaCompras(ComprasDTO editaCompra) {
        java.util.Date fecha = new Date();
        Optional<ComprasItem> compra = compraRepository.findById(editaCompra.getId_compra());
        if (compra.isEmpty()) {
            throw new BusinessException(22);
        }
        ComprasItem editaRegistro = compra.get();
        editaRegistro.setFecha_compra(fecha);
        editaRegistro.setDescripcion(editaCompra.getDescripcion());
        editaRegistro.setFk_id_cliente(compra.get().getFk_id_cliente());
        editaRegistro.setTotal(editaCompra.getTotal());
        editaRegistro.setFk_id_direccion(compra.get().getFk_id_direccion());
        compraRepository.save(editaRegistro);
        editaCompra.setId_compra(editaRegistro.getId_compra());

        return editaCompra;
    }

    @Override
    @Transactional
    public RespuestaDTO eliminarCompras(Long id) {
        RespuestaDTO respuesta = new RespuestaDTO();
        ComprasProductoDTO aux;
        List<ComprasProductoDTO> lstComPro = new ArrayList<ComprasProductoDTO>();
        Optional<ComprasItem> registro = compraRepository.findById(id);
        if (registro.isEmpty()) {
            throw new BusinessException(21);
        }
        if (registro.get().getCompras() != null) {
            for (ComprasProductoItem compras : registro.get().getCompras()) {
                aux = new ComprasProductoDTO();
                aux.setId_compras_productos(compras.getId_compras_productos());
                lstComPro.add(aux);
            }
            for (int i = 0; i < lstComPro.size(); i++) {
                comprasProductoRepository.deleteById(lstComPro.get(i).getId_compras_productos());
            }
        }
        compraRepository.deleteById(id);
        respuesta.setMensajeRespuesta("Registro eliminado correctamente");
        return respuesta;
    }

    @Override
    public ComprasDTO obtenerInformacionCompras(Long id) {
        ComprasDTO retorno = new ComprasDTO();
        ComprasProductoDTO aux;
        List<ComprasProductoDTO> lstComPro = new ArrayList<ComprasProductoDTO>();
        Optional<ComprasItem> registro = compraRepository.findById(id);
        if (registro.isEmpty()) {
            throw new BusinessException(22);
        }
        retorno.setId_compra(registro.get().getId_compra());
        retorno.setFecha_compra(registro.get().getFecha_compra());
        retorno.setDescripcion(registro.get().getDescripcion());
        retorno.setFk_id_cliente(registro.get().getFk_id_cliente().getId_cliente());
        retorno.setTotal(registro.get().getTotal());
        retorno.setFk_id_direccion(registro.get().getFk_id_direccion().getId_direccion());
        if (registro.get().getCompras() != null) {
            for (ComprasProductoItem compras : registro.get().getCompras()) {
                aux = new ComprasProductoDTO();
                aux.setId_compras_productos(compras.getId_compras_productos());
                aux.setNo_articulo(compras.getNo_articulos());
                aux.setFk_id_producto(compras.getProductoItem().getId_producto());
                aux.setFk_id_compra(compras.getComprasItem().getId_compra());
                aux.setPrecio(compras.getPrecio());
                lstComPro.add(aux);
            }
            retorno.setLstComprasProductos(lstComPro);
        }
        return retorno;
    }

    //Compras Producto
    @Override
    @Transactional
    public ComprasProductoDTO comprasProducto(ComprasProductoDTO nuevaCompraProducto) {
        Optional<ProductoItem> producto = productoRepository.findById(nuevaCompraProducto.getFk_id_producto());
        Optional<ComprasItem> compra = compraRepository.findById(nuevaCompraProducto.getFk_id_compra());
        ComprasProductoItem nuevoRegistro = new ComprasProductoItem();
        nuevoRegistro.setNo_articulos(nuevaCompraProducto.getNo_articulo());
        nuevoRegistro.setProductoItem(producto.get());
        nuevoRegistro.setComprasItem(compra.get());
        nuevoRegistro.setPrecio(producto.get().getPrecio() * nuevoRegistro.getNo_articulos());
        comprasProductoRepository.save(nuevoRegistro);
        nuevaCompraProducto.setId_compras_productos(nuevoRegistro.getId_compras_productos());

        return nuevaCompraProducto;
    }

    @Override
    @Transactional
    public ComprasProductoDTO editaComprasProducto(ComprasProductoDTO editaComprasProducto) {
        Optional<ComprasProductoItem> editarCompra = comprasProductoRepository.findById(editaComprasProducto.getId_compras_productos());
        if (editarCompra.isEmpty()) {
            throw new BusinessException(22);
        }
        ComprasProductoItem editaRegistro = editarCompra.get();
        editaRegistro.setNo_articulos(editaComprasProducto.getNo_articulo());
        editaRegistro.setProductoItem(editarCompra.get().getProductoItem());
        editaRegistro.setComprasItem(editarCompra.get().getComprasItem());
        editaRegistro.setPrecio(editaComprasProducto.getPrecio());
        comprasProductoRepository.save(editaRegistro);
        editaComprasProducto.setId_compras_productos(editaRegistro.getId_compras_productos());

        return editaComprasProducto;
    }

    @Override
    @Transactional
    public RespuestaDTO eliminarComprasProductos(Long id) {
        RespuestaDTO respuesta = new RespuestaDTO();
        Optional<ComprasProductoItem> registro = comprasProductoRepository.findById(id);
        if (registro.isEmpty()) {
            throw new BusinessException(21);
        }
        comprasProductoRepository.deleteById(id);
        respuesta.setMensajeRespuesta("Registro eliminado correctamente");
        return respuesta;
    }

    @Override
    public ComprasProductoDTO obtenerInformacionComprasProductos(Long id) {
        ComprasProductoDTO retorno = new ComprasProductoDTO();
        Optional<ComprasProductoItem> registro = comprasProductoRepository.findById(id);
        if (registro.isEmpty()) {
            throw new BusinessException(22);
        }
        retorno.setId_compras_productos(registro.get().getId_compras_productos());
        retorno.setNo_articulo(registro.get().getNo_articulos());
        retorno.setFk_id_producto(registro.get().getProductoItem().getId_producto());
        retorno.setFk_id_compra(registro.get().getComprasItem().getId_compra());
        retorno.setPrecio(registro.get().getPrecio());

        return retorno;
    }
}
