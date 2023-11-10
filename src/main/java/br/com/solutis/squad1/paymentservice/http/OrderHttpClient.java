package br.com.solutis.squad1.paymentservice.http;

import br.com.solutis.squad1.paymentservice.dto.OrderPutDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("order-service")
public interface OrderHttpClient {
    @RequestMapping(method = RequestMethod.PUT, value = "/api/v1/orders/{id}")
    void update(@PathVariable("id") Long id, @RequestBody OrderPutDto order);
}
