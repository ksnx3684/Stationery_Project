package com.stationery.project.cart;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stationery.project.order.OrderDetailDTO;
import com.stationery.project.order.UsersOrderDTO;
import com.stationery.project.product.OptionDTO;
import com.stationery.project.product.ProductDTO;
import com.stationery.project.users.UsersDTO;

@Repository
public class CartDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.stationery.project.cart.CartDAO.";

	public CartDTO cartCk(CartDTO cartDTO)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"cartCk", cartDTO);
	}
	
	public List<CartDTO> cartlist(UsersDTO usersDTO) throws Exception {
		return sqlSession.selectList(NAMESPACE+"cartlist", usersDTO);
	}
	
	public OptionDTO cartoption(CartDTO cartDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"cartoption", cartDTO);
	}

	public int cartlistDelete(Long caNum) throws Exception {
		return sqlSession.delete(NAMESPACE+"cartlistDelete", caNum);
	}
	
	public CartDTO cartOrder(Long cartNum) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"cartOrder", cartNum);
	}
	
	public int order(UsersOrderDTO usersOrderDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"order", usersOrderDTO);
	}
	
	public int orderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"orderDetail", orderDetailDTO);
	}
	
	public int cartOrderDelete(Long cartNum) throws Exception {
		return sqlSession.delete(NAMESPACE+"cartOrderDelete", cartNum);
	}
	
	public Integer stock(Integer productNum) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"stock", productNum);
	}
	
	public Integer optionstock(OptionDTO optionDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"optionstock", optionDTO);
	}
	
	public int stockUpdate(ProductDTO productDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"stockUpdate", productDTO);
	}
	
	public int optionstockUpdate(OptionDTO optionDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"optionstockUpdate", optionDTO);
	}
	
}
