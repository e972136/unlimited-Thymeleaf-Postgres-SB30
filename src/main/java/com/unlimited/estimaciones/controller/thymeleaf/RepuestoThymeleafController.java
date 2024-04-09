package com.unlimited.estimaciones.controller.thymeleaf;


import com.unlimited.estimaciones.entity.Repuesto;
import com.unlimited.estimaciones.entity.dto.RepuestoRequest;
import com.unlimited.estimaciones.repository.RepuestoRepository;
import com.unlimited.estimaciones.service.RepuestoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/thymeleaf/repuestos")
public class RepuestoThymeleafController {

    private final RepuestoRepository repuestoRepository;
    private final RepuestoService repuestoService;

    public RepuestoThymeleafController(RepuestoRepository repuestoRepository, RepuestoService repuestoService) {
        this.repuestoRepository = repuestoRepository;
        this.repuestoService = repuestoService;
    }

    @GetMapping("/editarRepuestos")
    public ModelAndView obtenerPaginaEditarRepuesto(
            RedirectAttributes attributes,
            @RequestParam int id
    ){
        ModelAndView mav = new ModelAndView("editarRepuestos");
        List<Repuesto> repuestos = repuestoRepository.findAllByEstimacionIdOrderByIdAsc(id);

        mav.addObject("estimacion",new MantenimientoRepuesto(id,repuestos,new RepuestoRequest(id,0,"", BigDecimal.ZERO)));
        return mav;
    }

    @PostMapping("/saveRepuestosEntity")
    public ModelAndView guardarRepuestoEntidad(
            @ModelAttribute RepuestoRequest repuestoRequest,
            @RequestParam Integer idEstimacion
    ){
        ModelAndView mav = new ModelAndView("redirect:/thymeleaf/repuestos/editarRepuestos?id="+idEstimacion);

        System.err.println("repuestoRequest "+repuestoRequest);
        RepuestoRequest request = repuestoService.saveRepuesto(repuestoRequest);
        System.err.println(request);

        return mav;
    }

    @GetMapping("eliminarRepuesto")
    public ModelAndView eliminarRepuesto(
            @RequestParam Integer idEstimacion,
            @RequestParam int idRepuesto
    ){
        System.err.println(idEstimacion);
        System.err.println(idRepuesto);
        ModelAndView mav = new ModelAndView("redirect:/thymeleaf/repuestos/editarRepuestos?id="+idEstimacion);

        int estimacion = repuestoService.eliminarRepuesto(idRepuesto);

        return mav;
    }

    @PostMapping("/saveRepuestos")
    public ModelAndView guardarRepuesto(
            @ModelAttribute MantenimientoRepuesto estimacion
    ){

        ModelAndView mav = new ModelAndView("redirect:/thymeleaf/estimacion/editarEstimacion?id="+estimacion.estimacionId);

        repuestoService.saveRepuestos(estimacion.getRepuestos());

        return mav;
    }


    @Data
    @AllArgsConstructor
    public class MantenimientoRepuesto{
        Integer estimacionId;
        List<Repuesto> repuestos;
        RepuestoRequest repuestoRequest;
    }
}
