package com.green.sdsd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import service.ChallengeService;
import vo.ChallengeVO;
import vo.ReplyVO;

@Controller
public class ChallengeController {

	@Autowired
	ChallengeService service;

	@RequestMapping(value = "/cinsertf")
	public ModelAndView loginf(ModelAndView mv) {
		mv.setViewName("challenge/cinsertForm");
		return mv;
	}

	@RequestMapping(value = "/cinsert")
	public ModelAndView nlist(HttpServletRequest request, ModelAndView mv, ChallengeVO vo, RedirectAttributes rttr)
			throws IOException {
		String uri = "redirect:clist";

		String realPath = request.getRealPath("/"); // deprecated Method
		System.out.println("** realPath => " + realPath);

		if (realPath.contains(".eclipse."))
			realPath = "C:/MTest/HJWork/DoDo/src/main/webapp/resources/image/";
		// realPath = "D:/MTest/HJWork/DoDo/src/main/webapp/resources/image/";
		// realPath =
		// "D:/MTest/HJWork/DoDo/src/main/webapp/resources/uploadImage/"+vo.getId()+"/";
		else
			realPath += "resources\\image\\";

		File f1 = new File(realPath);
		if (!f1.exists())
			f1.mkdir();

		String file1, file2 = "resources/image/basic.jpg";

		MultipartFile uploadfilef = vo.getThumbnailf();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			file1 = realPath + uploadfilef.getOriginalFilename();
			uploadfilef.transferTo(new File(file1));
			file2 = "resources/image/" + uploadfilef.getOriginalFilename();
		}
		vo.setThumbnail(file2);

