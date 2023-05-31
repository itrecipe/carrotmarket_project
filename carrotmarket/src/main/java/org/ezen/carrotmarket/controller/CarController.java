package org.ezen.carrotmarket.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.ezen.carrotmarket.domain.CarAttachVO;
import org.ezen.carrotmarket.domain.CarVO;
import org.ezen.carrotmarket.domain.Criteria;
import org.ezen.carrotmarket.domain.PageDTO;
import org.ezen.carrotmarket.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller //컨트롤러 클래스로서 스프링 빈으로 등록한다.
@Log4j //log를 출력하기 위해 사용
@RequestMapping("/car") //받은 요청을 해당 메서드로 연결하며 클래스로 지정할시에는 공통 경로가 된다.
@AllArgsConstructor //lombok의 모든 멤버변수를 파라미터로 갖는 생성자를 생성해준다, 멤버변수가 하나일때 사용한다.
public class CarController {
	private CarService service; //CarService 인터페이스를 구현한 구현체를 주입받는 멤버 변수
	
	/* 페이징 처리 전 - list(R)
	@GetMapping("/list_car") //보통 보여주는 페이지는 getmapping으로 처리 해준다.
	public void list(Model model) {
		log.info("list_car");
		model.addAttribute("list_car", service.getList());
	}
	*/
	//@GetMapping("/list_car"): GET 요청이 "/list_car" 경로와 매핑,
	//Model 객체를 파라미터로 받아와서 list_car라는 이름으로 service.getList()의 결과를 모델에 추가
	
