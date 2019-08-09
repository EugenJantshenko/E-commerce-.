package com.yanchenko.onlineshop.controller;

import com.yanchenko.onlineshop.domain.Employee;
import com.yanchenko.onlineshop.entity.Ware;
import com.yanchenko.onlineshop.entity.WareCategory;
import com.yanchenko.onlineshop.entity.WareType;
import com.yanchenko.onlineshop.repository.WareCategoryRepository;
import com.yanchenko.onlineshop.repository.WareRepository;
import com.yanchenko.onlineshop.repository.WareTypeRepository;
import com.yanchenko.onlineshop.service.impl.WareHouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private final WareRepository wareRepository;
    private final WareCategoryRepository wareCategoryRepository;
    private final WareTypeRepository wareTypeRepository;
    private final WareHouseServiceImpl wareHouseService;

    @Autowired
    public AdminController(WareRepository wareRepository, WareCategoryRepository wareCategoryRepository, WareTypeRepository wareTypeRepository) {
        this.wareRepository = wareRepository;
        this.wareCategoryRepository = wareCategoryRepository;
        this.wareTypeRepository = wareTypeRepository;
        wareHouseService = new WareHouseServiceImpl(wareRepository, wareCategoryRepository, wareTypeRepository);
    }

    @GetMapping("/allWares")
    public String showAllWare(Model model) {
        List<Ware> wares= wareHouseService.showWareList();
        model.addAttribute("wares", wares);
        return "listAllWares";
    }

    @GetMapping("/allTypes")
    public String showAllTypes(Model model){
        List<WareType> types =wareHouseService.showWareTypeList();
        model.addAttribute("types", types);
        return "listAllWareTypes";
    }

    @GetMapping("/allCategories")
    public String showAllCategories(Model model){
        List<WareCategory> categories =wareHouseService.showWareCategoryList();
        model.addAttribute("categories", categories);
        return "listAllWareCategories";
    }


    @GetMapping("/giveMeForm")
    public String getAddWareForm() {
        return "adminLists/addWareForm";
    }


    @RequestMapping(value = "/addWare", method = RequestMethod.POST)
    public String addWare(@ModelAttribute("ware") Ware ware,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            System.out.println("error");
            return "error";
        }
        else {
            System.out.println(ware.toString());
            model.addAttribute("ware", ware);

        }
        return "ware";
    }

}