		if (service.insert(vo) > 0) {
			service.participate(vo);
			rttr.addFlashAttribute("message", "※ 새글 등록 완료 ※");
		} else {
			mv.addObject("message", "※ 새글 등록 실패 ※");
			uri = "challenge/cinsertForm";
		}
		mv.setViewName(uri);
		return mv;
	} // cinsert

	@RequestMapping(value = "/clist")
	public ModelAndView nlist(ModelAndView mv) {
		List<ChallengeVO> list = new ArrayList<ChallengeVO>();
		list = service.selectList();
		if (list != null && list.size() > 0)
			mv.addObject("banana", list);
		else
			mv.addObject("message", "※ 출력 자료가 없습니다 ※");

		mv.setViewName("challenge/cList");
		return mv;
	} // clist

	@RequestMapping(value = "/cdetail")
	public ModelAndView bdetail(HttpServletRequest request, ModelAndView mv, ChallengeVO vo, ReplyVO rvo) {
		String uri = "challenge/cDetail";
		vo = service.selectOne(vo);
		if (vo != null) {
			if ("U".equals(request.getParameter("jcode")))
				uri = "challenge/cupdateForm";
			mv.addObject("apple", vo);
			/*
			 * List<ReplyVO> list = new ArrayList<ReplyVO>(); int seq = vo.getSeq();
			 * rvo.setSeq(Integer.parseInt(request.getParameter("seq"))); list =
			 * service.rList(); if ( list!=null && list.size() > 0) mv.addObject("cherry",
			 * list); else mv.addObject("message", "※ 댓글이 아직 없습니다 ※");
			 */
		} else {
			mv.addObject("message", "※ 글번호에 해당하는 자료가 없습니다 ※");
		}
		mv.setViewName(uri);
		return mv;
	} // cdetail

	@RequestMapping(value = "/checkTitle")
	public ModelAndView checkTitle(ModelAndView mv, ChallengeVO vo) {
		List<ChallengeVO> list = null;
		if (vo.getCheck() != null)
			list = service.checkTitle(vo);
		else
			list = service.selectList();

		if (list != null && list.size() > 0)
			mv.addObject("banana", list);
		else
			mv.addObject("message", "※ 출력할 자료가 1건도 없습니다 ※");

		mv.setViewName("challenge/cList");
		return mv;
	} // checkTitle

	@RequestMapping(value = "/checkCategory")
	public ModelAndView checkCategory(ModelAndView mv, ChallengeVO vo) {
		List<ChallengeVO> list = null;
		if (vo.getCheck() != null)
			list = service.checkCategory(vo);
		else
			list = service.selectList();

		if (list != null && list.size() > 0)
			mv.addObject("banana", list);
		else
			mv.addObject("message", "※ 출력할 자료가 1건도 없습니다 ※");

		mv.setViewName("challenge/cList");
		return mv;
	} // checkCategory

	@RequestMapping(value = "/checkState")
	public ModelAndView checkState(ModelAndView mv, ChallengeVO vo) {
		List<ChallengeVO> list = null;
		if (vo.getCheck() != null)
			list = service.checkState(vo);
		else
			list = service.selectList();
		if (list != null && list.size() > 0)
			mv.addObject("banana", list);
		else
			mv.addObject("message", "※ 출력할 자료가 1건도 없습니다 ※");

		mv.setViewName("challenge/cList");
		return mv;
	} // checkCategory

	@RequestMapping(value = "/cupdate")
	public ModelAndView bupdate(HttpServletRequest request, ModelAndView mv, ChallengeVO vo, RedirectAttributes rttr)
			throws IOException {
		String uri = "challenge/cList";
		MultipartFile uploadfilef = vo.getThumbnailf();
		String file1, file2;
		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			String realPath = request.getRealPath("/");
			if (realPath.contains(".eclipse."))
				realPath = "C:/MTest/HJWork/DoDo/src/main/webapp/resources/image/";
			// realPath = "D:/MTest/HJWork/DoDo/src/main/webapp/resources/image/";
			else
				realPath += "resources\\image\\";
			File f1 = new File(realPath);
			if (!f1.exists())
				f1.mkdir();
			file1 = realPath + uploadfilef.getOriginalFilename();
			uploadfilef.transferTo(new File(file1));
			file2 = "resources/image/" + uploadfilef.getOriginalFilename();
			vo.setThumbnail(file2);
		}
		if (service.update(vo) > 0) {
			rttr.addFlashAttribute("message", "※ 글수정 성공 ※");
		} else {
			rttr.addFlashAttribute("message", "※ 글수정 실패 ※");
			uri = "redirect:cdetail?jcode=U&seq=" + vo.getSeq();
		}
		mv.setViewName(uri);
		return mv;
	} // cupdate

	@RequestMapping(value = "/cdelete")
	public ModelAndView bdelete(HttpServletRequest request, ModelAndView mv, ChallengeVO vo, RedirectAttributes rttr) {
		String uri = "redirect:clist";
		if (service.delete(vo) > 0) {
			rttr.addFlashAttribute("message", "※ 글삭제 성공 ※");
		} else {
			rttr.addFlashAttribute("message", "※ 글삭제 실패 ※");
			uri = "redirect:cdetail?seq=" + vo.getSeq();
		}
		mv.setViewName(uri);
		return mv;
	} // cdelete

	@RequestMapping(value = "/participate")
	public ModelAndView patricipate(ModelAndView mv, ChallengeVO vo, RedirectAttributes rttr, HttpSession session) {
		String uri = "redirect:cdetail?seq=" + vo.getSeq();
		vo.setLoginID((String) session.getAttribute("loginID"));
		if (service.checkParticipate(vo) != null) {
			rttr.addFlashAttribute("message", "※ 해당 챌린지에 이미 참여하셨습니다 ※");
		} else {
			if (service.participate(vo) > 0) {
				rttr.addFlashAttribute("message", "※ 챌린지 참여 완료 ※");
			} else {
				mv.addObject("message", "※ 챌린지 참여 실패 ※");
				uri = "challenge/cList";
			}
		}
		mv.setViewName(uri);
		return mv;
	} // participate

	@RequestMapping(value = "/rrinsert")
	public ModelAndView rrinsert(ModelAndView mv, ReplyVO vo, RedirectAttributes rttr) {

		String uri = "redirect:cdetail?seq=" + vo.getSeq();
		vo.setStep(vo.getStep() + 1);

		if (service.rrinsert(vo) > 0) {
			rttr.addFlashAttribute("message", "※ 댓글 입력 성공 ※");
		} else {
			vo.setStep(vo.getStep() - 1);
			mv.addObject("message", "※ 댓글 입력 실패 ※");
		}
		mv.setViewName(uri);
		return mv;
	} // rrinsert

	@RequestMapping(value = "/rlist")
	public ModelAndView rlist(ModelAndView mv) {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		list = service.rList();
		if (list != null && list.size() > 0)
			mv.addObject("cherry", list);
		else
			mv.addObject("message", "※ 댓글이 아직 없습니다 ※");

		mv.setViewName("challenge/cList");
		return mv;
	} // rlist

}