package com.eshop.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("index")
	public ResponseEntity<?> getAll(){
		List<Product> products = pdao.findAll();
		List<JSONObject> entities = new ArrayList<JSONObject>();
		for (Product product : products) {
			JSONObject object = new JSONObject();
			object.put("id", product.getId());
			object.put("name", product.getName());
			object.put("image", "http://localhost/static/images/items/" + product.getImage());
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
		return ResponseEntity.ok().body(entities);
	}
	
	@RequestMapping("/detail/{id}")
	public ResponseEntity<?> detail(@PathVariable("id") Integer id) {
		Product p = pdao.getOne(id);		
		return ResponseEntity.ok().body(p);
	}

}
