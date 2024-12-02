# language: pt
Funcionalidade: API - Produtos

  Cenário: Registrar um novo produto
    Quando submeter o registro de um novo produto
    Então o produto deve ser registrado com sucesso

  Cenário: Consulta de uma categoria invalida
    Quando submeter uma consulta por uma categoria inexistente ou invalida
    Entao deve apresentar o response code  400