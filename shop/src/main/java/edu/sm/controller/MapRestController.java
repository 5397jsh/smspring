package edu.sm.controller;

import edu.sm.app.dto.Content;
import edu.sm.app.dto.Marker;
import edu.sm.app.service.ContentService; // ContentService import 추가
import edu.sm.app.service.MarkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping; // GetMapping import 추가
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MapRestController {
    final MarkerService markerService;
    final ContentService contentService;
    double lat;
    double lng;

    @RequestMapping("/iot")
    public Object getaddrshop(@RequestParam("lat") double lat, @RequestParam("lng") int lng) throws Exception {
        log.info(lat+" : "+lng);
        this.lat = lat;
        this.lng = lng;
        return "ok";
    }

    @RequestMapping("/getlatlng")
    public Object getlatlng() throws Exception {
        JSONObject jsonObject = new JSONObject();
        //36.800209, 127.074968
        Random r = new Random();
        double lat = 36.800209 + r.nextDouble(0.005);
        double lng = 127.074968 + r.nextDouble(0.001);
        jsonObject.put("lat", this.lat);
        jsonObject.put("lng", this.lng);
        // {lat:xxxxx, lng:xxxxxx}
        return jsonObject;
    }
    @RequestMapping("/getmarkers")
    public Object getMarkers(@RequestParam("target") int target) throws Exception {
        List<Marker> list = markerService.findByLoc(target);
        return list;
    }

    // --- 여기에 병원 정보 조회 기능 추가 ---
    /**
     * 데이터베이스에서 병원 정보를 가져와 JSON 형태로 반환하는 API입니다.
     * Service 레이어를 호출하여 데이터를 가져옵니다.
     * @return 병원 정보 리스트 (JSON)
     */
    @GetMapping("/gethospitals") // HTTP GET 요청을 '/gethospitals' 경로와 매핑합니다.
    public List<Content> getHospitals() {
        // ContentService를 사용하여 병원 데이터를 DB에서 가져옵니다.
        return contentService.getHospitals();
    }
    // ------------------------------------

    @RequestMapping("/getcontents")
    public Object getcontents(@RequestParam("target") int target, @RequestParam("type") int type){
        log.info(target+" "+type);
        List<Content> contents = new ArrayList<>();
        if(target == 100){
            if(type == 10){
                contents.add(new Content(37.564472,126.990841,"순대국1", "ss1.jpg", 101));
                contents.add(new Content(37.544472,126.970841,"순대국2", "ss2.jpg", 102));
                contents.add(new Content(37.564472,126.970841,"순대국3", "ss3.jpg", 103));

            }else if(type == 20){
                contents.add(new Content(37.554472,126.910841,"순1", "ss1.jpg", 101));
                contents.add(new Content(37.514472,126.920841,"순2", "ss2.jpg", 102));
                contents.add(new Content(37.534472,126.990841,"순3", "ss3.jpg", 103));

            }else if(type == 30){
                contents.add(new Content(37.574472,126.920841,"순대국1", "ss1.jpg", 101));
                contents.add(new Content(37.584472,126.970841,"순대국2", "ss2.jpg", 102));
                contents.add(new Content(37.514472,126.930841,"순대국3", "ss3.jpg", 103));

            }
        }else if(target == 200){
            if(type == 10){
                contents.add(new Content(35.175109, 129.171474,"순대국1", "ss1.jpg", 101));
                contents.add(new Content(35.176109, 129.176474,"순대국2", "ss2.jpg", 102));
                contents.add(new Content(35.172109, 129.179474,"순대국3", "ss3.jpg", 103));

            }else if(type == 20){
                contents.add(new Content(35.171109, 129.174474,"순1", "ss1.jpg", 101));
                contents.add(new Content(35.175109, 129.170474,"순2", "ss2.jpg", 102));
                contents.add(new Content(35.179109, 129.171474,"순3", "ss3.jpg", 103));

            }else if(type == 30){
                contents.add(new Content(35.165109, 129.170474,"순대국1", "ss1.jpg", 101));
                contents.add(new Content(35.171109, 129.171474,"순대국2", "ss2.jpg", 102));
                contents.add(new Content(35.169109, 129.168474,"순대국3", "ss3.jpg", 103));

            }
        }else if(target == 300){
            if(type == 10){
                contents.add(new Content(33.254564, 126.569944,"순대국1", "ss1.jpg", 101));
                contents.add(new Content(33.251564, 126.566944,"순대국2", "ss2.jpg", 102));
                contents.add(new Content(33.259564, 126.561944,"순대국3", "ss3.jpg", 103));

            }else if(type == 20){
                contents.add(new Content(33.259564, 126.561944,"순1", "ss1.jpg", 101));
                contents.add(new Content(33.252564, 126.565944,"순2", "ss2.jpg", 102));
                contents.add(new Content(33.256564, 126.568944,"순3", "ss3.jpg", 103));

            }else if(type == 30){
                contents.add(new Content(33.251564, 126.568944,"순대국1", "ss1.jpg", 101));
                contents.add(new Content(33.256564, 126.561944,"순대국2", "ss2.jpg", 102));
                contents.add(new Content(33.259564, 126.565944,"순대국3", "ss3.jpg", 103));

            }
        }
        return contents;
    }
}