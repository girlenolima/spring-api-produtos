package br.com.leno.market.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/*

* Record ja tem varios metodos prontos: getters, toString, equals, construtor...
* Sao imutaveis, por isso nao tem metodos setters
* Por default todos os atributos sao final e private
*
* */
public record ProductRecordDto(
  @NotBlank String name,
  @NotNull BigDecimal value
) {

}
