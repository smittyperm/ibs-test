package main.ru.ibs.test.controller;

import main.ru.ibs.test.dto.Claim;
import main.ru.ibs.test.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claim")
public class ClaimController {

    private DataService dataService;

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Claim> getAllClaims() {
        return dataService.getAllClaims();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Claim createClaim(@RequestBody Claim claim) {
        return dataService.createClaim(claim);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Claim getClaim(@PathVariable Long id) {
        return dataService.getClaim(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Claim updateClaim(@PathVariable Long id,
                                @RequestBody Claim claim) {
        return dataService.updateClaim(id, claim);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteClaim(@PathVariable Long id) {
        dataService.removeClaim(id);
    }
}
