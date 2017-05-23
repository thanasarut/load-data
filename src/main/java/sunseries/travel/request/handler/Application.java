package sunseries.travel.request.handler;

import com.google.common.base.CaseFormat;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import sunseries.travel.request.handler.message.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {
        String serverHost = "127.0.0.1";
        String serverPort = "8080";

        //String fileName = "/Users/thanasarut/sunseries/source_load_data/hotelMetadata.sort.csv"; // 1st - hotel_meta_data
        String fileName = "/Users/thanasarut/sunseries/source_load_data/vick/backend_hotel.csv"; // 2nd - additional hotel_meta_date with room_class
        //String fileName = "/Users/thanasarut/sunseries/source_load_data/backend_hotel.csv"; // 2nd - additional hotel_meta_date with room_class
        //String fileName = "/Users/thanasarut/sunseries/source_load_data/room_rate.sort.csv";
        //String fileName = "/Users/thanasarut/sunseries/source_load_data/temp.csv";
        //String fileName = "/Users/thanasarut/sunseries/source_load_data/data_abook_127.0.0.1.csv";
        //String fileName = "/Users/thanasarut/sunseries/source_load_data/location_data.csv";
        //String fileName = "/Users/thanasarut/sunseries/source_load_data/promotion_data.valid.csv";

        File roomClassIdDummyProblemFile = new File("/Users/thanasarut/sunseries/testing/log/roomClassIdDummyProblem.txt");
        File roomClassBedTypeProblemFile = new File("/Users/thanasarut/sunseries/testing/log/roomClassBedTypeProblem.txt");
        File childPolicyProblemFile = new File("/Users/thanasarut/sunseries/testing/log/childPolicyProblem.txt");
        File optionsProblemFile = new File("/Users/thanasarut/sunseries/testing/log/optionProblem.txt");
        File hotelMetaDataProblemFile = new File("/Users/thanasarut/sunseries/testing/log/hotelMetaDataProblem.txt");

        //<editor-fold desc="grep begin pattern HotelMetaData,BackendHotel,RoomRate">
        // Pattern of Hotel Metadata -- It will start with "sunsXXXX" hotel_id
        String hotelMetaDataRegEx = "^suns[0-9]";
        Pattern hotelMetaDataPattern = Pattern.compile(hotelMetaDataRegEx);

        // Pattern of RoomClass Metadata -- It will start with "rcm::{hotelId}"
        String hotelBackEndMetaDataRegEx = "^backend_hotel::";
        Pattern hotelBackEndMetaDataPattern = Pattern.compile(hotelBackEndMetaDataRegEx);

        // Pattern of RoomRate Metadata -- It will start with "room_rate::{roomClassId}"
        String hotelBaseRateDataRegEx = "^room_rate::";
        Pattern hotelBaseRateDataPattern = Pattern.compile(hotelBaseRateDataRegEx);
        //</editor-fold>


        // <editor-fold desc="grep begin pattern Location">
        // Pattern of country Metadata -- It will start with "country::{countryId}"
        String countryDataRegEx = "^country::";
        Pattern countryDataPattern = Pattern.compile(countryDataRegEx);

        String cityDataRegEx = "^city::";
        Pattern cityDataPattern = Pattern.compile(cityDataRegEx);
        //</editor-fold>

        String promotionDataRegEx = "^promotion_";
        Pattern promotionDataPattern = Pattern.compile(promotionDataRegEx);

        String loginToken = null;
        String loginToken2 = null;

        int hotelMetadataCounter = 0, hotelMetadataFailedCounter = 0;
        int backendHotelMetadataCounter = 0, backendHotelMetadataFailedCounter = 0, backendHotelMetadataMissMatchCounter = 0;
        int hotelBaseRateCounter = 0, hotelBaseRateFailedCounter = 0;

        try {
            //<editor-fold desc="request for token">
            if (loginToken == null) {
                String input = "{\"type\":\"authenticate\",\"event_data\":{\"email\":\"pea@sunseries.travel\",\"password\":\"P@ssw0rd\"},\"id\":\"2a7075216f26dc06cae416ef45a3ecd4\",\"ttid\":\"2a7075216f26dc06cae416ef45a3ecd4\",\"origin\":\"postman\"}";
                //String stringHttpResponse = doHttpPostClient("http://172.16.2.4:8080/sunseries/v1/authenticate", input);
                String loginResponseData = doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/authenticate", input);
                JsonObject jsonResponseData = new Gson().fromJson(loginResponseData, JsonObject.class);
                loginToken = jsonResponseData.get("token").toString().replaceAll("\"", "");
            }
            System.out.println("token: " + loginToken);
            //</editor-fold>

            //<editor-fold desc="request for token on INT">
            if (loginToken2 == null) {
                String input = "{\"type\":\"authenticate\",\"event_data\":{\"email\":\"pea@sunseries.travel\",\"password\":\"P@ssw0rd\"},\"id\":\"2a7075216f26dc06cae416ef45a3ecd4\",\"ttid\":\"2a7075216f26dc06cae416ef45a3ecd4\",\"origin\":\"postman\"}";
                String loginResponseData = doHttpPostClient("http://" + serverHost + ":8089/sunseries/v1/authenticate", input);
                JsonObject jsonResponseData = new Gson().fromJson(loginResponseData, JsonObject.class);
                loginToken2 = jsonResponseData.get("token").toString().replaceAll("\"", "");
            }
            System.out.println("token-on-INT: " + loginToken2);
            //</editor-fold>

            // Reading from export.csv file
            File f = new File(fileName);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";

            System.out.println("Reading file using Buffered Reader");

            while ((readLine = b.readLine()) != null) {
                // filter only match of pattern
                if (1==1) {
                    if (hotelMetaDataPattern.matcher(readLine).find()) {
                        //<editor-fold desc="Hotel meta data pattern using 'hotelMetadata.sort.csv' file only.">
                        //<editor-fold desc="fixed dirty data">
                        // extract field of value (which is field 4 of export.csv file
                        String[] tokens = readLine.split("^[\\w,]+\"|\"[,\\d]+$");
                        String jsonString = tokens[1].replaceAll("\"\"", "%@").replaceAll("%@", "\"");

                        // fixed type remarks transform
                        jsonString = jsonString.replaceAll("\"remarks\":\"(.+)\",\"room_count", "\"remarks\":[{\"^o\": \"Sunseries::Domain::Model::Hotel::Remark\", \"description\": \"$1\", \"id\":\"\"}],\"room_count");
                        //</editor-fold>

                        _HotelMetadata _hotel = new Gson().fromJson(jsonString, _HotelMetadata.class);
                        if (_hotel != null) {
                            hotelMetadataCounter++;
                            System.out.println(jsonString);
                            Hotel hotel = transformHotel(_hotel);

                            if (!StringUtils.isEmpty(_hotel.getAreaId())) {
                                //<editor-fold desc="Add area_id">
                                JsonObject jsonGetAreaNameResponse = new Gson().fromJson(doHttpGetClient("http://" + serverHost + ":8089/sunseries/v1/cities/" + hotel.getCity() + "/areas?token=" + loginToken2), JsonObject.class);
                                if (!StringUtils.isEmpty(jsonGetAreaNameResponse)) {
                                    List<_Area> allArea = new Gson().fromJson(jsonGetAreaNameResponse.get("areas"), new TypeToken<List<_Area>>(){}.getType());
                                    List<String> areas = new ArrayList<>();
                                    allArea.stream().forEach(area -> {
                                        if (_hotel.getAreaId().equals(area.getId().substring(6))) {
                                            areas.add(area.getName());
                                        }
                                    });

                                    if (!areas.isEmpty()) {
                                        hotel.setAreas(areas);
                                    } else {
                                        writeToFileApacheCommonIO("not found area_id: " + _hotel.getAreaId().toString() + ", hotel_id: " + _hotel.getId() + ", city_id: " + _hotel.getCityId(), hotelMetaDataProblemFile);
                                        System.out.println("not found area_id: " + _hotel.getAreaId().toString() + ", hotel_id: " + _hotel.getId() + ", city_id: " + _hotel.getCityId());
                                    }
                                } else {
                                    writeToFileApacheCommonIO("not found area_id: " + _hotel.getAreaId().toString() + ", hotel_id: " + _hotel.getId() + ", city_id: " + _hotel.getCityId(), hotelMetaDataProblemFile);
                                    System.out.println("can't get area list of city_id: " + _hotel.getCityId());
                                }
                                //</editor-fold>
                            }

                            if (!StringUtils.isEmpty(_hotel.getImages()) && (!_hotel.getImages().isEmpty())) {
                                //<editor-fold desc="add images">
                                List<Image> imageList = new ArrayList<>();
                                _hotel.getImages().forEach(_image -> {
                                    Image image = new Image();
                                    Boolean isDataProblem = false;
                                    //<editor-fold desc="validatation data and copy to new object">
                                    if (!StringUtils.isEmpty(_image.getIsPrimary().toString())) {
                                        image.setIsPrimary(Boolean.parseBoolean(_image.getIsPrimary().toString()));
                                    } else {
                                        // data problem
                                        isDataProblem = true;
                                    }
                                    if (!StringUtils.isEmpty(_image.getImageUrl())) {
                                        image.setImageUrl(_image.getImageUrl().toString());
                                    } else {
                                        // data problem
                                        isDataProblem = true;
                                    }
                                    if (!StringUtils.isEmpty(_image.getThumbnailUrl())) {
                                        image.setThumbnailUrl(_image.getThumbnailUrl().toString());
                                    } else {
                                        // data problem
                                        isDataProblem = true;
                                    }
                                    //</editor-fold>
                                    if (!isDataProblem) {
                                        imageList.add(image);
                                    } else {
                                        // write to file or something
                                    }
                                    hotel.setImages(imageList);
                                });
                                //</editor-fold>
                            }

                            // if hotelMetada already add then skipped
                            JsonObject jsonHotelResponseData = new Gson().fromJson(doHttpGetClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + _hotel.getId() + "?token=" + loginToken), JsonObject.class);
                            if (jsonHotelResponseData.get("status").toString().equals("\"SUCCESS\"")) {
                                continue;
                            }

                            if (Integer.parseInt(_hotel.getId().substring(4)) == hotelMetadataCounter) {
                                // add hotel meta data with old data
                                String payload = "{\"type\":\"create_hotel\",\"origin\":\"ms-load-data\",\"event_data\":{\"hotel\":" + new Gson().toJson(hotel) + "}}";
                                JsonObject jsonAddHotelResponse = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels?token=" + loginToken, payload), JsonObject.class);
                                System.out.println("i: " + hotelMetadataCounter + ", id: " + jsonAddHotelResponse.get("id").toString() + ", add_hotel status: " + jsonAddHotelResponse.get("status").toString());
                                // add delay for couchbase
                                sleep(300);
                            } else {
                                // no _hotel_id X no need to add empty hotel
                                String payload = "{\"type\":\"create_hotel\",\"origin\":\"ms-load-data\",\"event_data\":{\"hotel\":{\"hotel_name\":\"dummy\"}}}";
                                JsonObject jsonAddHotelResponse = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels?token=" + loginToken, payload), JsonObject.class);
                                System.out.println("i: " + hotelMetadataCounter + ", id: " + jsonAddHotelResponse.get("id").toString() + ", add_hotel_dummy status: " + jsonAddHotelResponse.get("status").toString());
                                // add delay for couchbase
                                sleep(300);

                                // real information
                                hotelMetadataCounter++;
                                payload = "{\"type\":\"create_hotel\",\"origin\":\"ms-load-data\",\"event_data\":{\"hotel\":" + new Gson().toJson(hotel) + "}}";
                                jsonAddHotelResponse = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels?token=" + loginToken, payload), JsonObject.class);
                                System.out.println("i: " + hotelMetadataCounter + ", id: " + jsonAddHotelResponse.get("id").toString() + ", add_hotel status: " + jsonAddHotelResponse.get("status").toString());
                                // add delay for couchbase
                                sleep(300);

                                // delete dummy information
                            /*stringHttpResponse = doHttpDeleteClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + dummyId.replaceAll("\"","") + "?token=" + loginToken);
                            httpResponseData = new Gson().fromJson(stringHttpResponse, JsonObject.class);
                            System.out.println("delete status: " + httpResponseData.get("status").toString());*/
                            }
                        } else {
                            hotelMetadataFailedCounter++;
                        }
                        //</editor-fold>
                    } else if (hotelBaseRateDataPattern.matcher(readLine).find()) {
                        //<editor-fold desc="RESP API hotel_base_rate">
                        // TODO :: display_markup from v.2 still need to add to ms-agents in v.3
                        // check pattern of room_rate that already specify room_rate data not empty_list
                        if (!Pattern.compile(",[0-9]+,[0-9]+,[0-9]+,[{}]+").matcher(readLine).find()) {
                            hotelBaseRateCounter++;
                            // means specify room_rate
                            // extract field of value (which is field 4 of export.csv file
                            String[] tokens = readLine.split(",[0-9]+,[0-9]+,[0-9]+,\\\"|\",[0-9]+,[0-9]+,[0-9]+$");
                            String[] _id_of_hotel_base_rate_id_array = tokens[0].replaceAll("\"\"", "\"").split("::|-");
                            String _hotel_base_id = _id_of_hotel_base_rate_id_array[_id_of_hotel_base_rate_id_array.length - 3];
                            String _hotel_id = _id_of_hotel_base_rate_id_array[_id_of_hotel_base_rate_id_array.length - 2];
                            String _hotel_base_rate_id = _id_of_hotel_base_rate_id_array[_id_of_hotel_base_rate_id_array.length - 2] + "-" + _id_of_hotel_base_rate_id_array[_id_of_hotel_base_rate_id_array.length - 1];

                            String jsonString = tokens[1].replaceAll("\"\"", "\"");

                            System.out.println(jsonString);
                            JsonObject checkHotelBaseRateAddedResponse = new Gson().fromJson(doHttpGetClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + _hotel_id.toString() + "/base-rate?token=" + loginToken), JsonObject.class);
                            HotelBaseRate hotelBaseRate = new Gson().fromJson(checkHotelBaseRateAddedResponse.get("result"), HotelBaseRate.class);
                            if (hotelBaseRate == null || hotelBaseRate.getBaseRateList() == null || hotelBaseRate.getBaseRateList().size() == 0) {
                                //<editor-fold desc="fix dirty data">
                                // fixed any [] empty list;
                                jsonString = jsonString.replaceAll(":\\[]", ":null");

                                // fixed \" to " only
                                jsonString = jsonString.replaceAll("\\\\\"", "\"");
                                // fixed "{ at beginning and end }"
                                jsonString = jsonString.replaceAll("\"\\{", "{");
                                jsonString = jsonString.replaceAll("}\"", "}");
                                //</editor-fold>

                                // prepare data of base-rate old to transform
                                LinkedTreeMap<String, Object> _marketRawMap = new Gson().fromJson(jsonString, LinkedTreeMap.class);
                                List<_Market> _marketList = new ArrayList<>();
                                _marketRawMap.keySet().forEach(_keyName -> {
                                    if (!(new Gson().toJson(_marketRawMap.get(_keyName))).equals("{}")) {
                                        _Market _market = new Gson().fromJson(new Gson().toJson(_marketRawMap.get(_keyName)), _Market.class);
                                        _market.setMarket(_keyName);
                                        _marketList.add(_market);
                                    }
                                });

                                List<BaseRate> baseRatesList = new ArrayList<>();

                                _marketList.forEach(_market -> {
                                    _market.getRoom().forEach(_periodRate -> {
                                        BaseRate baseRate = new BaseRate();
                                        if (_periodRate.getValid_period() != null) {
                                            baseRate.setFromDate(transformOldDateToString(_periodRate.getValid_period().get("from")));
                                            baseRate.setToDate(transformOldDateToString(_periodRate.getValid_period().get("to")));

                                            baseRate.setMarket(_market.getMarket());
                                            baseRate.setWeekdays(_periodRate.getValidDay());
                                            baseRate.setRoomRate(String.format("%1$.2f", _periodRate.getAmount().getSatang() / 100.0));
                                            baseRate.setCurrencyCode(_periodRate.getAmount().getCurrency());
                                            baseRate.setRoomClass(_hotel_base_rate_id);

                                            SaleMarkup saleMarkup = new SaleMarkup();
                                            String[] _markupValue = _periodRate.getSaleMarkup().getName().split("::");
                                            saleMarkup.setType(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, _markupValue[_markupValue.length - 1]));
                                            baseRate.setSaleMarkup(saleMarkup);

                                            if (_market.getBreakFast() == null) {
                                                baseRate.setBreakfastRate("-1");
                                            } else {
                                                _market.getBreakFast().forEach(_breakfastPeriod -> {
                                                    if (transformOldDateToString(_breakfastPeriod.getValid_period().get("from")).equals(baseRate.getFromDate())
                                                            && transformOldDateToString(_breakfastPeriod.getValid_period().get("to")).equals(baseRate.getToDate())) {
                                                        baseRate.setBreakfastRate(String.format("%1$.2f", _breakfastPeriod.getAmount().getSatang() / 100.0));
                                                    }
                                                });
                                            }
                                            if (_market.getExtraBed() == null) {
                                                baseRate.setExtraBedRate("-1");
                                            } else {
                                                _market.getExtraBed().forEach(_extraBedPeriod -> {
                                                    if (transformOldDateToString(_extraBedPeriod.getValid_period().get("from")).equals(baseRate.getFromDate())
                                                            && transformOldDateToString(_extraBedPeriod.getValid_period().get("to")).equals(baseRate.getToDate())) {
                                                        baseRate.setExtraBedRate(String.format("%1$.2f", _extraBedPeriod.getAmount().getSatang() / 100.0));
                                                    }
                                                });
                                            }

                                            baseRatesList.add(baseRate);
                                        } else {
                                            // not found valid_period --> have only period that is very old data, martin confirm to not load to v.3
                                        }
                                    });
                                });

                                for (BaseRate baseRate : baseRatesList) {
                                    String payload = "{\"type\":\"create_hotel_base_rate\",\"origin\":\"ms-load-data\",\"event_data\":{\"base_rate\":" + new Gson().toJson(baseRate) + "}}";
                                    JsonObject jsonAddHotelBaseRateResponse = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + _hotel_id + "/base-rate?token=" + loginToken, payload), JsonObject.class);
                                    System.out.println("i: " + hotelBaseRateCounter + ", hotel_id: " + _hotel_id + ", hotel_base_rate_id: " + jsonAddHotelBaseRateResponse.get("id") + ", add_hotel_base_rate status: " + jsonAddHotelBaseRateResponse.get("status").toString());
                                    sleep(100);
                                }
                            } else {
                                // already add base_rate for this hotel_id
                            }
                        } else {
                            // means not specify room_rate or it is empty_list
                        }
                        //</editor-fold>
                    } else if (hotelBackEndMetaDataPattern.matcher(readLine).find()) {
                        // TODO :: SEQUESNCE-2 :: load for backend_hotel
                        //<editor-fold desc="fix dirty data">
                        // extract field of value (which is field 4 of export.csv file
                        String[] tokens = readLine.split("^[\\w,-:]+\"|\"[,\\d]+$");
                        String jsonString = tokens[1].replaceAll("\"\"", "%@").replaceAll("%@", "\"");

                        // fixed type remarks transform
                        jsonString = jsonString.replaceAll("\"remarks\":\"(.+)\",\"room_count", "\"remarks\":[{\"^o\": \"Sunseries::Domain::Model::Hotel::Remark\", \"description\": \"$1\", \"id\":\"\"}],\"room_count");
                        //</editor-fold>

                        _BackEndHotelMetadata _backendHotel = new Gson().fromJson(jsonString, _BackEndHotelMetadata.class);
                        if (_backendHotel != null) {
                            System.out.println(jsonString);
                            backendHotelMetadataCounter++;
                            // if hotelMetada already add then skipped
                            JsonObject jsonGetHotelResponse = new Gson().fromJson(doHttpGetClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + _backendHotel.getFacadeId().replaceAll("\"", "") + "?token=" + loginToken), JsonObject.class);
                            JsonObject jsonUpdateHotelResponse = null;
                            if (jsonGetHotelResponse.get("status").toString().equals("\"SUCCESS\"")) {
                                // start to copy each data from backend_hotel to hotel
                                Hotel hotel = new Gson().fromJson(jsonGetHotelResponse.get("hotel"), Hotel.class);

                                if (hotel.getRoomClasses() == null || hotel.getRoomClasses().size() == 0) {

                                    //<editor-fold desc="REST API update 2nd-hotel_meta_data">
                                    if (_backendHotel.getChildPolicy() != null) {
                                        if (_backendHotel.getChildPolicy().get("age_to") != null) {
                                            hotel.setChildrenAgeFrom(convertObjectToInt(_backendHotel.getChildPolicy().get("age_to")));
                                        }
                                        if (_backendHotel.getChildPolicy().get("age_from") != null) {
                                            hotel.setChildrenAgeFrom(convertObjectToInt(_backendHotel.getChildPolicy().get("age_from")));
                                        } else {
                                            if (_backendHotel.getChildPolicy().get("age_to") != null) {
                                                hotel.setChildrenAgeFrom(0);
                                            }
                                        }
                                    }

                                    hotel.setReservationEmail(_backendHotel.getReservationEmail());
                                    hotel.setOfficialEmail(_backendHotel.getOfficialEmail());
                                    hotel.setSalesEmail(_backendHotel.getSalesEmail());
                                    if (!StringUtils.isEmpty(_backendHotel.getRemarks())) {
                                        List<String> remarkList = new ArrayList<>();
                                        if (_backendHotel.getRemarks().getClass().equals(String.class)) {
                                            remarkList.add(_backendHotel.getRemarks().toString());
                                        } else {
                                            ((List<Map<String, Object>>) _backendHotel.getRemarks()).forEach(map -> {
                                                remarkList.add(map.get("description").toString());
                                            });
                                        }
                                        hotel.setRemark(remarkList);
                                    }

                                    // update hotel data with old data
                                    String payload = "{\"type\":\"update_hotel\",\"origin\":\"ms-load-data\",\"event_data\":{\"hotel\":" + new Gson().toJson(hotel) + "}}";
                                    jsonUpdateHotelResponse = new Gson().fromJson(doHttpPatchClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId().replaceAll("\"", "") + "?token=" + loginToken, payload), JsonObject.class);
                                    System.out.println("i: " + backendHotelMetadataCounter + ", id: " + jsonUpdateHotelResponse.get("id").toString() + ", hotel_update_backend status: " + jsonUpdateHotelResponse.get("status").toString());
                                    sleep(50);
                                    //</editor-fold>

                                    //<editor-fold desc="Rest API to add room_class">
                                    if ((!StringUtils.isEmpty(_backendHotel.getRoomClasses()) && (!_backendHotel.getRoomClasses().isEmpty()))) {
                                        if (hotel.getRoomClasses().isEmpty()) {
                                            List<RoomClass> roomClassList = new ArrayList<>();
                                            Integer _rcm_counter = 1;
                                            Collections.sort(_backendHotel.getRoomClasses(), new Comparator<Map<String, Object>>() {
                                                @Override
                                                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                                                    return o1.get("id").toString().compareTo(o1.get("id").toString());
                                                }
                                            });
                                            for (Map<String, Object> _roomClasses : _backendHotel.getRoomClasses()) {
                                                RoomClass roomClass = new RoomClass();
                                                while (!_roomClasses.get("id").equals("backend_hotel::bess-" + hotel.getHotelId() + "-" + String.format("%1$07d", _rcm_counter))) {
                                                    // add dummy_room_class_id
                                                    RoomClass dummyRoomClass = new RoomClass();
                                                    dummyRoomClass.setRoomClassName("dummy");
                                                    roomClassList.add(dummyRoomClass);
                                                    writeToFileApacheCommonIO("dummy_room_class_id:" + hotel.getHotelId() + "-" + String.format("%1$07d", _rcm_counter) + System.lineSeparator(), roomClassIdDummyProblemFile);
                                                    _rcm_counter++;
                                                }
                                                if (_roomClasses.get("id").equals("backend_hotel::bess-" + hotel.getHotelId() + "-" + String.format("%1$07d", _rcm_counter))) {
                                                    // in case that never load data of room classes to new version

                                                    roomClass.setRoomClassName(_roomClasses.get("name").toString());
                                                    // TODO : in case that room_classes not specify MaxOcc --> jack will help to fill in again.
                                                    if (_roomClasses.get("max_occupancy_without_extra_bed") == null ||
                                                            _roomClasses.get("max_occupancy_with_extra_bed") == null ||
                                                            _roomClasses.get("max_adults_with_extra_bed") == null) {
                                                        roomClass.setMaxOccupancyExcludeExtraBed("0");
                                                        roomClass.setMaxOccupancyIncludeExtraBed("0");
                                                        roomClass.setMaxAdultIncludeExtraBed("0");
                                                        roomClass.setMixAdultAndChildInRoom(false);
                                                        roomClass.setMaxChild("0");
                                                        roomClass.setOrder(0);
                                                        writeToFileApacheCommonIO("no_max_occ:" + _roomClasses.get("id").toString() + System.lineSeparator(), roomClassBedTypeProblemFile);
                                                    } else {
                                                        roomClass.setMaxOccupancyExcludeExtraBed(convertObjectToInt(_roomClasses.get("max_occupancy_without_extra_bed")).toString());
                                                        roomClass.setMaxOccupancyIncludeExtraBed(convertObjectToInt(_roomClasses.get("max_occupancy_with_extra_bed")).toString());
                                                        roomClass.setMaxAdultIncludeExtraBed(convertObjectToInt(_roomClasses.get("max_adults_with_extra_bed")).toString());
                                                        if (_roomClasses.get("mix_adults_children_extra_bed") != null) {
                                                            roomClass.setMixAdultAndChildInRoom(Boolean.parseBoolean(_roomClasses.get("mix_adults_children_extra_bed").toString()));
                                                        } else {
                                                            roomClass.setMixAdultAndChildInRoom(false);
                                                        }
                                                        //<editor-fold desc="Set max child for each room_class - must already have room_class">
                                                        // set child policy for each room class
                                                        if (_backendHotel.getChildPolicy() != null) {
                                                            if (_backendHotel.getChildPolicy().get("maximum_children") != null) {
                                                                Map<String, Object> _max_child = new Gson().fromJson(new Gson().toJson(_backendHotel.getChildPolicy().get("maximum_children")), Map.class);
                                                                if (_max_child.get("self") != null) {
                                                                    Map<String, Object> _self = new Gson().fromJson(new Gson().toJson(_max_child.get("self")), Map.class);
                                                                    if (!StringUtils.isEmpty(_self.get("backend_hotel::bess-" + hotel.getHotelId() + "-" + String.format("%1$07d", _rcm_counter)))) {
                                                                        roomClass.setMaxChild(convertObjectToInt(_self.get("backend_hotel::bess-" + hotel.getHotelId() + "-" + String.format("%1$07d", _rcm_counter))).toString());
                                                                    } else {
                                                                        // jack confirm that if have 0-set or not-set means 0 --> not allow children in that room_class --> martin confirm to put 99 and track the hotel in list, then let other fixed
                                                                        roomClass.setMaxChild("99");
                                                                        writeToFileApacheCommonIO("Must fix data :: " + hotel.getHotelId() + " not specify MaxChild of room_class" + System.lineSeparator(), childPolicyProblemFile);
                                                                    }
                                                                } else {
                                                                    if (!StringUtils.isEmpty(_max_child.get("backend_hotel::bess-" + hotel.getHotelId() + "-" + String.format("%1$07d", _rcm_counter)))) {
                                                                        roomClass.setMaxChild(convertObjectToInt(_max_child.get("backend_hotel::bess-" + hotel.getHotelId() + "-" + String.format("%1$07d", _rcm_counter))).toString());
                                                                    } else {
                                                                        // jack confirm that if have 0-set or not-set means 0 --> not allow children in that room_class --> martin confirm to put 99 and track the hotel in list, then let other fixed
                                                                        roomClass.setMaxChild("99");
                                                                        writeToFileApacheCommonIO("Must fix data :: " + hotel.getHotelId() + " not specify MaxChild of room_class" + System.lineSeparator(), childPolicyProblemFile);
                                                                    }
                                                                }
                                                            } else {
                                                                writeToFileApacheCommonIO("Must fix data :: " + hotel.getHotelId() + " not specify MaxChild of room_class" + System.lineSeparator(), childPolicyProblemFile);
                                                            }
                                                        } else {
                                                            // if v.2 not specify any child_policy no need to do anything for v.3
                                                        }
                                                        //</editor-fold>
                                                        roomClass.setOrder(0);
                                                    }
                                                    //<editor-fold desc="Set BedType for each room_class - mush already have room_class">
                                                    // TODO : some hotel not specify bedtype or maxOccu-- list in log.
                                                    List<BedType> bedTypeList = new ArrayList<>();
                                                    if (_roomClasses.get("room_configurations") != null) {
                                                        ((List<Map<String, Object>>) _roomClasses.get("room_configurations")).forEach(_roomConfiguration -> {
                                                            bedTypeList.add(new BedType(_roomConfiguration.get("name").toString(), (boolean) _roomConfiguration.get("extra_bed")));
                                                        });
                                                    } else {
                                                        // set default bedType first
                                                        bedTypeList.add(new BedType("single", false));
                                                        writeToFileApacheCommonIO("no_bed_type:" + _roomClasses.get("id").toString() + System.lineSeparator(), roomClassBedTypeProblemFile);
                                                    }
                                                    roomClass.setBedTypes(bedTypeList);
                                                    //</editor-fold>
                                                    roomClassList.add(roomClass);
                                                    _rcm_counter++;
                                                }
                                            }
                                            payload = "{\"type\":\"create_room_class\",\"origin\":\"ms-load-data\",\"event_data\":{\"hotel\": {\"room_classes\":" + new Gson().toJson(roomClassList) + "}}}";
                                            JsonObject jsonCreateRoomClassesResponse = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId() + "/room-classes" + "?token=" + loginToken, payload), JsonObject.class);
                                            System.out.println("i: " + backendHotelMetadataCounter + ", id: " + hotel.getHotelId().toString() + ", create_room_classe status: " + jsonCreateRoomClassesResponse.get("status").toString());
                                            sleep(300);
                                        } else {
                                            // already add room_classes
                                        }
                                    } else {
                                        // old data not specify room_classes
                                    }
                                    //</editor-fold>

                                } else {
                                    //<editor-fold desc="REST API 3rd - cancellation_policy, minimum_night_stay, child_policy, hotel_options parallels">

                                    if (1 == 0) {
                                        //<editor-fold desc="REST API add cancellation_policy">
                                        // TODO :: Add cancellation_policy == doing parallel just put "if (0==1)" instead
                                        // check if already add cancellation_policy for this hotel_id or not?
                                        JsonObject checkCancellationAddedResponse = new Gson().fromJson(doHttpGetClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId() + "/cancellation-policies?token=" + loginToken), JsonObject.class);
                                        HotelsCancellationPolicy hotelsCancellationPolicy = new Gson().fromJson(checkCancellationAddedResponse.get("result"), HotelsCancellationPolicy.class);
                                        if (hotelsCancellationPolicy.getCancellationPolicyList() == null || hotelsCancellationPolicy.getCancellationPolicyList().size() == 0) {
                                            if (!StringUtils.isEmpty(_backendHotel.getCancellationPolicies()) && (_backendHotel.getCancellationPolicies().size() != 0)) {
                                                List<CancellationPolicy> cancellationPolicyList = new ArrayList<>();
                                                hotel.getRoomClasses().forEach(roomClass -> {
                                                    _backendHotel.getCancellationPolicies().forEach(_cancellationPolicy -> {
                                                        CancellationPolicy cancellationPolicy = new CancellationPolicy();

                                                        if (_cancellationPolicy.getPeriod() != null) {
                                                            cancellationPolicy.setFromDate(transformOldDateToString(_cancellationPolicy.getPeriod().get("from")));
                                                            cancellationPolicy.setToDate(transformOldDateToString(_cancellationPolicy.getPeriod().get("to")));
                                                        }
                                                        cancellationPolicy.setMarket("market::1");
                                                        cancellationPolicy.setRoomClass(roomClass.getRoomClassId());
                                                        if (_cancellationPolicy.getDay() != null) {
                                                            cancellationPolicy.setDays(convertObjectToInt(_cancellationPolicy.getDay()));
                                                        } else {
                                                            cancellationPolicy.setDays(7);
                                                        }

                                                        cancellationPolicyList.add(cancellationPolicy);
                                                    });
                                                });

                                                System.out.println("hotel_id: " + hotel.getHotelId() + ", room_classes: " + hotel.getRoomClasses().size() + ", old_cancellation_policy: " + _backendHotel.getCancellationPolicies().size() + ", new_cancellation_policy: " + cancellationPolicyList.size());
                                                AtomicInteger i = new AtomicInteger(0);
                                                for (CancellationPolicy cancellationPolicy : cancellationPolicyList) {
                                                    String input = "{\"type\":\"create_hotel_cancellation_policy\",\"event_data\":{\"cancellation_policy\":" + new Gson().toJson(cancellationPolicy) + "}}";
                                                    JsonObject jsonAddCancellationPolicy = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId() + "/cancellation-policies?token=" + loginToken, input), JsonObject.class);
                                                    System.out.println("i: " + backendHotelMetadataCounter + ", hotel_id: " + hotel.getHotelId() + ", cancellation policy@: " + i.incrementAndGet() + ", cancellationPolicyId: " + jsonAddCancellationPolicy.get("id").toString() + ", status: " + jsonAddCancellationPolicy.get("status").toString());
                                                    sleep(100);
                                                }
                                            } else {
                                                // old version not specify cancellation_policy
                                            }
                                        } else {
                                            // already added
                                        }
                                        //</editor-fold>
                                    }

                                    if (1 == 0) {
                                        //<editor-fold desc="REST API add minimum_night_stay">
                                        // TODO :: Add minimum_night_stay == doing parallels just put "if (0==1)" instead
                                        JsonObject checkMinimumNightStayAddedResponse = new Gson().fromJson(doHttpGetClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId() + "/minimum-night-stay?token=" + loginToken), JsonObject.class);
                                        HotelMinimumNightStay hotelMinimumNightStay = new Gson().fromJson(checkMinimumNightStayAddedResponse.get("result"), HotelMinimumNightStay.class);
                                        if ((hotelMinimumNightStay == null) || (hotelMinimumNightStay.getMiniMumNightStayList() == null) || (hotelMinimumNightStay.getMiniMumNightStayList().size() == 0)) {
                                            if (!StringUtils.isEmpty(_backendHotel.getMinimumNightStayPeriods()) && (_backendHotel.getMinimumNightStayPeriods().size() != 0)) {
                                                List<MiniMumNightStay> miniMumNightStayList = new ArrayList<>();
                                                hotel.getRoomClasses().forEach(roomClass -> {
                                                    MiniMumNightStay miniMumNightStay = new MiniMumNightStay();
                                                    _backendHotel.getMinimumNightStayPeriods().forEach(_minimumNightStay -> {
                                                        if (!StringUtils.isEmpty(_minimumNightStay.getMinimumNightStay())) {
                                                            miniMumNightStay.setNights(convertObjectToInt(_minimumNightStay.getMinimumNightStay()));
                                                        } else {
                                                            // should not happended
                                                        }
                                                        if (!StringUtils.isEmpty(_minimumNightStay.getApplicationCriteria())) {
                                                            miniMumNightStay.setApplicationCriteria(_minimumNightStay.getApplicationCriteria().replaceAll(":", ""));
                                                        } else {
                                                            // should not happended
                                                        }
                                                        miniMumNightStay.setRoomClass(roomClass.getRoomClassId());
                                                        if (_minimumNightStay.getPeriod() != null) {
                                                            miniMumNightStay.setFromDate(transformOldDateToString(_minimumNightStay.getPeriod().get("from")));
                                                            miniMumNightStay.setToDate(transformOldDateToString(_minimumNightStay.getPeriod().get("to")));
                                                        } else {
                                                            // should not happended
                                                        }
                                                        miniMumNightStay.setMarket("market::1");
                                                        miniMumNightStayList.add(miniMumNightStay);
                                                    });
                                                });

                                                System.out.println("hotel_id: " + hotel.getHotelId() + ", room_classes: " + hotel.getRoomClasses().size() + ", old_minimum_night_stay: " + _backendHotel.getMinimumNightStayPeriods().size() + ", new_minimum_night_stay: " + miniMumNightStayList.size());
                                                AtomicInteger i = new AtomicInteger(0);
                                                for (MiniMumNightStay miniMumNightStay : miniMumNightStayList) {
                                                    String input = "{\"type\":\"create_hotel_minimum_night_stay\",\"event_data\":{\"minimum_night_stay\":" + new Gson().toJson(miniMumNightStay) + "}}";
                                                    JsonObject jsonMinimumNightStayResponse = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId() + "/minimum-night-stay?token=" + loginToken, input), JsonObject.class);
                                                    System.out.println("i: " + backendHotelMetadataCounter + ", hotel_id: " + hotel.getHotelId() + ", minimum_night_stay@: " + i.incrementAndGet() + ", minimum_night_stay_id: " + jsonMinimumNightStayResponse.get("id").toString() + ", status: " + jsonMinimumNightStayResponse.get("status").toString());
                                                    sleep(100);
                                                }
                                            } else {
                                                // v2 no any specify minimum_night_stay
                                            }
                                        } else {
                                            // // already load minimum night stay
                                        }
                                        //</editor-fold>
                                    }

                                    if (1 == 0) {
                                        //<editor-fold desc="REST API add child_policy">
                                        // TODO :: Add child_policy == doing parallels just put "if (0==1)" instead
                                        JsonObject checkChildPolicyAddedResponse = new Gson().fromJson(doHttpGetClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId() + "/child-policy?token=" + loginToken), JsonObject.class);
                                        HotelChildPolicy hotelChildPolicy = new Gson().fromJson(checkChildPolicyAddedResponse.get("result"), HotelChildPolicy.class);
                                        if ((hotelChildPolicy == null) || (hotelChildPolicy.getChildPolicyList() == null) || (hotelChildPolicy.getChildPolicyList().size() == 0)) {
                                            if (!StringUtils.isEmpty(_backendHotel.getChildPolicy())) {
                                                List<ChildPolicy> childPolicies = new ArrayList<>();

                                                System.out.println("hotel_id: " + hotel.getHotelId() + ", room_classes: " + hotel.getRoomClasses().size() + ", old_child_policy: " + _backendHotel.getChildPolicy().size() + ", new_child_policy: " + childPolicies.size());
                                                AtomicInteger i = new AtomicInteger(0);
                                                for (ChildPolicy childPolicy : childPolicies) {
                                                    String input = "{\"type\":\"create_hotel_child_policy\",\"event_data\":{\"child_policy\":" + new Gson().toJson(childPolicy) + "}}";
                                                    JsonObject jsonChildPolicyResponse = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId() + "/child-policy?token=" + loginToken, input), JsonObject.class);
                                                    System.out.println("i: " + backendHotelMetadataCounter + ", hotel_id: " + hotel.getHotelId() + ", child_policy@: " + i.incrementAndGet() + ", child_policy_id: " + jsonChildPolicyResponse.get("id").toString() + ", status: " + jsonChildPolicyResponse.get("status").toString());
                                                    sleep(100);
                                                }
                                            } else {
                                                // v2 no any specify minimum_night_stay
                                            }
                                        } else {
                                            // already load minimum night stay
                                        }
                                        //</editor-fold>
                                    }

                                    if (1 == 0) {
                                        //<editor-fold desc="REST API add hotel_options">
                                        // TODO :: Add hotel_options == doing parallels just put "if (0==1)" instead
                                        JsonObject checkHotelOptionAddedResponse = new Gson().fromJson(doHttpGetClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId() + "/option?token=" + loginToken), JsonObject.class);
                                        HotelOption hotelOption = new Gson().fromJson(checkHotelOptionAddedResponse.get("result"), HotelOption.class);
                                        if ((hotelOption == null) || (hotelOption.getOptionList() == null) || (hotelOption.getOptionList().size() == 0)) {
                                            if (!StringUtils.isEmpty(_backendHotel.getOptions()) && (_backendHotel.getOptions().size() != 0)) {
                                                List<Option> optionList = new ArrayList<>();
                                                _backendHotel.getOptions().forEach(_option -> {
                                                    //<editor-fold desc="validate data and copy to new object">
                                                    Option option = new Option();
                                                    Boolean isDataProblem = false;
                                                    if (!StringUtils.isEmpty(_option.getName())) {
                                                        option.setName(_option.getName().toString());
                                                    } else {
                                                        isDataProblem = true;
                                                    }
                                                    if (!StringUtils.isEmpty(_option.getRate())) {
                                                        option.setRate(String.format("%1$.2f", _option.getRate().getAmount().getSatang() / 100.0));
                                                        option.setCurrencyCode(_option.getRate().getAmount().getCurrency().toString());
                                                    } else {
                                                        isDataProblem = true;
                                                    }
                                                    if (!StringUtils.isEmpty(_option.getAppliesTo())) {
                                                        option.setAppliesTo(_option.getAppliesTo().toString());
                                                    } else {
                                                        isDataProblem = true;
                                                    }
                                                    if (!StringUtils.isEmpty(_option.getAppliesEvery())) {
                                                        option.setAppliesEvery(_option.getAppliesEvery().toString());
                                                    } else {
                                                        isDataProblem = true;
                                                    }
                                                    if (!StringUtils.isEmpty(_option.isCompulsory())) {
                                                        option.setCompulsory(_option.isCompulsory());
                                                    } else {
                                                        isDataProblem = true;
                                                    }
                                                    if (!StringUtils.isEmpty(_option.getPeriod())) {
                                                        if (!StringUtils.isEmpty(transformOldDateToString(_option.getPeriod().get("from")))) {
                                                            option.setFromDate(transformOldDateToString(_option.getPeriod().get("from")));
                                                        } else {
                                                            isDataProblem = true;
                                                        }
                                                        if (!StringUtils.isEmpty(transformOldDateToString(_option.getPeriod().get("to")))) {
                                                            option.setToDate(transformOldDateToString(_option.getPeriod().get("to")));
                                                        } else {
                                                            isDataProblem = true;
                                                        }
                                                    } else {
                                                        isDataProblem = true;
                                                    }
                                                    if (!StringUtils.isEmpty(_option.getMarket())) {
                                                        option.setMarket(_option.getMarket().toString());
                                                    } else {
                                                        isDataProblem = true;
                                                        option.setMarket("Market::1");
                                                        writeToFileApacheCommonIO(hotel.getHotelId() + System.lineSeparator(), optionsProblemFile);
                                                        isDataProblem = false;
                                                    }
                                                    //</editor-fold>
                                                    if (!isDataProblem) {
                                                        // only for room_class if not specify means all room_class
                                                        if (!StringUtils.isEmpty(_option.getRoomClass())) {
                                                            option.setRoomClass(_option.getRoomClass().toString());
                                                            optionList.add(option);
                                                        } else {
                                                            hotel.getRoomClasses().forEach(roomClass -> {
                                                                option.setRoomClass(roomClass.getRoomClassName());
                                                                optionList.add(option);
                                                            });
                                                        }
                                                    } else {
                                                        // write to file or something
                                                        writeToFileApacheCommonIO(hotel.getHotelId() + System.lineSeparator(), optionsProblemFile);
                                                    }
                                                });

                                                System.out.println("hotel_id: " + hotel.getHotelId() + ", old_options: " + _backendHotel.getOptions().size());
                                                AtomicInteger i = new AtomicInteger(0);
                                                for (Option option : optionList) {
                                                    String input = "{\"type\":\"create_hotel_option\",\"origin\":\"ms-load-data\",\"event_data\":{\"option\":" + new Gson().toJson(option) + "}}";
                                                    JsonObject jsonOptionsResponse = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId() + "/option?token=" + loginToken, input), JsonObject.class);
                                                    System.out.println("i: " + backendHotelMetadataCounter + ", hotel_id: " + hotel.getHotelId() + ", option@: " + i.incrementAndGet() + ", option_id: " + jsonOptionsResponse.get("id").toString() + ", status: " + jsonOptionsResponse.get("status").toString());
                                                    sleep(50);
                                                }
                                            } else {
                                                // v2 no any specify options
                                            }
                                        } else {
                                            // already load options
                                        }
                                        //</editor-fold>
                                    }

                                    //</editor-fold>
                                }
                            } else {
                                // can't found hotel_id on new version which we have backend_hotel data from old version
                                backendHotelMetadataMissMatchCounter++;
                            }
                            //sleep(50);
                        } else {
                            // failed to map json to _BackEndHotelMetadata
                            backendHotelMetadataFailedCounter++;
                        }
                        //sleep(50);
                    }
                }

                //<editor-fold desc="load data for ms-locations">
                if (1==0) {
                    if (countryDataPattern.matcher(readLine).find()) {
                        String[] tokens = readLine.split("^[().&a-z:_-]+,[0-9]+,[0-9]+,[0-9]+,\"|\",[0-9]+,[0-9]+,[0-9]+$");
                        String jsonString = tokens[1].replace("\"\"", "\"");

                        _Country _country = new Gson().fromJson(jsonString, _Country.class);

                        String input = "{\"type\":\"new_country\",\"event_data\":" + new Gson().toJson(_country) + "}";
                        JsonObject jsonNewCountry = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":8089/sunseries/v1/countries" + "?token=" + loginToken2, input), JsonObject.class);
                        System.out.println("Added :: country_id: " + jsonNewCountry.get("country").toString());
                        sleep(50);
                    }
                }
                if (1==0) {
                    if (cityDataPattern.matcher(readLine).find()) {
                        String[] tokens = readLine.split("^[().&a-z:_-]+,[0-9]+,[0-9]+,[0-9]+,\"|\",[0-9]+,[0-9]+,[0-9]+$");
                        String jsonString = tokens[1].replace("\"\"", "\"");
                        System.out.println(jsonString);

                        _City _city = new Gson().fromJson(jsonString, _City.class);

                        City city = new City();
                        city.setName(_city.getName());
                        city.setCode(_city.getCode());
                        city.setLatitude(_city.getLatitude());
                        city.setLongitude(_city.getLongitude());
                        if (!StringUtils.isEmpty(_city.getImage()) && !StringUtils.isEmpty(_city.getImage().getImageUrl()) && !StringUtils.isEmpty(_city.getImage().getThumbnailUrl())) {
                            city.setImageUrl(_city.getImage().getImageUrl().toString());
                            city.setThumnailUrl(_city.getImage().getThumbnailUrl().toString());
                        }

                        String input = "{\"type\":\"new_city\",\"event_data\":" + new Gson().toJson(city) + "}";
                        JsonObject jsonNewCity = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":8089/sunseries/v1/countries/" + _city.getCountryId().toString() + "/cities?token=" + loginToken2, input), JsonObject.class);
                        System.out.println("Added :: city_id: " + jsonNewCity.get("city").toString());
                        sleep(50);

                        if (!StringUtils.isEmpty(jsonNewCity.get("city"))) {
                            if (!StringUtils.isEmpty(_city.getAreaList()) && _city.getAreaList().size() > 0) {
                                for (_Area _area : _city.getAreaList()) {
                                    _area.setId("area::" + _area.getId());
                                    input = "{\"type\":\"new_area\",\"event_data\":" + new Gson().toJson(_area) + "}";
                                    JsonObject jsonNewArea = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":8089/sunseries/v1/cities/" + _city.getId().toString() + "/areas?token=" + loginToken2, input), JsonObject.class);
                                    System.out.println("Added :: area_id: " + jsonNewArea.get("area").toString());
                                    sleep(50);
                                }
                            }
                        }
                    }
                }
                //</editor-fold>

                if (1==0) {
                    //<editor-fold desc="RestAPI to load promotion_container">
                    if (promotionDataPattern.matcher(readLine).find()) {
                        // extract field of value (which is field 4 of export.csv file)
                        //<editor-fold desc="fixed format for promotion_container">
                        String[] tokens = readLine.split("^[\\w:\\d,-]+\"|\"[,\\d]+$");
                        String jsonString = tokens[1].replaceAll("\"\"", "%@").replaceAll("%@", "\"");
                        //</editor-fold>

                        //System.out.println(jsonString);

                        _PromotionContainer _promotionContainer = new Gson().fromJson(jsonString, _PromotionContainer.class);
                        PromotionContainer promotionContainer = new PromotionContainer();
                        List<Promotion> promotionList = new ArrayList<>();
                        Boolean isDataProblem = false;
                        //<editor-fold desc="promotion_containers transform">
                        if (!StringUtils.isEmpty(_promotionContainer)) {
                            promotionContainer.setCode(_promotionContainer.getId());
                        } else {
                            isDataProblem = true;
                        }
                        promotionContainer.setAllotmentsPromotionalEnabled(false);
                        if (!StringUtils.isEmpty(_promotionContainer.getDescription())) {
                            promotionContainer.setDescription(_promotionContainer.getDescription());
                        } else {
                            //isDataProblem = true;
                        }
                        if (!StringUtils.isEmpty(_promotionContainer.getName())) {
                            promotionContainer.setName(_promotionContainer.getName());
                        } else {
                            isDataProblem = true;
                        }
                        if (!StringUtils.isEmpty(_promotionContainer.getTags())) {
                            promotionContainer.setPromotionTag(_promotionContainer.getTags());
                        } else {
                            //isDataProblem = true;
                        }
                        //</editor-fold>

                        //<editor-fold desc="RESP API for create promotion_container">
                    /*String payload = "{\"type\":\"update_hotel\",\"origin\":\"ms-load-data\",\"event_data\":{\"hotel\": { \"hotel_id\":\"" + hotel.getHotelId() + "\", \"images\":" + new Gson().toJson(imageList) + "}}}";
                    jsonUpdateHotelResponse = new Gson().fromJson(doHttpPatchClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId().replaceAll("\"", "") + "?token=" + loginToken, payload), JsonObject.class);
                    System.out.println("i: " + backendHotelMetadataCounter + ", id: " + jsonUpdateHotelResponse.get("id").toString() + ", hotel_update_backend :: add images status: " + jsonUpdateHotelResponse.get("status").toString());
                    sleep(50);*/
                        //</editor-fold>
                        String payload = "{\"type\":\"get_all_room_class\",\"origin\":\"ms-load-data\"}";
                        JsonObject jsonGetRoomClasses = new Gson().fromJson(doHttpGetClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + _promotionContainer.getHotelServiceId().substring(_promotionContainer.getHotelServiceId().indexOf("suns")) + "/room-classes?token=" + loginToken), JsonObject.class);
                        if (!_promotionContainer.getPromotions().isEmpty()) {
                            _promotionContainer.getPromotions().stream().forEach(_promotion -> {
                                switch (_promotion.getO().toString()) {
                                    case "Sunseries::Domain::Model::FreeProductPromotion":
                                        System.out.println(jsonString);
                                        FreeProductPromotion freeProductPromotion = new FreeProductPromotion();
                                        freeProductPromotion.setType("free_product");
                                        if (!StringUtils.isEmpty(_promotion.getId())) {
                                            freeProductPromotion.setId(_promotion.getId());
                                        } else {
                                            // data have problem
                                        }
                                        if (!StringUtils.isEmpty(_promotion.getBlackoutPeriod()) && !_promotion.getBlackoutPeriod().isEmpty()) {
                                            freeProductPromotion.setBlackoutPeriods(new ArrayList<>());
                                            _promotion.getBlackoutPeriod().stream().forEach(_period -> {
                                                BlackoutPeriod blackoutPeriod = new BlackoutPeriod();
                                                blackoutPeriod.setRoomClass(new ArrayList<>());
                                                blackoutPeriod.setBlackoutFrom(transformOldDateToString(_period.getPeriod().getFrom()));
                                                blackoutPeriod.setBlackoutTo(transformOldDateToString(_period.getPeriod().getTo()));
                                                if (_period.getRoomClassIds() == null) {
                                                    if (jsonGetRoomClasses.get("status").toString().replaceAll("\"", "").equals("SUCCESS") && jsonGetRoomClasses.get("room_classes").isJsonArray()) {
                                                        List<Map<String, String>> roomClassList = new Gson().fromJson(new Gson().toJson(jsonGetRoomClasses.get("room_classes")), List.class);
                                                        roomClassList.forEach(roomClass -> blackoutPeriod.getRoomClass().add(roomClass.get("room_class_id").toString()));
                                                    } else {
                                                        System.out.println("data is problem");
                                                    }
                                                } else {
                                                    _period.getRoomClassIds().stream().forEach(_roomClass -> {
                                                        blackoutPeriod.getRoomClass().add(_roomClass.substring(_roomClass.indexOf("suns")));
                                                    });
                                                }
                                                freeProductPromotion.getBlackoutPeriods().add(blackoutPeriod);
                                            });
                                        } else {
                                            // normal
                                        }
                                        if (!StringUtils.isEmpty(_promotion.getActions()) && (!_promotion.getActions().isEmpty())) {
                                            System.out.println("something to do");
                                        }
                                        if (!StringUtils.isEmpty(_promotion.getChecks()) && (!_promotion.getChecks().isEmpty())) {
                                            System.out.println("something to do");
                                        }
                                        if (!StringUtils.isEmpty(_promotion.getPromotionCharge())) {
                                            System.out.println("something to do");
                                        }
                                        if (!StringUtils.isEmpty(_promotion.getSpec())) {
                                            _Spec _spec = new Gson().fromJson(new Gson().toJson(_promotion.getSpec()), _Spec.class);
                                            freeProductPromotion.setCode(_spec.getCode());
                                            freeProductPromotion.setApplicableFrom(transformOldDateToString(_spec.getApplicablePeriod().getFrom()));
                                            freeProductPromotion.setApplicableTo(transformOldDateToString(_spec.getApplicablePeriod().getTo()));
                                            freeProductPromotion.setApplicableDaysOfWeek(_spec.getApplicableDays());
                                            // Todo:: check _spec.getEarlyBird() boolean
                                            freeProductPromotion.setMarket(_spec.getMarket());
                                            freeProductPromotion.setMaximumAppliedNights(convertObjectToInt(_spec.getMaximumAppliedNights()));
                                            freeProductPromotion.setMaximumNightStay(convertObjectToInt(_spec.getMaximumNightStay()));
                                            freeProductPromotion.setMinimumNightStay(convertObjectToInt(_spec.getMinimumNightStay()));
                                            freeProductPromotion.setMinimumNightStayInsidePromotion(convertObjectToInt(_spec.getMinimumNightStayInsidePromotion()));
                                            freeProductPromotion.setMinimumRooms(convertObjectToInt(_spec.getMinimumRooms()));
                                            freeProductPromotion.setForceCombine(_spec.getForcedCombinable());
                                            freeProductPromotion.setExclusiveCombine(_spec.getExclusive());
                                            freeProductPromotion.setInflexible(_spec.getInflexible());
                                            _spec.getRoomClasses().stream().forEach(_roomClass -> {
                                                if (freeProductPromotion.getApplicableRoomClass() == null) {
                                                    freeProductPromotion.setApplicableRoomClass(new ArrayList<>());
                                                }
                                                freeProductPromotion.getApplicableRoomClass().add(_roomClass.substring(_roomClass.indexOf("suns")));
                                            });
                                            freeProductPromotion.setBookByDate(transformOldDateToString(_spec.getBookByDate()));
                                            freeProductPromotion.setApplicableDaysOfWeek(_spec.getApplicableDays());
                                            if (!StringUtils.isEmpty(_spec.getArgs().getO()) && (_spec.getArgs().getO().equals("ActiveSupport::HashWithIndifferentAccess"))) {
                                                freeProductPromotion.setCurrencyCode(_spec.getArgs().getSelf().get("charge_currency_code").toString().replaceAll("\"", ""));

                                                if (freeProductPromotion.getFreeProductSpecifications() == null) {
                                                    freeProductPromotion.setFreeProductSpecifications(new ArrayList<>());
                                                }
                                                ((List<String>) _spec.getArgs().getSelf().get("products")).stream().forEach(_product -> freeProductPromotion.getFreeProductSpecifications().add(_product.toString().replaceAll("\"", "")));
                                            } else {
                                                JsonElement jsonElement = new Gson().fromJson(new Gson().toJson(_promotion.getSpec()), JsonElement.class);

                                                freeProductPromotion.setCurrencyCode(jsonElement.getAsJsonObject().get("args").getAsJsonObject().get(":charge_currency_code").toString().replaceAll("\"", ""));
                                                jsonElement.getAsJsonObject().get("args").getAsJsonObject().get(":products").getAsJsonArray().forEach(_product -> {
                                                    if (freeProductPromotion.getFreeProductSpecifications() == null) {
                                                        freeProductPromotion.setFreeProductSpecifications(new ArrayList<>());
                                                    }
                                                    freeProductPromotion.getFreeProductSpecifications().add(_product.toString().replace("\"", ""));
                                                });
                                            }
                                        }
                                        break;
                                    case "Sunseries::Domain::Model::FreeUpgradePromotion":
                                        break;
                                    case "Sunseries::Domain::Model::FreeNightWithBonusRatePromotion":
                                        break;
                                    case "Sunseries::Domain::Model::FreeNightPromotion":
                                        break;
                                    case "Sunseries::Domain::Model::RoomNightsPackagePromotion":
                                        break;
                                    case "Sunseries::Domain::Model::PercentageDiscountPromotion":
                                        break;
                                    case "Sunseries::Domain::Model::FlatRatePromotion":
                                        break;
                                    default:
                                        break;
                                }
                            });
                        }

                    }
                    //</editor-fold>
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.print("Hotel total: " + hotelMetadataCounter);
        System.out.println(", Hotel can't map: " + hotelMetadataFailedCounter);
        System.out.print("BackendHotel total: " + backendHotelMetadataCounter);
        System.out.print(", BackendHotel can't map: " + backendHotelMetadataFailedCounter);
        System.out.println(", BackendHotel miss match id: " + backendHotelMetadataMissMatchCounter);
    }

    private static String transformOldDateToString(Object date) {
        if (!StringUtils.isEmpty(date)) {
            if (date.getClass().equals(String.class)) {
                return date.toString();
            } else if (date.getClass().equals(LinkedTreeMap.class)) {
                _Date _date = new Gson().fromJson(new Gson().toJson(date), _Date.class);
                return _date.toString();
            }
        }
        return "date-have-problem";
    }

    private static Integer convertObjectToInt(Object numberKeepAsString) {
        Integer returnInt = null;
        if (!StringUtils.isEmpty(numberKeepAsString)) {
            returnInt = (int)Float.parseFloat(numberKeepAsString.toString().replaceAll("`",""));
        }
        return returnInt;
    }

    public static Hotel transformHotel(_HotelMetadata _hotel) {
        Hotel hotel = new Hotel();
        hotel.setHotelName(_hotel.getName());
        List<String> hotelTypeList = new ArrayList<>();
        if ((!StringUtils.isEmpty(_hotel.getTypes()) && (!_hotel.getTypes().isEmpty()))) {
            _hotel.getTypes().forEach(_type -> {
                if (_type != null) {
                    Map<String, String> tableMap = (Map<String, String>) _type.get("table");
                    hotelTypeList.add(tableMap.get(":name").toString());
                }
            });
        }
        hotel.setHotelTypes(hotelTypeList);
        if (!StringUtils.isEmpty(_hotel.getDescription())) {
            hotel.setDescription(_hotel.getDescription());
        }
        hotel.setCity(_hotel.getCityId());
        if (!StringUtils.isEmpty(_hotel.getStars())) {
            hotel.setStar(convertObjectToInt(_hotel.getStars()).toString());
        }
        if (!StringUtils.isEmpty(_hotel.getTelephone())) {
            hotel.setTelephone(_hotel.getTelephone());
        }
        if (!StringUtils.isEmpty(_hotel.getAddress())) {
            hotel.setAddress(_hotel.getAddress());
        }
        if (!StringUtils.isEmpty(_hotel.getFax())) {
            hotel.setFax(_hotel.getFax());
        }
        List<String> amenitiesList = new ArrayList<>();
        if ((!StringUtils.isEmpty(_hotel.getAmenities()) && (!_hotel.getAmenities().isEmpty()))) {
            _hotel.getAmenities().forEach(map -> {
                        if (map.get("^o").equals("Sunseries::Domain::Model::Hotel::Amenity")) {
                            hotelTypeList.add(map.get("description").toString());
                        } else {
                            Map<String, String> tableMap = (Map<String, String>) map.get("table");
                            amenitiesList.add(tableMap.get(":description").toString());
                        }
                    });
            hotel.setAmenities(amenitiesList);
        }
        if (!StringUtils.isEmpty(_hotel.getRemarks())) {
            List<String> remarkList = new ArrayList<>();
            ((List<Map<String, Object>>) _hotel.getRemarks()).forEach(map -> {
                remarkList.add(map.get("description").toString());
            });
            hotel.setRemark(remarkList);
        }
        if (_hotel.getLatitude() != null && _hotel.getLongitude() != null) {
            hotel.setLocation(new Location(_hotel.getLatitude().toString(), _hotel.getLongitude().toString()));
        }
        return hotel;
    }

    public static String doHttpPostClient(String url, String input) throws UnirestException {
        HttpResponse<String> response = Unirest.post(url)
                .header("content-type", "application/json")
                .header("cache-control", "no-cache")
                .body(input)
                .asString();
        return response.getBody().toString();
    }

    public static String doHttpPatchClient(String url, String input) throws UnirestException {
        HttpResponse<String> response = Unirest.patch(url)
                .header("content-type", "application/json")
                .header("cache-control", "no-cache")
                .body(input)
                .asString();
        return response.getBody().toString();
    }

    public static String doHttpDeleteClient(String url) throws UnirestException {
        HttpResponse<String> response = Unirest.delete(url)
                .header("content-type", "application/json")
                .header("cache-control", "no-cache")
                .asString();
        return response.getBody().toString();
    }

    public static String doHttpGetClient(String url) throws UnirestException {
        HttpResponse<String> response = Unirest.get(url)
                .header("cache-control", "no-cache")
                .header("postman-token", "11b8d48b-be8f-839e-8ea5-4fd13727e859")
                .asString();
        return response.getBody().toString();
    }

    public static void writeToFileApacheCommonIO(String msg, File file) {
        try {
            // 3rd parameter boolean append = true
            FileUtils.writeStringToFile(file, msg, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
