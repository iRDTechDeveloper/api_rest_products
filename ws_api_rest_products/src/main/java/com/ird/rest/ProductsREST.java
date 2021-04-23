package com.ird.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ird.dao.ProductsDAO;
import com.ird.entities.ProductsEntity;

@RestController
@RequestMapping("products")
public class ProductsREST {

	/*@GetMapping
		public ResponseEntity<ProductsEntity> getObtenerProducto() {
			ProductsEntity producto = new ProductsEntity();
			producto.setId(1L);
			producto.setName("Producto 1");
			return ResponseEntity.ok(producto);
	}*/
	
	@Autowired
	private ProductsDAO productsDao;
	
	@GetMapping
	public ResponseEntity<List<ProductsEntity>> getObtenerProducto() {
		List<ProductsEntity> listaProductos = productsDao.findAll();
		return ResponseEntity.ok(listaProductos);
	}
	
	@RequestMapping(value = "{productId}")
	public ResponseEntity<ProductsEntity> getObtenerProductoPorId(@PathVariable("productId") Long productId) {
		
		Optional<ProductsEntity> OptProductosPorId = productsDao.findById(productId);
		
		if (OptProductosPorId.isPresent()) {
			return ResponseEntity.ok(OptProductosPorId.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<ProductsEntity> crearProducto(@RequestBody ProductsEntity productsEntity){
		ProductsEntity nuevoProducto = productsDao.save(productsEntity);
		return ResponseEntity.ok(nuevoProducto);
	}
	
	@DeleteMapping(value = "{productId}")
	public ResponseEntity<ProductsEntity> borrarProducto(@PathVariable("productId") Long productId){
		productsDao.deleteById(productId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<ProductsEntity> actualizarProducto(@RequestBody ProductsEntity productsEntity){
		
		Optional<ProductsEntity> OptActualizarProductos = productsDao.findById(productsEntity.getId());
		
		if (OptActualizarProductos.isPresent()) {
			
			ProductsEntity updateProducto = OptActualizarProductos.get();
			updateProducto.setName(productsEntity.getName());
			productsDao.save(updateProducto);
			return ResponseEntity.ok(updateProducto);
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
