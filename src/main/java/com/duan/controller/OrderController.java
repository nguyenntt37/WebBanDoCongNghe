package com.duan.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duan.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	
@RequestMapping("/order/checkout")
public String checkout() {
	return "order/checkout";
}
/* Địa chỉ tới liệt kê đơn hàng đã đặt */
@RequestMapping("/order/list")
public String list(Model model, HttpServletRequest request) {
	String username = request.getRemoteUser();						/* Lấy user đã đăng nhập */
	model.addAttribute("orders", orderService.findByUsername(username));
	return "order/list";
}
/* Địa chỉ tới trang chi tiết đơn hàng đã đặt */
@RequestMapping("/order/detail/{id}")
public String detail(@PathVariable("id") Long id, Model model) {
	model.addAttribute("order", orderService.findById(id));
	return "order/detail";
}
}
