
# Sistema de Gerenciamento de Produtos com Spring

O projeto em questão é um Sistema de Gerenciamento de Produtos desenvolvido em Java, utilizando o framework Spring. A aplicação consiste em uma API RESTful para realizar operações básicas relacionadas a produtos, tais como criação, leitura, atualização e exclusão (CRUD). O projeto adere a boas práticas de desenvolvimento Spring, fazendo uso de anotações para simplificar a configuração e a implementação de funcionalidades RESTful. Adicionalmente, incorpora o padrão DTO para a transferência eficiente de dados e HATEOAS para enriquecer a API com links relacionados.

Entretanto, é importante notar que esta é uma implementação básica. Em futuras atualizações, serão incorporadas práticas adicionais, incluindo segurança com Spring Security, testes automatizados e a separação de serviços.

Como diziam os antigos em latim: 'Ex nihilo nihil fit' - Nada surge do nada. Esta implementação foi adaptada a partir das aulas da professora [Michelli Brito](https://www.youtube.com/@MichelliBrito).


## Classes Principais:

#### ProductController:
Responsável por gerenciar as requisições HTTP relacionadas aos produtos.
Utiliza a anotação `@RestController` para indicar que se trata de uma API REST.
Contém métodos para criar (`saveProduct`), listar todos (`getAllProducts`),
obter um produto específico (`getOneProduct`), atualizar (`updateProduct)`, e excluir (`deleteProduct`).
Utiliza o padrão HATEOAS para incluir links relacionados aos recursos. 

#### ProductRecordDto:
Uma classe DTO (Data Transfer Object) que representa os dados de um produto.
Utiliza a funcionalidade de records do Java, proporcionando uma forma concisa de 
criar classes imutáveis com métodos padrão como getters, toString, equals, e construtor.

#### ProductModel:
Representa a entidade de Produto no banco de dados, mapeada com a anotação `@Entity`.
Estende a classe RepresentationModel do Spring HATEOAS para suportar a criação de links.
Possui atributos como idProduct (identificador único do produto), name (nome do produto) e value (valor do produto).
Utiliza anotações como `@Id`, `@GeneratedValue`, e `@Table` para configuração da persistência no banco de dados.

#### MarketApplication:
Classe principal do projeto, responsável por iniciar a aplicação Spring Boot.
Utiliza a anotação `@SpringBootApplication` para configurar automaticamente a aplicação Spring.
O método main inicia a aplicação quando executado.

## Funcionalidades Principais:

#### Cadastro de Produto: 
> Através do método saveProduct na classe ProductController, é possível cadastrar um novo produto na aplicação.
#### Listagem de Produtos: 
> O método getAllProducts retorna a lista de todos os produtos cadastrados, incluindo links para cada recurso individual.
#### Consulta de Produto: 
> Utilizando o método getOneProduct, é possível obter detalhes de um produto específico com base em seu ID.
#### Atualização de Produto: 
> O método updateProduct permite a atualização dos dados de um produto existente.
#### Exclusão de Produto: 
> Através do método deleteProduct, é possível excluir um produto do sistema.


