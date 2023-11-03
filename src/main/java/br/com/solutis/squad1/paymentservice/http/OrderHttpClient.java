package br.com.solutis.squad1.paymentservice.http;

import br.com.solutis.squad1.paymentservice.dto.OrderPutDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("order-service")
public interface OrderHttpClient {
    @RequestMapping(method = RequestMethod.PUT, value = "/api/v1/orders")
    void update(@RequestParam Long id, @RequestBody OrderPutDto order);
}
