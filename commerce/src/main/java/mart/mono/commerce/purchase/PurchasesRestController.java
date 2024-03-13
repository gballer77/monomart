package mart.mono.commerce.purchase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/purchases")
public class PurchasesRestController {

    private final PurchasesService purchasesService;

    public PurchasesRestController(PurchasesService purchasesService) {
        this.purchasesService = purchasesService;
    }

    @GetMapping
    public List<Purchase> list() {
        return purchasesService.getAll();
    }
}
