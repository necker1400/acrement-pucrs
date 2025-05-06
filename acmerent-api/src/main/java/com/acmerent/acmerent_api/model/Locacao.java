package com.acmerent.acmerent_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    
    private LocalDate dataInicial;
    private Integer quantidadeDias;
    
    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Automovel automovel;
    
    private Double valorLocacao;
    
    @Enumerated(EnumType.STRING)
    private StatusLocacao status;
    
    @PrePersist
    @PreUpdate
    public void calcularValorLocacao() {
        if (automovel != null && quantidadeDias != null) {
            double valorBase = automovel.getValorDiaria() * quantidadeDias;
            
            // Aplica desconto de 5% para locau00e7u00f5es com mais de 7 dias
            if (quantidadeDias > 7) {
                valorLocacao = valorBase * 0.95;
            } else {
                valorLocacao = valorBase;
            }
        }
    }
}