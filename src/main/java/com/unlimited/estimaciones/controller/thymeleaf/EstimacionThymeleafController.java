package com.unlimited.estimaciones.controller.thymeleaf;

import com.unlimited.estimaciones.config.LoggerColor;
import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.dto.EstimacionListado;
import com.unlimited.estimaciones.entity.dto.EstimacionResponse;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/editarEstimacion")
    public ModelAndView obtenerPaginaEditarEstimacion(
            RedirectAttributes attributes,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String busqueda,
            @RequestParam(required = false) String actual
    ){
        System.out.println("id"+id);
        System.out.println("busqueda"+busqueda);
        System.out.println("actual"+actual);

        ModelAndView mav = new ModelAndView("editarEstimacion");

        EstimacionResponse estimacion = null;

        if(isNull(id)){
            try {
                id = Integer.parseInt(busqueda);
            }catch (Exception e){
                id = Integer.parseInt(actual);
            }
            estimacion = estimacionService.findById(id);
            if(isNull(estimacion)){
                estimacion = estimacionService.findById(Integer.parseInt(actual));
            }
        }else{
            estimacion = estimacionService.findById(id);
        }



        mav.addObject("estimacion",estimacion);
        return mav;
    }

}
