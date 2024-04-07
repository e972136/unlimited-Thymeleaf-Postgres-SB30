package com.unlimited.estimaciones.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unlimited.estimaciones.config.LoggerColor;
import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.dto.EstimacionListado;
import com.unlimited.estimaciones.entity.dto.EstimacionResponse;
import com.unlimited.estimaciones.repository.EstimacionRepository;
import com.unlimited.estimaciones.repository.ReparacionAdicionalRepository;
import com.unlimited.estimaciones.repository.ReparacionRepository;
import com.unlimited.estimaciones.repository.RepuestoRepository;
import com.unlimited.estimaciones.service.EstimacionService;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Service
public class EstimacionServiceImpl implements EstimacionService {

    private final LoggerColor log = new LoggerColor(LoggerFactory.getLogger(getClass()));
    private final EstimacionRepository estimacionRepository;
    private final ReparacionRepository reparacionRepository;

    private final RepuestoRepository repuestoRepository;

    private final ReparacionAdicionalRepository reparacionAdicionalRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    public EstimacionServiceImpl(EstimacionRepository estimacionRepository, ReparacionRepository reparacionRepository, RepuestoRepository repuestoRepository, ReparacionAdicionalRepository reparacionAdicionalRepository) {
        this.estimacionRepository = estimacionRepository;
        this.reparacionRepository = reparacionRepository;
        this.repuestoRepository = repuestoRepository;
        this.reparacionAdicionalRepository = reparacionAdicionalRepository;
    }



    @Override
    public EstimacionResponse findById(int id) {
        return cambioEstimacionResponse(estimacionRepository.findById(id).orElse(null));
    }

    @Override
    public EstimacionListado findByIdListado(Integer idEstimacion) {
        return cambioEstimacionListado(estimacionRepository.findById(idEstimacion).orElse(null));
    }

    @Override
    public Page<EstimacionListado> traerListado(Pageable page) {
        Page<Estimacion> all = estimacionRepository.findAll(page);
        return  all.map(this::cambioEstimacionListado);
    }

    @Override
    public Page<EstimacionListado> traerListadoFiltrado(String busqueda, Pageable page) {
        int id = 0;
        try{
            id = Integer.parseInt(busqueda);
        }catch (Exception e){

        }
        Page<Estimacion> all = estimacionRepository.findAllByPlacaContainingIgnoreCaseOrAseguradoContainingIgnoreCaseOrId(busqueda,busqueda,id,page);
        return all.map(this::cambioEstimacionListado);
    }

    private EstimacionListado cambioEstimacionListado(Estimacion estimacion) {
        try{
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            String jsonResult = mapper.writeValueAsString(estimacion);
            return mapper.readValue(jsonResult, EstimacionListado.class);
        }catch (Exception e){
            return null;
        }
    }


    private EstimacionResponse cambioEstimacionResponse(Estimacion estimacion) {

        try{
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);


            String s1 = repuestoRepository.selectTotals(estimacion.getId());
            if(isNull(s1)){
                s1="0.0";
            }
            estimacion.setTotalRepuestos(new BigDecimal(s1));

            String s2 = reparacionRepository.selectTotals(estimacion.getId());
            if(isNull(s2)){
                s2="0.0";
            }
            estimacion.setTotalReparaciones(new BigDecimal(s2));

            String s3 = reparacionAdicionalRepository.selectTotals(estimacion.getId());
            if(isNull(s3)){
                s3="0.0";
            }
            estimacion.setTotalReparacionesAdicionales(new BigDecimal(s3));

            String jsonResult = mapper.writeValueAsString(estimacion);
            return mapper.readValue(jsonResult, EstimacionResponse.class);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }




    @Override
    public Estimacion saveEstimacion(Estimacion estimacion) {
        return null;
    }


}
