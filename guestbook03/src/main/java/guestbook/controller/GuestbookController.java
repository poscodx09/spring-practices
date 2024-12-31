package guestbook.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import guestbook.repository.GuestbookRepository;
import guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	
	private GuestbookRepository guestbookRepository;
	
	public GuestbookController(GuestbookRepository guestbookRepository) {
		this.guestbookRepository = guestbookRepository;
	}
	
	@RequestMapping("/")
	public String index(/* HttpServletRequest request, */ Model model) {
//		ServletContext sc = request.getServletContext();
//		Enumeration<String> e = sc.getAttributeNames();
//		
//		while(e.hasMoreElements()) {
//			String name = e.nextElement();
//			System.out.println(name);
//		}
//		
//		ApplicationContext ac1 = (ApplicationContext) sc.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
//		ApplicationContext ac2 = (ApplicationContext) sc.getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.spring");
//		
//		GuestbookRepository repository = ac1.getBean(GuestbookRepository.class);
//		System.out.println(repository);
//		
//		GuestbookController controller = ac2.getBean(GuestbookController.class);
//		System.out.println(controller);
//		
		List<GuestbookVo> list = guestbookRepository.findAll();
		model.addAttribute("list", list);
		return "index";
	}
	
	
	@RequestMapping("/add")
	public String add(GuestbookVo vo) {
		guestbookRepository.insert(vo);
		return "redirect:/";
	}
	
	@RequestMapping("/deleteform/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "deleteform";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, @RequestParam("password") String password, Model model) {
		int count = guestbookRepository.deleteByIdAndPassword(id, password);
        if (count > 0) {
            return "redirect:/"; // 삭제 후 메인 페이지로 이동
        } else {
        	model.addAttribute("message", "비밀번호가 틀렸습니다.");
            return "deleteform"; // 실패 시 폼으로 다시 이동
        }
	}
	

}
