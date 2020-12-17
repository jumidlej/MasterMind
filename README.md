# Mastermind
O jogo “Mastermind” foi inventado por Mordechai Meirowitz e consiste de um tabuleiro com, normalmente, quatro furos grandes, onde se posicionam os pinos de código, além de, normalmente, quatro furos menores onde se posicionam os pinos de chave. Os pinos de código são coloridos, num conjunto finito de cores. Os pinos de chave são pretos ou brancos. O objetivo é descobrir uma senha secreta, composta de uma sequência não repetitiva de pinos de código, na base de tentativa e erro. Para cada tentativa o jogador recebe uma resposta através dos pinos de chave. Basicamente uma de três informações diferentes lhe são passadas a respeito de cada um dos seus pinos de código.

* a) O pino não faz parte da senha, e neste caso uma casa vazia é mostrada no espaço dos pinos de chave.
* b) O pino faz parte da senha, mas está numa posição incorreta, e neste caso um pino de chave branco é colocado em algum furo.
* c) O pino compõe a senha e está posicionado no seu local correto, e assim um pino de chave preto vai surgir como resposta.

Não há qualquer relação entre as posições ocupadas pelos pinos de chave e de código.

No programa que implementa o jogo temos um alfabeto composto por uma sequência de diferentes caracteres ASCII que representam os pinos de código e uma senha a ser descoberta. A cada tentativa realizada obtemos uma resposta composta por uma sequência de caracteres na qual cada caracter representa um dos itens a, b e c comentados acima. O alfabeto e tamanho da senha são escolhidos pelo usuário.

### Solução proposta
O algoritmo desenvolvido tenta resolver o jogo de descoberta de senha Mastermind ao guardar sempre o melhor estado e atualizá-lo, sendo o melhor estado a tentativa que obteve mais elementos na posição correta (melhor estado de posição) ou apenas existentes na resposta (melhor estado de elementos). A partir disso, são feitas novas tentativas parcialmente aleatórias com base nesse estado, as tentativas são analisadas, e caso a tentativa tenha tido mais acertos que o melhor estado, atualizamos o melhor estado. Repetimos até descobrir todos os elementos e posições da resposta correta. A descoberta dos elementos e das posições ocorre de maneira independente. Primeiro descobrimos os elementos e somente após o término dessa etapa tentamos descobrir as posições.

### Soluções disponíveis
* RANDOM: Tentativas aleatórias não repetitivas.
* CACHORRO: Armazenar o melhor estado, realizar novas tentativas com base nele e atualizá-lo quando for superado.

### Execução
No arquivo testParameters.txt selecionar:
* ALPHABET: Caracteres da senha.
* PUZZLESIZE: Tamanho da senha.
* SOLVER_TYPE: Algoritmo para solucionar a senha (RANDON/CACHORRO).

Dentro do diretório /bin executar:
* java facade.Facade (arquivo de parâmetros) (número de execuções)

Exemplo:
* java facade.Facade testParameters.txt 10

### Artigo: Agente Inteligente para Resolução do Jogo Mastermind
https://drive.google.com/file/d/1H6kTFiKmPqdJNLMWeYCEJDoojd4eXV71/view?usp=sharing
