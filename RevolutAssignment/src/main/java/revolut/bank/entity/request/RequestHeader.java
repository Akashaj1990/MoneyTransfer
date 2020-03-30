package revolut.bank.entity.request;

import java.io.Serializable;

public class RequestHeader implements Serializable {
    private String clientId;
    private String authToken;
    private String dateTime;
    private String checksum;
}
