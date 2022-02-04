package com.green.sdsd;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		// ** Logger Message Test
		// 1) {} 활용
		logger.info("** Test1) 안녕하세요 ~~");
		logger.info("** Test1) 안녕하세요~~ {}님.","게스트");
		// 2) 변수 활용
		String name = "게스트"; int age=100;
		logger.info("** Test2) 이름:"+name+", 나이:"+age);
		logger.info("** Test2) 이름:{}, 나이:{}",name,age);
		
		// ** 로깅레벨 조정 Test (log4j.xml 의)
		// => root Tag 에서 출력 level 조정 (system 오류 level조정) 
		//	    <root> <priority value 값 >
		// => <logger name="com.ncs.green"> 에서 출력 level 조정
		//      <level value="DEBUG" />
		// => 이 두곳의 값을 warn (default) , error, debug, trace
		logger.warn("** 로깅레벨 Test warn , name => "+name) ;
		logger.error("** 로깅레벨 Test error , name => "+name) ;
		logger.debug("** 로깅레벨 Test debug , name => "+name) ;
		logger.trace("** 로깅레벨 Test trace , name => "+name) ;
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "home";
	} //home
	
// ** AjaxTest Main Form
	@RequestMapping(value = "/axtestf")
	public ModelAndView axtestf(ModelAndView mv) {
		mv.setViewName("axTest/axTestForm");
		return mv;
	} 
// ** BCryptPasswordEncoder Test
//@RequestMapping(value = "/bcrypt")
//@RequestMapping() -> 요청명과 메서드멸 동일한경우 생략가능하다고함 그러나 value 생략시 404 (확인필요함)
	public ModelAndView bcrypt(ModelAndView mv) {
		
		// PasswordEncoder (Interface) -> BCryptPasswordEncoder 구현클래스
		// => import org.springframework.security.crypto.password.PasswordEncoder;
		// => import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password="12345!" ; // rawData
		
		// 1) encode(rawData) => 동일 원본(rawData)에 대해 다른 Digest 생성 Test
		//    String digest1,2,3 = passwordEncoder.encode("password");
		//    모두 동일 rawData 적용후 모두 다른 digest 가 생성됨을 Test 해본다.
		
		String digest1 = passwordEncoder.encode(password) ;
		String digest2 = passwordEncoder.encode("22221!") ;
		String digest3 = passwordEncoder.encode("33333!") ;
		System.out.println("** digest1 => "+digest1);
		System.out.println("** digest2 => "+digest2);
		System.out.println("** digest3 => "+digest3);
		
		// 2) matches(rawData, digest)
		System.out.println("** matches1 => "+ passwordEncoder.matches(password, digest1));
		System.out.println("** matches2 => "+ passwordEncoder.matches(password, digest2));
		System.out.println("** matches3 => "+ passwordEncoder.matches(password, digest3));
		
		mv.setViewName("redirect:home");
		return mv;
	}//bcrypt
	
// ** Exception  Test *************************** 	
	@RequestMapping(value = "/etest")
	public ModelAndView etest(ModelAndView mv) {
		// 1. ArithmeticException
		// => 1.1) web.xml 적용
		// => 1.2) Spring exceptionResolver 적용 ( servlet~~~.xml )
		//int i = 100/0 ; // by zero, 500
		
		// ** 실수형 Zero 나눔 Test : Exception 발생하지 않음
		// => 그러므로 Zero 연산 확인은 
		//	  if (Double.isInfinity(d)) ~ ~ ,  if (Double.isNan(p)) ~~~ 
		double d = 100.0/0.0 ; // Infinity
		double p = 100.0%0.0 ; // NaN
		System.out.println("** ArithmeticException Test => " +d+" , "+p);
		
		// 2. IllegalArgumentException
		String s = "123" ;  // s="123a" : Error_Value
		int i = 100;
		//i += Integer.parseInt(s);
		if ( Integer.parseInt(s) == 0) i=100 ;
		System.out.println("** IllegalArgumentException Test => " + i);
		
		// 3. NullPointerException
		s=null;
		// if ( s.equals("test")) s="Test" ; -> NullPointerException 발생
		// => 예방코드
		if ( "test".equals(s) ) s="Test" ;
		else s="Null" ;
		System.out.println("** NullPointerException Test => " + s);
		
		// 4. defaultErrorView Test
		String[] city = {"성남","용인","서울"};
		System.out.println("** defaultErrorView Test => " + city[3]);
		// java.lang.ArrayIndexOutOfBoundsException : xml 에 정의하지 않음
		
		// 5. Sql Test 
		// => axJoin 으로  DuplicateKeyException , SqlException 
		
		mv.setViewName("redirect:home");
		return mv;
	} 
// ************************************************	
// ** KaKaoMap Test *******************************
	@RequestMapping(value = "/greensn")
	public ModelAndView greensn(ModelAndView mv) {
		mv.setViewName("kakaoMapJsp/map01_greenSN");
		return mv;
	}	
	@RequestMapping(value = "/greenall")
	public ModelAndView greenall(ModelAndView mv) {
		mv.setViewName("kakaoMapJsp/map02_greenAll");
		return mv;
	}
	@RequestMapping(value = "/jeju")
	public ModelAndView jeju(ModelAndView mv) {
		mv.setViewName("kakaoMapJsp/map03_jeju");
		return mv;
	}

// **************************************************	
// ** WebSocket Echo Test ***************************
	@RequestMapping(value = "/echo")
	public ModelAndView echo(ModelAndView mv) {
		mv.setViewName("axTest/wsEcho");
		return mv;
	} //echo
	
// ** WebSocket Chat Test **
	@RequestMapping(value = "/chat")
	public ModelAndView chat(ModelAndView mv) {
		mv.setViewName("axTest/wsChat");
		return mv;
	} //chat	
	
	
} //class
