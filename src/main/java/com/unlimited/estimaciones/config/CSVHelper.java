package com.unlimited.estimaciones.config;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.Reparacion;
import com.unlimited.estimaciones.entity.ReparacionAdicional;
import com.unlimited.estimaciones.entity.Repuesto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
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
                    estimacion.setKilometraje(csvRecord.get("KM"));
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

    public static List<Repuesto> csvToRepuesto(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Repuesto> tutorials = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

//            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");



            for (CSVRecord csvRecord : csvRecords) {
                try{
                    Repuesto repuesto = new Repuesto();
                    repuesto.setEstimacionId(Integer.parseInt(csvRecord.get("estimacion_id")));
                    repuesto.setDescripcion(csvRecord.get("descripcion"));
                    repuesto.setPrecio(new BigDecimal(csvRecord.get("precio")));
                    tutorials.add(repuesto);
                }catch (Exception e){
                    System.out.println(e+""+csvRecord);
                }
            }

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static List<Reparacion> csvToReparaciones(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Reparacion> tutorials = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                try{
                    Reparacion reparacion = new Reparacion();
                    reparacion.setEstimacionId(Integer.parseInt(csvRecord.get("estimacion_id")));
                    reparacion.setDetalleReparacion(csvRecord.get("detalle_reparacion"));
                    reparacion.setPrecio(new BigDecimal(csvRecord.get("precio_")));
                    tutorials.add(reparacion);
                }catch (Exception e){
                    System.out.println(e+""+csvRecord);
                }
            }

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static List<ReparacionAdicional> csvToReparacionAdicional(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<ReparacionAdicional> tutorials = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//            "estimacion_id","reparacion_adicional","valor_reparacion","Tipo"
            for (CSVRecord csvRecord : csvRecords) {
                try{
                    ReparacionAdicional reparacionAdicional = new ReparacionAdicional();
                    reparacionAdicional.setEstimacionId(Integer.parseInt(csvRecord.get("estimacion_id")));
                    reparacionAdicional.setReparacionAdicionalDetalle(csvRecord.get("reparacion_adicional"));
                    reparacionAdicional.setValorReparacion(new BigDecimal(csvRecord.get("valor_reparacion")));
                    reparacionAdicional.setTipo(csvRecord.get("Tipo"));
                    tutorials.add(reparacionAdicional);
                }catch (Exception e){
                    System.out.println(e+""+csvRecord);
                }
            }

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
