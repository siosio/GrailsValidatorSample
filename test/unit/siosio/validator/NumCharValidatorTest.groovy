package siosio.validator

import grails.validation.ValidationErrors
import org.junit.Test
import org.junit.Before

class NumCharValidatorTest {

  NumCharValidator validator

  @Before
  void setUp() {
    validator = new NumCharValidator()
    validator.setOwningClass(Domain1)
    validator.setPropertyName("field")
    validator.setParameter(true)
  }

  @Test
  void 空文字列の場合() {
    def domain = new Domain1(field: '')
    def errors = new ValidationErrors(domain)
    validator.validate(domain, domain.field, errors)
    assert errors.errorCount == 0
  }

  @Test
  void 数字1桁() {
    def domain = new Domain1(field: '0')
    def errors = new ValidationErrors(domain)
    validator.validate(domain, domain.field, errors)
    assert errors.errorCount == 0
  }

  @Test
  void 数字n桁() {
    def domain = new Domain1(field: '0123456789')
    def errors = new ValidationErrors(domain)
    validator.validate(domain, domain.field, errors)
    assert errors.errorCount == 0
  }

  @Test
  void 英字() {
    def domain = new Domain1(field: 'abc')
    def errors = new ValidationErrors(domain)
    validator.validate(domain, domain.field, errors)
    assert errors.errorCount == 1
  }

  @Test
  void 記号() {
    def domain = new Domain1(field: 'abcdefg')
    def errors = new ValidationErrors(domain)
    validator.validate(domain, domain.field, errors)
    assert errors.errorCount == 1
  }
}

class Domain1 {
  String field
}
