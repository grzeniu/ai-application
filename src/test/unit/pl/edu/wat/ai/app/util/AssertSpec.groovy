package pl.edu.wat.ai.app.util

import pl.edu.wat.ai.app.currency.Currency
import spock.lang.Specification

class AssertSpec extends Specification {

	def 'notNull should not throw exception if given parameter is not null'() {
		when:
			Assert.notNull(Currency.builder().build(),_ as String)
		then:
			noExceptionThrown()
	}

	def 'notNull should throw exception if given parameter is null'() {
		given:
			def exceptionMessage = 'exception-message'
		when:
			Assert.notNull(null,exceptionMessage)
		then:
			def exception = thrown(IllegalArgumentException)
			exception.message == exceptionMessage
	}
}
