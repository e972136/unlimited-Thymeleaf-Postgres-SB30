package com.unlimited.estimaciones.controller.thymeleaf;

import com.unlimited.estimaciones.entity.ReparacionAdicional;
import com.unlimited.estimaciones.entity.dto.RepuestoRequest;
import com.unlimited.estimaciones.service.ReparacionesAdicionalesService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/thymeleaf/reparacionesAdicionales")
public class ReparacionesAdicionalesController {

    private final ReparacionesAdicionalesService reparacionesAdicionalesService;

    public ReparacionesAdicionalesController(ReparacionesAdicionalesService reparacionesAdicionalesService) {
        this.reparacionesAdicionalesService = reparacionesAdicionalesService;
    }

    @GetMapping("/editar")
    public ModelAndView obtenerReparaciones(
            RedirectAttributes attributes,
            @RequestParam int id
    ){
        ModelAndView mav = new ModelAndView("editarReparacionesAdicionales");
        List<ReparacionAdicional> list = reparacionesAdicionalesService.findAllByEstimacionIdOrderById(id);
        mav.addObject("estimacion",new MantenimientoReparacionAdicional(id,list,new RepuestoRequest(id,0,"", 1, BigDecimal.ZERO,"")));
        return mav;
    }

    @Data
    @AllArgsConstructor
    private class MantenimientoReparacionAdicional {
        Integer estimacionId;
        List<ReparacionAdicional> reparacionesAdicionales;
        RepuestoRequest repuestoRequest;

    }
}
