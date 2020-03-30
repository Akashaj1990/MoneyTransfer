package revolut.bank.entity.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TransferRequest implements Serializable {

    @
    private RequestHeader head;
    private TransferRequestBody body;
}
