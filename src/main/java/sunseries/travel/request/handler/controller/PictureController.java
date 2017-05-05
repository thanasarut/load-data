package sunseries.travel.request.handler.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PictureController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    @Qualifier("updatePictureURL")
    private String updatePictureURL;

    @RequestMapping(value="/pictures", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePictureTags(@RequestBody String tags,
                                    @RequestParam(name = "cloud_name") String cloudName,
                                    @RequestParam(name = "public_id") String publicId,
                                    HttpServletRequest request)
            throws UnirestException {
        try {
            HttpResponse<String> response = Unirest.post(updatePictureURL+"/"+cloudName+"/resources/image/upload/"+publicId)
                    .header("authorization", request.getHeader("Authorization"))
                    .header("content-type",  request.getContentType())
                    .header("cache-control", "no-cache")
                    .body(tags)
                    .asString();
            return response.getBody();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

}
