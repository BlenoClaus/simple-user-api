package br.com.registration.user;

import br.com.registration.core.model.Address;
import br.com.registration.core.model.Client;
import br.com.registration.core.repository.AddressRepository;
import br.com.registration.core.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"br.com.registration.core.model"})
@EnableJpaRepositories({"br.com.registration.core.repository"})
public class UserApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void run(String... args) throws Exception {
		Client client1 = new Client().builder()
				.id(null)
				.name("José Silva")
				.cpf("655.558.859-94")
				.build();

		clientRepository.save(client1);

		Address addrs1 = new Address().builder()
				.id(null)
				.street("Rua Luiz Dionisio")
				.number("222")
				.cep("13255-956")
				.neighborhood("Barão Geraldo")
				.build();

		Address addrs2 = new Address().builder()
				.id(null)
				.street("Rua Luiz Fernando")
				.number("333")
				.cep("13255-957")
				.neighborhood("Barão Geraldo")
				.build();


		addrs1.setClient(client1);
		addrs2.setClient(client1);

		addressRepository.saveAll(Arrays.asList(addrs1, addrs2));
		client1.setAllAddress(Arrays.asList(addrs1, addrs2));
	}

}
