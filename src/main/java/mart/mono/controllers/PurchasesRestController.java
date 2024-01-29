package mart.mono.controllers;

import mart.mono.models.Purchase;
import mart.mono.repositories.PurchasesRepository;
import mart.mono.services.PurchasesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/purchases")
public class PurchasesRestController {

    private PurchasesService purchasesService;

    public PurchasesRestController(PurchasesService purchasesService) {
        this.purchasesService = purchasesService;
    }

    @GetMapping
    public List<Purchase> list() {
        return purchasesService.getAll();
    }
}
