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
import vo.AskVO;
import vo.PageVO;

@Controller
public class AskController {
	@Autowired
	AskService service;
	
	// ** Notice PageList 1.
			@RequestMapping(value = "/apagelist")
			public ModelAndView apagelist(ModelAndView mv, PageVO<AskVO> pvo) {
				
				int currPage = 1;
				if (pvo.getCurrPage() > 1) currPage = pvo.getCurrPage();
				else pvo.setCurrPage(currPage) ;
				
				int sno = (currPage-1)*pvo.getRowsPerPage() + 1 ;
				int eno = sno + pvo.getRowsPerPage() - 1 ;
				pvo.setSno(sno);
				pvo.setEno(eno);
				
				// 2) Service 처리
				// => List 읽어오기, 전체Row수(totalRowCount) 
				// => 전체 PageNo 계산하기
				pvo = service.pageList(pvo) ;
				int totalPageNo = pvo.getTotalRowCount()/pvo.getRowsPerPage();
				// 20/3 -> 6 나머지 2 : 6 page 와 2개 -> 7page
				if ( pvo.getTotalRowCount()%pvo.getRowsPerPage() !=0 )
					totalPageNo +=1;
				
				int sPageNo = ((currPage-1)/pvo.getPageNoCount())*pvo.getPageNoCount() + 1 ;
				int ePageNo = sPageNo + pvo.getPageNoCount() - 1 ;
				// 계산으로 얻어진 ePageNo가 실제 LastPage 인 totalPageNo 보다 크면 수정 필요.
				if ( ePageNo > totalPageNo ) ePageNo = totalPageNo ;
				mv.addObject("sPageNo", sPageNo);
				mv.addObject("ePageNo", ePageNo);
				mv.addObject("pageNoCount", pvo.getPageNoCount());
				
				// ** view01
				mv.addObject("currPage",currPage);
				mv.addObject("totalPageNo",totalPageNo);
				mv.addObject("abanana", pvo.getList());
				
				mv.setViewName("board/pageAList");
				return mv;
			}//bpagelist
	
	
	// ** Reply Insert **
		@RequestMapping(value = "/ansinsertf")
		public ModelAndView ansinsertf(ModelAndView mv, AskVO vo) {
			// => vo 에는 전달된 부모글의 root, step, indent 가 담겨있음 
			// => 매핑메서드의 인자로 정의된 vo 는 request.setAttribute 와 동일 scope
			//    단, 클래스명의 첫글자를 소문자로 ...  ${boardVO.root}
			mv.setViewName("board/answerInput");
			return mv;
		} //ansinsertf
		
		@RequestMapping(value = "/ansinsert")
		public ModelAndView ansinsert(ModelAndView mv, AskVO vo, RedirectAttributes rttr) {
			
			String uri="redirect:apagelist";
			vo.setStep(vo.getStep()+1);
	        vo.setIndent(vo.getIndent()+1);
			if (service.ansinsert(vo) > 0) {
				// 답글 입력 성공 -> alist
				rttr.addFlashAttribute("message","~~ 답글 입력 성공 ~~");
			}else {
				// 답글 입력 실패 -> 재입력 유도 (rinsertForm)
				// => step, indent 값을 부모의 값으로 변경한 후, forward
				vo.setStep(vo.getStep()-1);
				vo.setIndent(vo.getIndent()-1);
				mv.addObject("message","~~ 답글 입력 실패 !! 다시하세요 ~~");
				uri="board/answerInput";
			}
			mv.setViewName(uri);
			return mv;
		} //ansinsert
		
       
	@RequestMapping(value = "/alist")
	public ModelAndView alist(ModelAndView mv) {
		
		List<AskVO> list = new ArrayList<AskVO>();
    	list = service.selectList();
    	
    	// => Mapper 는 null 을 return 하지 않으므로 길이로 확인 
    	if ( list!=null && list.size()>0 ) mv.addObject("abanana", list);
    	else mv.addObject("message", "출력 자료가 없습니다 ");
		
    	mv.setViewName("board/askList");
		return mv;
	} //alist
	
	@RequestMapping(value = "/adetail")
	public ModelAndView adetail(HttpServletRequest request, ModelAndView mv, AskVO vo) {
		
		String uri = "board/askDetail";
		vo = service.selectOne(vo);
    	if ( vo!=null ) {
    		mv.addObject("aapple", vo);
    	}else {
    		mv.addObject("message", "글번호에 해당하는 자료가 없습니다");
    	}
		mv.setViewName(uri);
		return mv;
	} //adetail
	
	@RequestMapping(value = "/ainsertf")
	public ModelAndView ainsertf(ModelAndView mv) {
		mv.setViewName("board/askInput");
		return mv;
	} //ainsertf
	
	@RequestMapping(value = "/ainsert")
	public ModelAndView ainsert(ModelAndView mv, AskVO vo, RedirectAttributes rttr) {
		
		String uri = "redirect:apagelist";
		if ( service.insert(vo) > 0 ) { 
    		// 글등록 성공 -> nlist , redirect
    		rttr.addFlashAttribute("message", "~~ 새글 등록 완료 !!! ~~");
    	}else {
    		mv.addObject("message", "~~ 새글 등록 실패 !!! ~~");
    		uri = "board/askInput";
    	}
		mv.setViewName(uri);
		return mv;
	} //ainsert
	
	 @RequestMapping(value = "/aupdatef") 
	  public ModelAndView aupdatef(ModelAndView mv, AskVO vo) { 
		  vo = service.selectOne(vo);
		  mv.addObject("aapple", vo);
		  mv.setViewName("board/aupdateForm"); 
		  return mv;
	  }
	
	@RequestMapping(value = "/aupdate")
	public ModelAndView aupdate(ModelAndView mv, AskVO vo, RedirectAttributes rttr) {
		
		String uri = "redirect:apagelist";
		if ( service.update(vo) > 0 ) { 
    		// 글수정 성공 -> blist : redirect
    		rttr.addFlashAttribute("message", "~~ 글수정 성공 !!! ~~");
    	}else {
    		rttr.addFlashAttribute("message", "~~ 글수정 실패 !!! 다시 하세요 ~~");
    		uri = "redirect:adetail?jcode=U&seq="+vo.getSeq();
    	}
		mv.setViewName(uri);
		return mv;
	} //aupdate
	
	@RequestMapping(value = "/adelete")
	public ModelAndView adelete(ModelAndView mv, AskVO vo, RedirectAttributes rttr) {
		
		String uri = "redirect:apagelist";
		if ( service.delete(vo) > 0 ) { 
    		// 글삭제 성공 -> alist  : redirect
			rttr.addFlashAttribute("message", "~~ 글삭제 성공 !!! ~~");
    	}else {
    		rttr.addFlashAttribute("message", "~~ 글삭제 실패 !!! ~~");
    		uri = "redirect:adetail?seq="+vo.getSeq();
    	}
		mv.setViewName(uri);
		return mv;
	} //adelete	
	
	
	
}
