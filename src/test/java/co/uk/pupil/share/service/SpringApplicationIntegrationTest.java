package co.uk.pupil.share.service;

import co.uk.pupil.share.service.config.IntegrationTestConfig;
import co.uk.pupil.share.service.model.Point;
import co.uk.pupil.share.service.model.Square;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApplication.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(IntegrationTestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class SpringApplicationIntegrationTest {

    @LocalServerPort
    int port;

    private String BASE_URL;

    @Before
    public void setup() {
        BASE_URL = "http://localhost:"+port;
    }

    @Autowired
    private RestTemplate restTemplate;

    private HttpEntity<Square> createSquare(String name, Point leftBottom, Point rightTop) {
        Square square = new Square(name, leftBottom, rightTop);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Square> entity = new HttpEntity<>(square, headers);
        return entity;
    }

    @Test
    public void testCreateShapeSuccessfully() {

        HttpEntity<Square> entity = createSquare("One", new Point(4, 4), new Point(6, 6));

        ResponseEntity<Square> response = restTemplate.exchange(BASE_URL+ "/shape", HttpMethod.POST, entity, Square.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testCreateShapeFailedWhenAnOverlapExist() {

        HttpEntity<Square> entity = createSquare("Two", new Point(8, 8), new Point(10, 10));
        restTemplate.exchange(BASE_URL+ "/shape", HttpMethod.POST, entity, Square.class);
        try {
            entity = createSquare("Two", new Point(9, 9), new Point(11, 11));
            ResponseEntity<Square> response = restTemplate.exchange(BASE_URL + "/shape", HttpMethod.POST, entity, Square.class);
        } catch (HttpClientErrorException ex)   {
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
            assertEquals("Shape is overlapped with some other shape", ex.getResponseBodyAsString());
            return;
        }
        fail("Should not reach here");
    }

    @Test
    public void testReturnMultipleShapes() {

        HttpEntity<Square> entity = createSquare("Three", new Point(12, 12), new Point(14, 14));
        restTemplate.exchange(BASE_URL+ "/shape", HttpMethod.POST, entity, Square.class);
        HttpEntity<Square> entity2 = createSquare("Four", new Point(16, 16), new Point(18, 18));
        restTemplate.exchange(BASE_URL+ "/shape", HttpMethod.POST, entity2, Square.class);

        ResponseEntity<List<Square>> response = restTemplate.exchange(BASE_URL+ "/shapes", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Square>>(){});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());

        assertEquals(12, response.getBody().get(0).getBottomLeft().getxCoordinate());
        assertEquals(12, response.getBody().get(0).getBottomLeft().getyCoordinate());
        assertEquals(14, response.getBody().get(0).getTopRight().getxCoordinate());
        assertEquals(14, response.getBody().get(0).getTopRight().getyCoordinate());

        assertEquals(16, response.getBody().get(1).getBottomLeft().getxCoordinate());
        assertEquals(16, response.getBody().get(1).getBottomLeft().getyCoordinate());
        assertEquals(18, response.getBody().get(1).getTopRight().getxCoordinate());
        assertEquals(18, response.getBody().get(1).getTopRight().getyCoordinate());
    }

    @Test
    public void testCreateShapeFailedWhenShapeWithSameExistAlready() {

        HttpEntity<Square> entity = createSquare("Two", new Point(8, 8), new Point(10, 10));
        restTemplate.exchange(BASE_URL+ "/shape", HttpMethod.POST, entity, Square.class);
        try {
            entity = createSquare("Two", new Point(12, 12), new Point(14, 14));
            ResponseEntity<Square> response = restTemplate.exchange(BASE_URL + "/shape", HttpMethod.POST, entity, Square.class);
        } catch (HttpClientErrorException ex)   {
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
            assertEquals("Some other shape already has the same name", ex.getResponseBodyAsString());
            return;
        }
        fail("Should not reach here");
    }


}
