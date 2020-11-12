package com.test.kafka.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayersController {

	private final PlayersRepository repository;

	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	PlayersController(KafkaTemplate<String, String> kafkaTemplate, PlayersRepository repository) {
		this.kafkaTemplate = kafkaTemplate;
		this.repository = repository;

	}

	// Aggregate root

	@RequestMapping("/retrievePlayers")
	public List<Player> retrievePlayers() {

		return (List<Player>) repository.findAll();
	}

	@PostMapping("/players")
	public List<String> players(@RequestBody List<Player> players) {

		List<String> result = new ArrayList<String>();

		for (Player player : players) {

			switch (player.getType()) {
			case "expert":

				repository.save(player);
				result.add("player Sub zero stored in DB");
				 break;

			case "novice":

				kafkaTemplate.send("novice-players", player.getName());
				result.add("player Scorpion sent to Kafka topic");
				 break;
			default:
				result.add("player Reptile did not fit");
			}

		}

		return result;
	}

}
