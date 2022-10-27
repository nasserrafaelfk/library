# Alunos
- Nasser Rafael França Kilesse | 20193018051
- Alex Meireles dos Santos Almeida | 20193020407
- Darmes Araújo Dias | 20193020480

# Conteúdo

1. [Sobre](#sobre)
2. [O que é Domain Drive Design](#o-que-é-domain-driven-design)
    <br>2.1 [Domínio](#domínio)
    <br>2.2 [Pilares](#pilares)
    <br>2.3 [Design Estratégico e Tático](#design-estratégico-e-tático)
3. [Como rodar](#como-rodar)
4. [Referências](#referências)

## Sobre

Esse é um projeto de uma livraria, motivada por regras de negócio reais.
Foram utilizadas técnicas fortemente conectadas com DDD, BDD, ES e USM.

## O que é Domain Driven Design
DDD ou domain driven design que traduzindo é o design guiado pelo domínio é uma abordagem para modelagem de software que centraliza o desenvolvimento na criação de um modelo de domínio ou seja a criação da modelagem de software baseada em implementar modelos que refletem as competências da organização a complexidade do negócio. Muito se fala dessa abordagem que muitas vezes é confundida com uma tecnologia como se fosse um framework ou como uma arquitetura.
O Domain Driven Design surgiu através de um livro “Domain Driven Design: Atacando As Complexidades No Coração Do Software” que foi lançado em 2003 por Eric Evans. O DDD oferece ferramentas de modelagem estratégica e tática para entregar um software de alta qualidade com o objetivo de acelerar o desenvolvimento de aplicações que lidam com processos de negócios complexos. Ela não é uma tecnologia ou uma metodologia, então pode ser utilizada independente da linguagem, não importa se C# ou Java. O DDD também não é arquitetura em camadas e não impõe processos rígidos ao time, em seus princípios o DDD é sobre discussão escuta e compreensão, todo um esforço para centralizar o conhecimento transformando ele em modelos de domínio em softwares que irão de verdade atender às necessidades e expectativas dos usuários. O próprio Eric Evans cita 2 frases bem importante sobre esse princípio: “Se os programadores não estão interessados no domínio eles aprendem apenas o que a aplicação deve fazer e não os princípios por trás delas. Software útil pode até ser desenvolvido dessa maneira mas o projeto nunca chegará em um ponto onde novas funcionalidades poderosas surgiram como de desdobramento de funcionalidades existente”. Agora vamos explicar cada parte do nome dessa abordagem de modelagem de software.

### Domínio

O domínio é um negócio da sua empresa ou o assunto do seu projeto existe ainda o Core Domain que é a principal parte de um domínio. Eric deixa bem claro sua preocupação no entendimento do negócio, e diz que entender o negócio é o único meio de implementar o DDD em um projeto. Nessa implementação existem basicamente 2 papéis: o time de desenvolvimento e os domain experts: esses são os especialistas no negócio. Por exemplo, se estamos criando uma aplicação para auxiliar um escritório de advocacia, os advogados seriam os nossos domain experts. Eles seriam capazes de guiar a equipe para desenvolver um bom software tirando dúvidas, definindo regras, processos e termos. Sendo assim o desenvolvimento com DDD é uma jornada de descobertas, todos aprendem, os devs com os domain experts e os domain experts com os devs.

### Pilares
#### Linguagem Ubíqua

Falando em termos, esses são um dos pilares do DDD. Se especialistas da área e desenvolvedores devem trabalhar em conjunto, é preciso que ambos falem a mesma língua, surge então a linguagem ubíqua utilizada por toda a equipe do projeto. Nós desenvolvedores pensamos em classes, métodos, componentes, quais objetos criar, qual o relacionamento entre eles, pensamos em herança, polimorfismo, e por aí vai. Já os domain experts não entendem nada disso. Eles são especialistas no negócio, conhecem e sabem como a empresa deve funcionar, só que por sua vez também falam um bando de termos que nós não fazemos ideia do que se trata. Por isso a linguagem ubíqua é uma linguagem da equipe, compartilhada e principalmente entendida por todos; durante o projeto ela também vai evoluindo.

#### Bounded Context

Outro pilar é o Bounded Context, a delimitação do contexto. Ela é o limite conceitual, uma vez que todo domínio é colocado em um modelo, ela pode se tornar complexo demais, e o Bounded Context é justamente onde ela irá delimitar esses contextos baseados nas intenções de negócios. Cada Bounded Context poderá ter a sua linguagem ubíqua, a sua abordagem de arquitetura, de armazenamento de dados, e até a sua própria equipe de desenvolvimento. Para criar esse modelo e entender como os bounded contexts se relacionam e como eles se comunicam, é utilizado Context Mapping, um modelo que vai nos dar a visão geral do software. Existem alguns padrões de relacionamento entre os Bounded Context e eles implicam diretamente na estratégia de como os times do projeto irão trabalhar, na dependência entre eles, na arquitetura, e até na estratégia de comunicação. Os principais tipos de relacionamentos entre o contextos são:
- <b>Shared Kernel</b>: Quando vários Bounded Context se compartilham de um mesmo domínio. Alterar ele significa que todas as equipes serão afetadas
- <b>Customer Supply Development</b>: Quando existe um relacionamento cliente fornecedor chamado no DDD de downstream e upstream. Significa que a equipe upstream pode ter êxito interdependente da equipe downstream, ou seja, um contexto é o fornecedor do serviço e o outro é o consumidor. Tecnicamente as equipes irão definir testes de aceitação automatizados, que irão validar as interfaces que a equipe upstream fornece. Com isso a equipe de fornecedores poderá fazer as modificações sem se preocupar em quebrar o sistema.
- <b>Conformist</b>: Nesse relacionamento o Bounded Context downstream está conformado que o modelo upstream não atende suas necessidades, e o pior, que as modificações realizadas por ele irão impactar diretamente em seu contexto, mas ele aceita o fato e se vira com o que tem ficando aí atento a todas as mudanças realizadas.
- <b>Partner</b>: São aqueles Bounded Context que são parceiros, estão unidos por uma dependência mútua e deverão trabalhar em conjunto.
- <b>Anticorruption Layer</b>: Nesse relacionamento a equipe downstream decide criar uma camada para proteger o seu contexto das modificações do upstream, é muito comum ser encontrado em sistemas legados. 

### Design Estratégico e Tático

Outros dois termos bem utilizados quando falamos de domain driven design são o design estratégico e o design tático. O design estratégico, ou modelagem estratégica, é tudo o que já falamos, na verdade é bem difícil imaginar a implementação do DDD sem aplicar os conceitos da modelagem estratégica. Vaughn Vernon, outro grande autor do assunto, disse que o design estratégico é como fazer o rascunho antes de entrar nos detalhes da implementação. Ele destaca o que é estrategicamente importante para o negócio, como dividir o trabalho por importância, e como fazer integrações da melhor maneira. Já o design tático, ou a modelagem tática, diz respeito à implementação e usa o domain model pattern, uma boa uma abordagem que diz como escrever as classes que irão mapear os modelos do mundo real e implementar os comportamentos do negócio. Existem também alguns padrões na modelagem tática como por exemplo o entities e o value objects, que definem os conceitos do domínio, o domain service que assume responsabilidades que não se encaixam em outros projetos, aggregates que define fronteiras entre objetos, e o factories e repositories, que lida com a criação e armazenamento de objetos.

## Como rodar

### Requerimentos

* Java 11
* Maven

### Quickstart

Você pode rodar o app da livraria digitando o seguinte:

```console
$ mvn spring-boot:run
...
...
2019-04-03 15:55:39.162  INFO 18957 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 2 endpoint(s) beneath base path '/actuator'
2019-04-03 15:55:39.425  INFO 18957 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2019-04-03 15:55:39.428  INFO 18957 --- [           main] io.pillopl.library.LibraryApplication    : Started LibraryApplication in 5.999 seconds (JVM running for 23.018)

```

### Build do pacote Jar

Você pode buildar um jar com o maven fazendo o seguinte:

```console
$ mvn clean package
...
...
[INFO] Building jar: /home/pczarkowski/development/spring/library/target/library-0.0.1-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

### Build com Docker

Se você já buildou o arquivo jar, rode:

```console
docker build -t spring/library .
```

De outra forma, você pode buildar o arquivo jar utilizando um dockerfile multi-estado:

```console
docker build -t spring/library -f Dockerfile.build .
```

De qualquer forma, uma vez buildado você pode rodá-lo da seguinte maneira:

```console
$ docker run -ti --rm --name spring-library -p 8080:8080 spring/library
```

Por favor note que a aplicação será rodada com um perfil Spring `local` para iniciar alguns dados iniciais.

## Referências

1. [Introducing EventStorming](https://leanpub.com/introducing_eventstorming) by Alberto Brandolini
2. [Domain Modelling Made Functional](https://pragprog.com/book/swdddf/domain-modeling-made-functional) by Scott Wlaschin
3. [Software Architecture for Developers](https://softwarearchitecturefordevelopers.com) by Simon Brown
4. [Clean Architecture](https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164) by Robert C. Martin
5. [Domain-Driven Design: Tackling Complexity in the Heart of Software](https://www.amazon.com/Domain-Driven-Design-Tackling-Complexity-Software/dp/0321125215) by Eric Evans
