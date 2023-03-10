package com.capgemini.academia.service.impl;

import com.capgemini.academia.dto.ClienteDTO;
import com.capgemini.academia.dto.DomicilioDTO;
import com.capgemini.academia.dto.RespuestaDTO;
import com.capgemini.academia.exceptions.BusinessException;
import com.capgemini.academia.model.ClienteItem;
import com.capgemini.academia.model.DomicilioItem;
import com.capgemini.academia.repository.ClienteRepository;
import com.capgemini.academia.repository.DomicilioRepository;
import com.capgemini.academia.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    DomicilioRepository domicilioRepository;

    //Cliente
    @Override
    @Transactional
    public ClienteDTO crearCliente(ClienteDTO nuevoCliente) {
        Optional<ClienteItem> lstBusqueda = clienteRepository
                .findByCliente(nuevoCliente.getRfc());
        if (!lstBusqueda.isEmpty()) {
            throw new BusinessException(25);
        }
        ClienteItem nuevoRegistro = new ClienteItem();
        nuevoRegistro.setNombre(nuevoCliente.getNombre());
        nuevoRegistro.setApellido_paterno(nuevoCliente.getApellido_paterno());
        nuevoRegistro.setApellido_materno(nuevoCliente.getApellido_materno());
        nuevoRegistro.setFechaNac(nuevoCliente.getFechaNac());
        nuevoRegistro.setRfc(nuevoCliente.getRfc());
        clienteRepository.save(nuevoRegistro);
        nuevoCliente.setId_cliente(nuevoRegistro.getId_cliente());
        return nuevoCliente;
    }

    @Override
    @Transactional
    public ClienteDTO actualizarCliente(ClienteDTO actualizarCliente) {
        Optional<ClienteItem> actualizar = clienteRepository.findById(actualizarCliente.getId_cliente());
        if (actualizar.isEmpty()) {
            throw new BusinessException(19);
        }
        ClienteItem actuaCliente = actualizar.get();
        actuaCliente.setNombre(actualizarCliente.getNombre());
        actuaCliente.setApellido_paterno(actualizarCliente.getApellido_paterno());
        actuaCliente.setApellido_materno(actualizarCliente.getApellido_materno());
        actuaCliente.setFechaNac(actualizarCliente.getFechaNac());
        actuaCliente.setRfc(actualizarCliente.getRfc());
        clienteRepository.save(actuaCliente);
        actualizarCliente.setId_cliente(actuaCliente.getId_cliente());
        return actualizarCliente;
    }

    @Transactional
    public RespuestaDTO eliminarCliente(Long id) {
        RespuestaDTO respuesta = new RespuestaDTO();
        DomicilioDTO auxDir;
        List<DomicilioDTO> lstDirec = new ArrayList<DomicilioDTO>();
        Optional<ClienteItem> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            throw new BusinessException(19);
        }
        if (cliente.get().getDireciones() != null) {
            for (DomicilioItem direcciones : cliente.get().getDireciones()) {
                auxDir = new DomicilioDTO();
                auxDir.setId_direccion(direcciones.getId_direccion());
                lstDirec.add(auxDir);
            }
            for (int i = 0; i < lstDirec.size(); i++) {
                domicilioRepository.deleteById(lstDirec.get(i).getId_direccion());
            }
        }
        clienteRepository.deleteById(id);
        respuesta.setMensajeRespuesta("Registro eliminado correctamente");
        return respuesta;
    }

    @Override
    public ClienteDTO obtenerInformacionCliente(Long id) {
        ClienteDTO retorno = new ClienteDTO();
        DomicilioDTO auxDom;
        List<DomicilioDTO> lstDom = new ArrayList<DomicilioDTO>();
        Optional<ClienteItem> registro = clienteRepository.findById(id);
        if (registro.isEmpty()) {
            throw new BusinessException(19);
        }
        retorno.setId_cliente(registro.get().getId_cliente());
        retorno.setNombre(registro.get().getNombre());
        retorno.setApellido_paterno(registro.get().getApellido_paterno());
        retorno.setApellido_materno(registro.get().getApellido_materno());
        retorno.setFechaNac(registro.get().getFechaNac());
        retorno.setRfc(registro.get().getRfc());
        if (registro.get().getDireciones() != null) {
            for (DomicilioItem domicilio : registro.get().getDireciones()) {
                auxDom = new DomicilioDTO();
                auxDom.setId_direccion(domicilio.getId_direccion());
                auxDom.setCalle(domicilio.getCalle());
                auxDom.setNo_interior(domicilio.getNo_interior());
                auxDom.setNo_exterior(domicilio.getNo_exterior());
                auxDom.setColonia(domicilio.getColonia());
                auxDom.setCodigo_postal(domicilio.getCodigo_postal());
                auxDom.setFk_id_cliente(registro.get().getId_cliente());
                lstDom.add(auxDom);
            }
            retorno.setLstDomicilios(lstDom);
        }
        return retorno;
    }

    //Domicilio
    @Override
    @Transactional
    public DomicilioDTO crearDomicilio(DomicilioDTO nuevoDomicilio) {
        Optional<ClienteItem> registro = clienteRepository.findById(nuevoDomicilio.getFk_id_cliente());
        /*Optional<ClienteItem> registro2 = clienteRepository.findByDomicilio(nuevoDomicilio.getCalle(),
                nuevoDomicilio.getNo_interior(), nuevoDomicilio.getNo_exterior(), nuevoDomicilio.getColonia(),
                nuevoDomicilio.getCodigo_postal(), nuevoDomicilio.getFk_id_cliente());*/
        if (registro.isEmpty()) {
            throw new BusinessException(17);
        } /*else if (!registro2.isEmpty()) {
            throw new BusinessException(27);
        }*/
        DomicilioItem nuevoRegistro = new DomicilioItem();
        nuevoRegistro.setCalle(nuevoDomicilio.getCalle());
        nuevoRegistro.setNo_interior(nuevoDomicilio.getNo_interior());
        nuevoRegistro.setNo_exterior(nuevoDomicilio.getNo_exterior());
        nuevoRegistro.setColonia(nuevoDomicilio.getColonia());
        nuevoRegistro.setCodigo_postal(nuevoDomicilio.getCodigo_postal());
        nuevoRegistro.setClienteItem(registro.get());
        domicilioRepository.save(nuevoRegistro);
        nuevoDomicilio.setId_direccion(nuevoRegistro.getId_direccion());
        return nuevoDomicilio;
    }

    @Override
    @Transactional
    public DomicilioDTO actualizarDomicilio(DomicilioDTO actualizarDomicilio) {
        Optional<DomicilioItem> actualizar = domicilioRepository.findById(actualizarDomicilio.getId_direccion());
        if (actualizar.isEmpty()) {
            throw new BusinessException(20);
        }
        DomicilioItem actuaDomicilio = actualizar.get();
        actuaDomicilio.setCalle(actualizarDomicilio.getCalle());
        actuaDomicilio.setNo_interior(actualizarDomicilio.getNo_interior());
        actuaDomicilio.setNo_exterior(actualizarDomicilio.getNo_exterior());
        actuaDomicilio.setColonia(actualizarDomicilio.getColonia());
        actuaDomicilio.setCodigo_postal(actualizarDomicilio.getCodigo_postal());
        actuaDomicilio.setClienteItem(actualizar.get().getClienteItem());
        domicilioRepository.save(actuaDomicilio);
        actualizarDomicilio.setId_direccion(actuaDomicilio.getId_direccion());
        return actualizarDomicilio;
    }

    @Transactional
    public RespuestaDTO eliminarDomicilio(Long id) {
        RespuestaDTO respuesta = new RespuestaDTO();
        Optional<DomicilioItem> domicilio = domicilioRepository.findById(id);
        if (domicilio.isEmpty()) {
            throw new BusinessException(20);
        }
        domicilioRepository.deleteById(id);
        respuesta.setMensajeRespuesta("Registro eliminado correctamente");
        return respuesta;
    }

    @Override
    public DomicilioDTO obtenerInformacionDomicilio(Long id) {
        DomicilioDTO retorno = new DomicilioDTO();
        ClienteDTO auxCliente;
        List<ClienteDTO> lstClientes = new ArrayList<ClienteDTO>();
        Optional<DomicilioItem> registro = domicilioRepository.findById(id);
        if (registro.isEmpty()) {
            throw new BusinessException(20);
        }
        retorno.setId_direccion(registro.get().getId_direccion());
        retorno.setCalle(registro.get().getCalle());
        retorno.setNo_interior(registro.get().getNo_interior());
        retorno.setNo_exterior(registro.get().getNo_exterior());
        retorno.setColonia(registro.get().getColonia());
        retorno.setCodigo_postal(registro.get().getCodigo_postal());
        retorno.setFk_id_cliente(registro.get().getClienteItem().getId_cliente());
        if (registro.get().getClientes() != null) {
            for (ClienteItem clientes : registro.get().getClientes()) {
                auxCliente = new ClienteDTO();
                auxCliente.setId_cliente(clientes.getId_cliente());
                auxCliente.setNombre(clientes.getNombre());
                auxCliente.setApellido_paterno(clientes.getApellido_paterno());
                auxCliente.setApellido_materno(clientes.getApellido_materno());
                auxCliente.setFechaNac(clientes.getFechaNac());
                auxCliente.setRfc(clientes.getRfc());
                lstClientes.add(auxCliente);
            }
            retorno.setLstClientes(lstClientes);
        }
        return retorno;
    }
}
