package com.app.productserv.controller;

import com.app.productserv.dto.Product;
import com.app.productserv.dto.Seller;
import com.app.productserv.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:60184"})
public class ProductController {

    @Autowired
    MetricsService metricsService;

    @GetMapping
    public List<Product> getAllProducts() {
        String[] names = {"Samsung Television", "Whirlpool Refrigerator", "Bosch Washing Machine",
                "Sansui Hair Dryer", "Samsung Inverter Air Conditoner | 1.5 Ton", "Preethi Mixer", "Bajaj Wet Grinder", "Orient Ceiling Fan", "Crompton Lights", "Lenovo TouchPad Laptop"};
        String[] imageUrls = {"https://media.istockphoto.com/id/486895337/id/foto/televisi-definisi-tinggi.jpg?s=1024x1024&w=is&k=20&c=X8hjFadNblubOuxFHfpeQ5q5SjA8GvgE_etC_7gHh5M=",
                                "https://vasanthandco.in/UploadedFiles/productimages/20241106052808-Untitled-1.png",
                                "https://whirlpoolindia.vtexassets.com/arquivos/ids/167803-1200-auto?v=638379748757530000&width=1200&height=auto&aspect=true",
                                "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcRlDk7DeA_5g5rVG-INc1MJU8KBCJ2AibxbS-Bxm6ZAkw6E_Caesti_ipYY42_0egY6p_phqOSHlYXIDF472HWluVRUVAFBiQEchPHc5zPR4ycEHeYpR18vvw",
                                "https://static.wixstatic.com/media/4af009_50b99ed648a4405980138b37e56d3abb~mv2.jpg/v1/fill/w_1000,h_580,al_c,q_85,usm_0.66_1.00_0.01/4af009_50b99ed648a4405980138b37e56d3abb~mv2.jpg",
                                "https://m.media-amazon.com/images/I/51bIN85P2-L._SL1107_.jpg",
                                "https://www.premierkitchen.in/wp-content/uploads/2023/05/Smart-Grinder.jpg",
                                "https://static1.industrybuying.com/products/electrical/electrical-appliances/ceiling-fans/ELE.CEI.67140801_1668385945498.webp",
                                "https://www.whiteteak.com/media/catalog/product/w/l/wl52-10001_6_.jpg?width=680&height=680&canvas=680,680&optimize=medium&fit=bounds",
                                "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"};
        String[] sellerNames = {"Ajay", "Vijay", "Sujitha",
                "Ramesh", "Supesh", "Suresh", "Surya", "Jamal", "Vimal", "Divya"};
        double[] ratings = { 2.5, 3.5, 4, 4.3, 5.0, 4.7, 3.1, 3.7, 4.4, 3.2};
        List<Product> products = new ArrayList<>();
        int id = 1;
        for(int i = 0;i<names.length; i++){
            Seller seller = new Seller(sellerNames[i], "m", true);
            if(seller.getSellerName().equalsIgnoreCase("Divya")) {
                seller.setMarried(false);
                seller.setGender("f");
            }

            if(seller.getSellerName().equalsIgnoreCase("sujitha")) {
                seller.setGender("f");
            }

            Product product = new Product(id, names[i], id * 100, seller);
            Map<String, String> featureMap = new HashMap<>();
            featureMap.put("Great Performance", "Latest intel i12 processor");
            featureMap.put("High Memory", "It has a ram of 32 GB");
            featureMap.put("Battery Life", "Battery life of 12 hours");
            featureMap.put("Quality Display", "Smooth Amoled Display");
            featureMap.put("High Graphics", "4 gb graphics card available");
            product.setFeatureDescMap(featureMap);
            product.setImageUrl(imageUrls[i]);
            product.setRating(ratings[i]);
            products.add(product);
            id++;
        }

        metricsService.publishLoginMetric(9);
        return products;
    }

    @GetMapping("/by-id")
    public Product getAllProducts(@RequestParam String id) {
         List<Product> products = getAllProducts();
            Product productById = products.stream()
                .filter(product -> product.getId() == Integer.parseInt(id))
                .findFirst().orElse(null);
         return productById;
    }

}