package mart.mono.purchases;

import mart.mono.cart.CartItem;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public Boolean purchase (@RequestBody List<CartItem> cart){
       return purchasesService.purchase(cart);
    }
}
