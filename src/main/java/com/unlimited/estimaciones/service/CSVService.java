package com.unlimited.estimaciones.service;

import com.unlimited.estimaciones.config.CSVHelper;
import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.Reparacion;
import com.unlimited.estimaciones.entity.ReparacionAdicional;
import com.unlimited.estimaciones.entity.Repuesto;
import com.unlimited.estimaciones.repository.EstimacionRepository;
import com.unlimited.estimaciones.repository.ReparacionAdicionalRepository;
import com.unlimited.estimaciones.repository.ReparacionRepository;
import com.unlimited.estimaciones.repository.RepuestoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CSVService {
    private final EstimacionRepository estimacionRepository;
    private final RepuestoRepository repuestoRepository;

    private final ReparacionRepository reparacionRepository;

    private final ReparacionAdicionalRepository reparacionAdicionalRepository;

    public CSVService(EstimacionRepository estimacionRepository, RepuestoRepository repuestoRepository, ReparacionRepository reparacionRepository, ReparacionAdicionalRepository reparacionAdicionalRepository) {
        this.estimacionRepository = estimacionRepository;
        this.repuestoRepository = repuestoRepository;
        this.reparacionRepository = reparacionRepository;
        this.reparacionAdicionalRepository = reparacionAdicionalRepository;
    }

    public void save(MultipartFile file){
        int i=0;
        try{
            int batchSize = 2000;
            List<Estimacion> tutorials = CSVHelper.csvToEstimacion(file.getInputStream());
            int totalElementos = tutorials.size();
            for(i=0;i<totalElementos;i+=batchSize){
                if(i+batchSize>totalElementos){
                    List<Estimacion> subiendo = tutorials.subList(i,totalElementos);
                    estimacionRepository.saveAll(subiendo);
                    System.out.println("guardado hasta "+i+" "+totalElementos);
                    break;
                }
                List<Estimacion> subiendo = tutorials.subList(i,i+batchSize);
                estimacionRepository.saveAll(subiendo);
                System.out.println("guardado hasta "+i+" "+(i+batchSize));
            }
        }catch (Exception e){
            System.out.println(i);
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void saveRepuestos(MultipartFile file) {
        int i=0;
        try{
            int batchSize = 2000;
            List<Repuesto> tutorials = CSVHelper.csvToRepuesto(file.getInputStream());
            int totalElementos = tutorials.size();
            for(i=0;i<totalElementos;i+=batchSize){
                if(i+batchSize>totalElementos){
                    List<Repuesto> subiendo = tutorials.subList(i,totalElementos);
                    repuestoRepository.saveAll(subiendo);
                    System.out.println("guardado hasta "+i+" "+totalElementos);
                    break;
                }
                List<Repuesto> subiendo = tutorials.subList(i,i+batchSize);
                repuestoRepository.saveAll(subiendo);
                System.out.println("guardado hasta "+i+" "+(i+batchSize));
            }
        }catch (Exception e){
            System.out.println(i);
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void saveReparaciones(MultipartFile file) {
        int i=0;
        try{
            int batchSize = 2000;
            List<Reparacion> tutorials = CSVHelper.csvToReparaciones(file.getInputStream());
            int totalElementos = tutorials.size();
            for(i=0;i<totalElementos;i+=batchSize){
                if(i+batchSize>totalElementos){
                    List<Reparacion> subiendo = tutorials.subList(i,totalElementos);
                    reparacionRepository.saveAll(subiendo);
                    System.out.println("guardado hasta "+i+" "+totalElementos);
                    break;
                }
                List<Reparacion> subiendo = tutorials.subList(i,i+batchSize);
                reparacionRepository.saveAll(subiendo);
                System.out.println("guardado hasta "+i+" "+(i+batchSize));
            }
        }catch (Exception e){
            System.out.println(i);
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void saveReparacionesAdicionales(MultipartFile file) {
        int i=0;
        try{
            int batchSize = 2000;
            List<ReparacionAdicional> tutorials = CSVHelper.csvToReparacionAdicional(file.getInputStream());
            int totalElementos = tutorials.size();
            for(i=0;i<totalElementos;i+=batchSize){
                if(i+batchSize>totalElementos){
                    List<ReparacionAdicional> subiendo = tutorials.subList(i,totalElementos);
                    reparacionAdicionalRepository.saveAll(subiendo);
                    System.out.println("guardado hasta "+i+" "+totalElementos);
                    break;
                }
                List<ReparacionAdicional> subiendo = tutorials.subList(i,i+batchSize);
                reparacionAdicionalRepository.saveAll(subiendo);
                System.out.println("guardado hasta "+i+" "+(i+batchSize));
            }
        }catch (Exception e){
            System.out.println(i);
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
