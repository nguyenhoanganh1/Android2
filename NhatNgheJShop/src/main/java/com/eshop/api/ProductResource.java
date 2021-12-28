package com.eshop.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.dao.ProductDAO;
import com.eshop.entity.Product;

import net.minidev.json.JSONObject;


@RestController
@RequestMapping("/api/product")
public class ProductResource {
	
	@Autowired
	ProductDAO pdao;
	
	@GetMapping("/find-by-category/{id}")
	public ResponseEntity<?> listByCategory(@PathVariable("id") Integer categoryId) {
		List<Product> list = pdao.findAllByCategory(categoryId);
		JSONObject data = new JSONObject();
		List<JSONObject> entities = new ArrayList<JSONObject>();
		for (Product product : list) {
			JSONObject object = new JSONObject();
			object.put("id", product.getId());
			object.put("name", product.getName());
			object.put("image", "http://192.168.1.4/static/images/items/" + product.getImage());
			object.put("unitPrice", product.getUnitPrice());
			object.put("discount", product.getDiscount());
			object.put("quantity", product.getQuantity());
			object.put("productDate", product.getProductDate());
			object.put("description", product.getDescription());
			object.put("special", product.isSpecial());
			object.put("latest", product.isLatest());
			object.put("clickCount", product.getClickCount());
			entities.add(object);
		}
		data.put("data", entities);
		data.put("status", "ok");
		return ResponseEntity.ok().body(data);
	}
	
	@GetMapping("/find-by-special/{special}")
	public ResponseEntity<?> listBySpecials(@PathVariable("special") Integer special) {
		List<Product> list;
		List<JSONObject> entities = new ArrayList<JSONObject>();
		JSONObject data = new JSONObject();

		switch (special) {
		case 0:
			list = pdao.findByBestSeller(PageRequest.of(0, 9));
			for (Product product : list) {
				JSONObject object = new JSONObject();
				object.put("id", product.getId());
				object.put("name", product.getName());
				object.put("image", "http://192.168.1.4/static/images/items/" + product.getImage());
				object.put("unitPrice", product.getUnitPrice());
				object.put("discount", product.getDiscount());
				object.put("quantity", product.getQuantity());
				object.put("productDate", product.getProductDate());
				object.put("description", product.getDescription());
				object.put("special", product.isSpecial());
				object.put("latest", product.isLatest());
				object.put("clickCount", product.getClickCount());
				entities.add(object);
			}
			break;
		case 1:
			list = pdao.findByLatest();
			for (Product product : list) {
				JSONObject object = new JSONObject();
				object.put("id", product.getId());
				object.put("name", product.getName());
				object.put("image", "http://192.168.1.4/static/images/items/" + product.getImage());
				object.put("unitPrice", product.getUnitPrice());
				object.put("discount", product.getDiscount());
				object.put("quantity", product.getQuantity());
				object.put("productDate", product.getProductDate());
				object.put("description", product.getDescription());
				object.put("special", product.isSpecial());
				object.put("latest", product.isLatest());
				object.put("clickCount", product.getClickCount());
				entities.add(object);
			}
			break;
		case 2:
			list = pdao.findBySaleOff();
			for (Product product : list) {
				JSONObject object = new JSONObject();
				object.put("id", product.getId());
				object.put("name", product.getName());
				object.put("image", "http://192.168.1.4/static/images/items/" + product.getImage());
				object.put("unitPrice", product.getUnitPrice());
				object.put("discount", product.getDiscount());
				object.put("quantity", product.getQuantity());
				object.put("productDate", product.getProductDate());
				object.put("description", product.getDescription());
				object.put("special", product.isSpecial());
				object.put("latest", product.isLatest());
				object.put("clickCount", product.getClickCount());
				entities.add(object);
			}
			break;
		case 3:
			list = pdao.findByMostView();
			for (Product product : list) {
				JSONObject object = new JSONObject();
				object.put("id", product.getId());
				object.put("name", product.getName());
				object.put("image", "http://192.168.1.4/static/images/items/" + product.getImage());
				object.put("unitPrice", product.getUnitPrice());
				object.put("discount", product.getDiscount());
				object.put("quantity", product.getQuantity());
				object.put("productDate", product.getProductDate());
				object.put("description", product.getDescription());
				object.put("special", product.isSpecial());
				object.put("latest", product.isLatest());
				object.put("clickCount", product.getClickCount());
				entities.add(object);
			}
			break;
		case 4:
			list = pdao.findBySpecial(PageRequest.of(0, 9));
			for (Product product : list) {
				JSONObject object = new JSONObject();
				object.put("id", product.getId());
				object.put("name", product.getName());
				object.put("image", "http://192.168.1.4/static/images/items/" + product.getImage());
				object.put("unitPrice", product.getUnitPrice());
				object.put("discount", product.getDiscount());
				object.put("quantity", product.getQuantity());
				object.put("productDate", product.getProductDate());
				object.put("description", product.getDescription());
				object.put("special", product.isSpecial());
				object.put("latest", product.isLatest());
				object.put("clickCount", product.getClickCount());
				entities.add(object);
			}
			break;
		default:
			list = pdao.findAll();
			for (Product product : list) {
				JSONObject object = new JSONObject();
				object.put("id", product.getId());
				object.put("name", product.getName());
				object.put("image", "http://192.168.1.4/static/images/items/" + product.getImage());
				object.put("unitPrice", product.getUnitPrice());
				object.put("discount", product.getDiscount());
				object.put("quantity", product.getQuantity());
				object.put("productDate", product.getProductDate());
				object.put("description", product.getDescription());
				object.put("special", product.isSpecial());
				object.put("latest", product.isLatest());
				object.put("clickCount", product.getClickCount());
				entities.add(object);
			}
		}
		data.put("data", entities);
		data.put("status", "ok");
		return ResponseEntity.ok().body(data);
	}
	
	@GetMapping("/index")
	public ResponseEntity<?> getAll(){
		JSONObject data = new JSONObject();
		List<Product> products = pdao.findAll();
		List<JSONObject> entities = new ArrayList<JSONObject>();
		for (Product product : products) {
			JSONObject object = new JSONObject();
			object.put("id", product.getId());
			object.put("name", product.getName());
			object.put("image", "http://192.168.1.4/static/images/items/" + product.getImage());
			object.put("unitPrice", product.getUnitPrice());
			object.put("discount", product.getDiscount());
			object.put("quantity", product.getQuantity());
			object.put("productDate", product.getProductDate());
			object.put("description", product.getDescription());
			object.put("special", product.isSpecial());
			object.put("latest", product.isLatest());
			object.put("clickCount", product.getClickCount());
			entities.add(object);
		}
		data.put("data", entities);
		data.put("status", "success");

		return ResponseEntity.ok().body(data);
	}
	
	@RequestMapping("detail/{id}")
	public ResponseEntity<?> detail(@PathVariable("id") Integer id) {
		Product product = pdao.getOne(id);
		if(product != null) {
			JSONObject object = new JSONObject();
			object.put("id", product.getId());
			object.put("name", product.getName());
			object.put("image", "http://192.168.1.4/static/images/items/" + product.getImage());
			object.put("unitPrice", product.getUnitPrice());
			object.put("discount", product.getDiscount());
			object.put("quantity", product.getQuantity());
			object.put("productDate", product.getProductDate());
			object.put("description", product.getDescription());
			object.put("special", product.isSpecial());
			object.put("latest", product.isLatest());
			object.put("clickCount", product.getClickCount());
		}
		return ResponseEntity.ok().body(product);
	}

}
