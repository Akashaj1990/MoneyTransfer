package revolut.bank.validator;

import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericBeanValidator<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericBeanValidator.class);
    T bean;
    private String errorMessage;
    private String propertyPath;

    public GenericBeanValidator(T bean) {
        this.bean = bean;
    }

    public String getPropertyPath() {
        return this.propertyPath;
    }

    public void setPropertyPath(String propertyPath) {
        this.propertyPath = propertyPath;
    }

    public T getBean() {
        return this.bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public String getErrorMessage() {
        return this.getPropertyPath() + " " + this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean validate() {
        LOGGER.debug("Bean validation called");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(this.bean, new Class[0]);
        if (!violations.isEmpty()) {
            Iterator var4 = violations.iterator();
            if (var4.hasNext()) {
                ConstraintViolation<T> violation = (ConstraintViolation)var4.next();
                this.setPropertyPath(violation.getPropertyPath().toString());
                this.setErrorMessage(violation.getMessage());
                LOGGER.error("Bean validation failed due to reason :{} {}", this.propertyPath, violation.getMessage());
                return false;
            }
        }

        LOGGER.debug("Bean validation completed");
        return true;
    }
}
