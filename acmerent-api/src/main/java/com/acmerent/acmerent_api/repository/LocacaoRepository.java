package com.acmerent.acmerent_api.repository;

import com.acmerent.acmerent_api.model.Automovel;
import com.acmerent.acmerent_api.model.Cliente;
import com.acmerent.acmerent_api.model.Locacao;
import com.acmerent.acmerent_api.model.StatusLocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
    
    /**
     * Encontra locacoes pelo status
     * 
     * @param status Status da locacao
     * @return Lista de locacoes com o status especificado
     */
    List<Locacao> findByStatus(StatusLocacao status);
    
    /**
     * Encontra uma locacao pelo numero
     * 
     * @param numero Numero da locacao
     * @return Locacao encontrada ou null se nao encontrada
     */
    Locacao findByNumero(Long numero);
    
    /**
     * Encontra locacoes por automovel
     * 
     * @param automovel Automovel
     * @return Lista de locacoes do automovel especificado
     */
    List<Locacao> findByAutomovel(Automovel automovel);
    
    /**
     * Encontra locacoes por cliente
     * 
     * @param cliente Cliente
     * @return Lista de locacoes do cliente especificado
     */
    List<Locacao> findByCliente(Cliente cliente);
}