# desafio-wayon
Desafio para vaga desenvolvedor Java

#Questions: 

1 - Write a simple code to solve the following problem: Given a group of letters, print all existing anagram. For example: a,b,c} => abc; acb; bac; bca; cab; cba

R = Esta na aplicação a parte deste codigo no package anagram, juntamente com classe de testes.

2 - Describe a situation that you would override the method equals() from Object class

R = O método equals() da classe Object compara referências de memória, ou seja, verifica se dois objetos são exatamente a mesma instância. Em muitos casos, isso não é suficiente, principalmente quando você quer comparar objetos com base em seus atributos (valores) e não na identidade. Eu coloquei este exemplo em codigo na aplicação também dentro do package (equalshascode).

3 - Which design pattern would you use to protect your code from a third party library, which will likely to be replaced in a couple of years..

R =  Bom pensando em um desacoplamento e olhando para um padrão de projeto, podemos colocar um exemplo como o Adapter neste caso, onde o adapter ele atua como uma camada intermediária entre seu codigo e a biblioteca externa. No caso você define uma interface própria, onde sua aplicação consome, e após implementa um adaptador que traduz as chamadas dessa interface para os métodos da biblioteca  - muito usado em (Arquiteura Hexagonal). Eu poderia citar as vantagens: 

=> Desacomplamento, onde código principal não conhece a biblioteca diretamente.
=> A facilidade, de apenas criar outro adaptador para nova biblioteca.
=> Não podendo e nem menos mencionar a testabilidade de criação de mocks e stubs nos testes.

**Observação**
Podemos ter uma limitação onde se a biblioteca muda frequentemente, o adaptador pode precisar de uma manutenção contínua.

*** O exemplo de codigo está na aplicação e vou explicar basicamente o que foi feito, em cima da resposta supracitada. ***

No caso implementei um cenário basico de envio de e-mail, onde se eu tenho uma biblioteca de terceiros (fictícia) chamada ThirdPartyEmailSender que envia os e-mails. Para manter o codigo desacoplado, eu teria que criar uma interface própria e um adaptador. Em resultado se eu trocar ThirdPartyEmailSender por outro provedor(adapter), eu só precisaria trocar o adaptador.

4- Describe what you know about Angular and provide some code and applications of use examples.

R = Tenho trabalhado com Angular por 6 anos, onde minhas experiências sempre atuei como desenvolvedor FullStack, atuando tanto na parte de backend quando front, trazendo uma bagagem na parte de front desde Angularjs ate Angular +15, e tenho bastante experiência também na parte de React e React Native(Mobile). Minha experiência inclui uso intensivo de recursos principais do Angular, como : 

=> Componetização
=> Data Binding(one-way e two-way)
=> Comunicação entre os componentes pai e filho (Input/Output e Services)
=> Injeação de dependências a partir do constructor.
=> Rotas com RouteModule
=> Consumo de Apis e Rest e integração de sistemas, entre backend e front.
=> Criação de formulários reativos(ReactForms)
=> A parte de estilização da aplicação utilizando o proprio Angular Material para construção de interfaces ricas e responsivas.

*** Observação ***
==> Foi criando umma pequena aplicação em Angular para explicar como funciona  um recurso chave, e comunicação entre os componentes, além da construção do template(Perfumaria). Segue o link do github do projeto:

() => https://github.com/douglas2363/desafio-wayon-angular/tree/master

5 -  In web development, how do you prevent SQL injection attacks?

R = Para previnir ataques de injeção de SQL em aplicações web, recomneda-se seguir estratégias que são fundamentais como o uso de consultas parametrizadas, ORMs(Object-Relational Mapping) e validação/escapamento de entrada do usuário. Além adotar também boas práticas de segurança na configuração da base de dados. Temos ORMs como próprio Hibernate em java, Sequelize(Node.js), que oferecem camadas de abstração que ajudam previnir injeções SQL, pois geram as consultas de forma segura. Exemplo em codigo : 

Query query = session.createQuery("FROM Usuario WHERE email = :email");
query.setParameter("email", email);
Usuario usuario = (Usuario) query.uniqueResult();

Podemos antes de passar os dados também, aplicar validações para garantir que os dados estejam no formato esperado no caso de email,  utilizando bibliotecas de validação como => Bean Validation, @Valid e etc. Fiz em codigo, esta no projeto, no package seguranca.orm

6 - Let’s suppose you have a batch process that communicates with database, executes some internal logic and saves a file in a FTP server. User is complaining that this process is taking a long time to execute. How would you proceed to solve the problem?

