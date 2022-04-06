package com.hamonize.portal.board;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hamonize.portal.user.SecurityUser;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SitemapRepository smr;

    @Autowired
    BoardConfigRepository bcr;

    @Autowired
    BoardRepository br;

    @RequestMapping("/list")
    @ResponseBody
    public String getBoardList(HttpSession session,BoardConfig vo) throws IOException {

        List <Sitemap> slist = smr.findAll((Sort.by(Sort.Direction.ASC, "smseq")));
        List <BoardConfig> blist = bcr.findAllByBcused((Sort.by(Sort.Direction.ASC, "bcseq")),1);
        List<Object> list = new ArrayList <>();
            
        for( Sitemap el : slist){
            Map<String, Object> sitemap = new HashMap <String, Object>();

            sitemap.put("smseq", el.getSmseq());
            sitemap.put("sitemapname", el.getSitemapname());
            list.add(sitemap);
            
            for( BoardConfig bel : blist ){
                Map<String, Object> menu = new HashMap <String, Object>();
                if(el.getSmseq() == bel.getPseq()){
                    menu.put("bcseq", bel.getBcseq());
                    menu.put("bcid", bel.getBcid());
                    menu.put("bcname" ,bel.getBcname());
                    menu.put("bcrole" ,bel.getBcrole());
                    menu.put("bcdomains", bel.getBcdomains());
                    menu.put("bctype" ,bel.getBctype());
                    list.add(menu);
                }
            }
            
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(list);

        return jsonString;
    }

    @RequestMapping("/{bcid}")
    public String get(@PathVariable ("bcid")String bcid, HttpSession session, BoardConfig vo, Model model) throws IOException {
        vo = bcr.findByBcid("/board/"+bcid);    
        Sitemap smvo = smr.findBySmseq(vo.getPseq());
        List <Board> blist = br.findAllByBcseq((Sort.by(Sort.Direction.ASC, "bseq")),vo.getBcseq());   
        List <Board> list = new ArrayList<>();

        model.addAttribute("sitemap",smvo);
        model.addAttribute("boardCfg",vo);
       
        try {
            if(vo.getBctype().equals("wiki") ){
                blist.get(0).setBcontent(blist.get(0).getBcontent().replaceAll("\"", "\'"));
                model.addAttribute("board", blist.get(0));
    
                return "/board/plain_wiki";

            } else if(vo.getBctype().equals("board") ){
                for(Board el : blist){
                    el.setViewdate(el.getRgstrdate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
                    list.add(el);
                }
                model.addAttribute("boards", list);
    
                return "/board/plain_board_list";
            } else{
                return "/board/plain_wiki";
            }
        
        } catch (IndexOutOfBoundsException e) {
            logger.error("page가 없는경우 ");
            model.addAttribute("sitemap",smvo);
            model.addAttribute("boardCfg",vo);
            model.addAttribute("board", null);

            return "/board/plain_wiki";

        }
        
        
    }

    @RequestMapping("/{bcid}/view/{bseq}")
    public String getview(@PathVariable ("bcid") String bcid, HttpServletRequest request, HttpServletResponse response, HttpSession session, Board vo, Model model) throws IOException {
        BoardConfig cvo = bcr.findByBcid("/board/"+bcid);
        Sitemap smvo = smr.findBySmseq(cvo.getPseq());
            
        vo = br.getByBseq(vo.getBseq());
        vo.setBcontent(vo.getBcontent().replaceAll("\"", "\'"));
        vo.setViewdate(vo.getRgstrdate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
     
        SecurityUser user = (SecurityUser) session.getAttribute("userSession");
        Cookie[] cookies = request.getCookies();
        List<String> cnames = new ArrayList<>();

        try {
            logger.info("user id > {}", user.getUserid());
            logger.info("user seq > {}", user.getSeq());
        
            for(Cookie cookie : cookies){
                cnames.add(cookie.getName());

                if( cookie.getName().equals(user.getSeq().toString()) == true){
                    logger.info("cookie 22 : {}", cookie.getName());
                    
                    if(cookie.getValue().contains(vo.getBseq().toString())){
                        logger.info("이미 해당 페이지를 봄");
                    } else{
                        logger.info("새로운 페이지를 봄");
                        cookie.setValue(cookie.getValue()+'/'+vo.getBseq().toString());
                        response.addCookie(cookie);
                        vo.setViewcnt(vo.getViewcnt()+1); 
                        br.updateViewcnt(vo);
                    }
                }
                
           
            }   
            
            logger.info("cookies > {}", cnames.contains(user.getSeq().toString()));
            if(!cnames.contains(user.getSeq().toString())){
                Cookie newCookie = createAccNttIdCookie(user.getSeq().toString(), vo.getBseq().toString());
                response.addCookie(newCookie);
                vo.setViewcnt(vo.getViewcnt()+1); 
                br.updateViewcnt(vo);
            }  
        } catch (NullPointerException e) {
            logger.info("로그인 만료 ");
            response.sendRedirect("/login");
        }
       

        
        model.addAttribute("sitemap",smvo);
        model.addAttribute("boardCfg",cvo);
        model.addAttribute("board", vo);

        return "/board/plain_board_view";
    }



    private Cookie createAccNttIdCookie(String userid ,String cookieValue) {
        Cookie cookie = new Cookie(userid, cookieValue);
        cookie.setComment("조회수 중복 증가 방지 쿠키");    
        cookie.setMaxAge(getRemainSecondForTommorow());          
        cookie.setHttpOnly(true);                
        return cookie;
    }
    
    private int getRemainSecondForTommorow() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tommorow = LocalDateTime.now().plusDays(1L).truncatedTo(ChronoUnit.DAYS);
        return (int) now.until(tommorow, ChronoUnit.SECONDS);
    }
}
