# desafio-wayon
Desafio para vaga desenvolvedor Java

#Questions: 

Explain how you would use a design pattern to decouple your code from a third-party library that might be replaced in the future. Describe the advantages and limitations of your chosen approach, and provide a small code snippet illustrating its application.

R =  Bom pensando em um desacoplamento e olhando para um padrão de projeto, podemos colocar um exemplo como o Adapter neste caso, onde o adapter ele atua como uma camada intermediária entre seu codigo e a biblioteca externa. No caso você define uma interface própria, onde sua aplicação consome, e após implementa um adaptador que traduz as chamadas dessa interface para os métodos da biblioteca  - muito usado em (Arquiteura Hexagonal). Eu poderia citar as vantagens: 

=> Desacomplamento, onde código principal não conhece a biblioteca diretamente.
=> A facilidade, de apenas criar outro adaptador para nova biblioteca.
=> Não podendo e nem menos mencionar a testabilidade de criação de mocks e stubs nos testes.

**Observação**
Podemos ter uma limitação onde se a biblioteca muda frequentemente, o adaptador pode precisar de uma manutenção contínua.
