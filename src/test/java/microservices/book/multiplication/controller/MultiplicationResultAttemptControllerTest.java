package microservices.book.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.ResultResponse;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.service.MultiplicationService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class MultiplicationResultAttemptControllerTest {

	@MockBean
	private MultiplicationService multiplicationService;

	@Autowired
	private MockMvc mockMvc;

	private JacksonTester<MultiplicationResultAttempt> jsonResult;
	private JacksonTester<List<MultiplicationResultAttempt>> jsonResultAttemptList;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void postResultReturnCorrect() throws Exception {
		genericParameterizedTest(true);
	}

	@Test
	public void postResultReturnfalse() throws Exception {
		genericParameterizedTest(false);
	}

	private void genericParameterizedTest(final boolean correct) throws Exception {
		//given
		BDDMockito.given(multiplicationService.checkAttempt(ArgumentMatchers.any(MultiplicationResultAttempt.class))).willReturn(correct);

		User user = new User("john");
		Multiplication multiplication = new Multiplication(50, 70);
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500, correct);

		//when
		MockHttpServletResponse response = mockMvc.perform(
						MockMvcRequestBuilders
								.post("/results")
								.contentType(MediaType.APPLICATION_JSON)
								.content(jsonResult.write(attempt).getJson())).andReturn().getResponse();

		//then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonResult.write(attempt).getJson());
	}
}
