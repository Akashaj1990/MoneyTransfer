package revolut.bank.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import revolut.bank.entity.request.TransferRequest;
import revolut.bank.enums.ValidationStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TransferValidator extends GenericRequestValidator<ValidationStatus, TransferRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferValidator.class);

    private TransferValidator() {

    }

    private List<GenericRequestValidator<ValidationStatus, TransferRequest>> transferValidatorList = Arrays.asList(
            new RequestParamValidator());

    @Override
    public Optional<ValidationStatus> validate(TransferRequest request) {
        Optional<ValidationStatus> errorResponse = transferValidatorList.stream().map(validator -> validator.validate(request))
                .filter(Optional::isPresent).findFirst().orElse(Optional.empty());
        LOGGER.info("Edc Payment Request Validator => is valid {} ", !errorResponse.isPresent());
        return errorResponse;
    }

    /**
     * Request Param Validator
     */
    private static class RequestParamValidator extends GenericRequestValidator<ValidationStatus, TransferRequest> {

        @Override
        public Optional<ValidationStatus> validate(TransferRequest request) {
            ValidationStatus errorResponse = null;
            GenericBeanValidator<TransferRequest> bean = new GenericBeanValidator<>(request);
            if (!bean.validate()) {
                errorResponse = ValidationStatus.INVALID_REQUEST;
            }
            LOGGER.info("RequestParamValidator => is valid {} ", null == errorResponse);
            return Optional.ofNullable(errorResponse);
        }
    }

}
