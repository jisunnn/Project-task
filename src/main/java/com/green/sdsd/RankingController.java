package com.green.sdsd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import service.AskService;
import service.RankingService;
import vo.AskVO;
import vo.PageVO;
import vo.RankingVO;

@Controller
public class RankingController {
	@Autowired
	RankingService service;

	
	@RequestMapping(value = "/ranklist")
	public ModelAndView ranklist(ModelAndView mv) {
		
		List<RankingVO> list = new ArrayList<RankingVO>();
    	list = service.selectList();
    	
    	// => Mapper 는 null 을 return 하지 않으므로 길이로 확인 
    	if ( list!=null && list.size()>0 ) mv.addObject("rankbanana", list);
    	else mv.addObject("message", "출력 자료가 없습니다 ");
		
    	mv.setViewName("board/ranking");
		return mv;
	} //alist
	
	
}
