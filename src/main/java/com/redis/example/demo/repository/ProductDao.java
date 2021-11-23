package com.redis.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.redis.example.demo.entity.Product;

@Repository
public class ProductDao {
	
	@Autowired
	private RedisTemplate<String, Object> template;
	public static final String HASH_KEY = "Product";
	
	public Product save(Product product) {
		template.opsForHash().put(HASH_KEY, product.getId(),product);
		return product;
	}
	
	public List<Object> finAll(){
		return template.opsForHash().values(HASH_KEY);  
	}
	
	public Product findProductById(int id){
		return (Product) template.opsForHash().get(HASH_KEY, id);
	}
	
	public String deleteProduct(int id) {
		template.opsForHash().delete(HASH_KEY,id);
		return "Product removed";
	}
	
}
