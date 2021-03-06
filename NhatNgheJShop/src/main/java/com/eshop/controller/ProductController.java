package com.eshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.bean.MailInfo;
import com.eshop.dao.ProductDAO;
import com.eshop.entity.Product;
import com.eshop.service.CookieService;
import com.eshop.service.MailerService;
import com.eshop.utils.XStr;

@Controller
public class ProductController {
	@Autowired
	ProductDAO dao;
	@Autowired
	MailerService mailer;
	@Autowired
	CookieService cookie;

	@RequestMapping("/product/find-by-category/{id}")
	public String listByCategory(@PathVariable("id") Integer categoryId, Model model) {
		List<Product> list = dao.findAllByCategory(categoryId);
		model.addAttribute("cateProduct", list);
		return "product/list";
	}

	@RequestMapping("/product/find-by-keywords")
	public String listByKeywords(@RequestParam("keywords") String keywords, Model model) {
		List<Product> list = dao.findByKeywords(keywords);
		model.addAttribute("cateProduct", list);
		return "product/list";
	}

	@RequestMapping("/product/find-by-special/{special}")
	public String listBySpecials(@PathVariable("special") Integer special, Model model) {
		List<Product> list;
		switch (special) {
		case 0:
			list = dao.findByBestSeller(PageRequest.of(0, 9));
			break;
		case 1:
			list = dao.findByLatest();
			break;
		case 2:
			list = dao.findBySaleOff();
			break;
		case 3:
			list = dao.findByMostView();
			break;
		case 4:
			list = dao.findBySpecial(PageRequest.of(0, 9));
			break;
		default:
			list = dao.findAll();
		}
		model.addAttribute("cateProduct", list);
		return "product/list";
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		// l???y id s???n ph???m ????? hi???n chi ti???t sp v?? t???ng s??? l?????ng ng?????i ???? xem sp
		// ghi nh???n s??? l???n click
		Product p = dao.getOne(id);
		p.setClickCount(p.getClickCount() + 1);
		dao.save(p);

		// l???y cookie c?? n???u cookie c?? ch??a c?? th?? l??u v??o
		// String s = cookie.getValue("visits", "");
		// List<String> ids = Arrays.asList(cookie.getValue("visits",
		// "").split("[,]+"));
		String ids = XStr.decB64(cookie.getValue("visits", ""));
		// m?? h??a c??c k?? t??? ?????c bi???t ????? cookie k b??? l???i
		if (!ids.contains(id.toString())) {
			ids += "," + id;
			cookie.create("visits", XStr.encB64(ids), 30);
		}

		// L???y danh s??ch m???t h??ng ???? xem
		List<Integer> list = new ArrayList<Integer>();
		for (String itemId : ids.substring(1).split(",")) {
			list.add(Integer.parseInt(itemId));
		}
		List<Product> visits = dao.findByIds(list);

		// b??? d???u ph???y
		// Arrays.as = ids.substring(1).split(",");
		model.addAttribute("visits", visits);
		model.addAttribute("prod", p);
		return "product/detail";
	}

	@ResponseBody
	@RequestMapping("/product/share/{id}")
	public void share(@PathVariable("id") Integer id, MailInfo mail, HttpServletRequest req) {
		String link = req.getRequestURL().toString().replace("share", "detail");
		String body = mail.getContent() + "<hr/> M???i b???n v??o click v??o " + link + " ????? xem chi ti???t s???n ph???m";
		mailer.send(mail.getSender(), mail.getReceiver(), mail.getSubject(), body);
		
	}

	
	@RequestMapping("/product/like/{id}")
	public String like(@PathVariable("id") Integer id) {
		List<Integer> ids = cookie.getFavoriteIds();
		// L???y danh s??ch m???t h??ng ???? xem
		if (!ids.remove(id)) {
			ids.add(id);
		}
		cookie.setFavoriteIds(ids);
		return "redirect:/layout/aside/favorite";
	}

}