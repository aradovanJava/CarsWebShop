package hr.cars.webshop.carswebshop.controller.mvc;

import hr.cars.webshop.carswebshop.dto.CarDTO;
import hr.cars.webshop.carswebshop.event.CustomSpringEvent;
import hr.cars.webshop.carswebshop.model.CarSearchForm;
import hr.cars.webshop.carswebshop.model.ColorEnum;
import hr.cars.webshop.carswebshop.model.EngineTypeEnum;
import hr.cars.webshop.carswebshop.publisher.CustomSpringEventPublisher;
import hr.cars.webshop.carswebshop.service.CarsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mvc/carswebshop")
@AllArgsConstructor
@SessionAttributes({"carSearchForm", "engineTypes", "colors", "cars"})
public class CarsController {

    private CarsService carsService;
    private CustomSpringEventPublisher publisher;

    @GetMapping("/searchCars.html")
    public String showSearchCarsForm(Model model) {
        publisher.publishCustomEvent("CarsController :: Search cars screen displayed!");
        if(!model.containsAttribute("carSearchForm")) {
            model.addAttribute("carSearchForm", new CarSearchForm());
        }
        if(!model.containsAttribute("engineTypes")) {
            model.addAttribute("engineTypes", EngineTypeEnum.values());
        }
        if(!model.containsAttribute("colors")) {
            model.addAttribute("colors", ColorEnum.values());
        }
        if(!model.containsAttribute("cars")) {
            model.addAttribute("cars", carsService.getAllCars());
        }
        return "carsSearch";
    }

    @PostMapping("/searchCars.html")
    public String searchCars(CarSearchForm carSearchForm, Model model) {
        List<CarDTO> cars = carsService.filterCars(carSearchForm);
        model.addAttribute("cars", cars);
        return "redirect:/mvc/carswebshop/searchCars.html";
    }

    @GetMapping("/getAllCars.html")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carsService.getAllCars());
        return "cars";
    }

    @GetMapping("/getOneCar.html")
    public String getOneCar(@ModelAttribute("carId") Integer carId,
                            Model model)
    {
        model.addAttribute("car", carsService.getOneCar(carId));
        return "cars";
    }

    @GetMapping("/saveNewCar.html")
    public String showScreenForNewCar(Model model) {
        model.addAttribute("colorsList", ColorEnum.values());
        model.addAttribute("engineTypes", EngineTypeEnum.values());
        model.addAttribute("newCarDTO", new CarDTO());

        return "carsForm";
    }

    @PostMapping("/saveNewCar.html")
    public String saveNewCar(@ModelAttribute CarDTO carDTO, Model model) {
        model.addAttribute("newCarDTO", carDTO);
        carsService.addNewCar(carDTO);
        return "redirect:getAllCars.html";
    }

}
