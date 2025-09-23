package edu.sm.controller;

import com.opencsv.CSVReader;
import edu.sm.app.dto.Content;
import edu.sm.app.dto.Marker;
import edu.sm.app.dto.Search;
import edu.sm.app.repository.PhoneRepository;
import edu.sm.app.service.MarkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChartRestController {

    @Value("${app.dir.logsdirRead")
    String dir;

    private final PhoneRepository phoneRepository;
    @RequestMapping("/chart2_1")
    public Object chart2_1() throws Exception {
        //[[],[]]
        JSONArray jsonArray = new JSONArray();
        String [] nation = {"Kor","Eng","Jap","Chn","Usa"};
        Random random = new Random();
        for(int i=0;i<nation.length;i++){
            JSONArray jsonArray1 = new JSONArray();
            jsonArray1.add(nation[i]);
            jsonArray1.add(random.nextInt(100)+1);
            jsonArray.add(jsonArray1);
        }
        return jsonArray;
    }
    @RequestMapping("/chart2_2")
    public Object chart2_2() throws Exception {
        //{cate:[],data:[]}
        JSONObject jsonObject = new JSONObject();
        String arr [] = {"0-9", "10-19", "20-29", "30-39", "40-49", "50-59", "60-69", "70-79", "80-89", "90+"};
        jsonObject.put("cate",arr);
        Random random = new Random();
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<arr.length;i++){
            jsonArray.add(random.nextInt(100)+1);
        }
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }
    @RequestMapping("/chart2_3")
    public Object chart2_3() throws Exception {
        // text
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(dir+"click.log"), "UTF-8"));
        String[] line;
        //reader.readNext();
        StringBuffer sb = new StringBuffer();
        while ((line = reader.readNext()) != null) {
            sb.append(line[2]+" ");
        }
        return sb.toString();
    }
    @RequestMapping("/chart1")
    public Object chart1() throws Exception {
        // []
        JSONArray jsonArray = new JSONArray();

        // {}
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        jsonObject1.put("name","Korea");
        jsonObject2.put("name","Japan");
        jsonObject3.put("name","China");
        // []
        JSONArray data1Array = new JSONArray();
        JSONArray data2Array = new JSONArray();
        JSONArray data3Array = new JSONArray();

        Random random = new Random();
        for(int i=0;i<12;i++){
            data1Array.add(random.nextInt(100)+1);
            data2Array.add(random.nextInt(100)+1);
            data3Array.add(random.nextInt(100)+1);
        }
        jsonObject1.put("data",data1Array);
        jsonObject2.put("data",data2Array);
        jsonObject3.put("data",data3Array);

        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
        jsonArray.add(jsonObject3);
        return  jsonArray;
    }
    // 컨트롤러

    @RequestMapping("/chart/phonesales")
    public Object phoneSales() {
        List<Map<String, Object>> salesList = phoneRepository.getSalesByBrand();
        List<Map<String, Object>> avgList = phoneRepository.getAverageSalesByBrand();

        // Use LinkedHashMap to preserve insertion order for brand names
        Map<String, double[]> sumMap = new LinkedHashMap<>();
        Map<String, double[]> avgMap = new LinkedHashMap<>();

        // Process sales data
        for (Map<String, Object> r : salesList) {
            String brand = (String) r.get("phoneName");
            sumMap.computeIfAbsent(brand, k -> new double[12]); // Initialize 12 months with 0.0
            int month = ((Number) r.get("month")).intValue();
            int idx = month - 1; // month is 1-12
            if (idx >= 0 && idx < 12) {
                sumMap.get(brand)[idx] = ((Number) r.get("value")).doubleValue();
            }
        }

        // Process average data
        for (Map<String, Object> r : avgList) {
            String brand = (String) r.get("phoneName");
            avgMap.computeIfAbsent(brand, k -> new double[12]); // Initialize 12 months with 0.0
            int month = ((Number) r.get("month")).intValue();
            int idx = month - 1; // month is 1-12
            if (idx >= 0 && idx < 12) {
                avgMap.get(brand)[idx] = ((Number) r.get("value")).doubleValue();
            }
        }

        // Highcharts series 구성
        List<Map<String, Object>> totalSalesSeries = new ArrayList<>();
        for (String brand : sumMap.keySet()) {
            Map<String, Object> series = new HashMap<>();
            series.put("name", brand);
            series.put("data", sumMap.get(brand));
            totalSalesSeries.add(series);
        }

        List<Map<String, Object>> averageSalesSeries = new ArrayList<>();
        for (String brand : avgMap.keySet()) {
            Map<String, Object> series = new HashMap<>();
            series.put("name", brand);
            series.put("data", avgMap.get(brand));
            averageSalesSeries.add(series);
        }

        // Combine both series
        Map<String, Object> response = new HashMap<>();
        response.put("totalSalesSeries", totalSalesSeries);
        response.put("averageSalesSeries", averageSalesSeries);
        // Also add categories for the chart's X-axis
        List<String> categories = new ArrayList<>();
        for (int m = 1; m <= 12; m++) {
            categories.add(m + "월");
        }
        response.put("categories", categories);


        return response;
    }

}






