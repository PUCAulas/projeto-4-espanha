# Instruções de Uso

O sistema desenvolvido neste projeto tem como objetivo gerir informações sobre livros, teses, revistas, CDs e DVDs. O projeto faz uso dos conceitos da programação modular aprendidos na disciplina, incluindo heranças, interfaces, modularização, classes abstratas e testes. 

## Menu Principal

Ao iniciar o sistema, você terá seis opções:

1. **Cadastros:** Vai para o menu de cadastro, para cadastrar um novo item. Para acessar esta opção, digite `1`.
2. **Edições:** Vai para o menu de edição, para editar um item existente. Para acessar esta opção, digite `2`.
3. **Pesquisas:** Vai para o menu de pesquisas, para pesquisar um item existente. Para acessar esta opção, digite `3`.
4. **Empréstimos:** Vai para o menu de empréstimos, para emprestar um item existente. Para acessar esta opção, digite `4`.
5. **Gerenciar Usuários:**: Vai para o menu gerenciar usuários, para cadastrar, listar e editar os usuários. Para acessar esta opção, digite `5`.
6. **Sair do Sistema:** Para sair do sistema, digite `6`.

## Menu Cadastros
1. **Cadastrar Livro:** Solicita o título, o autor, o ano de publicação e o número de exemplares disponíveis, e cadastra o livro. Para acessar esta opção, digite `1`.
2. **Cadastrar Revista:** Solicita o título, o autor, o ano de publicação e o número de exemplares disponíveis, e cadastra a revista. Para acessar esta opção, digite `2`.
3. **Cadastrar Tese:** Solicita o título, o autor, o ano de publicação e o número de exemplares disponíveis, e cadastra a tese. Para acessar esta opção, digite `3`.
4. **Cadastrar CD:** Solicita o título, o autor, o ano de publicação e o número de exemplares disponíveis, e cadastra o CD. Para acessar esta opção, digite `4`.
5. **Cadastrar DVD:** Solicita o título, o autor, o ano de publicação e o número de exemplares disponíveis, e cadastra o DVD. Para acessar esta opção, digite `5`.
6. **Voltar:** Para voltar ao menu principal do sistema, digite `6`.

## Menu Edições
1. **Editar Livro:** Solicita o título do livro que deseja editar, e, se o título já foi cadastrado, é solicitado o novo título, o novo autor, o novo ano de publicação, e o novo número de exemplares disponíveis, e o título é editado. Para acessar esta opção, digite `1`.
2. **Editar Revista:** Solicita o título da revista que deseja editar, e, se o título já foi cadastrado, é solicitado o novo título, o novo autor, o novo ano de publicação, e o novo número de exemplares disponíveis, e o título é editado. Para acessar esta opção, digite `2`.
3. **Editar Tese:** Solicita o título da tese que deseja editar, e, se o título já foi cadastrado, é solicitado o novo título, o novo autor, o novo ano de publicação, e o novo número de exemplares disponíveis, e o título é editado. Para acessar esta opção, digite `3`.
4. **Editar CD:** Solicita o título do CD que deseja editar, e, se o título já foi cadastrado, é solicitado o novo título, o novo autor, o novo ano de publicação, e o novo número de exemplares disponíveis, e o título é editado. Para acessar esta opção, digite `4`.
5. **Editar DVD:** Solicita o título do DVD que deseja editar, e, se o título já foi cadastrado, é solicitado o novo título, o novo autor, o novo ano de publicação, e o novo número de exemplares disponíveis, e o título é editado. Para acessar esta opção, digite `5`.
6. **Voltar:** Para voltar ao menu principal do sistema, digite `6`.
   
## Menu Pesquisas
1. **Pesquisar Por Título:** Solicita o título da obra a ser pesquisada e lista todos os dados cadastrados do item. Para acessar esta opção, digite `1`.
2. **Pesquisar Por Autor:** Solicita o nome do autor que terá os itens pesquisados e lista todos os itens e seus dados cadastrados. Para acessar esta opção, digite `2`.
3. **Pesquisar Por Ano:** Solicita o ano de publicação a ser pesquisado e lista todos os itens que possuem o ano de publicação pesquisado e seus dados cadastrados. Para acessar esta opção, digite `3`.
4. **Pesquisar Por Tipo:** Abre o menu pesquisa por tipo. Para acessar esta opção, digite `4`.
   ### Menu Pesquisa Por Tipo
   1. **Pesquisar Livros:** Lista todos os livros e suas informações cadastradas. Para acessar esta opção, digite `1`. 
   2. **Pesquisar Revistas:** Lista todas as revistas e suas informações cadastradas. Para acessar esta opção, digite `2`.
   3. **Pesquisar Teses:** Lista todas as teses e suas informações cadastradas. Para acessar esta opção, digite `3`.
   4. **Pesquisar CDs:** Lista todos os CDs e suas informações cadastradas. Para acessar esta opção, digite `4`.
   5. **Pesquisar DVDs:** Lista todos os DVDs e suas informações cadastradas. Para acessar esta opção, digite `5`.
   6. **Voltar:** Para voltar para o menu pesquisas, digite `6`.
   7. **Voltar:** Para voltar para o menu principal do sistema, digite `5`
   
## Menu Empréstimos
1. **Realizar Empréstimo:** Solicita o ID do usuário que irá realizar o empréstimo, e o título e o tipo do item (Livro, CD ou DVD) a ser emprestado, se o usuário não tiver atingido o limite de 3 empréstimos simultâneos atingido, tal empréstimo é realizado. Para acessar esta opção, digite `1`.
2. **Realizar Devolução:** Solicita o ID do usuário que irá devolver o item emprestado, e o título e o tipo do item (Livro, CD ou DVD) a ser devolvido, se o usuário tiver com o empréstimo pendente a devolução, o item é devolvido. Para acessar esta opção, digite `2`.
3. **Relatório de Emprestimos:** Cria um relatório listando todos os empréstimos já realizados. Para acessar esta opção, digite `3`.
4. **Relatório de Emprestimos Por Usuário:** Solicita o ID do usuário e cria um relatório listando todos os empréstimos já realizados por tal usuário. Para acessar esta opção, digite `4`.
5. **Voltar:** Para voltar ao menu principal do sistema, digite `5`.

## Menu Gerenciar Usuários
1. **Cadastrar Usuário:** Solicita ao usuário o seu nome, CPF, o curso em que está matriculado e duas categorias de interesse, e o cadastra como novo usuário. Para acessar esta opção, digite `1`.
2. **Listar Usuário:** Lista todos os usuários, bem como seus dados, já cadastrados. Para acessar esta opção, digite `2`.
3. **Editar Usuário:** Solicita ID do usuário que deseja editar, e o novo nome, CPF, curso e/ou categorias de interesse a ser editado, e edita o usuário relativo ao ID. Para acessar esta opção, digite `3`.
4. **Recomendação Usuario:** Solicita ID do usuário que deseja uma recomendação, e em seguida exibe a recomendação de 3 itens que se
relacionam com os interesses do usuário, ranqueados do mais interessante para o menos interessante para o usuário. Para acessar esta opção, digite `4`.
5. **Voltar:** Para voltar ao menu principal do sistema, digite `5`.
