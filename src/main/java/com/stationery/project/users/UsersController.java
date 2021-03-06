package com.stationery.project.users;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.stationery.project.cart.CartDTO;
import com.stationery.project.cart.CartService;
import com.stationery.project.order.UsersOrderDTO;
import com.stationery.project.product.ProductDTO;
import com.stationery.project.product.ProductService;


@Controller
@RequestMapping("/users/*")
public class UsersController {

	@Autowired
	private UsersService usersService;
		
	// join form 이동
	@GetMapping("join")
	public void join() throws Exception {	
	}
	
	// joinCheck form 이동
	@GetMapping("joinCheck")
	public void joinCheck() throws Exception {
	}
	
	// 휴대폰 본인인증 form
	@GetMapping("identification")
	public void identification() throws Exception {
	}
	
	// join 기능
	@PostMapping("join")
	public String join(UsersDTO usersDTO, MultipartFile multipartFile) throws Exception {
		System.out.println(multipartFile.getOriginalFilename());
		System.out.println(multipartFile.getSize());
		int result = usersService.join(usersDTO, multipartFile);
		return "redirect:../";
	}
	
	// ID 중복 체크 기능
	@PostMapping("idChecker")
	@ResponseBody
	public int idCheck(@RequestParam("id") String id) throws Exception {
		int result = usersService.idChecker(id);
		return result;
	}
	
	// login form 이동
	@GetMapping("login")
	public void login(Model model, @CookieValue(value="remember", defaultValue="", required = false) String rememberId) throws Exception {
	}
	
	// login 기능
	@PostMapping("login")
	public String login(UsersDTO usersDTO, Model model, String remember, HttpSession httpSession, HttpServletResponse httpServletResponse) throws Exception {
		
		if(remember != null && remember.equals("1")) { // login.jsp의 remember 값이 null이 아니거나 "1"일 경우
			Cookie cookie = new Cookie("remember", usersDTO.getId()); // usersDTO의 ID값으로 쿠키 생성
			cookie.setMaxAge(-1);
			httpServletResponse.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("remember", ""); // 공백으로 쿠키 생성
			cookie.setMaxAge(0);
			httpServletResponse.addCookie(cookie);
		}
		
		usersDTO = usersService.login(usersDTO); // 로그인 기능
		
		String message = "로그인 실패";
		String p = "./login";
		
		if(usersDTO != null) {
			httpSession.setAttribute("auth", usersDTO); // "auth"에 로그인 세션 삽입
			
//			Cookie cookie1 = new Cookie("ordername", usersDTO.getName());
//			Cookie cookie2 = new Cookie("orderphone", usersDTO.getPhone());
//			Cookie cookie3 = new Cookie("orderaddress", usersDTO.getAddressDetail());
//			httpServletResponse.addCookie(cookie1);
//			httpServletResponse.addCookie(cookie2);
//			httpServletResponse.addCookie(cookie3);
			
			message = "로그인 성공";
			p = "../";
		}
		model.addAttribute("message", message);
		model.addAttribute("path", p);

		String path = "common/result";
		
		return path;
	}
	// logout 기능
	@GetMapping("logout")
	public String logout(HttpSession httpSession, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		httpSession.invalidate();
//		Cookie[] cookies = httpServletRequest.getCookies();
//		if(cookies != null) {
//			for(int i = 0; i < cookies.length; i++) {
//				cookies[i].setMaxAge(0);
//				httpServletResponse.addCookie(cookies[i]);
//			}
//		}
		
		return "redirect:../";
	}
	
	// wishlist form 이동
	@GetMapping("wishlist")
	public void wishlist(Model model, HttpSession httpSession) throws Exception {
		UsersDTO usersDTO = (UsersDTO)httpSession.getAttribute("auth");
		List<WishListDTO> list = usersService.wishlist(usersDTO);
		model.addAttribute("wishlistDTO", list);
		
	}
	
	// wishlist 제거
	@PostMapping("wishlistDelete")
	@ResponseBody
	public void wishlistDelete(HttpServletRequest request) throws Exception {
		String wishNum[] = request.getParameterValues("checkbox");
		List<String> wish = Arrays.asList(wishNum);
		int size = 1;
		size = wish.size();
		
		for(int i = 0; i < size; i++) {
			Long wiNum = Long.parseLong(wish.get(i));
			int result = usersService.wishlistDelete(wiNum);
		}
	}
	
	
	
	// wishlist 추가 기능
	@PostMapping("addWishList")
	public ModelAndView addWishList(WishListDTO wishListDTO) throws Exception{
		ModelAndView mv= new ModelAndView();
		int result=2;
		WishListDTO ck=usersService.wishlistCk(wishListDTO);
		if(ck==null) {
		usersService.addWishList(wishListDTO);
		result=1;
		}
		mv.setViewName("common/ajaxResult");
		mv.addObject("result",result);
		return mv;
	}
	
	// wishlist 삭제 기능
	@PostMapping("deleteWishList")
	public ModelAndView deleteWishList(WishListDTO wishListDTO) throws Exception{
		ModelAndView mv= new ModelAndView();
		int result=usersService.deleteWishList(wishListDTO);
		mv.setViewName("common/ajaxResult");
		mv.addObject("result",result);
		return mv;
	}
	
