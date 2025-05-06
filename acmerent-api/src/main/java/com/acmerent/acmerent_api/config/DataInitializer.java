package com.acmerent.acmerent_api.config;

import com.acmerent.acmerent_api.model.Automovel;
import com.acmerent.acmerent_api.model.Cliente;
import com.acmerent.acmerent_api.model.Locacao;
import com.acmerent.acmerent_api.model.StatusAutomovel;
import com.acmerent.acmerent_api.model.StatusLocacao;
import com.acmerent.acmerent_api.repository.AutomovelRepository;
import com.acmerent.acmerent_api.repository.ClienteRepository;
import com.acmerent.acmerent_api.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final AutomovelRepository automovelRepository;
    private final LocacaoRepository locacaoRepository;

    @Autowired
    public DataInitializer(ClienteRepository clienteRepository, AutomovelRepository automovelRepository, LocacaoRepository locacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.automovelRepository = automovelRepository;
        this.locacaoRepository = locacaoRepository;
    }

    @Override
    public void run(String... args) {
        // Verifica se ju00e1 existem dados no banco
        if (clienteRepository.count() > 0 || automovelRepository.count() > 0 || locacaoRepository.count() > 0) {
            System.out.println("Banco de dados ju00e1 possui dados. Pulando inicializau00e7u00e3o.");
            return;
        }

        System.out.println("Inicializando dados de teste...");

        // Cria clientes
        Cliente cliente1 = new Cliente(null, "123.456.789-00", "Jou00e3o Silva", "(51) 98765-4321");
        Cliente cliente2 = new Cliente(null, "987.654.321-00", "Maria Oliveira", "(51) 91234-5678");
        Cliente cliente3 = new Cliente(null, "456.789.123-00", "Pedro Santos", "(51) 95555-9999");

        List<Cliente> clientes = clienteRepository.saveAll(List.of(cliente1, cliente2, cliente3));
        System.out.println("Clientes criados: " + clientes.size());

        // Cria automu00f3veis
        Automovel auto1 = new Automovel(null, "ABC1234", "Fiat Uno", 2021, 100.0, StatusAutomovel.DISPONIVEL);
        Automovel auto2 = new Automovel(null, "DEF5678", "Volkswagen Gol", 2022, 120.0, StatusAutomovel.DISPONIVEL);
        Automovel auto3 = new Automovel(null, "GHI9012", "Chevrolet Onix", 2023, 150.0, StatusAutomovel.DISPONIVEL);
        Automovel auto4 = new Automovel(null, "JKL3456", "Hyundai HB20", 2022, 130.0, StatusAutomovel.DISPONIVEL);
        Automovel auto5 = new Automovel(null, "MNO7890", "Toyota Corolla", 2023, 200.0, StatusAutomovel.DISPONIVEL);
        Automovel auto6 = new Automovel(null, "PQR1234", "Honda Civic", 2022, 180.0, StatusAutomovel.DISPONIVEL);
        Automovel auto7 = new Automovel(null, "STU5678", "Ford Ka", 2021, 110.0, StatusAutomovel.DISPONIVEL);
        Automovel auto8 = new Automovel(null, "VWX9012", "Renault Kwid", 2022, 90.0, StatusAutomovel.DISPONIVEL);
        Automovel auto9 = new Automovel(null, "YZA3456", "Nissan Versa", 2023, 160.0, StatusAutomovel.DISPONIVEL);
        Automovel auto10 = new Automovel(null, "BCD7890", "Jeep Renegade", 2022, 220.0, StatusAutomovel.DISPONIVEL);

        List<Automovel> automoveis = automovelRepository.saveAll(List.of(
                auto1, auto2, auto3, auto4, auto5, auto6, auto7, auto8, auto9, auto10
        ));
        System.out.println("Automu00f3veis criados: " + automoveis.size());

        // Cria locau00e7u00f5es
        Locacao locacao1 = new Locacao();
        locacao1.setDataInicial(LocalDate.now());
        locacao1.setQuantidadeDias(5);
        locacao1.setCliente(clientes.get(0));
        locacao1.setAutomovel(automoveis.get(0));
        locacao1.setStatus(StatusLocacao.ATIVA);
        locacao1.calcularValorLocacao();

        Locacao locacao2 = new Locacao();
        locacao2.setDataInicial(LocalDate.now().minusDays(10));
        locacao2.setQuantidadeDias(8);
        locacao2.setCliente(clientes.get(1));
        locacao2.setAutomovel(automoveis.get(1));
        locacao2.setStatus(StatusLocacao.FINALIZADA);
        locacao2.calcularValorLocacao();

        List<Locacao> locacoes = locacaoRepository.saveAll(List.of(locacao1, locacao2));
        System.out.println("Locau00e7u00f5es criadas: " + locacoes.size());

        // Atualiza o status dos automu00f3veis em locau00e7u00e3o ativa
        for (Locacao locacao : locacoes) {
            if (locacao.getStatus() == StatusLocacao.ATIVA) {
                Automovel automovel = locacao.getAutomovel();
                automovel.setStatus(StatusAutomovel.INDISPONIVEL);
                automovelRepository.save(automovel);
                System.out.println("Automu00f3vel " + automovel.getPlaca() + " marcado como INDISPONIVEL");
            }
        }

        System.out.println("Inicializau00e7u00e3o de dados concluu00edda com sucesso!");
    }
}