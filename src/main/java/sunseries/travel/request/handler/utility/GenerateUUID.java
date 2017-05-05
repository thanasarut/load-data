package sunseries.travel.request.handler.utility;

import java.util.UUID;

public class GenerateUUID {

    public static String generate() {
        return String.valueOf(UUID.randomUUID());
    }

}
