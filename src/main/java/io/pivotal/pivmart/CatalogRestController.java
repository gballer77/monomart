package io.pivotal.pivmart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("api/catalogs")
public class CatalogRestController {

    @GetMapping
    public List<HashMap<String, Object>> list() {
        HashMap<String, Object> catalogItem = new HashMap<>();
        catalogItem.put("name", "");
        return asList(catalogItem);
    }

}
