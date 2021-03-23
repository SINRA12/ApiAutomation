package com.test.validation;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import org.apache.commons.collections4.CollectionUtils;

public class JsonConstraintValidator<T> {
public JsonConstraintValidator() {
}

private static Validator getValidator() {
return Validation.buildDefaultValidatorFactory().getValidator();
}

public static <T> void validateForFields(T t) {
Set<ConstraintViolation<T>> constraintViolations = getValidator().validate(t, new Class[0]);
if (CollectionUtils.isNotEmpty(constraintViolations)) {
constraintViolations.forEach((constraintViolation) -> {
throw new ValidationException(constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage() + " in Leaf bean >>>>>" + constraintViolation.getLeafBean().toString());
});
}

}
}