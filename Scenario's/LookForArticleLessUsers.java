
import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class LookForArticleLessUsers extends Simulation {

  {
    HttpProtocolBuilder httpProtocol = http
      .baseUrl("http://localhost:1083");

    ScenarioBuilder scn1 = scenario("LookForAnArticle1")
      .exec(
        http("request_0")
          .post("/login")
          .body(RawFileBody("lookforanarticlelessusers/0000_request.json"))
      )
      .pause(9)
      .exec(
        http("request_2")
          .get("/inventory/1")
      )
      .pause(1)
      .exec(
        http("request_3")
          .get("/pricing/1")
      );

ScenarioBuilder scn2 = scenario("LookForAnArticle2")
     
      .exec(
        http("request_0")
          .post("/login")
          .body(RawFileBody("lookforanarticlelessusers/0000_request.json"))
      )
      .pause(9)
      .exec(
        http("request_2")
          .get("/inventory/2")
      )
      .pause(1)
      .exec(
        http("request_3")
          .get("/pricing/2")
      );


ScenarioBuilder scn3 = scenario("LookForAnArticle3")
      
      .exec(
        http("request_0")
          .post("/login")
          .body(RawFileBody("lookforanarticlelessusers/0000_request.json"))
      )
      .pause(9)
      .exec(
        http("request_2")
          .get("/inventory/3")
      )
      .pause(1)
      .exec(
        http("request_3")
          .get("/pricing/3")
      );


      ScenarioBuilder scn4 = scenario("LookForAnArticle4")
      
      .exec(
        http("request_0")
          .post("/login")
          .body(RawFileBody("lookforanarticlelessusers/0000_request.json"))
      )
      .pause(9)
      .exec(
        http("request_2")
          .get("/inventory/4")
      )
      .pause(1)
      .exec(
        http("request_3")
          .get("/pricing/4")
      );

      ScenarioBuilder scn5 = scenario("LookForAnArticle5")
      
      .exec(
        http("request_0")
          .post("/login")
          .body(RawFileBody("lookforanarticlelessusers/0000_request.json"))
      )
      .pause(9)
      .exec(
        http("request_2")
          .get("/inventory/5")
      )
      .pause(1)
      .exec(
        http("request_3")
          .get("/pricing/5")
      );

      ScenarioBuilder scn6 = scenario("LookForAnArticle6")
     
      .exec(
        http("request_0")
          .post("/login")
          .body(RawFileBody("lookforanarticlelessusers/0000_request.json"))
      )
      .pause(9)
      .exec(
        http("request_2")
          .get("/inventory/6")
      )
      .pause(1)
      .exec(
        http("request_3")
          .get("/pricing/6")
      );

      ScenarioBuilder scn7 = scenario("LookForAnArticle7")
      
      .exec(
        http("request_0")
          .post("/login")
          .body(RawFileBody("lookforanarticlelessusers/0000_request.json"))
      )
      .pause(9)
      .exec(
        http("request_2")
          .get("/inventory/7")
      )
      .pause(1)
      .exec(
        http("request_3")
          .get("/pricing/7")
      );

      ScenarioBuilder scn8 = scenario("LookForAnArticle8")
      
      .exec(
        http("request_0")
          .post("/login")
          .body(RawFileBody("lookforanarticlelessusers/0000_request.json"))
      )
      .pause(9)
      .exec(
        http("request_2")
          .get("/inventory/8")
      )
      .pause(1)
      .exec(
        http("request_3")
          .get("/pricing/8")
      );

      ScenarioBuilder scn9 = scenario("LookForAnArticle9")
      
      .exec(
        http("request_0")
          .post("/login")
          .body(RawFileBody("lookforanarticlelessusers/0000_request.json"))
      )
      .pause(9)
      .exec(
        http("request_2")
          .get("/inventory/9")
      )
      .pause(1)
      .exec(
        http("request_3")
          .get("/pricing/9")
      );

      ScenarioBuilder scn10 = scenario("LookForAnArticle10")
      .exec(
        http("request_0")
          .post("/login")
          .body(RawFileBody("lookforanarticlelessusers/0000_request.json"))
      )
      .pause(9)
      .exec(
        http("request_2")
          .get("/inventory/10")
      )
      .pause(1)
      .exec(
        http("request_3")
          .get("/pricing/10")
      );
      
      setUp(
        scn1.injectOpen(
        nothingFor(4),
        atOnceUsers(5),
        rampUsersPerSec(1).to(5).during(300)
        ),
        scn2.injectOpen(
        nothingFor(4),
        atOnceUsers(6),
        rampUsersPerSec(1).to(6).during(350)
        ),
        scn3.injectOpen(
        nothingFor(4),
        atOnceUsers(2),
        rampUsersPerSec(1).to(3).during(150)
        ),
        scn4.injectOpen(
        nothingFor(4),
        atOnceUsers(1),
        rampUsersPerSec(1).to(5).during(250)
        ),
        scn5.injectOpen(
        nothingFor(5),
        atOnceUsers(2),
        rampUsersPerSec(0).to(1).during(225)
        ),
        scn6.injectOpen(
        nothingFor(2),
        atOnceUsers(3),
        rampUsersPerSec(1).to(3).during(360)
        ),
        scn7.injectOpen(
        nothingFor(9),
        atOnceUsers(3),
        rampUsersPerSec(1).to(4).during(120)
        ),
        scn8.injectOpen(
        nothingFor(0),
        atOnceUsers(20),
        rampUsersPerSec(1).to(4).during(140)
        ),
        scn9.injectOpen(
        nothingFor(2),
        atOnceUsers(20),
        rampUsersPerSec(0).to(6).during(180)
        ),
        scn10.injectOpen(
        nothingFor(4),
        atOnceUsers(10),
        rampUsersPerSec(1).to(3).during(450)
        )
        ).protocols(httpProtocol);

  }
}
