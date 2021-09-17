package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.BadUrlException;

/**
 * this class is responsible for converting url to deep link or deep link to url
 */
public interface URLService {
    /**
     * converts given url to deeplink
     * OR
     * converts deeplink to url
     */
    String getLink(String url) throws BadUrlException;
}
