package br.com.leno.market.controllers;

import br.com.leno.market.dtos.ProductRecordDto;
import br.com.leno.market.models.ProductModel;
import br.com.leno.market.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/*
 * @RestController é derivada da @Controoler
 * Usa-se @RestController apenas para indentificar que é uma API Rest.
 * */
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    /*
     * @PostMapping("/products") , ele é derivado de @RequestMapping.
     * @RequestBody, injeta a dependencia.
     * @Valid, ativa as validaçoes do DTO.
     * ResponseEntity é usada para representar a resposta HTTP.
     * BeanUtils converte DTO para MODEL. Assim nao precisa criar um construtor para fazer essa converçao
     * BeanUtils tomar cuidado usar apenas em objetos simples, tem muitas limitaçoes .
     * BeanUtils pode usar o construtor para subistituir.
     * */
    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {

        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));

    }


    /*
    * Uma forma de fazer findAll
    *
    @GetMapping
    public ResponseEntity<List<ProductModel>> findAll(){

        List<ProductModel>  products = productRepository.findAll();
        return new ResponseEntity<>(products,HttpStatus.OK);

    }*/

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {

        List<ProductModel> productModelList = productRepository.findAll();
        if(!productModelList.isEmpty()){
            for (ProductModel item: productModelList ) {
                UUID id = item.getIdProduct();
                item.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(productModelList);
    }

    /*
     * @PathVariable pega o id e usa como parametro
     *
     * */

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {

        Optional<ProductModel> product1 = productRepository.findById(id);
        if (product1.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(product1.get());


    }

    @PutMapping ("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto) {

        //verifica se o recurso existe (ID)
        Optional<ProductModel> product1 = productRepository.findById(id);
        if (product1.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not foubd");
        }

        // Se ele existir salva ele na variavel
        var productModel = product1.get();

        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));


    }

    @DeleteMapping ("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {

        //verifica se o recurso existe (ID)
        Optional<ProductModel> product1 = productRepository.findById(id);
        if (product1.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not foubd");
        }


        productRepository.delete(product1.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado");


    }







}
