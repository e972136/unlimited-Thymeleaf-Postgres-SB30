package com.unlimited.estimaciones.controller.thymeleaf;


import com.unlimited.estimaciones.entity.Repuesto;
import com.unlimited.estimaciones.entity.dto.RepuestoRequest;
import com.unlimited.estimaciones.repository.RepuestoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/thymeleaf/repuestos")
public class RepuestoThymeleafController {

    private final RepuestoRepository repuestoRepository;

    public RepuestoThymeleafController(RepuestoRepository repuestoRepository) {
        this.repuestoRepository = repuestoRepository;
    }

    @GetMapping("/editarRepuestos")
    public ModelAndView obtenerPaginaEditarRepuesto(
            RedirectAttributes attributes,
            @RequestParam int id
    ){
        ModelAndView mav = new ModelAndView("editarRepuestos");
        List<Repuesto> repuestos = repuestoRepository.findAllByEstimacionId(id);

        mav.addObject("estimacion",new MantenimientoRepuesto(id,repuestos,new RepuestoRequest(id,0,"", BigDecimal.ZERO)));
        return mav;
    }

    public record MantenimientoRepuesto(Integer estimacionId,List<Repuesto> repuestos,RepuestoRequest repuestoRequest){

    }
}