R = Primeiramente é interessante você entender o fluxo do processo antes de tudo, para ter uma analise sólida de como começar por exemplo:

-> Quais são as entradas e saídas do processo ? 
-> Com que frequência ele roda ? 
-> Qual o volume de dados processados?
-> Onde ocorrem os maiores tempos de execução? 

Então tendo essa analise e entendimento do fluxo, pode-se já ter o entendimento por onde começar... No caso temos que identificar os gargalos dentro de um fluxo de processo e aplicar técnicas e utilizar ferramentas. Vamos colocar  técnicas para ficar mais claro por exemplo temos :

Logs com timestamps detalhados, adicionando marcações de inicio e fim para cada etapa que seira (Consulta, processamento, envio FTP)onde podemos ter uma ideia e medir os tempos reais. Temos que ter ideia que precisamos de monitoração então cabe inserirmos aqui um monitoramento de recursos, onde verifica a CPU, memória, disco e rede, durante uma execução de thread de processos por exemplo tendo em vista ferramentas como htop, iotop, ou o mais utilizado hoje no mercado por grande empresas Grafana, Prometheus, GrayLog e DataDog.
Além temos que ter em mente quando se fala de consultas e banco, onde temos analise das depedências externas que precisamos dentro de uma aplicação, onde precisamos verificar o tempo de resposta de queris SQL, e de envio/recebimento de arquivos FTP.

Então podemos citar varias coisas dentro desse contexto quando nos referimos um diagnostico de processos ou threads de processo onde temos varios maneiras  de otimizar e analisar olhando para otimização de consultas ao banco de dados, melhoria na logica de execução, teste e monitoramento contínuos.
Um bom exemplo real que podemos colocar aqui seria:

Vamos imaginar que  lote demore 40 minutos, então vamos analisar o por que esse tempo todo: 
Temos uma query a principal do sistema que assim, carrega todos os dados principais da aplicação, o que sobrecarrega pq leva 20 minutos, então uma estratégia seria otimizar com novos índices, que resultaria em uma grande redução de tempo para 5 minutos.
Tivemos uma logica de loop por exemplo paralelizada, onde reduziu de 10 para 3 minnutos.
Todos os arquivos FTP enormes, foram comprimidos, onde o tempo de envio caiu de 10 para 2 minutos. Então esse seria um grande exemplo e um cenário, onde temos toda analise e ferramentas necessárias para uma otimização e estratégia para diagnosticar e aplicar a solução para gerir uma aplicação e processos com seguranaça , escalabilidade e disponibilidade.

Em codigo citando so um exemplo podemos fazer uma melhoria de lógica de processamento, por exemplo: 

Utiliza Spring Batch para dividir o processo em steps(Leitura, processamento, escrita), com possibilidades de: 

Chunk processing(ex: chunk(100));
Paralelismo com TaskExecutor, que nos permite processar múltiplos chunks simultaneamente.
E o principal para falhas temos o Retry, e Skip para falhas temporárias:

Agora cito um exemplo deste paralelismo:

step("processStep")
.chunk(100)
.reader(itemReader())
.processor(itemProcessor())
.writer(itemWriter())
.taskExecutor(new SimpleAsyncTaskExecutor())
.throttleLimit(4) // até 4 threads paralelas

Com isso o tempo total da execução do processo é reduzido significativamente, especialmente em cenários com grande volume de dados. Cada thread processa um chunk de 100 itens de forma assíncrona, permitindo maior aproveitamento da CPU e melhor escalabilidade.



![image](https://github.com/user-attachments/assets/c7d85045-252e-45b5-a6ea-e251568863a0)

7  - Given the tables above, write the SQL query that:
a. Returns the names of all Salesperson that don’t have any order with Samsonic.
b. Updates the names of Salesperson that have 2 or more orders. It’s necessary to add an ‘*’ in the end of the name
c. Deletes all Salesperson that placed orders to the city of Jackson.
d. TThe total sales amount for each Salesperson. If the salesperson hasn’t sold anything, show zero.

a) 
SELECT s.Name
FROM Salesperson s
WHERE s.ID NOT IN (
    SELECT o.salesperson_id
    FROM Orders o
    JOIN Customer c ON o.customer_id = c.ID
    WHERE c.Name = 'Samsonic'
);

b)
UPDATE Salesperson
SET Name = Name || '*'
WHERE ID IN (
    SELECT salesperson_id
    FROM Orders
    GROUP BY salesperson_id
    HAVING COUNT(*) >= 2
);

