package sandbox.springbootswagger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Component
@Path("/hello")
@Api("hello")
@Produces("text/plain")
public class HelloEndPoint {
	@GET
	@ApiOperation(value = "Hello World from JAXRS", response = String.class)
	public String get() {
		return "Hello";
	}
}