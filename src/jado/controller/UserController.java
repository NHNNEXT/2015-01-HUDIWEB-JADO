package jado.controller;

import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;
import jado.service.UserService;
import jado.service.SignUpService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
/*
 * \/user도메인 설계를 다시 고민해봐도 좋을것 같아요. 모든 컨트롤러에서 다 user를 사용하는것 같네요.
 */
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private SignUpService signUpService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String viewEditUserInfoPage(HttpSession session, Model model, ServletRequest req) {
		String userId = (String) session.getAttribute("userId");
		Customer customer = userService.selectUserById(userId);
		model.addAttribute("customer", customer);

		if (session.getAttribute("isSeller") != null) {
			Seller seller = userService.selectSellerById(userId);
			Shop shop = userService.selectShopByUrl(seller.getShopUrl());
			model.addAttribute("seller", seller);
			model.addAttribute("shop", shop);
		}
		if (req.getAttribute("errorMessage") != null) {
			return "encryptedReadyFailure";
		}
		return "editUser";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	protected String editUserInfo(String name, String phone, String address, String shopUrl, String shopPhone, String bank, String bankAccount, String isSeller, HttpSession session, Model model,
			ServletRequest req) {

		if (req.getAttribute("errorMessage") != null) {
			return "errorCommon";
		}
		String password = (String) req.getAttribute("password");
		String userId = (String) session.getAttribute("userId");
		Customer user = new Customer(userId, password, name, phone, address);
		Shop shop = new Shop(shopUrl, shopPhone);
		Seller seller = new Seller(userId, shopUrl, bank, bankAccount);

		userService.updateCustomer(user);

		if (isSeller == null)
			return "main";
		if (session.getAttribute("isSeller") == null) {
			signUpService.insertShop(shop);
			signUpService.insertSeller(seller);
		} else {
			userService.updateShop(shop);
			userService.updateSeller(seller);
		}
		return "main";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String processeUserPage(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		String isSeller = (String) session.getAttribute("isSeller");

		if (isSeller != null) {
			userService.removeSeller(userId);
			// TODO [우선순위 : 높음!!!]
			// Shop 정보랑 Shop의 내용 모두 지워야 함 Article, 등등 등등;
			// CART, CART_has_PRODUCT, MAIL_AUTH,
			// SELLER, SHOP, BOARD, ARTICLE, ARTICLE_COMMNET
			// 이건 정말 노답임 ㅠㅡㅠ
		}

		userService.removeCustomer(userId);
		session.invalidate();
		// TODO [우선순위 : 보통]
		// 또한 그냥 main로 돌아가는것이 아니라 회원탈퇴가 성공적으로 처리되었음을 알려주는 페이지로 이동하는것이 좋을 듯
		return "main";
	}
}
