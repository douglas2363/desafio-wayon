# desafio-wayon
Desafio para vaga desenvolvedor Java

#Questions: 

2 - Explain how you would use a design pattern to decouple your code from a third-party library that might be replaced in the future. Describe the advantages and limitations of your chosen approach, and provide a small code snippet illustrating its application.

R =  Bom pensando em um desacoplamento e olhando para um padrão de projeto, podemos colocar um exemplo como o Adapter neste caso, onde o adapter ele atua como uma camada intermediária entre seu codigo e a biblioteca externa. No caso você define uma interface própria, onde sua aplicação consome, e após implementa um adaptador que traduz as chamadas dessa interface para os métodos da biblioteca  - muito usado em (Arquiteura Hexagonal). Eu poderia citar as vantagens: 

=> Desacomplamento, onde código principal não conhece a biblioteca diretamente.
=> A facilidade, de apenas criar outro adaptador para nova biblioteca.
=> Não podendo e nem menos mencionar a testabilidade de criação de mocks e stubs nos testes.

**Observação**
Podemos ter uma limitação onde se a biblioteca muda frequentemente, o adaptador pode precisar de uma manutenção contínua.

*** O exemplo de codigo está na aplicação e vou explicar basicamente o que foi feito, em cima da resposta supracitada. ***

No caso implementei um cenário basico de envio de e-mail, onde se eu tenho uma biblioteca de terceiros (fictícia) chamada ThirdPartyEmailSender que envia os e-mails. Para manter o codigo desacoplado, eu teria que criar uma interface própria e um adaptador. Em resultado se eu trocar ThirdPartyEmailSender por outro provedor(adapter), eu só precisaria trocar o adaptador.

3- Describe your experience with Angular, including its core features and use cases. Provide an example of a practical application where you used Angular and include a code snippet demonstrating a key feature, such as component communication, data binding, or service integration.

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

4- Discuss the techniques you use to prevent SQL injection attacks in web applications. Provide examples of code showing secure implementations, such as using parameterized queries or ORMs. Mention any additional measures you take to secure the database layer.

R = Para previnir ataques de injeção de SQL em aplicações web, recomneda-se seguir estratégias que são fundamentais como o uso de consultas parametrizadas, ORMs(Object-Relational Mapping) e validação/escapamento de entrada do usuário. Além adotar também boas práticas de segurança na configuração da base de dados. Temos ORMs como próprio Hibernate em java, Sequelize(Node.js), que oferecem camadas de abstração que ajudam previnir injeções SQL, pois geram as consultas de forma segura. Exemplo em codigo : 

Query query = session.createQuery("FROM Usuario WHERE email = :email");
query.setParameter("email", email);
Usuario usuario = (Usuario) query.uniqueResult();

Podemos antes de passar os dados também, aplicar validações para garantir que os dados estejam no formato esperado no caso de email,  utilizando bibliotecas de validação como => Bean Validation, @Valid e etc. Fiz em codigo, esta no projeto, no package seguranca.orm





