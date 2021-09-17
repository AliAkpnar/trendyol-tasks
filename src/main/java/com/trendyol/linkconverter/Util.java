package com.trendyol.linkconverter;

import com.trendyol.linkconverter.repository.LinkType;
import com.trendyol.linkconverter.repository.model.UrlType;
import com.trendyol.linkconverter.service.UrlServiceImpl;

public class Util {
    public static boolean checkUrlGotValidChars(String url) {
        return url != null && url.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }

    public static UrlType getUrlType(String url) {
        if (url.startsWith("ty:")) {
            return UrlType.deeplink;
        }
        return UrlType.url;
    }

    public static LinkType getLinkType(String url) {
        if (url.contains(UrlServiceImpl.PRODUCT_IDENTIFIER) || url.contains("Page=Product")) {
            return LinkType.product;
        }
        return LinkType.search;
    }
}
