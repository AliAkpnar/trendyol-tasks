import com.trendyol.linkconverter.Util;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UrlTest {



    @Test
    public void testUrlGotTurkishChars(){
        String url1 = "www.türkiye.com";
        String url2 = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=1050";

        assertFalse(Util.checkUrlGotValidChars(url1));
        assertTrue(Util.checkUrlGotValidChars(url2));
    }
}
