package pl.edu.wat.ai.app.interfaces.rest.user.dto


import pl.edu.wat.ai.app.finances.user.User
import spock.lang.Specification
import spock.lang.Subject

class UserDtoMapperSpec extends Specification {

	@Subject
	def userDtoMapper = new UserDtoMapper()

	def 'mapUserToDto should return user mapped as dto with proper values'() {
		given:
			def user = User.builder()
					.username('user-username')
					.firstName('user-firstname')
					.lastName('user-lastname')
					.mail('user-mail')
					.userMonthlyLimit('user-monthly-limit')
					.build()
		when:
			def userDto = userDtoMapper.mapUserToDto(user)
		then:
			with(userDto) {
				it.username == user.username
				it.firstName == user.firstName
				it.lastName == user.lastName
				it.mail == user.mail
				it.limit == user.userMonthlyLimit
			}
	}
}