	// mypage form 이동
	@GetMapping("mypage")
	public void mypage(Model model, HttpSession httpSession) throws Exception {
		UsersDTO usersDTO = (UsersDTO)httpSession.getAttribute("auth");
		usersDTO = usersService.mypage(usersDTO);
		model.addAttribute("usersDTO", usersDTO);
	}
	
	// mychangecheck form 이동
	@GetMapping("mychangecheck")
	public void mychangecheck() throws Exception {
	}
	
	// mychangecheck 기능
	@PostMapping("mychangecheck")
	public String mychangecheck(UsersDTO usersDTO, Model model, HttpSession httpSession, HttpServletResponse httpServletResponse) throws Exception {
		
		usersDTO = usersService.login(usersDTO); // 로그인 기능
		
		String message = "아이디 또는 비밀번호가 일치하지 않습니다";
		String p = "./mychangecheck";
		
		if(usersDTO != null) {
			httpSession.setAttribute("mychange", usersDTO); // "mychange"에 로그인 세션 삽입
			message = "회원정보 수정 페이지로 이동합니다";
			p = "./mychange";
		}
		model.addAttribute("message", message);
		model.addAttribute("path", p);
		
		String path = "common/result";
		
		return path;
	}
	
	// mychange form 이동
	@GetMapping("mychange")
	public void mychange() throws Exception {
	}
	
	// infochange form 이동
	@GetMapping("infochange")
	public void infochange(HttpSession httpSession, Model model) throws Exception {
		UsersDTO usersDTO = (UsersDTO)httpSession.getAttribute("mychange");
		usersDTO = usersService.mypage(usersDTO);
		model.addAttribute("usersDTO", usersDTO);
	}
	
	// infochange 기능
	@PostMapping("infochange")
	public String infochange(UsersDTO usersDTO, MultipartFile multipartFile) throws Exception {
		int result = usersService.infochange(usersDTO, multipartFile);
		return "redirect:./mypage";
	}
	
	@PostMapping("fileDelete")
	public ModelAndView fileDelete(UsersDTO usersDTO, HttpSession httpSession) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = usersService.fileDelete(usersDTO, httpSession);
		
		mv.setViewName("common/ajaxResult");
		mv.addObject("result", result);
		
		return mv;
	}
	
	// pwchange form 이동
	@GetMapping("pwchange")
	public void pwchange(HttpSession httpSession, Model model) throws Exception {
		UsersDTO usersDTO = (UsersDTO)httpSession.getAttribute("auth");
		usersDTO = usersService.mypage(usersDTO);
		model.addAttribute("usersDTO", usersDTO);
	}
	
	// pwchange 기능
	@PostMapping("pwchange")
	public String pwchange(UsersDTO usersDTO) throws Exception {
		int result = usersService.pwchange(usersDTO);
		return "redirect:../";
	}
	
	// withdrawal form 이동
	@GetMapping("withdrawal")
	public void withdrawal() throws Exception {
	}	
	
	// withdrawal 기능
	@PostMapping("withdrawal")
	public String withdrawal(UsersDTO usersDTO, Model model, HttpSession httpSession, HttpServletResponse httpServletResponse) throws Exception {
		
		usersDTO = usersService.withdrawal(usersDTO); // 로그인 기능
		
		String message = "아이디 또는 비밀번호가 일치하지 않습니다";
		String p = "./withdrawal";
		
		if(usersDTO != null) {
			httpSession.setAttribute("withdrawal", usersDTO); // "withdrawal"에 로그인 세션 삽입
			message = "회원탈퇴로 진행합니다";
			p = "./withdrawalfinal";
		}
		model.addAttribute("message", message);
		model.addAttribute("path", p);
		
		String path = "common/result";
		return path;
	}
	
	// withdrawalfinal form 이동
	@GetMapping("withdrawalfinal")
	public void withdrawalfinal() throws Exception {
	}
	
	// withdrawalfinal 기능
	@PostMapping("withdrawalfinal")
	public String withdrawalfinal(UsersDTO usersDTO, HttpSession httpSession) throws Exception {
		UsersDTO users = (UsersDTO)httpSession.getAttribute("withdrawal");
		int result = usersService.withdrawalfinal(users);
		httpSession.invalidate();
		System.out.println(users.getId());
		return "redirect:../";
	}
	
	// orderlist form 이동
	@GetMapping("orderlist")
	public String orderlist(Model model, HttpSession httpSession) throws Exception {
		UsersDTO usersDTO = (UsersDTO)httpSession.getAttribute("auth");
		List<UsersOrderDTO> orderlist = usersService.orderlist(usersDTO);
		model.addAttribute("orderlist", orderlist);
		return "users/orderlist";
	}
	
	// orderdetail form 이동
	@GetMapping("orderDetail")
	public String orderDetail(Model model, UsersOrderDTO usersOrderDTO) throws Exception {
		usersOrderDTO = usersService.orderDetail(usersOrderDTO);
		model.addAttribute("orderDetail", usersOrderDTO);
		// System.out.println(usersOrderDTO.getOrderDetailDTOs().get(1).getProductDTOs().get(0).getThumbnail());
		return "users/orderDetail";
	}


}
