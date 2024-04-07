package com.unlimited.estimaciones.controller.thymeleaf;

import com.unlimited.estimaciones.config.LoggerColor;
import com.unlimited.estimaciones.entity.dto.EstimacionListado;
import com.unlimited.estimaciones.service.EstimacionService;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/thymeleaf/estimacion")
public class EstimacionThymeleafController {

    private final LoggerColor log = new LoggerColor(LoggerFactory.getLogger(getClass()));
    private final EstimacionService estimacionService;


    public EstimacionThymeleafController(EstimacionService estimacionService) {
        this.estimacionService = estimacionService;
    }

    @GetMapping("/listado")
    public ModelAndView listadoEstimaciones(
            @PageableDefault(size = 15,sort = "id",direction  = Sort.Direction.DESC) Pageable page,
            @RequestParam(value = "message",required = false) String message,
            @RequestParam(required = false) String busqueda
    ){
        log.infoRed("/thymeleaf/estimacion/");
        ModelAndView mav = new ModelAndView("listado");
        Page<EstimacionListado> estimaciones;
        if(isNull(busqueda)){
            estimaciones = estimacionService.traerListado(page);
        }else{
            estimaciones = estimacionService.traerListadoFiltrado(busqueda,page);
        }
        mav.addObject("listado",estimaciones);
        return mav;
    }
}
