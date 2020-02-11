package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/hello")
	public String index() {
		return "hello";
	}
	@RequestMapping("/hello2")
	public String hello2(@RequestParam(value="name") String name, Model model) {
		model.addAttribute("name",name);
		return "hello2";
	}
	@RequestMapping(value= {"/hello2","/hello2/{name}"})
	public String helloPath(@PathVariable Optional<String> name, Model model) {
		if(name.isPresent()) {
			model.addAttribute("name",name.get());
		}else {
			model.addAttribute("name","Phoenix");
		}
		return "hello2";
	}
	
	@RequestMapping(value= {"/calculator","/calculator/{params1}/{params2}"})
	public String calcuPath(@PathVariable int params1, @PathVariable int params2, Model model) {
		model.addAttribute("params1",params1);
		model.addAttribute("params2",params2);
		model.addAttribute("hasil",params1+params2);
		
		String hasilterbilang = "";
		
		String[] nominal={"","satu","dua","tiga","empat","lima","enam",
                "tujuh","delapan","sembilan","sepuluh","sebelas"};
		
		if(params1+params2 < 12)
        {
			hasilterbilang = nominal[(int)params1+params2];
        }else if(params1+params2 >= 12 && params1+params2 <= 19)
        {
        	hasilterbilang = nominal[(int)((params1+params2)%10)] +" belas";
        }else if(params1+params2 >= 20 && params1+params2 <= 99)
        {
        	hasilterbilang = nominal[(int)(params1+params2)/10] +" puluh "+nominal[(int)((params1+params2)%10)];
        }
            
        
		model.addAttribute("terbilang",hasilterbilang);
		return "calcuconvert";
	}
}
