package com.win.ldo.web.index;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("mvc")
public class TestMVCController {
	@RequestMapping(method = RequestMethod.GET)
	public String get() {
		System.err.println("get");
		return "get";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String post() {
		System.err.println("post");
		return "post";
	}

	@RequestMapping(value = "/{day}", method = RequestMethod.GET)
	public String getForDay(
			@PathVariable @DateTimeFormat(iso = ISO.DATE) Date day, Model model) {
		System.err.println(day.toString());
		return "getForDay";
	}
	public static void main(String[] args) {
		System.err.println(new Date());
	}
}
