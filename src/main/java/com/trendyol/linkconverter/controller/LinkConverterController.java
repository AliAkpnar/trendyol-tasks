package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.BadUrlException;
import com.trendyol.linkconverter.Util;
import com.trendyol.linkconverter.repository.model.URLRequest;
import com.trendyol.linkconverter.service.DBService;
import com.trendyol.linkconverter.service.URLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

/**
 * this class is end point for api requests
 */

@Controller
public class LinkConverterController {

    @Autowired
    URLService urlService;

    @Autowired
    DBService dbService;

    private static final Logger logger = LoggerFactory.getLogger(LinkConverterController.class);

    @RequestMapping(value = "url", method = RequestMethod.POST)
    public void  getLink(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            String link = urlService.getLink(url);

            dbService.saveRequest(new URLRequest(url,link, Util.getUrlType(url),Util.getLinkType(url)));

            response.sendRedirect(link);
        } catch (BadUrlException | IOException e) {
            e.printStackTrace();
            // we will return url blank not deeplink but log the error
            logger.error(e.getMessage());
        }
    }
}
