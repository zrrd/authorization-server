package cn.worken.auth.web;

import cn.worken.commons.resource.Permission;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 徐靖峰 Date 2018-04-19
 */
@RestController
public class TestEndpoints {

    @Permission("A")
    @GetMapping("/product/1")
    public String getProduct(String id, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @Permission("B")
    @GetMapping("/order/1")
    public String getOrder(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }


}
