package mvc;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getAllProduct(Model model) {
        model.addAttribute("frontProduct", productService.getAllProduct());
        return "all_product";
    }

    @GetMapping("/remove/{id}")
    public String deleteProductById(@PathVariable int id) {
        productService.deleteById(id);
        return "redirect:/product/all";
    }

    @PostMapping("/add")
//    public String addNewProduct(@RequestParam int id, @RequestParam String title, @RequestParam int cost) {
    public String addNewProduct (@ModelAttribute Product product) {
//        Product product = new Product(id, title, cost);
        productService.save(product);
        return "redirect:/product/all";
    }

    @PostMapping("/json/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewJsonProduct(@RequestBody Product product) {
        productService.save(product);
    }
}
