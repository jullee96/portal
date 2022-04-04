package com.hamonize.portal.board;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.servlet.http.HttpSession;

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

            logger.info(el.getSitemapname());
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
        logger.info("bcid??? >> {}",bcid);
        vo = bcr.findByBcid("/board/"+bcid);    
        logger.info("getBcseq : {}", vo.getBcseq());        
        logger.info("board type : {}", vo.getBctype());
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


    // @RequestMapping("/view")
    // public String getview(Board vo, Model model) {


    //     return "/board/plain_board_view";
    // }




    @RequestMapping("/{bcid}/view/{bseq}")
    public String getview(@PathVariable ("bcid") String bcid, @PathVariable ("bseq") String bseq, Board vo, Model model) {
        logger.info("getview.....");
        logger.info("bcid > {}", bcid);

        BoardConfig cvo = bcr.findByBcid("/board/"+bcid);
        logger.info("pseq > {}", cvo.getPseq());
        Sitemap smvo = smr.findBySmseq(cvo.getPseq());
        logger.info("bseq > {}", vo.getBseq());
        vo = br.getByBseq(vo.getBseq());
        vo.setBcontent(vo.getBcontent().replaceAll("\"", "\'"));
        
        logger.info("content ?? {}",vo.getBcontent());
        model.addAttribute("sitemap",smvo);
        model.addAttribute("boardCfg",cvo);
        model.addAttribute("board", vo);

        return "/board/plain_board_view";
    }



    @RequestMapping("/{bcid}/list")
    @ResponseBody
    public String getlist(HttpSession session,BoardConfig vo) throws IOException {
        return "";
    }

}
