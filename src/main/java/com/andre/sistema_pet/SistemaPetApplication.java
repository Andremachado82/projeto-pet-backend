package com.andre.sistema_pet;

import com.andre.sistema_pet.entity.EspecieEntity;
import com.andre.sistema_pet.entity.RacaEntity;
import com.andre.sistema_pet.repository.ClienteRepository;
import com.andre.sistema_pet.repository.EspecieRepository;
import com.andre.sistema_pet.repository.RacaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SistemaPetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaPetApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(
            EspecieRepository especieRepository,
            RacaRepository racaRepository,
            ClienteRepository clienteRepository
    ) {
        return args -> {
            // Inserir espécies
            EspecieEntity especie1 = new EspecieEntity();
            especie1.setNomeEspecie("Canina");
            especieRepository.save(especie1);

            EspecieEntity especie2 = new EspecieEntity();
            especie2.setNomeEspecie("Felina");
            especieRepository.save(especie2);

            // Inserir raças
            RacaEntity raca1 = new RacaEntity();
            raca1.setNomeRaca("Labrador");
            racaRepository.save(raca1);

            RacaEntity raca2 = new RacaEntity();
            raca2.setNomeRaca("Siamês");
            racaRepository.save(raca2);
        };
    }
}
