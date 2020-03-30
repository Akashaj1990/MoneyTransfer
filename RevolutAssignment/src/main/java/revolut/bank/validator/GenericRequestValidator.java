package revolut.bank.validator;

import java.util.Optional;

public abstract class GenericRequestValidator<RS, RQ> {

    public abstract Optional<RS> validate(RQ request);

}