package com.amplinno.wordpuzzle.Controller;

import com.amplinno.wordpuzzle.Form.WordResult;
import com.amplinno.wordpuzzle.Util.WordUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;

@Controller
public class HomeController {

    @Value("classpath:/static/words.txt")
    private Resource resourceFile;

    @RequestMapping("/")
    public String homePage(HttpSession session, Model model) throws IOException {
        WordResult wordResult = new WordResult();
        Integer noOfTry = 0;
        session.setAttribute("noOfTry", noOfTry);
        wordResult.setWord(WordUtil.maskWord(WordUtil.getRandomWord(resourceFile.getFile())));
        wordResult.setWordLength(wordResult.getWord().length());
        model.addAttribute(wordResult);
        return "index";
    }

    @SneakyThrows
    @PostMapping("/submitResult")
    public String submitResult(HttpSession session,
                               @ModelAttribute WordResult wordResult, Model model, HttpServletRequest request) {
        Integer noOfTry = (Integer) session.getAttribute("noOfTry");
        if (WordUtil.findWordInFile(resourceFile.getFile(), wordResult.getResult())) {
            wordResult.setMessage("Success !! Next Word for you");
            wordResult.setWord(WordUtil.maskWord(WordUtil.getRandomWord(resourceFile.getFile())));
            wordResult.setResult("");
            noOfTry = 0;
            session.setAttribute("noOfTry", noOfTry);
        } else {
            noOfTry += 1;
            session.setAttribute("noOfTry", noOfTry);

            if (wordResult.getWord().length() > wordResult.getResult().length()) {
                wordResult.setMessage("Wrong !! You have tried " + noOfTry + " times, Some hint --> Check your answered word length");
            } else {
                wordResult.setMessage("Wrong !! You have tried " + noOfTry + " times, please try one more time");
            }
        }
        System.out.println(noOfTry);
        return "index";
    }
}


