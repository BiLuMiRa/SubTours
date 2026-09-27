# Projeto para disciplina de Programação para Web 3 do curso de Bacharelado em Engenharia de Software

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

### Cascatas
- Expedição --> Coletas científicas (cascade = CascadeType.ALL): As coletas científicas têm ciclo de vida totalmente dependente da expedição onde foram efetuadas.
- Expedição --> Utilizações (cascade = CascadeType.ALL) : Trata-se de uma entidade de movimentação/histórico. Ao remover uma expedição, o histórico de utilização de equipamentos vinculado a ela deve ser limpo
- Expedicao --> PlanoSeguranca (cascade = CascadeType.ALL) : Plano de segurança possue composição e ciclo de vida estreitamente atrelados à expedição
- Expedicao --> AutorizacaoAmbiental (cascade = CascadeType.ALL) : Autorização Ambiental possue composição e ciclo de vida estreitamente atrelados à expedição
- Expedicao --> Relatorio (cascade = CascadeType.ALL) : Relatório possue composição e ciclo de vida estreitamente atrelados à expedição
- Caverna --> SetorPesquisa (cascade = CascadeType.ALL) : Um setor não possui significado ou utilidade sem estar atrelado a uma caverna.

### OrphanRemoval
- Expedicao --> PlanoSeguranca (orphanRemoval = true): Permite a substituição ou desvinculação do plano na expedição (expedicao.setPlanoSeguranca(null)), garantindo que o registro do plano antigo seja deletado fisicamente do PostgreSQL em vez de permanecer como dado órfão no banco.
- Expedicao --> AutorizacaoAmbiental (orphanRemoval = true) : Garante que o cancelamento ou substituição da autorização desvincule e remova o documento obsoleto da tabela correspondente.
- Expedicao --> Relatorio (orphanRemoval = true) : Permite que, ao descartar ou reemitir o relatório final associado a uma expedição em memória Java, o Hibernate execute a exclusão física do relatório no banco de dados.
- Expedição --> Coletas científicas (orphanRemoval = true) : Garante que a remoção de um item de movimentação diretamente da coleção Java dispare a exclusão física da linha na respectiva tabela do banco de dados.
- Expedição --> Utilizações (orphanRemoval = true) : Garante que a remoção de um item de movimentação diretamente da coleção Java dispare a exclusão física da linha na respectiva tabela do banco de dados.
- Caverna --> SetorPesquisa (orphanRemoval = true) : Garante que se uma caverna for delatada todos os seus setores são apagados e se um setor for retirado da lista da caverna, ele seja removido fisicamente do banco de dados.

## Fetch
- Relacionamentos @OneToOne e @ManyToOne: Todos os relacionamentos de entidade única foram explicitamente definidos como FetchType.LAZY (ex: Caverna, PlanoSeguranca, Relatorio). Essa prática substitui o padrão default EAGER do JPA para esses mapeamentos, evitando o problema de consultas N+1 e carregamentos desnecessários em memória ao listar expedições.
- Coleções @OneToMany: Mantidos com o padrão intrínseco LAZY.
- Otimização Especial de Mídia/BLOB: Os campos binários e a lista de utilizações em Equipamento foram configurados como LAZY. Isso assegura que arquivos binários pesados e o histórico de movimentações dos equipamentos não sejam trazidos do banco durante buscas simples do sistema.