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
     * Encontra locau00e7u00f5es pelo status
     * 
     * @param status Status da locau00e7u00e3o
     * @return Lista de locau00e7u00f5es com o status especificado
     */
    List<Locacao> findByStatus(StatusLocacao status);
    
    /**
     * Encontra uma locau00e7u00e3o pelo nu00famero
     * 
     * @param numero Nu00famero da locau00e7u00e3o
     * @return Locau00e7u00e3o encontrada ou null se nu00e3o encontrada
     */
    Locacao findByNumero(Long numero);
    
    /**
     * Encontra locau00e7u00f5es por automu00f3vel
     * 
     * @param automovel Automu00f3vel
     * @return Lista de locau00e7u00f5es do automu00f3vel especificado
     */
    List<Locacao> findByAutomovel(Automovel automovel);
    
    /**
     * Encontra locau00e7u00f5es por cliente
     * 
     * @param cliente Cliente
     * @return Lista de locau00e7u00f5es do cliente especificado
     */
    List<Locacao> findByCliente(Cliente cliente);
}