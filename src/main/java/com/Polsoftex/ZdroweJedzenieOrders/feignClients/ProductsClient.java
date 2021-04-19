package com.Polsoftex.ZdroweJedzenieOrders.feignClients;

import com.Polsoftex.ZdroweJedzenieOrders.model.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

//TODO: A real address
@FeignClient(name = "ZdroweJedzenie-Products", url = "PLACEHOLDER/products")
public interface ProductsClient {

    @GetMapping("")
    List<ProductDTO> getProducts(@RequestParam(required = false, name="id") List<UUID> id);

    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable("id") UUID id);
}
