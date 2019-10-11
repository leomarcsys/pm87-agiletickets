package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Month;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void deveCriarDezSessoesEmPeriodicidadeDiaria() {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate dataInicio = LocalDate.parse("2019-10-10");
		LocalDate dataFim = LocalDate.parse("2019-10-19");
		LocalTime horario = LocalTime.parse("20:30");
		
		DateTime dataHoraIn = dataInicio.toDateTime(horario);
		DateTime dataHoraFim = dataFim.toDateTime(horario);
		
		List<Sessao> lista = espetaculo.criaSessoes(dataInicio, dataFim, horario, Periodicidade.DIARIA);
		
		assertEquals(10,lista.size());
		
		assertEquals(lista.get(0).getInicio(),dataHoraIn);
		assertEquals(lista.get(lista.size()-1).getInicio(),dataHoraFim);
	}
	
	@Test
	public void deveCriar2SessoesEmPeriodicidadeSemanal() {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate dataInicio = LocalDate.parse("2019-10-10");
		LocalDate dataFim = LocalDate.parse("2019-10-19");
		LocalDate dataSegundaSessao = LocalDate.parse("2019-10-17");
		LocalTime horario = LocalTime.parse("20:30");
		
		DateTime dataHoraIn = dataInicio.toDateTime(horario);
		DateTime dataHoraSegundaSessao = dataSegundaSessao.toDateTime(horario);
		
		List<Sessao> lista = espetaculo.criaSessoes(dataInicio, dataFim, horario, Periodicidade.SEMANAL);
		
		assertEquals(5,lista.size());
		
		assertEquals(lista.get(0).getInicio(),dataHoraIn);
		assertEquals(lista.get(lista.size()-1).getInicio(),dataHoraSegundaSessao);
	}	
	
}
