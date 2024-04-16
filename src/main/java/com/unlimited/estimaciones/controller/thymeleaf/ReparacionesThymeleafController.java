package com.unlimited.estimaciones.controller.thymeleaf;

import com.unlimited.estimaciones.config.LoggerColor;
import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.Reparacion;
import com.unlimited.estimaciones.entity.dto.RepuestoRequest;
import com.unlimited.estimaciones.service.ReparacionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/thymeleaf/reparaciones")
public class ReparacionesThymeleafController {
    private final LoggerColor log = new LoggerColor(LoggerFactory.getLogger(getClass()));

    private final ReparacionService reparacionService;

    public ReparacionesThymeleafController(ReparacionService reparacionService) {
        this.reparacionService = reparacionService;
    }

    @GetMapping("/editarReparaciones")
    public ModelAndView obtenerReparaciones(
            RedirectAttributes attributes,
            @RequestParam int id
    ){
        ModelAndView mav = new ModelAndView("editarReparaciones");

        List<Reparacion> list = reparacionService.findAllByEstimacionIdOrderById(id);

        mav.addObject("estimacion",new MantenimientoReparacion(id,list,new RepuestoRequest(id,0,"", 1,BigDecimal.ZERO,"")));

        return mav;
    }


    @PostMapping("/saveReparacionEntity")
    public ModelAndView guardarReparacionEntity(
            @ModelAttribute RepuestoRequest reparacionRequest,
            @RequestParam Integer idEstimacion
    ){
        ModelAndView mav = new ModelAndView("redirect:/thymeleaf/reparaciones/editarReparaciones?id="+idEstimacion);

        System.err.println("reparacionRequest "+reparacionRequest);

        RepuestoRequest request = reparacionService.saveReparacion(reparacionRequest);

        return mav;
    }

    @PostMapping("/saveReparaciones")
    public ModelAndView guardarReparaciones(
            @ModelAttribute  MantenimientoReparacion estimacion
            ){

        log.infoRed("/principal/saveReparaciones/"+estimacion);

        ModelAndView mav = new ModelAndView("redirect:/thymeleaf/estimacion/editarEstimacion?id="+estimacion.estimacionId);

        reparacionService.saveReparaciones(estimacion.getReparaciones());

        return mav;
    }


    @Data
    @AllArgsConstructor
    private class MantenimientoReparacion {
        Integer estimacionId;
        List<Reparacion> reparaciones;
        RepuestoRequest repuestoRequest;
    }



}