	//페이징 처리 후 - list(R)
	@GetMapping("/list_car")
	public void list(Criteria cri, Model model) {
		log.info("list_car");
		log.info(cri);
		model.addAttribute("list_car", service.getList(cri));
		
		//실제 게시글의 개수
		int total = service.getTotal(cri);
		log.info("total : " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	//게시글 등록창 보기
	@GetMapping("/register_car")
	public void registerForm() {
		log.info("registerForm : ");
	}
	//@GetMapping("/register_car"): GET 요청이 "/register_car" 경로와 매핑된다. registerForm 메서드는 로그를 출력한다.
	//반환 타입이 void이면 보여주는 페이지라고 생각하면 이해가 쉽다.
	//void는 반환타입이 없다는 의미이며, 스프링에서는 void를 사용하여 get 요청이 들어오면 "/register_car" 경로와 자동으로 매핑 해준다. 
	
	/*
	//게시글 등록 처리 - 첨부파일 처리 전
	@PostMapping("/register_car")
	public String register(CarVO car, RedirectAttributes rttr) {
		
		log.info("register : " + car);
		
		service.register(car);
		
		rttr.addFlashAttribute("result", car.getCno());
		
		return "redirect:list_car";
	}
	//@PostMapping("/register_car"): POST 요청이 "/register_car" 경로와 매핑된다.
	//CarVO 객체와 RedirectAttributes 객체를 파라미터로 받아와서 register 메서드가 실행된다.
	//register 메서드는 로그를 출력하고 service.register()를 호출하여 CarVO 객체를 등록한다.
	//그리고 RedirectAttributes 객체에 result라는 속성과 car.getCno()의 값을 추가한 후
	//"redirect:list_car"로 리다이렉트 한다.
	*/
	
	//게시글 등록 처리 - 첨부파일 처리 후
	
	//게시글 등록 처리 - 첨부파일 처리 전
		@PostMapping("/register_car")
		public String register(CarVO car, RedirectAttributes rttr) {
			
			log.info("register : " + car);
		
			if(car.getAttachList() != null) {
				car.getAttachList().forEach(attach -> log.info(attach));
			}
			
			service.register(car);
			
			rttr.addFlashAttribute("result", car.getCno());
			
			return "redirect:list_car";
		}
	
	@GetMapping({"/get_car", "/modify_car"})
	public void get(@RequestParam("cno") Long cno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("get_car & modify");
		
		model.addAttribute("car", service.get(cno));
	}
	
	//@GetMapping("/get_car"): GET 요청이 "/get_car", "/modify_car" 경로랑 매핑되며 배열 형태로 같이 작성해 주었다. 
	//Model 객체와 Long 타입의 cno 파라미터를 받아와서 get 메서드가 실행된다. 
	//get 메서드는 로그를 출력하고 service.get()을 호출하여 cno에 해당하는 자동차 정보를 가져온다. 
	//그리고 car라는 이름으로 모델에 추가 해준다.
	
	/* 첨부파일, 페이징 처리 전
	@PostMapping("/modify_car")
	public String modify(CarVO car, RedirectAttributes rttr) {
		
		log.info("modify_car" + car);
		
		if(service.modify(car)) {
			
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:list_car";
	}
	
//		@PostMapping 어노테이션이 붙은 modify_car 메서드이며, 이 메서드는 car 게시글 정보를 수정하는 기능을 처리한다.
//		CarVO 객체를 매개변수로 받고, 이 객체는 수정할 자동차의 정보를 담고 있고, 로그에 자동차 정보를 출력한다.
//		service.modify(car)를 호출하여 자동차 정보를 수정합니다. modify 메서드는 수정 작업이 성공하면 true를 반환하고, 실패하면 false를 반환합니다.
//		수정 작업이 성공한 경우, rttr.addFlashAttribute("result", "success")를 통해 "result"라는 이름으로 "success"라는 값을 리다이렉트 속성에 추가합니다. 이 값을 다음 페이지로 전달할 수 있습니다.
//		마지막으로, "redirect:list_car"를 반환하여 수정 작업이 끝난 후 자동차 목록 페이지로 리다이렉트 한다.
//		즉, 이 코드는 자동차 정보를 수정하고 수정 작업이 성공하면 "result" 속성에 "success" 값을 추가한 후 자동차 목록 페이지로 리다이렉트합니다.
	 */
	
	
	// 첨부파일, 페이징 처리 후
	@PostMapping("/modify_car")
	public String modify(CarVO car, Criteria cri, RedirectAttributes rttr) {
		
		log.info("modify_car" + car);
		
		if(service.modify(car)) {
			
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:list_car";
	}
	
	/* 페이징, 첨부파일 처리 전
	@PostMapping("/remove_car")
	public String remove(@RequestParam("cno") Long cno, RedirectAttributes rttr ) {
		
		log.info("remove_car : " + cno);
		
		if(service.remove(cno)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:list_car";
	}
	
//		POST 방식으로 "/remove" 경로에 요청이 들어왔을 때 실행되는 삭제 메서드
//		@PostMapping("/remove")는 POST 요청이 "/remove" 경로로 들어올 때 해당 메서드가 실행 되도록 매핑 해준다.
//		public String remove(@RequestParam("cno") Long cno, RedirectAttributes rttr)은 cno라는 요청 파라미터를 받아들이는 메서드 매개변수이며, 
//		@RequestParam("bno") 어노테이션을 통해 "bno"라는 이름의 요청 파라미터 값을 bno 변수에 바인딩(데이터나 값을 연결) 한다.
//		RedirectAttributes는 리다이렉트 시에 속성을 전달하기 위한 객체다. 
// 		rttr.addFlashAttribute("result", "success")는 리다이렉트 후에 "result"라는 이름으로 "success"라는 값을 전달한다. (1회성 데이터 처리 목적으로 사용)
//	    리다이렉트된 페이지에서 사용할 수 있는 속성으로 전달된다.
	*/
	
	// 페이징, 첨부파일 처리 후
	@PostMapping("/remove_car")
	public String remove(@RequestParam("cno") Long cno, Criteria cri, RedirectAttributes rttr ) {
		
		log.info("remove_car : " + cno);
		
		List<CarAttachVO> attachList = service.getAttachList(cno);
		
		if(service.remove(cno)) {
			
			//폴더에 있는 파일들을 삭제 한다.
			deleteFiles(attachList);
			
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addFlashAttribute("pageNum" + cri.getPageNum());
		rttr.addFlashAttribute("amount" + cri.getAmount());
		
		return "redirect:list_car";
	}
	
	
	//클라이언트에서 특정 게시물에 대한 첨부파일 정보를 요청하는 메서드
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CarAttachVO>> getAttachList(Long cno) {
		log.info("getAttachList : " + cno);
		log.info(service.getAttachList(cno));
		return new ResponseEntity<>(service.getAttachList(cno), HttpStatus.OK);
	}
	
	//첨부파일을 삭제하는 메서드
	
	private void deleteFiles(List<CarAttachVO> attachList) {
		
		if(attachList == null || attachList.size() == 0) {
			return;		
		}
		log.info("delete attach files...");
		log.info(attachList);
		
		attachList.forEach(attach -> {
			try {
				Path file = Paths.get(
						"c:/upload/" + attach.getUploadPath() + "/" + attach.getUuid() + "_" + attach.getFileName());
				
				Files.deleteIfExists(file);
				
				if(Files.probeContentType(file).startsWith("image")) {
					
					Path thumbNail = Paths.get("c:/uplaod/" + attach.getUploadPath() + "/s_" + attach.getUuid() + "_"
							+ attach.getFileName());
					
					Files.delete(thumbNail);
				}
			
			} catch (Exception e) {
				log.error("delete file error" + e.getMessage());
			}
		});
	}
}