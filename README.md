# Mastermind Game
O algoritmo desenvolvido tenta resolver o jogo de descoberta de senha MasterMind ao guardar sempre o melhor estado e atualizá-lo, sendo o melhor estado a tentativa que obteve mais elementos na posição correta (melhor estado de posição) ou apenas existentes na resposta (melhor estado de elementos). A partir disso, são feitas tentativas parcialmente aleatórias com base nesse estado, as tentativas são analisadas, e caso a tentativa tenha sido melhor (tenha tido mais acertos) que o melhor estado, atualizamos o melhor estado e repetimos isso até descobrir todos os elementos e posições da resposta correta. A descoberta dos elementos e das posições ocorre de maneira independente. Primeiro descobrimos os elementos e somente após o término dessa etapa tentamos descobrir as posições.

### Soluções
* RANDOM: Tentativas aleatórias não repetitivas
* CACHORRO: Armazenar o melhor estado, realizar novas tentativas com base nele e atualizá-lo quando for superado.

### Execução
No arquivo testParameters.txt selecionar:
* ALPHABET: Caracteres da senha
* PUZZLESIZE: Tamanho da senha 
* SOLVER_TYPE: Algoritmo para solucionar a senha (RANDON/CACHORRO)
Dentro do diretório /bin executar:
* java facade.Facade (arquivo de parâmetros) (número de execuções)
Exemplo:
* java facade.Facade testParameters.txt 10

### Artigo: Agente Inteligente para Resolução do Jogo Mastermind
https://drive.google.com/file/d/1H6kTFiKmPqdJNLMWeYCEJDoojd4eXV71/view?usp=sharing