# Projeto para disciplina de Programação para Web 3 do curso de Bacharelado em Engenharia de Software

## Justificativa das escolhas feitas para o projeto
### Herança
    - A herança dos tipos de Pessoa foram configuradas com JOINED, ou seja, Existe a superclasse Pessoa e suas subClasses. Essa decisão foi tomada a partir da regra de negócio que deixa claro que novos tipos de Pessoa podem ser incluídos a qualquer momento, então a estratégia JOINED é a que melhor se aplica nesse cenário, mesmo que exija consultas JOIN.
    - Uma Single Table teria muitos valores nulos, além de exigir reestruturação da tabela caso novas subclasses fossem incluídas.
    - A estretégia Table per Concrete Class dificulta consultas polimórficas e manutenção de chaves estrangeiras, visto que é difícil garantir a integridade referencial (consultas não sabem para qual tipo de Pessoa apontar)

### Ownership das associações
    -

### Cascatas
    -

### OrphanRemoval
    - 

## Fetch
    -