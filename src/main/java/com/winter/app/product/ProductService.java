package com.winter.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO; 
	
	public List<ProductDTO> list ()throws Exception{
		return productDAO.list();
	}
	
	public ProductDTO detail(ProductDTO productDTO)throws Exception{
		return productDAO.detail(productDTO);
	}
	
	public int add(ProductDTO productDTO)throws Exception{
		return productDAO.add(productDTO);
	}
	
	public int update(ProductDTO productDTO)throws Exception{
		return productDAO.update(productDTO);
	}
	
	public int delete(ProductDTO productDTO)throws Exception{
		return productDAO.delete(productDTO);
	}
}