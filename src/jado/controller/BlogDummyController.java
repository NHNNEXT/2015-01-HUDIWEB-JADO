package jado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BlogDummyController {
	
	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	/*
	 * servlet에서 spring에서 바꾼상황이므로 HttpServletResquest, HttpServletResponse 객체 주입은 필요 없을것 같아요.
	 * 그리고, 이름도 상황에 맞게 바꾸는게 좋을것 같아요.
	 * 
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 */
	public String blogDummy() {
		return "blogDummy";
	}

}
