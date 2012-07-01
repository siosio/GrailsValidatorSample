package siosio.validator

import org.codehaus.groovy.grails.validation.AbstractConstraint
import org.springframework.validation.Errors

class NumCharValidator extends AbstractConstraint {

  public static final NAME = "number"
  private static final String DEFAULT_MESSAGE = "default.invalid.number.message"

  private boolean number = false

  @Override
  void setParameter(Object constraintParameter) {
    if (constraintParameter instanceof Boolean) {
      number = constraintParameter
    }
    super.setParameter(constraintParameter)
  }

  @Override
  protected void processValidate(Object target, Object propertyValue, Errors errors) {
    if (number) {
      if (!(propertyValue as String ==~ /[0-9]+/)) {
        super.rejectValue(target, errors, DEFAULT_MESSAGE,
                NAME, constraintPropertyName, constraintOwningClass)
      }
    }
  }

  @Override
  protected boolean skipBlankValues() {
    true
  }

  boolean supports(Class type) {
    String.class.isAssignableFrom(type)
  }

  String getName() {
    NAME
  }
}
