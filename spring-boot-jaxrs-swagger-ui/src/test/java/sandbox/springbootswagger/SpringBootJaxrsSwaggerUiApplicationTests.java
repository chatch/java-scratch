package sandbox.springbootswagger;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootJaxrsSwaggerUiApplication.class)
@WebIntegrationTest(randomPort = true)
public class SpringBootJaxrsSwaggerUiApplicationTests {

	@Value("${local.server.port}")
	int port;

	@Before
	public void setUp() {
		RestAssured.port = port;
	}

	@Test
	public void helloPath() {
		expect().
			body(equalTo("Hello")).
		when().
			get("/api/hello");
	}

	@Test
	public void swaggerApi() {
		when().
			get("/api/swagger.json").
		then().
			body("swagger", is("2.0")).
			body(containsString("/hello"));
	}

	@Test
	public void swaggerUi() {
		when().
			get("/webjars/swagger-ui/2.1.4/index.html?url=http://localhost:" + port).
		then().
			body(containsString("body class=\"swagger-section\""));
	}

}
