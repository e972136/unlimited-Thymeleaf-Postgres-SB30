package com.unlimited.estimaciones.config;

import com.unlimited.estimaciones.entity.Estimacion;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVHelper {
    public static String TYPE="text/csv";
    static String[] HEADERs = { "id","anio_vehiculo","Asegurado","Aseguradora","color_vehiculo","costo_mano_de_obra_gerente","costo_materiales_gerente","estimado_por","fecha_evaluacion","implementado_por","KM","Marca","Modelo","obs","Placa","vinoserie" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Estimacion> csvToEstimacion(InputStream is){
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Estimacion> tutorials = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

//            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");



            for (CSVRecord csvRecord : csvRecords) {
                try{
                    Estimacion estimacion = new Estimacion();
                    estimacion.setId(Integer.parseInt(csvRecord.get("id")));
                    estimacion.setAsegurado(csvRecord.get("Asegurado"));
                    estimacion.setEstimadoPor(csvRecord.get("estimado_por"));
//                    Date fecha = formato.parse(csvRecord.get("fechaEvaluacion"));
//                    estimacion.setFechaEvaluacion(fecha);
                    estimacion.setAseguradora(csvRecord.get("Aseguradora"));
                    estimacion.setPlaca(csvRecord.get("Placa"));
                    estimacion.setMarca(csvRecord.get("Marca"));
                    estimacion.setModelo(csvRecord.get("Modelo"));
                    estimacion.setKM(csvRecord.get("KM"));
                    estimacion.setColorVehiculo(csvRecord.get("color_vehiculo"));
                    estimacion.setAnioVehiculo(csvRecord.get("anio_vehiculo"));
                    estimacion.setVinOSerie(csvRecord.get("vinoserie"));
//                    estimacion.setObs(csvRecord.get("obs"));
                    estimacion.setImplementadoPor(csvRecord.get("implementado_por"));

                    tutorials.add(estimacion);
                }catch (Exception e){
                    System.out.println(e+""+csvRecord);
                }
            }
            //"id","","","","","","","","fecha_evaluacion","","","","","obs","",""
            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }

    }
}
