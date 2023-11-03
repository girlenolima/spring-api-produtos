package br.com.leno.market.repositories;

import br.com.leno.market.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


/*
* @Component para classe mais generics.
* @Service para classe de serviços e regras de negocios.
* @Repository para tipo transaçoes com base de dados
* @Controller para classes de controller de acessos com endpoints
*
* Lembrando que no final das contas tudo vira um @Component,
* esses outros @ é para por convençao e leitura
*
* A inteface JpaRepository ja vem com varios metodos prontos,
* muitas vezes nao sendo necessario criar outros.
* */


/*Em interfaces que extends JpaRepository a marcaçao @Repository é opcional*/

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
