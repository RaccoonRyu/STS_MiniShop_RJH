package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;

@Repository
public class GoodsDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public void orderDone(OrderDTO oDTO) {
		int n = template.insert("CartMapper.orderDone", oDTO);		
	}
	
	public CartDTO orderConfirmByNum(int num) {
		CartDTO dto = template.selectOne("CartMapper.cartByNum", num);
		return dto;
	}
	
	public List<CartDTO> cartList(String userid) {
		List<CartDTO> list = template.selectList("CartMapper.cartList", userid);
		return list;
	}
	public List<GoodsDTO> goodsList(String gCategory) {
		List<GoodsDTO> list= template.selectList("GoodsMapper.goodsList", gCategory);
		return list;
	}

	public GoodsDTO goodsRetrieve(String gCode) {
		GoodsDTO dto= template.selectOne("GoodsMapper.goodsRetrieve", gCode);
		return dto;
	}

	public void cartAdd(CartDTO cart) {
		template.insert("CartMapper.cartAdd", cart);		
	}
	public void cartUpdate(Map<String, String> map) {
		int n = template.update("CartMapper.cartUpdate", map);
		
	}
	public void cartDelete(int num) {
		int n= template.delete("CartMapper.cartDel", num);
		
	}
	public void delAllCart(ArrayList<String> list) {
		int n = template.delete("CartMapper.cartAllDel", list);
		
	}

	public List<CartDTO> orderAllConfirm(List<String> list) {
		List<CartDTO> n = template.selectList("CartMapper.orderAllConfirm", list);
		return n;
	}

	public void orderAllDone(ArrayList<OrderDTO> oList) {
		int n = template.insert("CartMapper.orderAllDone", oList);	
	}
	
}
