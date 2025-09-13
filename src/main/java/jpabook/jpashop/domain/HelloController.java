package jpabook.jpashop.domain;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // model: Controller에 데이터를 실어서 View에 넘길 수 있다
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!!");
        return "hello"; // return: 화면이름. 관례상 templates에 hello.html 추가
    }
}
