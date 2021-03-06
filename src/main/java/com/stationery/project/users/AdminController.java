package com.stationery.project.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stationery.project.board.BoardDTO;
import com.stationery.project.board.faq.FaqDAO;
import com.stationery.project.board.faq.FaqService;
import com.stationery.project.order.OrderDetailDTO;
import com.stationery.project.order.UsersOrderDTO;
import com.stationery.project.util.Pager;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private FaqService faqService;
	
	// manager form 이동
	// 관리자 페이지 접근에 대한 보안 취약점
	// 1. 유추하기 쉬운 디렉토리와 url명으로 설정하지 말것
	// 2. 인가된 특정 IP로만 접속이 가능하도록 설정
	// 3. Google OTP와 같은 이중보안 장치 설정
	@GetMapping("nf15ndf894khqiv730jifds")
	public void nf15ndf894khqiv730jifds() throws Exception {
//			Otp.getTOTPCode("CSXRCF3CHPTFBQJYDQS5VFXRVGX2IPYD");
//			Otp.getGoogleAuthenticatorBarCode("CSXRCF3CHPTFBQJYDQS5VFXRVGX2IPYD", "ksnx3684", "test");
//			Otp.createQRCode("otpauth://totp/test%3Aksnx3684?secret=CSXRCF3CHPTFBQJYDQS5VFXRVGX2IPYD&issuer=test", "C://H4_2021_2/QR.png", 300, 300);
	}
	
	
	@GetMapping("usersList")
	public void usersList(Model model, UsersDTO usersDTO) throws Exception {
		List<UsersDTO> list = usersService.usersList(usersDTO);
		model.addAttribute("usersList", list);
	}
	
	@GetMapping("usersDetail")
	public void usersDetail(Model model, UsersDTO usersDTO) throws Exception {
		usersDTO = usersService.usersDetail(usersDTO);
		model.addAttribute("usersDetail", usersDTO);
	}
	
	@GetMapping("usersOrderList")
	public void usersOrderList(Model model, UsersOrderDTO usersOrderDTO) throws Exception {
		List<UsersOrderDTO> list = usersService.usersOrderList(usersOrderDTO);
		model.addAttribute("usersOrderList", list);
	}
	
	@GetMapping("usersOrderDetail")
	public void usersOrderDetail(Model model, UsersOrderDTO usersOrderDTO) throws Exception {
		usersOrderDTO = usersService.usersOrderDetail(usersOrderDTO);
		model.addAttribute("usersOrderDetail", usersOrderDTO);
	}
	
	@GetMapping("usersOrderProduct")
	public void usersOrderProduct(Model model, OrderDetailDTO orderDetailDTO) throws Exception {
		List<OrderDetailDTO> list = usersService.usersOrderProduct(orderDetailDTO);
		model.addAttribute("usersOrderProduct", list);
	}
	
	@GetMapping("faqManage")
	public void faqManage(Model model, FaqDAO faqDAO, Pager pager) throws Exception {
		List<BoardDTO> list = faqService.list(pager);
		model.addAttribute("faqManage",list);
	}

}
