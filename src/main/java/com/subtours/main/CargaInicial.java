package com.subtours.main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import com.subtours.controller.GenericController;
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

            GenericController<Pessoa> pessoaController = new GenericController<>(Pessoa.class);
            GenericController<Pesquisador> pesquisadorController = new GenericController<>(Pesquisador.class);
            GenericController<GuiaEspeleologia> guiaController = new GenericController<>(GuiaEspeleologia.class);
            GenericController<Caverna> cavernaController = new GenericController<>(Caverna.class);
            GenericController<Equipamento> equipamentoController = new GenericController<>(Equipamento.class);
            GenericController<Expedicao> expedicaoController = new GenericController<>(Expedicao.class);

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
                    .numero("100")
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
                    .numero("50")
                    .bairro("Tambaú")
                    .cidade("João Pessoa")
                    .unidadeFed("PB")
                    .cep("58039000")
                    .build())
                .registroInst("1234")
                .areaPesquisa("Biologia")
                .titulacao("Mestrado")
                .valor_diario_bolsa(new BigDecimal(250))
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
                    .numero("200")
                    .bairro("Bessa")
                    .cidade("João Pessoa")
                    .unidadeFed("PB")
                    .cep("58035000")
                    .build())
                .num_credenc("456")
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
                .altitude(new BigDecimal("46"))
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
                .dificuldade(nivelDificuldadeEnum.MODERADO)
                .profuncidadeMaxima(new BigDecimal("15.5"))
                .extensao(new BigDecimal("120.0"))
                .descricao("Setor de acesso para estudos.")
                .riscoInundacao(nivelDificuldadeEnum.BAIXO)
                .condicao(condicaoCorrenteEnum.EXCELENTE)
                .caverna(caverna)
                .build();

            caverna.addSetor(setor);

            // 6. Equipamento
            Equipamento equipamento = Equipamento.builder()
                .codPatrimonial("EQ0001")
                .nome("Lanterna")
                .tipo(tipoEquipamentoEnum.ILUMINACAO)
                .fabricante("Marca Exemplo")
                .valor(new BigDecimal("250.00"))
                .dataCompra(LocalDate.of(2025, 1, 10))
                .situacaoOperacional(
                    situacaoOperacionalEquipamentoEnum.DISPONIVEL)
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
                    .numAutoriz("462")
                    .orgaoEmissor("SUDEMA")
                    .dataEmissao(LocalDate.now())
                    .validade(LocalDate.now().plusYears(1))
                    .situacao(situacaoAutorizacaoAmbientalEnum.ATIVO)
                    .observacoes("Autorização para pesquisa.")
                    .pdfAssinado(new byte[]{1})
                    .build();

            // 9. Relatório
            Relatorio relatorio = Relatorio.builder()
                .titulo("Relatório da expedição")
                .Resumo("Registro inicial das atividades.")
                .dataSubmissao(LocalDate.now())
                .numeroPaginas(5)
                .arqCompleto(new byte[]{40, 50, 60})
                .situacaoAprovacao(situacaoAprovacaoRelEnum.APROVADO)
                .publicacaoAprovada(true)
                .build();

            // 10. Expedição
            Expedicao expedicao = Expedicao.builder()
                .codExped("exped_2025_003")
                .titulo("Expedição de reconhecimento")
                .objetivo("Reconhecer e documentar a caverna.")
                .inicio(LocalDateTime.now())
                .termino(LocalDateTime.now().plusHours(4))
                .orcamento(new BigDecimal("1500.00"))
                .custo(new BigDecimal("500.00"))
                .qntdParticip(3)
                .situacao(situacaoExpedicaoEnum.PLANEJADA)
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
                .papelExpedicao(papelExpedicaoEnum.COORDENADOR)
                .dataConfirmacao(LocalDate.now())
                .valorDiaria(new BigDecimal("200.00"))
                .quantidadeDias(1)
                .presenca(false)
                .observacoes("Participação confirmada.")
                .pessoa(pessoa)
                .expedicao(expedicao)
                .build();

            expedicao.addParticipacao(participacao);
            pessoa.addParticipacao(participacao);

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
                    situacaoValidacaoColetaEnum.EM_ABERTO)
                .expedicao(expedicao)
                .amostras(new ArrayList<>())
                .build();

            expedicao.addColeta(coleta);
            pesquisador.addColeta(coleta);

            // 13. Amostra científica
            AmostraCientifica amostra = AmostraCientifica.builder()
                .codAmostra("AM0001")
                .categoria(categoriaAmostraEnum.BIOLOGICA)
                .massa(new BigDecimal("10.10"))
                .volume(null)
                .unidadeMedida(unidadeMedidaAmostraEnum.G)
                .dataAcondicionamento(LocalDate.now())
                .condicaoConservacao(condicaoAmostraEnum.BOA)
                .materialPerigoso(false)
                .fotografiaBinaria(null)
                .obsorvacoes("Amostra inicial.")
                .coleta(coleta)
                .build();

            coleta.addAmostra(amostra);

            // 14. Utilização de equipamento
            UtilizacaoEquipamento utilizacao =
                UtilizacaoEquipamento.builder()
                    .responsavel(pessoa)
                    .equipamento(equipamento)
                    .expedicao(expedicao)
                    .dataHoraRetirada(LocalDateTime.now())
                    .previsaoDevolucao(LocalDate.now().plusDays(1))
                    .dataDevolucao(null)
                    .estadoSaida(estadoEquipamentoEnum.BOM)
                    .estadoRetorno(null)
                    .custoAvaria(null)
                    .build();

                Pessoa pessoa2 = Pessoa.builder()
                    .cpf("09876543210")
                    .nome("Roberto Santos")
                    .datanasc(LocalDate.of(1982, 10, 12))
                    .email("roberto.santos@email.com")
                    .telefone("83911112222")
                    .ativo(true)
                    .endereco(Endereco.builder().logradouro("Rua das Trincheiras").numero("45").bairro("Centro").cidade("Joao Pessoa").unidadeFed("PB").cep("58010000").build())
                    .participacoes(new ArrayList<>())
                    .retiradas(new ArrayList<>())
                    .build();
                Pessoa pessoa3 = Pessoa.builder()
                    .cpf("55544433322")
                    .nome("Juliana Medeiros")
                    .datanasc(LocalDate.of(1991, 2, 25))
                    .email("juliana.medeiros@email.com")
                    .telefone("83944445555")
                    .ativo(true)
                    .endereco(Endereco.builder().logradouro("Av. Epitacio P.").numero("1500").bairro("Torre").cidade("Joao Pessoa").unidadeFed("PB").cep("58040000").build())
                    .participacoes(new ArrayList<>())
                    .retiradas(new ArrayList<>())
                    .build();
                Pessoa pessoa4 = Pessoa.builder()
                    .cpf("22233344455")
                    .nome("Mariana Silva")
                    .datanasc(LocalDate.of(1990, 3, 18))
                    .email("mariana.silva@email.com")
                    .telefone("83955556666")
                    .ativo(true)
                    .endereco(Endereco.builder()
                        .logradouro("Rua da Areia").numero("210")
                        .bairro("Centro").cidade("Joao Pessoa")
                        .unidadeFed("PB").cep("58010010").build())
                    .participacoes(new ArrayList<>())
                    .retiradas(new ArrayList<>())
                    .build();

                Pessoa pessoa5 = Pessoa.builder()
                    .cpf("33344455566")
                    .nome("Pedro Henrique")
                    .datanasc(LocalDate.of(1988, 8, 9))
                    .email("pedro.henrique@email.com")
                    .telefone("83977778888")
                    .ativo(true)
                    .endereco(Endereco.builder()
                        .logradouro("Rua das Flores").numero("75")
                        .bairro("Manaira").cidade("Joao Pessoa")
                        .unidadeFed("PB").cep("58038020").build())
                    .participacoes(new ArrayList<>())
                    .retiradas(new ArrayList<>())
                    .build();

                Pesquisador pesquisador2 = Pesquisador.builder()
                    .cpf("11122233344")
                    .nome("Beatriz Lins")
                    .datanasc(LocalDate.of(1985, 7, 30))
                    .email("beatriz.lins@email.com")
                    .telefone("83966667777")
                    .ativo(true)
                    .endereco(Endereco.builder().logradouro("Rua Bancario").numero("320").bairro("Bancarios").cidade("Joao Pessoa").unidadeFed("PB").cep("58051000").build())
                    .registroInst("5678")
                    .areaPesquisa("Geologia")
                    .titulacao("Doutorado")
                    .valor_diario_bolsa(new BigDecimal("400"))
                    .participacoes(new ArrayList<>())
                    .retiradas(new ArrayList<>())
                    .coletas(new ArrayList<>())
                    .build();
                Pesquisador pesquisador3 = Pesquisador.builder()
                    .cpf("99988877766")
                    .nome("Fernando Castro")
                    .datanasc(LocalDate.of(1978, 12, 5))
                    .email("fernando.castro@email.com")
                    .telefone("83933334444")
                    .ativo(true)
                    .endereco(Endereco.builder().logradouro("Praca Independencia").numero("12").bairro("Tambia").cidade("Joao Pessoa").unidadeFed("PB").cep("58020000").build())
                    .registroInst("9101")
                    .areaPesquisa("Arqueologia")
                    .titulacao("Doutorado")
                    .valor_diario_bolsa(new BigDecimal(350))
                    .participacoes(new ArrayList<>())
                    .retiradas(new ArrayList<>())
                    .coletas(new ArrayList<>())
                    .build();

                Pesquisador pesquisador4 = Pesquisador.builder()
                    .cpf("44433322211")
                    .nome("Camila Ferreira")
                    .datanasc(LocalDate.of(1987, 6, 22))
                    .email("camila.ferreira@email.com")
                    .telefone("83912121212")
                    .ativo(true)
                    .endereco(Endereco.builder().logradouro("Rua dos Ipês").numero("90").bairro("Bancarios").cidade("Joao Pessoa").unidadeFed("PB").cep("58051020").build())
                    .registroInst("2345")
                    .areaPesquisa("Biologia")
                    .titulacao("Mestrado")
                    .valor_diario_bolsa(new BigDecimal("320.00"))
                    .participacoes(new ArrayList<>())
                    .retiradas(new ArrayList<>())
                    .coletas(new ArrayList<>())
                    .build();

                Pesquisador pesquisador5 = Pesquisador.builder()
                    .cpf("55566677788")
                    .nome("Rafael Nascimento")
                    .datanasc(LocalDate.of(1983, 11, 3))
                    .email("rafael.nascimento@email.com")
                    .telefone("83913131313")
                    .ativo(true)
                    .endereco(Endereco.builder().logradouro("Av. Central").numero("430").bairro("Torre").cidade("Joao Pessoa").unidadeFed("PB").cep("58040030").build())
                    .registroInst("3456")
                    .areaPesquisa("Geologia")
                    .titulacao("Doutorado")
                    .valor_diario_bolsa(new BigDecimal("450.00"))
                    .participacoes(new ArrayList<>())
                    .retiradas(new ArrayList<>())
                    .coletas(new ArrayList<>())
                    .build();

                GuiaEspeleologia guia2 = GuiaEspeleologia.builder()
                    .cpf("44455566677")
                    .nome("Lucas Mendes")
                    .datanasc(LocalDate.of(1995, 4, 14))
                    .email("lucas.mendes@email.com")
                    .telefone("83922223333")
                    .ativo(true)
                    .endereco(Endereco.builder().logradouro("Rua dos Pescadores").numero("80").bairro("Cabo Branco").cidade("Joao Pessoa").unidadeFed("PB").cep("58045000").build())
                    .participacoes(new ArrayList<>())
                    .retiradas(new ArrayList<>())
                    .num_credenc("789")
                    .nivel_certif(1)
                    .validade_certif(LocalDate.of(2027, 6, 15))
                    .qntd_expedicoes(2)
                    .build();
                GuiaEspeleologia guia3 = GuiaEspeleologia.builder()
                    .cpf("12312312312")
                    .nome("Jonas Almeida")
                    .datanasc(LocalDate.of(1970, 1, 10))
                    .email("jonas.almeida@email.com")
                    .telefone("83900001111")
                    .ativo(true)
                    .endereco(Endereco.builder().logradouro("Estrada Rural").numero("5").bairro("Zona Rural").cidade("Areia").unidadeFed("PB").cep("58397000").build())
                    .participacoes(new ArrayList<>())
                    .retiradas(new ArrayList<>())
                    .num_credenc("101")
                    .nivel_certif(5)
                    .validade_certif(LocalDate.of(2030, 1, 1))
                    .qntd_expedicoes(150)
                    .build();
                GuiaEspeleologia guia4 = GuiaEspeleologia.builder()
                    .cpf("66677788899")
                    .nome("Andre Oliveira")
                    .datanasc(LocalDate.of(1992, 9, 12))
                    .email("andre.oliveira@email.com")
                    .telefone("83914141414")
                    .ativo(true)
                    .endereco(Endereco.builder().logradouro("Rua do Sol").numero("32").bairro("Cabo Branco").cidade("Joao Pessoa").unidadeFed("PB").cep("58045010").build())
                    .participacoes(new ArrayList<>())
                    .retiradas(new ArrayList<>())
                    .num_credenc("202")
                    .nivel_certif(3)
                    .validade_certif(LocalDate.of(2029, 12, 31))
                    .qntd_expedicoes(35)
                    .build();
                GuiaEspeleologia guia5 = GuiaEspeleologia.builder()
                    .cpf("77788899900")
                    .nome("Isabela Moura")
                    .datanasc(LocalDate.of(1996, 5, 27))
                    .email("isabela.moura@email.com")
                    .telefone("83915151515")
                    .ativo(true)
                    .endereco(Endereco.builder().logradouro("Estrada da Serra").numero("18").bairro("Zona Rural").cidade("Areia").unidadeFed("PB").cep("58397010").build())
                    .participacoes(new ArrayList<>())
                    .retiradas(new ArrayList<>())
                    .num_credenc("303")
                    .nivel_certif(2)
                    .validade_certif(LocalDate.of(2028, 7, 20))
                    .qntd_expedicoes(18)
                    .build();

                Caverna caverna2 = Caverna.builder()
                    .nomeCaverna("Gruta do Rio Subterraneo")
                    .codCadAmbiental("PB0002")
                    .municipio("Santa Luzia")
                    .uf("PB")
                    .altitude(new BigDecimal("275"))
                    .ultimaInsp(LocalDate.now().minusMonths(6))
                    .indAcesso(false)
                    .extensao(new BigDecimal("1200.00"))
                    .localizacao(Localizacao.builder().latitude(new BigDecimal("-6.8710")).longitude(new BigDecimal("-36.9189")).datum(datumGeodesicoEnum.SIRGAS2000).build())
                    .setores(new ArrayList<>())
                    .build();
                Caverna caverna3 = Caverna.builder()
                    .nomeCaverna("Toca dos Fosseis")
                    .codCadAmbiental("PB0003")
                    .municipio("Sousa")
                    .uf("PB")
                    .altitude(new BigDecimal("240"))
                    .ultimaInsp(LocalDate.now().minusYears(1))
                    .indAcesso(true)
                    .extensao(new BigDecimal("500.25"))
                    .localizacao(Localizacao.builder().latitude(new BigDecimal("-6.7629")).longitude(new BigDecimal("-38.2255")).datum(datumGeodesicoEnum.SIRGAS2000).build())
                    .setores(new ArrayList<>())
                    .build();
                Caverna caverna4 = Caverna.builder()
                    .nomeCaverna("Gruta da Pedra Clara")
                    .codCadAmbiental("PB0004")
                    .municipio("Cabaceiras")
                    .uf("PB")
                    .altitude(new BigDecimal("310.00"))
                    .ultimaInsp(LocalDate.now().minusMonths(3))
                    .indAcesso(true)
                    .extensao(new BigDecimal("850.500"))
                    .localizacao(Localizacao.builder().latitude(new BigDecimal("-7.4880")).longitude(new BigDecimal("-36.2860")).datum(datumGeodesicoEnum.SIRGAS2000).build())
                    .setores(new ArrayList<>())
                    .build();
                Caverna caverna5 = Caverna.builder()
                    .nomeCaverna("Caverna do Vale Verde")
                    .codCadAmbiental("PB0005")
                    .municipio("Pocinhos")
                    .uf("PB")
                    .altitude(new BigDecimal("290.00"))
                    .ultimaInsp(LocalDate.now().minusMonths(2))
                    .indAcesso(true)
                    .extensao(new BigDecimal("640.250"))
                    .localizacao(Localizacao.builder().latitude(new BigDecimal("-7.0760")).longitude(new BigDecimal("-36.0630")).datum(datumGeodesicoEnum.SIRGAS2000).build())
                    .setores(new ArrayList<>())
                    .build();

                SetorPesquisa setor2 = SetorPesquisa.builder()
                    .denominacao("Salao Submerso")
                    .dificuldade(nivelDificuldadeEnum.EXTREMO)
                    .profuncidadeMaxima(new BigDecimal("80.0"))
                    .extensao(new BigDecimal("300.0"))
                    .descricao("Area alagada que exige mergulho especializado.")
                    .riscoInundacao(nivelDificuldadeEnum.ALTO)
                    .condicao(condicaoCorrenteEnum.INUNDADO)
                    .caverna(caverna2)
                    .coletas(new ArrayList<>())
                    .build();
                SetorPesquisa setor3 = SetorPesquisa.builder()
                    .denominacao("Fosso dos Ossos")
                    .dificuldade(nivelDificuldadeEnum.ALTO)
                    .profuncidadeMaxima(new BigDecimal("45.0"))
                    .extensao(new BigDecimal("150.0"))
                    .descricao("Sitio de escavacao paleontologica.")
                    .riscoInundacao(nivelDificuldadeEnum.BAIXO)
                    .condicao(condicaoCorrenteEnum.EXCELENTE)
                    .caverna(caverna3)
                    .coletas(new ArrayList<>())
                    .build();
                SetorPesquisa setor4 = SetorPesquisa.builder()
                    .denominacao("Galeria dos Cristais")
                    .dificuldade(nivelDificuldadeEnum.MODERADO)
                    .profuncidadeMaxima(new BigDecimal("22.0"))
                    .extensao(new BigDecimal("180.0"))
                    .descricao("Galeria com formações minerais e pontos de observação.")
                    .riscoInundacao(nivelDificuldadeEnum.BAIXO)
                    .condicao(condicaoCorrenteEnum.RISCO_ESTRUTURAL)
                    .caverna(caverna4)
                    .coletas(new ArrayList<>())
                    .build();
                SetorPesquisa setor5 = SetorPesquisa.builder()
                    .denominacao("Conduto do Vale")
                    .dificuldade(nivelDificuldadeEnum.ALTO)
                    .profuncidadeMaxima(new BigDecimal("55.0"))
                    .extensao(new BigDecimal("420.0"))
                    .descricao("Conduto estreito com trechos de acesso técnico.")
                    .riscoInundacao(nivelDificuldadeEnum.BAIXO)
                    .condicao(condicaoCorrenteEnum.UMIDO_ESCORREGADIO)
                    .caverna(caverna5)
                    .coletas(new ArrayList<>())
                    .build();
                caverna2.addSetor(setor2);
                caverna3.addSetor(setor3);
                caverna4.addSetor(setor4);
                caverna5.addSetor(setor5);

                Equipamento equipamento2 = Equipamento.builder()
                    .codPatrimonial("EQ0002")
                    .nome("Medidor multigas")
                    .tipo(tipoEquipamentoEnum.MONITORAMENTO_AMBIENTAL)
                    .fabricante("SafetyCorp")
                    .valor(new BigDecimal("3500.00"))
                    .dataCompra(LocalDate.of(2024, 5, 20))
                    .dataUltimaManutencao(LocalDate.of(2026, 2, 10))
                    .situacaoOperacional(situacaoOperacionalEquipamentoEnum.EM_USO)
                    .exigeCalibracao(true)
                    .utilizacoes(new ArrayList<>())
                    .build();
                Equipamento equipamento3 = Equipamento.builder()
                    .codPatrimonial("EQ0003")
                    .nome("Corda dinamica 100m")
                    .tipo(tipoEquipamentoEnum.SEGURANCA)
                    .fabricante("ClimbPro")
                    .valor(new BigDecimal("800.00"))
                    .dataCompra(LocalDate.of(2026, 2, 10))
                    .dataUltimaManutencao(LocalDate.of(2026, 3, 1))
                    .situacaoOperacional(situacaoOperacionalEquipamentoEnum.DISPONIVEL)
                    .exigeCalibracao(false)
                    .utilizacoes(new ArrayList<>())
                    .build();
                Equipamento equipamento4 = Equipamento.builder()
                    .codPatrimonial("EQ0004")
                    .nome("Receptor GPS")
                    .tipo(tipoEquipamentoEnum.MONITORAMENTO_AMBIENTAL)
                    .fabricante("GeoTech")
                    .valor(new BigDecimal("2200.00"))
                    .dataCompra(LocalDate.of(2025, 3, 12))
                    .dataUltimaManutencao(LocalDate.of(2026, 6, 10))
                    .situacaoOperacional(situacaoOperacionalEquipamentoEnum.EM_MANUNTENCAO)
                    .exigeCalibracao(false)
                    .utilizacoes(new ArrayList<>())
                    .build();
                Equipamento equipamento5 = Equipamento.builder()
                    .codPatrimonial("EQ0005")
                    .nome("Capacete com iluminacao")
                    .tipo(tipoEquipamentoEnum.SEGURANCA)
                    .fabricante("CaveSafe")
                    .valor(new BigDecimal("480.00"))
                    .dataCompra(LocalDate.of(2025, 8, 5))
                    .dataUltimaManutencao(LocalDate.of(2026, 5, 15))
                    .situacaoOperacional(situacaoOperacionalEquipamentoEnum.DISPONIVEL)
                    .exigeCalibracao(false)
                    .utilizacoes(new ArrayList<>())
                    .build();

                PlanoSeguranca plano2 = PlanoSeguranca.builder()
                    .procedsEvacuacao("Retornar em dupla pela corda-guia e acionar a equipe de superficie.")
                    .pontoEncontro("Acampamento Base 1")
                    .tempoSemComunic(LocalTime.of(1, 0))
                    .telefoneEmerg("83988887777")
                    .precisaMedico(true)
                    .mapa(new byte[]{4, 5, 6})
                    .build();
                PlanoSeguranca plano3 = PlanoSeguranca.builder()
                    .procedsEvacuacao("Icar a equipe pela entrada superior em caso de bloqueio.")
                    .pontoEncontro("Plato da Pedra")
                    .tempoSemComunic(LocalTime.of(2, 0))
                    .telefoneEmerg("83966665555")
                    .precisaMedico(true)
                    .mapa(new byte[]{7, 8, 9})
                    .build();
                PlanoSeguranca plano4 = PlanoSeguranca.builder()
                    .procedsEvacuacao("Retornar pela galeria principal em grupos acompanhados.")
                    .pontoEncontro("Entrada da Gruta")
                    .tempoSemComunic(LocalTime.of(0, 45))
                    .telefoneEmerg("83988880004")
                    .precisaMedico(false)
                    .mapa(new byte[]{10, 11, 12})
                    .build();
                PlanoSeguranca plano5 = PlanoSeguranca.builder()
                    .procedsEvacuacao("Interromper a atividade e seguir a rota sinalizada.")
                    .pontoEncontro("Base do Vale")
                    .tempoSemComunic(LocalTime.of(1, 30))
                    .telefoneEmerg("83988880005")
                    .precisaMedico(true)
                    .mapa(new byte[]{13, 14, 15})
                    .build();

                AutorizacaoAmbiental autorizacao2 = AutorizacaoAmbiental.builder()
                    .numAutoriz("15")
                    .orgaoEmissor("IBAMA")
                    .dataEmissao(LocalDate.now().minusDays(10))
                    .validade(LocalDate.now().plusMonths(6))
                    .situacao(situacaoAutorizacaoAmbientalEnum.ATIVO)
                    .observacoes("Levantamento hidrologico com mergulho cientifico.")
                    .pdfAssinado(new byte[]{37, 80, 68, 70, 45, 49, 46, 52, 2})
                    .build();
                AutorizacaoAmbiental autorizacao3 = AutorizacaoAmbiental.builder()
                    .numAutoriz("9")
                    .orgaoEmissor("SUDEMA")
                    .dataEmissao(LocalDate.now().minusMonths(2))
                    .validade(LocalDate.now().plusYears(2))
                    .situacao(situacaoAutorizacaoAmbientalEnum.ATIVO)
                    .observacoes("Escavacao paleontologica controlada.")
                    .pdfAssinado(new byte[]{37, 80, 68, 70, 45, 49, 46, 52, 3})
                    .build();
                AutorizacaoAmbiental autorizacao4 = AutorizacaoAmbiental.builder()
                    .numAutoriz("24")
                    .orgaoEmissor("SUDEMA")
                    .dataEmissao(LocalDate.now().minusDays(20))
                    .validade(LocalDate.now().plusMonths(8))
                    .situacao(situacaoAutorizacaoAmbientalEnum.INATIVO)
                    .observacoes("Autorizacao para levantamento geologico.")
                    .pdfAssinado(new byte[]{37, 80, 68, 70, 4})
                    .build();
                AutorizacaoAmbiental autorizacao5 = AutorizacaoAmbiental.builder()
                    .numAutoriz("25")
                    .orgaoEmissor("IBAMA")
                    .dataEmissao(LocalDate.now().minusDays(12))
                    .validade(LocalDate.now().plusYears(1))
                    .situacao(situacaoAutorizacaoAmbientalEnum.ATIVO)
                    .observacoes("Autorizacao para estudo de biodiversidade.")
                    .pdfAssinado(new byte[]{37, 80, 68, 70, 5})
                    .build();
    
                Relatorio relatorio2 = Relatorio.builder()
                    .titulo("Analise do Rio Subterraneo")
                    .Resumo("Resultados da coleta hidrologica e do mapeamento do lencol.")
                    .dataSubmissao(LocalDate.now().minusDays(2))
                    .numeroPaginas(30)
                    .arqCompleto(new byte[]{40, 50, 60})
                    .situacaoAprovacao(situacaoAprovacaoRelEnum.REPROVADO)
                    .publicacaoAprovada(false)
                    .build();
                Relatorio relatorio3 = Relatorio.builder()
                    .titulo("Catalogo de fosseis da Toca")
                    .Resumo("Catalogacao preliminar dos achados paleontologicos.")
                    .dataSubmissao(LocalDate.now().minusDays(5))
                    .numeroPaginas(45)
                    .arqCompleto(null)
                    .situacaoAprovacao(situacaoAprovacaoRelEnum.PENDENTE)
                    .publicacaoAprovada(false)
                    .build();
                Relatorio relatorio4 = Relatorio.builder()
                    .titulo("Levantamento Geologico da Pedra Clara")
                    .Resumo("Registro de observacoes geologicas e caracteristicas da gruta.")
                    .dataSubmissao(LocalDate.now().minusDays(1))
                    .numeroPaginas(22)
                    .arqCompleto(new byte[]{40, 41, 42})
                    .situacaoAprovacao(situacaoAprovacaoRelEnum.PENDENTE)
                    .publicacaoAprovada(false)
                    .build();
                Relatorio relatorio5 = Relatorio.builder()
                    .titulo("Estudo de Biodiversidade do Vale")
                    .Resumo("Relatorio preliminar sobre especies observadas na caverna.")
                    .dataSubmissao(LocalDate.now())
                    .numeroPaginas(36)
                    .arqCompleto(new byte[]{43, 44, 45})
                    .situacaoAprovacao(situacaoAprovacaoRelEnum.APROVADO)
                    .publicacaoAprovada(true)
                    .build();

                Expedicao expedicao2 = Expedicao.builder()
                    .codExped("exped_2026_001")
                    .titulo("Mergulho no Rio Subterraneo")
                    .objetivo("Mapear e coletar agua do aquifero.")
                    .inicio(LocalDateTime.now().minusHours(1))
                    .termino(LocalDateTime.now().plusHours(5))
                    .orcamento(new BigDecimal("15000.00"))
                    .custo(new BigDecimal("7500.00"))
                    .qntdParticip(5)
                    .situacao(situacaoExpedicaoEnum.EM_ANDAMENTO)
                    .cancelEmerg(false)
                    .caverna(caverna2)
                    .planoSeguranca(plano2)
                    .autorizAmbiental(autorizacao2)
                    .relatorio(relatorio2)
                    .participacoes(new ArrayList<>())
                    .coletasCientificas(new ArrayList<>())
                    .utilizacoes(new ArrayList<>())
                    .build();
                Expedicao expedicao3 = Expedicao.builder()
                    .codExped("exped_2026_002")
                    .titulo("Busca Paleontologica na Toca")
                    .objetivo("Localizar e documentar fosseis subterraneos.")
                    .inicio(LocalDateTime.now().minusMonths(1))
                    .termino(LocalDateTime.now().minusMonths(1).plusDays(5))
                    .orcamento(new BigDecimal("8000.00"))
                    .custo(new BigDecimal("8200.00"))
                    .qntdParticip(8)
                    .situacao(situacaoExpedicaoEnum.CONCLUIDA)
                    .cancelEmerg(false)
                    .caverna(caverna3)
                    .planoSeguranca(plano3)
                    .autorizAmbiental(autorizacao3)
                    .relatorio(relatorio3)
                    .participacoes(new ArrayList<>())
                    .coletasCientificas(new ArrayList<>())
                    .utilizacoes(new ArrayList<>())
                    .build();
                Expedicao expedicao4 = Expedicao.builder()
                    .codExped("exped_2026_003")
                    .titulo("Levantamento da Pedra Clara")
                    .objetivo("Mapear as galerias e registrar formacoes minerais.")
                    .inicio(LocalDateTime.now().minusHours(2))
                    .termino(LocalDateTime.now().plusHours(6))
                    .orcamento(new BigDecimal("12000.00"))
                    .custo(new BigDecimal("3500.00"))
                    .qntdParticip(4)
                    .situacao(situacaoExpedicaoEnum.EM_ANDAMENTO)
                    .cancelEmerg(false)
                    .caverna(caverna4)
                    .planoSeguranca(plano4)
                    .autorizAmbiental(autorizacao4)
                    .relatorio(relatorio4)
                    .participacoes(new ArrayList<>())
                    .coletasCientificas(new ArrayList<>())
                    .utilizacoes(new ArrayList<>())
                    .build();
                Expedicao expedicao5 = Expedicao.builder()
                    .codExped("exped_2026_004")
                    .titulo("Biodiversidade do Vale Verde")
                    .objetivo("Registrar especies e condicoes ambientais.")
                    .inicio(LocalDateTime.now().minusDays(10))
                    .termino(LocalDateTime.now().minusDays(8))
                    .orcamento(new BigDecimal("9000.00"))
                    .custo(new BigDecimal("7200.00"))
                    .qntdParticip(3)
                    .situacao(situacaoExpedicaoEnum.CONCLUIDA)
                    .cancelEmerg(false)
                    .caverna(caverna5)
                    .planoSeguranca(plano5)
                    .autorizAmbiental(autorizacao5)
                    .relatorio(relatorio5)
                    .participacoes(new ArrayList<>())
                    .coletasCientificas(new ArrayList<>())
                    .utilizacoes(new ArrayList<>())
                    .build();

                Participacao participacao2 = Participacao.builder()
                    .papelExpedicao(papelExpedicaoEnum.PESQUISADOR)
                    .dataConfirmacao(LocalDate.now().minusDays(5))
                    .valorDiaria(new BigDecimal("400.00"))
                    .quantidadeDias(4)
                    .presenca(true)
                    .observacoes("Especialista responsavel pela geologia.")
                    .pessoa(pesquisador2)
                    .expedicao(expedicao2)
                    .build();
                Participacao participacao3 = Participacao.builder()
                    .papelExpedicao(papelExpedicaoEnum.GUIA)
                    .dataConfirmacao(LocalDate.now().minusMonths(2))
                    .valorDiaria(new BigDecimal("300.00"))
                    .quantidadeDias(5)
                    .presenca(true)
                    .observacoes("Lider de seguranca da equipe.")
                    .pessoa(guia3)
                    .expedicao(expedicao3)
                    .build();
                Participacao participacao4 = Participacao.builder()
                    .papelExpedicao(papelExpedicaoEnum.PESQUISADOR)
                    .dataConfirmacao(LocalDate.now().minusDays(3))
                    .valorDiaria(new BigDecimal("350.00"))
                    .quantidadeDias(2)
                    .presenca(true)
                    .observacoes("Responsavel pelo levantamento geologico.")
                    .pessoa(pesquisador4)
                    .expedicao(expedicao4)
                    .build();
                Participacao participacao5 = Participacao.builder()
                    .papelExpedicao(papelExpedicaoEnum.GUIA)
                    .dataConfirmacao(LocalDate.now().minusDays(15))
                    .valorDiaria(new BigDecimal("280.00"))
                    .quantidadeDias(2)
                    .presenca(true)
                    .observacoes("Guia responsavel pelo trajeto.")
                    .pessoa(guia4)
                    .expedicao(expedicao4)
                    .build();
                Participacao participacao6 = Participacao.builder()
                    .papelExpedicao(papelExpedicaoEnum.PESQUISADOR)
                    .dataConfirmacao(LocalDate.now().minusDays(12))
                    .valorDiaria(new BigDecimal("450.00"))
                    .quantidadeDias(2)
                    .presenca(true)
                    .observacoes("Responsavel pelo estudo de biodiversidade.")
                    .pessoa(pesquisador5)
                    .expedicao(expedicao5)
                    .build();
                Participacao participacao7 = Participacao.builder()
                    .papelExpedicao(papelExpedicaoEnum.GUIA)
                    .dataConfirmacao(LocalDate.now().minusDays(12))
                    .valorDiaria(new BigDecimal("300.00"))
                    .quantidadeDias(2)
                    .presenca(true)
                    .observacoes("Acompanhamento da equipe de campo.")
                    .pessoa(guia5)
                    .expedicao(expedicao5)
                    .build();
                expedicao2.addParticipacao(participacao2);
                expedicao3.addParticipacao(participacao3);
                expedicao4.addParticipacao(participacao4);
                expedicao4.addParticipacao(participacao5);
                expedicao5.addParticipacao(participacao6);
                expedicao5.addParticipacao(participacao7);
                pesquisador2.addParticipacao(participacao2);
                guia3.addParticipacao(participacao3);
                pesquisador4.addParticipacao(participacao4);
                guia4.addParticipacao(participacao5);
                pesquisador5.addParticipacao(participacao6);
                guia5.addParticipacao(participacao7);

                ColetaCientifica coleta2 = ColetaCientifica.builder()
                    .setor(setor2)
                    .pesquisador(pesquisador2)
                    .dataHora(LocalDateTime.now().minusHours(2))
                    .metodoEmpregado("Mergulho autonomo")
                    .descricaoPonto("Fundo do lago subterraneo.")
                    .temperatura(new BigDecimal("18.0"))
                    .umidadeRelativa(new BigDecimal("100.0"))
                    .profundidade(new BigDecimal("80.0"))
                    .observacoes("Amostra de agua coletada em profundidade.")
                    .situacaoValidacao(situacaoValidacaoColetaEnum.CONCLUIDO)
                    .amostras(new ArrayList<>())
                    .build();
                ColetaCientifica coleta3 = ColetaCientifica.builder()
                    .setor(setor3)
                    .pesquisador(pesquisador3)
                    .dataHora(LocalDateTime.now().minusMonths(1).plusDays(2))
                    .metodoEmpregado("Escavacao fina")
                    .descricaoPonto("Solo argiloso junto a parede norte.")
                    .temperatura(new BigDecimal("26.5"))
                    .umidadeRelativa(new BigDecimal("60.0"))
                    .profundidade(new BigDecimal("15.0"))
                    .observacoes("Fragmento osseo localizado e catalogado.")
                    .situacaoValidacao(situacaoValidacaoColetaEnum.CONCLUIDO)
                    .amostras(new ArrayList<>())
                    .build();
                ColetaCientifica coleta4 = ColetaCientifica.builder()
                    .setor(setor4)
                    .pesquisador(pesquisador4)
                    .dataHora(LocalDateTime.now().minusHours(1))
                    .metodoEmpregado("Observacao e registro fotografico")
                    .descricaoPonto("Parede leste da galeria.")
                    .temperatura(new BigDecimal("21.5"))
                    .umidadeRelativa(new BigDecimal("75.0"))
                    .profundidade(new BigDecimal("12.0"))
                    .observacoes("Registro de minerais na parede.")
                    .situacaoValidacao(situacaoValidacaoColetaEnum.CONCLUIDO)
                    .amostras(new ArrayList<>())
                    .build();
                ColetaCientifica coleta5 = ColetaCientifica.builder()
                    .setor(setor5)
                    .pesquisador(pesquisador5)
                    .dataHora(LocalDateTime.now().minusDays(9))
                    .metodoEmpregado("Observacao de campo")
                    .descricaoPonto("Conduto lateral proximo a entrada.")
                    .temperatura(new BigDecimal("19.8"))
                    .umidadeRelativa(new BigDecimal("88.0"))
                    .profundidade(new BigDecimal("30.0"))
                    .observacoes("Registro de especies observadas.")
                    .situacaoValidacao(situacaoValidacaoColetaEnum.CANCELADA)
                    .amostras(new ArrayList<>())
                    .build();
                expedicao2.addColeta(coleta2);
                expedicao3.addColeta(coleta3);
                expedicao4.addColeta(coleta4);
                expedicao5.addColeta(coleta5);
                pesquisador2.addColeta(coleta2);
                pesquisador3.addColeta(coleta3);
                pesquisador4.addColeta(coleta4);
                pesquisador5.addColeta(coleta5);
                setor2.addColeta(coleta2);
                setor3.addColeta(coleta3);
                setor4.addColeta(coleta4);
                setor5.addColeta(coleta5);

                AmostraCientifica amostra2 = AmostraCientifica.builder()
                    .codAmostra("AM0002")
                    .categoria(categoriaAmostraEnum.HIDROLOGICA)
                    .massa(null)
                    .volume(new BigDecimal("500.0"))
                    .unidadeMedida(unidadeMedidaAmostraEnum.ML)
                    .dataAcondicionamento(LocalDate.now())
                    .condicaoConservacao(condicaoAmostraEnum.BOA)
                    .materialPerigoso(false)
                    .fotografiaBinaria(new byte[]{21, 22, 23})
                    .obsorvacoes("Agua coletada no fundo do lago subterraneo.")
                    .coleta(coleta2)
                    .build();
                AmostraCientifica amostra3 = AmostraCientifica.builder()
                    .codAmostra("AM0003")
                    .categoria(categoriaAmostraEnum.PALEONTOLOGICA)
                    .massa(new BigDecimal("150.5"))
                    .volume(null)
                    .unidadeMedida(unidadeMedidaAmostraEnum.G)
                    .dataAcondicionamento(LocalDate.now().minusMonths(1).plusDays(2))
                    .condicaoConservacao(condicaoAmostraEnum.REGULAR)
                    .materialPerigoso(true)
                    .fotografiaBinaria(new byte[]{31, 32, 33})
                    .obsorvacoes("Fragmento fossil com sedimento aderido.")
                    .coleta(coleta3)
                    .build();
                AmostraCientifica amostra4 = AmostraCientifica.builder()
                    .codAmostra("AM0004")
                    .categoria(categoriaAmostraEnum.GEOLOGICA)
                    .massa(new BigDecimal("220.500"))
                    .volume(null)
                    .unidadeMedida(unidadeMedidaAmostraEnum.G)
                    .dataAcondicionamento(LocalDate.now())
                    .condicaoConservacao(condicaoAmostraEnum.BOA)
                    .materialPerigoso(false)
                    .fotografiaBinaria(new byte[]{51, 52, 53})
                    .obsorvacoes("Fragmento mineral coletado na galeria.")
                    .coleta(coleta4)
                    .build();
                AmostraCientifica amostra5 = AmostraCientifica.builder()
                    .codAmostra("AM0005")
                    .categoria(categoriaAmostraEnum.BIOLOGICA)
                    .massa(null)
                    .volume(new BigDecimal("125.00"))
                    .unidadeMedida(unidadeMedidaAmostraEnum.ML)
                    .dataAcondicionamento(LocalDate.now().minusDays(9))
                    .condicaoConservacao(condicaoAmostraEnum.REGULAR)
                    .materialPerigoso(false)
                    .fotografiaBinaria(new byte[]{54, 55, 56})
                    .obsorvacoes("Material coletado para analise biologica.")
                    .coleta(coleta4)
                    .build();
                AmostraCientifica amostra6 = AmostraCientifica.builder()
                    .codAmostra("AM0006")
                    .categoria(categoriaAmostraEnum.GEOLOGICA)
                    .massa(new BigDecimal("95.750"))
                    .volume(null)
                    .unidadeMedida(unidadeMedidaAmostraEnum.G)
                    .dataAcondicionamento(LocalDate.now())
                    .condicaoConservacao(condicaoAmostraEnum.REGULAR)
                    .materialPerigoso(false)
                    .fotografiaBinaria(new byte[]{57, 58, 59})
                    .obsorvacoes("Pequena amostra de rocha para análise.")
                    .coleta(coleta4)
                    .build();
                coleta2.addAmostra(amostra2);
                coleta3.addAmostra(amostra3);
                coleta4.addAmostra(amostra4);
                coleta4.addAmostra(amostra5);
                coleta4.addAmostra(amostra6);

                UtilizacaoEquipamento utilizacao2 = UtilizacaoEquipamento.builder()
                    .responsavel(pesquisador2)
                    .equipamento(equipamento2)
                    .expedicao(expedicao2)
                    .dataHoraRetirada(LocalDateTime.now().minusHours(5))
                    .previsaoDevolucao(LocalDate.now().plusDays(2))
                    .estadoSaida(estadoEquipamentoEnum.BOM)
                    .build();
                UtilizacaoEquipamento utilizacao3 = UtilizacaoEquipamento.builder()
                    .responsavel(guia3)
                    .equipamento(equipamento3)
                    .expedicao(expedicao3)
                    .dataHoraRetirada(LocalDateTime.now().minusMonths(1))
                    .previsaoDevolucao(LocalDate.now().minusMonths(1).plusDays(5))
                    .dataDevolucao(LocalDate.now().minusMonths(1).plusDays(6))
                    .estadoSaida(estadoEquipamentoEnum.BOM)
                    .estadoRetorno(estadoEquipamentoEnum.DANIFICADO)
                    .custoAvaria(new BigDecimal("150.00"))
                    .build();
                UtilizacaoEquipamento utilizacao4 = UtilizacaoEquipamento.builder()
                    .responsavel(pesquisador4)
                    .equipamento(equipamento4)
                    .expedicao(expedicao4)
                    .dataHoraRetirada(LocalDateTime.now().minusHours(3))
                    .previsaoDevolucao(LocalDate.now().plusDays(1))
                    .estadoSaida(estadoEquipamentoEnum.REGULAR)
                    .build();

                UtilizacaoEquipamento utilizacao5 = UtilizacaoEquipamento.builder()
                    .responsavel(guia4)
                    .equipamento(equipamento5)
                    .expedicao(expedicao4)
                    .dataHoraRetirada(LocalDateTime.now().minusHours(2))
                    .previsaoDevolucao(LocalDate.now().plusDays(1))
                    .estadoSaida(estadoEquipamentoEnum.NOVO)
                    .build();

                UtilizacaoEquipamento utilizacao6 = UtilizacaoEquipamento.builder()
                    .responsavel(pesquisador5)
                    .equipamento(equipamento4)
                    .expedicao(expedicao5)
                    .dataHoraRetirada(LocalDate.now().atStartOfDay())
                    .previsaoDevolucao(LocalDate.now().plusDays(1))
                    .estadoSaida(estadoEquipamentoEnum.BOM)
                    .build();

                UtilizacaoEquipamento utilizacao7 = UtilizacaoEquipamento.builder()
                    .responsavel(guia5)
                    .equipamento(equipamento5)
                    .expedicao(expedicao5)
                    .dataHoraRetirada(LocalDate.now().atStartOfDay())
                    .previsaoDevolucao(LocalDate.now().plusDays(1))
                    .estadoSaida(estadoEquipamentoEnum.BOM)
                    .build();
                

            // associar a utilização aos relacionamentos
            expedicao.addUtilizacao(utilizacao);
            expedicao2.addUtilizacao(utilizacao2);
            expedicao3.addUtilizacao(utilizacao3);
            expedicao4.addUtilizacao(utilizacao4);
            expedicao4.addUtilizacao(utilizacao5);
            expedicao5.addUtilizacao(utilizacao6);
            expedicao5.addUtilizacao(utilizacao7);

            equipamento.addUso(utilizacao);
            pessoa.addRetirada(utilizacao);

            equipamento2.addUso(utilizacao2);
            pesquisador2.addRetirada(utilizacao2);

            equipamento3.addUso(utilizacao3);
            guia3.addRetirada(utilizacao3);

            equipamento4.addUso(utilizacao6);
            equipamento4.addUso(utilizacao4);
            guia4.addRetirada(utilizacao5);

            pesquisador4.addRetirada(utilizacao4);
            equipamento5.addUso(utilizacao5);
            
            pesquisador5.addRetirada(utilizacao6);
            equipamento5.addUso(utilizacao7);
            guia5.addRetirada(utilizacao7);

            // Controller
            pessoaController.criar(em, pessoa);
            pessoaController.criar(em, pessoa2);
            pessoaController.criar(em, pessoa3);
            pessoaController.criar(em, pessoa4);
            pessoaController.criar(em, pessoa5);
           
            pesquisadorController.criar(em, pesquisador);
            pesquisadorController.criar(em, pesquisador2);
            pesquisadorController.criar(em, pesquisador3);
            pesquisadorController.criar(em, pesquisador4);
            pesquisadorController.criar(em, pesquisador5);
            
            guiaController.criar(em, guia);
            guiaController.criar(em, guia2);
            guiaController.criar(em, guia3);
            guiaController.criar(em, guia4);
            guiaController.criar(em, guia5);
            
            cavernaController.criar(em, caverna);
            cavernaController.criar(em, caverna2);
            cavernaController.criar(em, caverna3);
            cavernaController.criar(em, caverna4);
            cavernaController.criar(em, caverna5);
           
            equipamentoController.criar(em, equipamento);
            equipamentoController.criar(em, equipamento2);
            equipamentoController.criar(em, equipamento3);
            equipamentoController.criar(em, equipamento4);
            equipamentoController.criar(em, equipamento5);

            expedicaoController.criar(em, expedicao);
            expedicaoController.criar(em, expedicao2);
            expedicaoController.criar(em, expedicao3);
            expedicaoController.criar(em, expedicao4);
            expedicaoController.criar(em, expedicao5);             

            tx.commit();
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception rollbackError) {
                e.addSuppressed(rollbackError);
            }
        }
    }
}
