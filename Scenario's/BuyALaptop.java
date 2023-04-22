
import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class BuyALaptop extends Simulation {

  {
    HttpProtocolBuilder httpProtocol = http
      .baseUrl("http://localhost:1083");

    ScenarioBuilder scn = scenario("BuyALaptop")
      .exec(
        http("request_0")
          .post("/login")
          .body(RawFileBody("buyalaptop/0000_request.json"))
      )
      .pause(8)
      .exec(
        http("request_2")
          .get("/inventory/5")
      )
      .pause(7)
      .exec(
        http("request_3")
          .get("/pricing/5")
      )
      .pause(10)
      .pause(17)
      .exec(
        http("request_4")
          .post("/order")
          .body(RawFileBody("buyalaptop/0025_request.json"))
      );

	  setUp(scn.injectOpen(atOnceUsers(100),constantUsersPerSec(20).during(15))).protocols(httpProtocol);
  }
}
