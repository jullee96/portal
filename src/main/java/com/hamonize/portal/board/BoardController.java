package com.hamonize.portal.board;

import java.io.IOException;
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

        if(vo.getBctype().equals("wiki") ){
            model.addAttribute("sitemap",smvo);
            
            model.addAttribute("boardCfg",vo);
            model.addAttribute("board", blist.get(0));

            return "/board/plain_list";
        } else if(vo.getBctype().equals("board") ){
            return "/board/plain_list_board";
        } else{
            return "/board/plain_list";
        }

        
    }


    @RequestMapping("/{bcid}/view")
    @ResponseBody
    public String getview(HttpSession session,BoardConfig vo) throws IOException {


        return "";
    }



    @RequestMapping("/{bcid}/list")
    @ResponseBody
    public String getlist(HttpSession session,BoardConfig vo) throws IOException {


        return "";
    }

}
