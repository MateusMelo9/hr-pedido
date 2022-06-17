package com.devmelo.hrpedido.feignclients;

import com.devmelo.hrpedido.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "hr-user", url = "localhost:54092", path = "/users")
public interface UserFeignClient {

    @GetMapping(value = "/search")
    public ResponseEntity<User> findUserByEmail(@RequestParam String email);
}
