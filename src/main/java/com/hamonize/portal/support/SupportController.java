package com.hamonize.portal.support;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hamonize.portal.file.FileRepository;
import com.hamonize.portal.file.FileVO;
import com.hamonize.portal.user.SecurityUser;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/support")
public class SupportController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FileRepository fr;

    @Autowired
    SupportRepository sr;

    @Autowired
    CommentsRespository cr;

    @RequestMapping("/list")
    public String supportList(@RequestParam(required = false, defaultValue = "0", value = "page") int page, Pageable pageable ,  HttpSession session, Support vo, Model model) {
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");

        // paging
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC,"rgstrdate"));
        Page<Support> resultPage = sr.findAllByUserid(pageable, user.getUserid());
        logger.info("resultPage getTotalPages >>>> {}", resultPage.getTotalPages());
        logger.info("resultPage nextPageable >>>> {}", resultPage.nextPageable());

        List<Support> list = resultPage.getContent();
        List<Support> slist = new ArrayList<>();

        for (Support support : list) {
            support.setStatus(support.getStatus().trim());
            support.setType(support.getType().trim());
            support.setViewDate(support.getRgstrdate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            slist.add(support);
            
        } 
        Long totalCnt = sr.countByUserid(user.getUserid());
        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("tmpCnt", slist.size());
                    
        model.addAttribute("list", slist);
        model.addAttribute("nowPage", page);
        model.addAttribute("totalPage", resultPage.getTotalPages());

        return "/support/list";
	}



    @RequestMapping("/search")
    public String supportListSearch(String keyword, @RequestParam(required = false, defaultValue = "0", value = "page") int page, Pageable pageable ,  HttpSession session, Support vo, Model model) {
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        
        logger.info("\n\n\n <<< list >> page : {}", page);
        logger.info("keyword : {}", keyword);
        logger.info("userid : {}", user.getUserid());
        
        try {
            pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC,"rgstrdate"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            if(vo.getStartDate() != null && vo.getEndDate() != null ){ // keyword + page + 날짜 계산하는 경우
                LocalDateTime startDate = LocalDate.parse(vo.getStartDate(), formatter).atStartOfDay();
                LocalDateTime endDate = LocalDate.parse(vo.getEndDate(), formatter).atStartOfDay();
                endDate = endDate.plusHours(23).plusMinutes(59).plusSeconds(60);
                
                logger.info("endDate  >> {}", endDate);
                
                Page<Support> resultPage = sr.findAllByUseridAndTitleContainingIgnoreCaseAndRgstrdateBetween(pageable,user.getUserid(),keyword, startDate, endDate);
                List<Support> list = resultPage.getContent();
                List<Support> slist = new ArrayList<>();
                
                for (Support support : list) {
                    support.setStatus(support.getStatus().trim());
                    support.setType(support.getType().trim());
                    support.setViewDate(support.getRgstrdate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
                    slist.add(support);
                } 
                Long totalCnt = sr.countByUseridAndTitleContainingIgnoreCaseAndRgstrdateBetween(user.getUserid() ,keyword, startDate, endDate);
 
                model.addAttribute("totalCnt", totalCnt);
                model.addAttribute("tmpCnt", slist.size());

                // paging
                model.addAttribute("list", slist);
                model.addAttribute("nowPage", page);
                model.addAttribute("totalPage", resultPage.getTotalPages());
            
                model.addAttribute("startDate", vo.getStartDate());
                model.addAttribute("endDate", vo.getEndDate());

            }else{
                if( keyword.matches("[0-9]+") ){ //숫자만 있는 경우
                    Long tmpLong = Long.parseLong(keyword);
                    logger.info("tmpLong : {}", tmpLong);
                    logger.info("tmpLong type : {}", tmpLong.getClass());
    
                    Page<Support> resultPage = sr.findBySeq(pageable, tmpLong);
                    List<Support> list = resultPage.getContent();
                    List<Support> slist = new ArrayList<>();
                    
                    for (Support support : list) {
                        support.setStatus(support.getStatus().trim());
                        support.setType(support.getType().trim());
                        support.setViewDate(support.getRgstrdate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
                        slist.add(support);
                    } 
                    Long totalCnt = sr.countBySeq(tmpLong);
                    model.addAttribute("totalCnt", totalCnt);
                    model.addAttribute("tmpCnt", slist.size());
                    // paging
                    model.addAttribute("list", slist);
                    model.addAttribute("nowPage", page);
                    model.addAttribute("totalPage", resultPage.getTotalPages());
                   
                    model.addAttribute("keyword",keyword);

                } else{ //문자만 있는 경우
                    Page<Support> resultPage = sr.findByUseridAndTitleContainingIgnoreCase(pageable, user.getUserid(), keyword);
                    List<Support> list = resultPage.getContent();
                    List<Support> slist = new ArrayList<>();
                    
                    for (Support support : list) {
                        support.setStatus(support.getStatus().trim());
                        support.setType(support.getType().trim());
                        support.setViewDate(support.getRgstrdate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
                        slist.add(support);
                    }  
                    
                    Long totalCnt = sr.countByUseridAndTitleContainingIgnoreCase(user.getUserid(), keyword);
                    logger.info("totalCnt: {}",totalCnt);
                    logger.info("tmpCnt: {}", slist.size());

                    model.addAttribute("totalCnt", totalCnt);
                    model.addAttribute("tmpCnt", slist.size());
                   
            
                    // paging
                    model.addAttribute("list", slist);
                    model.addAttribute("nowPage", page);
                    model.addAttribute("totalPage", resultPage.getTotalPages());
                    
                    model.addAttribute("keyword",keyword);
                }

            }
            

        } catch (Exception e) {
            logger.error("[ERROR] string to long...{}", e);

        }
        return "/support/list";

        
     }

    @RequestMapping("/searchDate")
    public String supportListSearchDate(@RequestParam(required = false, defaultValue = "0", value = "page") int page, Pageable pageable, Support vo, Model model, HttpSession session) {
        logger.info("\n\n\n <<< list >> page : {}", page);
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");

        try {
            pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC,"rgstrdate"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            if(vo.getStartDate() != null){
                LocalDateTime startDate = LocalDate.parse(vo.getStartDate(), formatter).atStartOfDay();
                LocalDateTime endDate = LocalDate.parse(vo.getEndDate(), formatter).atStartOfDay();
                endDate = endDate.plusHours(23).plusMinutes(59).plusSeconds(60);
                
                logger.info("endDate  >> {}", endDate);
                
                Page<Support> resultPage = sr.findAllByUseridAndRgstrdateBetween(pageable, user.getUserid() ,startDate, endDate);
                List<Support> list = resultPage.getContent();
                List<Support> slist = new ArrayList<>();
                
                for (Support support : list) {
                    support.setStatus(support.getStatus().trim());
                    support.setType(support.getType().trim());
                    support.setViewDate(support.getRgstrdate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
                    slist.add(support);
                } 
                Long totalCnt = sr.countByUseridAndRgstrdateBetween(user.getUserid(), startDate, endDate);
 
                model.addAttribute("totalCnt", totalCnt);
                model.addAttribute("tmpCnt", slist.size());

                // paging
                model.addAttribute("list", slist);
                model.addAttribute("nowPage", page);
                model.addAttribute("totalPage", resultPage.getTotalPages());
            
                model.addAttribute("startDate", vo.getStartDate());
                model.addAttribute("endDate", vo.getEndDate());
        
            } else{
                LocalDateTime endDate = LocalDate.parse(vo.getEndDate(), formatter).atStartOfDay();
                endDate = endDate.plusHours(23).plusMinutes(59).plusSeconds(60);
                
                Page<Support> resultPage = sr.findAllByUseridAndRgstrdateLessThanEqual(pageable, user.getUserid(),endDate);
                List<Support> list = resultPage.getContent();
                List<Support> slist = new ArrayList<>();

                for (Support support : list) {
                    support.setStatus(support.getStatus().trim());
                    support.setType(support.getType().trim());
                    support.setViewDate(support.getRgstrdate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
                    slist.add(support);
                }
                    
                // paging
                model.addAttribute("list", slist);
                model.addAttribute("nowPage", page);
                model.addAttribute("totalPage", resultPage.getTotalPages());
                model.addAttribute("endDate", vo.getEndDate());
        
            }
            

        } catch (Exception e) {
            logger.error("[ERROR] string to long...{}", e);
        }
        
        return "/support/list";
	}


    @GetMapping("/apply")
    public String supportCreate(Support vo, HttpSession session, Model model) {
      
        return "/support/apply";
	}

    @GetMapping("/edit")
    public String supportEdit(Support vo, HttpSession session, Model model) {
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        Support edit = sr.findBySeq(vo.getSeq());
        model.addAttribute("edit", edit);
    
        return "/support/apply";
	}

    @GetMapping("/view")
    public String supportView(Support vo, HttpSession session, Model model) {
        List <Comments> list = cr.findAllBySupportseq(vo.getSeq());

        for (Comments cm : list) {
            cm.setViewDate(cm.getRgstrdate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
        }

        logger.info("seq >> {}", vo.getSeq());
        Support edit = sr.findBySeq(vo.getSeq());

        edit.setViewDate(edit.getRgstrdate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
        edit.setContents(edit.getContents().replace("\"", "\'"));
        logger.info("list length : {}", list.size());
        model.addAttribute("edit", edit);
        model.addAttribute("clist", list);
        model.addAttribute("clistSize", list.size());



        return "/support/view";
	}

    @RequestMapping("/save")
    @ResponseBody
    public Long save(HttpSession session, Support vo ) {
        Support ret = new Support();
        Long retval = (long) 0;
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        
        vo.setUserid(user.getUserid());
        
        if(vo.getSeq() != null ){
            vo.setUpdtdate(LocalDateTime.now());
            int aa = sr.update(vo);
            retval = (long) aa;
       
        } else{
            vo.setStatus("W"); // 답변대기
            vo.setRgstrdate(LocalDateTime.now());
            ret = sr.save(vo);
            retval = ret.getSeq();
        }
        
      
        return retval;
    }

    @RequestMapping("/delete")
    public String delete(HttpSession session, Support vo) throws Exception{
        sr.delete(vo);

        return "redirect:/support/list";
	}

    @RequestMapping("/getImageUrl")
    public void imgView(@RequestParam("seq") Integer seq, HttpSession session, HttpServletResponse response, Model model)  throws IOException {
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        FileVO file = fr.findByUseridAndSeq(user.getUserid(), seq );
        
        StringBuilder sb = new StringBuilder("file:"+ file.getFilepath());
        URL fileUrl = new URL(sb.toString());
        IOUtils.copy(fileUrl.openStream(), response.getOutputStream());
	}

}
