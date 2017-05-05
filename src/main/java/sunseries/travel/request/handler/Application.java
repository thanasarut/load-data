package sunseries.travel.request.handler;

import com.google.common.base.CaseFormat;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import sun.awt.image.ImageWatched;
import sun.util.resources.cldr.ro.CalendarData_ro_MD;
import sunseries.travel.request.handler.message.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {
        String serverHost = "127.0.0.1";
        String serverPort = "8080";

        //String fileName = "/Users/thanasarut/sunseries/source_load_data/hotelMetadata.sort.csv";
        String fileName = "/Users/thanasarut/sunseries/source_load_data/backend_hotel.csv";
        //String fileName = "/Users/thanasarut/sunseries/source_load_data/room_rate.sort.csv";
        //String fileName = "/Users/thanasarut/sunseries/source_load_data/temp.csv";
        //String fileName = "/Users/vick.thanasarut/sunseries/data_abook_127.0.0.1.csv";

        File childPolicyProblemFile = new File("./childPolicyProblem.txt");

        // Pattern of Hotel Metadata -- It will start with "sunsXXXX" hotel_id
        String hotelMetaDataRegEx = "^suns[0-9]";
        Pattern hotelMetaDataPattern = Pattern.compile(hotelMetaDataRegEx);

        // Pattern of RoomClass Metadata -- It will start with "rcm::{hotelId}"
        String hotelBackEndMetaDataRegEx = "^backend_hotel::";
        Pattern hotelBackEndMetaDataPattern = Pattern.compile(hotelBackEndMetaDataRegEx);

        // Pattern of RoomRate Metadata -- It will start with "room_rate::{roomClassId}"
        String hotelBaseRateDataRegEx = "^room_rate::";
        Pattern hotelBaseRateDataPattern = Pattern.compile(hotelBaseRateDataRegEx);

        String loginToken = null;
        int hotelMetadataCounter = 0, hotelMetadataFailedCounter = 0;
        int backendHotelMetadataCounter = 0, backendHotelMetadataFailedCounter = 0, backendHotelMetadataMissMatchCounter = 0;
        int hotelBaseRateCounter = 0, hotelBaseRateFailedCounter = 0;
        List<String> hotelWhichNotSpecifyBedTypeOrMaxOccu = new ArrayList<>();

        try {
            if (loginToken == null) {
                String input = "{\"type\":\"authenticate\",\"event_data\":{\"email\":\"pea@sunseries.travel\",\"password\":\"P@ssw0rd\"},\"id\":\"2a7075216f26dc06cae416ef45a3ecd4\",\"ttid\":\"2a7075216f26dc06cae416ef45a3ecd4\",\"origin\":\"postman\"}";
                //String stringHttpResponse = doHttpPostClient("http://172.16.2.4:8080/sunseries/v1/authenticate", input);
                String loginResponseData = doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/authenticate", input);
                JsonObject jsonResponseData = new Gson().fromJson(loginResponseData, JsonObject.class);
                loginToken = jsonResponseData.get("token").toString().replaceAll("\"", "");
            }
            System.out.println("token: " + loginToken);

            // Reading from export.csv file
            File f = new File(fileName);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";

            System.out.println("Reading file using Buffered Reader");

            while ((readLine = b.readLine()) != null) {
                // filter only match of pattern
                if (hotelMetaDataPattern.matcher(readLine).find()) {
                    // extract field of value (which is field 4 of export.csv file
                    String[] tokens = readLine.split(",\"\\{|}\",");
                    String jsonString = "{" + tokens[1].replace("\"\"", "\"") + "}";

                    // fixed type remarks transform
                    jsonString = jsonString.replace("\"remarks\":\"\"", "\"remarks\":null");
                    jsonString = jsonString.replace("\"remarks\":[]", "\"remarks\":null");
                    jsonString = jsonString.replaceAll("\"remarks\":\"(.+)\",\"room_count", "\"remarks\":[{\"^o\": \"Sunseries::Domain::Model::Hotel::Remark\", \"description\": \"$1\", \"id\":\"\"}],\"room_count");
                    // fixed type stars transform
                    jsonString = jsonString.trim();
                    jsonString = jsonString.replace("\"stars\":([0-9])", "\"stars\":\"$1\"");

                    _HotelMetadata _hotel = new Gson().fromJson(jsonString, _HotelMetadata.class);
                    if (_hotel != null) {
                        hotelMetadataCounter++;
                        System.out.println(jsonString);
                        Hotel hotel = transformHotel(_hotel);
                        String newHoteljSon = new Gson().toJson(hotel);

                        // if hotelMetada already add then skipped
                        JsonObject jsonHotelResponseData = new Gson().fromJson(doHttpGetClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + _hotel.getId().replaceAll("\"","") + "?token=" + loginToken), JsonObject.class);
                        if (jsonHotelResponseData.get("status").toString().equals("\"SUCCESS\"")) {
                            continue;
                        }

                        if (Integer.parseInt(_hotel.getId().substring(4)) == hotelMetadataCounter) {
                            // add hotel meta data with old data
                            String payload = "{\"type\":\"create_hotel\",\"origin\":\"ms-load-data\",\"event_data\":{\"hotel\":" + newHoteljSon + "}}";
                            JsonObject jsonAddHotelResponse = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels?token=" + loginToken, payload), JsonObject.class);
                            System.out.println("i: " + hotelMetadataCounter + ", id: " + jsonAddHotelResponse.get("id").toString() + ", add_hotel status: " + jsonAddHotelResponse.get("status").toString());
                            //System.out.println(i);
                        } else {
                            // no _hotel_id X no need to add empty hotel
                            String payload = "{\"type\":\"create_hotel\",\"origin\":\"ms-load-data\",\"event_data\":{\"hotel\":{\"hotel_name\":\"dummy\"}}}";
                            JsonObject jsonAddHotelResponse = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels?token=" + loginToken, payload), JsonObject.class);
                            System.out.println("i: " + hotelMetadataCounter + ", id: " + jsonAddHotelResponse.get("id").toString() + ", add_hotel_dummy status: " + jsonAddHotelResponse.get("status").toString());
                            //System.out.println("dummy: " + i);

                            // add delay for couchbase
                            sleep(300);

                            // real information
                            hotelMetadataCounter++;
                            payload = "{\"type\":\"create_hotel\",\"origin\":\"ms-load-data\",\"event_data\":{\"hotel\":" + newHoteljSon + "}}";
                            jsonAddHotelResponse = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels?token=" + loginToken, payload), JsonObject.class);
                            System.out.println("i: " + hotelMetadataCounter + ", id: " + jsonAddHotelResponse.get("id").toString() + ", add_hotel status: " + jsonAddHotelResponse.get("status").toString());
                            //System.out.println(i);

                            // delete dummy information
                            /*stringHttpResponse = doHttpDeleteClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + dummyId.replaceAll("\"","") + "?token=" + loginToken);
                            httpResponseData = new Gson().fromJson(stringHttpResponse, JsonObject.class);
                            System.out.println("delete status: " + httpResponseData.get("status").toString());*/
                        }
                    } else {
                        hotelMetadataFailedCounter++;
                    }
                    // add delay for couchbase
                    sleep(300);
                } else if (hotelBaseRateDataPattern.matcher(readLine).find()) {
                    // TODO :: display_markup from v.2 still need to add to ms-agents in v.3
                    if (!Pattern.compile(",[0-9]+,[0-9]+,[0-9]+,[{}]+").matcher(readLine).find()) {
                        hotelBaseRateCounter++;
                        // means specify room_rate
                        // extract field of value (which is field 4 of export.csv file
                        String[] tokens = readLine.split(",[0-9]+,[0-9]+,[0-9]+,\\\"|\",[0-9]+,[0-9]+,[0-9]+$");
                        String[] _id_of_hotel_base_rate_id_array = tokens[0].replaceAll("\"\"", "\"").split("::|-");
                        String _hotel_base_id = _id_of_hotel_base_rate_id_array[_id_of_hotel_base_rate_id_array.length - 3];
                        String _hotel_id = _id_of_hotel_base_rate_id_array[_id_of_hotel_base_rate_id_array.length -2];
                        String _hotel_base_rate_id = _id_of_hotel_base_rate_id_array[_id_of_hotel_base_rate_id_array.length - 2] + "-" + _id_of_hotel_base_rate_id_array[_id_of_hotel_base_rate_id_array.length - 1];

                        String jsonString = tokens[1].replaceAll("\"\"", "\"");
                        System.out.println(_hotel_base_rate_id);

                        //if (Integer.parseInt(_hotel_id.substring(4)) == hotelBaseRateCounter) {
                        if (1==1) {
                            // fixed any [] empty list;
                            jsonString = jsonString.replaceAll(":\\[]", ":null");

                            // fixed \" to " only
                            jsonString = jsonString.replaceAll("\\\\\"", "\"");
                            // fixed "{ at beginning and end }"
                            jsonString = jsonString.replaceAll("\"\\{", "{");
                            jsonString = jsonString.replaceAll("}\"", "}");

                            System.out.println(jsonString);

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
                                    baseRate.setMarket(_market.getMarket());
                                    if (_periodRate.getValid_period() != null) {
                                        baseRate.setFromDate(transformOldDateToString(_periodRate.getValid_period().get("from")));
                                        baseRate.setToDate(transformOldDateToString(_periodRate.getValid_period().get("to")));
                                    } else {
                                        // not found valid_period --> have only period that is very old data, martin confirm to not load to v.3
                                    }
                                    baseRate.setWeekdays(_periodRate.getValidDay());
                                    baseRate.setRoomRate(String.format("%1$.2f",_periodRate.getAmount().getSatang()/100.0));
                                    baseRate.setCurrencyCode(_periodRate.getAmount().getCurrency());
                                    baseRate.setRoomClass(_hotel_base_rate_id);

                                    SaleMarkup saleMarkup = new SaleMarkup();
                                    String[] _markupValue = _periodRate.getSaleMarkup().getName().split("::");
                                    saleMarkup.setType(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, _markupValue[_markupValue.length-1]));
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
                                });
                            });

                            for (BaseRate baseRate : baseRatesList) {
                                String payload = "{\"type\":\"create_hotel_base_rate\",\"origin\":\"ms-load-data\",\"event_data\":{\"base_rate\":" + new Gson().toJson(baseRate) + "}}";
                                JsonObject jsonAddHotelBaseRateResponse = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + _hotel_id + "/base-rate?token=" + loginToken, payload), JsonObject.class);
                                System.out.println("add_hotel_base_rate status: " + jsonAddHotelBaseRateResponse.get("status").toString());
                                //System.out.println("i: " + hotelBaseRateCounter + ", id: " + _hotel_base_rate_id + ", Doing something");
                                sleep(100);
                            }
                        } else {
                            System.out.println("i: " + hotelBaseRateCounter + ", id: " + _hotel_base_rate_id);
                        }

                    } else {
                        // means specify room_rate
                    }
                } else if (hotelBackEndMetaDataPattern.matcher(readLine).find()) {
                    // extract field of value (which is field 4 of export.csv file
                    String[] tokens = readLine.split("^[a-z:_-]+[0-9]+,[0-9]+,[0-9]+,[0-9]+,\"|\",[0-9]+,[0-9]+,[0-9]+$");
                    String jsonString = tokens[1].replace("\"\"", "\"");

                    // fixed type remarks transform
                    jsonString = jsonString.replace("\"remarks\":\"\"", "\"remarks\":null");
                    jsonString = jsonString.replace("\"remarks\":[]", "\"remarks\":null");
                    jsonString = jsonString.replaceAll("\"remarks\":\"(.+)\",\"room_count", "\"remarks\":[{\"^o\": \"Sunseries::Domain::Model::Hotel::Remark\", \"description\": \"$1\", \"id\":\"\"}],\"room_count");
                    // fixed type stars transform
                    jsonString = jsonString.trim();
                    jsonString = jsonString.replace("\"stars\":([0-9])", "\"stars\":\"$1\"");
                    // fixed any [] empty list;
                    jsonString = jsonString.replaceAll(":\\[]", ":null");
                    // fixed any error missing " ":","
                    jsonString = jsonString.replaceAll("\":\",", "\":\"\",");

                    System.out.println(jsonString);
                    _BackEndHotelMetadata _backendHotel = new Gson().fromJson(jsonString, _BackEndHotelMetadata.class);
                    if (_backendHotel != null) {
                        backendHotelMetadataCounter++;
                        // if hotelMetada already add then skipped
                        JsonObject jsonGetHotelResponse = new Gson().fromJson(doHttpGetClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + _backendHotel.getFacadeId().replaceAll("\"","") + "?token=" + loginToken), JsonObject.class);
                        JsonObject jsonUpdateHotelResponse = null;
                        if (jsonGetHotelResponse.get("status").toString().equals("\"SUCCESS\"")) {
                            // start to copy each data from backend_hotel to hotel
                            Hotel hotel = new Gson().fromJson(jsonGetHotelResponse.get("hotel"), Hotel.class);

                            // TODO : double check childPolicy
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

                            if (hotel.getRoomClasses() == null || hotel.getRoomClasses().size() == 0) {
                                if (_backendHotel.getRoomClasses() != null) {
                                    List<RoomClass> roomClassList = new ArrayList<>();
                                    Integer _rcm_counter = 0;
                                    for (Map<String, Object> _roomClasses : _backendHotel.getRoomClasses()) {
                                        _rcm_counter++;
                                        RoomClass roomClass = new RoomClass();
                                        if (_roomClasses.get("id").equals("backend_hotel::bess-" + hotel.getHotelId() + "-" + String.format("%1$07d",_rcm_counter))) {
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
                                                hotelWhichNotSpecifyBedTypeOrMaxOccu.add(_roomClasses.get("id").toString());
                                            } else {
                                                roomClass.setMaxOccupancyExcludeExtraBed(convertObjectToInt(_roomClasses.get("max_occupancy_without_extra_bed")).toString());
                                                roomClass.setMaxOccupancyIncludeExtraBed(convertObjectToInt(_roomClasses.get("max_occupancy_with_extra_bed")).toString());
                                                roomClass.setMaxAdultIncludeExtraBed(convertObjectToInt(_roomClasses.get("max_adults_with_extra_bed")).toString());
                                                if (_roomClasses.get("mix_adults_children_extra_bed") != null) {
                                                    roomClass.setMixAdultAndChildInRoom(Boolean.parseBoolean(_roomClasses.get("mix_adults_children_extra_bed").toString()));
                                                } else {
                                                    roomClass.setMixAdultAndChildInRoom(false);
                                                }
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
                                                                writeToFileApacheCommonIO("Must fix data :: " + hotel.getHotelId() + " not specify MaxChild of room_class", childPolicyProblemFile);
                                                            }
                                                        } else {
                                                            if (!StringUtils.isEmpty(_max_child.get("backend_hotel::bess-" + hotel.getHotelId() + "-" + String.format("%1$07d", _rcm_counter)))) {
                                                                roomClass.setMaxChild(convertObjectToInt(_max_child.get("backend_hotel::bess-" + hotel.getHotelId() + "-" + String.format("%1$07d", _rcm_counter))).toString());
                                                            } else {
                                                                // jack confirm that if have 0-set or not-set means 0 --> not allow children in that room_class --> martin confirm to put 99 and track the hotel in list, then let other fixed
                                                                roomClass.setMaxChild("99");
                                                                writeToFileApacheCommonIO("Must fix data :: " + hotel.getHotelId() + " not specify MaxChild of room_class", childPolicyProblemFile);
                                                            }
                                                        }
                                                    } else {
                                                        writeToFileApacheCommonIO("Must fix data :: " + hotel.getHotelId() + " not specify MaxChild of room_class", childPolicyProblemFile);
                                                    }
                                                } else {
                                                    // if v.2 not specify any child_policy no need to do anything for v.3
                                                }
                                                roomClass.setOrder(0);
                                            }
                                            // TODO : some hotel not specify bedtype or maxOccu-- list in log.
                                            List<BedType> bedTypeList = new ArrayList<>();
                                            if (_roomClasses.get("room_configurations") != null) {
                                                ((List<Map<String, Object>>) _roomClasses.get("room_configurations")).forEach(_roomConfiguration -> {
                                                    bedTypeList.add(new BedType(_roomConfiguration.get("name").toString(), (boolean) _roomConfiguration.get("extra_bed")));
                                                });
                                            } else {
                                                // set default bedType first
                                                if (!hotelWhichNotSpecifyBedTypeOrMaxOccu.contains(_roomClasses.get("id").toString())) {
                                                    hotelWhichNotSpecifyBedTypeOrMaxOccu.add(_roomClasses.get("id").toString());
                                                }
                                                bedTypeList.add(new BedType("single", false));
                                            }
                                            roomClass.setBedTypes(bedTypeList);
                                        } else {
                                            // add dummy roomClass
                                            roomClass.setRoomClassName("dummy");
                                        }
                                        roomClassList.add(roomClass);
                                    }
                                    String payload = "{\"type\":\"create_room_class\",\"origin\":\"ms-load-data\",\"event_data\":{\"hotel\": {\"room_classes\":" + new Gson().toJson(roomClassList) + "}}}";
                                    JsonObject jsonCreateRoomClassesResponse = new Gson().fromJson(doHttpPostClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId() + "/room-classes" + "?token=" + loginToken, payload), JsonObject.class);
                                    System.out.println("i: " + backendHotelMetadataCounter + ", id: " + hotel.getHotelId().toString() + ", create_room_classe status: " + jsonCreateRoomClassesResponse.get("status").toString());
                                    sleep(300);
                                } else {
                                    // old data not specify room_classes
                                }

                                String newHoteljSon = new Gson().toJson(hotel);
                                // update hotel data with old data
                                String payload = "{\"type\":\"update_hotel\",\"origin\":\"ms-load-data\",\"event_data\":{\"hotel\":" + newHoteljSon + "}}";
                                jsonUpdateHotelResponse = new Gson().fromJson(doHttpPatchClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId().replaceAll("\"","") + "?token=" + loginToken, payload), JsonObject.class);
                                System.out.println("i: " + backendHotelMetadataCounter + ", id: " + jsonUpdateHotelResponse.get("id").toString() + ", status: " + jsonUpdateHotelResponse.get("status").toString());
                                sleep(300);
                            } else {
                                // TODO : add cancellation_policy SEQUENCE 3 :: Have to add HotelMetaData first, Have to add RoomClass (in backend_hotel) second
                                // in case new data hotel already have room_class_id

                                // After update hotel with roomclass information will get roomclass_id also
                                jsonGetHotelResponse = new Gson().fromJson(doHttpGetClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + _backendHotel.getFacadeId().replaceAll("\"", "") + "?token=" + loginToken), JsonObject.class);
                                if (jsonGetHotelResponse.get("status").toString().equals("\"SUCCESS\"")) {
                                    // start to copy another data from backend_hotel to another micro-services
                                    hotel = new Gson().fromJson(jsonGetHotelResponse.get("hotel"), Hotel.class);

                                    if (_backendHotel.getCancellationPolicies() != null) {
                                        // after already add roomclass --> continue add cancellation_policies for each room class on new version
                                        // check if already add cancellation_policy for this hotel_id or not?
                                        JsonObject checkCancellationAddedResponse = new Gson().fromJson(doHttpGetClient("http://" + serverHost + ":" + serverPort + "/sunseries/v1/hotels/" + hotel.getHotelId() + "/cancellation-policies?token=" + loginToken), JsonObject.class);
                                        HotelsCancellationPolicy hotelsCancellationPolicy = new Gson().fromJson(checkCancellationAddedResponse.get("result"), HotelsCancellationPolicy.class);
                                        if (hotelsCancellationPolicy.getCancellationPolicyList().size() == 0) {
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
                                }
                            }
                        } else {
                            // can't found hotel_id on new version which we have backend_hotel data from old version
                            backendHotelMetadataMissMatchCounter++;
                        }
                        sleep(300);
                    } else {
                        // failed to map json to _BackEndHotelMetadata
                        backendHotelMetadataFailedCounter++;
                    }
                    sleep(300);
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
        System.out.println("Hotel which not specify bedType: " + hotelWhichNotSpecifyBedTypeOrMaxOccu.toString());
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
        return null;
    }

    private static Integer convertObjectToInt(Object numberKeepAsString) {
        Integer returnInt = 0;
        if (numberKeepAsString.toString() != null) {
            returnInt = (int)Float.parseFloat(numberKeepAsString.toString());
        }
        return returnInt;
    }

    public static Hotel transformHotel(_HotelMetadata _hotel) {
        Hotel hotel = new Hotel();
        hotel.setHotelName(_hotel.getName());
        List<String> hotelTypeList = new ArrayList<>();
        if (_hotel.getTypes() != null) {
            _hotel.getTypes()
                    .forEach(map -> {
                        if (map != null) {
                            Map<String, String> tableMap = (Map<String, String>) map.get("table");
                            hotelTypeList.add(tableMap.get(":name").toString());
                        }
                    });
        }
        hotel.setHotelTypes(hotelTypeList);
        hotel.setDescription(_hotel.getDescription());
        hotel.setCity(_hotel.getCityId());
        // TODO : copy value of area_id from old hotel to new hotel
        // area id of old is only ID need to get more information
        //hotel.setAreas(_hotel.getAreaId());
        hotel.setStar(_hotel.getStars().toString());
        hotel.setTelephone(_hotel.getTelephone());
        hotel.setAddress(_hotel.getAddress());
        List<String> amenitiesList = new ArrayList<>();
        if (_hotel.getAmenities() != null) {
            _hotel.getAmenities()
                    .forEach(map -> {
                        if (map.get("^o").equals("Sunseries::Domain::Model::Hotel::Amenity")) {
                            hotelTypeList.add(map.get("description").toString());
                        } else {
                            Map<String, String> tableMap = (Map<String, String>) map.get("table");
                            amenitiesList.add(tableMap.get(":description").toString());
                        }
                    });
            hotel.setAmenities(amenitiesList);
        }
        if (_hotel.getRemarks() != null) {
            List<String> remarkList = new ArrayList<>();
            ((List<Map<String, Object>>) _hotel.getRemarks()).forEach(map -> {
                        remarkList.add(map.get("description").toString());
                    }
            );
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
