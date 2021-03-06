package com.stationery.project.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stationery.project.cart.CartDTO;
import com.stationery.project.util.Pager;


@Repository
public class ProductDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE="com.stationery.project.product.ProductDAO.";
	
	public List<ProductDTO> subCateList(Pager pager)throws Exception{
		return sqlSession.selectList(NAMESPACE+"subCateList",pager);
	}
	
	public OptionDTO optionCk(OptionDTO optionDTO)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"optionCk",optionDTO);
	}
	
	public int addCart(CartDTO cartDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"addCart", cartDTO);
	}
	
	public int productStockUpdate(ProductDTO productDTO)throws Exception{
		return sqlSession.update(NAMESPACE+"productStockUpdate", productDTO);
	}
	
	public int stockUpdate(OptionDTO optionDTO)throws Exception{
		return sqlSession.update(NAMESPACE+"stockUpdate", optionDTO);
	}

	public int optionDelete(OptionDTO optionDTO)throws Exception{
		return sqlSession.delete(NAMESPACE+"optionDelete",optionDTO);
	}
	
	public int optionAdd(OptionDTO optionDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"optionAdd", optionDTO);
	}
	
	public List<OptionDTO> optionList(ProductDTO productDTO)throws Exception{
		return sqlSession.selectList(NAMESPACE+"optionList",productDTO);
	}
	
	
	public int updateThumbnail(ProductDTO productDTO) throws Exception{
		return sqlSession.update(NAMESPACE+"updateThumbnail", productDTO);
	}
	
	public List<ProductFileDTO> listFile(ProductDTO productDTO)throws Exception{
		return sqlSession.selectList(NAMESPACE+"listFile",productDTO);
	}

	
	public int fileDelete(ProductFileDTO productFileDTO)throws Exception{
		return sqlSession.delete(NAMESPACE+"fileDelete",productFileDTO);
	}
	
	
	
	public int addFile(ProductFileDTO productFileDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"addFile",productFileDTO);
	}
	
	public List<ProductDTO> list(Pager pager) throws Exception{
		return sqlSession.selectList(NAMESPACE+"list",pager);
	}
	public Long total(Pager pager)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"total", pager);
	}
	
	public ProductDTO detail(ProductDTO productDTO) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"detail",productDTO);
	}
	
	public int add(ProductDTO productDTO) throws Exception{
		return sqlSession.insert(NAMESPACE+"add",productDTO);
	}
	
	public int delete(ProductDTO productDTO) throws Exception{
		return sqlSession.delete(NAMESPACE+"delete",productDTO);
	}
	
	public int update(ProductDTO productDTO) throws Exception{
		return sqlSession.update(NAMESPACE+"update", productDTO);
	}
	
	public ProductDTO detailProduct(ProductDTO productDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"detailProduct", productDTO);
	}
}
