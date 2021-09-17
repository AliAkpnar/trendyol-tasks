package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.BadUrlException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UrlServiceTest {

    String url1 = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064";
    String url2 = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865";
    String url3 = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?boutiqueId=439892";
    String url4 = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?merchantId=105064";
    String url5 = "https://www.trendyol.com/sr?q=elbise";
    String url6 = "https://www.trendyol.com/sr?q=%C3%BCt%C3%BC";
    String url7 = "https://www.trendyol.com/Hesabim/Favoriler";
    String url8 = "https://www.trendyol.com/Hesabim/#/Siparislerim";

    String deeplink1 = "ty://?Page=Product&ContentId=1925865&CampaignId=439892&merchantId=105064";
    String deeplink2 = "ty://?Page=Product&ContentId=1925865";
    String deeplink3 = "ty://?Page=Product&ContentId=1925865&CampaignId=439892";
    String deeplink4 = "ty://?Page=Product&ContentId=1925865&merchantId=105064";
    String deeplink5 = "ty://?Page=Search&Query=elbise";
    String deeplink6 = "ty://?Page=Search&Query=%C3%BCt%C3%BC";
    String deeplink7 = "ty://?Page=Home";
    String deeplink8 = "ty://?Page=Home";

    UrlServiceImpl urlService;

    @Before
    public void initVariables() {
        urlService = new UrlServiceImpl();
    }


    @Test
    public void productIdTest() {
        assertEquals("1925865", urlService.getContentId(url1));
        assertEquals("1925865", urlService.getContentId(url2));
        assertEquals("1925865", urlService.getContentId(url3));
        assertEquals("1925865", urlService.getContentId(url4));
    }

    @Test
    public void boutiqueIdTest() {
        assertEquals("439892", urlService.getCampaignId(url1, UrlServiceImpl.QUERY_IDENTIFIER));
        assertEquals("439892", urlService.getCampaignId(url3, UrlServiceImpl.QUERY_IDENTIFIER));
    }

    @Test
    public void merchantIdTest() {
        assertEquals("105064", urlService.getMerchantId(url1));
        assertEquals("105064", urlService.getMerchantId(url4));
    }

    @Test
    public void queryTest() {
        assertEquals("elbise", urlService.getQuery(url5, UrlServiceImpl.QUERY_IDENTIFIER));
    }

    @Test
    public void deepLinkTest() {
        try {
            assertEquals(deeplink1, urlService.urlToDeepLink(url1));
            assertEquals(deeplink2, urlService.urlToDeepLink(url2));
            assertEquals(deeplink3, urlService.urlToDeepLink(url3));
            assertEquals(deeplink4, urlService.urlToDeepLink(url4));
            assertEquals(deeplink5, urlService.urlToDeepLink(url5));
            //assertEquals(deeplink6,urlService.urlToDeepLink(url6));
            assertEquals(deeplink7, urlService.urlToDeepLink(url7));
            assertEquals(deeplink8, urlService.urlToDeepLink(url8));
        } catch (BadUrlException e) {
            e.printStackTrace();
        }
    }

    String deeplink9 = "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064";

    String url9 = "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064";

    String deeplink10 = "ty://?Page=Product&ContentId=1925865";

    String url10 = "https://www.trendyol.com/brand/name-p-1925865";

    String deeplink11 = "ty://?Page=Product&ContentId=1925865&CampaignId=439892";

    String url11 = "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892";

    String deeplink12 = "ty://?Page=Product&ContentId=1925865&MerchantId=105064";

    String url12 = "https://www.trendyol.com/brand/name-p-1925865?merchantId=105064";

    String deeplink13 = "ty://?Page=Search&Query=elbise";

    String url13 = "https://www.trendyol.com/sr?q=elbise";

    String deeplink14 = "ty://?Page=Search&Query=%C3%BCt%C3%BC";

    String url14 = "https://www.trendyol.com/sr?q=%C3%BCt%C3%BC";

    String deeplink15 = "ty://?Page=Favorites";

    String url15 = "https://www.trendyol.com";

    String deeplink16 = "ty://?Page=Orders";

    String url16 = "https://www.trendyol.com";

    @Test
    public void urlTest() {
        try {
            assertEquals(url9, urlService.deeplinkToUrl(deeplink9));
            assertEquals(url10, urlService.deeplinkToUrl(deeplink10));
            assertEquals(url11, urlService.deeplinkToUrl(deeplink11));
            assertEquals(url12, urlService.deeplinkToUrl(deeplink12));
            assertEquals(url13, urlService.deeplinkToUrl(deeplink13));
            assertEquals(url14.toLowerCase(),urlService.deeplinkToUrl(deeplink14));
            assertEquals(url15, urlService.deeplinkToUrl(deeplink15));
            assertEquals(url16, urlService.deeplinkToUrl(deeplink16));
        } catch (BadUrlException e) {
            e.printStackTrace();
        }
    }
}
