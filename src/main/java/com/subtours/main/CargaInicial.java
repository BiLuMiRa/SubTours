package com.subtours.main;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import com.subtours.embeddable.*;
import com.subtours.enums.*;
import com.subtours.model.*;

import jakarta.persistence.EntityManager;
import jakarta.transaction.UserTransaction;

public final class CargaInicial {
    private CargaInicial(){}

    public static void carregar(UserTransaction tx, EntityManager em){

        try{
            tx.begin();

            // 1. Pessoa
            Pessoa pessoa = Pessoa.builder()
                .cpf("12345678901")
                .nome("Ana Oliveira")
                .datanasc(LocalDate.of(1995, 5, 10))
                .email("ana@email.com")
                .telefone("83999999999")
                .ativo(true)
                .endereco(Endereco.builder()
                    .logradouro("Rua das Flores")
                    .numero(100)
                    .complemento("Apto 2")
                    .bairro("Centro")
                    .cidade("João Pessoa")
                    .unidadeFed("PB")
                    .cep("58000000")
                    .build())
                .participacoes(new ArrayList<>())
                .retiradas(new ArrayList<>())
                .build();

            // 2. Pesquisador
            Pesquisador pesquisador = Pesquisador.builder()
                .cpf("23456789012")
                .nome("Carlos Santos")
                .datanasc(LocalDate.of(1990, 3, 15))
                .email("carlos@email.com")
                .telefone("83988888888")
                .ativo(true)
                .endereco(Endereco.builder()
                    .logradouro("Rua do Sol")
                    .numero(50)
                    .bairro("Tambaú")
                    .cidade("João Pessoa")
                    .unidadeFed("PB")
                    .cep("58039000")
                    .build())
                .registroInst((short) 1234)
                .areaPesquisa("Biologia")
                .titulacao("Mestrado")
                .valor_diario_bolsa(BigInteger.valueOf(250))
                .coletas(new ArrayList<>())
                .build();

            // 3. Guia de espeleologia
            GuiaEspeleologia guia = GuiaEspeleologia.builder()
                .cpf("34567890123")
                .nome("Mariana Costa")
                .datanasc(LocalDate.of(1988, 8, 20))
                .email("mariana@email.com")
                .telefone("83977777777")
                .ativo(true)
                .endereco(Endereco.builder()
                    .logradouro("Av. Principal")
                    .numero(200)
                    .bairro("Bessa")
                    .cidade("João Pessoa")
                    .unidadeFed("PB")
                    .cep("58035000")
                    .build())
                .num_credenc(456)
                .nivel_certif(3)
                .validade_certif(LocalDate.of(2028, 12, 31))
                .qntd_expedicoes(10)
                .build();

            // 4. Caverna
            Caverna caverna = Caverna.builder()
                .nomeCaverna("Caverna da Esperança")
                .codCadAmbiental("PB0001")
                .municipio("João Pessoa")
                .uf("PB")
                .ultimaInsp(LocalDate.now())
                .indAcesso(true)
                .extensao(new BigDecimal("350.50"))
                .localizacao(Localizacao.builder()
                    .latitude(new BigDecimal("-7.1195"))
                    .longitude(new BigDecimal("-34.8450"))
                    .datum(datumGeodesicoEnum.SIRGAS2000)
                    .build())
                .setores(new ArrayList<>())
                .build();

            // 5. Setor de pesquisa
            SetorPesquisa setor = SetorPesquisa.builder()
                .denominacao("Galeria Principal")
                .dificuldade(nivelDificuldadeEnum.moderado)
                .profuncidadeMaxima(new BigDecimal("15.5"))
                .extensao(new BigDecimal("120.0"))
                .descricao("Setor de acesso para estudos.")
                .riscoInundacao(nivelDificuldadeEnum.baixo)
                .condicao(condicaoAmostraEnum.boa)
                .caverna(caverna)
                .build();

            caverna.getSetores().add(setor);

            // 6. Equipamento
            Equipamento equipamento = Equipamento.builder()
                .codPatrimonial("EQ0001")
                .nome("Lanterna")
                .tipo(tipoEquipamentoEnum.iluminacao)
                .fabricante("Marca Exemplo")
                .valor(new BigDecimal("250.00"))
                .dataCompra(LocalDate.of(2025, 1, 10))
                .situacaoOperacional(
                    situacaoOperacionalEquipamentoEnum.disponivel)
                .exigeCalibracao(false)
                .utilizacoes(new ArrayList<>())
                .build();

            // 7. Plano de segurança
            PlanoSeguranca plano = PlanoSeguranca.builder()
                .procedsEvacuacao(
                    "Seguir a rota indicada pelo guia até a saída.")
                .pontoEncontro("Entrada da caverna")
                .tempoSemComunic(LocalTime.of(0, 30))
                .telefoneEmerg("83999999999")
                .precisaMedico(false)
                .mapa(null)
                .build();

            // 8. Autorização ambiental
            AutorizacaoAmbiental autorizacao =
                AutorizacaoAmbiental.builder()
                    .orgaoEmissor("SUDEMA")
                    .dataEmissao(LocalDate.now())
                    .validade(LocalDate.now().plusYears(1))
                    .situacao(situacaoAutorizacaoAmbientalEnum.ativo)
                    .observacoes("Autorização para pesquisa.")
                    .pdfAssinado(new byte[]{1})
                    .build();

            // 9. Relatório
            Relatorio relatorio = Relatorio.builder()
                .titulo("Relatório da expedição")
                .Resumo("Registro inicial das atividades.")
                .dataSubmissao(LocalDate.now())
                .numeroPaginas(5)
                .arqCompleto(null)
                .publicacaoAprovada(false)
                .build();

            // 10. Expedição
            Expedicao expedicao = Expedicao.builder()
                .titulo("Expedição de reconhecimento")
                .objetivo("Reconhecer e documentar a caverna.")
                .inicio(LocalDateTime.now())
                .termino(LocalDateTime.now().plusHours(4))
                .orcamento(new BigDecimal("1500.00"))
                .custo(new BigDecimal("500.00"))
                .qntdParticip(3)
                .situacao(situacaoExpedicaoEnum.planejada)
                .cancelEmerg(false)
                .caverna(caverna)
                .planoSeguranca(plano)
                .autorizAmbiental(autorizacao)
                .relatorio(relatorio)
                .participacoes(new ArrayList<>())
                .coletasCientificas(new ArrayList<>())
                .utilizacoes(new ArrayList<>())
                .build();

            // 11. Participação
            Participacao participacao = Participacao.builder()
                .papelExpedicao(papelExpedicaoEnum.Coordenador)
                .dataConfirmacao(LocalDate.now())
                .valorDiaria(new BigDecimal("200.00"))
                .quantidadeDias((short) 1)
                .presenca(false)
                .observacoes("Participação confirmada.")
                .pessoa(pessoa)
                .expedicao(expedicao)
                .build();

            expedicao.getParticipacoes().add(participacao);
            pessoa.getParticipacoes().add(participacao);

            // 12. Coleta científica
            ColetaCientifica coleta = ColetaCientifica.builder()
                .setor(setor)
                .pesquisador(pesquisador)
                .dataHora(LocalDateTime.now())
                .metodoEmpregado("Coleta manual")
                .descricaoPonto("Ponto próximo à entrada.")
                .temperatura(new BigDecimal("24.5"))
                .umidadeRelativa(new BigDecimal("80.0"))
                .profundidade(new BigDecimal("2.0"))
                .observacoes("Coleta para análise.")
                .situacaoValidacao(
                    situacaoValidacaoColetaEnum.em_aberto)
                .expedicao(expedicao)
                .amostras(new ArrayList<>())
                .build();

            expedicao.addColeta(coleta);
            pesquisador.getColetas().add(coleta);

            // 13. Amostra científica
            AmostraCientifica amostra = AmostraCientifica.builder()
                .codAmostra("AM0001")
                .categoria(categoriaAmostraEnum.biologica)
                .massa(10.0)
                .volume(5.0)
                .unidadeMedida(unidadeMedidaAmostraEnum.G)
                .dataAcondicionamento(LocalDate.now())
                .condicaoConservacao(condicaoAmostraEnum.boa)
                .materialPerigoso(null)
                .fotografiaBinaria(null)
                .obsorvacoes("Amostra inicial.")
                .coleta(coleta)
                .build();

            coleta.getAmostras().add(amostra);

            // 14. Utilização de equipamento
            UtilizacaoEquipamento utilizacao =
                UtilizacaoEquipamento.builder()
                    .responsavel(pessoa)
                    .equipamento(equipamento)
                    .expedicao(expedicao)
                    .dataHoraRetirada(LocalDateTime.now())
                    .previsaoDevolucao(LocalDate.now().plusDays(1))
                    .dataDevolucao(null)
                    .estadoSaida(estadoEquipamentoEnum.bom)
                    .estadoRetorno(null)
                    .custoAvaria(null)
                    .build();

            em.persist(pessoa);
            em.persist(pesquisador);
            em.persist(guia);
            em.persist(caverna);
            em.persist(equipamento);

            // Persistir a expedição e suas dependências em cascade
            em.persist(expedicao);

            // associar a utilização aos relacionamentos
            expedicao.addUtilizacao(utilizacao);
            equipamento.addEquipamento(utilizacao);
            pessoa.addRetirada(utilizacao);

            // Persistir a utilização explicitamente
            em.persist(utilizacao);

            tx.commit();
        }catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception rollbackError) {
                rollbackError.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}
