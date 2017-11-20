package main.ru.ibs.test.controller;

import main.ru.ibs.test.dto.ComboBoxElem;
import main.ru.ibs.test.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private DataService dataService;

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ComboBoxElem> getAllStatuses() {
        return dataService.getAllPersons();
    }
}