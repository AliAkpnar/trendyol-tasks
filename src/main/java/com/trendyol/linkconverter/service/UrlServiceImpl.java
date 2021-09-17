package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.BadUrlException;
import com.trendyol.linkconverter.Util;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("userService")
public class UrlServiceImpl implements URLService {

    public static final String PRODUCT_IDENTIFIER = "-p-";
    public static final String SEARCH_IDENTIFIER = "sr?";
    public static final  String QUERY_IDENTIFIER = "q=";

    private final String BASE_URL = "https://www.trendyol.com/";
    private final String BASE_DEEP_URL = "ty://?Page=";
    private final String DEEP_HOME_URL = "ty://?Page=Home";
    private final String HOME_URL = "https://www.trendyol.com/";


    private final String PRODUCT = "Product";
    private final String CONTENT_ID = "ContentId";
    private final String BOUTIQUE_ID = "boutiqueId";
    private final String MERCHANT_ID = "merchantId";
    private final String CAMPAIGN_ID = "CampaignId";

    private final String SEARCH = "Search";
    private final String QUERY = "Query";


    private static final Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);

    protected String deeplinkToUrl(String deepLink) throws BadUrlException {
        if (deepLink == null && deepLink.isEmpty()) {
            throw new BadUrlException("Url is empthy");
        }


        deepLink = deepLink.toLowerCase();
        boolean isItTyUrl = deepLink.contains(BASE_DEEP_URL.toLowerCase());

        if (isItTyUrl) {
                return createUrl(deepLink);
        } else {
            return deepLink;
        }
    }

    private String createUrl(String deepLink) {
        deepLink = deepLink.replaceAll(BASE_URL, "");

        if (deepLink.contains(PRODUCT.toLowerCase())) {
            return createUrlProductDetail(deepLink);
        }
        if (deepLink.contains(SEARCH.toLowerCase())) {
            return createUrlSearch(deepLink);
        }
        return BASE_URL.substring(0,BASE_URL.length()-1);
    }

    private String createUrlSearch(String deepLink) {
        String deepUrl = BASE_URL + SEARCH_IDENTIFIER+QUERY_IDENTIFIER  + getQuery(deepLink,QUERY+"=");
        return deepUrl;
    }

    private String createUrlProductDetail(String deepLink) {
        String newUrl = BASE_URL + "brand/" + "name" + PRODUCT_IDENTIFIER;

        newUrl +=  getContentIdDeep(deepLink);

        char nextSign = '?';
        if (deepLink.contains(CAMPAIGN_ID.toLowerCase())) {
            newUrl += "?"+BOUTIQUE_ID + "=" + getCampaignId(deepLink, CAMPAIGN_ID.toLowerCase());
            nextSign = '&';
        }

        if (deepLink.contains(MERCHANT_ID.toLowerCase())) {
            newUrl += nextSign + MERCHANT_ID + "=" + getMerchantId(deepLink);
        }

        return newUrl;
    }

    public String urlToDeepLink(String url) throws BadUrlException {
        if (url == null && url.isEmpty()) {
            throw new BadUrlException("Url is empthy");
        }


        url = url.toLowerCase();
        boolean isItTyUrl = url.contains("www.trendyol.com");
        boolean isItValidUrl = url.contains("boutiqueid") || !url.contains("Campaignid");


        if (isItTyUrl) {
            if (isItValidUrl) {
                return createDeepLink(url);
            } else {
                throw new BadUrlException("Url does not contain boutiqueId or contains campaignid");
            }
        } else {
            return url;
        }
    }

    protected String createDeepLink(String url) {
        url = url.replaceAll(BASE_URL, "");

        if (url.contains(PRODUCT_IDENTIFIER)) {
            return createDeepLinkProductDetail(url);
        }
        if (url.contains(SEARCH_IDENTIFIER)) {
            return createDeepLinkSearch(url);
        }
        return DEEP_HOME_URL;
    }

    private String createDeepLinkSearch(String url) {
        String deepUrl = BASE_DEEP_URL + SEARCH + "&" + QUERY + "=" + getQuery(url,QUERY_IDENTIFIER);
        return deepUrl;
    }

    protected String getQuery(String url,String queryIdentifier) {
        queryIdentifier = queryIdentifier.toLowerCase();
        int index = url.indexOf(queryIdentifier);

        return url.substring(index + queryIdentifier.length());
    }

    private String createDeepLinkProductDetail(String url) {
        String deepUrl = BASE_DEEP_URL + PRODUCT + "&" + CONTENT_ID + "=" + getContentId(url);

        if (url.contains(BOUTIQUE_ID.toLowerCase())) {
            deepUrl += "&" + CAMPAIGN_ID + "=" + getCampaignId(url, BOUTIQUE_ID.toLowerCase());
        }

        if (url.contains(MERCHANT_ID.toLowerCase())) {
            deepUrl += "&" + MERCHANT_ID + "=" + getMerchantId(url);
        }

        return deepUrl;
    }

    protected String getMerchantId(String url) {
        int indexOfMerchantId = url.indexOf(MERCHANT_ID.toLowerCase());
        if (url.substring(indexOfMerchantId + MERCHANT_ID.length() + 1).contains("&")) {
            /**
             * we need & but after merchant id
             */
            int indexOfAnd = url.substring(indexOfMerchantId + MERCHANT_ID.length() + 1).indexOf("&") + url.substring(0, indexOfMerchantId + MERCHANT_ID.length() + 1).length();

            return url.substring(indexOfMerchantId + MERCHANT_ID.length() + 1, indexOfAnd);
        }
        return url.substring(indexOfMerchantId + MERCHANT_ID.length() + 1);
    }

    protected String getCampaignId(String url, String storeId) {
        int indexOfBoutiqueId = url.indexOf(storeId.toLowerCase());
        if (url.substring(indexOfBoutiqueId + storeId.length() + 1).contains("&")) {
            int indexOfAnd = url.substring(indexOfBoutiqueId + storeId.length() + 1).indexOf("&") + url.substring(0, indexOfBoutiqueId + storeId.length() + 1).length();

            return url.substring(indexOfBoutiqueId + storeId.length() + 1, indexOfAnd);
        }
        return url.substring(indexOfBoutiqueId + storeId.length() + 1);
    }

    protected String getContentId(String url) {
        int indexOfP = url.indexOf(PRODUCT_IDENTIFIER);
        if (url.contains("?")) {
            int indexOfQuestionMark = url.indexOf("?");

            return url.substring(indexOfP + PRODUCT_IDENTIFIER.length(), indexOfQuestionMark);
        }


        return url.substring(indexOfP + PRODUCT_IDENTIFIER.length());
    }

    protected String getContentIdDeep(String url) {
        int indexOfP = url.indexOf(CONTENT_ID.toLowerCase() + "=");
        if (url.substring(indexOfP + (CONTENT_ID.toLowerCase() + "=").length() ).contains("&")) {
            int indexOfQuestionMark =  url.substring(indexOfP + (CONTENT_ID.toLowerCase() + "=").length() + 1).indexOf("&") + url.substring(0, indexOfP + (CONTENT_ID.toLowerCase() + "=").length() + 1).length();

            return url.substring(indexOfP + (CONTENT_ID + "=").length(), indexOfQuestionMark);
        }


        return url.substring(indexOfP + (CONTENT_ID + "=").length());
    }

    @Override
    public String getLink(String url) throws BadUrlException {
        if (url.startsWith("ty:")) {
            return deeplinkToUrl(url);
        } else {
            return urlToDeepLink(url);
        }
    }
}
