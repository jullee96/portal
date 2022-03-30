package com.hamonize.portal.board;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SitemapRepository smr;

    @Autowired
    BoardConfigRepository bcr;

    @RequestMapping("/list")
    @ResponseBody
    public String getBoardList(HttpSession session,BoardConfig vo) throws IOException {

        List <Sitemap> slist = smr.findAll((Sort.by(Sort.Direction.ASC, "smseq")));
        List <BoardConfig> blist = bcr.findAllByBoardused((Sort.by(Sort.Direction.ASC, "seq")),1);
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
                    menu.put("seq", bel.getSeq());
                    menu.put("boardid", bel.getBoardid());
                    menu.put("boardname" ,bel.getBoardname());
                    menu.put("boardrole" ,bel.getBoardrole());
                    menu.put("boardtype" ,bel.getBoardtype());
                    list.add(menu);
                }
            }
            
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(list);

        return jsonString;
    }

    @RequestMapping("/{boardid}")
    public String get(@PathVariable ("boardid")String boardid, HttpSession session,BoardConfig vo) throws IOException {

        logger.info("boardid??? >> {}",boardid);
        vo = bcr.findByBoardid("/board/"+boardid);    
        logger.info("board type : {}", vo.getBoardtype());
                
        if(vo.getBoardtype().equals("wiki") ){
            return "/board/plain_wiki";
        } else if(vo.getBoardtype().equals("board") ){
            return "/board/plain_board_list";
        } else{
            return "/board/plain_board_view";
        }

        
    }


    @RequestMapping("/{boardid}/view")
    @ResponseBody
    public String getview(HttpSession session,BoardConfig vo) throws IOException {


        return "";
    }



    @RequestMapping("/{boardid}/list")
    @ResponseBody
    public String getlist(HttpSession session,BoardConfig vo) throws IOException {


        return "";
    }

}