c)
DELETE FROM Salesperson
WHERE ID IN (
    SELECT DISTINCT o.salesperson_id
    FROM Orders o
    JOIN Customer c ON o.customer_id = c.ID
    WHERE c.City = 'Jackson'
);

d)
SELECT s.Name, COALESCE(SUM(o.Amount), 0) AS TotalSales
FROM Salesperson s
LEFT JOIN Orders o ON s.ID = o.salesperson_id
GROUP BY s.ID, s.Name;


8  - The customer has a system called XYZ and he wants to start some updates that will be split into 3 phases. The requirements for the first phase are the following:
I want to start new data entries in the system (new information that will be used in the 2nd phase)
•
I want to include, update, delete and search plants
•
Plants can contain code (only number) and description (10 digits)
•
Only code is mandatory;
•
No plant can have the same code;
•
Only admin users can delete plants;


** Observação **
==> Implemente o codigo dentro da aplicação também como exemplo dentro do package plant.management <==

 1. História de Usuário

Título: Gerenciamento de Plantas no Sistema XYZ
Como administrador ou usuário do sistema
Quero poder criar, atualizar, excluir (apenas admin) e pesquisar plantas
Para que o sistema possa gerenciar essas entidades como base para as fases futuras

1 - Critérios de Aceitação:

=> Código da planta deve ser único, numérico e obrigatório.

=> Descrição é opcional, com até 10 caracteres

=> penas administradores podem excluir plantas

=> Não permitir duplicação de códigos

2 - Regras de Negócio e Validações

=> Código é obrigatório, numérico e único

=> Descrição é opcional, até 10 caracteres

=> Apenas administradores podem excluir plantas

=> Pesquisa deve permitir busca por código ou descrição

3 -  Segurança

=> A autenticação deve identificar se o usuário tem o papel de “ADMIN”

=> Excluir planta só é permitido se user.hasRole("ADMIN")

=> Validações feitas tanto no frontend (se aplicável) quanto no backend


4 - Casos de Teste

![image](https://github.com/user-attachments/assets/f62742f2-3f55-4264-8ae2-883e0084a940)



9  - Read the following description of a system functionality:
User Registration
-
Screen where you can insert, delete or update user information;
-
User has properties “name”, “email”, “address” and “phone”. Only the first two are mandatory;
-
No user can have the same email;
-
Only admin users can delete other users;
DS 1.1
Describe which tests you would do to guarantee that everything is working correctly. (For example: insert user with every property filled)

R = Testes de Inserção de Usuário

Inserir usuário com todos os campos preenchidos corretamente
 ->  Esperado: Usuário cadastrado com sucesso.

Inserir usuário apenas com nome e email (campos obrigatórios)
 -> Esperado: Usuário cadastrado com sucesso.

==> Tentar inserir usuário sem nome
 -> Esperado: Erro de validação ("Nome é obrigatório").

Tentar inserir usuário sem email
 ->  Esperado: Erro de validação ("Email é obrigatório").

Tentar inserir usuário com email já existente no sistema
 ->  Esperado: Erro informando "Email já cadastrado".

==> Testes de Atualização de Usuário
Atualizar nome, endereço ou telefone de um usuário existente
 -> Esperado: Dados atualizados corretamente.

 ==> Testes de Atualização de Usuário
 -> Atualizar nome, endereço ou telefone de um usuário existente
 -> Esperado: Dados atualizados corretamente.

==> Testes de Atualização de Usuário
Atualizar nome, endereço ou telefone de um usuário existente
->  Esperado: Dados atualizados corretamente.
Atualizar o email para um que já existe em outro usuário
-> Esperado: Erro de duplicidade de email.

Testes de Exclusão de Usuário
 -> Usuário administrador exclui outro usuário
 -> Esperado: Usuário removido com sucesso.

Usuário comum tenta excluir outro usuário
-> Esperado: Erro de permissão ("Apenas administradores podem excluir usuários").

Usuário tenta excluir a si mesmo (dependendo da regra de negócio)
-> Esperado: Deve permitir ou negar, conforme definido no sistema.

Testes de Permissões e Restrições
 -> Verificar se somente usuários autenticados conseguem acessar a tela de cadastro
 -> Esperado: Tela bloqueada para não autenticados.

-> Verificar se botões de exclusão aparecem apenas para administradores
-> Esperado: Usuários comuns não visualizam botão de deletar.

*** Obervação *** 
===> Os Exemplos de teste unitário eu coloquei dentro da aplicação onde montei um pacote para ele (cadastro.usuario) e a parte de teste unitários dele <===













