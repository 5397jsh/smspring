package edu.sm.controller;

import edu.sm.app.dto.Marker;
import edu.sm.app.service.MarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MapRestController {
    final MarkerService markerService;

    @RequestMapping("/getmarkers")
    public Object getMarkers(@RequestParam("target") int target) throws Exception {
        List<Marker> list = markerService.findByLoc(target);
        return list;
    }
}






