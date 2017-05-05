package sunseries.travel.request.handler.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sunseries.travel.request.handler.repository.RedisRepository;

import java.util.*;

@RestController
public class CheckStatusController extends BaseController {

    @Autowired
    private RedisRepository redisRepository;

    private static List<String> componentList = new ArrayList<>(
            Arrays.asList(
                    "ms-hotels-search",
                    "ms-hotels-compiler",
                    "ms-hotels-itemizer",
                    "ms-hotels",
                    "ms-hotels-base-rates",
                    "ms-hotels-minimum-night-stay",
                    "ms-hotels-cancellation-policy",
                    "ms-hotels-options",
                    "ms-hotels-child-policy",
                    "ms-hotels-promotion-container",
                    "ms-allotments",
                    "ms-stopsale",
                    "ms-siteminder",
                    "ms-markets"
            ));

    @RequestMapping(value = "/services/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkServiceStatus()
            throws InterruptedException {

        Map<String, String> serviceStatusMap = new HashMap<>();

        componentList
                .stream()
                .forEach(component -> {
                    String key = "SERVICE::STATUS::" + component;
                    if (redisRepository.getData(key) != null) {
                        serviceStatusMap.put(component, redisRepository.getData(key).toString());
                    } else {
                        serviceStatusMap.put(component, "NOT RUNNING");
                    }
                });


        return new Gson().toJson(serviceStatusMap);
    }
}
