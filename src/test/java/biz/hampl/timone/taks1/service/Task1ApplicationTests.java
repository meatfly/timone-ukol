package biz.hampl.timone.taks1.service;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import biz.hampl.timone.taks1.vo.PackageVo;
import org.junit.jupiter.api.Test;

class Task1ApplicationTests {

    @Test
    void parseInput1() {
        PackageVo packageVo;
        PostalService postalService = new PostalService(null);
        packageVo = postalService.parseLine("3.4 08801");
        assertNotNull(packageVo);
        packageVo = postalService.parseLine("2 90005");
        assertNotNull(packageVo);
        packageVo = postalService.parseLine("12.56 08801");
        assertNotNull(packageVo);
        packageVo = postalService.parseLine("5.5 08079");
        assertNotNull(packageVo);
        packageVo = postalService.parseLine("3.2 09300");
        assertNotNull(packageVo);
    }

    @Test
    void parseInput2() {
        PackageVo packageVo;
        PostalService postalService = new PostalService(null);
        packageVo = postalService.parseLine("-3.4 08801");
        assertNull(packageVo);
        packageVo = postalService.parseLine("2.1234 90005");
        assertNull(packageVo);
        packageVo = postalService.parseLine("12.56 088012");
        assertNull(packageVo);
        packageVo = postalService.parseLine("5.5 -08079");
        assertNull(packageVo);
        packageVo = postalService.parseLine("3.2 09300 123");
        assertNull(packageVo);
    }

}
