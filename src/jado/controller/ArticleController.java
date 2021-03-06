package jado.controller;

import jado.model.Article;
import jado.model.ArticleComment;
import jado.model.Board;
import jado.model.Notice;
import jado.model.Shop;
import jado.service.ArticleService;
import jado.service.ShopService;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import core.exception.InsertTargetRecordNotFoundException;
import core.util.ModelAndViewUtils;

@Controller
@RequestMapping(value = "/shop/{shopUrl}/article")
public class ArticleController {
	@Autowired private ArticleService articleService;
	@Autowired private ShopService shopService;
	
	// article 보여주기
	@RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
	public String listGet(Model model, @PathVariable("articleId") String articleId, @PathVariable("shopUrl") String url, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		Article article = articleService.getArticle(Integer.parseInt(articleId));
		List<ArticleComment> comments = articleService.getComments(article.getId());
		Shop shop = shopService.getShopByUrl(url, userId);
		Board board = shopService.getBoard(article.getBoardId(), shop.getBoards());
		
		model.addAttribute("shop", shop);
		model.addAttribute("board", board);
		model.addAttribute("article", article);
		model.addAttribute("comments", comments);
		return "showArticle";
	}
	
	// article create form 보내주기
	@RequestMapping(value = "/write/{boardId}", method = RequestMethod.GET)
	public String wirteGet(Model model, @PathVariable("boardId") int boardId, @PathVariable("shopUrl") String url, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.getShopByUrl(url, userId);
		Board board = shopService.getBoard(boardId, shop.getBoards());
		
		model.addAttribute("shop", shop);
		model.addAttribute("board", board);
		return "boardForm";
	}

	// article create form 받아오기
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ModelAndView writePost(Article article, @PathVariable("shopUrl") String shopUrl, HttpSession session, Model model) {
		articleService.insertArticle(article);
		return new ModelAndView("redirect:/shop/"+shopUrl+"/board/"+article.getBoardId());
	}

	// article 수정 form 보내주기
	@RequestMapping(value = "/update/{articleId}", method = RequestMethod.GET)
	public String updateGet(Model model, @PathVariable("articleId") String articleId, @PathVariable("shopUrl") String url, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.getShopByUrl(url, userId);
		Article article = articleService.getArticle(Integer.parseInt(articleId));
		Board board = shopService.getBoard(article.getBoardId(), shop.getBoards());
		
		model.addAttribute("shop", shop);
		model.addAttribute("board", board);
		model.addAttribute("article", article);
		return "updateBoardForm";
	}

	// article 수정 form 받아오기
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView articleUpdatePost(Article article, @PathVariable("shopUrl") String shopUrl) {
		articleService.updateArticle(article);
		return new ModelAndView("redirect:/shop/"+shopUrl+"/article/"+article.getId());
	}

	// article 본문 삭제 구현
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView articleDeletePost(Integer articleId, @PathVariable("shopUrl") String shopUrl) {
		Article article = null;
		article = articleService.getArticle(articleId);
		articleService.deleteArticle(article.getId());
		return new ModelAndView("redirect:/shop/"+shopUrl+"/board/"+article.getBoardId());
	}
	
	/*
	 * 중복되는 예외는 아래처럼 controller 기반의 예외 핸들러를 쓰면 좋을 것 같아요.
	 * 참고: http://goo.gl/uuBkax
	 */
	@ExceptionHandler(InsertTargetRecordNotFoundException.class)
	public ModelAndView insertTargetRecordNotFoundException(InsertTargetRecordNotFoundException e) {
		return ModelAndViewUtils.renderToNoticeForSeller(new Notice("Failed", e.getMessage()));
	}
}
