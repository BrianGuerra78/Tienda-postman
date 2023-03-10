package com.capgemini.academia.service.impl;

import com.capgemini.academia.dto.ProductoDTO;
import com.capgemini.academia.dto.RespuestaDTO;
import com.capgemini.academia.exceptions.BusinessException;
import com.capgemini.academia.model.ProductoItem;
import com.capgemini.academia.repository.ProductoRepository;
import com.capgemini.academia.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

    @Autowired
    ProductoRepository productoRepository;

    //Producto
    @Override
    @Transactional
    public ProductoDTO crearProducto(ProductoDTO nuevoProducto) {
        Optional<ProductoItem> lstBusqueda = productoRepository
                .findByProducto(nuevoProducto.getCodigo());
        if (!lstBusqueda.isEmpty()) {
            throw new BusinessException(16);
        }
        ProductoItem nuevoRegistro = new ProductoItem();
        nuevoRegistro.setNombre(nuevoProducto.getNombre());
        nuevoRegistro.setDescripcion(nuevoProducto.getDescripcion());
        nuevoRegistro.setNo_piezas(nuevoProducto.getNo_piezas());
        nuevoRegistro.setProducto_activo(nuevoProducto.getProducto_activo());
        nuevoRegistro.setCodigo(nuevoProducto.getCodigo());
        nuevoRegistro.setPrecio(nuevoProducto.getPrecio());
        productoRepository.save(nuevoRegistro);
        nuevoProducto.setId_producto(nuevoRegistro.getId_producto());
        return nuevoProducto;
    }

    @Override
    @Transactional
    public ProductoDTO actualizarProducto(ProductoDTO actualizarProducto) {
        Optional<ProductoItem> actualizar = productoRepository.findById(actualizarProducto.getId_producto());
        if (actualizar.isEmpty()) {
            throw new BusinessException(26);
        }
        ProductoItem actualizaRegistro = actualizar.get();
        actualizaRegistro.setNombre(actualizarProducto.getNombre());
        actualizaRegistro.setDescripcion(actualizarProducto.getDescripcion());
        actualizaRegistro.setNo_piezas(actualizarProducto.getNo_piezas());
        actualizaRegistro.setProducto_activo(actualizarProducto.getProducto_activo());
        actualizaRegistro.setCodigo(actualizarProducto.getCodigo());
        actualizaRegistro.setPrecio(actualizarProducto.getPrecio());
        productoRepository.save(actualizaRegistro);
        actualizaRegistro.setId_producto(actualizarProducto.getId_producto());
        return actualizarProducto;
    }

    @Override
    @Transactional
    public RespuestaDTO eliminarProducto(Long id) {
        RespuestaDTO respuesta = new RespuestaDTO();
        Optional<ProductoItem> producto = productoRepository.findById(id);
        if (producto.isEmpty()) {
            throw new BusinessException(26);
        }
        productoRepository.deleteById(id);
        respuesta.setMensajeRespuesta("Registro eliminado correctamente");
        return respuesta;
    }

    @Override
    public ProductoDTO obtenerInformacionProducto(Long id) {
        ProductoDTO retorno = new ProductoDTO();
        Optional<ProductoItem> registro = productoRepository.findById(id);
        if (registro.isEmpty()) {
            throw new BusinessException(26);
        }
        retorno.setId_producto(registro.get().getId_producto());
        retorno.setNombre(registro.get().getNombre());
        retorno.setDescripcion(registro.get().getDescripcion());
        retorno.setNo_piezas(registro.get().getNo_piezas());
        retorno.setProducto_activo(registro.get().getProducto_activo());
        retorno.setCodigo(registro.get().getCodigo());
        retorno.setPrecio(registro.get().getPrecio());

        return retorno;
    }
}
