# MasterMind Game
A primeira versão ainda lida com a ordem de forma aleatória e não lida com repetição do alfabeto nas tentativas. O algoritmo tenta resolver o jogo ao guardar sempre o melhor estado, sendo este a tentativa que obteve mais elementos na posição certa ou apenas corretos. A partir disso, são feitas tentativas aleatórias com base nesse estado, analisadas cada tentativa, e caso a tentativa tenha sido melhor (mais acertos) que o melhor estado, atualizamos o melhor estado até descobrir todos os elementos da resposta. Depois de descobertos todos os elementos da resposta, trocamos a ordem de forma aleatória até acertar.

Exemplo:
Answer: 15378
Primeira Tentativa: 01234
Análise: 2 corretos (posição ou apenas estão lá)
Melhor estado: 01234 (Inicialização)
Nova tentativa: 
Pega 2 aleatoriamente do melhor estado e coloca no inicio
32 
Pega o restante aleatoriamento do alfabeto sem repetir
32 586
Análise: 3 corretos (posição ou apenas estão lá)
Melhor estado: 32586
Nova tentativa: 
Pega 3 aleatoriamente do melhor estado e coloca no inicio
683 
Pega o restante aleatoriamento do alfabeto sem repetir
683 10
Análise: 3 corretos (posição ou apenas estão lá)
Melhor estado: 32586 (Permanece o mesmo)
E repete até achar 5 corretos
Depois só troca ordem aleatoriamente até acertar
