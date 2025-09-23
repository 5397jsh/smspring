package edu.sm.weather;

import edu.sm.util.WeatherUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
class Weather2Tests {
    @Value("${app.key.wkey}")
    String key;

    @Test
    void contextLoads() throws IOException, ParseException {
        String key = "rSmgB4A2H12Adov4Wk%2BFpjaWS2LxvXSrly2eHCHELL40Ypr5ZWUbSRKoPBqtmJhawIG9qxvmDhzH2Qvjby%2Ba0A%3D%3D";
        String loc = "11B10101";
        Object object = WeatherUtil.getWeather(loc, key);
        log.info("{}",object);
    }

}