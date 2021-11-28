package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.GoodsService;
import com.service.MemberService;

@Controller
public class GoodsController {
	@Autowired
	GoodsService service;
	@Autowired
	MemberService mService;
	
	@RequestMapping("/loginCheck/orderDone")
	public String orderDone(OrderDTO oDTO,
			int orderNum, HttpSession session, RedirectAttributes xxx) {
		System.out.println(oDTO+"\t"+orderNum);
		MemberDTO dto=(MemberDTO)session.getAttribute("login");
		
		oDTO.setUserid(dto.getUserid());// 주문정보에 사용자 id 추가 
		//oDTO.setNum(orderNum);
		service.orderDone(oDTO, orderNum);// insert, delete	
		
		xxx.addFlashAttribute("oDTO", oDTO);
		return "redirect:../orderDone";//servlet-context.xml 에 주소등록 
	}
	
	@RequestMapping("/loginCheck/orderAllDone")
	public String orderAllDone(ArrayList<CartDTO> cList, @RequestParam("num") ArrayList<String> orderNumList,
			HttpSession session, RedirectAttributes xxx) {
		MemberDTO dto=(MemberDTO)session.getAttribute("login");
		System.out.println(cList);
		ArrayList<OrderDTO> oList = new ArrayList<>();
		for(CartDTO cDTO : cList) {//Cart정보와 member정보를 이용하여 OrderDTO 객체 생성 list에 저장
			OrderDTO oDTO = new OrderDTO();
			oDTO.setUserid(dto.getUserid()); // 주문정보에 사용자 id 추가 
			oDTO.setNum(cDTO.getNum());
			oDTO.setgCode(cDTO.getgCode());
			oDTO.setgName(cDTO.getgName());
			oDTO.setgPrice(cDTO.getgPrice());
			oDTO.setgAmount(cDTO.getgAmount());
			oDTO.setgSize(cDTO.getgSize());
			oDTO.setgColor(cDTO.getgColor());
			oDTO.setgImage(cDTO.getgImage());
			oDTO.setOrderName(dto.getUsername());
			oDTO.setPost(dto.getPost());
			oDTO.setAddr1(dto.getAddr1());
			oDTO.setAddr2(dto.getAddr2());
			oDTO.setPhone(dto.getPhone1()+dto.getPhone2()+dto.getPhone3());
			oDTO.setPayMethod(oDTO.getPayMethod());
			System.out.println(oDTO);
			oList.add(oDTO);
		}			
		service.orderAllDone(oList, orderNumList);	
		System.out.println(oList);
		
		xxx.addFlashAttribute("orderAllDone", oList);
		return "redirect:../orderAllDone";//servlet-context.xml 에 주소등록 
	}
	
	@RequestMapping("/loginCheck/orderConfirm")
	public String orderConfirm(@RequestParam("num") int num, HttpSession session, 
			RedirectAttributes xxx) {
		MemberDTO mDTO=(MemberDTO)session.getAttribute("login");
		String userid= mDTO.getUserid();
		mDTO= mService.myPage(userid); // id를 이용해 사용자 정보 가져오기 
		CartDTO cart= service.orderConfirmByNum(num); //num을 이용해 장바구니 정보가져오기 
		xxx.addFlashAttribute("mDTO", mDTO);  //request에 회원정보저장
		xxx.addFlashAttribute("cDTO", cart); //request에 카트정보저장	
		return "redirect:../orderConfirm"; //servlet-context에 등록
	}
	
	@RequestMapping("/loginCheck/orderAllConfirm")
	public String orderAllConfirm(@RequestParam("check") ArrayList<String> list, HttpSession session, 
			RedirectAttributes xxx) {
		MemberDTO mDTO=(MemberDTO)session.getAttribute("login");
		String userid= mDTO.getUserid();
		mDTO= mService.myPage(userid); //사용자 정보 가져오기 
		List<CartDTO> cart= service.orderAllConfirm(list); //장바구니 정보가져오기 
		xxx.addFlashAttribute("mDTO", mDTO);  //request에 회원정보저장
		xxx.addFlashAttribute("cList", cart); //request에 카트정보저장	
		return "redirect:../orderAllConfirm"; //servlet-context에 등록
	}
	
	@RequestMapping(value = "/loginCheck/delAllCart")
	public String delAllCart(@RequestParam("check") ArrayList<String> list) {
		System.out.println(list);
		service.delAllCart(list);
		return "redirect:../loginCheck/cartList"; // 카트리스트 다시 읽어오기
	}
	
	@RequestMapping(value = "/loginCheck/cartDelete")
	@ResponseBody
	public void cartDelte(@RequestParam("num") int num) {
		System.out.println(num);
		service.cartDelete(num);
	}
	@RequestMapping(value = "/loginCheck/cartUpdate")
	@ResponseBody
	public void cartUpdate(@RequestParam Map<String, String>map) {
		System.out.println(map);
		service.cartUpdate(map); // db 업데이트 후 개수 출력
	}
	
	@RequestMapping("/loginCheck/cartList") // interceptor를 통과함.
	public String cartList(RedirectAttributes attr, HttpSession session) {
		MemberDTO dto= (MemberDTO)session.getAttribute("login");
		String userid=dto.getUserid();
		List<CartDTO> list =service.cartList(userid);
		attr.addFlashAttribute("cartList", list); // 리다이렉트시 데이터(list)유지됨
		return "redirect:../cartList"; //servlet-context에 등록
		
	}
	@RequestMapping("/loginCheck/cartAdd")
	public String cartAdd(CartDTO cart, HttpSession session) {
		MemberDTO mDTO= (MemberDTO)session.getAttribute("login");
		cart.setUserid(mDTO.getUserid());
		session.setAttribute("mesg", cart.getgCode());
		service.cartAdd(cart);
		return "redirect:../goodsRetrieve?gCode="+cart.getgCode();
	}
	
	@RequestMapping("/goodsRetrieve") // view에 대한 지정이 없으므로 url이 뷰파일이 됨. goodsRetrieve.jsp
	@ModelAttribute("goodsRetrieve") // dto를 goodsRetrieve라는 key로 바인딩함.
	public GoodsDTO goodsRetrieve(@RequestParam("gCode") String gCode) {
		GoodsDTO dto= service.goodsRetrieve(gCode);
		return dto;		
	}
	
	@RequestMapping("/goodsList")
	public ModelAndView goodsList(@RequestParam("gCategory") String gCategory) { // 카테고리별 상품 목록보기
		if(gCategory== null) {
			gCategory="top";
		}
		List<GoodsDTO> list= service.goodsList(gCategory);
		ModelAndView mav= new ModelAndView();
		mav.addObject("goodsList", list);
		//request.setAttribute("goodsList", list)와 동일
		mav.setViewName("main"); //main.jsp 이동
		return mav;				
	}
}
