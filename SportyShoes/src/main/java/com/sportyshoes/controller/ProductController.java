package com.sportyshoes.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sportyshoes.dao.ProductCategoryRepository;
import com.sportyshoes.dao.ProductRepository;
import com.sportyshoes.dao.PurchaseRepository;
import com.sportyshoes.model.ProductCategory;
import com.sportyshoes.model.Products;
import com.sportyshoes.model.Purchase;

@Controller
public class ProductController {
	@Autowired
	ProductCategoryRepository productCategoryRepo;

	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	PurchaseRepository purchaseRepo;

	@RequestMapping("/manage_category")
	public String manageProductCategory(Model model) {
		List<ProductCategory> productCategory = this.productCategoryRepo.findAll();
		model.addAttribute("title", "Sporty Shoes: Manage Product Category");
		model.addAttribute("product_category", productCategory);
		return "admin/manage_product_category";
	}

	@RequestMapping("/add_category")
	public String addProductCategory(Model model) {
		model.addAttribute("title", "Sporty Shoes: Add Product Category");
		return "admin/add_product_category";
	}

	@PostMapping("/saveProductCategory")
	public String saveProductCategory(@RequestParam("categoryName") String categoryName,
			@RequestParam("description") String description, @RequestParam("image") MultipartFile file, Model model) {
		ProductCategory productCategory = new ProductCategory();
		try {
			if (file.isEmpty()) {

			} else {
				productCategory.setCategoryName(categoryName);
				productCategory.setDescription(description);
				productCategory.setImage(file.getOriginalFilename());
				this.productCategoryRepo.save(productCategory);
				File saveFile = new ClassPathResource("static/admin/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			System.out.println("Error Message : " + e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("title", "Sporty Shoes: Add Product Category");
		return "admin/add_product_category";
	}

	@RequestMapping("/{id}/editProductCategory")
	public String editProductCategory(@PathVariable("id") Integer id, Model model) {
		ProductCategory productCategories = this.productCategoryRepo.findById(id).get();
		System.out.println("Product Category: " + productCategories);
		model.addAttribute("productCategory", productCategories);
		model.addAttribute("title", "Sporty Shoes: Edit Product Category");
		return "admin/edit_product_category";
	}

	@PostMapping("/updateProductCategory")
	public String updateProductCategory(@RequestParam("id") Integer id,
			@RequestParam("categoryName") String categoryName, @RequestParam("description") String description,
			@RequestParam("image") MultipartFile file, Model model) {
		ProductCategory productCategory = new ProductCategory();
		try {
			if (file.isEmpty()) {

			} else {
				productCategory.setId(id);
				productCategory.setCategoryName(categoryName);
				productCategory.setDescription(description);
				productCategory.setImage(file.getOriginalFilename());
				this.productCategoryRepo.save(productCategory);
				File saveFile = new ClassPathResource("static/admin/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			System.out.println("Error Message : " + e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("title", "Sporty Shoes: Edit Product Category");
		return "redirect:/" + id + "/editProductCategory";
	}

	@GetMapping("/deleteProductCategory/{id}")
	public String deleteProductCategory(@PathVariable("id") Integer id, Model model) {
		Optional<ProductCategory> productCatOptional = this.productCategoryRepo.findById(id);
		ProductCategory category = productCatOptional.get();
		this.productCategoryRepo.delete(category);
		return "redirect:/manage_category";
	}

	@RequestMapping("/manageProduct")
	public String manageProduct(Model model) {
		List<Products> products = this.productRepo.findAll();
		model.addAttribute("title", "Sporty Shoes: Manage Product");
		model.addAttribute("product", products);
		return "admin/manage_product";
	}

	@RequestMapping("/add_product")
	public String addProduct(Model model) {
		List<ProductCategory> productCategory = this.productCategoryRepo.findAll();
		model.addAttribute("product_category", productCategory);
		model.addAttribute("title", "Sporty Shoes: Add Product");
		return "admin/add_product";
	}

	@PostMapping("/saveProduct")
	public String saveProduct(@RequestParam("category") Integer category,
			@RequestParam("productName") String productName, @RequestParam("brand") String brand,
			@RequestParam("price") Double price, @RequestParam("colour") String colour,
			@RequestParam("size") Integer size, @RequestParam("shortDescription") String shortDescription,
			@RequestParam("longDescription") String longDescription, @RequestParam("image") MultipartFile file,
			Model model) {
		Products products = new Products();
		products.setProductName(productName);
		products.setBrand(brand);
		products.setPrice(price);
		products.setColour(colour);
		products.setSize(size);
		products.setShortDescription(shortDescription);
		products.setLongDescription(longDescription);
		products.setImage(file.getOriginalFilename());
		ProductCategory productCategory = this.productCategoryRepo.findById(category).get();
		products.setCategoryId(productCategory);
		this.productRepo.save(products);
		try {
			if (file.isEmpty()) {

			} else {
				File saveFile = new ClassPathResource("static/admin/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			System.out.println("Error Message : " + e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("title", "Sporty Shoes: Manage Product");
		return "redirect:/manageProduct";
	}

	@GetMapping("/editProduct/{id}")
	public String editProduct(@PathVariable("id") Integer id, Model model) {
		List<ProductCategory> productCategory = this.productCategoryRepo.findAll();
		model.addAttribute("product_category", productCategory);
		Products products = this.productRepo.findById(id).get();
		model.addAttribute("product", products);
		return "admin/edit_product";
	}

	@PostMapping("/updateProduct")
	public String update(@RequestParam("id") Integer id, @RequestParam("category") Integer category,
			@RequestParam("productName") String productName, @RequestParam("brand") String brand,
			@RequestParam("price") Double price, @RequestParam("colour") String colour,
			@RequestParam("size") Integer size, @RequestParam("shortDescription") String shortDescription,
			@RequestParam("longDescription") String longDescription, @RequestParam("image") MultipartFile file,
			Model model) {
		Products products = new Products();
		products.setId(id);
		products.setProductName(productName);
		products.setBrand(brand);
		products.setPrice(price);
		products.setColour(colour);
		products.setSize(size);
		products.setShortDescription(shortDescription);
		products.setLongDescription(longDescription);
		products.setImage(file.getOriginalFilename());
		ProductCategory productCategory = this.productCategoryRepo.findById(category).get();
		products.setCategoryId(productCategory);
		this.productRepo.save(products);
		try {
			if (file.isEmpty()) {

			} else {
				File saveFile = new ClassPathResource("static/admin/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			System.out.println("Error Message : " + e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("title", "Sporty Shoes: Manage Product");
		return "redirect:/manageProduct";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable("id") Integer id, Model model) {
		Optional<Products> productsOptional = this.productRepo.findById(id);
		Products products = productsOptional.get();
		this.productRepo.delete(products);
		model.addAttribute("title", "Sporty Shoes: Manage Product");
		return "redirect:/manageProduct";
	}
	
	@RequestMapping("/purchaseList")
	public String purchaseList(Model model) {
		List<Purchase> purchases = this.purchaseRepo.findAll();
		model.addAttribute("title", "Sporty Shoes: Purchase Report");
		model.addAttribute("purchase", purchases);
		return "admin/manage_purchase";
	}
}
