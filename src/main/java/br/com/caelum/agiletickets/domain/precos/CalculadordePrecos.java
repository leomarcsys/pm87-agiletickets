package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public interface CalculadordePrecos {
	
	BigDecimal calcularPreco(Sessao sessao);

}
