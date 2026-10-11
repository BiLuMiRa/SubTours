# Projeto para disciplina de Programação para Web 3 do curso de Bacharelado em Engenharia de Software
[Diagrama de classes UML](https://lucid.app/lucidchart/a4eca017-bd3f-41ba-840d-09cd4d54c86d/edit?viewport_loc=-803%2C-350%2C3738%2C1969%2C0_0&invitationId=inv_f64f6de1-c8e5-4619-94f6-0b2fb15829d4)
## Justificativa das escolhas feitas para o projeto
### Herança
- A herança dos tipos de Pessoa foram configuradas com JOINED, ou seja, Existe a superclasse Pessoa e suas subClasses. Essa decisão foi tomada a partir da regra de negócio que deixa claro que novos tipos de Pessoa podem ser incluídos a qualquer momento, então a estratégia JOINED é a que melhor se aplica nesse cenário, mesmo que exija consultas JOIN.
- Uma Single Table teria muitos valores nulos, além de exigir reestruturação da tabela caso novas subclasses fossem incluídas.
- A estretégia Table per Concrete Class dificulta consultas polimórficas e manutenção de chaves estrangeiras, visto que é difícil garantir a integridade referencial (consultas não sabem para qual tipo de Pessoa apontar)

### Ownership das associações
- Expedicao --> PlanoSeguranca : Expedição é o owner, pois cada plano de segurança é criado especialmente para uma expedição, portanto faz sentido que Expedição tenha a chave estrangeira.
- Expedicao --> AutorizacaoAmbiental : Expedição é o owner, pois cada autorização pertence a apenas uma expedição, portanto faz sentido que Expedição tenha a chave estrangeira.
- Expedicao --> Caverna : Uma expedição acontece em apenas uma caverna, enquanto uma caverna pode receber várias expedições. Nesse caso, expedição é o lado ManyToOne, portanto é o que recebe a chave estrangeira
- Expedicao <--> Relatorio : Expedicao é owner da FK física.
- SetorPesquisa --> Caverna: SetorPesquisa é o owner da relação, pois cada setor possui apenas uma caverna.
- ColetaCientifica --> SetorPesquisa: ColetaCientifica é o owner da relação, pois cada coleta é realizada em um setor específico. 
- ColetaCientifica --> Pesquisador: ColetaCientifica é o owner, pois cada coleta possui um pesquisador responsável.
- ColetaCientifica --> Expedicao: Uma expedição pode possuir várias coletas. Então, ColetaCientifica é o owner, pois possui ManyToOne e é quem recebe a chave estrangeira.
- AmostraCientifica --> ColetaCientifica: Uma coleta pode ter várias amostras e várias amostras podem ser feitas em uma única coleta. Nesse caso, AmostraCientifica é o owner, pois possui o lado ManyToOne, que recebe a chave estrangeira.
- UtilizacaoEquipamento --> Expedicao: UtilizacaoEquipamento é o owner da relação, pois cada utilização está vinculada a uma única expedição, enquanto uma expedição pode registrar várias utilizações de equipamentos. Portanto, faz sentido que UtilizacaoEquipamento tenha a chave estrangeira.
- UtilizacaoEquipamento → Equipamento: UtilizacaoEquipamento é o owner da relação, pois cada utilização se refere a um único equipamento, enquanto um equipamento pode ser utilizado em várias expedições. Portanto, faz sentido que UtilizacaoEquipamento tenha a chave estrangeira.
- UtilizacaoEquipamento → Pessoa: UtilizacaoEquipamento é o owner da relação, pois cada utilização possui uma única pessoa responsável, enquanto uma pessoa pode ser responsável por várias utilizações. Portanto, faz sentido que UtilizacaoEquipamento tenha a chave estrangeira.

### Cascatas
- Expedição --> Coletas científicas (cascade = CascadeType.ALL): As coletas científicas têm ciclo de vida totalmente dependente da expedição onde foram efetuadas.
- Expedição --> Utilizações (cascade = CascadeType.ALL) : Trata-se de uma entidade de movimentação/histórico. Ao remover uma expedição, o histórico de utilização de equipamentos vinculado a ela deve ser limpo
- Expedicao --> PlanoSeguranca (cascade = CascadeType.ALL) : Plano de segurança possue composição e ciclo de vida estreitamente atrelados à expedição
- Expedicao --> AutorizacaoAmbiental (cascade = CascadeType.ALL) : Autorização Ambiental possue composição e ciclo de vida estreitamente atrelados à expedição
- Expedicao --> Relatorio (cascade = CascadeType.ALL) : Relatório possue composição e ciclo de vida estreitamente atrelados à expedição
- Caverna --> SetorPesquisa (cascade = CascadeType.ALL) : Um setor não possui significado ou utilidade sem estar atrelado a uma caverna.
- ColetaCientifica --> AmostraCientifica (CascadeType.ALL): permite persistir, atualizar e remover amostras a partir da coleta à qual pertencem.

### OrphanRemoval
- Expedicao --> PlanoSeguranca (orphanRemoval = true): Permite a substituição ou desvinculação do plano na expedição (expedicao.setPlanoSeguranca(null)), garantindo que o registro do plano antigo seja deletado fisicamente do PostgreSQL em vez de permanecer como dado órfão no banco.
- Expedicao --> AutorizacaoAmbiental (orphanRemoval = true) : Garante que o cancelamento ou substituição da autorização desvincule e remova o documento obsoleto da tabela correspondente.
- Expedicao --> Relatorio (orphanRemoval = true) : Permite que, ao descartar ou reemitir o relatório final associado a uma expedição em memória Java, o Hibernate execute a exclusão física do relatório no banco de dados.
- Expedição --> Coletas científicas (orphanRemoval = true) : Garante que a remoção de um item de movimentação diretamente da coleção Java dispare a exclusão física da linha na respectiva tabela do banco de dados.
- Expedição --> Utilizações (orphanRemoval = true) : Garante que a remoção de um item de movimentação diretamente da coleção Java dispare a exclusão física da linha na respectiva tabela do banco de dados.
- Caverna --> SetorPesquisa (orphanRemoval = true) : Garante que se uma caverna for delatada todos os seus setores são apagados e se um setor for retirado da lista da caverna, ele seja removido fisicamente do banco de dados.
- ColetaCientifica --> AmostraCientifica (orphanRemoval = true): Garante que ao remover uma amostra da coleção da coleta, seu registro seja excluído fisicamente do banco de dados.

## Fetch
- Relacionamentos @OneToOne e @ManyToOne: Todos os relacionamentos de entidade única foram explicitamente definidos como FetchType.LAZY (ex: Caverna, PlanoSeguranca, Relatorio). Essa prática substitui o padrão default EAGER do JPA para esses mapeamentos, evitando o problema de consultas N+1 e carregamentos desnecessários em memória ao listar expedições.
- Coleções @OneToMany: Mantidos com o padrão intrínseco LAZY.
- Otimização Especial de Mídia/BLOB: Os campos binários e a lista de utilizações em Equipamento foram configurados como LAZY. Isso assegura que arquivos binários pesados e o histórico de movimentações dos equipamentos não sejam trazidos do banco durante buscas simples do sistema.

## UniqueConstraint
- Participação: Garante que não haja repetição dos valores de uma coluna específica ou combinação de colunas. No caso de participação, assegura que uma pessoa não possa ser cadastrada mais de uma vez na mesma expedição.

## Problema N+1 e solução com JOIN FETCH
O problema de N+1 ocorre ao listar as coletas científicas de uma determinada expedição e acessar os dados de seus respectivos setores e pesquisadores responsáveis. Se o mapeamento estiver configurado como EAGER ou se as consultas forem feitas de forma isolada sem controle de carregamento, o Hibernate executa inicialmente 1 consulta para buscar a lista de coletas

    (SELECT * FROM coleta_cientifica WHERE expedicao_id = ?) 

e, em seguida, executa N consultas adicionais individuais — uma para buscar o setor e outra para buscar o pesquisador de cada coleta retornada. Isso resulta em dezenas de acessos desnecessários ao banco de dados.
### Solução aplicada: JOIN FETCH
A mitigação foi implementada no repositório de coletas utilizando o JOIN FETCH na instrução JPQL. Dessa forma, a consulta recupera a coleta científica, o setor e o pesquisador em uma única instrução SQL utilizando junções (JOIN).

Implementação utilizada (ColetaCientificaRepository):

    public List<ColetaCientifica> ccPorIdExpedicao(Integer id) {
    TypedQuery<ColetaCientifica> typedQueryCc = 
        em.createQuery(
            "SELECT cc FROM ColetaCientifica cc " +
            "JOIN FETCH cc.setor " +
            "JOIN FETCH cc.pesquisador " +
            "WHERE cc.expedicao.id = :id", 
            ColetaCientifica.class
        ).setParameter("id", id);

    return typedQueryCc.getResultList();
    }

## Resultado das Consultas
=== Coletas com setor e pesquisador responsável ===

    Descrição do ponto: Fundo do lago subterraneo.
    Setor: Salao Submerso
    Pesquisador: Beatriz Lins
=== Amostras pelo id da coleta cientifica ===

    Id da coleta cientifica 1
    Cod da amostra: AM0001
    Categoria da amostra: BIOLOGICA

=== Equipamentos ===

    Nome do equipamento: Capacete com iluminacao
    Tipo do equipamento: SEGURANCA
    Situação do equipamento: DISPONIVEL

    Nome do equipamento: Lanterna
    Tipo do equipamento: ILUMINACAO
    Situação do equipamento: DISPONIVEL

    Nome do equipamento: Corda dinamica 100m
    Tipo do equipamento: SEGURANCA
    Situação do equipamento: DISPONIVEL

=== Detalhes das expedições ===

    Quantidade de resultados: 1
    COD: exped_2025_003
    Título: Expedição de reconhecimento
    ID Caverna: 1
    Nome Caverna: Caverna da Esperança
    Início: 2026-09-29T10:18:03.786574
    Término: 2026-09-29T14:18:03.786574
    Situação: PLANEJADA


=== Expedição e participantes ===

    ID: 1
    Cod Expedição: exped_2025_003
    Título: Expedição de reconhecimento
    Objetivo: Reconhecer e documentar a caverna.
    Início: 2026-09-29T10:18:03.786574
    Término: 2026-09-29T14:18:03.786574
    Orçamento: 1500.00
    Custo: 500.00
    Quantidade de participantes: 3
    Situação: PLANEJADA
    Cancelamento emergencial: false
    Participante: Ana Oliveira | Papel: COORDENADOR

=== PDF Autorização Ambiental ===

    PDF: [B@42365c82

=== Mapa do Plano de Segurança ===

    Mapa: null

=== Arquivo completo do Relatório ===

    Arquivo: [B@2da3d7d3

=== Cavernas Acessíveis ===

    Codigo de cadastro amebiental: PB0001
    Nome: Caverna da Esperança
    Municipio: João Pessoa
    Estado: PB

    Codigo de cadastro amebiental: PB0003
    Nome: Toca dos Fosseis
    Municipio: Sousa
    Estado: PB

    Codigo de cadastro amebiental: PB0004
    Nome: Gruta da Pedra Clara
    Municipio: Cabaceiras
    Estado: PB

    Codigo de cadastro amebiental: PB0005
    Nome: Caverna do Vale Verde
    Municipio: Pocinhos
    Estado: PB

=== Cavernas com inspeção vencida ===

    Codigo de cadastro amebiental: PB0002
    Nome: Gruta do Rio Subterraneo
    Municipio: Santa Luzia
    Estado: PB
    Data última inspeção: 2026-03-29

    Codigo de cadastro amebiental: PB0003
    Nome: Toca dos Fosseis
    Municipio: Sousa
    Estado: PB
    Data última inspeção: 2025-09-29