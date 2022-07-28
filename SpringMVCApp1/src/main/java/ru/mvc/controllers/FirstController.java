package ru.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        System.out.println("Hello, " + name + " " + surname);

        return "first/hello";
    }

    @GetMapping("/goodbye")

    public String goodbyePage(@RequestParam("name") String name,
                              @RequestParam(value = "surname", required = false) String surname,
                              Model model) { //параметры обязательные иначе код 400 или использовать required = false
        // System.out.println("Goodbye, " + name + " " + surname );
        model.addAttribute("message", "Goodbye, " + name + " " + surname);
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculatorPage(@RequestParam("a") int a,
                                 @RequestParam("b") int b,
                                 @RequestParam("action") String action,
                                 Model model) {
        double result;
        String printAction;
        switch (action) {
            case "multiplication":
                result = a * b;
                printAction="*";
                break;
            case "devision":
                result = a / (double) b;
                printAction="/";
                break;
            case "substraction":
                result = a - b;
                printAction="-";
                break;
            case "addition":
                result = a + b;
                printAction="+";
                break;
            default:
                result = 0;
                printAction="____";
                break;
        }

        model.addAttribute("result", a + printAction + b + "=" + result);
        return "first/calculator";
    }

}
