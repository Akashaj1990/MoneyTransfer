package revolut.bank.controllers;

import com.google.gson.Gson;
import revolut.bank.constants.RevolutConstant;
import revolut.bank.entity.request.TransferRequest;
import revolut.bank.entity.request.TransferRequestBody;
import revolut.bank.entity.response.TransferResponse;
import revolut.bank.service.AccountService;
import revolut.bank.service.AccountServiceImpl;
import revolut.bank.service.BankingService;
import revolut.bank.service.BankingServiceImpl;

import static spark.Spark.get;
import static spark.Spark.post;

public class TransferController {

    public static void init() {
        BankingService bankingService = BankingServiceImpl.getInstance();
        AccountService accountService = AccountServiceImpl.getInstance();

        post(RevolutConstant.TRANSFER_ENDPOINT_URL, (request, response) -> {
            TransferRequest transferRequest = new Gson().fromJson(request.body(), TransferRequest.class);
            TransferRequestBody requestBody = transferRequest.getBody();
            TransferResponse transferResponse = bankingService.processTransfer(requestBody.getFromAccountNumber(), requestBody.getToAccountNumber(), requestBody.getTransferAmount());
            response.type("application/json");
            return new Gson().toJson(transferResponse);
        });

        get(RevolutConstant.TRANSFER_ENQUIRE_ENDPOINT_URL, (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(bankingService.getTransfer(12L));
        });

    }

}
