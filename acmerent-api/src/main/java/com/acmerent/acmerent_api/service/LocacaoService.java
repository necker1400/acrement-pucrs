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
     * Lista todas as locacoes cadastradas
     * 
     * @return Lista de locacoes
     */
    public List<Locacao> listarTodas() {
        return locacaoRepository.findAll();
    }

    /**
     * Cadastra uma nova locacao
     * 
     * @param locacao Dados da locacao
     * @return Locacao cadastrada
     * @throws RuntimeException se o cliente ou automovel nao existir, ou se o automovel nao estiver disponivel
     */
    @Transactional
    public Locacao cadastrar(Locacao locacao) {
        // Verifica se o cliente existe
        Cliente cliente = clienteService.buscarPorCodigo(locacao.getCliente().getCodigo());
        locacao.setCliente(cliente);
        
        // Verifica se o automovel existe e esta disponivel
        Automovel automovel = automovelService.buscarPorId(locacao.getAutomovel().getId());
        if (automovel.getStatus() != StatusAutomovel.DISPONIVEL) {
            throw new RuntimeException("Automóvel não esta disponível para locação");
        }
        locacao.setAutomovel(automovel);
        
        // Define o status da locacao como ATIVA
        locacao.setStatus(StatusLocacao.ATIVA);
        
        // Calcula o valor da locacao
        locacao.calcularValorLocacao();
        
        // Atualiza o status do automovel para INDISPONIVEL
        automovelService.atualizarStatus(automovel.getId(), StatusAutomovel.INDISPONIVEL);
        
        // Salva a locacao
        return locacaoRepository.save(locacao);
    }

    /**
     * Finaliza uma locacao
     * 
     * @param numero Numero da locacao
     * @return Locacao finalizada
     * @throws RuntimeException se a locacao nao for encontrada ou ja estiver finalizada
     */
    @Transactional
    public Locacao finalizar(Long numero) {
        Locacao locacao = locacaoRepository.findByNumero(numero);
        if (locacao == null) {
            throw new RuntimeException("Locação não encontrada com o número: " + numero);
        }
        
        if (locacao.getStatus() == StatusLocacao.FINALIZADA) {
            throw new RuntimeException("Locação ja esta finalizada");
        }
        
        // Atualiza o status da locacao para FINALIZADA
        locacao.setStatus(StatusLocacao.FINALIZADA);
        
        // Atualiza o status do automovel para DISPONIVEL
        automovelService.atualizarStatus(locacao.getAutomovel().getId(), StatusAutomovel.DISPONIVEL);
        
        // Salva a locacao
        return locacaoRepository.save(locacao);
    }
}