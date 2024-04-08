package com.unlimited.estimaciones.service;

import com.unlimited.estimaciones.config.CSVHelper;
import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.repository.EstimacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CSVService {
    private final EstimacionRepository estimacionRepository;

    public CSVService(EstimacionRepository estimacionRepository) {
        this.estimacionRepository = estimacionRepository;
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
}
