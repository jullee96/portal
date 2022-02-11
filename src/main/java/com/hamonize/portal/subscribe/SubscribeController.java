package com.hamonize.portal.subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.servlet.http.HttpSession;
import com.hamonize.portal.user.SecurityUser;
import com.hamonize.portal.user.UserRepository;


@Controller
@RequestMapping("/subscribe")
public class SubscribeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SubscribeService ss;
    
    @Autowired
    UserRepository ur;

    @RequestMapping("/payment")
    public String paymentView(HttpSession session, Subscribe vo) {
        // 결제 정보는 있나 도메인정보가 없다면 도메인 생성페이지로
        // 결제 정보랑 도메인 정보 둘다 있다면 메인의 뉴저 메뉴 활성화 > 마이페이지로

        // SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        String userid = (String) session.getAttribute("userid");
        String domain = (String) session.getAttribute("domain");

        logger.info("getUserid >> {}",userid);
        logger.info("getDomain >> {}",domain);

        if( vo.getItemno() == null ){
            return "redirect:/"; 
        } else if(domain != null){ // 도메인이 이미 있으면 마이페이지로
            return "redirect:/main"; 
        }

        // session.setAttribute("itemno", vo.getItemno());

        return "/subscribe/payment";
	}

    /**
     * 결제 정보 추가 func
     * 
     * 결제할 아이템과 결제할 카드정보등 저장 -> tbl_subscribe_info
     * @param session > userid
     * @param Subscribe > vo
     * @return
     */
    @PostMapping("/savePayment")
    public String paymentSave(HttpSession session, Subscribe vo) {
        // SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        String userid = (String) session.getAttribute("userid");
        
        try {
            vo.setUserid(userid);
            ss.save(vo);
            return "redirect:/subscribe/domain";       
        } catch (Exception e) {
            return "redirect:/subscribe/payment";       
        }
     
	}

    @GetMapping("/domain")
    public String domainView(HttpSession session, Subscribe vo) {       
        String userid = (String) session.getAttribute("userid");
        String domain = (String) session.getAttribute("domain");     
        String itemno = (String) session.getAttribute("itemno");
        
        vo.setUserid(userid);
       
        // 결제 정보 && 생성된 도메인이 있는지 확인 
        int isExistSub = ss.findSubscribeInfo(vo.getUserid().toString());
        
        if(isExistSub == 0){ //결제 정보 없음
            return "redirect:/subscribe/payment?itemno="+itemno; 
       }else if(domain != null){ // 결제&도메인 둘다 있는 경우 마이페이지로 이동
           return "redirect:/main";

       }else{ // 결제는 했으나 도메인이 없는 경우
            return "/subscribe/domain";
        }

	}

    /**
     * 도메인 정보 추가 func
     * 
     * 결제 정보가 있는 경우만 도메인 생성가능
     * @param session
     * @param vo
     * @return
     */
    @PostMapping("/saveDomain")
    public String domianSave(HttpSession session, Subscribe vo) {
        
        String userid = (String) session.getAttribute("userid");
        String domain = (String) session.getAttribute("domain");     

        vo.setUserid(userid);
       
        logger.info("<<< 도메인 정보 >>> ");
        logger.info("도메인 이름 : {}", domain);
        logger.info("유저 아이디 : {}", vo.getUserid());
       
        ur.updateDomain(vo.getDomain(), vo.getUserid());

        return "redirect:/main";
        // return "redirect:/user/detail";

	}

}
