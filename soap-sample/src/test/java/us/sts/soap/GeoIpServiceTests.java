package us.sts.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
       // GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("2606:a000:111c:c296:98f6:bfc9:73fc:7bd7");
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.28.29.152");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }
}
