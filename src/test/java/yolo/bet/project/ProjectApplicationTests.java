package yolo.bet.project;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ProjectApplicationTests {

	private static final String BASE_URL = "http://localhost:8083/api/bet";

	private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void testRTP() throws InterruptedException, ExecutionException {
		int numRounds = 1000000;
		int numThreads = 24;

		ExecutorService eService = Executors.newFixedThreadPool(numThreads);
		CompletionService<Double> completionService = new ExecutorCompletionService<>(eService);

		for (int i = 0; i < numRounds; i++) {
			completionService.submit(this::playRound);
		}

		double totalWinAmount = 0.0;
		for (int i = 0; i < numRounds; i++) {
			double winAmount = completionService.take().get();
			totalWinAmount += winAmount;
		}

		double totalBetAmount = numRounds;
		double rtp = (totalWinAmount / totalBetAmount) * 100.0;

		// assertEquals(rtp, rtp);
		System.out.println("RTP: " + rtp + "%");
		eService.shutdown();
	}

	private Double playRound() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Actual payload
		String payload = "{\"wholenumber\": 50, \"bet\": 45.5}";

		HttpEntity<String> request = new HttpEntity<>(payload, headers);

		ResponseEntity<Double> response = restTemplate.postForEntity(BASE_URL, request, Double.class);

		return response.getBody();
	}
}
