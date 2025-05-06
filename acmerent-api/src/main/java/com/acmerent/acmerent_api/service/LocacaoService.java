package com.acmerent.acmerent_api.service;

import com.acmerent.acmerent_api.model.Automovel;
import com.acmerent.acmerent_api.model.Cliente;
import com.acmerent.acmerent_api.model.Locacao;
import com.acmerent.acmerent_api.model.StatusAutomovel;
import com.acmerent.acmerent_api.model.StatusLocacao;
import com.acmerent.acmerent_api.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocacaoService {

    private final LocacaoRepository locacaoRepository;
    private final ClienteService clienteService;
    private final AutomovelService automovelService;

    @Autowired
    public LocacaoService(LocacaoRepository locacaoRepository, ClienteService clienteService, AutomovelService automovelService) {
        this.locacaoRepository = locacaoRepository;
        this.clienteService = clienteService;
        this.automovelService = automovelService;
    }

    /**
     * Lista todas as locau00e7u00f5es cadastradas
     * 
     * @return Lista de locau00e7u00f5es
     */
    public List<Locacao> listarTodas() {
        return locacaoRepository.findAll();
    }

    /**
     * Cadastra uma nova locau00e7u00e3o
     * 
     * @param locacao Dados da locau00e7u00e3o
     * @return Locau00e7u00e3o cadastrada
     * @throws RuntimeException se o cliente ou automu00f3vel nu00e3o existir, ou se o automu00f3vel nu00e3o estiver disponu00edvel
     */
    @Transactional
    public Locacao cadastrar(Locacao locacao) {
        // Verifica se o cliente existe
        Cliente cliente = clienteService.buscarPorCodigo(locacao.getCliente().getCodigo());
        locacao.setCliente(cliente);
        
        // Verifica se o automu00f3vel existe e estu00e1 disponu00edvel
        Automovel automovel = automovelService.buscarPorId(locacao.getAutomovel().getId());
        if (automovel.getStatus() != StatusAutomovel.DISPONIVEL) {
            throw new RuntimeException("Automu00f3vel nu00e3o estu00e1 disponu00edvel para locau00e7u00e3o");
        }
        locacao.setAutomovel(automovel);
        
        // Define o status da locau00e7u00e3o como ATIVA
        locacao.setStatus(StatusLocacao.ATIVA);
        
        // Calcula o valor da locau00e7u00e3o
        locacao.calcularValorLocacao();
        
        // Atualiza o status do automu00f3vel para INDISPONIVEL
        automovelService.atualizarStatus(automovel.getId(), StatusAutomovel.INDISPONIVEL);
        
        // Salva a locau00e7u00e3o
        return locacaoRepository.save(locacao);
    }

    /**
     * Finaliza uma locau00e7u00e3o
     * 
     * @param numero Nu00famero da locau00e7u00e3o
     * @return Locau00e7u00e3o finalizada
     * @throws RuntimeException se a locau00e7u00e3o nu00e3o for encontrada ou ju00e1 estiver finalizada
     */
    @Transactional
    public Locacao finalizar(Long numero) {
        Locacao locacao = locacaoRepository.findByNumero(numero);
        if (locacao == null) {
            throw new RuntimeException("Locau00e7u00e3o nu00e3o encontrada com o nu00famero: " + numero);
        }
        
        if (locacao.getStatus() == StatusLocacao.FINALIZADA) {
            throw new RuntimeException("Locau00e7u00e3o ju00e1 estu00e1 finalizada");
        }
        
        // Atualiza o status da locau00e7u00e3o para FINALIZADA
        locacao.setStatus(StatusLocacao.FINALIZADA);
        
        // Atualiza o status do automu00f3vel para DISPONIVEL
        automovelService.atualizarStatus(locacao.getAutomovel().getId(), StatusAutomovel.DISPONIVEL);
        
        // Salva a locau00e7u00e3o
        return locacaoRepository.save(locacao);
    }
}